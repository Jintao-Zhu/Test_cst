package net.mooctest;

import com.google.common.base.Function;
import org.junit.Test;

import javax.xml.soap.Node;
import java.util.*;

import static org.junit.Assert.*;

public class TreeLikeTest {
    @Test(timeout = 4000)
    public void testTree() {
        Tree<Integer> integerTree = new Tree<>(1);
        integerTree.breadthFirstIterator();
        integerTree.addChild(new Tree<>(2));
        integerTree.setParent(new Tree<>(3));

        Tree<Integer> integerTree1 = new Tree<>(1);
        integerTree1.addChild(new Tree<>(2));
        integerTree1.setParent(new Tree<>(3));

        integerTree.iterator();
        assertEquals("1", integerTree.getValue().toString());
        assertEquals("3", integerTree.getParent().getValue().toString());
        assertEquals("2", integerTree.getChildren().get(0).getValue().toString());

        Function<Integer, String> strToInt = Object::toString;
        assertEquals("1", integerTree.transform(strToInt).getValue().toString());

        integerTree.sort(Comparator.reverseOrder());

        assertTrue(integerTree.equals(integerTree));
        assertFalse(integerTree.equals("1"));
        assertFalse(integerTree.equals(new Tree<>(1)));
        assertFalse(integerTree.equals(new Tree<>(5)));
        assertTrue(integerTree.equals(integerTree1));

        assertEquals(124, integerTree.hashCode());
        assertEquals("node{1}", integerTree.toString());

        assertNull(integerTree.filter(value -> value > 10));
        assertEquals("1\r\n\t2", integerTree.filter(value -> value > -1).toStringTree());

        assertEquals("[1, 2]", integerTree1.find(value -> value > 0).toString());
        assertEquals("1", integerTree1.findOne(value -> value > 0).toString());
        assertNull(integerTree1.findOne(value -> value > 10));
    }

    @Test(timeout = 4000)
    public void testDFIterator() {
        Tree<Integer> integerTree1 = new Tree<>(1);
        Tree<Integer> integerTree2 = new Tree<>(2);
        Tree<Integer> integerTree3 = new Tree<>(3);

        integerTree1.addChild(integerTree2);
        integerTree2.addChild(integerTree3);
        DFIterator dfIterator = new DFIterator<>(integerTree1);
        assertTrue(dfIterator.hasNext());
        assertEquals(1, dfIterator.next());
        try {
            dfIterator.remove();
        } catch (UnsupportedOperationException e) {}

        DFIterator dfIterator2 = new DFIterator<>(integerTree2);
        assertEquals(2, dfIterator2.next());
    }

    @Test(timeout = 4000)
    public void testBFIterator() {
        Tree<Integer> integerTree1 = new Tree<>(1);
        Tree<Integer> integerTree2 = new Tree<>(2);

        integerTree1.addChild(integerTree2);
        BFIterator bfIterator = new BFIterator(integerTree1);
        assertTrue(bfIterator.hasNext());
        assertEquals(1, bfIterator.next());
        try {
            bfIterator.remove();
        } catch (UnsupportedOperationException e) {}
    }

    @Test(timeout = 4000)
    public void testForest() {
        List<Tree<Integer>> tree = new ArrayList<>();
        Forest<Integer> forest = new Forest<>(tree);

        forest.add(new Tree<>(1));
        forest.sort(Comparator.reverseOrder());
        forest.treeIterator();
        assertEquals("1", forest.filter(value -> value > -1).toStringTree());
        forest.appendToString(new StringBuilder("abc"), 3);
        Function<Integer, String> strToInt = Object::toString;
        forest.transform(strToInt);
        assertEquals("[node{1}]", forest.getChld().toString());
        assertEquals(1, forest.size());
//        assertTrue(forest.isEmpty());
        assertTrue(forest.add(new Tree<>(2)));
        assertEquals("node{2}", forest.get(1).toString());
    }

    private static class TestFunnel implements ChildrenLinkTreeBuilder.Funnel<String> {
        @Override
        public List<String> getChildren(String node) {
            switch (node) {
                case "root": return Arrays.asList("child1", "child2");
                case "child1": return Collections.singletonList("child1.1");
                case "child2": return Collections.emptyList();
                default: return Collections.emptyList();
            }
        }
    }

    @Test
    public void testBuildSingleTree() {
        ChildrenLinkTreeBuilder<String> builder = new ChildrenLinkTreeBuilder<>(new TestFunnel());
        Tree<String> rootTree = builder.build("root");

        assertEquals("root", rootTree.getValue());
        assertEquals(2, rootTree.getChildren().size());

        Tree<String> child1 = rootTree.getChildren().get(0);
        Tree<String> child2 = rootTree.getChildren().get(1);

        assertEquals("child1", child1.getValue());
        assertEquals("child2", child2.getValue());

        assertEquals(1, child1.getChildren().size());
        assertEquals("child1.1", child1.getChildren().get(0).getValue());
        try {
            child2.getChildren();
        } catch (NullPointerException e) {}
    }

    @Test
    public void testBuildForest() {
        ChildrenLinkTreeBuilder<String> builder = new ChildrenLinkTreeBuilder<>(new TestFunnel());
        List<String> roots = Arrays.asList("root", "child2");
        Forest<String> forest = builder.buildForest(roots);

        assertEquals(2, forest.size());
        Tree<String> rootTree = forest.get(0);
        Tree<String> child2Tree = forest.get(1);

        assertEquals("root", rootTree.getValue());
        assertEquals("child2", child2Tree.getValue());
    }

