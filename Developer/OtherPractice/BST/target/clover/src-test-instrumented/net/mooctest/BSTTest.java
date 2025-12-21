/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.javaparser.ast.Node;

public class BSTTest {static class __CLR4_4_1fbfblpnrioa0{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0036\u0030\u0032\u005c\u005c\u0031\u0033\u0034\u0034\u0038\u0034\u005c\u005c\u0042\u0053\u0054\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701503947351L,8589935092L,782,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

	@Test
	public void test() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),551);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1zi4lt3fb();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),551,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1zi4lt3fb(){try{__CLR4_4_1fbfblpnrioa0.R.inc(551);
		__CLR4_4_1fbfblpnrioa0.R.inc(552);BST<Integer, String> bst = new BST<>();
		
		__CLR4_4_1fbfblpnrioa0.R.inc(553);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(554);bst.select(-1);
		} catch (Exception e) {}
		__CLR4_4_1fbfblpnrioa0.R.inc(555);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(556);bst.max();
		} catch (Exception e) {}
		__CLR4_4_1fbfblpnrioa0.R.inc(557);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(558);bst.min();
		} catch (Exception e) {}
		__CLR4_4_1fbfblpnrioa0.R.inc(559);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(560);bst.deleteMin();
		} catch (Exception e) {}
		__CLR4_4_1fbfblpnrioa0.R.inc(561);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(562);bst.deleteMax();
		} catch (Exception e) {}
		__CLR4_4_1fbfblpnrioa0.R.inc(563);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(564);bst.delete(null);
		} catch (Exception e) {}
		
		
        __CLR4_4_1fbfblpnrioa0.R.inc(565);assertTrue(bst.isEmpty());
        __CLR4_4_1fbfblpnrioa0.R.inc(566);assertEquals(0, bst.size());
        
        __CLR4_4_1fbfblpnrioa0.R.inc(567);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(568);bst.contains(null);
		} catch (Exception e) {}
        __CLR4_4_1fbfblpnrioa0.R.inc(569);try {
        	__CLR4_4_1fbfblpnrioa0.R.inc(570);bst.get(null);
        } catch (Exception e) {}
        __CLR4_4_1fbfblpnrioa0.R.inc(571);try {
        	__CLR4_4_1fbfblpnrioa0.R.inc(572);bst.put(null, "a");
        } catch (Exception e) {}
        
        __CLR4_4_1fbfblpnrioa0.R.inc(573);bst.put(2, null);



        __CLR4_4_1fbfblpnrioa0.R.inc(574);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(575);assertFalse(bst.isEmpty());
        __CLR4_4_1fbfblpnrioa0.R.inc(576);assertEquals(1, bst.size());
        __CLR4_4_1fbfblpnrioa0.R.inc(577);assertTrue(bst.contains(5));
        __CLR4_4_1fbfblpnrioa0.R.inc(578);assertEquals("Five", bst.get(5));

        __CLR4_4_1fbfblpnrioa0.R.inc(579);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(580);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(581);assertEquals(3, bst.size());
        __CLR4_4_1fbfblpnrioa0.R.inc(582);assertTrue(bst.contains(3));
        __CLR4_4_1fbfblpnrioa0.R.inc(583);assertTrue(bst.contains(8));

        __CLR4_4_1fbfblpnrioa0.R.inc(584);bst.delete(3);
        __CLR4_4_1fbfblpnrioa0.R.inc(585);assertFalse(bst.contains(3));
        __CLR4_4_1fbfblpnrioa0.R.inc(586);assertEquals(2, bst.size());
        
        __CLR4_4_1fbfblpnrioa0.R.inc(587);bst.put(3, "three");
        __CLR4_4_1fbfblpnrioa0.R.inc(588);bst.put(3, "three");
	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	@Test
	public void test0() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),589);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1156cmdgd();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test0",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),589,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1156cmdgd(){try{__CLR4_4_1fbfblpnrioa0.R.inc(589);
		__CLR4_4_1fbfblpnrioa0.R.inc(590);Queue<String> queue = new Queue<>();
        __CLR4_4_1fbfblpnrioa0.R.inc(591);assertTrue(queue.isEmpty());
        __CLR4_4_1fbfblpnrioa0.R.inc(592);assertEquals(0, queue.size());

        __CLR4_4_1fbfblpnrioa0.R.inc(593);queue.enqueue("One");
        __CLR4_4_1fbfblpnrioa0.R.inc(594);assertFalse(queue.isEmpty());
        __CLR4_4_1fbfblpnrioa0.R.inc(595);assertEquals(1, queue.size());
        __CLR4_4_1fbfblpnrioa0.R.inc(596);assertEquals("One", queue.peek());

        __CLR4_4_1fbfblpnrioa0.R.inc(597);queue.enqueue("Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(598);queue.enqueue("Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(599);assertEquals(3, queue.size());
        __CLR4_4_1fbfblpnrioa0.R.inc(600);assertEquals("One Two Three ", queue.toString());

        __CLR4_4_1fbfblpnrioa0.R.inc(601);assertEquals("One", queue.dequeue());
        __CLR4_4_1fbfblpnrioa0.R.inc(602);assertEquals("Two", queue.dequeue());
        __CLR4_4_1fbfblpnrioa0.R.inc(603);assertEquals("Three", queue.dequeue());
        __CLR4_4_1fbfblpnrioa0.R.inc(604);assertTrue(queue.isEmpty());

	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	@Test
	public void test1() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),605);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_14e6beugt();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test1",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),605,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_14e6beugt(){try{__CLR4_4_1fbfblpnrioa0.R.inc(605);
		
		__CLR4_4_1fbfblpnrioa0.R.inc(606);BST<Integer, String> bst = new BST<>();

        __CLR4_4_1fbfblpnrioa0.R.inc(607);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(608);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(609);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(610);bst.put(2, "Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(611);bst.put(4, "Four");

        __CLR4_4_1fbfblpnrioa0.R.inc(612);bst.deleteMin();

        __CLR4_4_1fbfblpnrioa0.R.inc(613);assertFalse(bst.contains(2));
        __CLR4_4_1fbfblpnrioa0.R.inc(614);assertEquals(4, bst.size());
        __CLR4_4_1fbfblpnrioa0.R.inc(615);assertEquals("Three", bst.get(3));
        __CLR4_4_1fbfblpnrioa0.R.inc(616);assertEquals("Four", bst.get(4));
        __CLR4_4_1fbfblpnrioa0.R.inc(617);assertEquals("Five", bst.get(5));
        __CLR4_4_1fbfblpnrioa0.R.inc(618);assertEquals("Eight", bst.get(8));

	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	@Test
	public void test2() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),619);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_17n6a7bh7();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test2",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),619,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_17n6a7bh7(){try{__CLR4_4_1fbfblpnrioa0.R.inc(619);
		__CLR4_4_1fbfblpnrioa0.R.inc(620);BST<Integer, String> bst = new BST<>();
		
		__CLR4_4_1fbfblpnrioa0.R.inc(621);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(622);bst.rank(4);
		} catch (Exception e) {}
		__CLR4_4_1fbfblpnrioa0.R.inc(623);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(624);bst.keys(1, null);
		} catch (Exception e) {}
		__CLR4_4_1fbfblpnrioa0.R.inc(625);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(626);bst.keys(null, null);
		} catch (Exception e) {}

        __CLR4_4_1fbfblpnrioa0.R.inc(627);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(628);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(629);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(630);bst.put(2, "Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(631);bst.put(4, "Four");

        __CLR4_4_1fbfblpnrioa0.R.inc(632);bst.deleteMax();

        __CLR4_4_1fbfblpnrioa0.R.inc(633);assertFalse(bst.contains(8));
        __CLR4_4_1fbfblpnrioa0.R.inc(634);assertEquals(4, bst.size());
        __CLR4_4_1fbfblpnrioa0.R.inc(635);assertEquals("Two", bst.get(2));
        __CLR4_4_1fbfblpnrioa0.R.inc(636);assertEquals("Three", bst.get(3));
        __CLR4_4_1fbfblpnrioa0.R.inc(637);assertEquals("Four", bst.get(4));
        __CLR4_4_1fbfblpnrioa0.R.inc(638);assertEquals("Five", bst.get(5));

		
	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	@Test
	public void test3() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),639);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1aw68zshr();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test3",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),639,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1aw68zshr(){try{__CLR4_4_1fbfblpnrioa0.R.inc(639);
		
		__CLR4_4_1fbfblpnrioa0.R.inc(640);BST<Integer, String> bst = new BST<>();

        __CLR4_4_1fbfblpnrioa0.R.inc(641);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(642);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(643);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(644);bst.put(2, "Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(645);bst.put(4, "Four");
        __CLR4_4_1fbfblpnrioa0.R.inc(646);bst.put(41, "aa");
        __CLR4_4_1fbfblpnrioa0.R.inc(647);bst.put(42, "aaa");
        __CLR4_4_1fbfblpnrioa0.R.inc(648);bst.put(43, "aaa");
        __CLR4_4_1fbfblpnrioa0.R.inc(649);bst.put(44, "aaaaa");
        __CLR4_4_1fbfblpnrioa0.R.inc(650);bst.put(45, "aaaa");
        __CLR4_4_1fbfblpnrioa0.R.inc(651);bst.put(441, "aaaaaa");
        __CLR4_4_1fbfblpnrioa0.R.inc(652);bst.put(461, "aaaaaaaa");
        __CLR4_4_1fbfblpnrioa0.R.inc(653);bst.put(431, "aaaaaaaaa");
        __CLR4_4_1fbfblpnrioa0.R.inc(654);bst.put(48, "b");
        __CLR4_4_1fbfblpnrioa0.R.inc(655);bst.put(44, "bb");
        __CLR4_4_1fbfblpnrioa0.R.inc(656);bst.put(466, "bbb");
        __CLR4_4_1fbfblpnrioa0.R.inc(657);bst.put(49, "abbb");
        __CLR4_4_1fbfblpnrioa0.R.inc(658);bst.put(50, "bbbbb");
        __CLR4_4_1fbfblpnrioa0.R.inc(659);bst.put(21, "bbbbbbb");
        __CLR4_4_1fbfblpnrioa0.R.inc(660);bst.put(51, "bbbbbbbbbbb");
        __CLR4_4_1fbfblpnrioa0.R.inc(661);bst.put(52, "bbbbbbbbbbbbbbbb");
        __CLR4_4_1fbfblpnrioa0.R.inc(662);bst.put(53, "c");
        __CLR4_4_1fbfblpnrioa0.R.inc(663);bst.put(586, "cc");
        __CLR4_4_1fbfblpnrioa0.R.inc(664);bst.put(49, "ccc");
        __CLR4_4_1fbfblpnrioa0.R.inc(665);bst.put(464, "cccc");
        __CLR4_4_1fbfblpnrioa0.R.inc(666);bst.put(441, "ccccc");
        __CLR4_4_1fbfblpnrioa0.R.inc(667);bst.put(11, "cccccccccc");
        __CLR4_4_1fbfblpnrioa0.R.inc(668);bst.put(71, "cccccccccccc");
        __CLR4_4_1fbfblpnrioa0.R.inc(669);bst.put(91, "cccccccccccccccc");

        __CLR4_4_1fbfblpnrioa0.R.inc(670);bst.deleteMin();

        __CLR4_4_1fbfblpnrioa0.R.inc(671);assertFalse(bst.contains(2));
        __CLR4_4_1fbfblpnrioa0.R.inc(672);bst.delete(41);
        __CLR4_4_1fbfblpnrioa0.R.inc(673);bst.delete(42);
        __CLR4_4_1fbfblpnrioa0.R.inc(674);bst.delete(43);
        __CLR4_4_1fbfblpnrioa0.R.inc(675);bst.delete(441);
        __CLR4_4_1fbfblpnrioa0.R.inc(676);bst.delete(51);
        __CLR4_4_1fbfblpnrioa0.R.inc(677);bst.delete(11);
        __CLR4_4_1fbfblpnrioa0.R.inc(678);bst.delete(71);
        __CLR4_4_1fbfblpnrioa0.R.inc(679);bst.delete(41);
        __CLR4_4_1fbfblpnrioa0.R.inc(680);bst.delete(41);
        __CLR4_4_1fbfblpnrioa0.R.inc(681);bst.delete(41);
        __CLR4_4_1fbfblpnrioa0.R.inc(682);bst.delete(41);
        __CLR4_4_1fbfblpnrioa0.R.inc(683);bst.delete(41);
        __CLR4_4_1fbfblpnrioa0.R.inc(684);bst.delete(41);
        
        __CLR4_4_1fbfblpnrioa0.R.inc(685);try {
			__CLR4_4_1fbfblpnrioa0.R.inc(686);bst.select(1000000000);
		} catch (Exception e) {}
        __CLR4_4_1fbfblpnrioa0.R.inc(687);try {
        	__CLR4_4_1fbfblpnrioa0.R.inc(688);bst.rank(null);
        } catch (Exception e) {}
        

	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	@Test
	public void test4() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),689);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1e567s9j5();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test4",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),689,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1e567s9j5(){try{__CLR4_4_1fbfblpnrioa0.R.inc(689);
		__CLR4_4_1fbfblpnrioa0.R.inc(690);BST<Integer, String> bst = new BST<>();

        __CLR4_4_1fbfblpnrioa0.R.inc(691);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(692);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(693);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(694);bst.put(2, "Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(695);bst.put(4, "Four");
        __CLR4_4_1fbfblpnrioa0.R.inc(696);bst.floor(3);
        __CLR4_4_1fbfblpnrioa0.R.inc(697);bst.floor(4);
        __CLR4_4_1fbfblpnrioa0.R.inc(698);bst.floor(6);
        
        __CLR4_4_1fbfblpnrioa0.R.inc(699);assertNull(bst.floor(1));
	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	@Test
	public void test5() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),700);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1he66kqjg();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test5",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),700,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1he66kqjg(){try{__CLR4_4_1fbfblpnrioa0.R.inc(700);
		__CLR4_4_1fbfblpnrioa0.R.inc(701);BST<Integer, String> bst = new BST<>();

        __CLR4_4_1fbfblpnrioa0.R.inc(702);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(703);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(704);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(705);bst.put(2, "Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(706);bst.put(4, "Four");

        __CLR4_4_1fbfblpnrioa0.R.inc(707);bst.floor2(3);
        __CLR4_4_1fbfblpnrioa0.R.inc(708);bst.floor2(4);
        __CLR4_4_1fbfblpnrioa0.R.inc(709);bst.floor2(6);

        __CLR4_4_1fbfblpnrioa0.R.inc(710);assertNull(bst.floor2(1));
	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	@Test
	public void test6() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),711);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1kn65d7jr();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test6",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),711,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1kn65d7jr(){try{__CLR4_4_1fbfblpnrioa0.R.inc(711);
		__CLR4_4_1fbfblpnrioa0.R.inc(712);BST<Integer, String> bst = new BST<>();

        __CLR4_4_1fbfblpnrioa0.R.inc(713);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(714);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(715);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(716);bst.put(2, "Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(717);bst.put(4, "Four");
        __CLR4_4_1fbfblpnrioa0.R.inc(718);bst.ceiling(3);
        __CLR4_4_1fbfblpnrioa0.R.inc(719);bst.ceiling(4);
        __CLR4_4_1fbfblpnrioa0.R.inc(720);bst.ceiling(6);

        __CLR4_4_1fbfblpnrioa0.R.inc(721);assertNull(bst.ceiling(9));
	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	
	
	
	
	
	
	
	
	@Test
	public void test9() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),722);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1ue61qmk2();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test9",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),722,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1ue61qmk2(){try{__CLR4_4_1fbfblpnrioa0.R.inc(722);
		__CLR4_4_1fbfblpnrioa0.R.inc(723);BST<Integer, String> bst = new BST<>();

        __CLR4_4_1fbfblpnrioa0.R.inc(724);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(725);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(726);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(727);bst.put(2, "Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(728);bst.put(4, "Four");

        __CLR4_4_1fbfblpnrioa0.R.inc(729);assertEquals(5, bst.size());
        __CLR4_4_1fbfblpnrioa0.R.inc(730);assertEquals(3, bst.size(3, 5));
        
        __CLR4_4_1fbfblpnrioa0.R.inc(731);bst.size(4, 8);

        __CLR4_4_1fbfblpnrioa0.R.inc(732);bst.size(8, 8);
        __CLR4_4_1fbfblpnrioa0.R.inc(733);assertEquals(0, bst.size(10, 15));
	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	
	
	
	
	@Test
	public void test7() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),734);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1nw645oke();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test7",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),734,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1nw645oke(){try{__CLR4_4_1fbfblpnrioa0.R.inc(734);
		__CLR4_4_1fbfblpnrioa0.R.inc(735);BST<Integer, String> bst = new BST<>();

        __CLR4_4_1fbfblpnrioa0.R.inc(736);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(737);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(738);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(739);bst.put(2, "Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(740);bst.put(4, "Four");

        __CLR4_4_1fbfblpnrioa0.R.inc(741);assertEquals(2, bst.height());
	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	@Test
	public void test8() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),742);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1r562y5km();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test8",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),742,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1r562y5km(){try{__CLR4_4_1fbfblpnrioa0.R.inc(742);
		__CLR4_4_1fbfblpnrioa0.R.inc(743);BST<Integer, String> bst = new BST<>();

        __CLR4_4_1fbfblpnrioa0.R.inc(744);bst.put(5, "Five");
        __CLR4_4_1fbfblpnrioa0.R.inc(745);bst.put(3, "Three");
        __CLR4_4_1fbfblpnrioa0.R.inc(746);bst.put(8, "Eight");
        __CLR4_4_1fbfblpnrioa0.R.inc(747);bst.put(2, "Two");
        __CLR4_4_1fbfblpnrioa0.R.inc(748);bst.put(4, "Four");

        __CLR4_4_1fbfblpnrioa0.R.inc(749);Iterable<Integer> keys = bst.levelOrder();
        __CLR4_4_1fbfblpnrioa0.R.inc(750);List<Integer> expectedOrder = Arrays.asList(5, 3, 8, 2, 4);
        __CLR4_4_1fbfblpnrioa0.R.inc(751);List<Integer> actualOrder = new ArrayList<>();
        __CLR4_4_1fbfblpnrioa0.R.inc(752);for (Integer key : keys) {{
            __CLR4_4_1fbfblpnrioa0.R.inc(753);actualOrder.add(key);
        }
        }__CLR4_4_1fbfblpnrioa0.R.inc(754);assertEquals(expectedOrder, actualOrder);
	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	
	
	@Test
	public void test10() {__CLR4_4_1fbfblpnrioa0.R.globalSliceStart(getClass().getName(),755);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1ufo0uwkz();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1fbfblpnrioa0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1fbfblpnrioa0.R.globalSliceEnd(getClass().getName(),"net.mooctest.BSTTest.test10",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),755,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1ufo0uwkz(){try{__CLR4_4_1fbfblpnrioa0.R.inc(755);
		__CLR4_4_1fbfblpnrioa0.R.inc(756);StdOut.println("Hello, world!");

        // \u93b5\u64b3\u5d43\u93c1\u5b58\u669f
        __CLR4_4_1fbfblpnrioa0.R.inc(757);int num = 42;
        __CLR4_4_1fbfblpnrioa0.R.inc(758);StdOut.println(num);

        // \u93b5\u64b3\u5d43\u5a34\ue1be\u5063\u93c1\ufffd
        __CLR4_4_1fbfblpnrioa0.R.inc(759);double pi = 3.14159;
        __CLR4_4_1fbfblpnrioa0.R.inc(760);StdOut.println(pi);

        // \u93cd\u714e\u7d21\u9356\u682c\u7ded\u9351\ufffd
        __CLR4_4_1fbfblpnrioa0.R.inc(761);String name = "Alice";
        __CLR4_4_1fbfblpnrioa0.R.inc(762);int age = 25;
        __CLR4_4_1fbfblpnrioa0.R.inc(763);StdOut.printf("My name is %s and I am %d years old.\n", name, age);
        __CLR4_4_1fbfblpnrioa0.R.inc(764);StdOut.close();
        __CLR4_4_1fbfblpnrioa0.R.inc(765);StdOut.println();
        __CLR4_4_1fbfblpnrioa0.R.inc(766);StdOut.println(true);
        __CLR4_4_1fbfblpnrioa0.R.inc(767);StdOut.println('a');
        __CLR4_4_1fbfblpnrioa0.R.inc(768);StdOut.println(1.01);
        __CLR4_4_1fbfblpnrioa0.R.inc(769);StdOut.println((long)1000000000);
        __CLR4_4_1fbfblpnrioa0.R.inc(770);StdOut.println((short)10);
        __CLR4_4_1fbfblpnrioa0.R.inc(771);StdOut.println((byte)1);
        __CLR4_4_1fbfblpnrioa0.R.inc(772);StdOut.println((short)10);
        __CLR4_4_1fbfblpnrioa0.R.inc(773);StdOut.print();
        __CLR4_4_1fbfblpnrioa0.R.inc(774);StdOut.print(true);
        __CLR4_4_1fbfblpnrioa0.R.inc(775);StdOut.print('a');
        __CLR4_4_1fbfblpnrioa0.R.inc(776);StdOut.print(1.01);
        __CLR4_4_1fbfblpnrioa0.R.inc(777);StdOut.print((long)1000000000);
        __CLR4_4_1fbfblpnrioa0.R.inc(778);StdOut.print((short)10);
        __CLR4_4_1fbfblpnrioa0.R.inc(779);StdOut.print((byte)1);
        __CLR4_4_1fbfblpnrioa0.R.inc(780);StdOut.print((short)10);
        __CLR4_4_1fbfblpnrioa0.R.inc(781);StdOut.print((Object)10);
        
	}finally{__CLR4_4_1fbfblpnrioa0.R.flushNeeded();}}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
