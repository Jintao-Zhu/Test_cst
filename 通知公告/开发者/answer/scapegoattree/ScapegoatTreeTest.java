package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
//author:TheOnlyman
public class ScapegoatTreeTest {

	ScapegoatTree abt;
	ScapegoatTree.Node node;
	@Before
	public void setUp() {
		abt = new ScapegoatTree(0.2);
		node = null;
	}

	@Test
	public void test0() {
		node = new AbstractBinarySearchTree.Node(null,null,null,null);
		node.hashCode();
		abt.insert(20);
		assertFalse(node.equals(abt.root));
		assertTrue(node.equals(node));
		assertTrue(node.equals(new AbstractBinarySearchTree.Node(null,abt.root,null,null)));

		abt.root = node;

		try {
			abt.insert(20);
		} catch (Exception e) {
		}

		abt.printTreeInOrder();
		abt.printTree();
		abt.printTreePostOrder();
		abt.printTreePreOrder();
	}

	@Test
	public void test1() {

		abt.insert(20);
		node = abt.search(20);
		assertEquals(new Integer(20),node.value);
		assertNull(node.parent);

		node = abt.insert(10);
		assertEquals(new Integer(20),node.parent.value);
		assertEquals(node, node.parent.left);

		node = abt.insert(30);
		assertEquals(new Integer(20),node.parent.value);
		assertEquals(node, node.parent.right);

		node = abt.search(20);
		assertNotNull(node);
		assertEquals(new Integer(20), node.value);

		node = abt.search(10);
		assertNotNull(node);
		assertEquals(new Integer(10), node.value);

		node = abt.search(11);
		assertNull(node);

	}

	@Test
	public void test2() {

		abt.insert(20);
		node = abt.search(20);
		node = abt.insert(10);
		node = abt.insert(30);
		abt.getSuccessor(20);
		assertTrue(abt.contains(20));
		assertFalse(abt.contains(11));
		assertEquals(3,abt.getSize());
		try {
			abt.getSuccessor(30);
		} catch (Exception e) {
		}
		abt.printTree();
		assertTrue(node.equals(new AbstractBinarySearchTree.Node(30,abt.root,null,null)));
		assertFalse(abt.root.isLeaf());
		node.hashCode();
		assertFalse(node.equals(null));
		assertFalse(node.equals(abt.root));
		assertTrue(node.equals(node));
		assertFalse(node.equals(new AbstractBinarySearchTree.Node(null,null,null,null)));
		assertFalse(node.equals(abt));

		abt.delete(null);
		abt.delete(11);
		abt.delete(20);

	}

	@Test
	public void test3() {

		abt.insert(20);
		node = abt.search(20);
		node = abt.insert(10);
		abt.printTree();
		assertFalse(node.equals(new AbstractBinarySearchTree.Node(30,abt.root,null,null)));
		assertFalse(abt.root.isLeaf());
		node.hashCode();
		assertFalse(node.equals(null));
		assertFalse(node.equals(abt.root));
		assertTrue(node.equals(node));
		assertFalse(node.equals(new AbstractBinarySearchTree.Node(null,null,null,null)));
		assertFalse(node.equals(abt));

		abt.delete(null);
		abt.delete(11);
		abt.delete(20);

	}

	@Test
	public void test4() {

		abt.insert(20);
		node = abt.search(20);
		node = abt.insert(30);
		abt.insert(25);
		abt.printTree();
		assertTrue(node.equals(new AbstractBinarySearchTree.Node(30,abt.root,null,null)));
		assertFalse(abt.root.isLeaf());
		node.hashCode();
		assertFalse(node.equals(null));
		assertTrue(node.equals(abt.root));
		assertTrue(node.equals(node));
		assertFalse(node.equals(new AbstractBinarySearchTree.Node(null,null,null,null)));
		assertFalse(node.equals(abt));

		abt.delete(null);
		abt.delete(11);
		abt.delete(20);

	}

	@Test
	public void test5() {
		abt = new ScapegoatTree();
		abt.insert(20);
		assertNull(abt.findScapegoatNode(abt.root));
		assertEquals(-1,abt.getNodeHeight(null));
		node = abt.search(20);
		assertEquals(new Integer(20),node.value);
		assertNull(node.parent);

		node = abt.insert(10);
		assertEquals(new Integer(20),node.parent.value);
		assertEquals(node, node.parent.left);

		node = abt.insert(30);
		assertEquals(new Integer(20),node.parent.value);
		assertEquals(node, node.parent.right);

		node = abt.search(20);
		assertNotNull(node);
		assertEquals(new Integer(20), node.value);

		node = abt.search(10);
		assertNotNull(node);
		assertEquals(new Integer(10), node.value);

		node = abt.search(11);
		assertNull(node);

	}

	@Test
	public void cornerTest() {
		abt = new ScapegoatTree(0.9);
		abt.insert(20);
		abt.delete(20);
	}

	@Test
	public void randomTest() {
		int n = 100;
		Random rnd = new Random(998244353);
		for(int i=1;i<=10;i++) {
			Random random = new Random(rnd.nextInt(100));
			for (int j=1;j<=100;j++) {
				node = abt.insert(random.nextInt(n));
				node.isLeaf();
			}
			abt.root.isLeaf();
			for (int j=1;j<=100;j++) {
				try {
					abt.search(random.nextInt(n));
				} catch (Exception e) {
				}
				try {
					abt.delete(random.nextInt(n));
				} catch (Exception e) {
				}
				abt.getMinimum();
				abt.getMaximum();
				try {
					abt.getSuccessor(abt.root);
				} catch (Exception e) {
				}
			}
		}
	}

}