    @Test
    public void testNoChildren() {
        ChildrenLinkTreeBuilder<String> builder = new ChildrenLinkTreeBuilder<>(node -> Collections.emptyList());
        Tree<String> rootTree = builder.build("singleNode");

        assertEquals("singleNode", rootTree.getValue());
        try {
            rootTree.getChildren();
        } catch (NullPointerException e) {}
    }

    public static class TestNode {
        private final Integer id;
        private final Integer parentId;

        public TestNode(Integer id, Integer parentId) {
            this.id = id;
            this.parentId = parentId;
        }

        public Integer getId() {
            return id;
        }

        public Integer getParentId() {
            return parentId;
        }
    }

    private static class TestFunnel1 implements KeyTreeBuilder.Funnel<Integer, TestNode> {
        @Override
        public Integer getKey(TestNode node) {
            return node.getId();
        }

        @Override
        public Integer getParentKey(TestNode node) {
            return node.getParentId();
        }
    }

    @Test
    public void testBuildTreeSingleRoot() {
        KeyTreeBuilder<Integer, TestNode> builder = new KeyTreeBuilder<>(new TestFunnel1());
        List<TestNode> nodes = Arrays.asList(
                new TestNode(1, null), // root
                new TestNode(2, 1),
                new TestNode(3, 1),
                new TestNode(4, 2),
                new TestNode(5, 2)
        );

        Tree<TestNode> rootTree = builder.buildTree(nodes);

        assertEquals("1", rootTree.getValue().getId().toString());
        assertEquals(2, rootTree.getChildren().size());

        Tree<TestNode> child1 = rootTree.getChildren().get(0);
        Tree<TestNode> child2 = rootTree.getChildren().get(1);

        assertEquals("2", child1.getValue().getId().toString());
        assertEquals("3", child2.getValue().getId().toString());

        assertEquals(2, child1.getChildren().size());
        assertEquals("4", child1.getChildren().get(0).getValue().getId().toString());
        assertEquals("5", child1.getChildren().get(1).getValue().getId().toString());
    }

    @Test
    public void testBuildMultipleRootsInForest() {
        KeyTreeBuilder<Integer, TestNode> builder = new KeyTreeBuilder<>(new TestFunnel1());
        List<TestNode> nodes = Arrays.asList(
                new TestNode(1, null), // root 1
                new TestNode(2, 1),
                new TestNode(3, null), // root 2
                new TestNode(4, 3)
        );

        KeyTreeBuilder.BuildResult<Integer, TestNode> result = builder.build(nodes);
        Forest<TestNode> forest = result.getForest();

        assertEquals(2, forest.size());

        Tree<TestNode> root1 = forest.get(0);
        Tree<TestNode> root2 = forest.get(1);

        assertEquals("1", root1.getValue().getId().toString());
        assertEquals("3", root2.getValue().getId().toString());

        assertEquals(1, root1.getChildren().size());
        assertEquals("2", root1.getChildren().get(0).getValue().getId().toString());

        assertEquals(1, root2.getChildren().size());
        assertEquals("4", root2.getChildren().get(0).getValue().getId().toString());
    }

    @Test
    public void testNoRootsFound() {
        KeyTreeBuilder<Integer, TestNode> builder = new KeyTreeBuilder<>(new TestFunnel1());
        List<TestNode> nodes = Arrays.asList(
                new TestNode(1, 2),
                new TestNode(2, 3),
                new TestNode(3, 1)
        );

        try {
            builder.buildTree(nodes);
        } catch (IllegalStateException e) {}
    }

    @Test
    public void testMultipleRootsError() {
        KeyTreeBuilder<Integer, TestNode> builder = new KeyTreeBuilder<>(new TestFunnel1());
        List<TestNode> nodes = Arrays.asList(
                new TestNode(1, null),
                new TestNode(2, null)
        );

        try {
            builder.buildTree(nodes);
        } catch (IllegalStateException e) {}
    }

    private static class TestFunnel2 implements ParentLinkTreeBuilder.Funnel<Tree> {

        @Override
        public Tree getParent(Tree node) {
            return node.getParent();
        }
    }

    @Test
    public void testBuildSingleRootTree() {
        Tree<Integer> integerTree = new Tree<>(1);
        ParentLinkTreeBuilder<Tree> parentLinkTreeBuilder = new ParentLinkTreeBuilder<>(new TestFunnel2());
        Tree tree = parentLinkTreeBuilder.build(Arrays.asList(integerTree));

        assertEquals("node{1}", tree.getValue().toString());
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildSingleRootTree1() {
        Tree<Integer> integerTree = new Tree<>(1);
        Tree<Integer> integerTree1 = new Tree<>(2);

        ParentLinkTreeBuilder<Tree> parentLinkTreeBuilder = new ParentLinkTreeBuilder<>(new TestFunnel2());
        parentLinkTreeBuilder.build(Arrays.asList(integerTree, integerTree1));
    }

    private static class TestFunnel3 implements PathTreeBuilder.Funnel<Tree, Integer> {

        @Override
        public List<Integer> getPath(Tree value) {
            return Collections.emptyList();
        }
    }

    @Test
    public void testPathTreeBuilder() {
        Tree<Integer> integerTree = new Tree<>(1);
        Tree<Integer> integerTree1 = new Tree<>(2);
        Tree<Integer> integerTree2 = new Tree<>(3);
        PathTreeBuilder<Tree, Integer> pathTreeBuilder = new PathTreeBuilder<>(new TestFunnel3());

        Forest<Tree> forest = pathTreeBuilder.build(Arrays.asList(integerTree, integerTree1, integerTree2));
        assertEquals("node{node{3}}", forest.get(0).toString());
    }





}
