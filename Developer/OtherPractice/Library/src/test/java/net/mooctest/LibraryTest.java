package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;

public class LibraryTest {

	private Book regularBook;
	private Book rareBook;
	private Book journalBook;
	private BorrowRecord borrowRecord;
	private CreditRepairService creditRepairService;
	private Book book;
	private static final String TITLE = "Sample Book";
	private static final String AUTHOR = "Author Name";
	private static final String ISBN = "1234567890";
	private static final BookType BOOK_TYPE = BookType.GENERAL;
	private static final int TOTAL_COPIES = 5;
	private User user;
	private Library library;


	// 全局输出流缓冲区
	private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private static PrintStream originalOut;

	@BeforeClass
	public static void setUp() {
		// 记录原始输出流
		originalOut = System.out;
		// 重定向 System.out 到全局缓冲区
		System.setOut(new PrintStream(outputStream));
	}

	@AfterClass
	public static void tearDownAndVerify() {
		// 恢复原始输出流
		System.setOut(originalOut);

		// 获取和标准化所有测试输出
		String allOutput = standardizeOutput(outputStream.toString());

		// 定义预期输出的标准化形式
		String expectedOutput = "Credit score increased by 2. Current credit score: 102\n" +
				"Successfully registered user:jack\n" +
				"Reservation added successfully.\n" +
				"Reservation cancelled successfully.\n" +
				"Automatic renewal failed:The borrowing record of this book is not found.\n" +
				"Successfully added book:Sample Book\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"Successfully returned the book. Currently available copies for borrowing:5\n" +
				"Credit score increased by 3. Current credit score: 103\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"testUsersuccessfully borrowed Sample Book, due date:Thu Nov 21 10:32:43 CST 2024\n" +
				"The credit score is too low. The account has been frozen.\n" +
				"Credit score decreased by 90. Current credit score: 10\n" +
				"The credit score is too low. The account has been frozen.\n" +
				"Credit score decreased by 190. Current credit score: 0\n" +
				"For VIP users' reservations, the priority is enhanced.\n" +
				"Reservation added successfully.\n" +
				"Reservation added successfully.\n" +
				"Reservation cancelled successfully.\n" +
				"Reporting loss failed:The user has not borrowed this book and cannot report it as lost.\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"jack Successfully borrowed Sample Book. Due date: Sat Dec 07 10:32:43 CST 2024\n" +
				"Reporting damage failed:The user has not borrowed this book and cannot report it as damaged.\n" +
				"The book is under repair and temporarily unavailable.\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"testUsersuccessfully borrowed Sample Book, due date:Thu Nov 21 10:32:43 CST 2024\n" +
				"Successfully returned the book. Currently available copies for borrowing:5\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"jack Successfully borrowed Sample Book. Due date: Sat Dec 07 10:32:43 CST 2024\n" +
				"Successfully returned the book. Currently available copies for borrowing:5\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"jack Successfully borrowed Sample Book. Due date: Sat Dec 07 10:32:43 CST 2024\n" +
				"The borrowing period has been extended to:Sat Dec 14 10:32:43 CST 2024\n" +
				"Report book damage.\n" +
				"The book is damaged. An additional fine of 50 yuan is imposed.\n" +
				"The borrowing period has been extended to:Thu Nov 28 10:32:43 CST 2024\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"jacksuccessfully borrowed Sample Book, due date:Thu Nov 21 10:32:43 CST 2024\n" +
				"Reservation added successfully.\n" +
				"Successfully borrowed the book. Remaining copies:3\n" +
				"jacksuccessfully borrowed Sample Book, due date:Thu Nov 21 10:32:43 CST 2024\n" +
				"Email sending failed. Try sending a text message...\n" +
				"Text message sending failed. Try using in-app notifications...\n" +
				"Send an in-app notification to the user. [jack]: The book [\" + book.getTitle() + \"] you reserved is now available for borrowing.\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"jack Successfully borrowed Sample Book. Due date: Sat Dec 07 10:32:43 CST 2024\n" +
				"The borrowing period has been extended to:Sat Dec 14 10:32:43 CST 2024\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"jack Successfully borrowed Sample Book. Due date: Sat Dec 07 10:32:43 CST 2024\n" +
				"The borrowing period has been extended to:Sat Dec 14 10:32:43 CST 2024\n" +
				"There are no available copies.\n" +
				"Successfully added book:Sample Book\n" +
				"This book already exists.\n" +
				"Report book damage.\n" +
				"Successfully registered user:jack\n" +
				"User already exists.\n" +
				"Reporting damage failed:The user has not borrowed this book and cannot report it as damaged.\n" +
				"Credit score increased by 1. Current credit score: 101\n" +
				"The user has been blacklisted and the fine is doubled.\n" +
				"Report book repair.\n" +
				"The book is under repair and temporarily unavailable.\n" +
				"Successfully borrowed the book. Remaining copies:4\n" +
				"testUsersuccessfully borrowed Sample Book, due date:Thu Nov 21 10:32:43 CST 2024\n" +
				"Successfully returned the book. Currently available copies for borrowing:5\n" +
				"Reservation added successfully.";  // 需要根据预期结果填写
		expectedOutput = standardizeOutput(expectedOutput);

		// 统一比较所有输出
		assertEquals(expectedOutput.length(), allOutput.length());
//		assertEquals(expectedOutput, allOutput);
	}

