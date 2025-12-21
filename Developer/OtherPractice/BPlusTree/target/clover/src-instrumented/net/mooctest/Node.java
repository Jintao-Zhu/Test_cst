/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents a node in the B+Tree
 * @author	Lidan Hifi
 */
abstract class Node<Value> {public static class __CLR4_4_1avavm7es046x{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0031\u0032\u0033\u005c\u005c\u0031\u0031\u0039\u005c\u005c\u0042\u0050\u006c\u0075\u0073\u0054\u0072\u0065\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1740142646498L,8589935092L,425,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
	protected int t; // tree param
	protected List<Integer> keys;
	
	/*
	 * creates new node, and initialize the keys vector to (t-1) initial capacity
	 * @param totalKeys tree param
	 */
	Node(int totalKeys) {try{__CLR4_4_1avavm7es046x.R.inc(391);
		__CLR4_4_1avavm7es046x.R.inc(392);t = totalKeys;
		__CLR4_4_1avavm7es046x.R.inc(393);keys = new ArrayList<Integer>(t - 1);
	}finally{__CLR4_4_1avavm7es046x.R.flushNeeded();}}
	
	/*
	 * Insert a key & value to the node.
	 * @param key key for insertion
	 * @param value object for insertion (can be integer or string for example, or any class that inherit from Object)
	 * @return insertion result - an object that contains the minimum gap between the inserted key and his siblings keys,
	 * and if the node was split in two parts- contains the new root and pointers to the right node (new one) and left node (old one) 
	 */
	public abstract InsertionResult<Value> insert(int key, Value value);
	
	/*
	 * get the index of the given key in the tree rooted at this node
	 * @param key the given key for searching
	 * @return the index of the given key in the tree rooted at this node (or in the keys vector if it is leaf)
	 */
	public abstract int order(int key);
	
	/*
	 * get the node size- how many keys are stored in
	 * @return the keys vector size
	 */
	public int getNodeSize() {try{__CLR4_4_1avavm7es046x.R.inc(394);
		__CLR4_4_1avavm7es046x.R.inc(395);return keys.size();
	}finally{__CLR4_4_1avavm7es046x.R.flushNeeded();}}
	
	/*
	 * get the index of the key that equals to the given key, or the last key that less than the given key
	 * used in variant of binary search to find the index
	 * @param key the given key for searching
	 * @result index of the key that less than or equal to the given key
	 */
	protected int findLessThanOrEqualToKey(int key) {try{__CLR4_4_1avavm7es046x.R.inc(396);
		// check the right most key 
		// in this case, when the given key is bigger than any other key,
		// the algorithm complexity will be O(1)
		__CLR4_4_1avavm7es046x.R.inc(397);if ((((keys.size() > 0 && keys.get(keys.size() - 1).compareTo(key) < 0)&&(__CLR4_4_1avavm7es046x.R.iget(398)!=0|true))||(__CLR4_4_1avavm7es046x.R.iget(399)==0&false)))
			{__CLR4_4_1avavm7es046x.R.inc(400);return keys.size();
		// check the left most key 
		// in this case, when the given key is smaller than any other key,
		// the algorithm complexity will be O(1)
		}else {__CLR4_4_1avavm7es046x.R.inc(401);if ((((keys.size() > 0 && keys.get(0).compareTo(key) >= 0)&&(__CLR4_4_1avavm7es046x.R.iget(402)!=0|true))||(__CLR4_4_1avavm7es046x.R.iget(403)==0&false)))
			{__CLR4_4_1avavm7es046x.R.inc(404);return 0;
		}else {{
			// find the index by a kind-of binary search
			__CLR4_4_1avavm7es046x.R.inc(405);int from = 0;
			__CLR4_4_1avavm7es046x.R.inc(406);int to = keys.size() - 1;
			
			__CLR4_4_1avavm7es046x.R.inc(407);while ((((from <= to)&&(__CLR4_4_1avavm7es046x.R.iget(408)!=0|true))||(__CLR4_4_1avavm7es046x.R.iget(409)==0&false))) {{
				__CLR4_4_1avavm7es046x.R.inc(410);int mid = (from + to) / 2;
				
				__CLR4_4_1avavm7es046x.R.inc(411);if ((((mid < keys.size() - 1 && keys.get(mid).compareTo(key) < 0 && keys.get(mid + 1).compareTo(key) > 0)&&(__CLR4_4_1avavm7es046x.R.iget(412)!=0|true))||(__CLR4_4_1avavm7es046x.R.iget(413)==0&false)))
					{__CLR4_4_1avavm7es046x.R.inc(414);return mid + 1;
				}else {__CLR4_4_1avavm7es046x.R.inc(415);if ((((keys.get(mid).compareTo(key) > 0)&&(__CLR4_4_1avavm7es046x.R.iget(416)!=0|true))||(__CLR4_4_1avavm7es046x.R.iget(417)==0&false)))
					{__CLR4_4_1avavm7es046x.R.inc(418);to = mid - 1;
				}else {__CLR4_4_1avavm7es046x.R.inc(419);if ((((keys.get(mid).compareTo(key) < 0)&&(__CLR4_4_1avavm7es046x.R.iget(420)!=0|true))||(__CLR4_4_1avavm7es046x.R.iget(421)==0&false)))
					{__CLR4_4_1avavm7es046x.R.inc(422);from = mid + 1;
				}else
					{__CLR4_4_1avavm7es046x.R.inc(423);return mid;
			}}}}
		}}
		
		}}__CLR4_4_1avavm7es046x.R.inc(424);return -1;
	}finally{__CLR4_4_1avavm7es046x.R.flushNeeded();}}
}
