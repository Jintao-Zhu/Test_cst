package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.PublicKey;
import java.util.LinkedList;

public class DatalogTest {

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
		assertEquals(417, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	@Test
	public void test(){
		Argument argument = Argument.value(new Value("value"));
		System.out.println(Argument.variable(new Variable("variable")));
		System.out.println(argument.getVariable());
		System.out.println(argument.toString());

		Predicate predicate = new Predicate("predicate");
		try {
		    new Predicate(null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		System.out.println(predicate.toString());
		System.out.println(predicate.equals(new Predicate("predicate")));
		System.out.println(predicate.equals(null));
		System.out.println(predicate.equals(1));
		System.out.println(predicate.hashCode());
		System.out.println(predicate.getName());

		Datalog head = new Datalog(predicate, argument);
		Datalog body = new Datalog(predicate, argument);
		try {
		    new Datalog(null, argument);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    new Datalog(predicate, null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		System.out.println(head.toString());
		System.out.println(head.equals(new Datalog(predicate, argument)));
		System.out.println(head.equals(null));
		System.out.println(head.equals(1));
		System.out.println(head.hashCode());
		System.out.println(head.getArguments());
		System.out.println(head.getPredicate());
		body.substituteTo(new Fact(predicate, new Value("value")));

		Rule rule = new Rule(head, body);
		System.out.println(rule.toString());

		Program program = new Program(rule);
		program.canDerive(new Fact(predicate, new Value("value")), new Fact[]{new Fact(predicate, new Value("value"))});
		program.query(head, new Fact[]{new Fact(predicate, new Value("value"))});
		program.deriveAll(new Fact[]{new Fact(predicate, new Value("value"))});

		Fact fact = new Fact(predicate, new Value("value"));
		System.out.println(fact.toString());
		System.out.println(fact.equals(new Fact(predicate, new Value("value"))));
		System.out.println(fact.equals(null));
		System.out.println(fact.equals(1));
		System.out.println(fact.hashCode());
		System.out.println(fact.getPredicate());
		System.out.println(fact.getValues());
		try {
		    new Fact(null, new Value("value"));
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    new Fact(predicate, null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}

		Substitution substitution = new Substitution();
		Variable variable = new Variable("variable");
		Value value = new Value("value");
		substitution.extend(variable, value);
		substitution.applyOn(head);

		Value value2 = new Value("value2");
		Substitution substitution2 = substitution.extend(variable, value2);
		LinkedList<Substitution> res = new LinkedList<Substitution>();
		res.add(substitution);
		try {
			substitution2.extendAll(res);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    substitution2.extend(variable, null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    substitution2.extend(null, value);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    substitution2.extend(null, null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    substitution2.applyOn(null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		System.out.println(value2.equals(value));
		System.out.println(value2.equals(null));
		System.out.println(value2.equals(1));

		Variable variable2 = new Variable("variable2");
		try {
		    new Variable(null);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		System.out.println(variable2.toString());
		System.out.println(variable2.equals(new Variable("variable2")));
		System.out.println(variable2.equals(null));
		System.out.println(variable2.equals(1));
		System.out.println(variable2.hashCode());
	}

	@Test
	public void testSubstituteTo_incompatiblePredicate() {
		Datalog datalog = new Datalog(new Predicate("p"), new Argument[0]);
		Fact fact = new Fact(new Predicate("q"), new Value[0]);
		assertNull(datalog.substituteTo(fact));
	}

	@Test
	public void testSubstituteTo_incompatibleValuesLength() {
		Predicate predicate = new Predicate("p");
		Datalog datalog = new Datalog(predicate, new Argument[0]);
		Fact fact = new Fact(predicate, new Value[]{new Value("v1")});
		assertNull(datalog.substituteTo(fact));
	}
	
}