package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class AbstractSelfBalancingBinarySearchTreeTest {

	AbstractSelfBalancingBinarySearchTree abt;
	AbstractBinarySearchTree.Node node;
	@Before
	public void setUp() {
		abt = new AbstractSelfBalancingBinarySearchTree() {
			@Override
			protected Node createNode(int value, Node parent, Node left, Node right) {
				return new Node(value,parent,left,right);
			}
		};
		node = null;
	}

	@Test
	public void test() {
		ScapegoatTree scapegoatTree0 = new ScapegoatTree();
		AbstractBinarySearchTree.Node abstractBinarySearchTree_Node0 = scapegoatTree0.createNode((-3252),
				(AbstractBinarySearchTree.Node) null, (AbstractBinarySearchTree.Node) null,
				(AbstractBinarySearchTree.Node) null);
		AbstractBinarySearchTree.Node abstractBinarySearchTree_Node1 = new AbstractBinarySearchTree.Node(
				abstractBinarySearchTree_Node0.value, abstractBinarySearchTree_Node0, abstractBinarySearchTree_Node0,
				abstractBinarySearchTree_Node0);
		AbstractBinarySearchTree.Node abstractBinarySearchTree_Node2 = scapegoatTree0
				.rotateRight(abstractBinarySearchTree_Node1);
		scapegoatTree0.rotateLeft(abstractBinarySearchTree_Node1);
		AbstractBinarySearchTree.Node abstractBinarySearchTree_Node3 = scapegoatTree0
				.rotateRight(abstractBinarySearchTree_Node2);
		assertTrue(abstractBinarySearchTree_Node1.isLeaf());
		assertSame(abstractBinarySearchTree_Node0, abstractBinarySearchTree_Node3);
	}

	@Test
	public void test0() {

	}

	@Test
	public void randomTest() {
		int n = 100;
		Random rnd = new Random(998244353);
		for(int i=1;i<=10;i++) {
			Random random = new Random(rnd.nextInt(100));
			for (int j=1;j<=1000;j++) {
				node = abt.insert(random.nextInt(n));
				try {
					abt.rotateLeft(abt.root);
				} catch (Exception e) {
				}
				try {
					abt.rotateLeft(abt.root.left);
				} catch (Exception e) {
				}
				try {
					abt.rotateLeft(abt.root.right);
				} catch (Exception e) {
				}
				try {
					abt.rotateRight(abt.root);
				} catch (Exception e) {
				}
			}
			abt.root.isLeaf();
			for (int j=1;j<=1000;j++) {
				abt.search(random.nextInt(n));
				abt.delete(random.nextInt(n));
				try {
					abt.rotateLeft(abt.root);
				} catch (Exception e) {
				}
				try {
					abt.rotateLeft(abt.root.left);
				} catch (Exception e) {
				}
				try {
					abt.rotateLeft(abt.root.right);
				} catch (Exception e) {
				}
				try {
					abt.rotateRight(abt.root);
				} catch (Exception e) {
				}
			}
		}
	}

}
