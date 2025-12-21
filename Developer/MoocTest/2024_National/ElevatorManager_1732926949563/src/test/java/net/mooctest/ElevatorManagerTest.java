package net.mooctest;

import static org.junit.Assert.*;

/*
 * 测试代码基于JUnit 4，若eclipse提示未找到Junit 5的测试用例，请在Run Configurations中设置Test Runner为Junit 4。请不要使用Junit 5
 * 语法编写测试代码
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class ElevatorManagerTest {

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
		assertEquals(1385, allOutput.length(), 2000);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	@Test(timeout = 4000)
	public void testScheduler() {
		ElevatorManager elevatorManager = ElevatorManager.getInstance();
		ElevatorManager elevatorManager1 = ElevatorManager.getInstance();
		DispatchStrategy strategy = new EnergySavingStrategy();
		Elevator elevator = new Elevator(1, null);
		elevatorManager.registerElevator(elevator);
		System.out.println(elevatorManager.getElevatorById(1));
		System.out.println(elevatorManager.getAllElevators());
		List<Elevator> elevators = new ArrayList<>();
		elevators.add(elevator);
		Scheduler scheduler = new Scheduler(elevators, 10, strategy);
		scheduler.submitRequest(new PassengerRequest(1, 2, Priority.HIGH, RequestType.STANDARD));
		scheduler.submitRequest(new PassengerRequest(1, 2, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));
		scheduler.getRequestsAtFloor(10, Direction.UP);
	}

	@Test(timeout = 4000)
	public void testScheduler2() {
		Scheduler instance = Scheduler.getInstance();
		Scheduler instance1 = Scheduler.getInstance();
		System.out.println(instance);
		instance.dispatchElevator(null);
		instance.update(null, new Event(EventType.EMERGENCY, 1));
		instance.redistributeRequests(new Elevator(1, null));
		instance.update(null, new Event(EventType.MAINTENANCE_REQUIRED, 2));
		instance.setDispatchStrategy(new NearestElevatorStrategy());

		Elevator elevator = new Elevator(1, instance);
	}

	@Test
	public void testEventBus() {
		EventBus eventBus = EventBus.getInstance();
		EventBus eventBus1 = EventBus.getInstance();
		EventBus.Event event1 = new EventBus.Event(EventType.ELEVATOR_FAULT, 2);
		eventBus.publish(event1);
		eventBus.subscribe(EventType.ELEVATOR_FAULT, new EventBus.EventListener() {
			@Override
			public void onEvent(EventBus.Event event) {
				System.out.println(event);
			}
		});
		eventBus.publish(event1);
		System.out.println(event1.getData());
		System.out.println(event1.getType());
	}

	@Test
	public void testElevator() throws InterruptedException {

		Scheduler scheduler = Scheduler.getInstance(new ArrayList<>(), 10, new NearestElevatorStrategy());
		Scheduler scheduler1 = Scheduler.getInstance(new ArrayList<>(), 10, new NearestElevatorStrategy());

		Elevator elevator = new Elevator(1, scheduler);
		elevator.addDestination(1);
		elevator.setStatus(ElevatorStatus.EMERGENCY);
		System.out.println(elevator.getEnergyConsumption());
		System.out.println(elevator.getId());
		System.out.println(elevator.getDirection());
		System.out.println(elevator.getStatus());
		System.out.println(elevator.getCurrentFloor());
		System.out.println(elevator.getPassengerList());

		elevator.addObserver(scheduler);
		elevator.notifyObservers(new Event(EventType.ELEVATOR_FAULT, 1));
		elevator.move();
		elevator.updateDirection();
		elevator.move();

//		elevator.loadPassengers();
//		elevator.openDoor();
//		elevator.unloadPassengers();

		ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance();
		ThreadPoolManager threadPoolManager1 = ThreadPoolManager.getInstance();


		Thread elevatorThread = new Thread(elevator);
		elevatorThread.start();

		threadPoolManager.submitTask(elevatorThread);
//		threadPoolManager.shutdown();
	}

	@Test
	public void testStrategy() {
		Elevator elevator = new Elevator(1, null);
		Elevator elevator2 = new Elevator(2, null);
		Elevator elevator3 = new Elevator(3, null);
		Elevator elevator4 = new Elevator(4, null);

		List<Elevator> elevators = new ArrayList<>();
		elevator.setStatus(ElevatorStatus.MOVING);
		elevator2.setStatus(ElevatorStatus.EMERGENCY);
		elevator3.setStatus(ElevatorStatus.STOPPED);

		elevators.add(elevator);
		elevators.add(elevator2);
		elevators.add(elevator3);

		Scheduler scheduler = new Scheduler(elevators, 10, new EnergySavingStrategy());
		scheduler.submitRequest(new PassengerRequest(1, 2, Priority.HIGH, RequestType.STANDARD));
		scheduler.submitRequest(new PassengerRequest(1, 2, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));
		scheduler.getRequestsAtFloor(10, Direction.UP);


		PassengerRequest request1 = new PassengerRequest(1, 2, Priority.HIGH, RequestType.STANDARD);
		PassengerRequest request2 = new PassengerRequest(3, 1, Priority.LOW, RequestType.STANDARD);
		PassengerRequest request3 = new PassengerRequest(2, 2, Priority.MEDIUM, RequestType.STANDARD);

		EnergySavingStrategy energySavingStrategy = new EnergySavingStrategy();
		System.out.println(energySavingStrategy.selectElevator(elevators, request1));
		System.out.println(energySavingStrategy.selectElevator(elevators, request2));
		System.out.println(energySavingStrategy.selectElevator(elevators, request3));

		HighEfficiencyStrategy highEfficiencyStrategy = new HighEfficiencyStrategy();
		System.out.println(highEfficiencyStrategy.selectElevator(elevators, request1));
		System.out.println(highEfficiencyStrategy.selectElevator(elevators, request2));
		System.out.println(highEfficiencyStrategy.selectElevator(elevators, request3));

		NearestElevatorStrategy nearestElevatorStrategy = new NearestElevatorStrategy();
		System.out.println(nearestElevatorStrategy.selectElevator(elevators, request1));
		System.out.println(nearestElevatorStrategy.selectElevator(elevators, request2));
		System.out.println(nearestElevatorStrategy.selectElevator(elevators, request3));
		elevators.add(elevator4);
		System.out.println(nearestElevatorStrategy.selectElevator(elevators, new PassengerRequest(2, 2, Priority.MEDIUM, RequestType.STANDARD)));

		PredictiveSchedulingStrategy predictiveSchedulingStrategy = new PredictiveSchedulingStrategy();
		System.out.println(predictiveSchedulingStrategy.selectElevator(elevators, request1));
		System.out.println(predictiveSchedulingStrategy.selectElevator(elevators, request2));
		System.out.println(predictiveSchedulingStrategy.selectElevator(elevators, request3));
	}

	@Test
	public void testLogManager() throws InterruptedException {
		LogManager logManager = LogManager.getInstance();
		LogManager logManager1 = LogManager.getInstance();
		logManager.recordElevatorEvent(1, "event1");
		logManager.recordSchedulerEvent("event2");
		logManager.recordEvent("source1", "massage1");
		System.out.println(logManager.queryLogs("source2", 1, 2));
		System.out.println(logManager.queryLogs("source1", -1, -1));

		LogManager.SystemLog systemLog = new LogManager.SystemLog("source1", "message1", 5);
		System.out.println(systemLog.getSource());
		System.out.println(systemLog.getMessage());
		System.out.println(systemLog.getTimestamp());

		long startTime = System.currentTimeMillis();
		Thread.sleep(10); // 确保时间戳差异
		logManager.recordEvent("TestSource", "Test Message 1");
		Thread.sleep(10);
		long endTime = System.currentTimeMillis();

		logManager.recordEvent("TestSource", "Test Message 2");

		System.out.println(logManager.queryLogs("TestSource", startTime, endTime));
	}

	@Test
	public void testMaintenanceManager() {
		MaintenanceManager maintenanceManager = MaintenanceManager.getInstance();
		MaintenanceManager maintenanceManager1 = MaintenanceManager.getInstance();
		Elevator elevator = new Elevator(1, null);
		EventBus.Event event = new EventBus.Event(EventType.ELEVATOR_FAULT, elevator);
		EventBus.Event event1 = new EventBus.Event(EventType.MAINTENANCE_REQUIRED, elevator);
		maintenanceManager.onEvent(event);
		maintenanceManager.onEvent(event1);

		MaintenanceManager.MaintenanceTask maintenanceTask = new MaintenanceManager.MaintenanceTask(1, 10, "task1");
		System.out.println(maintenanceTask.getElevatorId());
		System.out.println(maintenanceTask.getScheduledTime());
		System.out.println(maintenanceTask.getDescription());

		MaintenanceManager.MaintenanceRecord maintenanceRecord = new MaintenanceManager.MaintenanceRecord(1, 10, "record1");
		System.out.println(maintenanceRecord.getElevatorId());
		System.out.println(maintenanceRecord.getMaintenanceTime());
		System.out.println(maintenanceRecord.getResult());

//		maintenanceManager.processTasks();
		maintenanceManager.performMaintenance(maintenanceTask);
	}

	@Test
	public void testAnalyticsEngine() {
		AnalyticsEngine analyticsEngine = AnalyticsEngine.getInstance();
		AnalyticsEngine analyticsEngine1 = AnalyticsEngine.getInstance();

		ElevatorStatusReport elevatorStatusReport = new ElevatorStatusReport(1, 1, Direction.UP, ElevatorStatus.MOVING, 1, 1, 1);
		System.out.println(elevatorStatusReport.getElevatorId());
		System.out.println(elevatorStatusReport.getCurrentFloor());
		System.out.println(elevatorStatusReport.getDirection());
		System.out.println(elevatorStatusReport.getStatus());
		System.out.println(elevatorStatusReport.getSpeed());
		System.out.println(elevatorStatusReport.getCurrentLoad());
		System.out.println(elevatorStatusReport.getPassengerCount());
		System.out.println(elevatorStatusReport.toString());

		analyticsEngine.processStatusReport(elevatorStatusReport);
		analyticsEngine.updateFloorPassengerCount(1, 3);
		System.out.println(analyticsEngine1.isPeakHours());
		analyticsEngine.updateFloorPassengerCount(1, 100);
		System.out.println(analyticsEngine1.isPeakHours());
		AnalyticsEngine.Report report = analyticsEngine1.generatePerformanceReport();
		System.out.println(report.getTitle());
		System.out.println(report.getGeneratedTime());
		System.out.println(report);

		analyticsEngine.updateFloorPassengerCount(1, 3);

	}

	@Test
	public void testSecurityMonitor() {
		SecurityMonitor securityMonitor = SecurityMonitor.getInstance();
		SecurityMonitor securityMonitor1 = SecurityMonitor.getInstance();
		Elevator elevator = new Elevator(1, null);
		EventBus.Event event = new EventBus.Event(EventType.EMERGENCY, elevator);
		EventBus.Event event1 = new EventBus.Event(EventType.CONFIG_UPDATED, elevator);
		securityMonitor.onEvent(event);
		securityMonitor.onEvent(event1);

		SecurityMonitor.SecurityEvent securityEvent = new SecurityMonitor.SecurityEvent("test", 1, event1);
		System.out.println(securityEvent.getDescription());
		System.out.println(securityEvent.getTimestamp());
		System.out.println(securityEvent.getData());
	}

	@Test
	public void testSystemConfig() {
		SystemConfig systemConfig = SystemConfig.getInstance();
		System.out.println(systemConfig.getElevatorCount());
		System.out.println(systemConfig.getMaxLoad());
		System.out.println(systemConfig.getFloorCount());
		systemConfig.setElevatorCount(10);
		systemConfig.setElevatorCount(-10);
		systemConfig.setMaxLoad(100);
		systemConfig.setMaxLoad(-100);
		systemConfig.setFloorCount(100);
		systemConfig.setFloorCount(-100);
		System.out.println(systemConfig.getElevatorCount());
		System.out.println(systemConfig.getMaxLoad());
		System.out.println(systemConfig.getFloorCount());
	}

	@Test
	public void testElevatorFunctions() throws InterruptedException {
		Scheduler mockScheduler = new Scheduler(new ArrayList<>(), 10, new NearestElevatorStrategy());
		Elevator elevator = new Elevator(1, mockScheduler);
		System.out.println((elevator));
		System.out.println(elevator.getId());
		System.out.println(elevator.getCurrentFloor());
		System.out.println(elevator.getStatus());
		System.out.println(elevator.getDirection());
		System.out.println(elevator.getCurrentLoad());

		elevator.addDestination(3);
		System.out.println(elevator.getDestinationSet().contains(3));
		elevator.updateDirection();
		System.out.println(elevator.getDirection());

		elevator.setCurrentFloor(5);
		elevator.addDestination(2);
		elevator.updateDirection();
		System.out.println(elevator.getDirection());

//		elevator.moveToFirstFloor();
//		System.out.println(elevator.getCurrentFloor());
//		System.out.println(elevator.getStatus());

		elevator.setCurrentFloor(5);
		elevator.handleEmergency();
		System.out.println(elevator.getStatus());
		System.out.println(elevator.getDestinationSet().contains(1));
		System.out.println(elevator.getPassengerList().isEmpty());

		elevator.addDestination(3);
		List<PassengerRequest> pendingRequests = elevator.clearAllRequests();
		System.out.println(elevator.getDestinationSet().isEmpty());
		System.out.println(pendingRequests.isEmpty());

		elevator.setCurrentFloor(1);
		elevator.openDoor();
		System.out.println(elevator.getCurrentLoad());
		System.out.println(elevator.getDestinationSet().contains(3));
		System.out.println(elevator.getDestinationSet().contains(4));

		elevator.setCurrentFloor(3);
		elevator.unloadPassengers();
		System.out.println(elevator.getPassengerList().size());
		System.out.println(elevator.getCurrentLoad());

		double initialEnergy = elevator.getEnergyConsumption();
		elevator.move();
		System.out.println(elevator.getEnergyConsumption());

//		Thread elevatorThread = new Thread(elevator);
//		elevatorThread.start();
//
//		elevator.addDestination(5);
////		Thread.sleep(1000);
//		System.out.println(elevator.getCurrentFloor());
//
//		elevatorThread.interrupt();
//		elevatorThread.join();
	}
}
