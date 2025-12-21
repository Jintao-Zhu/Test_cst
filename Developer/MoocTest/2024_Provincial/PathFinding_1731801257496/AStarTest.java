package net.mooctest;

import static net.mooctest.Grid.MAX_OPEN_NODE_SIZE;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/*
 * 测试代码基于JUnit 4，若eclipse提示未找到Junit 5的测试用例，请在Run Configurations中设置Test Runner为Junit 4。请不要使用Junit 5
 * 语法编写测试代码
 */

public class AStarTest {


    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static PrintStream originalOut;

    @BeforeClass
    public static void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterClass
    public static void tearDownAndVerify() {
        System.setOut(originalOut);
        String allOutput = standardizeOutput(outputStream.toString());
        assertEquals(142, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Test
    public void testSearch_WithNoObstacles_ShouldReturnPath() {
        ThreadLocal<AStar> nodes = ThreadLocalAStar.local;
        System.out.println(nodes);
        ThreadLocalAStar.current().search(0, 0, 1, 1, new Grid(2, 2));
    }
    @Test
    public void testAstarSearch() {
        AStar aStar = new AStar();
        Grid grid = new Grid(10, 10);
        Path path = aStar.search(0, 0, 9, 9, grid);
        aStar.search(-1, -1, 9, 9, grid, path);
        aStar.search(1, 1, -9, -9, grid, path);
        aStar.search(1, 1, 1, 1, grid, path);
    }
    @Test
    public void testAstarOpen() {
        AStar aStar = new AStar();
        Grid grid = new Grid(10, 10);
        System.out.println(grid.info(9, 9));
        aStar.search(100, 100, 0, 0, grid);
    }
    @Test
    public void testAstarfillpath1() {
        AStar aStar = new AStar();
        Grid grid = new Grid(10, 10);
        Path path = aStar.search(0, 0, 9, 9, grid);
//        aStar.fillPath(0, 0, 9, 9, path, grid, true);

    }
    @Test
    public void testAstarfillpath2() {
        AStar aStar = new AStar();
        Grid grid = new Grid(10, 10);
        Path path = aStar.search(0, 0, 9, 9, grid);
        Path path2 = new Path();
        path2.add(1, 1);
        path2.add(2, 2);
        path2.add(3, 3);
        aStar.fillPath(2, 2, path2, grid, true);
        aStar.fillPath(0, 0, path, grid, true);
        aStar.fillPath(10, 10, path, grid, true);
        aStar.fillPath(5, 5, path, grid, false);
        aStar.fillPath(-1, -5, path, grid, true);

    }

    public class SimpleFence implements Fence {
        @Override
        public boolean isReachable(int x1, int y1, int x2, int y2) {
            return x1 == x2 & y1 == y2;
        }
    }

    @Test
    public void testReachability() {
        AStar aStar = new AStar();
        Grid grid = new Grid(10, 10);
        Path path = aStar.search(0, 0, 9, 9, grid);
        Reachability.getClosestWalkablePointToTarget(0, 0, 9, 9, grid);
        try {
            Reachability.getClosestWalkablePointToTarget(0, 0, 9, 9, 0, grid, null);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        Reachability.getClosestWalkablePointToTarget(1, 1, 1, 1, grid);
        Reachability.getClosestWalkablePointToTarget(3, 1, 9, 1, grid);
        Reachability.getClosestWalkablePointToTarget(3, 1, 3, 5, grid);
        Reachability.getClosestWalkablePointToTarget(1, 3, 4, 5, grid);

        Fence fence = new SimpleFence();
        Reachability.getClosestWalkablePointToTarget(-1, -1, -1, -1, 5, grid, fence);
        Reachability.getClosestWalkablePointToTarget(-3, -1, -9, -1, 5, grid, fence);
        Reachability.getClosestWalkablePointToTarget(3, 1, 3, 5, 5, grid, fence);
        Reachability.getClosestWalkablePointToTarget(1, -1, 1, -1, 5, grid, fence);
        Reachability.getClosestWalkablePointToTarget(-3, -1, 100, 100, 5, grid, fence);
        Reachability.getClosestWalkablePointToTarget(3, 1, 3, 5, 5, grid, fence);
        Reachability.getClosestWalkablePointToTarget(1, 3, 4, 5, 5, grid, fence);
        Reachability.getClosestWalkablePointToTarget(-3, -1, -3, -5, 5, grid, fence);
        Reachability.getClosestWalkablePointToTarget(-1, -3, -4, -5, 5, grid, fence);
    }
    @Test
    public void testPath() {
        AStar aStar = new AStar();
        Grid grid = new Grid(10, 10);
        Path path = aStar.search(0, 0, 9, 9, grid);
        path.size = 50;
        System.out.println(path.size);
        System.out.println(path.ps.length);
        System.out.println(path.isEmpty());
        path.add(1, 1);
        path.clear();
        System.out.println(path.isEmpty());
        path.add(1, 1);
        path.remove();
//        try {
//            path.get(1);
//        } catch(AssertionError e) {
//            System.out.println(e.getMessage());
//        }
        path.grow(10);
        path.grow(100);
        path.clear();
        try {
            path.grow(-1);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
//        try {
//            path.get(-1);
//        } catch(AssertionError e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            path.get(100);
//        } catch(AssertionError e) {
//            System.out.println(e.getMessage());
//        }
    }
    @Test
    public void testGrid() {
        try {
            Grid grid = new Grid(0, 0);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Grid grid = new Grid(100, 100);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        Grid grid = new Grid(10, 10);
        grid.setWalkable(1, 1, false);
        System.out.println(grid.isWalkable(1, 1));
        System.out.println(grid.isWalkable(1, -1));
        System.out.println(grid.isWalkable(1, 100));
        System.out.println(grid.isClean());
        System.out.println(Grid.isUnwalkable(1));

        grid.setWalkable(1, 1, true);

//        try {
//            Grid.openNodeIdx(-1);
//        } catch(AssertionError e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            Grid.openNodeIdx(MAX_OPEN_NODE_SIZE + 1);
//        } catch(AssertionError e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            grid.openNodeIdxUpdate(1, 1, MAX_OPEN_NODE_SIZE + 1);
//        } catch(AssertionError e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            grid.openNodeIdxUpdate(1, 1, -1);
//        } catch(AssertionError e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            grid.nodeParentDirectionUpdate(1, 1, MAX_OPEN_NODE_SIZE + 1);
//        } catch(AssertionError e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            grid.nodeParentDirectionUpdate(1, 1, -1);
//        } catch(AssertionError e) {
//            System.out.println(e.getMessage());
//        }

    }

    @Test
    public void testNode() {
        try {
            Node.toNode(1, 1, 1, -1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testNodes() {
        Nodes nodes = new Nodes();
        nodes.size = MAX_OPEN_NODE_SIZE + 10;
        try {
            nodes.open(1, 1, 1, 1, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        nodes.size = 0;
        System.out.println(nodes.close());
        nodes.grow(50);
        nodes.nodes = new long[100];
        nodes.grow(5);
    }

    @Test
    public void testUtils() {
        try {
            Utils.check(false, "string");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Utils.check(false, "string", 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Utils.mask(-1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Utils.mask(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Utils.check(true, "string");
        Utils.check(true, "string", 1);
        System.out.println(Utils.mask(32));
    }

}
