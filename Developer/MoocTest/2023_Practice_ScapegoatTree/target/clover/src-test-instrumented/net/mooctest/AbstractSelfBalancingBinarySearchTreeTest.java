/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
//author:TheOnlyman
public class AbstractSelfBalancingBinarySearchTreeTest {static class __CLR4_4_1f7f7l9ktalhh{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0037\u0030\u0032\u0031\u005c\u005c\u0031\u0032\u0032\u0039\u0037\u0033\u005c\u005c\u0053\u0063\u0061\u0070\u0065\u0067\u006f\u0061\u0074\u0054\u0072\u0065\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666497013740L,8589935092L,673,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

	AbstractSelfBalancingBinarySearchTree abt;
	AbstractBinarySearchTree.Node node;
	@Before
	public void setUp() {try{__CLR4_4_1f7f7l9ktalhh.R.inc(547);
		__CLR4_4_1f7f7l9ktalhh.R.inc(548);abt = new AbstractSelfBalancingBinarySearchTree() {
			@Override
			protected Node createNode(int value, Node parent, Node left, Node right) {try{__CLR4_4_1f7f7l9ktalhh.R.inc(549);
				__CLR4_4_1f7f7l9ktalhh.R.inc(550);return new Node(value,parent,left,right);
			}finally{__CLR4_4_1f7f7l9ktalhh.R.flushNeeded();}}
		};
		__CLR4_4_1f7f7l9ktalhh.R.inc(551);node = null;
	}finally{__CLR4_4_1f7f7l9ktalhh.R.flushNeeded();}}

