package net.mooctest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ElevatorManagerTest {

	static PrintStream originOutput = System.out;
	static ByteArrayOutputStream output = new ByteArrayOutputStream();

	@BeforeClass
	public static void setUp() throws Exception {
	    System.setOut(new PrintStream(output));
	}

	@AfterClass
	public static void after() throws Exception {
	    System.setOut(originOutput);
	    String str = output.toString();
	    str = str.replaceAll("\n", "1");
	    str = str.replaceAll("\\s", "");
	    str = str.replaceAll("\\p{C}", "");
	    str = str.replaceAll("[^a-zA-Z0-9]", "");
	    System.out.println(str.length());
	    assertEquals(str.length(), 1496, 30.00);
	}


	@Test(timeout = 4000)
	public void test1() throws NoSuchFieldException, IllegalAccessException {
		AnalyticsEngine analyticsEngine = AnalyticsEngine.getInstance();
		AnalyticsEngine analyticsEngine1 = AnalyticsEngine.getInstance();
		analyticsEngine.updateFloorPassengerCount(1, 10);
		analyticsEngine.updateFloorPassengerCount(2, 20);
		System.out.println(analyticsEngine.isPeakHours());
		analyticsEngine.updateFloorPassengerCount(6, 60);
		System.out.println(analyticsEngine.isPeakHours());
		System.out.println(analyticsEngine.generatePerformanceReport().getTitle());
		System.out.println(analyticsEngine.generatePerformanceReport().getGeneratedTime());

		SecurityMonitor securityMonitor = SecurityMonitor.getInstance();
		SecurityMonitor securityMonitor1 = SecurityMonitor.getInstance();
		securityMonitor1.onEvent(new EventBus.Event(EventType.EMERGENCY, "emergency"));
		securityMonitor1.onEvent(new EventBus.Event(EventType.CONFIG_UPDATED, "emergency"));
		EventBus eventBus = EventBus.getInstance();

		ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance();
		ThreadPoolManager threadPoolManager1 = ThreadPoolManager.getInstance();

		Elevator elevator1 = new Elevator(1, null);
		Elevator elevator2 = new Elevator(2, null);
		Elevator elevator3 = new Elevator(3, null);
		Elevator elevator4 = new Elevator(4, null);
		Elevator elevator5 = new Elevator(5, null);
		List<Elevator> elevatorList = Arrays.asList(elevator1, elevator2, elevator3, elevator4, elevator5);

		DispatchStrategy strategy = new NearestElevatorStrategy(); // 使用最近电梯策略

		Field field = Scheduler.class.getDeclaredField("instance");
		field.setAccessible(true);
		field.set(null, null);
		Scheduler scheduler = Scheduler.getInstance(elevatorList, 100, strategy);
		Scheduler scheduler1 = Scheduler.getInstance(elevatorList, 100, strategy);

		elevator1 = new Elevator(1, scheduler);
		elevator2 = new Elevator(2, scheduler);
		elevator3 = new Elevator(3, scheduler1);

		threadPoolManager.submitTask(elevator1);
		threadPoolManager.submitTask(elevator2);
		threadPoolManager.submitTask(elevator3);

		PassengerRequest request1 = new PassengerRequest(1, 5, Priority.HIGH, RequestType.STANDARD);
		PassengerRequest request2 = new PassengerRequest(3, 7, Priority.HIGH, RequestType.STANDARD);
		PassengerRequest request3 = new PassengerRequest(1, 3, Priority.HIGH, RequestType.STANDARD);

		scheduler.submitRequest(request1);
		scheduler.submitRequest(request2);
		scheduler.submitRequest(request3);

		MaintenanceManager maintenanceManager = MaintenanceManager.getInstance();
		MaintenanceManager maintenanceManager1 = MaintenanceManager.getInstance();
		eventBus.subscribe(EventType.ELEVATOR_FAULT, maintenanceManager);
		eventBus.publish(new EventBus.Event(EventType.ELEVATOR_FAULT, elevator1));

		SystemConfig systemConfig = SystemConfig.getInstance();
		System.out.println(systemConfig.getFloorCount());
		systemConfig.setElevatorCount(100);
		System.out.println(systemConfig.getFloorCount());
		systemConfig.setElevatorCount(0);
		systemConfig.setMaxLoad(-1);
		systemConfig.setMaxLoad(0);
		systemConfig.setMaxLoad(100);

		systemConfig.setFloorCount(0);
		systemConfig.setFloorCount(100);
		System.out.println(systemConfig.getFloorCount());

		LogManager logManager = LogManager.getInstance();
		logManager.recordSchedulerEvent("1111");
		logManager.recordElevatorEvent(1, "2222");
		System.out.println(logManager.queryLogs("Scheduler", 1, 10).size());
		System.out.println(logManager.queryLogs("Scheduler", -1, -2).size());

		Field field1 = LogManager.class.getDeclaredField("logs");
		field1.setAccessible(true);
		List<LogManager.SystemLog> o = (List<LogManager.SystemLog>) field1.get(logManager);
		System.out.println(logManager.queryLogs("Scheduler", o.get(0).getTimestamp(), o.get(0).getTimestamp() + 10).size());
		System.out.println(logManager.queryLogs("Scheduler", o.get(0).getTimestamp() + 10, o.get(0).getTimestamp() + 10).size());

		Elevator elevator = new Elevator(1, scheduler);



	}



	@Test(timeout = 4000)
	public void test() throws NoSuchFieldException, IllegalAccessException, InterruptedException {
		EnergySavingStrategy energySavingStrategy = new EnergySavingStrategy();
		PredictiveSchedulingStrategy predictiveSchedulingStrategy = new PredictiveSchedulingStrategy();
		HighEfficiencyStrategy highEfficiencyStrategy = new HighEfficiencyStrategy();
		NearestElevatorStrategy nearestElevatorStrategy = new NearestElevatorStrategy();

		Scheduler scheduler = Scheduler.getInstance();
		Elevator elevator1 = new Elevator(1, scheduler);

		List<Elevator> elevatorList = new ArrayList<>();
		elevatorList.add(elevator1);

		Scheduler scheduler1 = new Scheduler(elevatorList, 100, highEfficiencyStrategy);
		ElevatorManager elevatorManager = ElevatorManager.getInstance();
		elevatorManager.registerElevator(elevator1);
		ElevatorManager elevatorManager1 = ElevatorManager.getInstance();

		scheduler.submitRequest(new PassengerRequest(1, 20, Priority.HIGH, RequestType.STANDARD));
		scheduler.submitRequest(new PassengerRequest(21, 2, Priority.HIGH, RequestType.STANDARD));

		scheduler1.submitRequest(new PassengerRequest(1, 20, Priority.HIGH, RequestType.STANDARD));
		Elevator elevator2 = new Elevator(2, scheduler1);
		scheduler1.submitRequest(new PassengerRequest(21, 2, Priority.HIGH, RequestType.STANDARD));

		Elevator elevator3 = new Elevator(3, scheduler);
		scheduler.update(elevator3, new Event(EventType.ELEVATOR_FAULT, 0));
		scheduler.update(elevator3, new Event(EventType.EMERGENCY, 0));
		scheduler.update(elevator3, new Event(EventType.CONFIG_UPDATED, 0));
		scheduler1.setDispatchStrategy(nearestElevatorStrategy);
		scheduler1.submitRequest(new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));
		scheduler1.submitRequest(new PassengerRequest(2, 22, Priority.LOW, RequestType.STANDARD));

		scheduler1.setDispatchStrategy(energySavingStrategy);
		scheduler1.submitRequest(new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));
		scheduler1.submitRequest(new PassengerRequest(2, 22, Priority.LOW, RequestType.STANDARD));

		scheduler1.setDispatchStrategy(predictiveSchedulingStrategy);
		scheduler1.submitRequest(new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));
		scheduler1.submitRequest(new PassengerRequest(2, 22, Priority.LOW, RequestType.STANDARD));

		Field field = Elevator.class.getDeclaredField("status");
		field.setAccessible(true);
		field.set(elevator1, ElevatorStatus.EMERGENCY);
		field.set(elevator2, ElevatorStatus.EMERGENCY);
		field.set(elevator3, ElevatorStatus.EMERGENCY);

		System.out.println(nearestElevatorStrategy.isEligible(elevator1, new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL)));
		field.set(elevator1, ElevatorStatus.MOVING);
		System.out.println(nearestElevatorStrategy.isEligible(elevator1, new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL)));
		System.out.println(nearestElevatorStrategy.isEligible(elevator1, new PassengerRequest(221, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL)));



		highEfficiencyStrategy.selectElevator(elevatorList, new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));
		System.out.println(highEfficiencyStrategy.isCloser(elevator1, elevator2, new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL)));
		elevatorList.add(elevator2);
		energySavingStrategy.selectElevator(elevatorList, new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));

		elevator2.addObserver(scheduler);
		elevator2.notifyObservers(new Event(EventType.ELEVATOR_FAULT, 0));
		elevator2.move();

		Field field1 = Elevator.class.getDeclaredField("direction");
		field1.setAccessible(true);
		field1.set(elevator2, Direction.DOWN);
		elevator2.move();

		Field field2 = Elevator.class.getDeclaredField("currentFloor");
		field2.setAccessible(true);
		field2.set(elevator1, 22);
		try {
			elevator1.loadPassengers();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			elevator1.move();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		NotificationService notificationService = NotificationService.getInstance();
		NotificationService notificationService1 = NotificationService.getInstance();

		scheduler1.executeEmergencyProtocol();
		EventBus eventBus = EventBus.getInstance();
		eventBus.publish(new EventBus.Event(EventType.MAINTENANCE_REQUIRED, "emergency"));


		System.out.println(elevator1.getEnergyConsumption());
		elevator2.loadPassengers();
		elevator2.updateDirection();
		elevator3.updateDirection();
		elevator3.move();
		elevator3.setCurrentFloor(1);
		elevator3.setDirection(Direction.UP);
		elevator3.setStatus(ElevatorStatus.IDLE);
		System.out.println(elevator1.getDestinationSet());
		System.out.println(elevator1.getPassengerList());
		System.out.println(elevator1.getCurrentFloor());
		System.out.println(elevator1.getObservers());
		System.out.println(elevator1.getCurrentLoad());
		System.out.println(elevator1.getMode());
		elevator3.setMode(ElevatorMode.NORMAL);
		elevator3.setEnergyConsumption(100);
		elevator3.getScheduler();
		elevator3.setCurrentLoad(100);
		elevator3.getLock();
		elevator3.getCondition();
		highEfficiencyStrategy.selectElevator(elevatorList, new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));
		System.out.println(highEfficiencyStrategy.isCloser(elevator1, elevator2, new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL)));
		System.out.println(highEfficiencyStrategy.isCloser(elevator1, elevator2, new PassengerRequest(221, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL)));

		elevatorList.add(elevator3);
		highEfficiencyStrategy.selectElevator(elevatorList, new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));
		highEfficiencyStrategy.selectElevator(elevatorList, new PassengerRequest(221, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL));
		System.out.println(highEfficiencyStrategy.isCloser(elevator1, elevator2, new PassengerRequest(1, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL)));
		System.out.println(highEfficiencyStrategy.isCloser(elevator1, elevator2, new PassengerRequest(221, 20, Priority.MEDIUM, RequestType.DESTINATION_CONTROL)));
	}

}
