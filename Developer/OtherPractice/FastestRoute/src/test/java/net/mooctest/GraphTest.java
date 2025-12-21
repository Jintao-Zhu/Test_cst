package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class GraphTest {

    // 全局输出流缓冲区
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static PrintStream originalOut;

    // 用于标准化输出的辅助方法
    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "")
                .replaceAll("\\w{3}\\w{3}\\d{2}\\d{6}CST\\d{4}", "")
                .trim();
    }

    @BeforeClass
    public static void setUp() {
        // 记录原始输出流
        originalOut = System.out;
        // 重定向 System.out 到全局缓冲区
        System.setOut(new PrintStream(outputStream));
    }

    @AfterClass
    public static void tearDownAndVerify() {
        // 恢复原始输出流
        System.setOut(originalOut);

        // 获取和标准化所有测试输出
        String allOutput = standardizeOutput(outputStream.toString());

        // 定义预期输出的标准化形式
        String expectedOutput = "∞ 5.0 ∞ \n" +
                "∞ ∞ ∞ \n" +
                "∞ ∞ ∞ ";  // 需要根据预期结果填写
        expectedOutput = standardizeOutput(expectedOutput);

        // 统一比较所有输出
        assertEquals(expectedOutput, allOutput);
    }

	@Test
    public void testNodeConstructorAndGetters() {
        Node node = new Node(1, false, "Highway", false, false, false, 1.5, 0, 24);
        Node node1 = new Node(1, true, "Highway", false, false, false, 1.5, 0, 24);
        assertEquals(1, node.getNodeId());
        assertFalse(node.isObstacle());
        assertEquals("Highway", node.getRoadType());
        assertFalse(node.isTollRoad());
        assertFalse(node.isRestrictedForHeavyVehicles());
        assertFalse(node.isHighRiskArea());
        assertEquals(1.5, node.getCostPerKm(), 0.00001);
        assertTrue(node.isOpenAt(1));
        assertFalse(node.isOpenAt(-1));
        assertFalse(node.isOpenAt(25));
        assertEquals(new ArrayList<>(), node.getNeighbors());
        node.addNeighbor(node1, 1);
        assertNotNull(node.getNeighbors());
        node1.addNeighbor(node, 1);
    }


	@Test
    public void testGraphConstructorAndGetters() {
        Graph graph = new Graph();
        Node node1 = new Node(1, false, "Highway", false, false, false, 1.5, 0, 24);
        Node node2 = new Node(2, true, "Highway", false, false, false, 1.5, 0, 24);
        graph.addNode(node1);
        graph.addNode(node2);
        assertNotNull(graph.getNodes());
        assertEquals(2, graph.getNodes().size());
        graph.addEdge(1, 2, 5);
        graph.addEdge(5, 2, 5);
        graph.addEdge(1, 6, 5);
        assertEquals(node2, graph.getNode(2));
    }

	@Test
    public void testAStarPath() {
        Graph graph = new Graph();
        Node node1 = new Node(1, true, "Highway", true, false, false, 1, 0, 24);
        Node node2 = new Node(2, false, "Highway", false, false, false, 2, 0, 24);
        Node node3 = new Node(3, true, "regular road", true, true, true, 3, 0, 24);
        node1.addNeighbor(node2, 1);
        node1.addNeighbor(node3, 1);
        node2.addNeighbor(node3, 1);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);

        Map<Integer, String> trafficData = new HashMap<>();
        Vehicle vehicle = new Vehicle("Standard Vehicle", 100, true, 100, 100, 10, 20, true);
        TrafficCondition trafficCondition = new TrafficCondition(trafficData);
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        AStar aStar1 = new AStar(graph, node1, node2, vehicle, trafficCondition, weatherCondition, 10);
        aStar1.findPath();
        AStar aStar2 = new AStar(graph, node3, node3, vehicle, trafficCondition, weatherCondition, 10);
        aStar2.findPath();


        Node node4 = new Node(1, false, "Highway", true, false, true, 1, 0, 24);
        Node node5 = new Node(2, false, "Highway", false, false, true, 2, 0, 24);
