package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphTest {

    Graph graph;
    Node startNode;
    Node endNode;
    Vehicle vehicle;
    TrafficCondition trafficCondition;
    WeatherCondition weatherCondition;
    int currentTime;
    Map<Integer, String> trafficData;
    Node middleNode1;
    Node middleNode2;
    Node middleNode3;
    Node middleNode4;

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static PrintStream originalOut;

    @BeforeClass
    public static void setUpClass() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterClass
    public static void tearDownAndVerify() {
        System.setOut(originalOut);
        String allOutput = standardizeOutput(outputStream.toString());
        assertEquals(346, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        startNode = new Node(1, false, "Highway", false, false, false, 1.5, 0, 24);
        endNode = new Node(6, false, "Regular Road", false, false, false, 3, 0, 24);
        vehicle = new Vehicle("Vehicle", 500, false, 100, 50, 2, 10,true);
        trafficData = new HashMap<Integer, String>(){{
            put(1, "Clear");
            put(2, "Congested");
            put(3, "Clear");
            put(4, "Accident");
            put(5, "Clear");
            put(6, "Closed");
            put(7, "Clear");
            put(8, "Congested");
            put(9, "Clear");
            put(10, "Others");
        }};
        trafficCondition = new TrafficCondition(trafficData);
        weatherCondition = new WeatherCondition("Rainy");
        currentTime = 10; // 10 AM
        middleNode1 = new Node(2, false, "Toll Road", false, false, true, 2, 0, 24);
        middleNode2 = new Node(3, true, "Highway", false, false, true, 1, 0, 24);
        middleNode3 = new Node(4, false, "Highway", false, false, true, 1, 0, 24);
        middleNode4 = new Node(5, false, "City Road", false, false, false, 3, 0, 24);
        graph.addNode(startNode);
        graph.addNode(endNode);
        graph.addNode(middleNode1);
        graph.addNode(middleNode2);
        graph.addNode(middleNode3);
        graph.addNode(middleNode4);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 12);
        graph.addEdge(1, 4, 20);
        graph.addEdge(1, 5, 30);
        graph.addEdge(1, 6, 24);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 4, 15);
        graph.addEdge(2, 5, 25);
        graph.addEdge(2, 6, 20);
        graph.addEdge(3, 4, 4);
        graph.addEdge(3, 5, 18);
        graph.addEdge(3, 6, 15);
        graph.addEdge(4, 5, 2);
        graph.addEdge(4, 6, 8);
        graph.addEdge(5, 6, 10);
    }

	@Test
    public void testAStar() {
        AStar aStar = new AStar(graph, startNode, endNode, vehicle, trafficCondition, weatherCondition, currentTime);
        System.out.println(aStar.heuristic(middleNode1));
        System.out.println(aStar.heuristic(middleNode2));

        PathResult result = aStar.findPath();
        System.out.println(result);

        AStar aStar1 = new AStar(graph, startNode, startNode, vehicle, trafficCondition, weatherCondition, currentTime);
        PathResult result1 = aStar1.findPath();
        result1.printPath();
        System.out.println(result1); // currentNode.equals(endNode)

        Vehicle vehicle1 = new Vehicle("Highway", 500, false, 100, 50, 2, 10,false);
        AStar aStar2 = new AStar(graph, startNode, endNode, vehicle1, trafficCondition, weatherCondition, 30);
        System.out.println(aStar2.findPath());

        AStar aStar3 = new AStar(graph, startNode, endNode, vehicle1, trafficCondition, weatherCondition, 24);
        System.out.println(aStar3.findPath());

        System.out.println(graph.getNodes());
        System.out.println(graph.getNode(1));
        graph.addEdge(1, 11, 10); // non-existing node
        graph.addEdge(11, 1, 10); // non-existing node
        System.out.println(startNode.isOpenAt(-1));
    }

    @Test
    public void testBellmanFord() {
        BellmanFord bellmanFord = new BellmanFord(graph, startNode, endNode, vehicle, trafficCondition, weatherCondition, currentTime);
        PathResult result = bellmanFord.findPath();
        result.printPath();
    }

    @Test
    public void testDijkstra() {
        Map<Integer, GasStation> gasStations = new HashMap<>();
        gasStations.put(2, new GasStation(2, 3));
        gasStations.put(3, new GasStation(3, 2));

        Dijkstra dijkstra = new Dijkstra(graph, startNode, endNode, vehicle, trafficCondition, weatherCondition, currentTime, gasStations);
        PathResult result = dijkstra.findPath();
        result.printPath();

        Vehicle vehicle1 = new Vehicle("Highway", 10, false, 100, 50, 2, 10,false);
        Dijkstra dijkstra2 = new Dijkstra(graph, startNode, startNode, vehicle1, trafficCondition, weatherCondition, 100, gasStations);
        PathResult result2 = dijkstra2.findPath();
        result2.printPath();

        Dijkstra dijkstra1 = new Dijkstra(graph, startNode, endNode, vehicle1, trafficCondition, weatherCondition, currentTime, gasStations);
        PathResult result1 = dijkstra1.findPath();
        System.out.println(result1);
    }

    @Test
    public void testFloydWarshall() {
        FloydWarshall floydWarshall = new FloydWarshall(graph);
        List<Node> list = floydWarshall.getShortestPath(startNode, endNode);
        floydWarshall.printDistanceMatrix();
    }

    @Test
    public void testIterativeDeepeningSearch() {
        IterativeDeepeningSearch ids = new IterativeDeepeningSearch(graph, startNode, endNode, vehicle, trafficCondition, weatherCondition, currentTime, 5);
        PathResult result = ids.findPath();
        result.printPath();

        IterativeDeepeningSearch ids1 = new IterativeDeepeningSearch(new Graph(), startNode, startNode, vehicle, trafficCondition, weatherCondition, currentTime, -1);
        ids1.findPath();
    }

    @Test
    public void testShortestTimeFirst() {
        ShortestTimeFirst stf = new ShortestTimeFirst(graph, startNode, endNode, vehicle, trafficCondition, weatherCondition, currentTime);
        PathResult result = stf.findPath();
        System.out.println(result);

        Vehicle vehicle1 = new Vehicle("Heavy Vehicle", 500, false, 100, 50, 2, 10,true);
        ShortestTimeFirst stf1 = new ShortestTimeFirst(graph, startNode, endNode, vehicle1, trafficCondition, weatherCondition, currentTime);
        PathResult result1 = stf1.findPath();

        trafficCondition.adjustWeight(10.0, 10);

        weatherCondition = new WeatherCondition("Sunny");
        weatherCondition.adjustWeightForWeather(10.0, startNode);
        weatherCondition = new WeatherCondition("Snowy");
        weatherCondition.adjustWeightForWeather(10.0, startNode);
        weatherCondition = new WeatherCondition("Stormy");
        weatherCondition.adjustWeightForWeather(10.0, startNode);

    }
}
