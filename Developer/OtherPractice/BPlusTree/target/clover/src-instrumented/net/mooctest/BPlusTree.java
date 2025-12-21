/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
/**
 * B+ Tree
 * @author Lidan Hifi
 */
public class BPlusTree<Value> {public static class __CLR4_4_100m7es03y1{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0031\u0032\u0033\u005c\u005c\u0031\u0031\u0039\u005c\u005c\u0042\u0050\u006c\u0075\u0073\u0054\u0072\u0065\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1740142646498L,8589935092L,82,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
	// tree param
	private int t;
	// pointer to the root node.  can be leaf or internal node.
	private Node<Value> root;
	// pointer to the first leaf in the tree, for kind-of InOrder scan.
	private LeafNode<Value> first;
	// the minimum gap between two elements in the tree.
	private int minGap;
	// pointer to a bloom filter object, for O(1) unsuccessful search.
	private IntegerBloomFilter filter = null;
	
	/*
	 * Create new B+Tree
	 * initialize a new root as an empty leaf
	 * @param treeParam t that represents how many child nodes can an internal node have
	 */
	public BPlusTree(int treeParam) {try{__CLR4_4_100m7es03y1.R.inc(0);
		__CLR4_4_100m7es03y1.R.inc(1);t = treeParam;
		__CLR4_4_100m7es03y1.R.inc(2);minGap = Integer.MAX_VALUE;
		__CLR4_4_100m7es03y1.R.inc(3);root = new LeafNode<Value>(treeParam);
		__CLR4_4_100m7es03y1.R.inc(4);first = (LeafNode<Value>)root;
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * Create new B+Tree
	 * @param treeParam t that represents how many child nodes can an internal node have
	 * @param expectedNumberOfElements expected number of elements that will be stored in the tree. used for initialize the bloom filter, for O(1) unsucessful search.
	 */
	public BPlusTree(int treeParam, int expectedNumberOfElements) {
		this(treeParam);__CLR4_4_100m7es03y1.R.inc(6);try{__CLR4_4_100m7es03y1.R.inc(5);
		// initialize the bloom filter, with a false-positive probability of 0.000001
		__CLR4_4_100m7es03y1.R.inc(7);filter = new IntegerBloomFilter(0.000001, expectedNumberOfElements);
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * find the leaf that might store the given key
	 * @param key the key that the search algorithm looking for
	 * @return the leaf that might store the given key
	 */
	private LeafNode<Value> findLeaf(int key) {try{__CLR4_4_100m7es03y1.R.inc(8);
		__CLR4_4_100m7es03y1.R.inc(9);Node<Value> current = root;
		
		__CLR4_4_100m7es03y1.R.inc(10);while ((((!(current instanceof LeafNode))&&(__CLR4_4_100m7es03y1.R.iget(11)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(12)==0&false))) {{
			__CLR4_4_100m7es03y1.R.inc(13);current = ((InternalNode<Value>)current).getChildNode(key);
		}
		
		}__CLR4_4_100m7es03y1.R.inc(14);return (LeafNode<Value>)current;
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * get the value for a given key, that is stored in the tree
	 * @param key the value's key that was stored in the tree
	 * @return Value if the search succeed, null if the key does not exist in the tree.
	 */
	public Value search(int key) {try{__CLR4_4_100m7es03y1.R.inc(15);
		__CLR4_4_100m7es03y1.R.inc(16);boolean continueToSearch = true;
		
		__CLR4_4_100m7es03y1.R.inc(17);if ((((filter != null)&&(__CLR4_4_100m7es03y1.R.iget(18)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(19)==0&false))) // check if bloom filter was initialized
			{__CLR4_4_100m7es03y1.R.inc(20);continueToSearch = filter.contains(key); // find the given key in the filter
		
		}__CLR4_4_100m7es03y1.R.inc(21);if ((((continueToSearch)&&(__CLR4_4_100m7es03y1.R.iget(22)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(23)==0&false))) // if the bloom filter return true (it might happen when the key really exists, or either false positive)
			{__CLR4_4_100m7es03y1.R.inc(24);return findLeaf(key).getValue(key);
		}else {{
			__CLR4_4_100m7es03y1.R.inc(25);System.out.println("Filter stopped the search.");
			__CLR4_4_100m7es03y1.R.inc(26);System.out.println("Current False Positive Probability: " + filter.getCurrentFalsePositiveProbability());
		}
		
		}__CLR4_4_100m7es03y1.R.inc(27);return null;
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * Insert key,value to the tree.
	 * @param key key for insertion
	 * @param value value for insertion
	 */
	public void insert(int key, Value value) {try{__CLR4_4_100m7es03y1.R.inc(28);
		// insert the data recursively, starting in the root
		__CLR4_4_100m7es03y1.R.inc(29);InsertionResult<Value> result = root.insert(key, value);
		
		// the tree was split, create a new root that points to the split nodes
		__CLR4_4_100m7es03y1.R.inc(30);if ((((result != null && result.getSplitRootKey() != null)&&(__CLR4_4_100m7es03y1.R.iget(31)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(32)==0&false))) 
			{__CLR4_4_100m7es03y1.R.inc(33);root = new InternalNode<Value>(t, result);
		
		// update the min gap if the return min gap is smaller than the current one
		}__CLR4_4_100m7es03y1.R.inc(34);if ((((result != null && result.getMinGap() < minGap)&&(__CLR4_4_100m7es03y1.R.iget(35)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(36)==0&false)))
			{__CLR4_4_100m7es03y1.R.inc(37);minGap = result.getMinGap();
		
		// if the bloom filter was initialized, add the given key to the filter for future search
		}__CLR4_4_100m7es03y1.R.inc(38);if ((((filter != null)&&(__CLR4_4_100m7es03y1.R.iget(39)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(40)==0&false)))
			{__CLR4_4_100m7es03y1.R.inc(41);filter.add(key);
	}}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * get string representation of the keys in descending order- from the last key to the first
	 * @return string representation of the tree's keys in descending order
	 */
	public String reverseInOrder() {try{__CLR4_4_100m7es03y1.R.inc(42);
		__CLR4_4_100m7es03y1.R.inc(43);Node<Value> current = root;
		
		// find the right-most leaf
		__CLR4_4_100m7es03y1.R.inc(44);while ((((!(current instanceof LeafNode))&&(__CLR4_4_100m7es03y1.R.iget(45)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(46)==0&false))) {{
			__CLR4_4_100m7es03y1.R.inc(47);current = ((InternalNode<Value>)current).getMaxChildNode();
		}
		
		}__CLR4_4_100m7es03y1.R.inc(48);LeafNode<Value> leaf = (LeafNode<Value>)current;
		__CLR4_4_100m7es03y1.R.inc(49);StringBuilder sb = new StringBuilder();
		
		// append to the result string the reversed elements in each leaf
		__CLR4_4_100m7es03y1.R.inc(50);while ((((leaf != null)&&(__CLR4_4_100m7es03y1.R.iget(51)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(52)==0&false))) {{
			__CLR4_4_100m7es03y1.R.inc(53);sb.append(leaf.reverseToString());
			__CLR4_4_100m7es03y1.R.inc(54);leaf = leaf.getPrev();
		}
		
		}__CLR4_4_100m7es03y1.R.inc(55);return sb.toString();
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * get the tree size- how many keys are stored
	 */
	public int getSize() {try{__CLR4_4_100m7es03y1.R.inc(56);
		__CLR4_4_100m7es03y1.R.inc(57);return root.getNodeSize();
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * get the minimum gap between any two keys in the tree
	 * @return minimum gap value
	 */
	public int getMinGap() {try{__CLR4_4_100m7es03y1.R.inc(58);
		__CLR4_4_100m7es03y1.R.inc(59);return minGap;
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * get the index of the given key in the tree
	 * @return index of the given key if the key exists, -1 if the key does not exists
	 */
	public int order(int key) {try{__CLR4_4_100m7es03y1.R.inc(60);
		__CLR4_4_100m7es03y1.R.inc(61);int orderIndex = root.order(key);
		__CLR4_4_100m7es03y1.R.inc(62);if ((((orderIndex != -1)&&(__CLR4_4_100m7es03y1.R.iget(63)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(64)==0&false))) // zero based
			{__CLR4_4_100m7es03y1.R.inc(65);return orderIndex + 1;
		
		}__CLR4_4_100m7es03y1.R.inc(66);return -1;
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * get string representation of the keys in ascending order- from the first key to the last
	 * @return string representation of the tree's keys in ascending order
	 */
	public String inOrder() {try{__CLR4_4_100m7es03y1.R.inc(67);
		__CLR4_4_100m7es03y1.R.inc(68);StringBuilder sb = new StringBuilder();
		__CLR4_4_100m7es03y1.R.inc(69);LeafNode<Value> current = first;
		
		// append the string representation of each leaf to the result string
		__CLR4_4_100m7es03y1.R.inc(70);while ((((current != null)&&(__CLR4_4_100m7es03y1.R.iget(71)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(72)==0&false))) {{
			__CLR4_4_100m7es03y1.R.inc(73);sb.append(current);
			__CLR4_4_100m7es03y1.R.inc(74);current = current.getNext();
		}
		
		}__CLR4_4_100m7es03y1.R.inc(75);return sb.toString();
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {try{__CLR4_4_100m7es03y1.R.inc(76);
		__CLR4_4_100m7es03y1.R.inc(77);if ((((root.getNodeSize() != 0)&&(__CLR4_4_100m7es03y1.R.iget(78)!=0|true))||(__CLR4_4_100m7es03y1.R.iget(79)==0&false)))
				{__CLR4_4_100m7es03y1.R.inc(80);return root.toString();
		
		}__CLR4_4_100m7es03y1.R.inc(81);return "";
	}finally{__CLR4_4_100m7es03y1.R.flushNeeded();}}
}
