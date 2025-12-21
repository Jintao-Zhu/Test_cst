/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ *//******************************************************************************
 *  Compilation:  javac BST.java
 *  Execution:    java BST
 *  Dependencies: StdIn.java StdOut.java Queue.java
 *  Data files:   https://algs4.cs.princeton.edu/32bst/tinyST.txt  
 *
 *  A symbol table implemented with a binary search tree.
 * 
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *  
 *  % java BST < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 *
 ******************************************************************************/

package net.mooctest;

import java.util.NoSuchElementException;

/**
 *  The {@code BST} class represents an ordered symbol table of generic
 *  key-value pairs.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  It also provides ordered methods for finding the <em>minimum</em>,
 *  <em>maximum</em>, <em>floor</em>, <em>select</em>, <em>ceiling</em>.
 *  It also provides a <em>keys</em> method for iterating over all of the keys.
 *  A symbol table implements the <em>associative array</em> abstraction:
 *  when associating a value with a key that is already in the symbol table,
 *  the convention is to replace the old value with the new value.
 *  Unlike {@link java.util.Map}, this class uses the convention that
 *  values cannot be {@code null}\u9225\u6515etting the
 *  value associated with a key to {@code null} is equivalent to deleting the key
 *  from the symbol table.
 *  <p>
 *  This implementation uses an (unbalanced) binary search tree. It requires that
 *  the key type implements the {@code Comparable} interface and calls the
 *  {@code compareTo()} and method to compare two keys. It does not call either
 *  {@code equals()} or {@code hashCode()}.
 *  The <em>put</em>, <em>contains</em>, <em>remove</em>, <em>minimum</em>,
 *  <em>maximum</em>, <em>ceiling</em>, <em>floor</em>, <em>select</em>, and
 *  <em>rank</em>  operations each take
 *  linear time in the worst case, if the tree becomes unbalanced.
 *  The <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes constant time.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/32bst">Section 3.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  For other implementations, see {@link ST}, {@link BinarySearchST},
 *  {@link SequentialSearchST}, {@link RedBlackBST},
 *  {@link SeparateChainingHashST}, and {@link LinearProbingHashST},
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class BST<Key extends Comparable<Key>, Value> {public static class __CLR4_4_100lpnrio6s{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0036\u0030\u0032\u005c\u005c\u0031\u0033\u0034\u0034\u0038\u0034\u005c\u005c\u0042\u0053\u0054\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701503946885L,8589935092L,429,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {try{__CLR4_4_100lpnrio6s.R.inc(0);
            __CLR4_4_100lpnrio6s.R.inc(1);this.key = key;
            __CLR4_4_100lpnrio6s.R.inc(2);this.val = val;
            __CLR4_4_100lpnrio6s.R.inc(3);this.size = size;
        }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}
    }

    /**
     * Initializes an empty symbol table.
     */
    public BST() {try{__CLR4_4_100lpnrio6s.R.inc(4);
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {try{__CLR4_4_100lpnrio6s.R.inc(5);
        __CLR4_4_100lpnrio6s.R.inc(6);return size() == 0;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {try{__CLR4_4_100lpnrio6s.R.inc(7);
        __CLR4_4_100lpnrio6s.R.inc(8);return size(root);
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {try{__CLR4_4_100lpnrio6s.R.inc(9);
        __CLR4_4_100lpnrio6s.R.inc(10);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(11)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(12)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(13);return 0;
        }else {__CLR4_4_100lpnrio6s.R.inc(14);return x.size;
    }}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {try{__CLR4_4_100lpnrio6s.R.inc(15);
        __CLR4_4_100lpnrio6s.R.inc(16);if ((((key == null)&&(__CLR4_4_100lpnrio6s.R.iget(17)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(18)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(19);throw new IllegalArgumentException("argument to contains() is null");
        }__CLR4_4_100lpnrio6s.R.inc(20);return get(key) != null;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {try{__CLR4_4_100lpnrio6s.R.inc(21);
        __CLR4_4_100lpnrio6s.R.inc(22);return get(root, key);
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    private Value get(Node x, Key key) {try{__CLR4_4_100lpnrio6s.R.inc(23);
        __CLR4_4_100lpnrio6s.R.inc(24);if ((((key == null)&&(__CLR4_4_100lpnrio6s.R.iget(25)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(26)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(27);throw new IllegalArgumentException("calls get() with a null key");
        }__CLR4_4_100lpnrio6s.R.inc(28);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(29)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(30)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(31);return null;
        }__CLR4_4_100lpnrio6s.R.inc(32);int cmp = key.compareTo(x.key);
        __CLR4_4_100lpnrio6s.R.inc(33);if      ((((cmp < 0)&&(__CLR4_4_100lpnrio6s.R.iget(34)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(35)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(36);return get(x.left, key);
        }else {__CLR4_4_100lpnrio6s.R.inc(37);if ((((cmp > 0)&&(__CLR4_4_100lpnrio6s.R.iget(38)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(39)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(40);return get(x.right, key);
        }else              {__CLR4_4_100lpnrio6s.R.inc(41);return x.val;
    }}}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {try{__CLR4_4_100lpnrio6s.R.inc(42);
        __CLR4_4_100lpnrio6s.R.inc(43);if ((((key == null)&&(__CLR4_4_100lpnrio6s.R.iget(44)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(45)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(46);throw new IllegalArgumentException("calls put() with a null key");
        }__CLR4_4_100lpnrio6s.R.inc(47);if ((((val == null)&&(__CLR4_4_100lpnrio6s.R.iget(48)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(49)==0&false))) {{
            __CLR4_4_100lpnrio6s.R.inc(50);delete(key);
            __CLR4_4_100lpnrio6s.R.inc(51);return;
        }
        }__CLR4_4_100lpnrio6s.R.inc(52);root = put(root, key, val);
        assert (((check())&&(__CLR4_4_100lpnrio6s.R.iget(53)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(54)==0&false));
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    private Node put(Node x, Key key, Value val) {try{__CLR4_4_100lpnrio6s.R.inc(55);
        __CLR4_4_100lpnrio6s.R.inc(56);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(57)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(58)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(59);return new Node(key, val, 1);
        }__CLR4_4_100lpnrio6s.R.inc(60);int cmp = key.compareTo(x.key);
        __CLR4_4_100lpnrio6s.R.inc(61);if      ((((cmp < 0)&&(__CLR4_4_100lpnrio6s.R.iget(62)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(63)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(64);x.left  = put(x.left,  key, val);
        }else {__CLR4_4_100lpnrio6s.R.inc(65);if ((((cmp > 0)&&(__CLR4_4_100lpnrio6s.R.iget(66)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(67)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(68);x.right = put(x.right, key, val);
        }else              {__CLR4_4_100lpnrio6s.R.inc(69);x.val   = val;
        }}__CLR4_4_100lpnrio6s.R.inc(70);x.size = 1 + size(x.left) + size(x.right);
        __CLR4_4_100lpnrio6s.R.inc(71);return x;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}


    /**
     * Removes the smallest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {try{__CLR4_4_100lpnrio6s.R.inc(72);
        __CLR4_4_100lpnrio6s.R.inc(73);if ((((isEmpty())&&(__CLR4_4_100lpnrio6s.R.iget(74)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(75)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(76);throw new NoSuchElementException("Symbol table underflow");
        }__CLR4_4_100lpnrio6s.R.inc(77);root = deleteMin(root);
        assert (((check())&&(__CLR4_4_100lpnrio6s.R.iget(78)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(79)==0&false));
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    private Node deleteMin(Node x) {try{__CLR4_4_100lpnrio6s.R.inc(80);
        __CLR4_4_100lpnrio6s.R.inc(81);if ((((x.left == null)&&(__CLR4_4_100lpnrio6s.R.iget(82)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(83)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(84);return x.right;
        }__CLR4_4_100lpnrio6s.R.inc(85);x.left = deleteMin(x.left);
        __CLR4_4_100lpnrio6s.R.inc(86);x.size = size(x.left) + size(x.right) + 1;
        __CLR4_4_100lpnrio6s.R.inc(87);return x;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Removes the largest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {try{__CLR4_4_100lpnrio6s.R.inc(88);
        __CLR4_4_100lpnrio6s.R.inc(89);if ((((isEmpty())&&(__CLR4_4_100lpnrio6s.R.iget(90)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(91)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(92);throw new NoSuchElementException("Symbol table underflow");
        }__CLR4_4_100lpnrio6s.R.inc(93);root = deleteMax(root);
        assert (((check())&&(__CLR4_4_100lpnrio6s.R.iget(94)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(95)==0&false));
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    private Node deleteMax(Node x) {try{__CLR4_4_100lpnrio6s.R.inc(96);
        __CLR4_4_100lpnrio6s.R.inc(97);if ((((x.right == null)&&(__CLR4_4_100lpnrio6s.R.iget(98)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(99)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(100);return x.left;
        }__CLR4_4_100lpnrio6s.R.inc(101);x.right = deleteMax(x.right);
        __CLR4_4_100lpnrio6s.R.inc(102);x.size = size(x.left) + size(x.right) + 1;
        __CLR4_4_100lpnrio6s.R.inc(103);return x;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {try{__CLR4_4_100lpnrio6s.R.inc(104);
        __CLR4_4_100lpnrio6s.R.inc(105);if ((((key == null)&&(__CLR4_4_100lpnrio6s.R.iget(106)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(107)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(108);throw new IllegalArgumentException("calls delete() with a null key");
        }__CLR4_4_100lpnrio6s.R.inc(109);root = delete(root, key);
        assert (((check())&&(__CLR4_4_100lpnrio6s.R.iget(110)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(111)==0&false));
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    private Node delete(Node x, Key key) {try{__CLR4_4_100lpnrio6s.R.inc(112);
        __CLR4_4_100lpnrio6s.R.inc(113);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(114)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(115)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(116);return null;

        }__CLR4_4_100lpnrio6s.R.inc(117);int cmp = key.compareTo(x.key);
        __CLR4_4_100lpnrio6s.R.inc(118);if      ((((cmp < 0)&&(__CLR4_4_100lpnrio6s.R.iget(119)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(120)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(121);x.left  = delete(x.left,  key);
        }else {__CLR4_4_100lpnrio6s.R.inc(122);if ((((cmp > 0)&&(__CLR4_4_100lpnrio6s.R.iget(123)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(124)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(125);x.right = delete(x.right, key);
        }else {{ 
            __CLR4_4_100lpnrio6s.R.inc(126);if ((((x.right == null)&&(__CLR4_4_100lpnrio6s.R.iget(127)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(128)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(129);return x.left;
            }__CLR4_4_100lpnrio6s.R.inc(130);if ((((x.left  == null)&&(__CLR4_4_100lpnrio6s.R.iget(131)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(132)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(133);return x.right;
            }__CLR4_4_100lpnrio6s.R.inc(134);Node t = x;
            __CLR4_4_100lpnrio6s.R.inc(135);x = min(t.right);
            __CLR4_4_100lpnrio6s.R.inc(136);x.right = deleteMin(t.right);
            __CLR4_4_100lpnrio6s.R.inc(137);x.left = t.left;
        } 
        }}__CLR4_4_100lpnrio6s.R.inc(138);x.size = size(x.left) + size(x.right) + 1;
        __CLR4_4_100lpnrio6s.R.inc(139);return x;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 


    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {try{__CLR4_4_100lpnrio6s.R.inc(140);
        __CLR4_4_100lpnrio6s.R.inc(141);if ((((isEmpty())&&(__CLR4_4_100lpnrio6s.R.iget(142)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(143)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(144);throw new NoSuchElementException("calls min() with empty symbol table");
        }__CLR4_4_100lpnrio6s.R.inc(145);return min(root).key;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    private Node min(Node x) {try{__CLR4_4_100lpnrio6s.R.inc(146); 
        __CLR4_4_100lpnrio6s.R.inc(147);if ((((x.left == null)&&(__CLR4_4_100lpnrio6s.R.iget(148)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(149)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(150);return x; 
        }else                {__CLR4_4_100lpnrio6s.R.inc(151);return min(x.left); 
    }}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {try{__CLR4_4_100lpnrio6s.R.inc(152);
        __CLR4_4_100lpnrio6s.R.inc(153);if ((((isEmpty())&&(__CLR4_4_100lpnrio6s.R.iget(154)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(155)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(156);throw new NoSuchElementException("calls max() with empty symbol table");
        }__CLR4_4_100lpnrio6s.R.inc(157);return max(root).key;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    private Node max(Node x) {try{__CLR4_4_100lpnrio6s.R.inc(158);
        __CLR4_4_100lpnrio6s.R.inc(159);if ((((x.right == null)&&(__CLR4_4_100lpnrio6s.R.iget(160)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(161)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(162);return x; 
        }else                 {__CLR4_4_100lpnrio6s.R.inc(163);return max(x.right); 
    }}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     *
     * @param  key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {try{__CLR4_4_100lpnrio6s.R.inc(164);
        __CLR4_4_100lpnrio6s.R.inc(165);if ((((key == null)&&(__CLR4_4_100lpnrio6s.R.iget(166)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(167)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(168);throw new IllegalArgumentException("argument to floor() is null");
        }__CLR4_4_100lpnrio6s.R.inc(169);if ((((isEmpty())&&(__CLR4_4_100lpnrio6s.R.iget(170)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(171)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(172);throw new NoSuchElementException("calls floor() with empty symbol table");
        }__CLR4_4_100lpnrio6s.R.inc(173);Node x = floor(root, key);
        __CLR4_4_100lpnrio6s.R.inc(174);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(175)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(176)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(177);return null;
        }else {__CLR4_4_100lpnrio6s.R.inc(178);return x.key;
    }}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    private Node floor(Node x, Key key) {try{__CLR4_4_100lpnrio6s.R.inc(179);
        __CLR4_4_100lpnrio6s.R.inc(180);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(181)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(182)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(183);return null;
        }__CLR4_4_100lpnrio6s.R.inc(184);int cmp = key.compareTo(x.key);
        __CLR4_4_100lpnrio6s.R.inc(185);if ((((cmp == 0)&&(__CLR4_4_100lpnrio6s.R.iget(186)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(187)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(188);return x;
        }__CLR4_4_100lpnrio6s.R.inc(189);if ((((cmp <  0)&&(__CLR4_4_100lpnrio6s.R.iget(190)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(191)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(192);return floor(x.left, key);
        }__CLR4_4_100lpnrio6s.R.inc(193);Node t = floor(x.right, key); 
        __CLR4_4_100lpnrio6s.R.inc(194);if ((((t != null)&&(__CLR4_4_100lpnrio6s.R.iget(195)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(196)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(197);return t;
        }else {__CLR4_4_100lpnrio6s.R.inc(198);return x; 
    }}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    public Key floor2(Key key) {try{__CLR4_4_100lpnrio6s.R.inc(199);
        __CLR4_4_100lpnrio6s.R.inc(200);return floor2(root, key, null);
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    private Key floor2(Node x, Key key, Key best) {try{__CLR4_4_100lpnrio6s.R.inc(201);
        __CLR4_4_100lpnrio6s.R.inc(202);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(203)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(204)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(205);return best;
        }__CLR4_4_100lpnrio6s.R.inc(206);int cmp = key.compareTo(x.key);
        __CLR4_4_100lpnrio6s.R.inc(207);if      ((((cmp  < 0)&&(__CLR4_4_100lpnrio6s.R.iget(208)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(209)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(210);return floor2(x.left, key, best);
        }else {__CLR4_4_100lpnrio6s.R.inc(211);if ((((cmp  > 0)&&(__CLR4_4_100lpnrio6s.R.iget(212)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(213)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(214);return floor2(x.right, key, x.key);
        }else               {__CLR4_4_100lpnrio6s.R.inc(215);return x.key;
    }}}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     *
     * @param  key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {try{__CLR4_4_100lpnrio6s.R.inc(216);
        __CLR4_4_100lpnrio6s.R.inc(217);if ((((key == null)&&(__CLR4_4_100lpnrio6s.R.iget(218)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(219)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(220);throw new IllegalArgumentException("argument to ceiling() is null");
        }__CLR4_4_100lpnrio6s.R.inc(221);if ((((isEmpty())&&(__CLR4_4_100lpnrio6s.R.iget(222)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(223)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(224);throw new NoSuchElementException("calls ceiling() with empty symbol table");
        }__CLR4_4_100lpnrio6s.R.inc(225);Node x = ceiling(root, key);
        __CLR4_4_100lpnrio6s.R.inc(226);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(227)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(228)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(229);return null;
        }else {__CLR4_4_100lpnrio6s.R.inc(230);return x.key;
    }}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    private Node ceiling(Node x, Key key) {try{__CLR4_4_100lpnrio6s.R.inc(231);
        __CLR4_4_100lpnrio6s.R.inc(232);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(233)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(234)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(235);return null;
        }__CLR4_4_100lpnrio6s.R.inc(236);int cmp = key.compareTo(x.key);
        __CLR4_4_100lpnrio6s.R.inc(237);if ((((cmp == 0)&&(__CLR4_4_100lpnrio6s.R.iget(238)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(239)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(240);return x;
        }__CLR4_4_100lpnrio6s.R.inc(241);if ((((cmp < 0)&&(__CLR4_4_100lpnrio6s.R.iget(242)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(243)==0&false))) {{ 
            __CLR4_4_100lpnrio6s.R.inc(244);Node t = ceiling(x.left, key); 
            __CLR4_4_100lpnrio6s.R.inc(245);if ((((t != null)&&(__CLR4_4_100lpnrio6s.R.iget(246)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(247)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(248);return t;
            }else {__CLR4_4_100lpnrio6s.R.inc(249);return x; 
        }} 
        }__CLR4_4_100lpnrio6s.R.inc(250);return ceiling(x.right, key); 
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    /**
     * Return the key in the symbol table whose rank is {@code k}.
     * This is the (k+1)st smallest key in the symbol table.
     *
     * @param  k the order statistic
     * @return the key in the symbol table of rank {@code k}
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>n</em>\u9225\ufffd1
     */
    public Key select(int k) {try{__CLR4_4_100lpnrio6s.R.inc(251);
        __CLR4_4_100lpnrio6s.R.inc(252);if ((((k < 0 || k >= size())&&(__CLR4_4_100lpnrio6s.R.iget(253)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(254)==0&false))) {{
            __CLR4_4_100lpnrio6s.R.inc(255);throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        }__CLR4_4_100lpnrio6s.R.inc(256);Node x = select(root, k);
        __CLR4_4_100lpnrio6s.R.inc(257);return x.key;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    // Return key of rank k. 
    private Node select(Node x, int k) {try{__CLR4_4_100lpnrio6s.R.inc(258);
        __CLR4_4_100lpnrio6s.R.inc(259);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(260)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(261)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(262);return null; 
        }__CLR4_4_100lpnrio6s.R.inc(263);int t = size(x.left); 
        __CLR4_4_100lpnrio6s.R.inc(264);if      ((((t > k)&&(__CLR4_4_100lpnrio6s.R.iget(265)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(266)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(267);return select(x.left,  k); 
        }else {__CLR4_4_100lpnrio6s.R.inc(268);if ((((t < k)&&(__CLR4_4_100lpnrio6s.R.iget(269)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(270)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(271);return select(x.right, k-t-1); 
        }else            {__CLR4_4_100lpnrio6s.R.inc(272);return x; 
    }}}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     *
     * @param  key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {try{__CLR4_4_100lpnrio6s.R.inc(273);
        __CLR4_4_100lpnrio6s.R.inc(274);if ((((key == null)&&(__CLR4_4_100lpnrio6s.R.iget(275)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(276)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(277);throw new IllegalArgumentException("argument to rank() is null");
        }__CLR4_4_100lpnrio6s.R.inc(278);return rank(key, root);
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {try{__CLR4_4_100lpnrio6s.R.inc(279);
        __CLR4_4_100lpnrio6s.R.inc(280);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(281)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(282)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(283);return 0; 
        }__CLR4_4_100lpnrio6s.R.inc(284);int cmp = key.compareTo(x.key); 
        __CLR4_4_100lpnrio6s.R.inc(285);if      ((((cmp < 0)&&(__CLR4_4_100lpnrio6s.R.iget(286)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(287)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(288);return rank(key, x.left); 
        }else {__CLR4_4_100lpnrio6s.R.inc(289);if ((((cmp > 0)&&(__CLR4_4_100lpnrio6s.R.iget(290)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(291)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(292);return 1 + size(x.left) + rank(key, x.right); 
        }else              {__CLR4_4_100lpnrio6s.R.inc(293);return size(x.left); 
    }}}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in the symbol table
     */
    public Iterable<Key> keys() {try{__CLR4_4_100lpnrio6s.R.inc(294);
        __CLR4_4_100lpnrio6s.R.inc(295);if ((((isEmpty())&&(__CLR4_4_100lpnrio6s.R.iget(296)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(297)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(298);return new Queue<Key>();
        }__CLR4_4_100lpnrio6s.R.inc(299);return keys(min(), max());
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the symbol table between {@code lo} 
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {try{__CLR4_4_100lpnrio6s.R.inc(300);
        __CLR4_4_100lpnrio6s.R.inc(301);if ((((lo == null)&&(__CLR4_4_100lpnrio6s.R.iget(302)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(303)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(304);throw new IllegalArgumentException("first argument to keys() is null");
        }__CLR4_4_100lpnrio6s.R.inc(305);if ((((hi == null)&&(__CLR4_4_100lpnrio6s.R.iget(306)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(307)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(308);throw new IllegalArgumentException("second argument to keys() is null");

        }__CLR4_4_100lpnrio6s.R.inc(309);Queue<Key> queue = new Queue<Key>();
        __CLR4_4_100lpnrio6s.R.inc(310);keys(root, queue, lo, hi);
        __CLR4_4_100lpnrio6s.R.inc(311);return queue;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {try{__CLR4_4_100lpnrio6s.R.inc(312); 
        __CLR4_4_100lpnrio6s.R.inc(313);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(314)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(315)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(316);return; 
        }__CLR4_4_100lpnrio6s.R.inc(317);int cmplo = lo.compareTo(x.key); 
        __CLR4_4_100lpnrio6s.R.inc(318);int cmphi = hi.compareTo(x.key); 
        __CLR4_4_100lpnrio6s.R.inc(319);if ((((cmplo < 0)&&(__CLR4_4_100lpnrio6s.R.iget(320)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(321)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(322);keys(x.left, queue, lo, hi); 
        }__CLR4_4_100lpnrio6s.R.inc(323);if ((((cmplo <= 0 && cmphi >= 0)&&(__CLR4_4_100lpnrio6s.R.iget(324)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(325)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(326);queue.enqueue(x.key); 
        }__CLR4_4_100lpnrio6s.R.inc(327);if ((((cmphi > 0)&&(__CLR4_4_100lpnrio6s.R.iget(328)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(329)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(330);keys(x.right, queue, lo, hi); 
    }}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    /**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return the number of keys in the symbol table between {@code lo} 
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
     */
    public int size(Key lo, Key hi) {try{__CLR4_4_100lpnrio6s.R.inc(331);
        __CLR4_4_100lpnrio6s.R.inc(332);if ((((lo == null)&&(__CLR4_4_100lpnrio6s.R.iget(333)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(334)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(335);throw new IllegalArgumentException("first argument to size() is null");
        }__CLR4_4_100lpnrio6s.R.inc(336);if ((((hi == null)&&(__CLR4_4_100lpnrio6s.R.iget(337)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(338)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(339);throw new IllegalArgumentException("second argument to size() is null");

        }__CLR4_4_100lpnrio6s.R.inc(340);if ((((lo.compareTo(hi) > 0)&&(__CLR4_4_100lpnrio6s.R.iget(341)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(342)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(343);return 0;
        }__CLR4_4_100lpnrio6s.R.inc(344);if ((((contains(hi))&&(__CLR4_4_100lpnrio6s.R.iget(345)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(346)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(347);return rank(hi) - rank(lo) + 1;
        }else              {__CLR4_4_100lpnrio6s.R.inc(348);return rank(hi) - rank(lo);
    }}finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Returns the height of the BST (for debugging).
     *
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {try{__CLR4_4_100lpnrio6s.R.inc(349);
        __CLR4_4_100lpnrio6s.R.inc(350);return height(root);
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}
    private int height(Node x) {try{__CLR4_4_100lpnrio6s.R.inc(351);
        __CLR4_4_100lpnrio6s.R.inc(352);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(353)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(354)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(355);return -1;
        }__CLR4_4_100lpnrio6s.R.inc(356);return 1 + Math.max(height(x.left), height(x.right));
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    /**
     * Returns the keys in the BST in level order (for debugging).
     *
     * @return the keys in the BST in level order traversal
     */
    public Iterable<Key> levelOrder() {try{__CLR4_4_100lpnrio6s.R.inc(357);
        __CLR4_4_100lpnrio6s.R.inc(358);Queue<Key> keys = new Queue<Key>();
        __CLR4_4_100lpnrio6s.R.inc(359);Queue<Node> queue = new Queue<Node>();
        __CLR4_4_100lpnrio6s.R.inc(360);queue.enqueue(root);
        __CLR4_4_100lpnrio6s.R.inc(361);while ((((!queue.isEmpty())&&(__CLR4_4_100lpnrio6s.R.iget(362)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(363)==0&false))) {{
            __CLR4_4_100lpnrio6s.R.inc(364);Node x = queue.dequeue();
            __CLR4_4_100lpnrio6s.R.inc(365);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(366)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(367)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(368);continue;
            }__CLR4_4_100lpnrio6s.R.inc(369);keys.enqueue(x.key);
            __CLR4_4_100lpnrio6s.R.inc(370);queue.enqueue(x.left);
            __CLR4_4_100lpnrio6s.R.inc(371);queue.enqueue(x.right);
        }
        }__CLR4_4_100lpnrio6s.R.inc(372);return keys;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

  /*************************************************************************
    *  Check integrity of BST data structure.
    ***************************************************************************/
    private boolean check() {try{__CLR4_4_100lpnrio6s.R.inc(373);
        __CLR4_4_100lpnrio6s.R.inc(374);if ((((!isBST())&&(__CLR4_4_100lpnrio6s.R.iget(375)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(376)==0&false)))            {__CLR4_4_100lpnrio6s.R.inc(377);StdOut.println("Not in symmetric order");
        }__CLR4_4_100lpnrio6s.R.inc(378);if ((((!isSizeConsistent())&&(__CLR4_4_100lpnrio6s.R.iget(379)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(380)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(381);StdOut.println("Subtree counts not consistent");
        }__CLR4_4_100lpnrio6s.R.inc(382);if ((((!isRankConsistent())&&(__CLR4_4_100lpnrio6s.R.iget(383)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(384)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(385);StdOut.println("Ranks not consistent");
        }__CLR4_4_100lpnrio6s.R.inc(386);return isBST() && isSizeConsistent() && isRankConsistent();
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {try{__CLR4_4_100lpnrio6s.R.inc(387);
        __CLR4_4_100lpnrio6s.R.inc(388);return isBST(root, null, null);
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {try{__CLR4_4_100lpnrio6s.R.inc(389);
        __CLR4_4_100lpnrio6s.R.inc(390);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(391)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(392)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(393);return true;
        }__CLR4_4_100lpnrio6s.R.inc(394);if ((((min != null && x.key.compareTo(min) <= 0)&&(__CLR4_4_100lpnrio6s.R.iget(395)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(396)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(397);return false;
        }__CLR4_4_100lpnrio6s.R.inc(398);if ((((max != null && x.key.compareTo(max) >= 0)&&(__CLR4_4_100lpnrio6s.R.iget(399)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(400)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(401);return false;
        }__CLR4_4_100lpnrio6s.R.inc(402);return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    // are the size fields correct?
    private boolean isSizeConsistent() {try{__CLR4_4_100lpnrio6s.R.inc(403); __CLR4_4_100lpnrio6s.R.inc(404);return isSizeConsistent(root); }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}
    private boolean isSizeConsistent(Node x) {try{__CLR4_4_100lpnrio6s.R.inc(405);
        __CLR4_4_100lpnrio6s.R.inc(406);if ((((x == null)&&(__CLR4_4_100lpnrio6s.R.iget(407)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(408)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(409);return true;
        }__CLR4_4_100lpnrio6s.R.inc(410);if ((((x.size != size(x.left) + size(x.right) + 1)&&(__CLR4_4_100lpnrio6s.R.iget(411)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(412)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(413);return false;
        }__CLR4_4_100lpnrio6s.R.inc(414);return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}} 

    // check that ranks are consistent
    private boolean isRankConsistent() {try{__CLR4_4_100lpnrio6s.R.inc(415);
        __CLR4_4_100lpnrio6s.R.inc(416);for (int i = 0; (((i < size())&&(__CLR4_4_100lpnrio6s.R.iget(417)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(418)==0&false)); i++)
            {__CLR4_4_100lpnrio6s.R.inc(419);if ((((i != rank(select(i)))&&(__CLR4_4_100lpnrio6s.R.iget(420)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(421)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(422);return false;
        }}__CLR4_4_100lpnrio6s.R.inc(423);for (Key key : keys())
            {__CLR4_4_100lpnrio6s.R.inc(424);if ((((key.compareTo(select(rank(key))) != 0)&&(__CLR4_4_100lpnrio6s.R.iget(425)!=0|true))||(__CLR4_4_100lpnrio6s.R.iget(426)==0&false))) {__CLR4_4_100lpnrio6s.R.inc(427);return false;
        }}__CLR4_4_100lpnrio6s.R.inc(428);return true;
    }finally{__CLR4_4_100lpnrio6s.R.flushNeeded();}}
}

/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
