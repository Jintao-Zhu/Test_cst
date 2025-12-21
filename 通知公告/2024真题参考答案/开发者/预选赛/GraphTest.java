package net.mooctest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class GraphTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = new PrintStream(outContent);
    private Graph MyGra;
    private Node startNode;
    private Node endNode;
    private Vehicle vehicle;
    private TrafficCondition trafficCondition;
    private WeatherCondition weatherCondition;
    private int currentTime;
    private AStar aStar;

    @Test(timeout = 1500)
    public void testNodeConstructorAndGetters() {
        Node node = new Node(1, false, "Highway", false, false, false, 1.5, 0, 24);
        assertEquals(1, node.getNodeId());

    }


    @Test(timeout = 1500)
    public void testHeuristic() {
        // 创建必要的节点和图
        Node endNode = new Node(10, false, "Regular", false, false, false, 0, 0, 24);
        Node nodeHighway = new Node(5, false, "Highway", false, false, false, 0, 0, 24);
        Node nodeRegular = new Node(5, false, "Regular", false, false, false, 0, 0, 24);
        Node nodeHighRisk = new Node(5, false, "Regular", false, false, true, 0, 0, 24);
        Node nodeHighwayHighRisk = new Node(5, false, "Highway", false, false, true, 0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(endNode);
        MyGra.addNode(nodeHighway);
        MyGra.addNode(nodeRegular);
        MyGra.addNode(nodeHighRisk);
        MyGra.addNode(nodeHighwayHighRisk);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        AStar aStar = new AStar(MyGra, nodeRegular, endNode, vehicle, trafficCondition, weatherCondition, 0);

        // 测试不同情况下的启发函数
        double heuristicHighway = aStar.heuristic(nodeHighway);
        double heuristicRegular = aStar.heuristic(nodeRegular);
        double heuristicHighRisk = aStar.heuristic(nodeHighRisk);
        double heuristicHighwayHighRisk = aStar.heuristic(nodeHighwayHighRisk);

        // 断言验证
        assertEquals(5 * 0.8, heuristicHighway, 1e-5);
        assertEquals(5 * 1.0, heuristicRegular, 1e-5);
        assertEquals(5 * 2.0, heuristicHighRisk, 1e-5);
        assertEquals(5 * 2.0, heuristicHighwayHighRisk, 1e-5);
    }

    @Test(timeout = 1500)
    public void testFindPath_NoPathDueToObstacle() {
        // 设置一个有障碍的节点，确保路径不存在
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node obstacleNode = new Node(2, true, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);
        MyGra.addNode(obstacleNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(obstacleNode, 1);
        obstacleNode.addNeighbor(endNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        AStar aStar = new AStar(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0);

        // 执行查找路径方法
        PathResult result = aStar.findPath();

        // 验证结果应为 null，因为没有可行路径
        assertNull(result);
    }

    @Test(timeout = 1500)
    public void testFindPath_RoadClosed() {
        // 设置道路在当前时间关闭
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 8, 20);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 8, 20);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 8, 20);

        MyGra.addNode(startNode);
        MyGra.addNode(middleNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(middleNode, 1);
        middleNode.addNeighbor(endNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        // 当前时间为 22 点，道路已关闭
        AStar aStar = new AStar(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 22);

        // 执行查找路径方法
        PathResult result = aStar.findPath();

        // 验证结果应为 null，因为道路关闭
        assertNull(result);
    }

    @Test(timeout = 1500)
    public void testFindPath_NeedsRefueling() {
        // 设置车辆燃料不足，无法到达目的地
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(endNode, 100); // 距离设置为 100 公里

        Vehicle vehicle = new Vehicle("Standard", 0, false, 50, 10, 1, 5, false); // 当前燃料仅 10，无法到达

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        AStar aStar = new AStar(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0);

        // 执行查找路径方法
        PathResult result = aStar.findPath();

        // 验证结果应为 null，因为需要加油
        assertNull(result);
    }

    @Test(timeout = 1500)
    public void testFindPath_HighRiskArea() {
        // 设置非紧急车辆无法通过高风险区域
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node highRiskNode = new Node(2, false, "Regular", false, false, true, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);
        MyGra.addNode(highRiskNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(highRiskNode, 1);
        highRiskNode.addNeighbor(endNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false); // 非紧急车辆

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        AStar aStar = new AStar(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0);

        // 执行查找路径方法
        PathResult result = aStar.findPath();

        // 验证结果应为 null，因为无法通过高风险区域
        assertNull(result);
    }

    @Test(timeout = 1500)
    public void testAdjustWeight_TrafficCondition() {
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        trafficCondition.updateTrafficStatus(1, "Congested");
        trafficCondition.updateTrafficStatus(2, "Closed");
        trafficCondition.updateTrafficStatus(3, "Accident");

        // 验证不同交通状况下的权重调整
        assertEquals(10, trafficCondition.adjustWeight(10, 0), 1e-5); // Clear
        assertEquals(20, trafficCondition.adjustWeight(10, 1), 1e-5); // Congested
        assertEquals(Double.MAX_VALUE, trafficCondition.adjustWeight(10, 2), 1e-5); // Closed
        assertEquals(30, trafficCondition.adjustWeight(10, 3), 1e-5); // Accident
    }

    @Test(timeout = 1500)
    public void testFindPath_SimplePath() {
        // 路径存在
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);

        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);
        MyGra.addNode(middleNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(middleNode, 1);
        startNode.addNeighbor(endNode, 10);
        startNode.addNeighbor(middleNode, 5);
        middleNode.addNeighbor(endNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        AStar aStar = new AStar(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0);

        // 执行查找路径方法
        PathResult result = aStar.findPath();

        // 验证结果
        assertNotNull(result);
        List<Node> path = result.getPath();
        assertEquals(3, path.size());
        assertEquals(startNode, path.get(0));
        assertEquals(middleNode, path.get(1));
        assertEquals(endNode, path.get(2));
    }

    @Test(timeout = 1500)
    public void testAdjustWeightForWeather() {
        Node node = new Node(1, false, "Regular", false, false, false, 0, 0, 24);

        WeatherCondition clearWeather = new WeatherCondition("Clear");
        WeatherCondition rainyWeather = new WeatherCondition("Rainy");
        WeatherCondition snowyWeather = new WeatherCondition("Snowy");
        WeatherCondition stormyWeather = new WeatherCondition("Stormy");

        // 验证不同天气条件下的权重调整
        assertEquals(10, clearWeather.adjustWeightForWeather(10, node), 1e-5);
        assertEquals(15, rainyWeather.adjustWeightForWeather(10, node), 1e-5);
        assertEquals(20, snowyWeather.adjustWeightForWeather(10, node), 1e-5);
        assertEquals(30, stormyWeather.adjustWeightForWeather(10, node), 1e-5);
    }

    @Test(timeout = 1500)
    public void testNeedsRefueling() {
        Vehicle vehicle = new Vehicle("Standard", 0, false, 50, 10, 1, 5, false);

        // 验证在不同距离下是否需要加油
        assertTrue(vehicle.needsRefueling(20)); // 需要加油
        assertFalse(vehicle.needsRefueling(1)); // 不需要加油
    }

    @Test(timeout = 1500)
    public void testIsOpenAt() {
        Node node = new Node(1, false, "Regular", false, false, false, 0, 8, 20);

        // 验证节点在不同时间是否开放
        assertTrue(node.isOpenAt(10));
        assertFalse(node.isOpenAt(22));
    }

    @Test(timeout = 1500)
    public void testAddNeighbor() {
        Node node = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node neighbor = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        Node obstacle = new Node(3, true, "Regular", false, false, false, 0, 0, 24);

        node.addNeighbor(neighbor, 1);
        node.addNeighbor(obstacle, 1);

        // 验证邻居列表中不包含障碍物
        assertEquals(1, node.getNeighbors().size());
        assertEquals(neighbor, node.getNeighbors().get(0).getNeighbor());
    }

    @Test(timeout = 1500)
    public void testReconstructPath() {
        // 模拟前驱节点映射
        Map<Node, Node> predecessors = new HashMap<>();
        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        predecessors.put(endNode, middleNode);
        predecessors.put(middleNode, startNode);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(middleNode);
        MyGra.addNode(endNode);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        AStar aStar = new AStar(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0);

        PathResult result = aStar.reconstructPath(predecessors);

        // 验证路径
        List<Node> path = result.getPath();
        assertEquals(3, path.size());
        assertEquals(startNode, path.get(0));
        assertEquals(middleNode, path.get(1));
        assertEquals(endNode, path.get(2));
    }

    @Test(timeout = 1500)
    public void testFindPath_EmergencyVehicleIgnoresRoadClosures() {
        // 设置道路在当前时间关闭，但紧急车辆可以通过，高风险区域更近
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 8, 12);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 8, 12);
        Node highRiskNode = new Node(2, false, "Regular", false, false, true, 0, 0, 24);

        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 8, 12);

        MyGra.addNode(startNode);
        MyGra.addNode(middleNode);
        MyGra.addNode(highRiskNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(middleNode, 2);
        startNode.addNeighbor(highRiskNode, 1);
        highRiskNode.addNeighbor(endNode, 1);
        middleNode.addNeighbor(endNode, 2);

        // 紧急车辆
        Vehicle vehicle = new Vehicle("Emergency", 0, false, 100, 100, 1, 0, true);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        // 当前时间为 22 点，道路已关闭，但紧急车辆可以通过
        AStar aStar = new AStar(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 22);

        // 执行查找路径方法
        PathResult result = aStar.findPath();

        // 验证结果，应能找到路径
        assertNotNull(result);
        List<Node> path = result.getPath();
        assertEquals(3, path.size());
        assertEquals(startNode, path.get(0));
        assertEquals(highRiskNode, path.get(1));
        assertEquals(endNode, path.get(2));
    }

    //    Iterative的情况
    @Test(timeout = 1500)
    public void testFindPath_PathFoundAtDepthZero() {
        // 测试起始节点就是目标节点，深度为0的情况
        Node node = new Node(1, false, "Regular", false, false, false, 0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(node);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        IterativeDeepeningSearch ids = new IterativeDeepeningSearch(MyGra, node, node, vehicle, trafficCondition, weatherCondition, 0, 0);

        PathResult result = ids.findPath();

        // 断言验证
        assertNotNull(result);
        assertEquals(1, result.getPath().size());
        assertEquals(node, result.getPath().get(0));
    }

    @Test(timeout = 1500)
    public void testFindPath_PathNotFoundAtDepthZero() {
        // 测试深度为0但起始节点不是目标节点的情况
        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(endNode);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        IterativeDeepeningSearch ids = new IterativeDeepeningSearch(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, 0);

        PathResult result = ids.findPath();

        // 断言验证
        assertNull(result);
    }

    @Test(timeout = 1500)
    public void testFindPath_PathFoundAtDepthOne() {
        // 测试在深度为1时找到路径的情况
        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(endNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        IterativeDeepeningSearch ids = new IterativeDeepeningSearch(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, 1);

        PathResult result = ids.findPath();

        // 断言验证
        assertNotNull(result);
        assertEquals(2, result.getPath().size());
        assertEquals(startNode, result.getPath().get(0));
        assertEquals(endNode, result.getPath().get(1));
    }

    @Test(timeout = 1500)
    public void testFindPath_PathNotFoundWithinMaxDepth() {
        // 测试在最大深度内找不到路径的情况
        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(middleNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(middleNode, 1);
        middleNode.addNeighbor(endNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        // 最大深度设置为1，不足以到达目标节点
        IterativeDeepeningSearch ids = new IterativeDeepeningSearch(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, 1);

        PathResult result = ids.findPath();

        // 断言验证
        assertNull(result);
    }

    @Test(timeout = 1500)
    public void testFindPath_PathFoundAtDepthTwo() {
        // 测试在深度为2时找到路径的情况
        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(middleNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(middleNode, 1);
        middleNode.addNeighbor(endNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        // 最大深度设置为2，足以到达目标节点
        IterativeDeepeningSearch ids = new IterativeDeepeningSearch(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, 2);

        PathResult result = ids.findPath();

        // 断言验证
        assertNotNull(result);
        assertEquals(3, result.getPath().size());
        assertEquals(startNode, result.getPath().get(0));
        assertEquals(middleNode, result.getPath().get(1));
        assertEquals(endNode, result.getPath().get(2));
    }


    @Test(timeout = 1500)
    public void testFindPath_NoPathDueToObstacles() {
        // 测试由于障碍物无法找到路径的情况
        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node obstacleNode = new Node(2, true, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(obstacleNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(obstacleNode, 1);
        obstacleNode.addNeighbor(endNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        IterativeDeepeningSearch ids = new IterativeDeepeningSearch(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, 2);

        PathResult result = ids.findPath();

        // 断言验证
        assertNull(result);
    }

    @Test(timeout = 1500)
    public void testDepthLimitedSearch_UnvisitedNeighborSkipped() {
        // 测试邻居节点已被访问的情况，应该跳过该邻居
        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node neighborNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(neighborNode);

        startNode.addNeighbor(neighborNode, 1);
        neighborNode.addNeighbor(startNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        IterativeDeepeningSearch ids = new IterativeDeepeningSearch(MyGra, startNode, neighborNode, vehicle, trafficCondition, weatherCondition, 0, 2);

        PathResult result = ids.findPath();

        // 断言验证
        assertNotNull(result);
        assertEquals(2, result.getPath().size());
        assertEquals(startNode, result.getPath().get(0));
        assertEquals(neighborNode, result.getPath().get(1));
    }

    @Test(timeout = 1500)
    public void testDepthLimitedSearch_NoUnvisitedNeighbors() {
        // 测试在深度大于0但没有未访问邻居的情况
        Node node1 = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node node2 = new Node(2, false, "Regular", false, false, false, 0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(node1);
        MyGra.addNode(node2);

        node1.addNeighbor(node2, 1);
        node2.addNeighbor(node1, 1); // 双向连接

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        IterativeDeepeningSearch ids = new IterativeDeepeningSearch(MyGra, node1, node2, vehicle, trafficCondition, weatherCondition, 0, 1);

        // 创建已访问节点集，包含所有节点
        Set<Node> visited = new HashSet<>();
        visited.add(node1);
        visited.add(node2);

        PathResult result = ids.depthLimitedSearch(node1, node2, 1, visited);

        assertNull(result);
    }

    //    Dijkstra类
    @Test(timeout = 1500)
    public void testFindPath_Dj_SimplePath() {
        // 测试基本的路径查找，没有障碍、道路关闭或燃料问题
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        Node neighborNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(4, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);

        MyGra.addNode(middleNode);

        MyGra.addNode(endNode);

        startNode.addNeighbor(middleNode, 5);
        startNode.addNeighbor(middleNode, 15); // 较长路径
        startNode.addNeighbor(neighborNode, 10);
        middleNode.addNeighbor(endNode, 5);

        neighborNode.addNeighbor(endNode, 3);


        Vehicle vehicle = new Vehicle("Standard", 0, false, 50, 50, 1, 0, false);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        Map<Integer, GasStation> gasStations = new HashMap<>();

        Dijkstra dijkstra = new Dijkstra(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, gasStations);

        // 执行查找路径方法
        PathResult result = dijkstra.findPath();

        // 验证结果
        assertNotNull(result);
        List<Node> path = result.getPath();
        assertEquals(3, path.size());
        assertEquals(startNode, path.get(0));
        assertEquals(middleNode, path.get(1));
        assertEquals(endNode, path.get(2));
    }

    @Test(timeout = 1500)
    public void testFindPath_RoadObstacle() {
//        测试路径关闭的情况
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node middleNode = new Node(2, true, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(4, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);

        MyGra.addNode(middleNode);

        MyGra.addNode(endNode);

        startNode.addNeighbor(middleNode, 5);
        startNode.addNeighbor(middleNode, 15); // 较长路径
        middleNode.addNeighbor(endNode, 5);


        Vehicle vehicle = new Vehicle("Standard", 0, false, 50, 50, 1, 0, false);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        Map<Integer, GasStation> gasStations = new HashMap<>();

        Dijkstra dijkstra = new Dijkstra(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, gasStations);

        // 执行查找路径方法
        PathResult result = dijkstra.findPath();

        // 验证结果
        assertNull(result);

    }

    @Test(timeout = 1500)
    public void testFindPath_Dj_RoadClosed() {
        // 测试道路在当前时间关闭的情况
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 8, 20);
        Node closedNode = new Node(2, false, "Regular", false, false, false, 0, 8, 20);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 8, 20);

        MyGra.addNode(startNode);
        MyGra.addNode(closedNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(closedNode, 5);
        closedNode.addNeighbor(endNode, 5);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 50, 50, 1, 0, false);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        Map<Integer, GasStation> gasStations = new HashMap<>();

        // 当前时间为 22 点，道路已关闭
        Dijkstra dijkstra = new Dijkstra(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 22, gasStations);

        // 执行查找路径方法
        PathResult result = dijkstra.findPath();

        // 验证结果，应为 null，因为道路关闭
        assertNull(result);
    }

    @Test(timeout = 1500)
    public void testFindPath_Dj_HighRiskArea() {
        // 测试非紧急车辆无法通过高风险区域
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node highRiskNode = new Node(2, false, "Regular", false, false, true, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);
        MyGra.addNode(highRiskNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(highRiskNode, 5);
        highRiskNode.addNeighbor(endNode, 5);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 50, 50, 1, 0, false);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        Map<Integer, GasStation> gasStations = new HashMap<>();

        Dijkstra dijkstra = new Dijkstra(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, gasStations);

        // 执行查找路径方法
        PathResult result = dijkstra.findPath();

        // 验证结果，应为 null，因为无法通过高风险区域
        assertNull(result);
    }

    @Test(timeout = 1500)
    public void testFindPath_Dj_EmergencyVehicleIgnoresClosuresAndHighRisk() {
        // 紧急车辆可以无视道路关闭和高风险区域
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 8, 8);
        Node highRiskNode = new Node(2, false, "Regular", false, false, true, 0, 8, 12);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 8, 12);

        MyGra.addNode(startNode);
        MyGra.addNode(highRiskNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(highRiskNode, 5);
        highRiskNode.addNeighbor(endNode, 5);

        // 紧急车辆
        Vehicle vehicle = new Vehicle("Emergency", 0, false, 50, 50, 1, 0, true);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        Map<Integer, GasStation> gasStations = new HashMap<>();

        // 道路已关闭，但紧急车辆可以通过
        Dijkstra dijkstra = new Dijkstra(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 22, gasStations);

        // 执行查找路径方法
        PathResult result = dijkstra.findPath();

        // 验证结果，应能找到路径
        assertNotNull(result);
        List<Node> path = result.getPath();
        assertEquals(3, path.size());
        assertEquals(startNode, path.get(0));
        assertEquals(highRiskNode, path.get(1));
        assertEquals(endNode, path.get(2));
    }

    @Test(timeout = 1500)
    public void testFindPath_Dj_NeedsRefuelingWithGasStation() {
        // 车辆需要加油且有加油站的情况
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node gasStationNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);
        MyGra.addNode(gasStationNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(gasStationNode, 25);
        gasStationNode.addNeighbor(endNode, 25);
        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 40, 1, 15, true);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        // 在 gasStationNode 处有加油站
        GasStation gasStation = new GasStation(2, 1.5);
        Map<Integer, GasStation> gasStations = new HashMap<>();
        gasStations.put(2, gasStation);

        Dijkstra dijkstra = new Dijkstra(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, gasStations);

        // 执行查找路径方法
        PathResult result = dijkstra.findPath();

        // 验证结果，应能找到路径
        assertNotNull(result);
        List<Node> path = result.getPath();
        assertEquals(3, path.size());
        assertEquals(startNode, path.get(0));
        assertEquals(gasStationNode, path.get(1));
        assertEquals(endNode, path.get(2));

        // 验证车辆已加油
        assertEquals(40, vehicle.getCurrentFuel(), 1e-5);
    }

    @Test(timeout = 1500)
    public void testFindPath_NeedsRefuelingWithoutGasStation() {
        // 测试车辆需要加油但没有加油站的情况
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);
        MyGra.addNode(middleNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(middleNode, 25); // 距离超过燃料容量
        middleNode.addNeighbor(endNode, 25);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 50, 20, 1, 5, false);

        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        Map<Integer, GasStation> gasStations = new HashMap<>(); // 没有加油站

        Dijkstra dijkstra = new Dijkstra(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, gasStations);

        // 执行查找路径方法
        PathResult result = dijkstra.findPath();

        // 验证结果，应为 null，因为无法加油
        assertNull(result);
    }


    @Test(timeout = 1500)
    public void test_DjReconstructPath() {
        // 模拟前驱节点映射
        Map<Node, Node> predecessors = new HashMap<>();
        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        predecessors.put(endNode, middleNode);
        predecessors.put(middleNode, startNode);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(middleNode);
        MyGra.addNode(endNode);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        Map<Integer, GasStation> gasStations = new HashMap<>();

        Dijkstra dijkstra = new Dijkstra(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0, gasStations);

        PathResult result = dijkstra.reconstructPath(predecessors);

        // 验证路径
        List<Node> path = result.getPath();
        assertEquals(3, path.size());
        assertEquals(startNode, path.get(0));
        assertEquals(middleNode, path.get(1));
        assertEquals(endNode, path.get(2));
    }

    //  其他类测试
    @Test(timeout = 1500)
    public void testVehicle() {


        Vehicle vehicle = new Vehicle("Standard", 0, false, 50, 50, 1, 0, false);
        assertEquals("Standard", vehicle.getVehicleType());
        assertEquals(0, vehicle.getMaxLoad(), 1e-5);
        assertFalse(vehicle.requiresTollFreeRoute());
        assertEquals(50, vehicle.getFuelCapacity(), 1e-5);
        assertEquals(1, vehicle.getFuelConsumptionPerKm(), 1e-5);
        assertEquals(0, vehicle.getMinFuelAtEnd(), 1e-5);
        vehicle.consumeFuel(50);
        assertEquals(0, vehicle.getCurrentFuel(), 1e-5);
        vehicle.refuel(30);
        assertEquals(30, vehicle.getCurrentFuel(), 1e-5);
    }

    @Test(timeout = 1500)
    public void testPathNode() {
//      测试PathNode类

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        PathNode pathNode = new PathNode(startNode, 2.5, 3.5);
        assertEquals(startNode, pathNode.getNode());
        assertEquals(2.5, pathNode.getDistance(), 1e-5);
        assertEquals(3.5, pathNode.getEstimatedTotalDistance(), 1e-5);
    }

    @Test(timeout = 1500)
    public void testPathResult() {
        System.setOut(new PrintStream(outContent));

//      测试PathResult类
        Node startNode = new Node(1, false, "Regular", false, false, false, 1, 0, 24);
        Node endNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        List<Node> path = new ArrayList<>();
        path.add(startNode);
        path.add(endNode);
        PathResult pathResult = new PathResult(path);
        assertEquals(path, pathResult.getPath());
        pathResult.printPath();
        String sep = System.lineSeparator();
        assertEquals("1 -> 2 -> End" + sep, outContent.toString());

//      补充测试Node类
        assertFalse(startNode.isTollRoad());
        assertFalse(startNode.isRestrictedForHeavyVehicles());
        assertEquals(1, startNode.getCostPerKm(), 1e-5);
        outContent.reset();
    }

    @Test(timeout = 1500)
    public void testGasStation() {
        System.setOut(new PrintStream(outContent));

        // 创建 GasStation 对象
        GasStation gasStation = new GasStation(1, 1.5);
        assertEquals(1, gasStation.getNodeId());
        assertEquals(1.5, gasStation.getFuelCostPerLitre(), 0.0001);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 50, 10, 1, 5, false);

        gasStation.refuel(vehicle, 20);
        assertEquals(30, vehicle.getCurrentFuel(), 0.0001);

        // 验证
        String expectedOutput = "Refueled 20.0 litres at node 1 for 30.0 cost." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        outContent.reset();
    }

    @Test(timeout = 1500)
    public void testRouteOptimizer() {
        Graph MyGra = new Graph();

        Node startNode = new Node(1, false, "Regular", false, false, false, 0, 0, 24);
        Node middleNode = new Node(2, false, "Regular", false, false, false, 0, 0, 24);
        Node endNode = new Node(3, false, "Regular", false, false, false, 0, 0, 24);

        MyGra.addNode(startNode);
        MyGra.addNode(middleNode);
        MyGra.addNode(endNode);

        startNode.addNeighbor(middleNode, 1);
        middleNode.addNeighbor(endNode, 1);

        Vehicle vehicle = new Vehicle("Standard", 0, false, 100, 100, 1, 0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        AStar aStar = new AStar(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, 0);


        RouteOptimizer routeOptimizer = new RouteOptimizer(aStar);
        PathResult result = routeOptimizer.optimizeRoute();
        // 验证
        assertNotNull(result);
        List<Node> path = result.getPath();
        assertEquals(3, path.size());
        assertEquals(1, path.get(0).getNodeId());
        assertEquals(2, path.get(1).getNodeId());
    }


    @Test(timeout = 1000)
    public void testBellmanFordFindPath() {
        Graph MyGra = new Graph();
        // 创建节点并添加到图中
        Node startNode = new Node(1, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node1 = new Node(2, false, "Highway", false, false, false, 1.0, 0, 24);
        Node node2 = new Node(3, false, "Highway", false, false, true, 1.0, 0, 24);
        Node node3 = new Node(4, false, "Toll Road", false, false, false, 1.0, 0, 24);
        Node endNode = new Node(5, false, "Regular Road", false, false, false, 1.0, 0, 24);
        MyGra.addNode(startNode);
        MyGra.addNode(node1);
        MyGra.addNode(node2);
        MyGra.addNode(node3);
        MyGra.addNode(endNode);
        // 添加边
        MyGra.addEdge(1, 2, 10.0);
        MyGra.addEdge(2, 3, 10.0);
        MyGra.addEdge(3, 4, 10.0);
        MyGra.addEdge(4, 5, 10.0);

        Vehicle vehicle1 = new Vehicle("Standard Vehicle", 100.0, false, 50.0, 25.0, 1.0, 5.0, false);
        Vehicle vehicle2 = new Vehicle("Standard Vehicle", 100.0, false, 50.0, 25.0, 1.0, 5.0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        int currentTime = 0;

        // 测试findPath
        BellmanFord bellmanFord1 = new BellmanFord(MyGra, startNode, endNode, vehicle1, trafficCondition, weatherCondition, currentTime);
        BellmanFord bellmanFord2 = new BellmanFord(MyGra, startNode, endNode, vehicle2, trafficCondition, weatherCondition, currentTime);
        assertEquals(1, bellmanFord1.findPath().getPath().get(0).getNodeId());
        assertEquals(1, bellmanFord2.findPath().getPath().get(0).getNodeId());
    }
    @Test(timeout = 1500)
    public void testBellmanFordReconstructPath() {
        Graph MyGra = new Graph();
        // 创建节点并添加到图中
        Node startNode = new Node(1, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node1 = new Node(2, false, "Highway", false, false, false, 1.0, 0, 24);
        Node node2 = new Node(3, false, "Highway", false, false, true, 1.0, 0, 24);
        Node node3 = new Node(4, false, "Toll Road", false, false, false, 1.0, 0, 24);
        Node endNode = new Node(5, false, "Regular Road", false, false, false, 1.0, 0, 24);
        MyGra.addNode(startNode);
        MyGra.addNode(node1);
        MyGra.addNode(node2);
        MyGra.addNode(node3);
        MyGra.addNode(endNode);
        // 添加边
        MyGra.addEdge(1, 2, 10.0);
        MyGra.addEdge(2, 3, 10.0);
        MyGra.addEdge(3, 4, 10.0);
        MyGra.addEdge(4, 5, 10.0);

        Vehicle vehicle1 = new Vehicle("Standard Vehicle", 100.0, false, 50.0, 25.0, 1.0, 5.0, false);
        Vehicle vehicle2 = new Vehicle("Standard Vehicle", 100.0, false, 50.0, 25.0, 1.0, 5.0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        int currentTime = 0;

        // 测试reconstructPath
        BellmanFord bellmanFord1 = new BellmanFord(MyGra, startNode, endNode, vehicle1, trafficCondition, weatherCondition, currentTime);
        Map<Node, Node> predecessors = new HashMap<>();
        predecessors.put(endNode, null);
        assertEquals(1, bellmanFord1.reconstructPath(predecessors).getPath().size());
    }

    @Test(timeout = 1500)
    public void testFloydWarshall() {
        // 创建节点并添加到图中
        Node startNode = new Node(1, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node1 = new Node(2, false, "Highway", false, false, false, 1.0, 0, 24);
        Node node2 = new Node(3, false, "Highway", false, false, true, 1.0, 0, 24);
        Node node3 = new Node(4, false, "Toll Road", false, false, false, 1.0, 0, 24);
        Node endNode = new Node(5, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node6 = new Node(6, false, "Toll Road", false, false, false, 1.0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(node1);
        MyGra.addNode(node2);
        MyGra.addNode(node3);
        MyGra.addNode(node6);
        MyGra.addNode(endNode);
        // 添加边
        MyGra.addEdge(1, 2, 10.0);
        MyGra.addEdge(2, 3, 10.0);
        MyGra.addEdge(3, 4, 10.0);
        MyGra.addEdge(4, 5, 10.0);


        FloydWarshall floydWarshall = new FloydWarshall(MyGra);
        List<Node> path = floydWarshall.getShortestPath(MyGra.getNode(1), MyGra.getNode(3));
        int size0 = floydWarshall.getShortestPath(MyGra.getNode(6), MyGra.getNode(3)).size();
        assertEquals(3, path.size());
        assertEquals(1, path.get(0).getNodeId());
        assertEquals(2, path.get(1).getNodeId());
        assertEquals(0, size0);
    }

    @Test(timeout = 1500)
    public void testFloydWarshallPrintDistanceMatrix() {
        System.setOut(new PrintStream(outContent));
//        System.setOut(new PrintStream(outContent));
        // 创建节点并添加到图中
        Node startNode = new Node(1, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node1 = new Node(2, false, "Highway", false, false, false, 1.0, 0, 24);
        Node node2 = new Node(3, false, "Highway", false, false, true, 1.0, 0, 24);
        Node node3 = new Node(4, false, "Toll Road", false, false, false, 1.0, 0, 24);
        Node endNode = new Node(5, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node6 = new Node(6, false, "Toll Road", false, false, false, 1.0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(node1);
        MyGra.addNode(node2);
        MyGra.addNode(node3);
        MyGra.addNode(node6);
        MyGra.addNode(endNode);
        // 添加边
        MyGra.addEdge(1, 2, 10.0);
        MyGra.addEdge(2, 3, 10.0);
        MyGra.addEdge(3, 4, 10.0);
        MyGra.addEdge(4, 5, 10.0);
        outContent.reset();

        FloydWarshall floydWarshall = new FloydWarshall(MyGra);


        floydWarshall.printDistanceMatrix();
        assertTrue(outContent.toString().contains("40"));
    }

    @Test(timeout = 1500)
    public void testShortestTimeFirstFindPath() {
        // 创建节点并添加到图中
        Node startNode = new Node(1, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node1 = new Node(2, false, "Highway", false, false, false, 1.0, 0, 24);
        Node node2 = new Node(3, false, "Highway", false, false, true, 1.0, 0, 24);
        Node node3 = new Node(4, false, "Toll Road", false, false, false, 1.0, 0, 24);
        Node endNode = new Node(5, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node6 = new Node(6, false, "Toll Road", false, false, false, 1.0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(node1);
        MyGra.addNode(node2);
        MyGra.addNode(node3);
        MyGra.addNode(node6);
        MyGra.addNode(endNode);
        // 添加边
        MyGra.addEdge(1, 2, 10.0);
        MyGra.addEdge(2, 3, 10.0);
        MyGra.addEdge(3, 4, 10.0);
        MyGra.addEdge(4, 5, 10.0);

        Vehicle vehicle = new Vehicle("Standard Vehicle", 100.0, false, 50.0, 25.0, 1.0, 5.0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        int currentTime = 0;
        ShortestTimeFirst shortestTimeFirst = new ShortestTimeFirst(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, currentTime);
        List<Node> path = shortestTimeFirst.findPath().getPath();
        assertEquals(5, path.size());
        assertEquals(1, path.get(0).getNodeId());
        assertEquals(3, path.get(2).getNodeId());
    }

    @Test(timeout = 1500)
    public void testShortestTimeFirstCalculateTravelTime() {
        // 创建节点并添加到图中
        Node startNode = new Node(1, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node1 = new Node(2, false, "Highway", false, false, false, 1.0, 0, 24);
        Node node2 = new Node(3, false, "Highway", false, false, true, 1.0, 0, 24);
        Node node3 = new Node(4, false, "Toll Road", false, false, false, 1.0, 0, 24);
        Node endNode = new Node(5, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node6 = new Node(6, false, "Toll Road", false, false, false, 1.0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(node1);
        MyGra.addNode(node2);
        MyGra.addNode(node3);
        MyGra.addNode(node6);
        MyGra.addNode(endNode);
        // 添加边
        MyGra.addEdge(1, 2, 10.0);
        MyGra.addEdge(2, 3, 10.0);
        MyGra.addEdge(3, 4, 10.0);
        MyGra.addEdge(4, 5, 10.0);

        Vehicle vehicle1 = new Vehicle("Standard Vehicle", 100.0, false, 50.0, 25.0, 1.0, 5.0, false);
        Vehicle vehicle2 = new Vehicle("Heavy Vehicle", 100.0, false, 50.0, 25.0, 1.0, 5.0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        int currentTime = 0;
        ShortestTimeFirst shortestTimeFirst1 = new ShortestTimeFirst(MyGra, startNode, endNode, vehicle1, trafficCondition, weatherCondition, currentTime);
        assertEquals(0.1, shortestTimeFirst1.calculateTravelTime(MyGra.getNode(1).getNeighbors().get(0), vehicle1), 0.001);
        ShortestTimeFirst shortestTimeFirst2 = new ShortestTimeFirst(MyGra, startNode, endNode, vehicle2, trafficCondition, weatherCondition, currentTime);
        assertEquals(0.133, shortestTimeFirst2.calculateTravelTime(MyGra.getNode(1).getNeighbors().get(0), vehicle2), 0.001);
    }

    @Test(timeout = 1500)
    public void testShortestTimeFirstReconstructPath() {
        // 创建节点并添加到图中
        Node startNode = new Node(1, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node1 = new Node(2, false, "Highway", false, false, false, 1.0, 0, 24);
        Node node2 = new Node(3, false, "Highway", false, false, true, 1.0, 0, 24);
        Node node3 = new Node(4, false, "Toll Road", false, false, false, 1.0, 0, 24);
        Node endNode = new Node(5, false, "Regular Road", false, false, false, 1.0, 0, 24);
        Node node6 = new Node(6, false, "Toll Road", false, false, false, 1.0, 0, 24);

        Graph MyGra = new Graph();
        MyGra.addNode(startNode);
        MyGra.addNode(node1);
        MyGra.addNode(node2);
        MyGra.addNode(node3);
        MyGra.addNode(node6);
        MyGra.addNode(endNode);
        // 添加边
        MyGra.addEdge(1, 2, 10.0);
        MyGra.addEdge(2, 3, 10.0);
        MyGra.addEdge(3, 4, 10.0);
        MyGra.addEdge(4, 5, 10.0);

        Vehicle vehicle = new Vehicle("Standard Vehicle", 100.0, false, 50.0, 25.0, 1.0, 5.0, false);
        TrafficCondition trafficCondition = new TrafficCondition(new HashMap<>());
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        int currentTime = 0;
        ShortestTimeFirst shortestTimeFirst = new ShortestTimeFirst(MyGra, startNode, endNode, vehicle, trafficCondition, weatherCondition, currentTime);
        assertEquals(0.1, shortestTimeFirst.calculateTravelTime(MyGra.getNode(1).getNeighbors().get(0), vehicle), 0.001);
    }


}
