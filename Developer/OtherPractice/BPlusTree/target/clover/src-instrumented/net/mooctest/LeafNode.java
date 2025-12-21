/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents an internal node in the B+Tree
 * @author	Lidan Hifi
 */
class LeafNode<Value> extends Node<Value> {public static class __CLR4_4_17l7lm7es045k{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0031\u0032\u0033\u005c\u005c\u0031\u0031\u0039\u005c\u005c\u0042\u0050\u006c\u0075\u0073\u0054\u0072\u0065\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1740142646498L,8589935092L,391,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
	// values vector (each index in the keys vector is matching another index in the values vector)
	private List<Value> values;
	// pointers to the next leaf and the previous leaf
	private LeafNode<Value> next;
	private LeafNode<Value> prev;
	
	/*
	 * creates new leaf node
	 * @param totalKeys tree param
	 */
	public LeafNode(int totalKeys) {
		super(totalKeys);__CLR4_4_17l7lm7es045k.R.inc(274);try{__CLR4_4_17l7lm7es045k.R.inc(273);
		__CLR4_4_17l7lm7es045k.R.inc(275);values = new ArrayList<Value>(t - 1);
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * creates new leaf node split from a previous leaf node
	 * @param totalKeys tree param
	 * @param prev previous leaf
	 */
	public LeafNode(int totalKeys, LeafNode<Value> prev) {
		this(totalKeys);__CLR4_4_17l7lm7es045k.R.inc(277);try{__CLR4_4_17l7lm7es045k.R.inc(276);
		__CLR4_4_17l7lm7es045k.R.inc(278);this.prev = prev;
		__CLR4_4_17l7lm7es045k.R.inc(279);this.next = prev.next;
		__CLR4_4_17l7lm7es045k.R.inc(280);if ((((this.next != null)&&(__CLR4_4_17l7lm7es045k.R.iget(281)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(282)==0&false)))
			{__CLR4_4_17l7lm7es045k.R.inc(283);this.next.prev = this;
		
		}__CLR4_4_17l7lm7es045k.R.inc(284);prev.next = this;
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * creates new leaf node split from a previous leaf node, with a given keys and values
	 * @param totalKeys tree param
	 * @param prev previous leaf
	 * @param keys keys list
	 * @param values values list
	 */
	public LeafNode(int totalKeys, LeafNode<Value> prev, List<Integer> keys, List<Value> values) {
		this(totalKeys, prev);__CLR4_4_17l7lm7es045k.R.inc(286);try{__CLR4_4_17l7lm7es045k.R.inc(285);
		__CLR4_4_17l7lm7es045k.R.inc(287);this.keys.addAll(keys);
		__CLR4_4_17l7lm7es045k.R.inc(288);this.values.addAll(values);
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * (non-Javadoc)
	 * @see data.Node#order(int)
	 */
	public int order(int key) {try{__CLR4_4_17l7lm7es045k.R.inc(289);
		__CLR4_4_17l7lm7es045k.R.inc(290);return keys.indexOf(key);
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * get the value that matches a given key
	 * @param key key for searching
	 * @return value if the key was found, null if not
	 */
	public Value getValue(int key) {try{__CLR4_4_17l7lm7es045k.R.inc(291);
		// find the index of the given key
		__CLR4_4_17l7lm7es045k.R.inc(292);int index = order(key);
		
		// if the index was found, return the matched value
		__CLR4_4_17l7lm7es045k.R.inc(293);if ((((index != -1)&&(__CLR4_4_17l7lm7es045k.R.iget(294)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(295)==0&false)))
			{__CLR4_4_17l7lm7es045k.R.inc(296);return values.get(index);
		}else
			{__CLR4_4_17l7lm7es045k.R.inc(297);return null;
	}}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * insert key,value to a non-full leaf node
	 * @param key key
	 * @param value value
	 */
	private InsertionResult<Value> insertNonFull(int key, Value value) {try{__CLR4_4_17l7lm7es045k.R.inc(298);
		__CLR4_4_17l7lm7es045k.R.inc(299);return insertNonFull(key, value, findLessThanOrEqualToKey(key));
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * insert key,value to a non-full leaf node
	 * @param key key
	 * @param value value
	 */
	private InsertionResult<Value> insertNonFull(int key, Value value, int index) {try{__CLR4_4_17l7lm7es045k.R.inc(300);
		__CLR4_4_17l7lm7es045k.R.inc(301);if ((((keys.isEmpty() || (index == keys.size() - 1 && keys.get(index).compareTo(key) < 0))&&(__CLR4_4_17l7lm7es045k.R.iget(302)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(303)==0&false))) {{
			// keys list is empty or insert as right most
			__CLR4_4_17l7lm7es045k.R.inc(304);keys.add(key);
			__CLR4_4_17l7lm7es045k.R.inc(305);values.add(value);
		} }else {{
			// insert in the middle of the list
			__CLR4_4_17l7lm7es045k.R.inc(306);keys.add(index, key);
			__CLR4_4_17l7lm7es045k.R.inc(307);values.add(index, value);
		}
		
		// return insertion result that contains the minimum gap between the given key and his siblings
		}__CLR4_4_17l7lm7es045k.R.inc(308);return new InsertionResult<Value>(calculateGap(index));
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * (non-Javadoc)
	 * @see data.Node#insert(int, java.lang.Object)
	 */
	public InsertionResult<Value> insert(int key, Value value) {try{__CLR4_4_17l7lm7es045k.R.inc(309);
		// find the index for insertion
		__CLR4_4_17l7lm7es045k.R.inc(310);int indexForInsertion = findLessThanOrEqualToKey(key);
		__CLR4_4_17l7lm7es045k.R.inc(311);InsertionResult<Value> resultFromNonFullInsertion = null;
		
		__CLR4_4_17l7lm7es045k.R.inc(312);if ((((indexForInsertion != -1 && indexForInsertion < keys.size() && keys.get(indexForInsertion).equals(key))&&(__CLR4_4_17l7lm7es045k.R.iget(313)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(314)==0&false))) {{
			// duplicate value, simply overwrite the old one
			__CLR4_4_17l7lm7es045k.R.inc(315);values.set(indexForInsertion, value);
		} }else {__CLR4_4_17l7lm7es045k.R.inc(316);if ((((keys.size() < t - 1)&&(__CLR4_4_17l7lm7es045k.R.iget(317)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(318)==0&false))) {{
			// if node is not full- insert non full and return insertion result that contains the minimum gap
			__CLR4_4_17l7lm7es045k.R.inc(319);resultFromNonFullInsertion = insertNonFull(key, value, indexForInsertion);
		} }else {{
			// if the leaf is full- SPLIT the keys and the values
			__CLR4_4_17l7lm7es045k.R.inc(320);LeafNode<Value> nextSibling;
			__CLR4_4_17l7lm7es045k.R.inc(321);int mid = 0;
			__CLR4_4_17l7lm7es045k.R.inc(322);if ((((t % 2 == 0)&&(__CLR4_4_17l7lm7es045k.R.iget(323)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(324)==0&false))) {{
				__CLR4_4_17l7lm7es045k.R.inc(325);mid = keys.size() / 2;
			} }else {{
				__CLR4_4_17l7lm7es045k.R.inc(326);mid = (keys.size() + 1) / 2;
			}
			
			// move keys & values to the next node, and make insertion- based on the original index and the mid
			}__CLR4_4_17l7lm7es045k.R.inc(327);if ((((indexForInsertion <= mid)&&(__CLR4_4_17l7lm7es045k.R.iget(328)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(329)==0&false))) {{
				// insert the new key to the current leaf
				__CLR4_4_17l7lm7es045k.R.inc(330);nextSibling = new LeafNode<Value>(t, this, this.keys.subList(mid, this.keys.size()), this.values.subList(mid, this.values.size()));
				__CLR4_4_17l7lm7es045k.R.inc(331);this.keys = new ArrayList<Integer>(this.keys.subList(0, mid));
				__CLR4_4_17l7lm7es045k.R.inc(332);this.values = new ArrayList<Value>(this.values.subList(0, mid));
				
				__CLR4_4_17l7lm7es045k.R.inc(333);resultFromNonFullInsertion = insertNonFull(key, value);
			} }else {{
				// insert the new key to the new sibling
				__CLR4_4_17l7lm7es045k.R.inc(334);nextSibling = new LeafNode<Value>(t, this, this.keys.subList(mid + 1, this.keys.size()), this.values.subList(mid + 1, this.values.size()));
				__CLR4_4_17l7lm7es045k.R.inc(335);this.keys = new ArrayList<Integer>(this.keys.subList(0, mid + 1));
				__CLR4_4_17l7lm7es045k.R.inc(336);this.values = new ArrayList<Value>(this.values.subList(0, mid + 1));
				
				__CLR4_4_17l7lm7es045k.R.inc(337);resultFromNonFullInsertion = nextSibling.insertNonFull(key, value);
			}
			
			// return new insertion result, contains the minimum gap and the split (right, left and new root)
			}__CLR4_4_17l7lm7es045k.R.inc(338);return new InsertionResult<Value>(this.keys.get(this.keys.size() - 1), this, nextSibling, resultFromNonFullInsertion);
		}
		
		}}__CLR4_4_17l7lm7es045k.R.inc(339);return resultFromNonFullInsertion;
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * calculates the gap between the key in the given index, and his siblings
	 * @param index index of the inserted key
	 * @return the minimum gap between the right gap and the left gap
	 */
	protected int calculateGap(int index) {try{__CLR4_4_17l7lm7es045k.R.inc(340);
		// initialize the init gaps to infinity
		__CLR4_4_17l7lm7es045k.R.inc(341);int leftGap = Integer.MAX_VALUE, rightGap = Integer.MAX_VALUE;
		
		__CLR4_4_17l7lm7es045k.R.inc(342);if ((((index >= 0)&&(__CLR4_4_17l7lm7es045k.R.iget(343)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(344)==0&false))) {{
			// rightGap
			__CLR4_4_17l7lm7es045k.R.inc(345);Integer nextKey = null;
			__CLR4_4_17l7lm7es045k.R.inc(346);if ((((index + 1 < keys.size())&&(__CLR4_4_17l7lm7es045k.R.iget(347)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(348)==0&false))) // inside the node
				{__CLR4_4_17l7lm7es045k.R.inc(349);nextKey = keys.get(index + 1);
			}else {__CLR4_4_17l7lm7es045k.R.inc(350);if ((((next != null)&&(__CLR4_4_17l7lm7es045k.R.iget(351)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(352)==0&false))) // calculate with the first element of the next node
				{__CLR4_4_17l7lm7es045k.R.inc(353);nextKey = next.keys.get(0);
			
			}}__CLR4_4_17l7lm7es045k.R.inc(354);if ((((nextKey != null)&&(__CLR4_4_17l7lm7es045k.R.iget(355)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(356)==0&false)))
				{__CLR4_4_17l7lm7es045k.R.inc(357);rightGap = Math.abs( (Integer)keys.get(index) - (Integer)nextKey );
			
			// leftGap
			}__CLR4_4_17l7lm7es045k.R.inc(358);Integer prevKey = null;
			__CLR4_4_17l7lm7es045k.R.inc(359);if ((((index - 1 >= 0)&&(__CLR4_4_17l7lm7es045k.R.iget(360)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(361)==0&false)))
				{__CLR4_4_17l7lm7es045k.R.inc(362);prevKey = keys.get(index - 1); // inside the node
			}else {__CLR4_4_17l7lm7es045k.R.inc(363);if ((((prev != null)&&(__CLR4_4_17l7lm7es045k.R.iget(364)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(365)==0&false)))
				{__CLR4_4_17l7lm7es045k.R.inc(366);prevKey = prev.keys.get(prev.keys.size() - 1); // calculate with the last element of the previous node
			
			}}__CLR4_4_17l7lm7es045k.R.inc(367);if ((((prevKey != null)&&(__CLR4_4_17l7lm7es045k.R.iget(368)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(369)==0&false)))
				{__CLR4_4_17l7lm7es045k.R.inc(370);leftGap = Math.abs( (Integer)keys.get(index) - (Integer)prevKey );
		}}
		
		}__CLR4_4_17l7lm7es045k.R.inc(371);return Math.min(rightGap, leftGap);
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * get the next leaf
	 * @return next leaf
	 */
	public LeafNode<Value> getNext() {try{__CLR4_4_17l7lm7es045k.R.inc(372);
		__CLR4_4_17l7lm7es045k.R.inc(373);return this.next;
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * get the previous leaf
	 * @return previous leaf
	 */
	public LeafNode<Value> getPrev() {try{__CLR4_4_17l7lm7es045k.R.inc(374);
		__CLR4_4_17l7lm7es045k.R.inc(375);return this.prev;
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * get string representation of the keys in descending order- from the last key to the first
	 * @return string representation of the leaf's keys in descending order
	 */
	public String reverseToString() {try{__CLR4_4_17l7lm7es045k.R.inc(376);
		__CLR4_4_17l7lm7es045k.R.inc(377);StringBuilder sb = new StringBuilder();
		
		__CLR4_4_17l7lm7es045k.R.inc(378);for (int i = this.keys.size() - 1; (((i >= 0)&&(__CLR4_4_17l7lm7es045k.R.iget(379)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(380)==0&false)); i--) {{
			__CLR4_4_17l7lm7es045k.R.inc(381);sb.append(this.keys.get(i) + ",");
		}

		}__CLR4_4_17l7lm7es045k.R.inc(382);return sb.toString().substring(0, sb.toString().length());
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
	
	/*
	 * get string representation of the keys in ascending order- from the first key to the last
	 * @return string representation of the leaf's keys in ascending order
	 */
	public String toString() {try{__CLR4_4_17l7lm7es045k.R.inc(383);
		__CLR4_4_17l7lm7es045k.R.inc(384);StringBuilder sb = new StringBuilder();
		
		__CLR4_4_17l7lm7es045k.R.inc(385);for (int i = 0; (((i < this.keys.size() - 1)&&(__CLR4_4_17l7lm7es045k.R.iget(386)!=0|true))||(__CLR4_4_17l7lm7es045k.R.iget(387)==0&false)); i++) {{
			__CLR4_4_17l7lm7es045k.R.inc(388);sb.append(this.keys.get(i) + ",");
		}
		}__CLR4_4_17l7lm7es045k.R.inc(389);sb.append(this.keys.get(this.keys.size() - 1));

		__CLR4_4_17l7lm7es045k.R.inc(390);return sb.toString();
	}finally{__CLR4_4_17l7lm7es045k.R.flushNeeded();}}
}