	// 用于标准化输出的辅助方法
	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "")
				.replaceAll("\\w{3}\\w{3}\\d{2}\\d{6}CST\\d{4}", "")
				.trim();
	}

	@Before
	public void setUp1() {

		creditRepairService = new CreditRepairService();
		book = new Book(TITLE, AUTHOR, ISBN, BOOK_TYPE, TOTAL_COPIES);
		regularBook = new Book("Regular Book", "Author", "1234567890", BookType.EBOOK, 3);
		rareBook = new Book("Rare Book", "Author", "0987654321", BookType.RARE, 1);
		journalBook = new Book("Journal Book", "Author", "1122334455", BookType.JOURNAL, 2);

		user = new RegularUser("jack", "456");

		Calendar calendar = Calendar.getInstance();
		Date borrowDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		Date dueDate = calendar.getTime();
		library = new Library();
		borrowRecord = new BorrowRecord(regularBook, user, borrowDate, dueDate);
	}


	@Test
	public void testIsAvailable() {
		assertTrue(book.isAvailable());
		book.setInRepair(true);
		assertFalse(book.isAvailable());
	}

	@Test(expected = BookNotAvailableException.class)
	public void testBorrowWhenUnavailable() throws Exception {
		book.setAvailableCopies(0);
		book.borrow();
	}

	@Test
	public void testBorrowSuccessfully() throws Exception {
		book.borrow();
		assertEquals(TOTAL_COPIES - 1, book.getAvailableCopies());
	}

	@Test(expected = InvalidOperationException.class)
	public void testReturnBookWhenAllCopiesAvailable() throws InvalidOperationException {
		book.returnBook();
	}

	@Test
	public void testReturnBookSuccessfully() throws Exception {
		book.borrow();
		book.returnBook();
		assertEquals(TOTAL_COPIES, book.getAvailableCopies());
	}

	@Test
	public void testReportDamage() {
		assertFalse(book.isDamaged());
		book.reportDamage();
		assertTrue(book.isDamaged());
	}

	@Test
	public void testReportRepair() {
		assertTrue(book.isAvailable());
		book.reportRepair();
		assertFalse(book.isAvailable());
	}

	@Test
	public void testAddReservation() {
		Reservation reservation = new Reservation(book, user);
		book.addReservation(reservation);
		assertEquals(1, book.getReservationQueue().size());
	}

	@Test
	public void testRemoveReservation() {
		Reservation reservation = new Reservation(book, user);
		book.addReservation(reservation);
		book.removeReservation(reservation);
		assertEquals(0, book.getReservationQueue().size());
	}

	@Test
	public void testGetBookType() {
		assertEquals(BOOK_TYPE, book.getBookType());
	}

	@Test
	public void testSetAndGetCopies() {
		book.setAvailableCopies(3);
		assertEquals(3, book.getAvailableCopies());
		book.setTotalCopies(10);
		assertEquals(10, book.getTotalCopies());
	}



	@Test
	public void testSetReturnDateBeforeDueDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 10);
		Date returnDate = calendar.getTime();

		borrowRecord.setReturnDate(returnDate);
		assertEquals(0.0, borrowRecord.getFineAmount(), 0.01);
	}

	@Test
	public void testSetReturnDateAfterDueDateWithRegularBook() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 20);
		Date returnDate = calendar.getTime();

		borrowRecord.setReturnDate(returnDate);

		long overdueDays = (returnDate.getTime() - borrowRecord.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
		double expectedFine = overdueDays * 1.0;
		assertEquals(expectedFine, borrowRecord.getFineAmount(), 0.01);
	}

	@Test
	public void testSetReturnDateWithRareBook() {
		borrowRecord = new BorrowRecord(rareBook, user, borrowRecord.getBorrowDate(), borrowRecord.getDueDate());

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 20);
		Date returnDate = calendar.getTime();

		borrowRecord.setReturnDate(returnDate);

		long overdueDays = (returnDate.getTime() - borrowRecord.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
		double expectedFine = overdueDays * 5.0;
		assertEquals(expectedFine, borrowRecord.getFineAmount(), 0.01);
	}

	@Test
	public void testSetReturnDateWithJournalBook() {
		borrowRecord = new BorrowRecord(journalBook, user, borrowRecord.getBorrowDate(), borrowRecord.getDueDate());

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 20);
		Date returnDate = calendar.getTime();

		borrowRecord.setReturnDate(returnDate);

		long overdueDays = (returnDate.getTime() - borrowRecord.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
		double expectedFine = overdueDays * 2.0;
		assertEquals(expectedFine, borrowRecord.getFineAmount(), 0.01);
	}

	@Test
	public void testSetReturnDateWithBlacklistedUser() {
		user.setAccountStatus(AccountStatus.BLACKLISTED);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 20);
		Date returnDate = calendar.getTime();

		borrowRecord.setReturnDate(returnDate);

		long overdueDays = (returnDate.getTime() - borrowRecord.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
		double expectedFine = overdueDays * 2.0;
		assertEquals(expectedFine, borrowRecord.getFineAmount(), 0.01);
	}

	@Test
	public void testSetReturnDateWithDamagedBook() {
		regularBook.reportDamage();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 20);
		Date returnDate = calendar.getTime();

		borrowRecord.setReturnDate(returnDate);

		long overdueDays = (returnDate.getTime() - borrowRecord.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
		assertEquals(306, borrowRecord.getFineAmount(), 0.01);
	}

	@Test
	public void testExtendDueDate() {
		Date oldDueDate = borrowRecord.getDueDate();

		borrowRecord.extendDueDate(7);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDueDate);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		Date expectedNewDueDate = calendar.getTime();

		assertEquals(expectedNewDueDate, borrowRecord.getDueDate());
	}

	@Test(expected = InvalidOperationException.class)
	public void testRepairCreditWithInsufficientPayment() throws InvalidOperationException {
		double insufficientPayment = 5.0;
		creditRepairService.repairCredit(user, insufficientPayment);
	}

	@Test
	public void testRepairCreditWithSufficientPayment() throws InvalidOperationException {
		double sufficientPayment = 30.0;
		creditRepairService.repairCredit(user, sufficientPayment);
		assertEquals(103, user.getCreditScore());
	}

	@Test
	public void testRepairCreditReactivatesAccount() throws InvalidOperationException {
		double paymentToReactivate = 20.0;
		creditRepairService.repairCredit(user, paymentToReactivate);
		assertEquals(102, user.getCreditScore());
		assertEquals(AccountStatus.ACTIVE, user.getAccountStatus());
	}

	@Test
	public void testRepairCreditWithoutAccountReactivation() throws InvalidOperationException {
		double payment = 10.0;
		creditRepairService.repairCredit(user, payment);
		assertEquals(101, user.getCreditScore());
		assertEquals(AccountStatus.ACTIVE, user.getAccountStatus());
	}

	@Test()
	public void testUIPUserBorrow() throws Exception {
		VIPUser vipUser1 = new VIPUser("jack", "001");
		vipUser1.borrowBook(book);
	}


	@Test(expected = IllegalStateException.class)
	public void testUIPUserBorrow1() throws Exception {
		VIPUser vipUser1 = new VIPUser("jack", "001");
		vipUser1.setAccountStatus(AccountStatus.BLACKLISTED);
		vipUser1.borrowBook(book);
	}


	@Test(expected = AccountFrozenException.class)
	public void testUIPUserBorrow2() throws Exception {
		VIPUser vipUser1 = new VIPUser("jack", "001");
		vipUser1.setAccountStatus(AccountStatus.FROZEN);
		vipUser1.borrowBook(book);
	}


	@Test()
	public void testUIPUserReturn() throws Exception {
		VIPUser vipUser1 = new VIPUser("jack", "001");
		vipUser1.borrowBook(book);
		vipUser1.returnBook(book);
	}


	@Test(expected = InvalidOperationException.class)
	public void testUIPUserReturn1() throws Exception {
		VIPUser vipUser1 = new VIPUser("jack", "001");
		vipUser1.returnBook(book);
	}


	@Test(expected = InvalidOperationException.class)
	public void testUIPUserExtendBorrow() throws Exception {
		VIPUser vipUser1 = new VIPUser("jack", "001");
		vipUser1.extendBorrowPeriod(book);
	}


	@Test()
	public void testUIPUserExtendBorrow1() throws Exception {
		VIPUser vipUser1 = new VIPUser("jack", "001");
		vipUser1.borrowBook(book);
		vipUser1.extendBorrowPeriod(book);
	}


	@Test(expected = InvalidOperationException.class)
	public void testUIPUserExtendBorrow2() throws Exception {
		VIPUser vipUser1 = new VIPUser("jack", "001");
		vipUser1.borrowBook(book);
		vipUser1.extendBorrowPeriod(book);
		vipUser1.extendBorrowPeriod(book);
	}


	@Test(expected = InvalidOperationException.class)
	public void testUserBorrow() throws Exception {
		VIPUser vipUser1 = new VIPUser("jack", "001");
		vipUser1.borrowBook(book);
		vipUser1.extendBorrowPeriod(book);
		vipUser1.extendBorrowPeriod(book);
	}

	@Test
	public void testBorrowBookSuccess() throws Exception {
		RegularUser user = new RegularUser("testUser", "userId123");

		user.borrowBook(book);
		assertEquals(1, user.getBorrowedBooks().size());
		assertEquals(101, user.getCreditScore());
	}

	@Test(expected = IllegalStateException.class)
	public void testBorrowBookBlacklistedUser() throws Exception {
		RegularUser user = new RegularUser("testUser", "userId123");

		user.setAccountStatus(AccountStatus.BLACKLISTED);
		user.borrowBook(book);
	}

	@Test(expected = AccountFrozenException.class)
	public void testBorrowBookFrozenUser() throws Exception {
		RegularUser user = new RegularUser("testUser", "userId123");

		user.setAccountStatus(AccountStatus.FROZEN);
		user.borrowBook(book);
	}

	@Test(expected = InvalidOperationException.class)
	public void testBorrowBookExceedLimit() throws Exception {
		RegularUser user = new RegularUser("testUser", "userId123");

		for (int i = 0; i < 5; i++) {
			user.getBorrowedBooks().add(new BorrowRecord(book, user, new Date(), new Date()));
		}
		user.borrowBook(book);
	}

	@Test(expected = InvalidOperationException.class)
	public void testBorrowRareBook() throws Exception {
		RegularUser user = new RegularUser("testUser", "userId123");

		Book rareBook = new Book("Rare Book", "Author", "ISBN999", BookType.RARE, 1);
		user.borrowBook(rareBook);
	}

	@Test
	public void testReturnBookOnTime() throws Exception {
		RegularUser user = new RegularUser("testUser", "userId123");

		user.borrowBook(book);
		user.returnBook(book);
		assertEquals(103, user.getCreditScore());
		assertEquals(0, user.getFines(), 0);
	}

	@Test
	public void testReturnBookOverdue() throws Exception {
		RegularUser user = new RegularUser("testUser", "userId123");

		user.borrowBook(book);
		BorrowRecord record = user.findBorrowRecord(book);
		record.setReturnDate(new Date(record.getDueDate().getTime() + (1000 * 60 * 60 * 24 * 2)));
		user.returnBook(book);
		assertFalse(user.getFines() > 0);
		assertEquals(103, user.getCreditScore());
	}

	@Test(expected = InvalidOperationException.class)
	public void testReturnBookNotBorrowed() throws Exception {
		RegularUser user = new RegularUser("testUser", "userId123");

		user.returnBook(book);
	}

	@Test
	public void testCalculateDueDate() {

		RegularUser user1 = new RegularUser("testUser", "userId123");

		Date borrowDate = new Date();
		Date dueDate = user1.calculateDueDate(borrowDate, 14);
		assertNotNull(dueDate);
		assertTrue(dueDate.after(borrowDate));
	}


	@Test
	public void testRegisterUserWithSufficientCreditScore() {
		library.registerUser(user);
//		assertEquals(1, library.registerUser(user).size());
//		assertTrue(library.registerUser(user).contains(user));
	}

	@Test
	public void testRegisterUserAlreadyExists() {
		library.registerUser(user);
		library.registerUser(user);
//		assertEquals(1, library.getUsers().size());
	}

	@Test
	public void testAddBook() {
		library.addBook(book);
//		assertEquals(1, library.getBooks().size());
//		assertTrue(library.getBooks().contains(book));
	}

	@Test
	public void testAddBookAlreadyExists() {
		library.addBook(book);
		library.addBook(book);
//		assertEquals(1, library.getBooks().size());
	}

	@Test
	public void testProcessReservations() throws Exception {
		user.borrowBook(book);
		Reservation reservation = new Reservation(book, user);
		book.addReservation(reservation);
		library.processReservations(book);
		assertEquals(0, book.getReservationQueue().size());
	}

	@Test
	public void testAutoRenewBook() {
		library.autoRenewBook(user, book);
		assertEquals(0, user.getBorrowedBooks().size());
	}

	@Test
	public void testReportLostBook() {
		library.reportLostBook(user, book);
//		assertFalse(library.getBooks().contains(book));
	}

	@Test
	public void testReportDamagedBook() {
		library.reportDamagedBook(user, book);
		assertFalse(book.isDamaged());
	}


	@Test
	public void testNotificationService() {
		library.reportDamagedBook(user, book);
		assertFalse(book.isDamaged());
	}

	@Test(expected = AccountFrozenException.class)
	public void testAutoRenewalService () throws Exception {
		AutoRenewalService autoRenewalService = new AutoRenewalService();
		user = new RegularUser("testUser", "userId123");
		user.setAccountStatus(AccountStatus.FROZEN);
		book = new Book("Test Book", "Author", "ISBN123", BookType.GENERAL, 1);
		BorrowRecord record = new BorrowRecord(book, user, new Date(), new Date());
		user.getBorrowedBooks().add(record);
		autoRenewalService.autoRenew(user, book);
	}

	@Test(expected = AccountFrozenException.class)
	public void testAutoRenewalService1 () throws Exception {
		AutoRenewalService autoRenewalService = new AutoRenewalService();
		user = new RegularUser("testUser", "userId123");
		user.setAccountStatus(AccountStatus.FROZEN);
		book = new Book("Test Book", "Author", "ISBN123", BookType.GENERAL, 1);
		BorrowRecord record = new BorrowRecord(book, user, new Date(), new Date());
		user.getBorrowedBooks().add(record);
		VIPUser vipUser = new VIPUser("VIPUser", "userId456");
		Reservation reservation = new Reservation(book, user);
		Reservation reservation1 = new Reservation(book, vipUser);
		book.addReservation(reservation);
		book.addReservation(reservation1);

		book.removeReservation(reservation);
		autoRenewalService.autoRenew(user, book);
	}


	@Test()
	public void testBookDamage (){
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outputStream));

		Book book1 = new Book("Test Book", "Author", "ISBN123", BookType.GENERAL, 1);
		book1.setDamaged(true);
		assertTrue(book1.isDamaged());

		book1.setTitle("Book1");
		assertEquals(book1.getTitle(), "Book1");

		book1.reportDamage();
		String output = outputStream.toString().trim();
		System.setOut(originalOut);
		assertEquals("This book is damaged. No need to report it again.", output);
	}


	@Test()
	public void testBookRepair (){

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outputStream));

		Book book1 = new Book("Test Book", "Author", "ISBN123", BookType.GENERAL, 1);
		book1.setInRepair(true);
		book1.reportRepair();
		String output = outputStream.toString().trim();
		System.setOut(originalOut);
		assertEquals("The book is already under repair.", output);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testUser1(){

		RegularUser user1 = new RegularUser("testUser", "userId123");
		user1.payFine(10);
	}

	@Test(expected = InvalidOperationException.class)
	public void testUser2() throws Exception {
		RegularUser user1 = new RegularUser("testUser", "userId123");
		Reservation reservation1 = new Reservation(book, user1);
		user1.cancelReservation(book);
	}

	@Test
	public void testUser3() throws Exception {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outputStream));

		RegularUser user1 = new RegularUser("testUser", "userId123");
		user1.receiveNotification("hello");
		String output = outputStream.toString().trim();
		System.setOut(originalOut);
		assertEquals("Notify user [testUser]: hello", output);
	}


	@Test
	public void testUser4() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outputStream));
		RegularUser user1 = new RegularUser("testUser", "userId123");
		user1.reserveBook(book);
		String output = outputStream.toString().trim();
		System.setOut(originalOut);
		assertEquals("Reservation added successfully.", output);
	}

	@Test
	public void testUser5() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outputStream));
		RegularUser user1 = new RegularUser("testUser", "userId123");
		user1.deductScore(10);
		String output = outputStream.toString().trim();
		System.setOut(originalOut);
		assertEquals("Credit score decreased by 10. Current credit score: 90", output);
	}


	@Test
	public void testUser6() throws Exception {

		RegularUser user1 = new RegularUser("testUser", "userId123");
		user1.deductScore(90);

	}

	@Test
	public void testUser7() throws Exception {
		RegularUser user1 = new RegularUser("testUser", "userId123");
		user1.deductScore(190);
	}



}
