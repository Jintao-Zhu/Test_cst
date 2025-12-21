/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
//author:TheOnlyman
public class ScapegoatTreeTest {static class __CLR4_4_1j1j1l9ktalk0{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0037\u0030\u0032\u0031\u005c\u005c\u0031\u0032\u0032\u0039\u0037\u0033\u005c\u005c\u0053\u0063\u0061\u0070\u0065\u0067\u006f\u0061\u0074\u0054\u0072\u0065\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666497013740L,8589935092L,827,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

	ScapegoatTree abt;
	ScapegoatTree.Node node;
	@Before
	public void setUp() {try{__CLR4_4_1j1j1l9ktalk0.R.inc(685);
		__CLR4_4_1j1j1l9ktalk0.R.inc(686);abt = new ScapegoatTree(0.2);
		__CLR4_4_1j1j1l9ktalk0.R.inc(687);node = null;
	}finally{__CLR4_4_1j1j1l9ktalk0.R.flushNeeded();}}

	@Test
	public void test0() {__CLR4_4_1j1j1l9ktalk0.R.globalSliceStart(getClass().getName(),688);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1156cmdj4();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1j1j1l9ktalk0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1j1j1l9ktalk0.R.globalSliceEnd(getClass().getName(),"net.mooctest.ScapegoatTreeTest.test0",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),688,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1156cmdj4(){try{__CLR4_4_1j1j1l9ktalk0.R.inc(688);
		__CLR4_4_1j1j1l9ktalk0.R.inc(689);node = new AbstractBinarySearchTree.Node(null,null,null,null);
		__CLR4_4_1j1j1l9ktalk0.R.inc(690);node.hashCode();
		__CLR4_4_1j1j1l9ktalk0.R.inc(691);abt.insert(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(692);assertFalse(node.equals(abt.root));
		__CLR4_4_1j1j1l9ktalk0.R.inc(693);assertTrue(node.equals(node));
		__CLR4_4_1j1j1l9ktalk0.R.inc(694);assertTrue(node.equals(new AbstractBinarySearchTree.Node(null,abt.root,null,null)));

		__CLR4_4_1j1j1l9ktalk0.R.inc(695);abt.root = node;

		__CLR4_4_1j1j1l9ktalk0.R.inc(696);try {
			__CLR4_4_1j1j1l9ktalk0.R.inc(697);abt.insert(20);
		} catch (Exception e) {
		}

		__CLR4_4_1j1j1l9ktalk0.R.inc(698);abt.printTreeInOrder();
		__CLR4_4_1j1j1l9ktalk0.R.inc(699);abt.printTree();
		__CLR4_4_1j1j1l9ktalk0.R.inc(700);abt.printTreePostOrder();
		__CLR4_4_1j1j1l9ktalk0.R.inc(701);abt.printTreePreOrder();
	}finally{__CLR4_4_1j1j1l9ktalk0.R.flushNeeded();}}

	@Test
	public void test1() {__CLR4_4_1j1j1l9ktalk0.R.globalSliceStart(getClass().getName(),702);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_14e6beuji();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1j1j1l9ktalk0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1j1j1l9ktalk0.R.globalSliceEnd(getClass().getName(),"net.mooctest.ScapegoatTreeTest.test1",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),702,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_14e6beuji(){try{__CLR4_4_1j1j1l9ktalk0.R.inc(702);

		__CLR4_4_1j1j1l9ktalk0.R.inc(703);abt.insert(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(704);node = abt.search(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(705);assertEquals(new Integer(20),node.value);
		__CLR4_4_1j1j1l9ktalk0.R.inc(706);assertNull(node.parent);

		__CLR4_4_1j1j1l9ktalk0.R.inc(707);node = abt.insert(10);
		__CLR4_4_1j1j1l9ktalk0.R.inc(708);assertEquals(new Integer(20),node.parent.value);
		__CLR4_4_1j1j1l9ktalk0.R.inc(709);assertEquals(node, node.parent.left);

		__CLR4_4_1j1j1l9ktalk0.R.inc(710);node = abt.insert(30);
		__CLR4_4_1j1j1l9ktalk0.R.inc(711);assertEquals(new Integer(20),node.parent.value);
		__CLR4_4_1j1j1l9ktalk0.R.inc(712);assertEquals(node, node.parent.right);

		__CLR4_4_1j1j1l9ktalk0.R.inc(713);node = abt.search(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(714);assertNotNull(node);
		__CLR4_4_1j1j1l9ktalk0.R.inc(715);assertEquals(new Integer(20), node.value);

		__CLR4_4_1j1j1l9ktalk0.R.inc(716);node = abt.search(10);
		__CLR4_4_1j1j1l9ktalk0.R.inc(717);assertNotNull(node);
		__CLR4_4_1j1j1l9ktalk0.R.inc(718);assertEquals(new Integer(10), node.value);

		__CLR4_4_1j1j1l9ktalk0.R.inc(719);node = abt.search(11);
		__CLR4_4_1j1j1l9ktalk0.R.inc(720);assertNull(node);

	}finally{__CLR4_4_1j1j1l9ktalk0.R.flushNeeded();}}

	@Test
	public void test2() {__CLR4_4_1j1j1l9ktalk0.R.globalSliceStart(getClass().getName(),721);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_17n6a7bk1();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1j1j1l9ktalk0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1j1j1l9ktalk0.R.globalSliceEnd(getClass().getName(),"net.mooctest.ScapegoatTreeTest.test2",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),721,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_17n6a7bk1(){try{__CLR4_4_1j1j1l9ktalk0.R.inc(721);

		__CLR4_4_1j1j1l9ktalk0.R.inc(722);abt.insert(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(723);node = abt.search(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(724);node = abt.insert(10);
		__CLR4_4_1j1j1l9ktalk0.R.inc(725);node = abt.insert(30);
		__CLR4_4_1j1j1l9ktalk0.R.inc(726);abt.getSuccessor(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(727);assertTrue(abt.contains(20));
		__CLR4_4_1j1j1l9ktalk0.R.inc(728);assertFalse(abt.contains(11));
		__CLR4_4_1j1j1l9ktalk0.R.inc(729);assertEquals(3,abt.getSize());
		__CLR4_4_1j1j1l9ktalk0.R.inc(730);try {
			__CLR4_4_1j1j1l9ktalk0.R.inc(731);abt.getSuccessor(30);
		} catch (Exception e) {
		}
		__CLR4_4_1j1j1l9ktalk0.R.inc(732);abt.printTree();
		__CLR4_4_1j1j1l9ktalk0.R.inc(733);assertTrue(node.equals(new AbstractBinarySearchTree.Node(30,abt.root,null,null)));
		__CLR4_4_1j1j1l9ktalk0.R.inc(734);assertFalse(abt.root.isLeaf());
		__CLR4_4_1j1j1l9ktalk0.R.inc(735);node.hashCode();
		__CLR4_4_1j1j1l9ktalk0.R.inc(736);assertFalse(node.equals(null));
		__CLR4_4_1j1j1l9ktalk0.R.inc(737);assertFalse(node.equals(abt.root));
		__CLR4_4_1j1j1l9ktalk0.R.inc(738);assertTrue(node.equals(node));
		__CLR4_4_1j1j1l9ktalk0.R.inc(739);assertFalse(node.equals(new AbstractBinarySearchTree.Node(null,null,null,null)));
		__CLR4_4_1j1j1l9ktalk0.R.inc(740);assertFalse(node.equals(abt));

		__CLR4_4_1j1j1l9ktalk0.R.inc(741);abt.delete(null);
		__CLR4_4_1j1j1l9ktalk0.R.inc(742);abt.delete(11);
		__CLR4_4_1j1j1l9ktalk0.R.inc(743);abt.delete(20);

	}finally{__CLR4_4_1j1j1l9ktalk0.R.flushNeeded();}}

	@Test
	public void test3() {__CLR4_4_1j1j1l9ktalk0.R.globalSliceStart(getClass().getName(),744);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1aw68zsko();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1j1j1l9ktalk0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1j1j1l9ktalk0.R.globalSliceEnd(getClass().getName(),"net.mooctest.ScapegoatTreeTest.test3",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),744,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1aw68zsko(){try{__CLR4_4_1j1j1l9ktalk0.R.inc(744);

		__CLR4_4_1j1j1l9ktalk0.R.inc(745);abt.insert(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(746);node = abt.search(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(747);node = abt.insert(10);
		__CLR4_4_1j1j1l9ktalk0.R.inc(748);abt.printTree();
		__CLR4_4_1j1j1l9ktalk0.R.inc(749);assertFalse(node.equals(new AbstractBinarySearchTree.Node(30,abt.root,null,null)));
		__CLR4_4_1j1j1l9ktalk0.R.inc(750);assertFalse(abt.root.isLeaf());
		__CLR4_4_1j1j1l9ktalk0.R.inc(751);node.hashCode();
		__CLR4_4_1j1j1l9ktalk0.R.inc(752);assertFalse(node.equals(null));
		__CLR4_4_1j1j1l9ktalk0.R.inc(753);assertFalse(node.equals(abt.root));
		__CLR4_4_1j1j1l9ktalk0.R.inc(754);assertTrue(node.equals(node));
		__CLR4_4_1j1j1l9ktalk0.R.inc(755);assertFalse(node.equals(new AbstractBinarySearchTree.Node(null,null,null,null)));
		__CLR4_4_1j1j1l9ktalk0.R.inc(756);assertFalse(node.equals(abt));

		__CLR4_4_1j1j1l9ktalk0.R.inc(757);abt.delete(null);
		__CLR4_4_1j1j1l9ktalk0.R.inc(758);abt.delete(11);
		__CLR4_4_1j1j1l9ktalk0.R.inc(759);abt.delete(20);

	}finally{__CLR4_4_1j1j1l9ktalk0.R.flushNeeded();}}

	@Test
	public void test4() {__CLR4_4_1j1j1l9ktalk0.R.globalSliceStart(getClass().getName(),760);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1e567s9l4();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1j1j1l9ktalk0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1j1j1l9ktalk0.R.globalSliceEnd(getClass().getName(),"net.mooctest.ScapegoatTreeTest.test4",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),760,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1e567s9l4(){try{__CLR4_4_1j1j1l9ktalk0.R.inc(760);

		__CLR4_4_1j1j1l9ktalk0.R.inc(761);abt.insert(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(762);node = abt.search(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(763);node = abt.insert(30);
		__CLR4_4_1j1j1l9ktalk0.R.inc(764);abt.insert(25);
		__CLR4_4_1j1j1l9ktalk0.R.inc(765);abt.printTree();
		__CLR4_4_1j1j1l9ktalk0.R.inc(766);assertTrue(node.equals(new AbstractBinarySearchTree.Node(30,abt.root,null,null)));
		__CLR4_4_1j1j1l9ktalk0.R.inc(767);assertFalse(abt.root.isLeaf());
		__CLR4_4_1j1j1l9ktalk0.R.inc(768);node.hashCode();
		__CLR4_4_1j1j1l9ktalk0.R.inc(769);assertFalse(node.equals(null));
		__CLR4_4_1j1j1l9ktalk0.R.inc(770);assertTrue(node.equals(abt.root));
		__CLR4_4_1j1j1l9ktalk0.R.inc(771);assertTrue(node.equals(node));
		__CLR4_4_1j1j1l9ktalk0.R.inc(772);assertFalse(node.equals(new AbstractBinarySearchTree.Node(null,null,null,null)));
		__CLR4_4_1j1j1l9ktalk0.R.inc(773);assertFalse(node.equals(abt));

		__CLR4_4_1j1j1l9ktalk0.R.inc(774);abt.delete(null);
		__CLR4_4_1j1j1l9ktalk0.R.inc(775);abt.delete(11);
		__CLR4_4_1j1j1l9ktalk0.R.inc(776);abt.delete(20);

	}finally{__CLR4_4_1j1j1l9ktalk0.R.flushNeeded();}}

	@Test
	public void test5() {__CLR4_4_1j1j1l9ktalk0.R.globalSliceStart(getClass().getName(),777);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1he66kqll();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1j1j1l9ktalk0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1j1j1l9ktalk0.R.globalSliceEnd(getClass().getName(),"net.mooctest.ScapegoatTreeTest.test5",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),777,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1he66kqll(){try{__CLR4_4_1j1j1l9ktalk0.R.inc(777);
		__CLR4_4_1j1j1l9ktalk0.R.inc(778);abt = new ScapegoatTree();
		__CLR4_4_1j1j1l9ktalk0.R.inc(779);abt.insert(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(780);assertNull(abt.findScapegoatNode(abt.root));
		__CLR4_4_1j1j1l9ktalk0.R.inc(781);assertEquals(-1,abt.getNodeHeight(null));
		__CLR4_4_1j1j1l9ktalk0.R.inc(782);node = abt.search(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(783);assertEquals(new Integer(20),node.value);
		__CLR4_4_1j1j1l9ktalk0.R.inc(784);assertNull(node.parent);

		__CLR4_4_1j1j1l9ktalk0.R.inc(785);node = abt.insert(10);
		__CLR4_4_1j1j1l9ktalk0.R.inc(786);assertEquals(new Integer(20),node.parent.value);
		__CLR4_4_1j1j1l9ktalk0.R.inc(787);assertEquals(node, node.parent.left);

		__CLR4_4_1j1j1l9ktalk0.R.inc(788);node = abt.insert(30);
		__CLR4_4_1j1j1l9ktalk0.R.inc(789);assertEquals(new Integer(20),node.parent.value);
		__CLR4_4_1j1j1l9ktalk0.R.inc(790);assertEquals(node, node.parent.right);

		__CLR4_4_1j1j1l9ktalk0.R.inc(791);node = abt.search(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(792);assertNotNull(node);
		__CLR4_4_1j1j1l9ktalk0.R.inc(793);assertEquals(new Integer(20), node.value);

		__CLR4_4_1j1j1l9ktalk0.R.inc(794);node = abt.search(10);
		__CLR4_4_1j1j1l9ktalk0.R.inc(795);assertNotNull(node);
		__CLR4_4_1j1j1l9ktalk0.R.inc(796);assertEquals(new Integer(10), node.value);

		__CLR4_4_1j1j1l9ktalk0.R.inc(797);node = abt.search(11);
		__CLR4_4_1j1j1l9ktalk0.R.inc(798);assertNull(node);

	}finally{__CLR4_4_1j1j1l9ktalk0.R.flushNeeded();}}

	@Test
	public void cornerTest() {__CLR4_4_1j1j1l9ktalk0.R.globalSliceStart(getClass().getName(),799);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1nesoiam7();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1j1j1l9ktalk0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1j1j1l9ktalk0.R.globalSliceEnd(getClass().getName(),"net.mooctest.ScapegoatTreeTest.cornerTest",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),799,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1nesoiam7(){try{__CLR4_4_1j1j1l9ktalk0.R.inc(799);
		__CLR4_4_1j1j1l9ktalk0.R.inc(800);abt = new ScapegoatTree(0.9);
		__CLR4_4_1j1j1l9ktalk0.R.inc(801);abt.insert(20);
		__CLR4_4_1j1j1l9ktalk0.R.inc(802);abt.delete(20);
	}finally{__CLR4_4_1j1j1l9ktalk0.R.flushNeeded();}}

	@Test
	public void randomTest() {__CLR4_4_1j1j1l9ktalk0.R.globalSliceStart(getClass().getName(),803);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1occ718mb();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1j1j1l9ktalk0.R.rethrow($CLV_t2$);}finally{__CLR4_4_1j1j1l9ktalk0.R.globalSliceEnd(getClass().getName(),"net.mooctest.ScapegoatTreeTest.randomTest",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),803,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1occ718mb(){try{__CLR4_4_1j1j1l9ktalk0.R.inc(803);
		__CLR4_4_1j1j1l9ktalk0.R.inc(804);int n = 100;
		__CLR4_4_1j1j1l9ktalk0.R.inc(805);Random rnd = new Random(998244353);
		__CLR4_4_1j1j1l9ktalk0.R.inc(806);for(int i=1;(((i<=10)&&(__CLR4_4_1j1j1l9ktalk0.R.iget(807)!=0|true))||(__CLR4_4_1j1j1l9ktalk0.R.iget(808)==0&false));i++) {{
			__CLR4_4_1j1j1l9ktalk0.R.inc(809);Random random = new Random(rnd.nextInt(100));
			__CLR4_4_1j1j1l9ktalk0.R.inc(810);for (int j=1;(((j<=100)&&(__CLR4_4_1j1j1l9ktalk0.R.iget(811)!=0|true))||(__CLR4_4_1j1j1l9ktalk0.R.iget(812)==0&false));j++) {{
				__CLR4_4_1j1j1l9ktalk0.R.inc(813);node = abt.insert(random.nextInt(n));
				__CLR4_4_1j1j1l9ktalk0.R.inc(814);node.isLeaf();
			}
			}__CLR4_4_1j1j1l9ktalk0.R.inc(815);abt.root.isLeaf();
			__CLR4_4_1j1j1l9ktalk0.R.inc(816);for (int j=1;(((j<=100)&&(__CLR4_4_1j1j1l9ktalk0.R.iget(817)!=0|true))||(__CLR4_4_1j1j1l9ktalk0.R.iget(818)==0&false));j++) {{
				__CLR4_4_1j1j1l9ktalk0.R.inc(819);try {
					__CLR4_4_1j1j1l9ktalk0.R.inc(820);abt.search(random.nextInt(n));
				} catch (Exception e) {
				}
				__CLR4_4_1j1j1l9ktalk0.R.inc(821);try {
					__CLR4_4_1j1j1l9ktalk0.R.inc(822);abt.delete(random.nextInt(n));
				} catch (Exception e) {
				}
				__CLR4_4_1j1j1l9ktalk0.R.inc(823);abt.getMinimum();
				__CLR4_4_1j1j1l9ktalk0.R.inc(824);abt.getMaximum();
				__CLR4_4_1j1j1l9ktalk0.R.inc(825);try {
					__CLR4_4_1j1j1l9ktalk0.R.inc(826);abt.getSuccessor(abt.root);
				} catch (Exception e) {
				}
			}
		}}
	}}finally{__CLR4_4_1j1j1l9ktalk0.R.flushNeeded();}}

}
