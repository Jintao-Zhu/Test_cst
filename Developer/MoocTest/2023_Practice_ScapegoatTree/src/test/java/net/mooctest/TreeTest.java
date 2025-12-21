package net.mooctest;

import org.junit.*;

import static org.junit.Assert.*;

public class TreeTest {
    @BeforeClass
    public static void test001() {
        System.out.println("test before class");
    }
    @Before
    public void test002() {
        System.out.println("test before");
    }
    @AfterClass
    public static void test003() {
        System.out.println("test after class");
    }
    @After
    public void test004() {
        System.out.println("test after");
    }

    @Test
    public void testInsert() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        int actual = scapegoatTree.insert(1).value;
        assertEquals(1, actual);
    }

    @Test
    public void testInsert1() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(10);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(1);
        int output = node1.value;
        assertEquals(1, output);
        scapegoatTree.insert(2);
    }

    @Test
    public void testDelete() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(10);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.delete(1);
        assertNull(node1);
    }

    @Test
    public void testFindScapegoatNode() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(10);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(1);
        AbstractBinarySearchTree.Node output = scapegoatTree.findScapegoatNode(node1);
        assertNull(output);
    }

    @Test
    public void testFindScapegoatNode1() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(10);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(1);
        AbstractBinarySearchTree.Node node2 = scapegoatTree.createNode(5, node1, null, null);
        AbstractBinarySearchTree.Node output = scapegoatTree.findScapegoatNode(node2);
        int value = output.value;
        assertEquals(1, value);
    }

    @Test
    public void testFindScapegoatNode2() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(1);
        AbstractBinarySearchTree.Node node2 = scapegoatTree.createNode(5, node1, null, null);
        assertNull(scapegoatTree.findScapegoatNode(node2));
    }

    @Test
    public void testGetSubtreeSize() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        AbstractBinarySearchTree.Node node1 = scapegoatTree.createNode(5, null, null, null);
        assertEquals(0, scapegoatTree.getSubtreeSize(null));
        assertEquals(1, scapegoatTree.getSubtreeSize(node1));
        AbstractBinarySearchTree.Node node2 = scapegoatTree.createNode(5, null, node1, null);
        assertEquals(2, scapegoatTree.getSubtreeSize(node2));
    }

    @Test
    public void testGetNodeHeight() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        assertEquals(-1, scapegoatTree.getNodeHeight(null));
    }

    @Test
    public void testGetDigitFromNumber() {
        assertEquals(3, MathUtils.getDigitFromNumber(357, 2));
    }

    @Test
    public void testGetMax() {
        assertEquals(2, MathUtils.getMax(0, 2));
        assertEquals(2, MathUtils.getMax(2, 1));
    }

    @Test
    public void testRotateLeft() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        AbstractBinarySearchTree.Node node3 = new AbstractBinarySearchTree.Node(3, null, null, null);
        AbstractBinarySearchTree.Node node7 = scapegoatTree.createNode(7, null, node3, null);
        AbstractBinarySearchTree.Node node5 = scapegoatTree.createNode(5, null, null, null);
        AbstractBinarySearchTree.Node node6 = scapegoatTree.createNode(6, null, null, null);
        AbstractBinarySearchTree.Node node4 = scapegoatTree.createNode(4, node5, node6, null);
        AbstractBinarySearchTree.Node node2 = scapegoatTree.createNode(2, null, node3, node4);
        scapegoatTree.createNode(1, node7, node2, null);
        scapegoatTree.rotateLeft(node2);
    }

    @Test
    public void testRotateLeft2() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        AbstractBinarySearchTree.Node node3 = new AbstractBinarySearchTree.Node(3, null, null, null);
        AbstractBinarySearchTree.Node node7 = scapegoatTree.createNode(7, null, node3, null);
        AbstractBinarySearchTree.Node node5 = scapegoatTree.createNode(5, null, null, null);
        AbstractBinarySearchTree.Node node6 = scapegoatTree.createNode(6, null, null, null);
        scapegoatTree.createNode(4, node5, node6, null);
        AbstractBinarySearchTree.Node node2 = scapegoatTree.createNode(2, node7, node3, node3);
        scapegoatTree.createNode(1, node7, node2, null);
        scapegoatTree.rotateLeft(node2);
    }

    @Test
    public void testRotateRight() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        AbstractBinarySearchTree.Node node3 = scapegoatTree.createNode(3, null, null, null);
        AbstractBinarySearchTree.Node node5 = scapegoatTree.createNode(5, null, null, null);
        AbstractBinarySearchTree.Node node6 = scapegoatTree.createNode(6, null, null, null);
        AbstractBinarySearchTree.Node node4 = scapegoatTree.createNode(4, node5, node6, null);
        AbstractBinarySearchTree.Node node2 = scapegoatTree.createNode(2, null, node3, node4);

        scapegoatTree.rotateRight(node2);
    }

    @Test
    public void testRotateRight1() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        AbstractBinarySearchTree.Node node7 = scapegoatTree.createNode(7, null, null, null);
        AbstractBinarySearchTree.Node node3 = scapegoatTree.createNode(3, node7, node7, node7);
        AbstractBinarySearchTree.Node node5 = scapegoatTree.createNode(5, null, null, null);
        AbstractBinarySearchTree.Node node6 = scapegoatTree.createNode(6, null, null, null);
        AbstractBinarySearchTree.Node node4 = scapegoatTree.createNode(4, node5, node6, null);
        AbstractBinarySearchTree.Node node2 = scapegoatTree.createNode(2, null, node3, node4);
        node2.parent = scapegoatTree.createNode(1, null, node2, null);
        scapegoatTree.rotateRight(node2);
    }

    @Test
    public void testRotateRight2() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        AbstractBinarySearchTree.Node node7 = scapegoatTree.createNode(7, null, null, null);
        AbstractBinarySearchTree.Node node5 = scapegoatTree.createNode(5, null, null, null);
        AbstractBinarySearchTree.Node node6 = scapegoatTree.createNode(6, null, null, null);
        AbstractBinarySearchTree.Node node4 = scapegoatTree.createNode(4, node5, node6, null);
        AbstractBinarySearchTree.Node node2 = scapegoatTree.createNode(2, null, node7, node4);
        scapegoatTree.rotateRight(node2);
    }

    @Test
    public void testPrintTree() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(1);
        AbstractBinarySearchTree.Node node2 = scapegoatTree.insert(1);
        scapegoatTree.printTree();
        assertEquals(32, node1.hashCode());

        assertEquals(node1, node1);

        assertNotEquals(null, node1);
        assertEquals(node1, node2);
        assertNotEquals(1, node1);
        scapegoatTree.delete(1);
        assertNotEquals(0, node1);
    }

    @Test
    public void testSearch() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(1);
        assertEquals(node1, scapegoatTree.search(1));
        scapegoatTree.root = node1;
        scapegoatTree.search(2);
    }

    @Test
    public void testSearch1() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(3);
        assertNull(scapegoatTree.search(1));
        scapegoatTree.root = node1;
        scapegoatTree.search(2);
    }

    @Test
    public void testInsert2() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(3);
        assertNull(scapegoatTree.search(1));
        scapegoatTree.root = node1;
        scapegoatTree.search(2);
        scapegoatTree.insert(1);
    }

    @Test
    public void testDelete1() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(3);
        assertNull(scapegoatTree.search(1));
        scapegoatTree.root = node1;
        scapegoatTree.insert(1);
        scapegoatTree.delete(node1);
        AbstractBinarySearchTree.Node node2 = new AbstractBinarySearchTree.Node(2, node1, null, null);
        AbstractBinarySearchTree.Node node3 = new AbstractBinarySearchTree.Node(3, null, null, null);
        node1.left = node2;
        node1.right = node3;
        node1.value = 6;
        assertEquals(node3, scapegoatTree.delete(node1));
        assertNull(scapegoatTree.delete(null));
    }

        @Test
    public void testGetMinimum() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(3);
        scapegoatTree.root = node1;
        AbstractBinarySearchTree.Node node2 = new AbstractBinarySearchTree.Node(2, node1, null, null);
        AbstractBinarySearchTree.Node node3 = new AbstractBinarySearchTree.Node(3, null, null, null);
        node1.left = node2;
        node1.right = node3;
        assertEquals(node2, scapegoatTree.getMinimum(node1));
    }

        @Test
    public void testGetMaximum() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(3);
        scapegoatTree.root = node1;
        AbstractBinarySearchTree.Node node2 = new AbstractBinarySearchTree.Node(2, node1, null, null);
        AbstractBinarySearchTree.Node node3 = new AbstractBinarySearchTree.Node(3, null, null, null);
        node1.left = node2;
        node1.right = node3;
        assertEquals(node3, scapegoatTree.getMaximum(node1));
    }

        @Test
    public void testGetSuccessor() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(3);
        scapegoatTree.root = node1;
        AbstractBinarySearchTree.Node node2 = new AbstractBinarySearchTree.Node(2, node1, null, null);
        AbstractBinarySearchTree.Node node3 = new AbstractBinarySearchTree.Node(3, null, null, null);
        node1.left = node2;
        node1.right = node3;
        assertEquals(node3, scapegoatTree.getSuccessor(node1));
    }

        @Test
    public void testGetSuccessor1() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(3);
        scapegoatTree.root = node1;
        AbstractBinarySearchTree.Node node2 = new AbstractBinarySearchTree.Node(2, node1, null, null);
        AbstractBinarySearchTree.Node node3 = new AbstractBinarySearchTree.Node(3, null, null, null);
        AbstractBinarySearchTree.Node node4 = new AbstractBinarySearchTree.Node(3, null, null, node1);
        node1.left = node2;
        node1.parent = node4;
        assertEquals(null, scapegoatTree.getSuccessor(node1));
    }

        @Test
    public void testPrintSubtree() {
        ScapegoatTree scapegoatTree = new ScapegoatTree(1);
        AbstractBinarySearchTree.Node node1 = scapegoatTree.insert(3);
        scapegoatTree.root = node1;
        AbstractBinarySearchTree.Node node2 = new AbstractBinarySearchTree.Node(2, node1, null, null);
        AbstractBinarySearchTree.Node node3 = new AbstractBinarySearchTree.Node(3, null, null, null);
        AbstractBinarySearchTree.Node node4 = new AbstractBinarySearchTree.Node(3, null, null, node1);
        node1.left = node2;
        node1.parent = node4;
        scapegoatTree.printSubtree(node1);
    }

        @Test
    public void testEquals() {
        ScapegoatTree scapegoatTree = new ScapegoatTree();
        assertFalse(scapegoatTree.equals(null));
        assertFalse(scapegoatTree.equals(1));
    }




}
