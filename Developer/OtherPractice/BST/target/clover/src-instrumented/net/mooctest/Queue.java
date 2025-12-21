/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ *//******************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/13stacks/tobe.txt  
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

package net.mooctest;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code Queue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the first item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a singly linked list with a static nested class for
 *  linked-list nodes. See {@link LinkedQueue} for the version from the
 *  textbook that uses a non-static nested class.
 *  See {@link ResizingArrayQueue} for a version that uses a resizing array.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of an item in this queue
 */
public class Queue<Item> implements Iterable<Item> {public static class __CLR4_4_1bxbxlpnrio7w{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0036\u0030\u0032\u005c\u005c\u0031\u0033\u0034\u0034\u0038\u0034\u005c\u005c\u0042\u0053\u0054\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701503946885L,8589935092L,489,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {try{__CLR4_4_1bxbxlpnrio7w.R.inc(429);
        __CLR4_4_1bxbxlpnrio7w.R.inc(430);first = null;
        __CLR4_4_1bxbxlpnrio7w.R.inc(431);last  = null;
        __CLR4_4_1bxbxlpnrio7w.R.inc(432);n = 0;
    }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {try{__CLR4_4_1bxbxlpnrio7w.R.inc(433);
        __CLR4_4_1bxbxlpnrio7w.R.inc(434);return first == null;
    }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {try{__CLR4_4_1bxbxlpnrio7w.R.inc(435);
        __CLR4_4_1bxbxlpnrio7w.R.inc(436);return n;
    }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {try{__CLR4_4_1bxbxlpnrio7w.R.inc(437);
        __CLR4_4_1bxbxlpnrio7w.R.inc(438);if ((((isEmpty())&&(__CLR4_4_1bxbxlpnrio7w.R.iget(439)!=0|true))||(__CLR4_4_1bxbxlpnrio7w.R.iget(440)==0&false))) {__CLR4_4_1bxbxlpnrio7w.R.inc(441);throw new NoSuchElementException("Queue underflow");
        }__CLR4_4_1bxbxlpnrio7w.R.inc(442);return first.item;
    }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(Item item) {try{__CLR4_4_1bxbxlpnrio7w.R.inc(443);
        __CLR4_4_1bxbxlpnrio7w.R.inc(444);Node<Item> oldlast = last;
        __CLR4_4_1bxbxlpnrio7w.R.inc(445);last = new Node<Item>();
        __CLR4_4_1bxbxlpnrio7w.R.inc(446);last.item = item;
        __CLR4_4_1bxbxlpnrio7w.R.inc(447);last.next = null;
        __CLR4_4_1bxbxlpnrio7w.R.inc(448);if ((((isEmpty())&&(__CLR4_4_1bxbxlpnrio7w.R.iget(449)!=0|true))||(__CLR4_4_1bxbxlpnrio7w.R.iget(450)==0&false))) {__CLR4_4_1bxbxlpnrio7w.R.inc(451);first = last;
        }else           {__CLR4_4_1bxbxlpnrio7w.R.inc(452);oldlast.next = last;
        }__CLR4_4_1bxbxlpnrio7w.R.inc(453);n++;
    }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {try{__CLR4_4_1bxbxlpnrio7w.R.inc(454);
        __CLR4_4_1bxbxlpnrio7w.R.inc(455);if ((((isEmpty())&&(__CLR4_4_1bxbxlpnrio7w.R.iget(456)!=0|true))||(__CLR4_4_1bxbxlpnrio7w.R.iget(457)==0&false))) {__CLR4_4_1bxbxlpnrio7w.R.inc(458);throw new NoSuchElementException("Queue underflow");
        }__CLR4_4_1bxbxlpnrio7w.R.inc(459);Item item = first.item;
        __CLR4_4_1bxbxlpnrio7w.R.inc(460);first = first.next;
        __CLR4_4_1bxbxlpnrio7w.R.inc(461);n--;
        __CLR4_4_1bxbxlpnrio7w.R.inc(462);if ((((isEmpty())&&(__CLR4_4_1bxbxlpnrio7w.R.iget(463)!=0|true))||(__CLR4_4_1bxbxlpnrio7w.R.iget(464)==0&false))) {__CLR4_4_1bxbxlpnrio7w.R.inc(465);last = null;   // to avoid loitering
        }__CLR4_4_1bxbxlpnrio7w.R.inc(466);return item;
    }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {try{__CLR4_4_1bxbxlpnrio7w.R.inc(467);
        __CLR4_4_1bxbxlpnrio7w.R.inc(468);StringBuilder s = new StringBuilder();
        __CLR4_4_1bxbxlpnrio7w.R.inc(469);for (Item item : this) {{
            __CLR4_4_1bxbxlpnrio7w.R.inc(470);s.append(item);
            __CLR4_4_1bxbxlpnrio7w.R.inc(471);s.append(' ');
        }
        }__CLR4_4_1bxbxlpnrio7w.R.inc(472);return s.toString();
    }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}} 

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {try{__CLR4_4_1bxbxlpnrio7w.R.inc(473);
        __CLR4_4_1bxbxlpnrio7w.R.inc(474);return new ListIterator<Item>(first);  
    }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {try{__CLR4_4_1bxbxlpnrio7w.R.inc(475);
            __CLR4_4_1bxbxlpnrio7w.R.inc(476);current = first;
        }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}

        public boolean hasNext()  {try{__CLR4_4_1bxbxlpnrio7w.R.inc(477); __CLR4_4_1bxbxlpnrio7w.R.inc(478);return current != null;                     }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}
        public void remove()      {try{__CLR4_4_1bxbxlpnrio7w.R.inc(479); __CLR4_4_1bxbxlpnrio7w.R.inc(480);throw new UnsupportedOperationException();  }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}

        public Item next() {try{__CLR4_4_1bxbxlpnrio7w.R.inc(481);
            __CLR4_4_1bxbxlpnrio7w.R.inc(482);if ((((!hasNext())&&(__CLR4_4_1bxbxlpnrio7w.R.iget(483)!=0|true))||(__CLR4_4_1bxbxlpnrio7w.R.iget(484)==0&false))) {__CLR4_4_1bxbxlpnrio7w.R.inc(485);throw new NoSuchElementException();
            }__CLR4_4_1bxbxlpnrio7w.R.inc(486);Item item = current.item;
            __CLR4_4_1bxbxlpnrio7w.R.inc(487);current = current.next; 
            __CLR4_4_1bxbxlpnrio7w.R.inc(488);return item;
        }finally{__CLR4_4_1bxbxlpnrio7w.R.flushNeeded();}}
    }
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
