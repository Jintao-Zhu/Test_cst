/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
/*
 * Represents a result of insertion operation.
 * always contains minGap- the minimum gap between the inserted key and his siblings.
 * may contains pointers to left & right nodes, if the node was split as a result of the insertion.
 * @author Lidan Hifi
 */
class InsertionResult<Value> {public static class __CLR4_4_12a2am7es03zv{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0031\u0032\u0033\u005c\u005c\u0031\u0031\u0039\u005c\u005c\u0042\u0050\u006c\u0075\u0073\u0054\u0072\u0065\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1740142646498L,8589935092L,100,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
	// new root after split
	private Integer splitRootKey;
	// pointers to the old node (left) and the new node (right)
	private Node<Value> left, right;
	// holds the minimum gap between the inserted key and his siblings
	private int minGap;
	
	/*
	 * creates new insertion result, after split
	 */
	public InsertionResult(Integer key, Node<Value> left, Node<Value> right) {try{__CLR4_4_12a2am7es03zv.R.inc(82);
		__CLR4_4_12a2am7es03zv.R.inc(83);splitRootKey = key;
		__CLR4_4_12a2am7es03zv.R.inc(84);this.left = left;
		__CLR4_4_12a2am7es03zv.R.inc(85);this.right = right;
	}finally{__CLR4_4_12a2am7es03zv.R.flushNeeded();}}
	
	/*
	 * creates new insertion result, with the minGap from the regular insertion (to a non-full node)
	 */
	public InsertionResult(Integer key, Node<Value> left, Node<Value> right, InsertionResult<Value> resultFromNonFullNode) {
		this(key, left, right);__CLR4_4_12a2am7es03zv.R.inc(87);try{__CLR4_4_12a2am7es03zv.R.inc(86);
		
		__CLR4_4_12a2am7es03zv.R.inc(88);this.minGap = resultFromNonFullNode.getMinGap();
	}finally{__CLR4_4_12a2am7es03zv.R.flushNeeded();}}
	
	/*
	 * creates new insertion result, as a result from non-full node insertion (holds only the min gap)
	 */
	public InsertionResult(int minGap) {
		this(null, null, null);__CLR4_4_12a2am7es03zv.R.inc(90);try{__CLR4_4_12a2am7es03zv.R.inc(89);
		__CLR4_4_12a2am7es03zv.R.inc(91);this.minGap = minGap;
	}finally{__CLR4_4_12a2am7es03zv.R.flushNeeded();}}
	
	public Node<Value> getLeftNode() {try{__CLR4_4_12a2am7es03zv.R.inc(92); __CLR4_4_12a2am7es03zv.R.inc(93);return left; }finally{__CLR4_4_12a2am7es03zv.R.flushNeeded();}}
	public Node<Value> getRightNode() {try{__CLR4_4_12a2am7es03zv.R.inc(94); __CLR4_4_12a2am7es03zv.R.inc(95);return right; }finally{__CLR4_4_12a2am7es03zv.R.flushNeeded();}}
	public Integer getSplitRootKey() {try{__CLR4_4_12a2am7es03zv.R.inc(96); __CLR4_4_12a2am7es03zv.R.inc(97);return splitRootKey; }finally{__CLR4_4_12a2am7es03zv.R.flushNeeded();}}
	public int getMinGap() {try{__CLR4_4_12a2am7es03zv.R.inc(98); __CLR4_4_12a2am7es03zv.R.inc(99);return minGap; }finally{__CLR4_4_12a2am7es03zv.R.flushNeeded();}}
}
