/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BPlusTreeTest {static class __CLR4_4_1btbtm7es04ah{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0031\u0032\u0033\u005c\u005c\u0031\u0031\u0039\u005c\u005c\u0042\u0050\u006c\u0075\u0073\u0054\u0072\u0065\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1740142647726L,8589935092L,456,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
	
	private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private static PrintStream originalOut;

	@BeforeClass
	public static void setUp() {try{__CLR4_4_1btbtm7es04ah.R.inc(425);
		__CLR4_4_1btbtm7es04ah.R.inc(426);originalOut = System.out;
		__CLR4_4_1btbtm7es04ah.R.inc(427);System.setOut(new PrintStream(outputStream));
	}finally{__CLR4_4_1btbtm7es04ah.R.flushNeeded();}}

	@AfterClass
	public static void tearDownAndVerify() {try{__CLR4_4_1btbtm7es04ah.R.inc(428);
		__CLR4_4_1btbtm7es04ah.R.inc(429);System.setOut(originalOut);
		__CLR4_4_1btbtm7es04ah.R.inc(430);String allOutput = standardizeOutput(outputStream.toString());
		__CLR4_4_1btbtm7es04ah.R.inc(431);assertEquals(50, allOutput.length(), 20);
	}finally{__CLR4_4_1btbtm7es04ah.R.flushNeeded();}}

	private static String standardizeOutput(String output) {try{__CLR4_4_1btbtm7es04ah.R.inc(432);
		__CLR4_4_1btbtm7es04ah.R.inc(433);return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}finally{__CLR4_4_1btbtm7es04ah.R.flushNeeded();}}

	@Test
	public void BPlsuTreetest1(){__CLR4_4_1btbtm7es04ah.R.globalSliceStart(getClass().getName(),434);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1npq308c2();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1btbtm7es04ah.R.rethrow($CLV_t2$);}finally{__CLR4_4_1btbtm7es04ah.R.globalSliceEnd(getClass().getName(),"net.mooctest.BPlusTreeTest.BPlsuTreetest1",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),434,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1npq308c2(){try{__CLR4_4_1btbtm7es04ah.R.inc(434);
	    __CLR4_4_1btbtm7es04ah.R.inc(435);BPlusTree<Integer> bp1 = new BPlusTree<Integer>(5);
	    __CLR4_4_1btbtm7es04ah.R.inc(436);System.out.println(bp1.toString());
	    __CLR4_4_1btbtm7es04ah.R.inc(437);bp1.search(0);
	    __CLR4_4_1btbtm7es04ah.R.inc(438);bp1.insert(0, 2);
	    __CLR4_4_1btbtm7es04ah.R.inc(439);bp1.search(0);
	    __CLR4_4_1btbtm7es04ah.R.inc(440);bp1.insert(0, 1);
	    __CLR4_4_1btbtm7es04ah.R.inc(441);bp1.search(2);
	    __CLR4_4_1btbtm7es04ah.R.inc(442);bp1.insert(0, 3);
	    __CLR4_4_1btbtm7es04ah.R.inc(443);bp1.search(1);
	    __CLR4_4_1btbtm7es04ah.R.inc(444);bp1.insert(1, 0);
	    __CLR4_4_1btbtm7es04ah.R.inc(445);bp1.search('2');
	    __CLR4_4_1btbtm7es04ah.R.inc(446);System.out.println(bp1.reverseInOrder());
	    __CLR4_4_1btbtm7es04ah.R.inc(447);System.out.println(bp1.getSize());
	    __CLR4_4_1btbtm7es04ah.R.inc(448);System.out.println(bp1.getMinGap());
	    __CLR4_4_1btbtm7es04ah.R.inc(449);System.out.println(bp1.inOrder());
	    __CLR4_4_1btbtm7es04ah.R.inc(450);System.out.println(bp1.order(0));
	    __CLR4_4_1btbtm7es04ah.R.inc(451);System.out.println(bp1.order(5));
	    __CLR4_4_1btbtm7es04ah.R.inc(452);System.out.println(bp1.toString());
	}finally{__CLR4_4_1btbtm7es04ah.R.flushNeeded();}}
	
	@Test
	public void BPlsuTreetest2(){__CLR4_4_1btbtm7es04ah.R.globalSliceStart(getClass().getName(),453);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1kgq47rcl();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1btbtm7es04ah.R.rethrow($CLV_t2$);}finally{__CLR4_4_1btbtm7es04ah.R.globalSliceEnd(getClass().getName(),"net.mooctest.BPlusTreeTest.BPlsuTreetest2",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),453,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1kgq47rcl(){try{__CLR4_4_1btbtm7es04ah.R.inc(453);
		__CLR4_4_1btbtm7es04ah.R.inc(454);BPlusTree bp1 = new BPlusTree(5, 3);
		__CLR4_4_1btbtm7es04ah.R.inc(455);bp1.search(0);
	}finally{__CLR4_4_1btbtm7es04ah.R.flushNeeded();}}

}
