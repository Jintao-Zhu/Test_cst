package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.Field;

public class QuadTreeTest {

	@Test
	public void test() throws NoSuchFieldException, IllegalAccessException {
		QuadTree tree = new QuadTree(0, 0, 2, 2);
		tree.set(0, 0, 0);
		tree.set(0, 1, 1);
		tree.set(0, 2, 2);
		tree.set(1, 0, 10);
		tree.set(1, 1, 11);
		tree.set(1, 2, 12);
		tree.set(0, 0, 33);
		tree.set(2, 0, 20);
		tree.set(2, 1, 21);
		tree.set(2, 2, 22);
		try {
			tree.set(-1, 1, 1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			tree.set(0, -1, 1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			tree.set(-2, 2, 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			tree.set(0, -2, 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tree.remove(0, 0);
		tree.remove(-1, -1);
		System.out.println(tree.contains(0,0));
		System.out.println(tree.contains(1,1));
		System.out.println(tree.isEmpty());
		System.out.println(tree.getCount());
		tree.clear();

		Point[] keys = tree.getKeys();
		Object[] values = tree.getValues();


		Point[] points1 = tree.searchIntersect(0, 0, 0, 0);
		Point[] points2 = tree.searchWithin(0, 0, 0, 0);
		tree.clone();

		Node root = tree.getRootNode();
		Node node = new Node(0, 0, 2, 2, null);
		Node nw = new Node(0, 1, 2, 2, null);
		Node ne = new Node(1, 0, 2, 2, null);
		Node sw = new Node(0, 2, 2, 2, null);
		Node se = new Node(2, 0, 2, 2, null);
		nw.setNodeType(NodeType.LEAF);
		ne.setNodeType(NodeType.LEAF);
		sw.setNodeType(NodeType.LEAF);
		se.setNodeType(NodeType.LEAF);
		node.setNw(nw);
		node.setNe(ne);
		node.setSw(sw);
		node.setSe(se);

		tree.balance(node);

		root.setNodeType(NodeType.POINTER);
		tree.balance(node);

//		node.setNodeType(NodeType.LEAF);
//		tree.balance(node);

		node.setParent(root);

		root.setNw(nw);
		tree.balance(node);

//		root.getNw().setNodeType(NodeType.EMPTY);
//		root.setNe(ne);
//		tree.balance(node);
//
//		root.getNe().setNodeType(NodeType.EMPTY);
//		root.setSw(sw);
//		tree.balance(node);
//
//		root.getSw().setNodeType(NodeType.EMPTY);
//		root.setSe(se);
//		tree.balance(node);
		node.setNodeType(NodeType.LEAF);
		node.setPoint(new Point(1, 1, 1));
		Field field = QuadTree.class.getDeclaredField("root_");
		field.setAccessible(true);
		field.set(tree, node);
		tree.searchIntersect(0, 0, 2, 2);
		tree.getValues();
		tree.searchWithin(0, 0, 2, 2);
		tree.clone();

		node.setNodeType(NodeType.POINTER);
		field.set(tree, node);
		tree.searchIntersect(0, 5, 2, 2);
//		tree.getValues();
		tree.searchWithin(0, 5, 2, 2);
//		tree.clone();

	}

	@Test
	public void testPoint() {
		Point point = new Point(1, 1, 1);
		point.setX(2);
		point.setY(2);
		point.setValue(2);
		System.out.println(point.toString());
		System.out.println(point.compareTo(new Point(1, 1, 1)));
		System.out.println(point.compareTo(new Point(3, 1, 1)));
		System.out.println(point.compareTo(new Point(2, 1, 1)));
		System.out.println(point.compareTo(new Point(2, 3, 1)));
		System.out.println(point.compareTo(new Point(2, 1, 0)));
		System.out.println(point.compareTo(new Point(2, 2, 0)));

	}

}
