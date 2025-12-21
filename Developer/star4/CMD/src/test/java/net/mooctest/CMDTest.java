package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class CMDTest {

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
		assertEquals(413, allOutput.length(), 20);
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
		CMD.Option o1 = cmd.addIntegerOption("--123");
		CMD.Option o2 = cmd.addIntegerOption('d', "123");
		CMD.Option o3 = cmd.addStringOption("test1");
		CMD.Option o4 = cmd.addBooleanOption("true");
		CMD.Option o5 = cmd.addDoubleOption("12.3");
		CMD.Option o6 = cmd.addDoubleOption('f', "12.3");
		CMD.Option o7 = cmd.addLongOption("123456");
		CMD.Option o8 = cmd.addLongOption('e', "123456");
		CMD.Option o9 = cmd.addBooleanOption('a', "123456");
		CMD.Option o10 = cmd.addBooleanOption('b', "false");
		CMD.Option o11 = cmd.addStringOption('c', "test2");

		try {
			cmd.addStringOption(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			cmd.addOption(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String[][] input = new String[][]{{"--"}, {"--help"}, {"--help"}, {"--arg=value"}, {"-abcd"},
				{"-a"}, {"--a"}, {"--name"}, {"--unknown"}, {"--name=John", "Doe"},
				{"--name=John", "--", "Doe"}, {"--option="}, {"test"}, {"--name=John", "Doe", "--name=John", "Doe"},
				{"--name=John", "Doe", "--name=John", "Doe", "--name=John", "Doe"}, {"--name=John", "Doe", "--name=John", "Doe", "--name=John", "Doe", "--name=John", "Doe"},
				{"-a-    -b", "-c", "-d", "-e", "-f", "-g", "-h", "-i", "-j", "-k", "-l", "-m", "-n", "-o", "-p", "-q", "-r", "-s", "-t", "-u", "-v", "-w", "-x", "-y", "-z"},
				{"-a", "-b", "-c", "-d", "-e", "-f", "-g", "-h", "-i", "-j", "-k", "-l", "-m", "-n", "-o", "-p", "-q", "-r", "-s", "-t", "-u", "-v", "-w", "-x", "-y", "-z"}};

		for (String[] strings : input) {
			try {
				cmd.parse(strings);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println(o2.longForm());
		System.out.println(cmd.getOptionValues(o2));
		System.out.println(cmd.getOptionValues(o3));
		System.out.println(cmd.getOptionValues(o4));
		System.out.println(cmd.getOptionValues(o5));

		Locale locale = Locale.getDefault();
		try {
			Locale.setDefault(Locale.ENGLISH);
			cmd.parse(new String[]{"--help"});
			System.out.println(o2.getValue("123", locale));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Locale.setDefault(locale);
		}

		try {
			o3.getValue(null, locale);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		CMD.Option.IntegerOption integerOption = (CMD.Option.IntegerOption) o1;
		try {
			integerOption.parseValue("123", locale);
			integerOption.parseValue("aaa", locale);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		CMD.Option.DoubleOption doubleOption = (CMD.Option.DoubleOption) o5;
		try {
			doubleOption.parseValue("12.3", locale);
			doubleOption.parseValue("aaa", locale);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private CMD cmd;

	@Before
	public void setUp1() {
		cmd = new CMD();
	}

	@Test
	public void testBooleanOption() throws CMD.OptionException {
		CMD.Option<Boolean> option = cmd.addBooleanOption('b', "boolean");
		cmd.parse(new String[]{"-b"});
		assertTrue(cmd.getOptionValue(option));
	}

	@Test
	public void testIntegerOptionValid() throws CMD.OptionException {
		CMD.Option<Integer> option = cmd.addIntegerOption('i', "integer");
		cmd.parse(new String[]{"-i", "123"});
		assertEquals(Integer.valueOf(123), cmd.getOptionValue(option));
	}

	@Test(expected = CMD.IllegalOptionValueException.class)
	public void testIntegerOptionInvalid() throws CMD.OptionException {
		CMD.Option<Integer> option = cmd.addIntegerOption('i', "integer");
		cmd.parse(new String[]{"-i", "abc"});
	}

	@Test
	public void testDoubleOptionValid() throws CMD.OptionException {
		CMD.Option<Double> option = cmd.addDoubleOption('d', "double");
		cmd.parse(new String[]{"-d", "12.34"});
		assertEquals(Double.valueOf(12.34), cmd.getOptionValue(option));
	}

	@Test
	public void testStringOption() throws CMD.OptionException {
		CMD.Option<String> option = cmd.addStringOption('s', "string");
		cmd.parse(new String[]{"-s", "value"});
		assertEquals("value", cmd.getOptionValue(option));
	}

	@Test
	public void testUnknownOption() {
		try {
			cmd.parse(new String[]{"-x"});
			fail("Expected UnknownOptionException");
		} catch (CMD.UnknownOptionException e) {
			assertEquals("Unknown option '-x'", e.getMessage());
		} catch (CMD.OptionException e) {
			fail("Unexpected exception type");
		}
	}

	@Test
	public void testUnknownSuboption() {
		CMD.Option<Boolean> option = cmd.addBooleanOption('a', "alpha");
		try {
			cmd.parse(new String[]{"-abc"});
			fail("Expected UnknownSuboptionException");
		} catch (CMD.UnknownSuboptionException e) {
			assertEquals('b', e.getSuboption());
		} catch (CMD.OptionException e) {
			fail("Unexpected exception type");
		}
	}

	@Test
	public void testNotFlagException() {
		CMD.Option<Integer> option = cmd.addIntegerOption('n', "number");
		try {
			cmd.parse(new String[]{"-nnn"});
			fail("Expected NotFlagException");
		} catch (CMD.NotFlagException e) {
			assertEquals('n', e.getOptionChar());
		} catch (CMD.OptionException e) {
			fail("Unexpected exception type");
		}
	}

	@Test
	public void testRemainingArgs() throws CMD.OptionException {
		CMD.Option<Boolean> option = cmd.addBooleanOption('b', "boolean");
		cmd.parse(new String[]{"-b", "arg1", "arg2"});
		assertArrayEquals(new String[]{"arg1", "arg2"}, cmd.getRemainingArgs());
	}

	@Test
	public void testMultipleValues() throws CMD.OptionException {
		CMD.Option<Integer> option = cmd.addIntegerOption('n', "number");
		cmd.parse(new String[]{"-n", "1", "-n", "2", "-n", "3"});
		assertArrayEquals(new Integer[]{1, 2, 3}, cmd.getOptionValues(option).toArray(new Integer[0]));
	}


	@Test
	public void testParse_MultipleShortOptions() throws CMD.OptionException {
		CMD cmd = new CMD();
		CMD.Option<Boolean> optA = cmd.addBooleanOption('a', "alpha");
		CMD.Option<Boolean> optB = cmd.addBooleanOption('b', "beta");

		String[] args = {"-ab"};
		cmd.parse(args);

		assertTrue(cmd.getOptionValue(optA));
		assertTrue(cmd.getOptionValue(optB));
		assertEquals(0, cmd.getRemainingArgs().length);
	}

	@Test(expected = CMD.UnknownSuboptionException.class)
	public void testParse_UnknownSubOption() throws CMD.OptionException {
		CMD cmd = new CMD();
		cmd.addBooleanOption('a', "alpha");
		cmd.addBooleanOption('b', "beta");

		String[] args = {"-ac"};
		cmd.parse(args);
	}

	@Test()
	public void testParse_NotFlagException() throws CMD.OptionException {
		CMD cmd = new CMD();
		CMD.Option<Integer> optC = cmd.addIntegerOption('c', "count");

		String[] args = {"-ac"};
		try {
			cmd.parse(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(expected = CMD.UnknownOptionException.class)
	public void testParse_UnknownOption() throws CMD.OptionException {
		CMD cmd = new CMD();

		String[] args = {"--unknown"};
		cmd.parse(args);
	}

	@Test
	public void testParse_LongOptionWithEquals() throws CMD.OptionException {
		CMD cmd = new CMD();
		CMD.Option<String> opt = cmd.addStringOption('n', "name");

		String[] args = {"--name=value"};
		cmd.parse(args);

		assertEquals("value", cmd.getOptionValue(opt));
		assertEquals(0, cmd.getRemainingArgs().length);
	}

	@Test
	public void testParse_EndOfOptionsDelimiter() throws CMD.OptionException {
		CMD cmd = new CMD();
		CMD.Option<String> opt = cmd.addStringOption('n', "name");

		String[] args = {"--name", "value", "--", "positional1", "positional2"};
		cmd.parse(args);

		assertEquals("value", cmd.getOptionValue(opt));
		assertArrayEquals(new String[]{"positional1", "positional2"}, cmd.getRemainingArgs());
	}

	@Test
	public void testParse_PositionalArgumentsOnly() throws CMD.OptionException {
		CMD cmd = new CMD();

		String[] args = {"positional1", "positional2"};
		cmd.parse(args);

		assertArrayEquals(new String[]{"positional1", "positional2"}, cmd.getRemainingArgs());
	}

	@Test
	public void testParse_MixedOptionsAndArguments() throws CMD.OptionException {
		CMD cmd = new CMD();
		CMD.Option<String> opt1 = cmd.addStringOption('n', "name");
		CMD.Option<Boolean> opt2 = cmd.addBooleanOption('v', "verbose");

		String[] args = {"--name", "test", "-v", "arg1", "arg2"};
		cmd.parse(args);

		assertEquals("test", cmd.getOptionValue(opt1));
		assertTrue(cmd.getOptionValue(opt2));
		assertArrayEquals(new String[]{"arg1", "arg2"}, cmd.getRemainingArgs());
	}

	@Test
	public void testParse_NullValueForOptionalOption() throws CMD.OptionException {
		CMD cmd = new CMD();
		CMD.Option<Boolean> opt = cmd.addBooleanOption('v', "verbose");

		String[] args = {"-v"};
		cmd.parse(args);

		assertTrue(cmd.getOptionValue(opt));
	}

	@Test(expected = CMD.IllegalOptionValueException.class)
	public void testParse_IllegalValueForOption() throws CMD.OptionException {
		CMD cmd = new CMD();
		CMD.Option<Integer> opt = cmd.addIntegerOption('n', "number");

		String[] args = {"--number", "not_a_number"};
		cmd.parse(args);
	}

	@Test
	public void testParse_MultiLocaleSupport() throws CMD.OptionException {
		CMD cmd = new CMD();
		CMD.Option<Double> opt = cmd.addDoubleOption('d', "double");

		String[] args = {"--double", "3,14"};
		cmd.parse(args, Locale.GERMANY);

		assertEquals(Double.valueOf(3.14), cmd.getOptionValue(opt));
	}

}
