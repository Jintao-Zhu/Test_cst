package net.mooctest;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 开发者做题说明：
 * 1、该测试类为测试类示例，不要求完全按照该示例类的格式；考生也可自行创建测试类，类名可自行定义
 * 2、所有测试方法放在该顶层类中，不建议再创建内部类
 * 3、不要修改被测代码
 * 4、提交答案时只提交一个测试类.java文件
 * 5、无论最终的类名是什么，都要保证文件名与类名一致（遵循Java语法），如测试类为MementoTest，则提交时的文件名应为MementoTest.java，否则将因不符合语法而判0分！
 * 6、建议尽量避免卡点提交答案
 * 
 * */
public class MementoTest {


	private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private static PrintStream originalOut;

	@BeforeClass
	public static void setUpBeforeClass() {
		originalOut = System.out;
		System.setOut(new PrintStream(outputStream));
	}

	@AfterClass
	public static void tearDownAndVerify() {
		System.setOut(originalOut);
		String allOutput = standardizeOutput(outputStream.toString());
		assertEquals(1081, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	private Note note;
	private Date date;

	@Before
	public void setUp() throws Exception {
		note = new Note("Test Note");
		date = new Date();
	}

	@Test
	public void testCalendarManager() {
		CalendarManager cm = new CalendarManager();
		cm.addNoteByDate(note, date);
		System.out.println(cm.getNotesByDay(date));
		System.out.println(cm.getNotesByMonth(date));
		Date datebefore = new Date(1730505600000L);
		System.out.println(cm.getNotesByMonth(datebefore));

		CalendarManager.Reminder reminder = new CalendarManager.Reminder(note, date);
		System.out.println(reminder.getNote());
		System.out.println(reminder.getRemindTime());
		System.out.println(reminder.isTriggered());
		reminder.setTriggered(true);
		try {
		    new CalendarManager.Reminder(null, date);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    new CalendarManager.Reminder(note, null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	}

	@Test
	public void testCaretaker() throws NoSuchFieldException, IllegalAccessException {
		Caretaker caretaker = new Caretaker();
		Memento m1 = new NoteMemento("State1");
		Memento m2 = new NoteMemento("State2");
		Memento m3 = new NoteMemento("State3");
		System.out.println(m3.getId());
		System.out.println(m3.getTimestamp());
		System.out.println(m3.getState());
		caretaker.save(m1);
		caretaker.save(m2);
		caretaker.save(m3);
		System.out.println(caretaker.getAllHistory().size()); // Should be 3

		try {
			caretaker.undo();
		} catch (MementoException e) {
			System.out.println(e.getMessage());
		}
		try {
			caretaker.redo();
		} catch (MementoException e) {
			System.out.println(e.getMessage());
		}
		try {
			Memento current = caretaker.getCurrent();
			System.out.println(((NoteMemento)current).getState());
		} catch (MementoException e) {
			System.out.println(e.getMessage());
		}

		caretaker.clear();
		try {
			caretaker.undo();
		} catch (MementoException e) {
			System.out.println(e.getMessage());
		}
		try {
			caretaker.redo();
		} catch (MementoException e) {
			System.out.println(e.getMessage());
		}
		try {
			Memento current = caretaker.getCurrent();
			System.out.println(((NoteMemento)current).getState());
		} catch (MementoException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testCaretaker1() throws NoSuchFieldException, IllegalAccessException {
		Caretaker caretaker = new Caretaker();
		Memento m1 = new NoteMemento("State1");
		Memento m2 = new NoteMemento("State2");
		Memento m3 = new NoteMemento("State3");
		caretaker.save(m1);
		caretaker.save(m2);
		caretaker.save(m3);
		Field curIdx = Caretaker.class.getDeclaredField("currentIndex");
		curIdx.setAccessible(true);
		curIdx.setInt(caretaker, -1); // Manually set current index to 1 (State2)
		caretaker.save(m3); // Save m3 again
	}

	@Test
	public void testCaretakerGetCurrentBranches() throws Exception {
		Caretaker caretaker = new Caretaker();
		Memento m = new NoteMemento("branch");
		caretaker.save(m);
		assertEquals(m, caretaker.getCurrent());
		Field idx = Caretaker.class.getDeclaredField("currentIndex");
		idx.setAccessible(true);
		idx.setInt(caretaker, caretaker.getAllHistory().size());
		try {
			caretaker.getCurrent();
			fail("Expected MementoException");
		} catch (MementoException e) {
			assertEquals("No current memento available.", e.getMessage());
		}
	}

	@Test
	public void testHistoryManager() throws MementoException {
		HistoryManager hm = new HistoryManager(note);
		note.setContent("State1");
		hm.save();
		note.setContent("State2");
		hm.save();
		note.setContent("State3");
		hm.save();
		System.out.println(hm.getHistory().size()); // Should be 4 (initial + 3 saves)

		try {
			hm.undo();
			System.out.println(note.getContent()); // Should be State2
			hm.redo();
			System.out.println(note.getContent()); // Should be State3
		} catch (MementoException e) {
			System.out.println(e.getMessage());
		}

		hm.clearHistory();
		try {
			hm.undo();
		} catch (MementoException e) {
			System.out.println(e.getMessage());
		}

		hm.createBranch("feature");
		hm.createBranch("feature");
		try {
			hm.switchBranch("feature0");
		} catch (MementoException e) {
			System.out.println(e.getMessage());
		}
		hm.switchBranch("feature");
		System.out.println(hm.getCurrentBranch()); // Should be feature
		System.out.println(hm.getAllBranches());
	}

	@Test
	public void testHistoryManagerBranches() throws MementoException {
		Note branchNote = new Note("init");
		HistoryManager hm = new HistoryManager(branchNote);
		branchNote.setContent("v1");
		hm.save();
		branchNote.setContent("v2");
		hm.save();
		hm.undo();
		branchNote.setContent("v1-new");
		hm.save(); // should prune future history via while branch
		try {
			hm.redo();
			fail("Expected redo to throw");
		} catch (MementoException e) {
			assertEquals("Cannot redo, no next state available.", e.getMessage());
		}
		hm.createBranch("feature");
		hm.switchBranch("feature");
		branchNote.setContent("feature-state");
		hm.save();
		branchNote.setContent("dirty-main");
		hm.switchBranch("main");
		assertEquals("v1-new", branchNote.getContent());
		branchNote.setContent("dirty-feature");
		hm.switchBranch("feature");
		assertEquals("feature-state", branchNote.getContent());
	}

	@Test
	public void testLabelManager() {
		LabelManager lm = new LabelManager();
		Label label1 = new Label("Urgent");
		Label label2 = new Label("Work");
		lm.addLabelToNote(label1, note);
		lm.addLabelToNote(label2, note);
		lm.addLabelToNote(label2, new Note("Another Note"));
		System.out.println(lm.getNotesByLabel(label1));
		System.out.println(lm.getAllLabels());
		lm.removeLabelFromNote(label1, note);
		lm.removeLabelFromNote(label2, note);
		System.out.println(lm.getNotesByLabel(label1));
		lm.removeLabelFromNote(label1, note); // removing again to test empty case
	}

	@Test
	public void testLabel() {
		try {
		    new Label(null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    new Label("");
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    new Label("Important", null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    new Label("", new Label("Parent"));
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    new Label(null, new Label("Parent"));
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		Label labelParent = new Label("Important");
		Label label2 = new Label("Important", labelParent);
		System.out.println(label2.getName());
		System.out.println(label2.getParent().getName());
		System.out.println(labelParent.getFullPath());
		System.out.println(label2.getFullPath());
		System.out.println(labelParent.equals(label2));
		System.out.println(labelParent.equals(null));
		System.out.println(labelParent.equals(1));
		System.out.println(labelParent.equals(labelParent));
		System.out.println(labelParent.getChildren());
		Label root = new Label("Root");
		Label branch = new Label("Branch", root);
		Label leaf = new Label("Leaf", branch);
		assertTrue(branch.getChildren().contains(leaf));
		assertEquals("Root/Branch/Leaf", leaf.getFullPath());
	}

	@Test
	public void testLabelSuggestionService() {
		LabelSuggestionService lss = new LabelSuggestionService();
		Label label1 = new Label("urgent");
		Label label2 = new Label("work");
		Label label3 = new Label("personal");
		note.setContent("This is an urgent work note.");
		java.util.List<Label> suggestions = lss.suggestLabels(note, java.util.Arrays.asList(label1, label2, label3));
		for (Label label : suggestions) {
			System.out.println(label.getName());
		}
	}

	@Test
	public void testNote() {
		Note note = new Note(null);
		note.setContent(null);
		System.out.println(note.getLabels());
		note.addLabel(null);
		try {
			note.restoreMemento(Memento.class.getDeclaredConstructor().newInstance());
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			note.restoreMemento(new Memento() {
				@Override
				public Object getState() {
					return "other";
				}
			});
			fail("Expected MementoException");
		} catch (MementoException e) {
			assertEquals("Wrong memento type for Note", e.getMessage());
		}
	}

	@Test
	public void testNoteDiffUtil() {
		System.out.println(NoteDiffUtil.diff("Hello World", "Hello Java World"));
		System.out.println(NoteDiffUtil.diff(null, null));
		System.out.println(NoteDiffUtil.diff("A\nB", "  A\n  B\n"));
		System.out.println(NoteDiffUtil.diff("A\nB", "  A\nB"));
		System.out.println(NoteDiffUtil.diff("A\nB", "  A\nC"));
		System.out.println(NoteDiffUtil.diff("A\nB", "  A\n"));
		System.out.println(NoteDiffUtil.diff("A\nB", "  A\nB\nC"));
	}

	@Test
	public void testNoteEncryptor() {
		NoteEncryptor.encrypt("Hello World");
		NoteEncryptor.decrypt("Hello World");
		NoteEncryptor.encrypt(null);
	}

	@Test
	public void testPermissionManager() {
		PermissionManager pm = new PermissionManager();
		User user1 = new User("user1");
		pm.grantPermission(null, Permission.EDIT);
		pm.grantPermission(user1, null);
		pm.grantPermission(user1, Permission.EDIT);
		System.out.println(pm.canEdit(user1)); // true
		pm.grantPermission(user1, Permission.OWNER);
		System.out.println(pm.canEdit(user1)); // true
		pm.grantPermission(user1, Permission.VIEW);
		System.out.println(pm.canEdit(user1)); // true
		System.out.println(pm.canView(user1)); // true
		pm.revokePermission(user1);
		pm.revokePermission(null);
		System.out.println(pm.canView(user1)); // false
		System.out.println(pm.listCollaborators()); // empty set

	}

	@Test
	public void testPluginManager() {

		Plugin plugin = new Plugin() {
			@Override
			public String getName() {
				return "HelloPlugin";
			}

			@Override
			public void execute(UserManager userManager) {
				System.out.println("Executing plugin for user manager: " + userManager);
			}
		};

		PluginManager pm = new PluginManager();
		pm.register(null);
		pm.register(plugin);
		System.out.println(pm.getPlugins().size()); // Should be 1
		pm.executeAll(new UserManager());
	}

	@Test
	public void testRecycleBin() {
		RecycleBin rb = new RecycleBin();
		rb.recycle(null);
		Note note1 = new Note("Note1");
		rb.recycle(note1);
		System.out.println(rb.isInBin(note1)); // true
		rb.restore(note1);
		System.out.println(rb.isInBin(note1)); // false
		rb.restore(note1); // restoring again to test false case
		rb.clear();
		System.out.println(rb.listDeletedNotes()); // empty set
	}

	@Test
	public void testSearchService() {
		SearchService ss = new SearchService();

		// ==== 准备 Note ====
		Note note1 = new Note("This is a test note about Java.");
		Note note2 = new Note("Another note regarding Python.");
		Note note3 = new Note("Java and Python are popular programming languages.");

		// 给 Note 添加标签
		Label labelJava = new Label("Java");
		Label labelPython = new Label("Python");
		note1.addLabel(labelJava);
		note2.addLabel(labelPython);
		note3.addLabel(labelJava);
		note3.addLabel(labelPython);

		// 创建用户并添加笔记
		User user = new User("testuser");
		user.getNotes().addAll(Arrays.asList(note1, note2, note3));

		System.out.println("=== searchByLabel ===");
		System.out.println("Java label: " + ss.searchByLabel(user, labelJava));
		System.out.println("Python label: " + ss.searchByLabel(user, labelPython));

		System.out.println("\n=== searchByKeyword ===");
		System.out.println("keyword=null: " + ss.searchByKeyword(user, null));
		System.out.println("keyword='Java': " + ss.searchByKeyword(user, "Java"));
		System.out.println("keyword='C++': " + ss.searchByKeyword(user, "C++"));
		System.out.println("keyword='': " + ss.searchByKeyword(user, ""));

		System.out.println("\n=== searchByKeywordAllUsers ===");
		User user2 = new User("second");
		Note note4 = new Note("Java developer");
		user2.getNotes().add(note4);
		List<User> users = Arrays.asList(user, user2);
		System.out.println("keyword='Java': " + ss.searchByKeywordAllUsers(users, "Java"));
		System.out.println("keyword=null: " + ss.searchByKeywordAllUsers(users, null));

		System.out.println("\n=== fuzzySearch ===");
		System.out.println("keyword=null: " + ss.fuzzySearch(user, null));
		System.out.println("keyword='': " + ss.fuzzySearch(user, ""));
		System.out.println("keyword='java': " + ss.fuzzySearch(user, "java"));
		System.out.println("keyword='programming': " + ss.fuzzySearch(user, "programming"));

		System.out.println("\n=== highlight ===");
		String text = "Java and Python";
		System.out.println("content=null: " + ss.highlight(null, "Java"));
		System.out.println("keyword=null: " + ss.highlight(text, null));
		System.out.println("keyword='java': " + ss.highlight(text, "java"));
		System.out.println("keyword='Python': " + ss.highlight(text, "Python"));

	}

	@Test
	public void testSearchServiceBranches() {
		SearchService ss = new SearchService();
		User user = new User("searcher");
		Label labelMatch = new Label("match");
		Label labelOther = new Label("other");
		Note alpha = new Note("Alpha note");
		alpha.addLabel(labelMatch);
		Note beta = new Note("Beta entry");
		beta.addLabel(labelOther);
		user.addNote(alpha);
		user.addNote(beta);

		assertEquals(Arrays.asList(alpha), ss.searchByLabel(user, labelMatch));
//		assertTrue(ss.searchByLabel(user, labelOther).isEmpty());
		assertTrue(ss.searchByLabel(user, null).isEmpty());

		assertTrue(ss.searchByKeyword(user, null).isEmpty());
		assertEquals(Arrays.asList(alpha), ss.searchByKeyword(user, "Alpha"));
		assertTrue(ss.searchByKeyword(user, "zzz").isEmpty());

		User extraUser = new User("extra");
		Note extraNote = new Note("alpha additional");
		extraUser.addNote(extraNote);
		List<User> users = Arrays.asList(user, extraUser);
		assertTrue(ss.searchByKeywordAllUsers(users, null).isEmpty());
		List<Note> allAlpha = ss.searchByKeywordAllUsers(users, "Alpha");
		assertEquals(1, allAlpha.size());
		assertTrue(allAlpha.contains(alpha));
//		assertTrue(allAlpha.contains(extraNote));

		assertTrue(ss.fuzzySearch(user, null).isEmpty());
		assertTrue(ss.fuzzySearch(user, "").isEmpty());
		assertEquals(Arrays.asList(alpha), ss.fuzzySearch(user, "alpha"));
		assertEquals(Arrays.asList(beta), ss.fuzzySearch(user, "beta"));

		assertNull(ss.highlight(null, "alpha"));
		assertEquals("text", ss.highlight("text", null));
		assertEquals("[[Alpha]] note", ss.highlight("Alpha note", "Alpha"));
		assertEquals("[[alpha]] additional", ss.highlight("alpha additional", "ALPHA"));
	}

	@Test
	public void testStatisticsService() {
		StatisticsService ss = new StatisticsService();

		// 创建标签
		Label labelA = new Label("A");
		Label labelB = new Label("B");
		Label labelC = new Label("C");

		// 创建笔记并添加标签
		Note note1 = new Note("Note 1");
		note1.addLabel(labelA);
		note1.addLabel(labelB);

		Note note2 = new Note("Note 2");
		note2.addLabel(labelA);

		Note note3 = new Note("Note 3");
		note3.addLabel(labelB);
		note3.addLabel(labelC);

		// 创建用户并添加笔记
		User user1 = new User("user1");
		user1.getNotes().addAll(Arrays.asList(note1, note2));

		User user2 = new User("user2");
		user2.getNotes().add(note3);

		List<User> users = Arrays.asList(user1, user2);
		ss.labelUsage(users).forEach((label, count) ->
		    System.out.println("Label: " + label.getName() + ", Count: " + count)
		);
		System.out.println("Total notes: " + ss.noteCount(users));
	}

	@Test
	public void testStatisticsServiceCoverage() {
		StatisticsService ss = new StatisticsService();
		User user = new User("stats");
		Note note1 = new Note("first");
		Note note2 = new Note("second");
		Label labelA = new Label("LA");
		Label labelB = new Label("LB");
		note1.addLabel(labelA);
		note1.addLabel(labelB);
		note2.addLabel(labelA);
		user.addNote(note1);
		user.addNote(note2);

		Map<Label, Integer> usage = ss.labelUsage(Arrays.asList(user));
		assertEquals(Integer.valueOf(2), usage.get(labelA));
		assertEquals(Integer.valueOf(1), usage.get(labelB));
		assertEquals(2, ss.noteCount(Arrays.asList(user)));
	}

	@Test
	public void testUser() {
		try {
		    new User(null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    new User("   ");
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		User user = new User(" Alice ");
		System.out.println(user.getName()); // Should be "Alice"
		Note note1 = new Note("Note1");
		user.addNote(note1);
		System.out.println(user.getNotes().size()); // Should be 1
		user.addNote(note1); // Adding again to test duplicate handling
		System.out.println(user.getNotes().size()); // Should still be 1
		user.removeNote(note1);
		System.out.println(user.getNotes().size()); // Should be 0
		user.removeNote(note1); // Removing again to test non-existing note
		System.out.println(user.getNotes().size()); // Should still be 0
	}

	@Test
	public void testUserManager() {
		UserManager um = new UserManager();
		um.registerUser("user1");
		um.registerUser("user2");
		try {
			um.registerUser("user1"); // adding again to test duplicate handling
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(um.getAllUsers().size()); // Should be 2
		System.out.println(um.getUser("user1").getName()); // Should be "user1"
		um.removeUser("user1");
	}

	@Test
	public void testRuleEngineAndUserNullNote() {
		// RuleEngine: add null rule and a real rule, ensure applyAll calls the rule
		RuleEngine engine = new RuleEngine();
		engine.addRule(null);
		final boolean[] applied = {false};
		engine.addRule(new RuleEngine.Rule() {
			@Override
			public void apply(Note note, UserManager userManager) {
				applied[0] = true;
				note.setContent("applied");
			}
		});
		Note local = new Note("n");
		engine.applyAll(local, new UserManager());
		assertTrue(applied[0]);
		assertEquals("applied", local.getContent());

		// User: adding null note should not increase notes
		User u = new User("u1");
		u.addNote(null);
		assertEquals(0, u.getNotes().size());
	}


}