	@Test
	public void test() {__CLR4_4_1f7f7l9ktalhh.R.globalSliceStart(getClass().getName(),552);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1zi4lt3fc();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1f7f7l9ktalhh.R.rethrow($CLV_t2$);}finally{__CLR4_4_1f7f7l9ktalhh.R.globalSliceEnd(getClass().getName(),"net.mooctest.AbstractSelfBalancingBinarySearchTreeTest.test",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),552,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1zi4lt3fc(){try{__CLR4_4_1f7f7l9ktalhh.R.inc(552);
		__CLR4_4_1f7f7l9ktalhh.R.inc(553);ScapegoatTree scapegoatTree0 = new ScapegoatTree();
		__CLR4_4_1f7f7l9ktalhh.R.inc(554);AbstractBinarySearchTree.Node abstractBinarySearchTree_Node0 = scapegoatTree0.createNode((-3252),
				(AbstractBinarySearchTree.Node) null, (AbstractBinarySearchTree.Node) null,
				(AbstractBinarySearchTree.Node) null);
		__CLR4_4_1f7f7l9ktalhh.R.inc(555);AbstractBinarySearchTree.Node abstractBinarySearchTree_Node1 = new AbstractBinarySearchTree.Node(
				abstractBinarySearchTree_Node0.value, abstractBinarySearchTree_Node0, abstractBinarySearchTree_Node0,
				abstractBinarySearchTree_Node0);
		__CLR4_4_1f7f7l9ktalhh.R.inc(556);AbstractBinarySearchTree.Node abstractBinarySearchTree_Node2 = scapegoatTree0
				.rotateRight(abstractBinarySearchTree_Node1);
		__CLR4_4_1f7f7l9ktalhh.R.inc(557);scapegoatTree0.rotateLeft(abstractBinarySearchTree_Node1);
		__CLR4_4_1f7f7l9ktalhh.R.inc(558);AbstractBinarySearchTree.Node abstractBinarySearchTree_Node3 = scapegoatTree0
				.rotateRight(abstractBinarySearchTree_Node2);
		__CLR4_4_1f7f7l9ktalhh.R.inc(559);assertTrue(abstractBinarySearchTree_Node1.isLeaf());
		__CLR4_4_1f7f7l9ktalhh.R.inc(560);assertSame(abstractBinarySearchTree_Node0, abstractBinarySearchTree_Node3);
	}finally{__CLR4_4_1f7f7l9ktalhh.R.flushNeeded();}}

	@Test
	public void test0() {__CLR4_4_1f7f7l9ktalhh.R.globalSliceStart(getClass().getName(),561);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1156cmdfl();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1f7f7l9ktalhh.R.rethrow($CLV_t2$);}finally{__CLR4_4_1f7f7l9ktalhh.R.globalSliceEnd(getClass().getName(),"net.mooctest.AbstractSelfBalancingBinarySearchTreeTest.test0",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),561,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1156cmdfl(){try{__CLR4_4_1f7f7l9ktalhh.R.inc(561);

	}finally{__CLR4_4_1f7f7l9ktalhh.R.flushNeeded();}}

	@Test
	public void randomTest() {__CLR4_4_1f7f7l9ktalhh.R.globalSliceStart(getClass().getName(),562);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1occ718fm();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1f7f7l9ktalhh.R.rethrow($CLV_t2$);}finally{__CLR4_4_1f7f7l9ktalhh.R.globalSliceEnd(getClass().getName(),"net.mooctest.AbstractSelfBalancingBinarySearchTreeTest.randomTest",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),562,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1occ718fm(){try{__CLR4_4_1f7f7l9ktalhh.R.inc(562);
		__CLR4_4_1f7f7l9ktalhh.R.inc(563);int n = 100;
		__CLR4_4_1f7f7l9ktalhh.R.inc(564);Random rnd = new Random(998244353);
		__CLR4_4_1f7f7l9ktalhh.R.inc(565);for(int i=1;(((i<=10)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(566)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(567)==0&false));i++) {{
			__CLR4_4_1f7f7l9ktalhh.R.inc(568);Random random = new Random(rnd.nextInt(100));
			__CLR4_4_1f7f7l9ktalhh.R.inc(569);for (int j=1;(((j<=1000)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(570)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(571)==0&false));j++) {{
				__CLR4_4_1f7f7l9ktalhh.R.inc(572);node = abt.insert(random.nextInt(n));
				__CLR4_4_1f7f7l9ktalhh.R.inc(573);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(574);abt.rotateLeft(abt.root);
				} catch (Exception e) {
				}
				__CLR4_4_1f7f7l9ktalhh.R.inc(575);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(576);abt.rotateLeft(abt.root.left);
				} catch (Exception e) {
				}
				__CLR4_4_1f7f7l9ktalhh.R.inc(577);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(578);abt.rotateLeft(abt.root.right);
				} catch (Exception e) {
				}
				__CLR4_4_1f7f7l9ktalhh.R.inc(579);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(580);abt.rotateRight(abt.root);
				} catch (Exception e) {
				}
			}
			}__CLR4_4_1f7f7l9ktalhh.R.inc(581);abt.root.isLeaf();
			__CLR4_4_1f7f7l9ktalhh.R.inc(582);for (int j=1;(((j<=1000)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(583)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(584)==0&false));j++) {{
				__CLR4_4_1f7f7l9ktalhh.R.inc(585);abt.search(random.nextInt(n));
				__CLR4_4_1f7f7l9ktalhh.R.inc(586);abt.delete(random.nextInt(n));
				__CLR4_4_1f7f7l9ktalhh.R.inc(587);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(588);abt.rotateLeft(abt.root);
				} catch (Exception e) {
				}
				__CLR4_4_1f7f7l9ktalhh.R.inc(589);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(590);abt.rotateLeft(abt.root.left);
				} catch (Exception e) {
				}
				__CLR4_4_1f7f7l9ktalhh.R.inc(591);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(592);abt.rotateLeft(abt.root.right);
				} catch (Exception e) {
				}
				__CLR4_4_1f7f7l9ktalhh.R.inc(593);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(594);abt.rotateRight(abt.root);
				} catch (Exception e) {
				}
			}
		}}
	}}finally{__CLR4_4_1f7f7l9ktalhh.R.flushNeeded();}}

	@Test(timeout=10000)
	public void test1() {__CLR4_4_1f7f7l9ktalhh.R.globalSliceStart(getClass().getName(),595);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_14e6beugj();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1f7f7l9ktalhh.R.rethrow($CLV_t2$);}finally{__CLR4_4_1f7f7l9ktalhh.R.globalSliceEnd(getClass().getName(),"net.mooctest.AbstractSelfBalancingBinarySearchTreeTest.test1",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),595,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_14e6beugj(){try{__CLR4_4_1f7f7l9ktalhh.R.inc(595);
		__CLR4_4_1f7f7l9ktalhh.R.inc(596);for(int i=1;(((i<=10)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(597)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(598)==0&false));++i) {{
			__CLR4_4_1f7f7l9ktalhh.R.inc(599);ScapegoatTree scapegoatTree0 = new ScapegoatTree();
			__CLR4_4_1f7f7l9ktalhh.R.inc(600);ScapegoatTree.Node [] nod = new ScapegoatTree.Node[105];
			__CLR4_4_1f7f7l9ktalhh.R.inc(601);for(int j=1;(((j<=100)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(602)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(603)==0&false));++j) {{
				__CLR4_4_1f7f7l9ktalhh.R.inc(604);nod[j]=scapegoatTree0.createNode(j%17, null, null, null);
				__CLR4_4_1f7f7l9ktalhh.R.inc(605);scapegoatTree0.createNode(j%13, nod[j], null, null);
				__CLR4_4_1f7f7l9ktalhh.R.inc(606);scapegoatTree0.insert(j%7);
				__CLR4_4_1f7f7l9ktalhh.R.inc(607);scapegoatTree0.delete(j%11);
				__CLR4_4_1f7f7l9ktalhh.R.inc(608);try {

					__CLR4_4_1f7f7l9ktalhh.R.inc(609);if((((scapegoatTree0.contains(j))&&(__CLR4_4_1f7f7l9ktalhh.R.iget(610)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(611)==0&false))) {{}
				}}catch(Exception e) {

				}
			}
			}__CLR4_4_1f7f7l9ktalhh.R.inc(612);try {
				__CLR4_4_1f7f7l9ktalhh.R.inc(613);int szz=scapegoatTree0.size;
				__CLR4_4_1f7f7l9ktalhh.R.inc(614);int mxx=scapegoatTree0.getMaximum();
				__CLR4_4_1f7f7l9ktalhh.R.inc(615);int mii=scapegoatTree0.getMinimum();
			}catch(Exception e) {

			}
			__CLR4_4_1f7f7l9ktalhh.R.inc(616);for(int j=5;(((j<=100)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(617)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(618)==0&false));++j) {{
				__CLR4_4_1f7f7l9ktalhh.R.inc(619);if((((scapegoatTree0.root==nod[j])&&(__CLR4_4_1f7f7l9ktalhh.R.iget(620)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(621)==0&false))) {{}
				}__CLR4_4_1f7f7l9ktalhh.R.inc(622);if((((nod[j].parent==null)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(623)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(624)==0&false))) {{
					__CLR4_4_1f7f7l9ktalhh.R.inc(625);int f=j%4+1;
					__CLR4_4_1f7f7l9ktalhh.R.inc(626);if((((nod[f].left==null)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(627)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(628)==0&false))) {{
						__CLR4_4_1f7f7l9ktalhh.R.inc(629);nod[f].left=nod[j];
						__CLR4_4_1f7f7l9ktalhh.R.inc(630);nod[j].parent=nod[f];
					}}else {__CLR4_4_1f7f7l9ktalhh.R.inc(631);if((((nod[f].right==null)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(632)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(633)==0&false))) {{
						__CLR4_4_1f7f7l9ktalhh.R.inc(634);nod[f].right=nod[j];
						__CLR4_4_1f7f7l9ktalhh.R.inc(635);nod[j].parent=nod[f];
					}
					}}__CLR4_4_1f7f7l9ktalhh.R.inc(636);if((((nod[j].isLeaf())&&(__CLR4_4_1f7f7l9ktalhh.R.iget(637)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(638)==0&false))) {{
					}
				}}
				}__CLR4_4_1f7f7l9ktalhh.R.inc(639);scapegoatTree0.printTree();
				__CLR4_4_1f7f7l9ktalhh.R.inc(640);scapegoatTree0.printTreeInOrder();
				__CLR4_4_1f7f7l9ktalhh.R.inc(641);scapegoatTree0.printTreePostOrder();
				__CLR4_4_1f7f7l9ktalhh.R.inc(642);scapegoatTree0.printTreePreOrder();
			}
			}__CLR4_4_1f7f7l9ktalhh.R.inc(643);try {
				__CLR4_4_1f7f7l9ktalhh.R.inc(644);for(int j=1;(((j<=10)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(645)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(646)==0&false));++j) {{
					__CLR4_4_1f7f7l9ktalhh.R.inc(647);scapegoatTree0.printSubtree(nod[j]);
				}
			}}catch(Exception e2) {

			}
		}
	}}finally{__CLR4_4_1f7f7l9ktalhh.R.flushNeeded();}}
	@Test(timeout=10000)
	public void test3() {__CLR4_4_1f7f7l9ktalhh.R.globalSliceStart(getClass().getName(),648);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1aw68zsi0();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1f7f7l9ktalhh.R.rethrow($CLV_t2$);}finally{__CLR4_4_1f7f7l9ktalhh.R.globalSliceEnd(getClass().getName(),"net.mooctest.AbstractSelfBalancingBinarySearchTreeTest.test3",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),648,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1aw68zsi0(){try{__CLR4_4_1f7f7l9ktalhh.R.inc(648);
		__CLR4_4_1f7f7l9ktalhh.R.inc(649);for(int i=1;(((i<=10)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(650)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(651)==0&false));++i) {{
			__CLR4_4_1f7f7l9ktalhh.R.inc(652);ScapegoatTree scapegoatTree0 = new ScapegoatTree();
			__CLR4_4_1f7f7l9ktalhh.R.inc(653);for(int j=1;(((j<=1000)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(654)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(655)==0&false));++j) {{
				__CLR4_4_1f7f7l9ktalhh.R.inc(656);scapegoatTree0.insert(j%7);
				__CLR4_4_1f7f7l9ktalhh.R.inc(657);scapegoatTree0.delete(j%11);
				__CLR4_4_1f7f7l9ktalhh.R.inc(658);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(659);if((((scapegoatTree0.contains(j))&&(__CLR4_4_1f7f7l9ktalhh.R.iget(660)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(661)==0&false))) {{}
				}}catch(Exception e) {

				}
			}
			}__CLR4_4_1f7f7l9ktalhh.R.inc(662);try {
				__CLR4_4_1f7f7l9ktalhh.R.inc(663);int szz=scapegoatTree0.size;
				__CLR4_4_1f7f7l9ktalhh.R.inc(664);int mxx=scapegoatTree0.getMaximum();
				__CLR4_4_1f7f7l9ktalhh.R.inc(665);int mii=scapegoatTree0.getMinimum();
			}catch(Exception e) {

			}
			__CLR4_4_1f7f7l9ktalhh.R.inc(666);for(int j=1;(((j<=1000)&&(__CLR4_4_1f7f7l9ktalhh.R.iget(667)!=0|true))||(__CLR4_4_1f7f7l9ktalhh.R.iget(668)==0&false));++j) {{
				__CLR4_4_1f7f7l9ktalhh.R.inc(669);try {
					__CLR4_4_1f7f7l9ktalhh.R.inc(670);scapegoatTree0.contains(j);
					__CLR4_4_1f7f7l9ktalhh.R.inc(671);scapegoatTree0.getSuccessor(j);
					__CLR4_4_1f7f7l9ktalhh.R.inc(672);scapegoatTree0.delete(j);
				}catch(Exception e) {

				}
			}
		}}
	}}finally{__CLR4_4_1f7f7l9ktalhh.R.flushNeeded();}}

}
