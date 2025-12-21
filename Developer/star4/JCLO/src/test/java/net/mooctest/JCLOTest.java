package net.mooctest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;

enum Color
{
	RED, GREEN, BLUE;
}

public class JCLOTest {

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
		assertEquals(941, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}


	static class TestConfig {
		private boolean verbose;
		private java.lang.Boolean verbose1;
		private final boolean ver = false;
		private Byte b;
		private short s;
		private int count;
		private float f;
		private double d;
		private long l;
		private char c;
		private String name;
		String[] additional;
		int[] array;
		private Enum<Color> color;
	}

	private TestConfig config;

	@Test
	public void test() throws NoSuchMethodException, NoSuchFieldException {
		config = new TestConfig();
		JCLO jclo = new JCLO(config);
		String[][] args = {
				{"--verbose=true"},
				{"--verbose=yes"},
				{"--verbose=false"},
				{"--verbose"},
				{"-ver"},
				{"--b=1"},
				{"--b"},
				{"--s=2"},
				{"--count=42"},
				{"--f=1.1"},
				{"--d=2.2"},
				{"--l=3"},
				{"--c=a"},
				{"--name=this"},
				{"--additional={\"string\"}"},
				{"--unknown"},
				{"-count=1"},
				{"args"}
		};
		try {
		    jclo.parse(args[0]);
			System.out.println(jclo.usage());
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[1]);
			System.out.println(jclo.usage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[2]);
			System.out.println(jclo.usage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[3]);
			System.out.println(jclo.usage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[4]);
			System.out.println(jclo.usage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[5]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[6]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[7]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[8]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[9]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[10]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[11]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[12]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[13]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[14]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[15]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[16]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			jclo.parse(args[17]);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			System.out.println(jclo.toString());
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		JCLO jclo1 = new JCLO("~", config);
		try {
			jclo1.parse(new String[]{"~count=1"});
			System.out.println(jclo1.usage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JCLO jclo11 = new JCLO("", config);
		try {
			jclo11.parse(new String[]{"--count=1"});
			System.out.println(jclo11.usage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String[][] aliases = {
				{"v", "verbose"}, // 别名 "v" 对应参数 "verbose"
				{"c", "count"}    // 别名 "c" 对应参数 "count"
		};
		JCLO jclo2 = new JCLO(config, aliases);
		try {
			jclo2.parse(new String[]{"-v", "--v=true"});
			System.out.println(jclo2.usage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		config = new TestConfig();
		JCLO jclo3 = new JCLO(config);
		Method method = JCLO.class.getDeclaredMethod("makeObject", String.class, String.class);
		method.setAccessible(true);
		try {
			method.invoke(jclo3, "java.lang.Boolean", "false");
			method.invoke(jclo3, "byte", "1");
			method.invoke(jclo3, "string", "false");
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			jclo3.parse(new String[]{"-b", "--b=1"});
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			jclo3.parse(new String[]{"--verbose1=1"});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Method method1 = JCLO.class.getDeclaredMethod("addToArray", Field.class, Object.class);
		method1.setAccessible(true);
		Field field = TestConfig.class.getDeclaredField("array");
		try {
			method1.invoke(jclo3, field, "1");
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		config.array = new int[]{1, 2, 3};
		try {
			method1.invoke(jclo3, field, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Method method2 = JCLO.class.getDeclaredMethod("getUsageType", Class.class);
		method2.setAccessible(true);
		Color color = Color.RED;
		try {
			method2.invoke(jclo3, color.getClass());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGetObject1() throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
		config = new TestConfig();
		JCLO jclo = new JCLO(config);
		try {
		    jclo.parse(new String[]{"-f"});
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		JCLO jclo1 = new JCLO(new int[]{1, 2, 3});
		System.out.println(jclo1.toString());
	}
}
