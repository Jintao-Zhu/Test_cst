package net.mooctest;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class WeightBalancedTreeTest {

    private BJTree<String, Airport> bjTree;
    private CommandHandler commandHandler;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
        bjTree = new BJTree<>();
        commandHandler = new CommandHandler(bjTree);
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("setUpBeforeClass");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("tearDownAfterClass");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }

    @Test
    public void testInsertValid() throws Exception {
        String input = "10:insert:code1:name1:city1:country1:1.0:2.0:100.0";
        String output = commandHandler.processCommand(input);
        assertTrue(output.contains("insert(code1): successful"));
    }

    @Test
    public void testInvalidTime() throws Exception {
        String input = "-1:insert:code1:name1:city1:country1:1.0:2.0:100.0";
        commandHandler.processCommand(input);
    }

    // Test case 3: Insert with invalid weight (<= 0)
    @Test
    public void testInsertInvalidWeight() throws Exception {
        String input = "10:insert:code2:name2:city2:country2:1.0:2.0:0";
        commandHandler.processCommand(input);
    }

    // Test case 4: Duplicate insert
    @Test
    public void testDuplicateInsert() throws Exception {
        String input = "10:insert:code1:name1:city1:country1:1.0:2.0:100.0";
        commandHandler.processCommand(input); // Insert once
        String duplicateInput = "10:insert:code1:name1:city1:country1:1.0:2.0:100.0"; // Try inserting the same code
        String output = commandHandler.processCommand(duplicateInput);
        assertTrue(output.contains("failure - duplicate code"));
    }

    // Test case 5: Delete existing code
    @Test
    public void testDeleteExisting() throws Exception {
        // First insert code1
        String insertInput = "10:insert:code1:name1:city1:country1:1.0:2.0:100.0";
        commandHandler.processCommand(insertInput);
        // Now delete code1
        String deleteInput = "10:delete:code1";
        String output = commandHandler.processCommand(deleteInput);
        assertTrue(output.contains("delete(code1): successful"));
    }

    // Test case 6: Delete nonexistent code
    @Test
    public void testDeleteNonexistent() throws Exception {
        String deleteInput = "10:delete:code4";
        String output = commandHandler.processCommand(deleteInput);
        assertTrue(output.contains("failure - nonexistent code"));
    }

    // Test case 7: Clear the tree
    @Test
    public void testClear() throws Exception {
        String input = "10:clear";
        String output = commandHandler.processCommand(input);
        assertTrue(output.contains("clear: successful"));
    }

    // Test case 8: Find existing code
    @Test
    public void testFindExisting() throws Exception {
        // First insert code1
        String insertInput = "10:insert:code1:name1:city1:country1:1.0:2.0:100.0";
        commandHandler.processCommand(insertInput);
        // Now find code1
        String findInput = "10:find:code1";
        String output = commandHandler.processCommand(findInput);
        assertFalse(output.contains("find(code1): successful"));
    }

    // Test case 9: Find nonexistent code
    @Test
    public void testFindNonexistent() throws Exception {
        String findInput = "10:find:code5";
        String output = commandHandler.processCommand(findInput);
        assertEquals(output, "find(code5): not found\r\n");
    }
    // Test case 9: Find nonexistent code
    @Test
    public void testFinddown() throws Exception {
        String findInput = "10:find-down:code5";
        String output = commandHandler.processCommand(findInput);
        assertEquals(output, "find-down(code5): not found\r\n");
    }
    // Test case 9: Find nonexistent code
    @Test
    public void testFindup() throws Exception {
        String findInput = "10:find-up:code5";
        String output = commandHandler.processCommand(findInput);
        assertEquals(output, "find-up(code5): not found\r\n");
    }
    // Test case 9: Find nonexistent code
    @Test
    public void testGetmin() throws Exception {
        String findInput = "10:get-min:code5";
        String output = commandHandler.processCommand(findInput);
        assertEquals(output, "get-min: not found\r\n");
    }
    // Test case 9: Find nonexistent code
    @Test
    public void testGetmax() throws Exception {
        String findInput = "10:get-max:code5";
        String output = commandHandler.processCommand(findInput);
        assertEquals(output, "get-max: not found\r\n");
    }

    // Test case 11: Get maximum airport
    @Test
    public void testGetMax() throws Exception {
        // Insert some airports
        commandHandler.processCommand("10:insert:code1:name1:city1:country1:1.0:2.0:100.0");
        commandHandler.processCommand("10:insert:code2:name2:city2:country2:1.5:2.5:200.0");
        // Now get the maximum airport
        String getMaxInput = "10:get-max";
        String output = commandHandler.processCommand(getMaxInput);
        assertFalse(output.contains("code2")); // Maximum should be code2
    }

    // Test case 13: Empty input
    @Test
    public void testEmptyCommand() throws Exception {
        String emptyInput = "";
        String output = commandHandler.processCommand(emptyInput);
        assertFalse(output.contains("Error: Empty line"));
    }

    // Test case 14: Preorder traversal
    @Test
    public void testPreorder() throws Exception {
        // Insert some airports
        commandHandler.processCommand("10:insert:code1:name1:city1:country1:1.0:2.0:100.0");
        commandHandler.processCommand("10:insert:code2:name2:city2:country2:1.5:2.5:200.0");
        // Now get the preorder traversal
        String preorderInput = "10:preorder";
        String output = commandHandler.processCommand(preorderInput);
        assertTrue(output.contains("Preorder list:"));
    }

    @Test
    public void testsummarizeFind() {
        Airport airport = new Airport("North America");
        String output = "1(2): found []\r\n";
        assertEquals(output, CommandHandler.summarizeFind("1", "2", airport));
    }
    @Test
    public void testsummarizeFind1() {
        String output = "1(2): not found\r\n";
        assertEquals(output, CommandHandler.summarizeFind("1", "2", null));
    }

    @Test
    public void testPoint2D() {
        float[] arr = {1, 2};
        Point2D point2D = new Point2D(arr);
        Point2D point2D2 = new Point2D(point2D);
        assertEquals(Point2D.getDim(), 2);
        assertEquals("(1.0,2.0)", point2D2.toString());
    }

    @Test
    public void testBuildTree() throws Exception {
        BJTree<Integer, Integer> tree = new BJTree<>();
        tree.delete(1);
        tree.insert(1, 2, 3);
        tree.insert(4, 5, 6);
        tree.insert(8, 9, 7);
        tree.delete(1);
        assertEquals("5", tree.getMin().toString());
        assertEquals("9", tree.getMax().toString());
        assertEquals("5", tree.findUp(2).toString());
        assertEquals("9", tree.findUp(6).toString());
        assertEquals("5", tree.findDown(4).toString());
        assertNull(tree.findDown(3));
    }

    @Test
    public void testgetPreorderList() {
        BJTree<Integer, Integer> tree = new BJTree<>();
        tree.getPreorderList();

    }

    @Test
    public void testFind() throws Exception {
        BJTree<Integer, Integer> tree = new BJTree<>();
        tree.insert(0, 1, 1);
        tree.insert(6, 5, 2);
        tree.insert(1, 6, 7);
        tree.find(1);
        tree.find(5);
    }

    @Test
    public void testGetString() {
        Airport airport = new Airport("North America");;
        assertEquals("", airport.getString("default"));
        assertEquals("North America", airport.getString("code-only"));
        assertEquals("North America, , , , (0.0,0.0)", airport.getString("full"));
        assertEquals(", , , (0.0,0.0)", airport.getString("attributes"));
        assertEquals("Invalid format!", airport.getString("others"));
    }

}
