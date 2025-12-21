/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
class Node {public static class __CLR4_4_110o10olpnjv53n{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,1364,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    String label;
    int weight;
    Node leftNode, rightNode;
    int x, y;
    int depth = -1;
    boolean highlightLeft = false, highlightRight = false, highlight = false;
    
    public Node(Node node1, Node node2) {try{__CLR4_4_110o10olpnjv53n.R.inc(1320);
	__CLR4_4_110o10olpnjv53n.R.inc(1321);leftNode = node1;
	__CLR4_4_110o10olpnjv53n.R.inc(1322);rightNode = node2;
	__CLR4_4_110o10olpnjv53n.R.inc(1323);weight = node1.getWeight() + node2.getWeight();
	__CLR4_4_110o10olpnjv53n.R.inc(1324);label = new String();
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}

    public Node(Node node) {try{__CLR4_4_110o10olpnjv53n.R.inc(1325);
	__CLR4_4_110o10olpnjv53n.R.inc(1326);leftNode = node;
	__CLR4_4_110o10olpnjv53n.R.inc(1327);rightNode = null;
	__CLR4_4_110o10olpnjv53n.R.inc(1328);weight = 0;
	__CLR4_4_110o10olpnjv53n.R.inc(1329);label = new String();
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}

    public Node() {try{__CLR4_4_110o10olpnjv53n.R.inc(1330);
	__CLR4_4_110o10olpnjv53n.R.inc(1331);label = new String();
	__CLR4_4_110o10olpnjv53n.R.inc(1332);weight = 0;
	__CLR4_4_110o10olpnjv53n.R.inc(1333);leftNode = rightNode = null;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}} // Constructor 1
    
    public Node(String label, int weight) {try{__CLR4_4_110o10olpnjv53n.R.inc(1334);
	__CLR4_4_110o10olpnjv53n.R.inc(1335);this.label = new String(label);
	__CLR4_4_110o10olpnjv53n.R.inc(1336);this.weight = weight;
	__CLR4_4_110o10olpnjv53n.R.inc(1337);leftNode = rightNode = null;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}} // Constructor 2
    
    public Node(int weight) {try{__CLR4_4_110o10olpnjv53n.R.inc(1338);
	__CLR4_4_110o10olpnjv53n.R.inc(1339);this.label = new String();
	__CLR4_4_110o10olpnjv53n.R.inc(1340);this.weight = weight;
	__CLR4_4_110o10olpnjv53n.R.inc(1341);leftNode = rightNode = null;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}} // Constructor 3
    
    public Node(String label) {try{__CLR4_4_110o10olpnjv53n.R.inc(1342);
	__CLR4_4_110o10olpnjv53n.R.inc(1343);this.label = new String(label);
	__CLR4_4_110o10olpnjv53n.R.inc(1344);this.weight = 0;
	__CLR4_4_110o10olpnjv53n.R.inc(1345);leftNode = rightNode = null;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}} // Constructor 4
    
    public void setWeight(int weight) {try{__CLR4_4_110o10olpnjv53n.R.inc(1346);
	__CLR4_4_110o10olpnjv53n.R.inc(1347);this.weight = weight;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}
    
    public void setLabel(String label) {try{__CLR4_4_110o10olpnjv53n.R.inc(1348);
	__CLR4_4_110o10olpnjv53n.R.inc(1349);this.label = new String(label);
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}
    
    public int getWeight() {try{__CLR4_4_110o10olpnjv53n.R.inc(1350);
	__CLR4_4_110o10olpnjv53n.R.inc(1351);return weight;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}
    
    public String getLabel() {try{__CLR4_4_110o10olpnjv53n.R.inc(1352);
	__CLR4_4_110o10olpnjv53n.R.inc(1353);return new String(label);
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}

    public void setLeftNode(Node node) {try{__CLR4_4_110o10olpnjv53n.R.inc(1354);
	__CLR4_4_110o10olpnjv53n.R.inc(1355);leftNode = node;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}

    public void setRightNode(Node node) {try{__CLR4_4_110o10olpnjv53n.R.inc(1356);
	__CLR4_4_110o10olpnjv53n.R.inc(1357);rightNode = node;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}

    public Node getLeftNode() {try{__CLR4_4_110o10olpnjv53n.R.inc(1358);
	__CLR4_4_110o10olpnjv53n.R.inc(1359);return leftNode;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}

    public Node getRightNode() {try{__CLR4_4_110o10olpnjv53n.R.inc(1360);
	__CLR4_4_110o10olpnjv53n.R.inc(1361);return rightNode;
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}

    public boolean isLeaf() {try{__CLR4_4_110o10olpnjv53n.R.inc(1362);
	__CLR4_4_110o10olpnjv53n.R.inc(1363);return ((rightNode==null)&&(leftNode==null));
    }finally{__CLR4_4_110o10olpnjv53n.R.flushNeeded();}}
} // class Node
