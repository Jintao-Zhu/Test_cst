package net.mooctest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class BudgetTest {

	@Test
	public void testEverythingInOneMethod() {
		Budget.Item normalized = new Budget.Item(null, -10.0, -5.0, null);
		assertEquals("", normalized.getName());
		assertEquals(0.0, normalized.getCost(), 0.0);
		assertEquals(0.0, normalized.getValue(), 0.0);
		assertEquals("GENERAL", normalized.getCategory());
		Budget.Item negZeroItem = new Budget.Item("negZero", -0.0d, -0.0d, "CAT");
		assertEquals(Double.doubleToLongBits(-0.0d), Double.doubleToLongBits(negZeroItem.getCost()));
		assertEquals(Double.doubleToLongBits(-0.0d), Double.doubleToLongBits(negZeroItem.getValue()));

		Budget budget = new Budget();
		budget.add(null);
		budget.add(new Budget.Item("Laptop", 1000.0, 800.0, "ELECTRONICS"));
		budget.add(new Budget.Item("Desk", 500.0, 200.0, "FURNITURE"));
		assertEquals(1500.0, budget.totalCost(), 0.001);
		assertEquals(1000.0, budget.totalValue(), 0.001);
		assertEquals(750.0, budget.forecastCost(-1.0), 0.001);
		assertEquals(3000.0, budget.forecastCost(2.0), 0.001);
		assertEquals(1800.0, budget.forecastCost(0.2), 0.001);
		assertEquals(1500.0 * (1 - 0.49), budget.forecastCost(-0.49), 0.001);
		assertEquals(1500.0 * (1 + 0.99), budget.forecastCost(0.99), 0.001);
		assertEquals(1500.0 * (1 - 0.5), budget.forecastCost(-0.5), 0.001);
		assertEquals(1500.0 * (1 + 1.0), budget.forecastCost(1.0), 0.001);
		double lowerNextDown = Math.nextDown(-0.5d);
		double lowerNextUp = Math.nextUp(-0.5d);
		assertEquals(1500.0 * (1 - 0.5), budget.forecastCost(lowerNextDown), 0.001);
		assertEquals(1500.0 * (1 + lowerNextUp), budget.forecastCost(lowerNextUp), 0.001);
		double upperNextDown = Math.nextDown(1.0d);
		double upperNextUp = Math.nextUp(1.0d);
		assertEquals(1500.0 * (1 + upperNextDown), budget.forecastCost(upperNextDown), 0.001);
		assertEquals(1500.0 * (1 + 1.0), budget.forecastCost(upperNextUp), 0.001);
		budget.setReserveRatio(-1.0);
		assertEquals(1000.0, budget.requiredReserve(), 0.001);
		budget.setReserveRatio(1.0);
		assertEquals(1000.0, budget.requiredReserve(), 0.001);
		Budget bigBudget = new Budget();
		bigBudget.add(new Budget.Item("Huge", 5000.0, 0.0, "CAT"));
		bigBudget.setReserveRatio(1.0);
		assertEquals(2500.0, bigBudget.requiredReserve(), 0.001);
		Budget reserveEdge = new Budget();
		reserveEdge.add(new Budget.Item("Edge", 2000.0, 0.0, "EDGE"));
		reserveEdge.setReserveRatio(0.5);
		assertEquals(1000.0, reserveEdge.requiredReserve(), 0.001);
		Budget reserveAbove = new Budget();
		reserveAbove.add(new Budget.Item("Above", 4000.0, 0.0, "EDGE"));
		reserveAbove.setReserveRatio(0.4);
		assertEquals(1600.0, reserveAbove.requiredReserve(), 0.001);
		Budget preciseReserve = new Budget();
		preciseReserve.add(new Budget.Item("Precise", 4000.0, 0.0, "EDGE"));
		preciseReserve.setReserveRatio(0.25);
		assertEquals(1000.0, preciseReserve.requiredReserve(), 0.0);
		double ratioJustUnder = Math.nextDown(1000.0) / 4000.0;
		preciseReserve.setReserveRatio(ratioJustUnder);
		assertEquals(1000.0, preciseReserve.requiredReserve(), 0.0);
		List<Budget.Item> copyItems = budget.getItems();
		copyItems.clear();
		assertEquals(2, budget.getItems().size());
		try {
			Field reserveField = Budget.class.getDeclaredField("reserveRatio");
			reserveField.setAccessible(true);
			budget.setReserveRatio(-0.0d);
			assertEquals(Double.doubleToLongBits(-0.0d), Double.doubleToLongBits(reserveField.getDouble(budget)));
			budget.setReserveRatio(0.25);
			assertEquals(Double.doubleToLongBits(0.25d), Double.doubleToLongBits(reserveField.getDouble(budget)));
			budget.setReserveRatio(0.5000001d);
			assertEquals(Double.doubleToLongBits(0.5d), Double.doubleToLongBits(reserveField.getDouble(budget)));
			budget.setReserveRatio(0.499999d);
			assertEquals(Double.doubleToLongBits(0.499999d), Double.doubleToLongBits(reserveField.getDouble(budget)));
		} catch (ReflectiveOperationException e) {
			fail(e.getMessage());
		}

		Budget fractionBudget = new Budget();
		fractionBudget.add(new Budget.Item("Frac", 2.6, 5.0, "X"));
		BudgetOptimizer optimizer = new BudgetOptimizer();
		try {
			optimizer.optimize(null, 10.0);
			fail("Expected DomainException for null budget");
		} catch (DomainException expected) {
			assertEquals("budget null", expected.getMessage());
		}
		BudgetOptimizer.Selection zeroSel = optimizer.optimize(fractionBudget, -5.0);
		assertTrue(zeroSel.getItems().isEmpty());
		Budget zeroValueBudget = new Budget();
		zeroValueBudget.add(new Budget.Item("Zero", 1.0, 0.0, "Z"));
		assertTrue(optimizer.optimize(zeroValueBudget, 1.0).getItems().isEmpty());
		fractionBudget.add(new Budget.Item("Big", 100.0, 10.0, "Y"));
		BudgetOptimizer.Selection selection = optimizer.optimize(fractionBudget, 3.0);
		assertEquals(1, selection.getItems().size());
		assertEquals(2.6, selection.getTotalCost(), 0.001);
		assertEquals(5.0, selection.getTotalValue(), 0.001);
		Budget optimizerBudget = new Budget();
		optimizerBudget.add(new Budget.Item("A", 2.0, 4.0, "OPT"));
		optimizerBudget.add(new Budget.Item("B", 2.0, 3.0, "OPT"));
		BudgetOptimizer.Selection optimized = optimizer.optimize(optimizerBudget, 2.0);
		assertEquals(1, optimized.getItems().size());
		assertEquals("A", optimized.getItems().get(0).getName());
		BudgetOptimizer.Selection fullSelection = optimizer.optimize(budget, 1500.0);
		assertEquals(2, fullSelection.getItems().size());
		assertEquals("Laptop", fullSelection.getItems().get(0).getName());
		assertEquals("Desk", fullSelection.getItems().get(1).getName());
		assertEquals(1500.0, fullSelection.getTotalCost(), 0.001);
		fractionBudget.add(new Budget.Item("Tiny", 0.5, 1.0, "OPT"));
		BudgetOptimizer.Selection tinySelection = optimizer.optimize(fractionBudget, 0.5);
		assertEquals(1, tinySelection.getItems().size());
		assertEquals(0.5, tinySelection.getTotalCost(), 0.0001);
		assertEquals("Tiny", tinySelection.getItems().get(0).getName());
		assertTrue(optimizer.optimize(fractionBudget, 0.49).getItems().isEmpty());

		long id = IdGenerator.nextId();
		assertTrue(id > 0);
		String idStr = IdGenerator.nextIdStr();
		assertFalse(idStr.isEmpty());
		long parsed = IdGenerator.fromString(idStr);
		assertTrue(parsed > 0);
		String manualId = Long.toUnsignedString(987654321L);
		assertEquals(987654321L, IdGenerator.fromString(manualId));
		try {
			IdGenerator.fromString(null);
			fail("Expected DomainException for null id string");
		} catch (DomainException expected) {
			assertEquals("id string invalid", expected.getMessage());
		}
		try {
			IdGenerator.fromString("");
			fail("Expected DomainException for empty id string");
		} catch (DomainException expected) {
			assertEquals("id string invalid", expected.getMessage());
		}
		try {
			IdGenerator.fromString("0");
			fail("Expected DomainException for zero id");
		} catch (DomainException expected) {
			assertEquals("id zero invalid", expected.getMessage());
		}
		try {
			IdGenerator.fromString("abc");
			fail("Expected DomainException for malformed id");
		} catch (DomainException expected) {
			assertEquals("id parse failed", expected.getMessage());
			assertTrue(expected.getCause() instanceof NumberFormatException);
		}
		try {
			Field counterField = IdGenerator.class.getDeclaredField("COUNTER");
			counterField.setAccessible(true);
			AtomicLong counter = (AtomicLong) counterField.get(null);
			long original = counter.get();
			long beforeTs = System.currentTimeMillis();
			counter.set(Long.MIN_VALUE);
			long forced = IdGenerator.nextId();
			long afterTs = System.currentTimeMillis();
			long usedCounter = counter.get();
			boolean matchedNegative = false;
			for (long ts = beforeTs; ts <= afterTs && !matchedNegative; ts++) {
				long raw = (ts << 20) ^ usedCounter;
				if (Math.abs(raw) == forced) {
					matchedNegative = raw < 0;
				}
			}
			assertTrue(matchedNegative);
			counter.set(original);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Task baseTask = new Task(null, -5, null);
		assertEquals("", baseTask.getName());
		assertEquals(0, baseTask.getDuration());
		assertEquals(Task.Priority.MEDIUM, baseTask.getPriority());
		baseTask.setDuration(-2);
		assertEquals(0, baseTask.getDuration());
		baseTask.setDuration(4);
		baseTask.setName("Base");
		baseTask.setPriority(Task.Priority.CRITICAL);
		assertEquals("Base", baseTask.getName());
		assertEquals(Task.Priority.CRITICAL, baseTask.getPriority());
		baseTask.setPriority(null);
		assertEquals(Task.Priority.MEDIUM, baseTask.getPriority());
		baseTask.setName(null);
		try {
			Field taskIdField = Task.class.getDeclaredField("id");
			taskIdField.setAccessible(true);
			assertEquals(taskIdField.getLong(baseTask), baseTask.getId());
		} catch (ReflectiveOperationException e) {
			fail(e.getMessage());
		}

		Task dependent = new Task("Dependent", 6, Task.Priority.HIGH);
		assertTrue(dependent.addDependency(baseTask));
		assertFalse(dependent.addDependency(baseTask));
		assertFalse(dependent.addDependency(dependent));
		assertFalse(dependent.addDependency(null));
		assertTrue(dependent.dependsOn(baseTask));
		assertFalse(dependent.dependsOn(null));
		Task tail = new Task("Tail", 3, Task.Priority.MEDIUM);
		assertTrue(tail.addDependency(dependent));
		Set<Task> depsCopy = dependent.getDependencies();
		depsCopy.clear();
		assertEquals(1, dependent.getDependencies().size());
		baseTask.requireSkill("AI", 12);
		baseTask.requireSkill("AI", 5);
		baseTask.requireSkill("Data", -1);
		baseTask.requireSkill("", 5);
		baseTask.requireSkill(null, 5);
		assertEquals(10, baseTask.getRequiredSkills().get("AI").intValue());
		assertEquals(0, baseTask.getRequiredSkills().get("Data").intValue());
		Map<String, Integer> skillsCopy = baseTask.getRequiredSkills();
		skillsCopy.clear();
		assertEquals(2, baseTask.getRequiredSkills().size());
		Task skillBounds = new Task("SkillBounds", 1, Task.Priority.MEDIUM);
		skillBounds.requireSkill("ExactZero", 0);
		assertEquals(0, skillBounds.getRequiredSkills().get("ExactZero").intValue());
		skillBounds.requireSkill("ExactMax", 10);
		skillBounds.requireSkill("ExactMax", 7);
		assertEquals(10, skillBounds.getRequiredSkills().get("ExactMax").intValue());

		baseTask.setSchedule(-1, -1, -1, -1);
		assertEquals(0, baseTask.getEst());
		assertEquals(0, baseTask.getEft());
		assertEquals(0, baseTask.getLst());
		assertEquals(0, baseTask.getLft());
		baseTask.setSchedule(2, 5, 10, 12);
		assertEquals(2, baseTask.getEst());
		assertEquals(5, baseTask.getEft());
		assertEquals(10, baseTask.getLst());
		assertEquals(12, baseTask.getLft());
		assertEquals(8, baseTask.slack());

		baseTask.start();
		baseTask.start();
		assertEquals(Task.Status.IN_PROGRESS, baseTask.getStatus());
		baseTask.cancel();
		assertEquals(Task.Status.CANCELLED, baseTask.getStatus());
		baseTask.start();
		assertEquals(Task.Status.CANCELLED, baseTask.getStatus());
		baseTask.complete();
		assertEquals(Task.Status.DONE, baseTask.getStatus());
		baseTask.updateProgress(-0.1);
		assertEquals(0.0, baseTask.getProgress(), 0.0);
		baseTask.updateProgress(2.0);
		assertEquals(1.0, baseTask.getProgress(), 0.0);
		baseTask.updateProgress(0.4);
		assertEquals(0.4, baseTask.getProgress(), 0.0);
		baseTask.updateProgress(-0.0d);
		assertEquals(Double.doubleToLongBits(-0.0d), Double.doubleToLongBits(baseTask.getProgress()));
		baseTask.updateProgress(Double.POSITIVE_INFINITY);
		assertEquals(1.0, baseTask.getProgress(), 0.0);
		baseTask.assignTo(42L);
		assertEquals(Long.valueOf(42L), baseTask.getAssignedResearcherId());

		List<Task> taskChain = Arrays.asList(baseTask, dependent, tail);
		List<Task> order = GraphUtils.topologicalSort(taskChain);
		assertEquals(3, order.size());
		assertEquals(tail, order.get(0));
		assertEquals(dependent, order.get(1));
		assertEquals(baseTask, order.get(2));
		assertFalse(GraphUtils.hasCycle(taskChain));
		assertEquals(6, GraphUtils.longestPathDuration(taskChain));
		Task alternate = new Task("Alternate", 2, Task.Priority.MEDIUM);
		assertTrue(alternate.addDependency(baseTask));
		List<Task> extended = Arrays.asList(baseTask, dependent, tail, alternate);
		List<Task> extendedOrder = GraphUtils.topologicalSort(extended);
		assertEquals(4, extendedOrder.size());
		assertTrue(extendedOrder.contains(alternate));
		Scheduler scheduler = new Scheduler();
		scheduler.schedule(taskChain);
		assertEquals(0, baseTask.getEst());
		assertEquals(4, baseTask.getEft());
		assertEquals(2, baseTask.getLst());
		assertEquals(6, baseTask.getLft());
		assertEquals(0, dependent.getEst());
		assertEquals(6, dependent.getEft());
		assertEquals(0, dependent.getLst());
		assertEquals(6, dependent.getLft());
		assertEquals(0, tail.getEst());
		assertEquals(3, tail.getEft());
		assertEquals(0, tail.getLst());
		assertEquals(3, tail.getLft());
		Task cycleA = new Task("cycleA", 1, Task.Priority.LOW);
		Task cycleB = new Task("cycleB", 1, Task.Priority.LOW);
		assertTrue(cycleA.addDependency(cycleB));
		assertTrue(cycleB.addDependency(cycleA));
		List<Task> cycleList = Arrays.asList(cycleA, cycleB);
		assertTrue(GraphUtils.hasCycle(cycleList));
		try {
			GraphUtils.topologicalSort(cycleList);
			fail("Expected DomainException for cyclic graph");
		} catch (DomainException expected) {
			assertEquals("cycle detected", expected.getMessage());
		}

		Researcher res = new Researcher(null, -1);
		assertEquals("", res.getName());
		assertEquals(0, res.getCapacity());
		try {
			Field researcherId = Researcher.class.getDeclaredField("id");
			researcherId.setAccessible(true);
			assertEquals(researcherId.getLong(res), res.getId());
		} catch (ReflectiveOperationException e) {
			fail(e.getMessage());
		}
		res.addSkill(null, 3);
		res.addSkill("", 3);
		res.addSkill("AI", 11);
		res.addSkill("AI", 2);
		res.addSkill("Stats", -3);
		assertEquals(2, res.getSkillLevel("AI"));
		assertEquals(0, res.getSkillLevel("Stats"));
		assertEquals(0, res.getSkillLevel("Unknown"));
		assertEquals(0, res.getSkillLevel(null));
//		assertTrue(res.hasSkill("AI", 5));
		assertFalse(res.hasSkill("AI", 11));
		assertTrue(res.hasSkill("AI", -5));
		assertTrue(res.hasSkill("Unknown", -3));
		assertFalse(res.canAssign(null));
		assertFalse(res.assignTask(baseTask));
		assertFalse(res.allocateHours(0));
		assertFalse(res.allocateHours(1));
		res.releaseHours(-2);
		res.releaseHours(50);
		assertEquals(40, res.getCapacity());
		res.updateRating(120.0);
		assertTrue(res.getRating() <= 100.0);
		res.updateRating(-10.0);
		assertTrue(res.getRating() >= 0.0);
		assertFalse(res.completeTask(null, 50.0));
		Task skillTask = new Task("SkillTask", 5, Task.Priority.MEDIUM);
		assertTrue(res.canAssign(skillTask));
		assertTrue(res.assignTask(skillTask));
		assertEquals(35, res.getCapacity());
		try {
			Field assignedCount = Researcher.class.getDeclaredField("assignedCount");
			assignedCount.setAccessible(true);
			assertEquals(1, assignedCount.getInt(res));
		} catch (ReflectiveOperationException e) {
			fail(e.getMessage());
		}
		assertTrue(res.completeTask(skillTask, 80.0));
		assertEquals(40, res.getCapacity());
		assertTrue(res.getRating() > 0.0);
		Set<String> skills = res.getSkills();
		skills.clear();
		assertFalse(res.getSkills().isEmpty());
		res.setName(null);
		assertEquals("", res.getName());
		res.setName("null");
		assertEquals("null", res.getName());
		Researcher capacityEdge = new Researcher("EdgeCase", 5);
		assertTrue(capacityEdge.allocateHours(5));
		assertEquals(0, capacityEdge.getCapacity());
		capacityEdge.releaseHours(0);
		assertEquals(0, capacityEdge.getCapacity());
		capacityEdge.releaseHours(3);
		assertEquals(3, capacityEdge.getCapacity());
		capacityEdge.addSkill("ZeroLevel", 0);
		assertEquals(0, capacityEdge.getSkillLevel("ZeroLevel"));
		capacityEdge.addSkill("MaxLevel", 10);
		assertEquals(10, capacityEdge.getSkillLevel("MaxLevel"));
		Researcher ratingEdge = new Researcher("Edges", 3);
		ratingEdge.updateRating(0.0);
		assertEquals(0.0, ratingEdge.getRating(), 0.0);
		ratingEdge.updateRating(100.0);
		assertEquals(30.0, ratingEdge.getRating(), 0.0001);
		Researcher ratingRes = new Researcher("Rated", 10);
		ratingRes.updateRating(50.0);
		assertEquals(15.0, ratingRes.getRating(), 0.0001);
		ratingRes.updateRating(20.0);
		assertEquals(16.5, ratingRes.getRating(), 0.0001);
		Task ratingTask = new Task("Rating", 4, Task.Priority.MEDIUM);
		assertTrue(ratingRes.assignTask(ratingTask));
		double beforeComplete = ratingRes.getRating();
		assertTrue(ratingRes.completeTask(ratingTask, 80.0));
		assertEquals(10, ratingRes.getCapacity());
		double expectedRating = beforeComplete * 0.7 + 80.0 * 0.3;
		assertEquals(expectedRating, ratingRes.getRating(), 0.0001);

		Researcher rMain = new Researcher("Main", 10);
		rMain.updateRating(90.0);
		Researcher rAlt = new Researcher("Alt", 5);
		rAlt.updateRating(70.0);
		Researcher rTiny = new Researcher("Tiny", 2);
		Researcher rLow = new Researcher("Low", 8);
		rLow.updateRating(10.0);
		Task critical = new Task("Critical", 8, Task.Priority.CRITICAL);
		Task highTask = new Task("High", 4, Task.Priority.HIGH);
		Task lowTask = new Task("Low", 20, Task.Priority.LOW);
		MatchingEngine engine = new MatchingEngine();
		assertTrue(engine.match(null, null).isEmpty());
		assertTrue(engine.match(null, new ArrayList<>(Arrays.asList(critical))).isEmpty());
		assertTrue(engine.match(new ArrayList<>(Arrays.asList(rMain)), null).isEmpty());
		List<MatchingEngine.Assignment> assignments = engine.match(new ArrayList<>(Arrays.asList(rLow, rMain, rAlt, rTiny)), new ArrayList<>(Arrays.asList(lowTask, highTask, critical)));
		assertEquals(2, assignments.size());
		MatchingEngine.Assignment firstAssignment = assignments.get(0);
		MatchingEngine.Assignment secondAssignment = assignments.get(1);
		assertEquals(Task.Priority.HIGH, firstAssignment.getTask().getPriority());
		assertEquals(rMain.getId(), firstAssignment.getResearcher().getId());
		assertNotNull(critical.getAssignedResearcherId());
		assertEquals(Task.Priority.CRITICAL, secondAssignment.getTask().getPriority());
		assertNotNull(highTask.getAssignedResearcherId());
		assertNull(lowTask.getAssignedResearcherId());
		assertEquals(6, rMain.getCapacity());
		assertEquals(5, rAlt.getCapacity());
		int restoredMainCapacity = rMain.getCapacity() + critical.getDuration();
		double firstScore = firstAssignment.getScore();
		double expectedFirstScore = Math.min(restoredMainCapacity, critical.getDuration()) * 0.1 + rMain.getRating() * 0.05;
		assertEquals(expectedFirstScore, firstScore, 1);
		assertTrue(firstScore > engine.score(rLow, critical));
		int restoredAltCapacity = rAlt.getCapacity() + highTask.getDuration();
		double secondScore = secondAssignment.getScore();
		double expectedSecondScore = Math.min(restoredAltCapacity, highTask.getDuration()) * 0.1 + rAlt.getRating() * 0.05;
		assertEquals(expectedSecondScore, secondScore, 1);
		assertTrue(secondScore >= engine.score(rLow, highTask));
		Researcher tieA = new Researcher("TieA", 4);
		Researcher tieB = new Researcher("TieB", 4);
		Task tieTask = new Task("Tie", 4, Task.Priority.MEDIUM);
		List<MatchingEngine.Assignment> tieAssignments = engine.match(Arrays.asList(tieA, tieB), Arrays.asList(tieTask));
		assertEquals(1, tieAssignments.size());
		assertEquals(tieA.getId(), tieAssignments.get(0).getResearcher().getId());
		for (MatchingEngine.Assignment a : assignments) {
			a.getResearcher().completeTask(a.getTask(), 95.0);
		}
		Researcher single = new Researcher("Single", 5);
		single.updateRating(50.0);
		Task lowPref = new Task("LowPref", 1, Task.Priority.LOW);
		Task criticalPref = new Task("CriticalPref", 5, Task.Priority.CRITICAL);
		List<MatchingEngine.Assignment> prioritized = engine.match(Arrays.asList(single), Arrays.asList(criticalPref, lowPref));
		assertEquals(1, prioritized.size());
		assertEquals(lowPref.getId(), prioritized.get(0).getTask().getId());
		assertEquals(Long.valueOf(single.getId()), lowPref.getAssignedResearcherId());
		assertNull(criticalPref.getAssignedResearcherId());

		Resource resource = new Resource(null, null);
		assertEquals("", resource.getName());
		assertNotNull(resource.getId());
		try {
			Field resourceId = Resource.class.getDeclaredField("id");
			resourceId.setAccessible(true);
			assertEquals(resourceId.getLong(resource), resource.getId());
		} catch (ReflectiveOperationException e) {
			fail(e.getMessage());
		}
		assertEquals("GENERIC", resource.getType());
		resource.setName("Lab");
		resource.setType(null);
		assertEquals("Lab", resource.getName());
		assertEquals("GENERIC", resource.getType());
		resource.setName(null);
		resource.setType("LabType");
		resource.setType(null);
		assertEquals("", resource.getName());
		assertEquals("GENERIC", resource.getType());
		Resource typedResource = new Resource("Server", "COMPUTE");
		assertEquals("Server", typedResource.getName());
		assertEquals("COMPUTE", typedResource.getType());
		typedResource.setName("Core");
		assertEquals("Core", typedResource.getName());
		typedResource.setType("NETWORK");
		assertEquals("NETWORK", typedResource.getType());
		typedResource.setName(null);
		typedResource.setType(null);
		assertEquals("", typedResource.getName());
		assertEquals("GENERIC", typedResource.getType());
		LocalDateTime now = LocalDateTime.now();
		assertFalse(resource.isAvailable(null, now));
		assertFalse(resource.isAvailable(now, null));
		assertFalse(resource.isAvailable(now, now));
		LocalDateTime later = now.plusHours(2);
		assertTrue(resource.isAvailable(now, later));
		assertTrue(resource.book(now, later));
		LocalDateTime postWindowStart = later.plusMinutes(1);
		LocalDateTime postWindowEnd = later.plusMinutes(30);
		assertTrue(resource.isAvailable(postWindowStart, postWindowEnd));
		LocalDateTime overlapStart = now.plusMinutes(10);
		assertFalse(resource.isAvailable(overlapStart, overlapStart.plusHours(1)));
		assertFalse(resource.book(now.plusMinutes(30), later.plusHours(1)));
		assertFalse(resource.isAvailable(now.plusMinutes(30), later.plusHours(1)));
		resource.cancel(null);
		resource.cancel(now);
		assertTrue(resource.isAvailable(now.plusHours(3), now.plusHours(4)));
		assertTrue(resource.book(now.plusHours(3), now.plusHours(4)));
		assertEquals(1, resource.listBookings().size());
		assertFalse(resource.conflicts(now, later));

		Risk riskHigh = new Risk("High", "Tech", 0.9, 0.9);
		Risk riskLow = new Risk(null, "Tech", 0.1, 0.1);
		Risk riskZero = new Risk("Zero", null, 0.0, 0.0);
		Risk riskTieA = new Risk("Alpha", "Ops", 0.6, 0.6);
		Risk riskTieB = new Risk("Beta", "Ops", 0.6, 0.6);
		Risk riskImpactA = new Risk("ImpactA", "Ops", 1.0, 0.9);
		Risk riskImpactB = new Risk("ImpactB", "Ops", 1.0, 0.7);
		Risk riskBoundaryHigh = new Risk("BoundaryHigh", "Ops", 0.5, 1.0);
		Risk riskBoundaryMedium = new Risk("BoundaryMedium", "Ops", 0.5, 0.5);
		assertEquals("", riskLow.getName());
		assertEquals("Zero", riskZero.getName());
		assertEquals(3, riskHigh.priority());
		assertEquals(1, riskLow.priority());
		assertEquals(0, riskZero.priority());
		assertEquals(3, riskBoundaryHigh.priority());
		assertEquals(2, riskBoundaryMedium.priority());
		assertTrue(riskHigh.compareTo(riskLow) < 0);
		assertTrue(riskTieA.compareTo(riskTieB) < 0);
		assertTrue(riskImpactA.compareTo(riskImpactB) < 0);
		assertEquals("GENERAL", riskZero.getCategory());
		assertEquals(1.0, riskHigh.clamp(2.5), 0.0);
		assertEquals(0.0, riskHigh.clamp(-3.0), 0.0);
		assertEquals(Double.doubleToLongBits(-0.0d), Double.doubleToLongBits(riskHigh.clamp(-0.0d)));
		assertEquals(0.4, riskHigh.clamp(0.4), 0.0);
		assertEquals(0.9999, riskHigh.clamp(0.9999), 0.0);
		assertEquals(1.0, riskHigh.clamp(1.0), 0.0);

		RiskAnalyzer analyzer = new RiskAnalyzer();
		double rndVal = analyzer.rnd();
		assertTrue(rndVal >= 0.0 && rndVal < 1.0);
		try {
			Field seedField = RiskAnalyzer.class.getDeclaredField("seed");
			seedField.setAccessible(true);
			long customSeed = 123456789L;
			seedField.setLong(analyzer, customSeed);
			long tempSeed = customSeed;
			tempSeed ^= (tempSeed << 13);
			tempSeed ^= (tempSeed >>> 7);
			tempSeed ^= (tempSeed << 17);
			long expectedMasked = tempSeed & ((1L << 53) - 1);
			double expectedRnd = expectedMasked / (double) (1L << 53);
			double deterministicRnd = analyzer.rnd();
			assertEquals(expectedRnd, deterministicRnd, 0.0);
			assertEquals(tempSeed, seedField.getLong(analyzer));
			List<Risk> deterministicRisks = Arrays.asList(new Risk("Det1", "Cat", 0.6, 3.0), new Risk("Det2", "Cat", 0.2, 5.0));
			int iterations = 5;
			tempSeed = customSeed;
			double[] scenarios = new double[iterations];
			for (int i = 0; i < iterations; i++) {
				double scenario = 0;
				for (Risk r : deterministicRisks) {
					tempSeed ^= (tempSeed << 13);
					tempSeed ^= (tempSeed >>> 7);
					tempSeed ^= (tempSeed << 17);
					long mask = tempSeed & ((1L << 53) - 1);
					double draw = mask / (double) (1L << 53);
					if (draw < r.getProbability()) {
						scenario += r.getImpact();
					}
				}
				scenarios[i] = scenario;
			}
			seedField.setLong(analyzer, customSeed);
			RiskAnalyzer.SimulationResult deterministic = analyzer.simulate(deterministicRisks, iterations);
			double[] sorted = Arrays.copyOf(scenarios, scenarios.length);
			Arrays.sort(sorted);
			double expectedMean = Arrays.stream(scenarios).sum() / iterations;
			double expectedP90 = sorted[Math.min(sorted.length - 1, (int) Math.floor(sorted.length * 0.9))];
			double expectedWorst = sorted[sorted.length - 1];
			assertEquals(expectedMean, deterministic.getMeanImpact(), 0.0001);
			assertEquals(expectedP90, deterministic.getP90Impact(), 0.0001);
			assertEquals(expectedWorst, deterministic.getWorstCaseImpact(), 0.0001);
		} catch (ReflectiveOperationException e) {
			fail(e.getMessage());
		}
		RiskAnalyzer.SimulationResult zeroRisks = analyzer.simulate(null, 10);
		assertEquals(0.0, zeroRisks.getMeanImpact(), 0.0);
		RiskAnalyzer.SimulationResult emptyRisks = analyzer.simulate(Collections.emptyList(), 10);
		assertEquals(0.0, emptyRisks.getWorstCaseImpact(), 0.0);
		RiskAnalyzer.SimulationResult noIterations = analyzer.simulate(Arrays.asList(riskHigh), 0);
		assertEquals(0.0, noIterations.getP90Impact(), 0.0);
		RiskAnalyzer.SimulationResult simulated = analyzer.simulate(Arrays.asList(riskHigh, riskLow), 10);
		assertTrue(simulated.getWorstCaseImpact() >= simulated.getP90Impact());
		RiskAnalyzer deterministicAnalyzer = new RiskAnalyzer() {
			private int idx;
			private final double[] draws = {0.0, 0.9};
			@Override
			public double rnd() {
				double value = draws[idx % draws.length];
				idx++;
				return value;
			}
		};
		Risk deterministicRisk = new Risk("Deterministic", "Cat", 0.7, 10.0);
		RiskAnalyzer.SimulationResult forced = deterministicAnalyzer.simulate(Arrays.asList(deterministicRisk), 2);
		assertEquals(0.5, forced.getMeanImpact(), 0.0001);
		assertEquals(1.0, forced.getP90Impact(), 0.0001);
		assertEquals(1.0, forced.getWorstCaseImpact(), 0.0001);
		RiskAnalyzer equalityAnalyzer = new RiskAnalyzer() {
			@Override
			public double rnd() {
				return 0.5;
			}
		};
		Risk equalityRisk = new Risk("Equality", "Cat", 0.5, 1.0);
		RiskAnalyzer.SimulationResult equality = equalityAnalyzer.simulate(Arrays.asList(equalityRisk), 1);
		assertEquals(0.0, equality.getMeanImpact(), 0.0);
		assertEquals(0.0, equality.getP90Impact(), 0.0);
		assertEquals(0.0, equality.getWorstCaseImpact(), 0.0);
		final int percentileIterations = 20;
		RiskAnalyzer percentileAnalyzer = new RiskAnalyzer() {
			private final double[] draws;
			private int idx;
			{
				draws = new double[percentileIterations];
				draws[0] = 0.0;
				Arrays.fill(draws, 1, draws.length, 0.95);
			}
			@Override
			public double rnd() {
				double value = draws[idx % draws.length];
				idx++;
				return value;
			}
		};
		Risk rareRisk = new Risk("Rare", "Cat", 0.1, 10.0);
		RiskAnalyzer.SimulationResult percentileResult = percentileAnalyzer.simulate(Arrays.asList(rareRisk), percentileIterations);
		assertEquals(0.05, percentileResult.getMeanImpact(), 0.0001);
		assertEquals(0.0, percentileResult.getP90Impact(), 0.0);
		assertEquals(1.0, percentileResult.getWorstCaseImpact(), 0.0);

		try {
			new Project(null);
			new Project("null");
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		Project project = new Project(null);
		assertEquals("", project.getName());
		project.setName(null);
		assertNull(project.addTask(null));
		assertNull(project.addResearcher(null));
		assertNotNull(project.getId());
		try {
			Field projectIdField = Project.class.getDeclaredField("id");
			projectIdField.setAccessible(true);
			assertEquals(projectIdField.getLong(project), project.getId());
		} catch (ReflectiveOperationException e) {
			fail(e.getMessage());
		}
		assertEquals("", project.getName());
		project.setName("ProjectX");
		assertEquals("ProjectX", project.getName());
		project.setBudget(bigBudget);
		assertEquals(bigBudget.totalCost(), project.getBudget().totalCost(), 0.001);
		project.setBudget(null);
		assertEquals(bigBudget.totalCost(), project.getBudget().totalCost(), 0.001);
		project.addTask(baseTask);
		project.addTask(dependent);
		project.addTask(tail);
		project.addResearcher(rMain);
		project.addResearcher(rAlt);
		project.addResearcher(rTiny);
		project.addRisk(riskHigh);
		project.addRisk(null);
		project.addRisk(riskLow);
		Project returnsProject = new Project("Returns");
		Task returnTask = new Task("Return", 1, Task.Priority.LOW);
		Researcher returnResearcher = new Researcher("Returner", 5);
		assertSame(returnTask, returnsProject.addTask(returnTask));
		assertSame(returnResearcher, returnsProject.addResearcher(returnResearcher));
		assertEquals(baseTask, project.getTask(baseTask.getId()));
		assertEquals(rMain, project.getResearcher(rMain.getId()));
		assertEquals(3, project.getTasks().size());
		assertEquals(3, project.getResearchers().size());
		assertEquals(2, project.getRisks().size());
		Map<Task.Status, Long> counts = project.statusCounts();
		long totalStatuses = 0;
		for (Task.Status status : Task.Status.values()) {
			totalStatuses += counts.get(status);
		}
		assertEquals(3, totalStatuses);
		assertEquals(6, project.criticalPathDuration());
		List<MatchingEngine.Assignment> planned = project.planAssignments();
		assertEquals(2, planned.size());
		assertEquals(baseTask, planned.get(0).getTask());
		assertEquals(rMain.getId(), planned.get(0).getResearcher().getId());
		assertEquals(tail, planned.get(1).getTask());
		assertEquals(rAlt.getId(), planned.get(1).getResearcher().getId());
		RiskAnalyzer.SimulationResult riskSummary = project.analyzeRisk(5);
		assertTrue(riskSummary.getWorstCaseImpact() >= riskSummary.getP90Impact());
		List<Risk> risksCopy = project.getRisks();
		risksCopy.clear();
		assertEquals(2, project.getRisks().size());

		ReportGenerator generator = new ReportGenerator();
		assertEquals("", generator.generate(null));
		String report = generator.generate(project);
		assertTrue(report.contains("Project:ProjectX"));
		assertTrue(report.contains("Status DONE"));
		assertTrue(report.contains("CriticalPath:6"));
		assertTrue(report.contains("BudgetCost:5000"));
		assertTrue(report.contains("RiskMean:"));

		ListIterator<Task> iterator = order.listIterator();
		while (iterator.hasNext()) {
			assertNotNull(iterator.next());
		}
	}

}
