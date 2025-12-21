package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryTest {
    static PrintStream originOutput = System.out;
    static ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeClass
    public static void setUp() throws Exception {
        System.setOut(new PrintStream(output));
    }

    @AfterClass
    public static void after() throws Exception {
        System.setOut(originOutput);
        String str = output.toString();

        str = str.replaceAll("\n", "1").replaceAll("\\s", "").replaceAll("\\p{C}", "").replaceAll("[^a-zA-Z0-9]", "");
        assertEquals(11391.00, str.length(), 30.00);
    }
    
    @Test
    public void test0() throws Exception {
        Book book0 = new Book("book0", "author0", "00", BookType.GENERAL, 5);
        Book book1 = new Book("book1", "author1", "01", BookType.RARE, 5);
        Book book2 = new Book("book2", "author2", "02", BookType.JOURNAL, 5);
        Book book3 = new Book("book3", "author3", "03", BookType.EBOOK, 2);
        Book book4 = new Book("book4", "author4", "04", BookType.GENERAL, 5);
        Book book5 = new Book("book5", "author5", "05", BookType.EBOOK, 5);
        Book book6 = new Book("book6", "author0", "06", BookType.RARE, 5);
        Book book7 = new Book("book7", "author1", "07", BookType.JOURNAL, 5);
        Book book8 = new Book("book8", "author2", "08", BookType.RARE, 5);
        Book book9 = new Book("book9", "author3", "09", BookType.GENERAL, 5);
        Book book10 = new Book("book10", "author4", "10", BookType.RARE, 5);
        Book book11 = new Book("book11", "author0", "11", BookType.GENERAL, 5);
        Book book12 = new Book("book12", "author1", "12", BookType.RARE, 5);
        Book book13 = new Book("book13", "author2", "13", BookType.EBOOK, 5);
        Book book14 = new Book("book14", "author3", "14", BookType.JOURNAL, 5);
        Book book15 = new Book("book15", "author0", "15", BookType.EBOOK, 5);

        RegularUser regularUser0 = new RegularUser("regularUser0", "00");
        RegularUser regularUser1 = new RegularUser("regularUser1", "01");
        RegularUser regularUser2 = new RegularUser("regularUser2", "02");
        RegularUser regularUser3 = new RegularUser("regularUser3", "03");

        VIPUser vipUser0 = new VIPUser("vipUser0", "10");
        VIPUser vipUser1 = new VIPUser("vipUser1", "11");
        VIPUser vipUser2 = new VIPUser("vipUser2", "12");
        VIPUser vipUser3 = new VIPUser("vipUser3", "13");

        Library library = new Library();
        library.addBook(book0);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
        library.addBook(book7);
        library.addBook(book8);
        library.addBook(book9);
        library.addBook(book10);
        library.addBook(book11);
        try {
            library.addBook(book11);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        library.addBook(book12);
        library.addBook(book13);
        library.addBook(book14);
        library.addBook(book15);

        library.registerUser(regularUser0);
        library.registerUser(regularUser1);
        library.registerUser(regularUser2);
        library.registerUser(regularUser3);

        library.registerUser(vipUser0);
        library.registerUser(vipUser1);
        library.registerUser(vipUser2);
        library.registerUser(vipUser3);

        //vip
        vipUser0.borrowBook(book0);

        try {
            vipUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            vipUser0.returnBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            vipUser0.creditScore = 49;
            library.registerUser(vipUser0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            library.registerUser(vipUser1);
            library.registerUser(vipUser1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        vipUser0.creditScore = 90;

        vipUser0.borrowBook(book2);
        vipUser0.borrowBook(book3);
        vipUser0.borrowBook(book4);
        vipUser0.borrowBook(book5);

        try {
            vipUser0.borrowBook(book6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        vipUser0.returnBook(book0);
        vipUser0.returnBook(book2);
        vipUser0.returnBook(book3);


        vipUser0.accountStatus = AccountStatus.BLACKLISTED;
        try {
            vipUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        vipUser0.accountStatus = AccountStatus.FROZEN;
        try {
            vipUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        vipUser0.accountStatus = AccountStatus.ACTIVE;
        try {
            book1.reportDamage();
            vipUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            book1.reportRepair();
            vipUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            vipUser0.creditScore = 59;
            vipUser0.borrowBook(book3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            vipUser1.borrowBook(book3);
            vipUser1.borrowBook(book3);
            book3.borrow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        vipUser0.fines = 51;
        try {
            vipUser0.borrowBook(book10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            vipUser3.borrowBook(book13);
            vipUser3.fines = 101;
            vipUser3.returnBook(book13);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        BorrowRecord record = new BorrowRecord(book11, vipUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.calculateFine());

        book11 = new Book("book11", "author0", "11", BookType.JOURNAL, 2);
        record = new BorrowRecord(book11, vipUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.calculateFine());

        book11 = new Book("book11", "author0", "11", BookType.RARE, 2);
        record = new BorrowRecord(book11, vipUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.calculateFine());

        vipUser2.accountStatus = AccountStatus.BLACKLISTED;
        record = new BorrowRecord(book11, vipUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.getDueDate());
        System.out.println(record.calculateFine());

        book11.setDamaged(true);
        record = new BorrowRecord(book11, vipUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.calculateFine());
        record.setReturnDate(null);
        System.out.println(record.calculateFine());
        System.out.println(record.getDueDate());


        library.processReservations(book6);
        book6.setDamaged(true);
        book6.addReservation(new Reservation(book6, vipUser0));
        book5.addReservation(new Reservation(book5, vipUser2));
        vipUser2.accountStatus = AccountStatus.ACTIVE;
        library.processReservations(book6);
        library.processReservations(book5);
        book5.addReservation(new Reservation(book5, vipUser2));
        vipUser2.accountStatus = AccountStatus.BLACKLISTED;
        library.processReservations(book5);
        library.processReservations(book5);

        library.autoRenewBook(vipUser3, book4);
        vipUser3.accountStatus = AccountStatus.ACTIVE;
        vipUser3.fines = 49;
        vipUser3.borrowBook(book4);
        library.autoRenewBook(vipUser3, book4);
        vipUser3.fines = 51;
        library.autoRenewBook(vipUser3, book4);

        library.repairUserCredit(vipUser3, 10);
        library.repairUserCredit(vipUser3, 0);
        vipUser3.creditScore = 49;
        library.repairUserCredit(vipUser3, 10);

        vipUser3.creditScore = 89;
        vipUser3.accountStatus = AccountStatus.ACTIVE;
        vipUser3.fines = 9;
        library.reportDamagedBook(vipUser3, book4);
        vipUser3.borrowBook(book5);
        library.reportDamagedBook(vipUser3, book5);
        library.reportLostBook(vipUser3, book4);
        vipUser3.borrowBook(book5);
        vipUser3.borrowedBooks.add(new BorrowRecord(book5, vipUser3, new Date(), new Date()));
        library.reportLostBook(vipUser3, book5);

        try {
            vipUser3.payFine(11);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        vipUser3.creditScore = 89;
        vipUser3.accountStatus = AccountStatus.ACTIVE;
        vipUser3.fines = 9;
        vipUser3.payFine(9);

        try {
            vipUser3.accountStatus = AccountStatus.BLACKLISTED;
            vipUser3.payFine(9);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        vipUser3.creditScore = 89;
        vipUser3.accountStatus = AccountStatus.FROZEN;
        vipUser3.fines = 19;
        vipUser3.payFine(19);
        vipUser3.fines = 19;
        vipUser3.payFine(9);
        System.out.println(vipUser3.getFines());

        vipUser3.reserveBook(book5);
        try {
            vipUser3.accountStatus = AccountStatus.FROZEN;
            vipUser3.reserveBook(book5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            vipUser3.accountStatus = AccountStatus.BLACKLISTED;
            vipUser3.reserveBook(book5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            vipUser3.accountStatus = AccountStatus.ACTIVE;
            vipUser3.creditScore = 49;
            vipUser3.reserveBook(book5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            vipUser3.accountStatus = AccountStatus.ACTIVE;
            vipUser3.creditScore = 51;
            vipUser3.reserveBook(book5);
            book5.setDamaged(true);
            vipUser3.reserveBook(book5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        vipUser3.cancelReservation(book5);
        try {
            vipUser3.cancelReservation(book4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        vipUser3.receiveNotification("message");
        vipUser3.accountStatus = AccountStatus.BLACKLISTED;
        vipUser3.receiveNotification("message");

        try {
            vipUser3.addScore(10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        vipUser3.deductScore(10);
        vipUser3.creditScore = 9;
        vipUser3.deductScore(10);
        vipUser3.creditScore = 49;
        vipUser3.accountStatus = AccountStatus.ACTIVE;
        vipUser3.deductScore(1);
        vipUser3.creditScore = 51;
        vipUser3.accountStatus = AccountStatus.ACTIVE;
        vipUser3.deductScore(1);

        vipUser3.setEmail("email");
        System.out.println(vipUser3.getEmail());
        vipUser3.setPhoneNumber("111");
        System.out.println(vipUser3.getPhoneNumber());

        BorrowRecord record1 = vipUser3.findBorrowRecord(book5);

    }

    @Test
    public void test() throws Exception {
        Book book0 = new Book("book0", "author0", "00", BookType.GENERAL, 5);
        Book book1 = new Book("book1", "author1", "01", BookType.RARE, 5);
        Book book2 = new Book("book2", "author2", "02", BookType.JOURNAL, 5);
        Book book3 = new Book("book3", "author3", "03", BookType.EBOOK, 2);
        Book book4 = new Book("book4", "author4", "04", BookType.GENERAL, 5);
        Book book5 = new Book("book5", "author5", "05", BookType.EBOOK, 5);
        Book book6 = new Book("book6", "author0", "06", BookType.RARE, 5);
        Book book7 = new Book("book7", "author1", "07", BookType.JOURNAL, 5);
        Book book8 = new Book("book8", "author2", "08", BookType.RARE, 5);
        Book book9 = new Book("book9", "author3", "09", BookType.GENERAL, 5);
        Book book10 = new Book("book10", "author4", "10", BookType.RARE, 5);
        Book book11 = new Book("book11", "author0", "11", BookType.GENERAL, 5);
        Book book12 = new Book("book12", "author1", "12", BookType.RARE, 5);
        Book book13 = new Book("book13", "author2", "13", BookType.EBOOK, 5);
        Book book14 = new Book("book14", "author3", "14", BookType.JOURNAL, 5);
        Book book15 = new Book("book15", "author0", "15", BookType.EBOOK, 5);

        RegularUser regularUser0 = new RegularUser("regularUser0", "00");
        RegularUser regularUser1 = new RegularUser("regularUser1", "01");
        RegularUser regularUser2 = new RegularUser("regularUser2", "02");
        RegularUser regularUser3 = new RegularUser("regularUser3", "03");

        VIPUser vipUser0 = new VIPUser("vipUser0", "10");
        VIPUser vipUser1 = new VIPUser("vipUser1", "11");
        VIPUser vipUser2 = new VIPUser("vipUser2", "12");
        VIPUser vipUser3 = new VIPUser("vipUser3", "13");

        Library library = new Library();
        library.addBook(book0);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
        library.addBook(book7);
        library.addBook(book8);
        library.addBook(book9);
        library.addBook(book10);
        library.addBook(book11);
		try {
			library.addBook(book11);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        library.addBook(book12);
        library.addBook(book13);
        library.addBook(book14);
        library.addBook(book15);

        library.registerUser(regularUser0);
        library.registerUser(regularUser1);
        library.registerUser(regularUser2);
        library.registerUser(regularUser3);

        library.registerUser(vipUser0);
        library.registerUser(vipUser1);
        library.registerUser(vipUser2);
        library.registerUser(vipUser3);

        regularUser0.borrowBook(book0);

        try {
            regularUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            regularUser0.returnBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            regularUser0.creditScore = 49;
			library.registerUser(regularUser0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            library.registerUser(regularUser1);
            library.registerUser(regularUser1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		regularUser0.creditScore = 90;

        regularUser0.borrowBook(book2);
        regularUser0.borrowBook(book3);
        regularUser0.borrowBook(book4);
        regularUser0.borrowBook(book5);

        try {
            regularUser0.borrowBook(book6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        regularUser0.returnBook(book0);
        regularUser0.returnBook(book2);
        regularUser0.returnBook(book3);


        regularUser0.accountStatus = AccountStatus.BLACKLISTED;
        try {
            regularUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        regularUser0.accountStatus = AccountStatus.FROZEN;
        try {
            regularUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        regularUser0.accountStatus = AccountStatus.ACTIVE;
        try {
            book1.reportDamage();
            regularUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            book1.reportRepair();
            regularUser0.borrowBook(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            regularUser0.creditScore = 59;
            regularUser0.borrowBook(book3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            regularUser1.borrowBook(book3);
            regularUser1.borrowBook(book3);
            book3.borrow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        regularUser0.fines = 51;
        try {
            regularUser0.borrowBook(book10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            regularUser3.borrowBook(book13);
            regularUser3.fines = 101;
            regularUser3.returnBook(book13);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        BorrowRecord record = new BorrowRecord(book11, regularUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.calculateFine());

        book11 = new Book("book11", "author0", "11", BookType.JOURNAL, 2);
        record = new BorrowRecord(book11, regularUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.calculateFine());

        book11 = new Book("book11", "author0", "11", BookType.RARE, 2);
        record = new BorrowRecord(book11, regularUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.calculateFine());

        regularUser2.accountStatus = AccountStatus.BLACKLISTED;
        record = new BorrowRecord(book11, regularUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.getDueDate());
        System.out.println(record.calculateFine());

        book11.setDamaged(true);
        record = new BorrowRecord(book11, regularUser2, simpleDateFormat.parse("2000-10-27"), simpleDateFormat.parse("2000-11-27"));
        record.setReturnDate(simpleDateFormat.parse("2001-11-28"));
        System.out.println(record.calculateFine());
        record.setReturnDate(null);
        System.out.println(record.calculateFine());
        System.out.println(record.getDueDate());


        library.processReservations(book6);
		book6.setDamaged(true);
		book6.addReservation(new Reservation(book6, regularUser0));
		book5.addReservation(new Reservation(book5, regularUser2));
        regularUser2.accountStatus = AccountStatus.ACTIVE;
		library.processReservations(book6);
		library.processReservations(book5);
        book5.addReservation(new Reservation(book5, regularUser2));
        regularUser2.accountStatus = AccountStatus.BLACKLISTED;
        library.processReservations(book5);
        library.processReservations(book5);

        library.autoRenewBook(regularUser3, book4);
        regularUser3.accountStatus = AccountStatus.ACTIVE;
        regularUser3.fines = 49;
        regularUser3.borrowBook(book4);
        library.autoRenewBook(regularUser3, book4);
        regularUser3.fines = 51;
        library.autoRenewBook(regularUser3, book4);

        library.repairUserCredit(regularUser3, 10);
        library.repairUserCredit(regularUser3, 0);
        regularUser3.creditScore = 49;
        library.repairUserCredit(regularUser3, 10);

        regularUser3.creditScore = 89;
        regularUser3.accountStatus = AccountStatus.ACTIVE;
        regularUser3.fines = 9;
        library.reportDamagedBook(regularUser3, book4);
        regularUser3.borrowBook(book5);
        library.reportDamagedBook(regularUser3, book5);
        library.reportLostBook(regularUser3, book4);
        regularUser3.borrowBook(book5);
        regularUser3.borrowedBooks.add(new BorrowRecord(book5, regularUser3, new Date(), new Date()));
        library.reportLostBook(regularUser3, book5);

        try {
            regularUser3.payFine(11);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        regularUser3.creditScore = 89;
        regularUser3.accountStatus = AccountStatus.ACTIVE;
        regularUser3.fines = 9;
        regularUser3.payFine(9);

        try {
            regularUser3.accountStatus = AccountStatus.BLACKLISTED;
            regularUser3.payFine(9);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        regularUser3.creditScore = 89;
        regularUser3.accountStatus = AccountStatus.FROZEN;
        regularUser3.fines = 19;
        regularUser3.payFine(19);
        regularUser3.fines = 19;
        regularUser3.payFine(9);
        System.out.println(regularUser3.getFines());

        regularUser3.reserveBook(book5);
        try {
            regularUser3.accountStatus = AccountStatus.FROZEN;
            regularUser3.reserveBook(book5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            regularUser3.accountStatus = AccountStatus.BLACKLISTED;
            regularUser3.reserveBook(book5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            regularUser3.accountStatus = AccountStatus.ACTIVE;
            regularUser3.creditScore = 49;
            regularUser3.reserveBook(book5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            regularUser3.accountStatus = AccountStatus.ACTIVE;
            regularUser3.creditScore = 51;
            regularUser3.reserveBook(book5);
            book5.setDamaged(true);
            regularUser3.reserveBook(book5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        regularUser3.cancelReservation(book5);
        try {
            regularUser3.cancelReservation(book4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        regularUser3.receiveNotification("message");
        regularUser3.accountStatus = AccountStatus.BLACKLISTED;
        regularUser3.receiveNotification("message");

        try {
            regularUser3.addScore(10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        regularUser3.deductScore(10);
        regularUser3.creditScore = 9;
        regularUser3.deductScore(10);
        regularUser3.creditScore = 49;
        regularUser3.accountStatus = AccountStatus.ACTIVE;
        regularUser3.deductScore(1);
        regularUser3.creditScore = 51;
        regularUser3.accountStatus = AccountStatus.ACTIVE;
        regularUser3.deductScore(1);

        regularUser3.setEmail("email");
        System.out.println(regularUser3.getEmail());
        regularUser3.setPhoneNumber("111");
        System.out.println(regularUser3.getPhoneNumber());

        BorrowRecord record1 = regularUser3.findBorrowRecord(book5);
    }

}
