package net.mooctest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SquareTest {

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
		assertEquals(469, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	@Test()
	public void testSquareCbc() throws Throwable {
		byte[] key_in = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
				0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};

		SquareCbc squareCbc = new SquareCbc();
		squareCbc.setKey(key_in);
		squareCbc.setIV(key_in);
		squareCbc.encrypt(key_in, 0, 16);
		squareCbc.decrypt(key_in, 0, 16);
		System.out.println(squareCbc.toString());
		squareCbc.finalize();


	}

	@Test()
	public void testSquareCfb() throws Throwable {
		byte[] key_in = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
				0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};

		SquareCfb squareCfb = new SquareCfb();
		squareCfb.setKey(key_in);
		squareCfb.setIV(key_in);

		Field exp = SquareCfb.class.getDeclaredField("avail");
		exp.setAccessible(true);

		exp.set(squareCfb, 1);
		squareCfb.encrypt(key_in, 0, 16);
		squareCfb.decrypt(key_in, 0, 16);

		try {
			exp.set(squareCfb, -1);
			squareCfb.encrypt(key_in, 0, 16);
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			exp.set(squareCfb, -1);
			squareCfb.decrypt(key_in, 0, 16);
		} catch (Exception e) {
			System.out.println(e);
		}

		exp.set(squareCfb, 16);
		squareCfb.encrypt(key_in, 0, 16);
		exp.set(squareCfb, 16);
		squareCfb.decrypt(key_in, 0, 16);

		System.out.println(squareCfb.toString());
		squareCfb.finalize();

	}

	@Test()
	public void testSquareCts() throws Throwable {
		byte[] key_in = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
				0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};

		SquareCts squareCts = new SquareCts();
		squareCts.setKey(key_in);
		squareCts.setIV(key_in);
		squareCts.encrypt(key_in, 0, 16);
		squareCts.decrypt(key_in, 0, 16);
		try {
			squareCts.encrypt(key_in, 0, 0);
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			squareCts.decrypt(key_in, 0, 0);
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			squareCts.encrypt(key_in, 0, 18);
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			squareCts.decrypt(key_in, 0, 36);
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(squareCts.toString());
		squareCts.finalize();

	}


	@Test()
	public void testSquareEcb() throws Throwable {
		byte[] key_in = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
				0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};

		SquareEcb squareCfb = new SquareEcb();
		squareCfb.setKey(key_in);
		squareCfb.encrypt(key_in, 0, 16);
		squareCfb.encrypt(key_in, 0, 0);
		squareCfb.decrypt(key_in, 0, 16);
		squareCfb.decrypt(key_in, 0, 0);
		System.out.println(squareCfb.toString());

	}

	@Test()
	public void testSquareOfb() throws Throwable {
		byte[] key_in = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
				0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};

		SquareOfb squareOfb = new SquareOfb();
		squareOfb.setKey(key_in);
		squareOfb.setIV(key_in);

		Field exp = SquareOfb.class.getDeclaredField("avail");
		exp.setAccessible(true);

		exp.set(squareOfb, 1);
		squareOfb.encrypt(key_in, 0, 16);
		squareOfb.decrypt(key_in, 0, 16);

		try {
			exp.set(squareOfb, -1);
			squareOfb.encrypt(key_in, 0, 16);
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			exp.set(squareOfb, -1);
			squareOfb.decrypt(key_in, 0, 16);
		} catch (Exception e) {
			System.out.println(e);
		}

		exp.set(squareOfb, 16);
		squareOfb.encrypt(key_in, 0, 16);
		exp.set(squareOfb, 16);
		squareOfb.decrypt(key_in, 0, 16);

		System.out.println(squareOfb.toString());
		squareOfb.finalize();
	}

	@Test
	public void testSquare() throws Throwable {
		byte[] key_in = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
				0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
		Square square = new Square(key_in);
		square.finalize();
		System.out.println(square.toString());
	}

	@Test
	public void testmul() throws Throwable {
		byte[] key_in = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
				0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
		Square square = new Square(key_in);
		Method mul = Square.class.getDeclaredMethod("mul", int.class, int.class);
		mul.setAccessible(true);
		System.out.println(mul.invoke(square, 0, 0x83));
		System.out.println(mul.invoke(square, 0x57, 0));
		System.out.println(mul.invoke(square, 0, 0));
	}


}