//        Node node6 = new Node(3, true, "regular road", true, true, true, 3, 0, 24);
        Vehicle vehicle1 = new Vehicle("Standard Vehicle", 100, true, 100, 0, 10, 20, false);

        AStar aStar3 = new AStar(graph, node1, node2, vehicle1, trafficCondition, weatherCondition, -1);
        aStar3.findPath();
        AStar aStar4 = new AStar(graph, node4, node5, vehicle, trafficCondition, weatherCondition, 10);
        assertNull(aStar4.findPath());
    }

	@Test
    public void testBellmanFordPath() {
        Graph graph = new Graph();
        Node node1 = new Node(1, true, "Highway", true, false, false, 1, 0, 24);
        Node node2 = new Node(2, false, "Highway", false, false, false, 2, 0, 24);
        Node node3 = new Node(3, true, "regular road", true, true, true, 3, 0, 24);
        node1.addNeighbor(node2, 1);
        node1.addNeighbor(node3, 1);
        node2.addNeighbor(node3, 1);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);

        Map<Integer, String> trafficData = new HashMap<>();
        Vehicle vehicle = new Vehicle("Standard Vehicle", 100, true, 100, 100, 10, 20, true);
        TrafficCondition trafficCondition = new TrafficCondition(trafficData);
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        BellmanFord bellmanFord1 = new BellmanFord(graph, node1, node2, vehicle, trafficCondition, weatherCondition, 10);
        bellmanFord1.findPath();

    }

	@Test
    public void testDijkstraPath() {
        Graph graph = new Graph();
        Node node1 = new Node(1, true, "Highway", true, false, false, 1, 0, 24);
        Node node2 = new Node(2, false, "Highway", false, false, false, 2, 0, 24);
        Node node3 = new Node(3, true, "regular road", true, true, true, 3, 0, 24);
        node1.addNeighbor(node2, 1);
        node1.addNeighbor(node3, 1);
        node2.addNeighbor(node3, 1);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);

        Map<Integer, String> trafficData = new HashMap<>();
        Vehicle vehicle = new Vehicle("Standard Vehicle", 100, true, 100, 100, 10, 20, true);
        TrafficCondition trafficCondition = new TrafficCondition(trafficData);
        WeatherCondition weatherCondition = new WeatherCondition("Clear");
        Map<Integer, GasStation> gasStations = new HashMap<>();

        Dijkstra dijkstra = new Dijkstra(graph, node1, node2, vehicle, trafficCondition, weatherCondition, 10, gasStations);
        dijkstra.findPath();

        Node node4 = new Node(1, false, "Highway", true, false, true, 1, 0, 24);
        Node node5 = new Node(2, false, "Highway", false, false, true, 2, 0, 24);
        Vehicle vehicle1 = new Vehicle("Standard Vehicle", 100, true, 100, 0, 10, 20, false);

        Dijkstra dijkstra1 = new Dijkstra(graph, node1, node2, vehicle1, trafficCondition, weatherCondition, -1, gasStations);
        dijkstra1.findPath();
        Dijkstra dijkstra2 = new Dijkstra(graph, node4, node5, vehicle, trafficCondition, weatherCondition, 25, gasStations);
        dijkstra2.findPath();

    }

	@Test
    public void testFloydWarshallPath() {
        Graph graph = new Graph();
        Node node1 = new Node(1, true, "Highway", true, false, false, 1, 0, 24);
        Node node2 = new Node(2, false, "Highway", false, false, false, 2, 0, 24);
        Node node3 = new Node(3, true, "regular road", true, true, true, 3, 0, 24);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 6);
        FloydWarshall floydWarshall = new FloydWarshall(graph);
        floydWarshall.getShortestPath(node1, node2);
        floydWarshall.printDistanceMatrix();
    }

	@Test
    public void testIterativeDeepeningSearchPath() {
        Graph graph = new Graph();
        Node node1 = new Node(1, true, "Highway", true, false, false, 1, 0, 24);
        Node node2 = new Node(2, false, "Highway", false, false, false, 2, 0, 24);
        Node node3 = new Node(3, true, "regular road", true, true, true, 3, 0, 24);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 6);


        Map<Integer, String> trafficData = new HashMap<>();
        Vehicle vehicle = new Vehicle("Standard Vehicle", 100, true, 100, 100, 10, 20, true);
        TrafficCondition trafficCondition = new TrafficCondition(trafficData);
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        IterativeDeepeningSearch iterativeDeepeningSearch = new IterativeDeepeningSearch(graph, node1, node2, vehicle, trafficCondition, weatherCondition, 10, 5);
        iterativeDeepeningSearch.findPath();
        Set<Node> visited = new HashSet<>();
        iterativeDeepeningSearch.depthLimitedSearch(node1, node3, 2, visited);
    }

	@Test
    public void testVehicleConstructorAndGetters() {
        Vehicle vehicle = new Vehicle("Standard Vehicle", 100, true, 100, 100, 10, 20, true);
        assertEquals("Standard Vehicle", vehicle.getVehicleType());
        assertEquals(100, vehicle.getMaxLoad(), 0.00001);
        assertTrue(vehicle.requiresTollFreeRoute());
        assertEquals(100, vehicle.getFuelCapacity(), 0.00001);
        assertEquals(100, vehicle.getCurrentFuel(), 0.00001);
        assertEquals(10, vehicle.getFuelConsumptionPerKm(), 0.00001);
        assertEquals(20, vehicle.getMinFuelAtEnd(), 0.00001);
        assertTrue(vehicle.isEmergencyVehicle());
        vehicle.consumeFuel(10);
        assertEquals(0, vehicle.getCurrentFuel(), 0.00001);
        vehicle.refuel(5);
        assertEquals(5, vehicle.getCurrentFuel(), 0.00001);
        assertTrue(vehicle.needsRefueling(100));
    }

	@Test
    public void testTrafficConditionConstructorAndGetters() {
        Map<Integer, String> trafficData = new HashMap<>();
        TrafficCondition trafficCondition = new TrafficCondition(trafficData);
    }

	@Test
    public void testWeatherConditionConstructorAndGetters() {
        Node node1 = new Node(1, true, "Highway", true, false, false, 1, 0, 24);
        Node node2 = new Node(2, false, "Highway", false, false, false, 2, 0, 24);
        WeatherCondition weatherCondition1 = new WeatherCondition("Clear");
        WeatherCondition weatherCondition2 = new WeatherCondition("Congested");
        WeatherCondition weatherCondition3 = new WeatherCondition("Closed");
        WeatherCondition weatherCondition4 = new WeatherCondition("Accident");
        assertEquals(1, weatherCondition1.adjustWeightForWeather(1,node1), 0.0001);
        assertEquals(2, weatherCondition2.adjustWeightForWeather(2,node2), 0.0001);
        assertEquals(3, weatherCondition3.adjustWeightForWeather(3,node1), 0.0001);
        assertEquals(4, weatherCondition4.adjustWeightForWeather(4,node1),  0.0001);
    }

	@Test
    public void testPathResult() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Node node1 = new Node(1, true, "Highway", true, false, false, 1, 0, 24);
        Node node2 = new Node(2, false, "Highway", false, false, false, 2, 0, 24);

        List<Node> path = new ArrayList<>();
        path.add(node1);
        path.add(node2);
        PathResult pathResult = new PathResult(path);
        assertEquals(path, pathResult.getPath());
        pathResult.printPath();

        String expectedoutput = "1 -> 2 -> End";
        String output = outputStream.toString().trim();
        System.setOut(originalOut);
        assertEquals(expectedoutput, output);

    }

	@Test
    public void testShortestTimeFirst() {

        Graph graph = new Graph();
        Node node1 = new Node(1, true, "Highway", true, false, false, 1, 0, 24);
        Node node2 = new Node(2, false, "Highway", false, false, false, 2, 0, 24);
        Node node3 = new Node(3, true, "regular road", true, true, true, 3, 0, 24);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 6);
        Edge edge = new Edge(node1, 3);
        Edge edge1 = new Edge(node3, 3);

        Map<Integer, String> trafficData = new HashMap<>();
        Vehicle vehicle = new Vehicle("Standard Vehicle", 100, true, 100, 100, 10, 20, true);
        TrafficCondition trafficCondition = new TrafficCondition(trafficData);
        WeatherCondition weatherCondition = new WeatherCondition("Clear");

        ShortestTimeFirst shortestTimeFirst = new ShortestTimeFirst(graph, node1, node2, vehicle, trafficCondition, weatherCondition, 10);
        shortestTimeFirst.findPath();
        shortestTimeFirst.calculateTravelTime(edge, vehicle);

        Map<Node, Node> predecessors = new HashMap<>();
        shortestTimeFirst.reconstructPath(predecessors);

        ShortestTimeFirst shortestTimeFirst1 = new ShortestTimeFirst(graph, node3, node2, vehicle, trafficCondition, weatherCondition, 10);
        shortestTimeFirst1.findPath();
        assertEquals(0.06, shortestTimeFirst1.calculateTravelTime(edge1, vehicle), 0.00001);
    }



}
