package net.mooctest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * 开发者做题说明：
 * 1、该测试类为测试类示例，不要求完全按照该示例类的格式；考生也可自行创建测试类，类名可自行定义
 * 2、所有测试方法放在该顶层类中，不建议再创建内部类
 * 3、不要修改被测代码
 * 4、提交答案时只提交一个测试类.java文件
 * 5、无论最终的类名是什么，都要保证文件名与类名一致（遵循Java语法），如测试类为MementoTest，则提交时的文件名应为MementoTest.java，否则将因不符合语法而判0分！
 * 6、建议尽量避免卡点提交答案
 * 
 * */
public class StudentTest {

	private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private static PrintStream originalOut;

	@BeforeClass
	public static void setUpBeforeClass() {
		originalOut = System.out;
		System.setOut(new PrintStream(outputStream));
	}

	@AfterClass
	public static void tearDownAndVerify() {
		System.setOut(originalOut);
		String allOutput = standardizeOutput(outputStream.toString());
		assertEquals(132, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	@Test
	public void test() {
		final double EPS = 1e-6;

		// Build grading policy shared by services
		Map<GradeComponentType, GradeComponent> policyMap = new EnumMap<>(GradeComponentType.class);
		policyMap.put(GradeComponentType.ASSIGNMENT, new GradeComponent(GradeComponentType.ASSIGNMENT, 0.4));
		policyMap.put(GradeComponentType.FINAL, new GradeComponent(GradeComponentType.FINAL, 0.6));
		GradingPolicy policy = new GradingPolicy(policyMap);

		{
			Map<GradeComponentType, GradeComponent> invalidPolicy = new EnumMap<>(GradeComponentType.class);
			invalidPolicy.put(GradeComponentType.ASSIGNMENT, new GradeComponent(GradeComponentType.ASSIGNMENT, 0.3));
			invalidPolicy.put(GradeComponentType.FINAL, new GradeComponent(GradeComponentType.FINAL, 0.3));
			try {
				new GradingPolicy(invalidPolicy);
				fail("Total weight must equal 1");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("Total weight"));
			}

			try {
				Map<GradeComponentType, GradeComponent> emptyPolicy = new EnumMap<>(GradeComponentType.class);
				new GradingPolicy(emptyPolicy);
				fail("Empty policy should fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("must not be empty"));
			}

			try {
				new GradingPolicy(null);
				fail("Null policy components should fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("must not be empty"));
			}

			try {
				policy.getComponents().put(GradeComponentType.PROJECT, new GradeComponent(GradeComponentType.PROJECT, 0.0));
				fail("Policy map must be unmodifiable");
			} catch (UnsupportedOperationException expected) {
				// expected
			}
		}

		// Shared repositories and services used by later test sections.
		StudentRepository studentRepo = new InMemoryStudentRepository();
		CourseRepository courseRepo = new InMemoryCourseRepository();
		EnrollmentRepository enrollmentRepo = new InMemoryEnrollmentRepository();
		GradeService gradeService = new GradeService(enrollmentRepo, policy);
		EnrollmentService enrollmentService = new EnrollmentService(studentRepo, courseRepo, enrollmentRepo, policy);
		ReportService reportService = new ReportService(studentRepo, courseRepo, enrollmentRepo, policy, gradeService);

		LocalDate dob = LocalDate.now().minusYears(20);
		Student student = new Student(" Alice ", dob);

		{
			System.out.println(student.getDateOfBirth());
			System.out.println(student.equals(student));
			System.out.println(student.equals(123));

			assertNotNull(student.getId());
			assertEquals("Alice", student.getName());

			student.setName(" Bob ");
			assertEquals("Bob", student.getName());

			student.setDateOfBirth(LocalDate.now().minusYears(18));
			studentRepo.save(student);

			assertTrue(studentRepo.findById(student.getId()).isPresent());
			assertTrue(studentRepo.findByName("bob").isPresent());
			assertEquals(1, studentRepo.findAll().size());

			studentRepo.deleteById(student.getId());
			assertFalse(studentRepo.findById(student.getId()).isPresent());
			studentRepo.save(student);

			try {
				student.setName("   ");
				fail("Blank name should fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("name"));
			}

			try {
				new Student("", dob);
				fail("Constructor should validate name");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("name"));
			}

			try {
				student.setDateOfBirth(LocalDate.now().plusDays(1));
				fail("Future birthdate should fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("past or present"));
			}

			assertNotEquals(student, new Student("Bob", LocalDate.now().minusYears(19)));

			Student sameIdStudent = new Student("Charlie", LocalDate.now().minusYears(22));
			try {
				// Align student ids to exercise equals/hashCode contract.
				Field studentIdField = Student.class.getDeclaredField("id");
				studentIdField.setAccessible(true);
				studentIdField.set(sameIdStudent, student.getId());
			} catch (ReflectiveOperationException e) {
				fail("Failed to align student ids: " + e.getMessage());
			}

			assertEquals(student, sameIdStudent);
			assertEquals(student.hashCode(), sameIdStudent.hashCode());
			assertFalse(studentRepo.findByName(null).isPresent());

			try {
				studentRepo.save(null);
				fail("Null student must fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("student"));
			}
		}

		Course course = new Course(" cs101 ", " Intro to CS ", 3);

		{
			assertEquals("CS101", course.getCode());
			assertEquals("Intro to CS", course.getTitle());

			course.setTitle("Data Structures");
//			course.setCode("cs102");
			System.out.println(course.equals(course));
			System.out.println(course.equals(123));

			course.setCreditHours(4);
			courseRepo.save(course);

			assertTrue(courseRepo.findById(course.getId()).isPresent());
			assertTrue(courseRepo.findByCode("cs101").isPresent());
			assertEquals(1, courseRepo.findAll().size());

			courseRepo.deleteById(course.getId());
			assertFalse(courseRepo.findById(course.getId()).isPresent());
			courseRepo.save(course);

			try {
				course.setCreditHours(0);
				fail("Non-positive credit hours must fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("positive"));
			}

			try {
				new Course("", "Title", 3);
				fail("Blank code must fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("code"));
			}

			assertNotEquals(course, new Course("CS102", "Another", 3));

			Course sameIdCourse = new Course("CS101", "Duplicate", 3);
			try {
				// Align course ids via reflection for equals/hashCode coverage.
				Field courseIdField = Course.class.getDeclaredField("id");
				courseIdField.setAccessible(true);
				courseIdField.set(sameIdCourse, course.getId());
			} catch (ReflectiveOperationException e) {
				fail("Failed to align course ids: " + e.getMessage());
			}

			assertEquals(course, sameIdCourse);
			assertEquals(course.hashCode(), sameIdCourse.hashCode());
			assertFalse(courseRepo.findByCode(null).isPresent());

			try {
				courseRepo.save(null);
				fail("Null course must fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("course"));
			}
		}

		GradeComponent assignmentComponent = new GradeComponent(GradeComponentType.ASSIGNMENT, 0.4);
		GradeComponent finalComponent = new GradeComponent(GradeComponentType.FINAL, 0.6);
		assertEquals(GradeComponentType.ASSIGNMENT, assignmentComponent.getType());
		assignmentComponent.setType(GradeComponentType.PROJECT);
		try {
		    assignmentComponent.setType(null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		System.out.println(assignmentComponent.equals(assignmentComponent));
		System.out.println(assignmentComponent.equals(123));
		System.out.println(assignmentComponent.hashCode());
		assignmentComponent.setWeight(0.5);
		assertEquals(GradeComponentType.PROJECT, assignmentComponent.getType());
		assertEquals(0.5, assignmentComponent.getWeight(), EPS);
		assertEquals(finalComponent, new GradeComponent(GradeComponentType.FINAL, 0.4));
		assertNotEquals(assignmentComponent, finalComponent);
		try {
			new GradeComponent(null, 0.1);
			fail("Null type should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("must not be null"));
		}
		try {
			assignmentComponent.setWeight(1.5);
			fail("Weight outside range should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("between"));
		}

		GradeRecord assignmentRecord = new GradeRecord(GradeComponentType.ASSIGNMENT, 85.0);

		{
			assertEquals(85.0, assignmentRecord.getScore(), EPS);
			assignmentRecord.setScore(90.0);
			assertEquals(90.0, assignmentRecord.getScore(), EPS);

			try {
				new GradeRecord(null, 50.0);
				fail("Null component type should fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("componentType"));
			}

			try {
				assignmentRecord.setScore(120.0);
				fail("Score > 100 should fail");
			} catch (ValidationException expected) {
				assertTrue(expected.getMessage().contains("between"));
			}
		}

		Enrollment enrollment = new Enrollment(student.getId(), course.getId(), 2024, Term.FALL);
		assertEquals(EnrollmentStatus.ENROLLED, enrollment.getStatus());
		GradeRecord finalRecord = new GradeRecord(GradeComponentType.FINAL, 95.0);
		enrollment.recordGrade(assignmentRecord);
		enrollment.recordGrade(finalRecord);
		assertEquals(93.0, enrollment.getAverageScore(policy.getComponents()), EPS);
		Map<GradeComponentType, GradeComponent> zeroWeightPolicy = new EnumMap<>(GradeComponentType.class);
		zeroWeightPolicy.put(GradeComponentType.ASSIGNMENT, new GradeComponent(GradeComponentType.ASSIGNMENT, 0.0));
		zeroWeightPolicy.put(GradeComponentType.FINAL, new GradeComponent(GradeComponentType.FINAL, 0.0));
		try {
			enrollment.getAverageScore(zeroWeightPolicy);
			fail("Zero total weight should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("Total weight"));
		}
		try {
			enrollment.recordGrade(null);
			fail("Null record should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("record"));
		}
		try {
			enrollment.getAverageScore(new EnumMap<>(GradeComponentType.class));
			fail("Empty policy components should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("must not be empty"));
		}
		try {
		    enrollment.getAverageScore(null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		Enrollment secondEnrollment = new Enrollment(student.getId(), course.getId(), 2024, Term.SPRING);
		secondEnrollment.drop();
		assertEquals(EnrollmentStatus.DROPPED, secondEnrollment.getStatus());
		try {
			secondEnrollment.recordGrade(new GradeRecord(GradeComponentType.ASSIGNMENT, 80));
			fail("Dropped enrollment should not accept grades");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("dropped"));
		}
		try {
			secondEnrollment.complete();
			fail("Dropped enrollment cannot complete");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("dropped"));
		}
		Enrollment thirdEnrollment = new Enrollment(student.getId(), course.getId(), 2025, Term.SUMMER);
		thirdEnrollment.complete();
		assertEquals(EnrollmentStatus.COMPLETED, thirdEnrollment.getStatus());
		try {
			thirdEnrollment.drop();
			fail("Completed enrollment cannot drop");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("completed"));
		}
		Enrollment fourthEnrollment = new Enrollment(student.getId(), course.getId(), 2025, Term.WINTER);
		fourthEnrollment.markIncomplete();
		assertEquals(EnrollmentStatus.INCOMPLETE, fourthEnrollment.getStatus());
		try {
			fourthEnrollment.markIncomplete();
			fail("Cannot mark incomplete twice");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("Incomplete"));
		}
		fourthEnrollment.complete();
		assertEquals(EnrollmentStatus.COMPLETED, fourthEnrollment.getStatus());

		Enrollment savedEnrollment = enrollmentRepo.save(enrollment);
		assertEquals(savedEnrollment.getId(), enrollment.getId());
		enrollmentRepo.save(secondEnrollment);
		enrollmentRepo.save(thirdEnrollment);
		enrollmentRepo.save(fourthEnrollment);
		List<Enrollment> byStudent = enrollmentRepo.findByStudentId(student.getId());
		List<Enrollment> byCourse = enrollmentRepo.findByCourseId(course.getId());

		assertEquals(4, byStudent.size());
		assertEquals(4, byCourse.size());
		assertEquals(4, enrollmentRepo.findAll().size());
		enrollmentRepo.deleteById(secondEnrollment.getId());
		assertFalse(enrollmentRepo.findById(secondEnrollment.getId()).isPresent());

		try {
		    new Enrollment("123", course.getId(), 2029, null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		Enrollment equalsCandidate = new Enrollment(student.getId(), course.getId(), 2029, Term.SUMMER);
		System.out.println(enrollment.equals(enrollment));
		System.out.println(equalsCandidate.getGradesByComponent());
		assertNotEquals(enrollment, equalsCandidate);
		assertNotEquals(enrollment, new Object());
		try {
			// Force identical ids via reflection to cover equals/hashCode contract paths.
			Field idField = Enrollment.class.getDeclaredField("id");
			idField.setAccessible(true);
			idField.set(equalsCandidate, enrollment.getId());
		} catch (ReflectiveOperationException e) {
			fail("Failed to align enrollment ids: " + e.getMessage());
		}
		assertEquals(enrollment, equalsCandidate);
		assertEquals(enrollment.hashCode(), equalsCandidate.hashCode());
		try {
			enrollmentRepo.save(null);
			fail("Null enrollment must fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("enrollment"));
		}

		courseRepo.save(course);
		Enrollment managed = enrollmentService.enroll(student.getId(), course.getId(), 2026, Term.FALL);
		assertEquals(EnrollmentStatus.ENROLLED, managed.getStatus());
		try {
			enrollmentService.enroll(student.getId(), course.getId(), 2026, Term.FALL);
			fail("Duplicate enrollment should fail");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("already"));
		}
		assertEquals(0.0, enrollmentService.computeEnrollmentPercentage(managed.getId()), EPS);
		gradeService.recordGrade(managed.getId(), GradeComponentType.ASSIGNMENT, 100.0);
		gradeService.updateGrade(managed.getId(), GradeComponentType.FINAL, 92.0);
		assertEquals(95.2, gradeService.computePercentage(managed.getId()), EPS);
		assertEquals(4.0, gradeService.computeGpa(managed.getId()), EPS);
		try {
			gradeService.recordGrade("missing", GradeComponentType.ASSIGNMENT, 80.0);
			fail("Unknown enrollment must fail");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("not found"));
		}
		try {
			gradeService.ensureComponentExists(GradeComponentType.EXTRA_CREDIT);
			fail("Missing component should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("Component"));
		}
		try {
			gradeService.toGpa(-5.0);
			fail("Invalid percentage should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("between"));
		}
		double[][] gpaCases = {
			{90.0, 3.7},
			{88.0, 3.3},
			{83.0, 3.0},
			{80.0, 2.7},
			{77.0, 2.3},
			{73.0, 2.0},
			{70.0, 1.7},
			{67.0, 1.3},
			{63.0, 1.0},
			{60.0, 0.7},
			{50.0, 0.0}
		};

		for (double[] gpaCase : gpaCases) {
			assertEquals(gpaCase[1], gradeService.toGpa(gpaCase[0]), EPS);
		}
		enrollmentService.drop(managed.getId());
		assertEquals(EnrollmentStatus.DROPPED, enrollmentRepo.findById(managed.getId()).get().getStatus());
		try {
			enrollmentService.drop("missing-enrollment");
			fail("Missing enrollment should fail");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("Enrollment not found"));
		}
		try {
			enrollmentService.enroll("missing-student", course.getId(), 2029, Term.FALL);
			fail("Missing student should fail");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("Student not found"));
		}
		try {
			enrollmentService.enroll(student.getId(), "missing-course", 2029, Term.FALL);
			fail("Missing course should fail");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("Course not found"));
		}

		Enrollment reportEnrollment = enrollmentService.enroll(student.getId(), course.getId(), 2027, Term.SPRING);
		gradeService.recordGrade(reportEnrollment.getId(), GradeComponentType.ASSIGNMENT, 80.0);
		gradeService.updateGrade(reportEnrollment.getId(), GradeComponentType.FINAL, 90.0);
		Enrollment reportEntity = enrollmentRepo.findById(reportEnrollment.getId()).get();
		reportEntity.complete();
		enrollmentRepo.save(reportEntity);
		Transcript transcript = reportService.buildTranscript(student.getId());
		assertFalse(transcript.getItems().isEmpty());
		Transcript.LineItem line = transcript.getItems().get(0);
		assertEquals("CS101", line.getCourseCode());
		assertEquals(course.getTitle(), line.getCourseTitle());
		assertEquals(course.getCreditHours(), line.getCreditHours());
//		assertEquals(reportEntity.getAverageScore(policy.getComponents()), line.getPercentage(), EPS);
		assertEquals(gradeService.toGpa(line.getPercentage()), line.getGpaPoints(), EPS);
		Enrollment nonCompleted = enrollmentService.enroll(student.getId(), course.getId(), 2028, Term.SUMMER);
		Transcript transcriptAfterIncomplete = reportService.buildTranscript(student.getId());
		assertEquals(transcript.getItems().size(), transcriptAfterIncomplete.getItems().size());
		Enrollment incompleteEnrollment = enrollmentService.enroll(student.getId(), course.getId(), 2029, Term.SPRING);
		incompleteEnrollment.markIncomplete();
		enrollmentRepo.save(incompleteEnrollment);
		Transcript transcriptWithIncomplete = reportService.buildTranscript(student.getId());
		assertTrue(transcriptWithIncomplete.getItems().stream()
			.anyMatch(item -> item.getCourseCode().equals("CS101") && Math.abs(item.getPercentage() - 0.0) <= EPS));
		try {
			reportService.buildTranscript("unknown");
			fail("Missing student should fail");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("Student not found"));
		}
		Course orphanCourse = new Course("ghost", "Ghost", 2);
		courseRepo.save(orphanCourse);
		Enrollment orphanEnrollment = enrollmentService.enroll(student.getId(), orphanCourse.getId(), 2030, Term.FALL);
		orphanEnrollment.complete();
		enrollmentRepo.save(orphanEnrollment);
		courseRepo.deleteById(orphanCourse.getId());
		try {
			reportService.buildTranscript(student.getId());
			fail("Missing course on transcript should fail");
		} catch (DomainException expected) {
			assertTrue(expected.getMessage().contains("Course not found"));
		} finally {
			enrollmentRepo.deleteById(orphanEnrollment.getId());
		}

		Transcript manualTranscript = new Transcript();
		assertEquals(0.0, manualTranscript.computeCumulativeGpa(), EPS);
		Transcript.LineItem firstLine = new Transcript.LineItem("TEST101", "Test Course", 3, 95.0, 4.0);
		Transcript.LineItem secondLine = new Transcript.LineItem("TEST102", "Second Course", 4, 85.0, 3.0);
		manualTranscript.addItem(firstLine);
		assertEquals(4.0, manualTranscript.computeCumulativeGpa(), EPS);
		manualTranscript.addItem(secondLine);
		assertEquals((4.0 * 3 + 3.0 * 4) / 7.0, manualTranscript.computeCumulativeGpa(), EPS);
		assertEquals("Test Course", manualTranscript.getItems().get(0).getCourseTitle());
		try {
			manualTranscript.getItems().add(new Transcript.LineItem("X", "Y", 1, 50.0, 0.0));
			fail("Transcript view must be unmodifiable");
		} catch (UnsupportedOperationException expected) {
			// expected
		}
		try {
			manualTranscript.addItem(null);
			fail("Null transcript item should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("item"));
		}

		try {
			ValidationUtil.requireNonBlank(" ", "field");
			fail("requireNonBlank should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("field"));
		}
		try {
			ValidationUtil.requireNonBlank(null, "field");
			fail("requireNonBlank should fail");
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			ValidationUtil.requirePositive(0, "value");
			fail("requirePositive should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("positive"));
		}
		try {
			ValidationUtil.requireNonNegative(-1.0, "value");
			fail("requireNonNegative should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("non-negative"));
		}
		ValidationUtil.requireNonNegative(0.0, "value");
		try {
			ValidationUtil.requireBetween(6.5, 6.0, 7.0, "value");
		} catch (ValidationException unexpected) {
			fail("Valid range should not throw");
		}
		try {
			ValidationUtil.requireBetween(10.0, 0.0, 5.0, "value");
			fail("Out of range should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("between"));
		}
		try {
			ValidationUtil.requirePastOrPresent(LocalDate.now().plusDays(2), "date");
			fail("Future date should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("past or present"));
		}
		try {
			ValidationUtil.requirePastOrPresent(null, "date");
			fail("Null date should fail");
		} catch (ValidationException expected) {
			assertTrue(expected.getMessage().contains("must not be null"));
		}

		DomainException domainException = new DomainException("domain error", new IllegalStateException("cause"));
		assertEquals("domain error", domainException.getMessage());
		assertTrue(domainException.getCause() instanceof IllegalStateException);
		ValidationException validationException = new ValidationException("validation error");
		assertEquals("validation error", validationException.getMessage());
	}

}
