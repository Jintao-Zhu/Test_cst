package net.mooctest;

/**
 *
 * 开发者做题前，请仔细阅读以下说明：
 *
 * 1、该测试类为测试类示例，不要求完全按照该示例类的格式；
 *	    考生可自行创建测试类，类名可自行定义，但需遵循JUnit命名规范，格式为xxxTest.java，提交类似test.java的文件名将因不符合语法而判0分！
 *
 * 2、所有测试方法放在该顶层类中，不建议再创建内部类。若必需创建内部类，则需检查JUnit对于内部测试类的要求，并添加相关注释，否则将因无法执行而判0分！
 *
 * 3、本比赛使用jdk1.8+JUnit4，未使用以上版本编写测试用例者，不再接受低分申诉；
 *
 * 4、不要修改被测代码，若被测代码中包含数据库相关的操作，可使用mock测试；
 *
 * 5、若测试用例需要生成本地文件，则需要使用相对路径；
 *
 * 6、建议尽量避免卡点提交答案，尤其是两份报告的zip包。
 *
 * */

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Mathematical_ExpressionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		final Boolean originalCacheFlag = Mathematical_Expression.Options.isUseCache();
		final boolean originalBigDecimalFlag = Mathematical_Expression.Options.isUseBigDecimal();
		final Set<String> initialFunctions = new HashSet<>(Mathematical_Expression.getFunctionMap());
		final Set<String> cleanup = new LinkedHashSet<>();
		class Tracker {
			void add(String name) {
				if (!initialFunctions.contains(name)) {
					cleanup.add(name);
				}
			}
			void remove(String name) {
				cleanup.remove(name);
			}
		}
		final Tracker tracker = new Tracker();
		class NameSupplier {
			private int index;
			String next() {
				int value = index++;
				StringBuilder builder = new StringBuilder("t");
				do {
					builder.append((char) ('a' + (value % 26)));
					value = value / 26 - 1;
				} while (value >= 0);
				return builder.toString();
			}
		}
		final NameSupplier nameSupplier = new NameSupplier();
		final PackExpression cachedPackExpression = new PackExpression("wrapped", new TrackerExpression(), "pack");
		try {
			Mathematical_Expression.Options.setUseBigDecimal(!originalBigDecimalFlag);
			assertEquals(!originalBigDecimalFlag, Mathematical_Expression.Options.isUseBigDecimal());
			Mathematical_Expression.Options.setUseBigDecimal(originalBigDecimalFlag);
			Mathematical_Expression.Options.setUseCache(true);
			assertTrue(Mathematical_Expression.Options.isUseCache());
			Mathematical_Expression.Options.cacheCalculation(12345, 9.5);
			Number cachedNumber = Mathematical_Expression.Options.getCacheCalculation(12345);
			assertNotNull(cachedNumber);
			assertEquals(9.5, cachedNumber.doubleValue(), 0.0);
			Mathematical_Expression.Options.cacheCalculation("formula", cachedPackExpression);
			assertSame(cachedPackExpression, Mathematical_Expression.Options.getCacheCalculation("formula"));
			Mathematical_Expression.Options.setUseCache(false);
			assertFalse(Mathematical_Expression.Options.isUseCache());
			assertNull(Mathematical_Expression.Options.getCacheCalculation(12345));
			assertNull(Mathematical_Expression.Options.getCacheCalculation("formula"));
			Calculation defaultCalc = Mathematical_Expression.getInstance(Mathematical_Expression.functionFormulaCalculation2);
			assertTrue(defaultCalc instanceof FunctionFormulaCalculation2);
			Calculation namedCalc = Mathematical_Expression.getInstance(Mathematical_Expression.jvmCalculation, "namedJvmCalc");
			assertEquals("namedJvmCalc", namedCalc.getName());
			String stringFunctionName = "stringFuncAuto";
			Mathematical_Expression.unregister_function(stringFunctionName);
			assertTrue(Mathematical_Expression.register_function(stringFunctionName + "(x)=x+2"));
			tracker.add(stringFunctionName);
			assertEquals(7.0, Mathematical_Expression.getFunction(stringFunctionName).run(5), 0.0);
			assertTrue(Mathematical_Expression.getFunctionMap().contains(stringFunctionName));
			@Functions({"annotatedSum(a,b)=a+b", "annotatedShift(t)=t-3"})
			class AnnotatedFunctionSet {
			}
			class PlainFunctionSet {
			}
			assertTrue(Mathematical_Expression.register_function(AnnotatedFunctionSet.class));
			tracker.add("annotatedSum");
			tracker.add("annotatedShift");
			assertEquals(5.0, Mathematical_Expression.getFunction("annotatedSum").run(2, 3), 0.0);
			assertFalse(Mathematical_Expression.register_function(PlainFunctionSet.class));
			Mathematical_Expression.register_jvm_function("standaloneJvm(z)=z+10");
			@Functions({"jvmAnnoFunc(y)=y*y"})
			class AnnotatedJvmSet {
			}
			class PlainJvmSet {
			}
			assertTrue(Mathematical_Expression.register_jvm_function(AnnotatedJvmSet.class));
			assertFalse(Mathematical_Expression.register_jvm_function(PlainJvmSet.class));
			assertTrue(JvmExpressionFunction.getRegisteredMethods().values().stream().anyMatch(source -> source.contains("jvmAnnoFunc")));
			final SerializableFunctionImpl directFunction = new SerializableFunctionImpl(nameSupplier.next(), 1.5);
			assertTrue(Mathematical_Expression.register_function(directFunction));
			tracker.add(directFunction.getName());
			assertFalse(Mathematical_Expression.register_function(directFunction));
			assertEquals(4.5, directFunction.run(1.5, 1.5), 0.0);
			assertTrue(Mathematical_Expression.unregister_function(directFunction));
			tracker.remove(directFunction.getName());
			String fileFunctionName = nameSupplier.next();
			SerializableFunctionImpl fileFunction = new SerializableFunctionImpl(fileFunctionName, 0.25);
			File functionFile = File.createTempFile("function", ".bin");
			functionFile.deleteOnExit();
			Mathematical_Expression.saveFunction(fileFunction, functionFile);
			assertTrue(Mathematical_Expression.register_function(functionFile));
			tracker.add(fileFunctionName);
			assertEquals(5.25, Mathematical_Expression.getFunction(fileFunctionName).run(2, 3), 0.0);
			File invalidFile = File.createTempFile("invalid", ".bin");
			invalidFile.deleteOnExit();
			try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(invalidFile))) {
				stream.writeObject("not a function");
			}
			assertFalse(Mathematical_Expression.register_function(invalidFile));
			String streamFunctionName = nameSupplier.next();
			SerializableFunctionImpl streamFunction = new SerializableFunctionImpl(streamFunctionName, 5);
			ByteArrayOutputStream rawBuffer = new ByteArrayOutputStream();
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(rawBuffer)) {
				objectOutputStream.writeObject(streamFunction);
				objectOutputStream.writeObject("bad payload");
			}
			Map.Entry<Integer, Integer> streamStats = Mathematical_Expression.register_function(new ByteArrayInputStream(rawBuffer.toByteArray()));
			assertEquals(Integer.valueOf(1), streamStats.getKey());
			assertEquals(Integer.valueOf(1), streamStats.getValue());
			tracker.add(streamFunctionName);
			String serializedName = nameSupplier.next();
			SerializableFunctionImpl serializableFunction = new SerializableFunctionImpl(serializedName, 2);
			NonSerializableFunctionImpl blockedFunction = new NonSerializableFunctionImpl(nameSupplier.next(), 0);
			ByteArrayOutputStream serializedBuffer = new ByteArrayOutputStream();
			Mathematical_Expression.saveFunction(serializedBuffer, serializableFunction, blockedFunction);
			Map.Entry<Integer, Integer> serializedStats = Mathematical_Expression.register_function(new ByteArrayInputStream(serializedBuffer.toByteArray()));
			assertEquals(Integer.valueOf(1), serializedStats.getKey());
			assertEquals(Integer.valueOf(0), serializedStats.getValue());
			tracker.add(serializedName);
			assertFalse(Mathematical_Expression.unregister_function(blockedFunction.getName()));
			Mathematical_Expression.register_function(FunctionPackage.MATH);
			for (String fn : FunctionPackage.MATH.getFunctionMap().keySet()) {
				tracker.add(fn);
			}
			assertEquals(5.0, Mathematical_Expression.getFunction("sum").run(2, 3), 0.0);
			Mathematical_Expression.register_function(FunctionPackage.BRANCH);
			for (String fn : FunctionPackage.BRANCH.getFunctionMap().keySet()) {
				tracker.add(fn);
			}
			assertEquals(9.0, Mathematical_Expression.getFunction("ifEq").run(1, 1, 9, 2), 0.0);
			assertTrue(Mathematical_Expression.getFunctionMap().contains(fileFunctionName));
			assertTrue(Mathematical_Expression.getFunctionMap().contains(streamFunctionName));
			assertTrue(Mathematical_Expression.getFunctionMap().contains(serializedName));
			assertTrue(Mathematical_Expression.unregister_function(fileFunctionName));
			tracker.remove(fileFunctionName);
			String removableName = nameSupplier.next();
			SerializableFunctionImpl removableFunction = new SerializableFunctionImpl(removableName, 0);
			assertTrue(Mathematical_Expression.register_function(removableFunction));
			tracker.add(removableName);
			assertTrue(Mathematical_Expression.unregister_function(removableFunction));
			tracker.remove(removableName);
			assertFalse(Mathematical_Expression.unregister_function("missingNameXYZ"));
			SerializableFunctionImpl ghostFunction = new SerializableFunctionImpl(nameSupplier.next(), 0);
			assertFalse(Mathematical_Expression.unregister_function(ghostFunction));
		} finally {
			for (String functionName : cleanup) {
				Mathematical_Expression.unregister_function(functionName);
			}
			Mathematical_Expression.Options.setUseBigDecimal(originalBigDecimalFlag);
			Mathematical_Expression.Options.setUseCache(originalCacheFlag != null && originalCacheFlag);
		}
	}

	private static class SerializableFunctionImpl extends ManyToOneNumberFunction {
		private final double bias;
		SerializableFunctionImpl(String name, double bias) {
			super(name);
			this.bias = bias;
		}
		@Override
		public double run(double... numbers) {
			double total = bias;
			for (double number : numbers) {
				total += number;
			}
			return total;
		}
	}

	private static class NonSerializableFunctionImpl extends SerializableFunctionImpl {
		NonSerializableFunctionImpl(String name, double bias) {
			super(name, bias);
		}
		@Override
		public boolean AllowSerialization() {
			return false;
		}
	}

	private static class TrackerExpression extends NameExpression {
		TrackerExpression() {
			super("1+1", "trackerCalc");
		}
		@Override
		public boolean isBigDecimal() {
			return false;
		}
		@Override
		public boolean isUnBigDecimal() {
			return true;
		}
		@Override
		public void convertToMultiPrecisionSupported() {
			// intentionally left blank
		}
		@Override
		public CalculationResults calculation(boolean isCopy) {
			return new CalculationNumberResults(0, 2, getCalculationName());
		}
		@Override
		public CalculationResults calculationBigDecimals(boolean isCopy) {
			return new CalculationNumberResults(0, 2, getCalculationName());
		}
	}

}
