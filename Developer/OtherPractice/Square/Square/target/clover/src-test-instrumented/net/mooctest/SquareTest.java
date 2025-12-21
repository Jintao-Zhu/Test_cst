/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTest {static class __CLR4_4_1ckckloux25vv{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0030\u0035\u0038\u005c\u005c\u0031\u0033\u0034\u0034\u0038\u0034\u005c\u005c\u0053\u0071\u0075\u0061\u0072\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1699759735596L,8589935092L,467,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

	@Test(timeout = 2000)
	public void test() throws Throwable {__CLR4_4_1ckckloux25vv.R.globalSliceStart(getClass().getName(),452);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1zi4lt3ck();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1ckckloux25vv.R.rethrow($CLV_t2$);}finally{__CLR4_4_1ckckloux25vv.R.globalSliceEnd(getClass().getName(),"net.mooctest.SquareTest.test",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),452,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1zi4lt3ck() throws Throwable{try{__CLR4_4_1ckckloux25vv.R.inc(452);
		__CLR4_4_1ckckloux25vv.R.inc(453);byte[] byte0 = new byte[16];
		__CLR4_4_1ckckloux25vv.R.inc(454);byte[] byte1 = new byte[16];
		__CLR4_4_1ckckloux25vv.R.inc(455);Square square = new Square(byte0);
		
		__CLR4_4_1ckckloux25vv.R.inc(456);square.blockDecrypt(byte0, 0, byte1, 0);
		__CLR4_4_1ckckloux25vv.R.inc(457);square.blockEncrypt(byte0, 0, byte1, 0);
		
		
		
		__CLR4_4_1ckckloux25vv.R.inc(458);square.finalize();
		
		
	}finally{__CLR4_4_1ckckloux25vv.R.flushNeeded();}}
	
	@Test
	public void test1() {__CLR4_4_1ckckloux25vv.R.globalSliceStart(getClass().getName(),459);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_14e6beucr();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1ckckloux25vv.R.rethrow($CLV_t2$);}finally{__CLR4_4_1ckckloux25vv.R.globalSliceEnd(getClass().getName(),"net.mooctest.SquareTest.test1",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),459,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_14e6beucr(){try{__CLR4_4_1ckckloux25vv.R.inc(459);
		__CLR4_4_1ckckloux25vv.R.inc(460);byte[] byte0 = new byte[16];
		__CLR4_4_1ckckloux25vv.R.inc(461);byte[] byte1 = new byte[16];
		__CLR4_4_1ckckloux25vv.R.inc(462);Square square = new Square(byte0);
		
		__CLR4_4_1ckckloux25vv.R.inc(463);square.blockDecrypt(byte0, 0, byte1, 0);
		__CLR4_4_1ckckloux25vv.R.inc(464);square.blockEncrypt(byte0, 0, byte1, 0);
		
		__CLR4_4_1ckckloux25vv.R.inc(465);assertArrayEquals(byte1, new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,-63,-100,38,-8,-49});
		__CLR4_4_1ckckloux25vv.R.inc(466);assertArrayEquals(byte0, new byte[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
		
	}finally{__CLR4_4_1ckckloux25vv.R.flushNeeded();}}

}
