/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import sun.misc.Unsafe;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PauselessHashMap: A java.util.HashMap compatible Map implementation that
 * performs background resizing for inserts, avoiding the common "resize/rehash"
 * outlier experienced by normal HashMap.
 * <p>
 * get() and put() operations are "pauseless" in the sense that they do not
 * block during resizing of the map. Other operations, like remove(), putAll(),
 * clear(), and the derivation of keysets and such *will* block for pending
 * resize operations.
 * <p>
 * Like HashMap, PauselessHashMap provides no synchronization or thread-safe
 * behaviors on it's own, and MUST be externally synchronized if used by multiple
 * threads. The background resizing mechanism relies on the calling program
 * enforcing serialized access to all methods, and behavior is undefined if
 * concurrent access (for modification or otherwise) is allowed.
 * <p>
 * And like HashMap, PauselessHashMap is an implementation of Map. All optional
 * operations (adding and removing) are supported. Keys and values can be any objects.
 *
 */
public class PauselessHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>,
        Cloneable, Serializable {public static class __CLR4_4_100ly8e8yn3{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u0057\u006f\u0072\u006b\u005c\u005c\u004a\u0061\u0076\u0061\u005f\u0070\u0072\u0061\u0063\u0074\u0069\u0063\u0065\u005c\u005c\u0050\u0061\u0075\u0073\u0065\u006c\u0065\u0073\u0073\u0048\u0061\u0073\u0068\u004d\u0061\u0070\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1720165707408L,8589935092L,809,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    // If we want to use this as a drop-in for java.util.HashMap, we'll need the following UID:
    // public static final long serialVersionUID = 362498820763181265L;

    // When NOT using as a drop-in, we need our own UID:
    public static final long serialVersionUID = 318739534392781639L;

    /*
     * Actual count of entries
     */
    transient int elementCount;

    /*
     * The internal data structure to hold Entries
     */
    transient Entry<K, V>[] elementData;

    /*
     * The target data structure for holding Entries during resizing:
     */
    transient Entry<K, V>[] resizingIntoElementData;

    /*
     * modification count, to keep track of structural modifications between the
     * HashMap and the iterator
     */
    transient int modCount = 0;

    /*
     * default size that an HashMap created using the default constructor would
     * have.
     */
    public static final int DEFAULT_SIZE = 16;

    /*
     * maximum ratio of (stored elements)/(storage size) which does not lead to
     * rehash
     */
    final float loadFactor;

    /*
     * maximum number of elements that can be put in this map before having to
     * rehash
     */
    int threshold;

    /*
     * pendingResize: A true value indicates a resize has been requested, or is ongoing.
     */
    transient boolean pendingResize = false;

    /**
     * needToKickOffAnotherBackgroundResize: A true value indicates a background resize
     * needs to be kicked off (even if pendingResize is true)
     */
    transient boolean needToKickOffAnotherBackgroundResize = false;

    /*
     * backgroundResizeComplete: A true value indicates that the currently in-progress
     * resizing has completed all background operations.
     */
    transient boolean backgroundResizeComplete = false;   // Worked hard to not use volatile reads for this...

    /*
     * indicatedObservedResizingIntoTable: a non-volatile guard for observedResizingIntoTable.
     * Helps avoid writing to volatiles in the common case.
     */
    transient boolean indicatedObservedResizingIntoTable = false;

    /*
     * observedResizingIntoTable: A true value indicates a put operation has observed the
     * resizingIntoTable allocated by background resizers.
     */
    transient volatile boolean observedResizingIntoTable = false;

    /*
     * volatileUpdateIndicator: An atomic boolean used to perform volatile update actions
     * to enforce ordering (with set, get and lazySet) during background resizing operations.
     */
    transient AtomicBoolean volatileUpdateIndicator = new AtomicBoolean(false);

    /*
     * rehashMonitor: serializes background resizing operations against things that need that sort of thing.
     */
    transient final Object rehashMonitor = new Object();


    /*
     * cachedExpectedHeadsArray: Used to avoid uniqueness scans where possible during background resizing
     */
    transient Entry<K,V>[] cachedExpectedHeadsArray = null;

    // keySet and valuesCollection are taken from Apache Harmony's java.util.AbstractMap. They do
    // Not exist in Java SE version...

    // Lazily initialized key set.
    transient Set<K> keySet;

    transient Collection<V> valuesCollection;

    static class Entry<K, V> extends InternalMapEntry<K, V> {
        final int origKeyHash;
        Entry<K, V> next;
        boolean isValid = true;

        Entry(K theKey, int hash) {
            super(theKey, null);__CLR4_4_100ly8e8yn3.R.inc(1);try{__CLR4_4_100ly8e8yn3.R.inc(0);
            __CLR4_4_100ly8e8yn3.R.inc(2);this.origKeyHash = hash;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        Entry(K theKey, V theValue) {
            super(theKey, theValue);__CLR4_4_100ly8e8yn3.R.inc(4);try{__CLR4_4_100ly8e8yn3.R.inc(3);
            __CLR4_4_100ly8e8yn3.R.inc(5);origKeyHash = ((((theKey == null )&&(__CLR4_4_100ly8e8yn3.R.iget(6)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(7)==0&false))? 0 : computeHashCode(theKey));
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        void setInvalid() {try{__CLR4_4_100ly8e8yn3.R.inc(8);
            __CLR4_4_100ly8e8yn3.R.inc(9);isValid = false;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        void setValid() {try{__CLR4_4_100ly8e8yn3.R.inc(10);
            __CLR4_4_100ly8e8yn3.R.inc(11);isValid = true;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        boolean isValid() {try{__CLR4_4_100ly8e8yn3.R.inc(12);
            __CLR4_4_100ly8e8yn3.R.inc(13);return isValid;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        @SuppressWarnings("unchecked")
        public Object clone() {try{__CLR4_4_100ly8e8yn3.R.inc(14);
            __CLR4_4_100ly8e8yn3.R.inc(15);Entry<K, V> entry = (Entry<K, V>) super.clone();
            __CLR4_4_100ly8e8yn3.R.inc(16);if ((((next != null)&&(__CLR4_4_100ly8e8yn3.R.iget(17)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(18)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(19);entry.next = (Entry<K, V>) next.clone();
            }
            }__CLR4_4_100ly8e8yn3.R.inc(20);return entry;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
    }


    public static class AbstractMapIterator<K, V>  {
        public int position = 0;
        int expectedModCount;
        Entry<K, V> futureEntry;
        Entry<K, V> currentEntry;
        Entry<K, V> prevEntry;

        final PauselessHashMap<K, V> associatedMap;

        AbstractMapIterator(PauselessHashMap<K, V> hm) {try{__CLR4_4_100ly8e8yn3.R.inc(21);
            __CLR4_4_100ly8e8yn3.R.inc(22);associatedMap = hm;
            __CLR4_4_100ly8e8yn3.R.inc(23);expectedModCount = hm.modCount;
            __CLR4_4_100ly8e8yn3.R.inc(24);futureEntry = null;
            // Make sure any pending resize operation completes before we start this:
            __CLR4_4_100ly8e8yn3.R.inc(25);associatedMap.forceFinishResizing();
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public boolean hasNext() {try{__CLR4_4_100ly8e8yn3.R.inc(26);
            __CLR4_4_100ly8e8yn3.R.inc(27);if ((((futureEntry != null)&&(__CLR4_4_100ly8e8yn3.R.iget(28)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(29)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(30);return true;
            }
            }__CLR4_4_100ly8e8yn3.R.inc(31);while ((((position < associatedMap.elementData.length)&&(__CLR4_4_100ly8e8yn3.R.iget(32)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(33)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(34);if ((((associatedMap.elementData[position] == null)&&(__CLR4_4_100ly8e8yn3.R.iget(35)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(36)==0&false))) {{
                    __CLR4_4_100ly8e8yn3.R.inc(37);position++;
                } }else {{
                    __CLR4_4_100ly8e8yn3.R.inc(38);return true;
                }
            }}
            }__CLR4_4_100ly8e8yn3.R.inc(39);return false;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        final void checkConcurrentMod() throws ConcurrentModificationException {try{__CLR4_4_100ly8e8yn3.R.inc(40);
            __CLR4_4_100ly8e8yn3.R.inc(41);if ((((associatedMap.resizingIntoElementData != null)&&(__CLR4_4_100ly8e8yn3.R.iget(42)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(43)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(44);throw new ConcurrentModificationException();
            }
            }__CLR4_4_100ly8e8yn3.R.inc(45);if ((((expectedModCount != associatedMap.modCount)&&(__CLR4_4_100ly8e8yn3.R.iget(46)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(47)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(48);throw new ConcurrentModificationException();
            }
        }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @SuppressWarnings("unchecked")
        final void makeNext() {try{__CLR4_4_100ly8e8yn3.R.inc(49);
            __CLR4_4_100ly8e8yn3.R.inc(50);checkConcurrentMod();
            __CLR4_4_100ly8e8yn3.R.inc(51);if ((((!hasNext())&&(__CLR4_4_100ly8e8yn3.R.iget(52)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(53)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(54);throw new NoSuchElementException();
            }
            }__CLR4_4_100ly8e8yn3.R.inc(55);if ((((futureEntry == null)&&(__CLR4_4_100ly8e8yn3.R.iget(56)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(57)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(58);currentEntry = associatedMap.elementData[position++];
                __CLR4_4_100ly8e8yn3.R.inc(59);futureEntry = currentEntry.next;
                __CLR4_4_100ly8e8yn3.R.inc(60);prevEntry = null;
            } }else {{
                __CLR4_4_100ly8e8yn3.R.inc(61);if((((currentEntry!=null)&&(__CLR4_4_100ly8e8yn3.R.iget(62)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(63)==0&false))){{
                    __CLR4_4_100ly8e8yn3.R.inc(64);prevEntry = currentEntry;
                }
                }__CLR4_4_100ly8e8yn3.R.inc(65);currentEntry = futureEntry;
                __CLR4_4_100ly8e8yn3.R.inc(66);futureEntry = futureEntry.next;
            }
        }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public final void remove() {try{__CLR4_4_100ly8e8yn3.R.inc(67);
            __CLR4_4_100ly8e8yn3.R.inc(68);checkConcurrentMod();
            __CLR4_4_100ly8e8yn3.R.inc(69);if ((((currentEntry==null)&&(__CLR4_4_100ly8e8yn3.R.iget(70)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(71)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(72);throw new IllegalStateException();
            }
            }__CLR4_4_100ly8e8yn3.R.inc(73);if((((prevEntry==null)&&(__CLR4_4_100ly8e8yn3.R.iget(74)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(75)==0&false))){{
                __CLR4_4_100ly8e8yn3.R.inc(76);int index = currentEntry.origKeyHash & (associatedMap.elementData.length - 1);
                __CLR4_4_100ly8e8yn3.R.inc(77);associatedMap.elementData[index] = associatedMap.elementData[index].next;
            } }else {{
                __CLR4_4_100ly8e8yn3.R.inc(78);prevEntry.next = currentEntry.next;
            }
            }__CLR4_4_100ly8e8yn3.R.inc(79);currentEntry = null;
            __CLR4_4_100ly8e8yn3.R.inc(80);expectedModCount++;
            __CLR4_4_100ly8e8yn3.R.inc(81);associatedMap.modCount++;
            __CLR4_4_100ly8e8yn3.R.inc(82);associatedMap.elementCount--;

        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
    }


    public static class EntryIterator <K, V> extends AbstractMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {

        EntryIterator (PauselessHashMap<K, V> map) {
            super(map);__CLR4_4_100ly8e8yn3.R.inc(84);try{__CLR4_4_100ly8e8yn3.R.inc(83);
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public Map.Entry<K, V> next() {try{__CLR4_4_100ly8e8yn3.R.inc(85);
            __CLR4_4_100ly8e8yn3.R.inc(86);makeNext();
            __CLR4_4_100ly8e8yn3.R.inc(87);return currentEntry;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
    }

    public static class KeyIterator <K, V> extends AbstractMapIterator<K, V> implements Iterator<K> {

        KeyIterator (PauselessHashMap<K, V> map) {
            super(map);__CLR4_4_100ly8e8yn3.R.inc(89);try{__CLR4_4_100ly8e8yn3.R.inc(88);
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public K next() {try{__CLR4_4_100ly8e8yn3.R.inc(90);
            __CLR4_4_100ly8e8yn3.R.inc(91);makeNext();
            __CLR4_4_100ly8e8yn3.R.inc(92);return currentEntry.key;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
    }

    public static class ValueIterator <K, V> extends AbstractMapIterator<K, V> implements Iterator<V> {

        ValueIterator (PauselessHashMap<K, V> map) {
            super(map);__CLR4_4_100ly8e8yn3.R.inc(94);try{__CLR4_4_100ly8e8yn3.R.inc(93);
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public V next() {try{__CLR4_4_100ly8e8yn3.R.inc(95);
            __CLR4_4_100ly8e8yn3.R.inc(96);makeNext();
            __CLR4_4_100ly8e8yn3.R.inc(97);return currentEntry.value;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
    }

    static class HashMapEntrySet<KT, VT> extends AbstractSet<Map.Entry<KT, VT>> {
        public final PauselessHashMap<KT, VT> associatedMap;

        public HashMapEntrySet(PauselessHashMap<KT, VT> hm) {try{__CLR4_4_100ly8e8yn3.R.inc(98);
            __CLR4_4_100ly8e8yn3.R.inc(99);associatedMap = hm;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        PauselessHashMap<KT, VT> hashMap() {try{__CLR4_4_100ly8e8yn3.R.inc(100);
            __CLR4_4_100ly8e8yn3.R.inc(101);return associatedMap;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        public int size() {try{__CLR4_4_100ly8e8yn3.R.inc(102);
            __CLR4_4_100ly8e8yn3.R.inc(103);return associatedMap.elementCount;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        public void clear() {try{__CLR4_4_100ly8e8yn3.R.inc(104);
            __CLR4_4_100ly8e8yn3.R.inc(105);associatedMap.clear();
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        public boolean remove(Object object) {try{__CLR4_4_100ly8e8yn3.R.inc(106);
            __CLR4_4_100ly8e8yn3.R.inc(107);if ((((!associatedMap.pendingResize)&&(__CLR4_4_100ly8e8yn3.R.iget(108)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(109)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(110);return removeImpl(object);
            }
            }__CLR4_4_100ly8e8yn3.R.inc(111);synchronized (associatedMap.rehashMonitor) {
                __CLR4_4_100ly8e8yn3.R.inc(112);associatedMap.forceFinishResizing();
                __CLR4_4_100ly8e8yn3.R.inc(113);return removeImpl(object);
            }
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public boolean removeImpl(Object object) {try{__CLR4_4_100ly8e8yn3.R.inc(114);
            __CLR4_4_100ly8e8yn3.R.inc(115);if ((((object instanceof Map.Entry)&&(__CLR4_4_100ly8e8yn3.R.iget(116)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(117)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(118);Map.Entry<?, ?> oEntry = (Map.Entry<?, ?>) object;
                __CLR4_4_100ly8e8yn3.R.inc(119);Entry<KT,VT> entry = associatedMap.getEntry(oEntry.getKey());
                __CLR4_4_100ly8e8yn3.R.inc(120);if((((valuesEq(entry, oEntry))&&(__CLR4_4_100ly8e8yn3.R.iget(121)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(122)==0&false))) {{
                    __CLR4_4_100ly8e8yn3.R.inc(123);associatedMap.removeEntry(entry);
                    __CLR4_4_100ly8e8yn3.R.inc(124);return true;
                }
            }}
            }__CLR4_4_100ly8e8yn3.R.inc(125);return false;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        public boolean contains(Object object) {try{__CLR4_4_100ly8e8yn3.R.inc(126);
            __CLR4_4_100ly8e8yn3.R.inc(127);if ((((object instanceof Map.Entry)&&(__CLR4_4_100ly8e8yn3.R.iget(128)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(129)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(130);Map.Entry<?, ?> oEntry = (Map.Entry<?, ?>) object;
                __CLR4_4_100ly8e8yn3.R.inc(131);Entry<KT, VT> entry = associatedMap.getEntry(oEntry.getKey());
                __CLR4_4_100ly8e8yn3.R.inc(132);return valuesEq(entry, oEntry);
            }
            }__CLR4_4_100ly8e8yn3.R.inc(133);return false;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public static boolean valuesEq(Entry entry, Map.Entry<?, ?> oEntry) {try{__CLR4_4_100ly8e8yn3.R.inc(134);
            __CLR4_4_100ly8e8yn3.R.inc(135);return (entry != null) &&
                    (((((entry.value == null) )&&(__CLR4_4_100ly8e8yn3.R.iget(136)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(137)==0&false))?
                            (oEntry.getValue() == null) :
                            (areEqualValues(entry.value, oEntry.getValue())));
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        public Iterator<Map.Entry<KT, VT>> iterator() {try{__CLR4_4_100ly8e8yn3.R.inc(138);
            __CLR4_4_100ly8e8yn3.R.inc(139);return new EntryIterator<KT,VT> (associatedMap);
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
    }

    /**
     * Create a new element array
     *
     * @param s
     * @return Reference to the element array
     */
    @SuppressWarnings("unchecked")
    Entry<K, V>[] newElementArray(int s) {try{__CLR4_4_100ly8e8yn3.R.inc(140);
        __CLR4_4_100ly8e8yn3.R.inc(141);return new Entry[s];
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Constructs a new empty {@code HashMap} instance.
     */
    public PauselessHashMap() {
        this(DEFAULT_SIZE);__CLR4_4_100ly8e8yn3.R.inc(143);try{__CLR4_4_100ly8e8yn3.R.inc(142);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Constructs a new {@code HashMap} instance with the specified capacity.
     *
     * @param capacity
     *            the initial capacity of this hash map.
     * @throws IllegalArgumentException
     *                when the capacity is less than zero.
     */
    public PauselessHashMap(int capacity) {
        this(capacity, 0.75f);__CLR4_4_100ly8e8yn3.R.inc(145);try{__CLR4_4_100ly8e8yn3.R.inc(144);  // default load factor of 0.75
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Calculates the capacity of storage required for storing given number of
     * elements
     *
     * @param x
     *            number of elements
     * @return storage size
     */
    public static final int calculateCapacity(int x) {try{__CLR4_4_100ly8e8yn3.R.inc(146);
        __CLR4_4_100ly8e8yn3.R.inc(147);if((((x >= 1 << 30)&&(__CLR4_4_100ly8e8yn3.R.iget(148)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(149)==0&false))){{
            __CLR4_4_100ly8e8yn3.R.inc(150);return 1 << 30;
        }
        }__CLR4_4_100ly8e8yn3.R.inc(151);if((((x == 0)&&(__CLR4_4_100ly8e8yn3.R.iget(152)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(153)==0&false))){{
            __CLR4_4_100ly8e8yn3.R.inc(154);return 16;
        }
        }__CLR4_4_100ly8e8yn3.R.inc(155);x = x -1;
        __CLR4_4_100ly8e8yn3.R.inc(156);x |= x >> 1;
        __CLR4_4_100ly8e8yn3.R.inc(157);x |= x >> 2;
        __CLR4_4_100ly8e8yn3.R.inc(158);x |= x >> 4;
        __CLR4_4_100ly8e8yn3.R.inc(159);x |= x >> 8;
        __CLR4_4_100ly8e8yn3.R.inc(160);x |= x >> 16;
        __CLR4_4_100ly8e8yn3.R.inc(161);return x + 1;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Constructs a new {@code HashMap} instance with the specified capacity and
     * load factor.
     *
     * @param capacity
     *            the initial capacity of this hash map.
     * @param loadFactor
     *            the initial load factor.
     * @throws IllegalArgumentException
     *                when the capacity is less than zero or the load factor is
     *                less or equal to zero.
     */
    public PauselessHashMap(int capacity, float loadFactor) {try{__CLR4_4_100ly8e8yn3.R.inc(162);
        __CLR4_4_100ly8e8yn3.R.inc(163);if ((((capacity >= 0 && loadFactor > 0)&&(__CLR4_4_100ly8e8yn3.R.iget(164)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(165)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(166);capacity = calculateCapacity(capacity);
            __CLR4_4_100ly8e8yn3.R.inc(167);elementCount = 0;
            __CLR4_4_100ly8e8yn3.R.inc(168);elementData = newElementArray(capacity);
            __CLR4_4_100ly8e8yn3.R.inc(169);this.loadFactor = loadFactor;
            __CLR4_4_100ly8e8yn3.R.inc(170);computeThreshold();
        } }else {{
            __CLR4_4_100ly8e8yn3.R.inc(171);throw new IllegalArgumentException();
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Constructs a new {@code HashMap} instance containing the mappings from
     * the specified map.
     *
     * @param map
     *            the mappings to add.
     */
    public PauselessHashMap(Map<? extends K, ? extends V> map) {
        this(calculateCapacity(map.size()));__CLR4_4_100ly8e8yn3.R.inc(173);try{__CLR4_4_100ly8e8yn3.R.inc(172);
        __CLR4_4_100ly8e8yn3.R.inc(174);putAllImpl(map);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Removes all mappings from this hash map, leaving it empty.
     *
     * @see #isEmpty
     * @see #size
     */
    @Override
    public void clear() {try{__CLR4_4_100ly8e8yn3.R.inc(175);
        __CLR4_4_100ly8e8yn3.R.inc(176);if ((((!pendingResize)&&(__CLR4_4_100ly8e8yn3.R.iget(177)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(178)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(179);clearImpl();
        } }else {{
            __CLR4_4_100ly8e8yn3.R.inc(180);synchronized (rehashMonitor) {
                __CLR4_4_100ly8e8yn3.R.inc(181);forceFinishResizing();
                __CLR4_4_100ly8e8yn3.R.inc(182);clearImpl();
            }
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    public void clearImpl() {try{__CLR4_4_100ly8e8yn3.R.inc(183);
        __CLR4_4_100ly8e8yn3.R.inc(184);if ((((elementCount > 0)&&(__CLR4_4_100ly8e8yn3.R.iget(185)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(186)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(187);elementCount = 0;
            // Arrays.fill(elementData, null);
            __CLR4_4_100ly8e8yn3.R.inc(188);for (int i = 0; (((i < elementData.length)&&(__CLR4_4_100ly8e8yn3.R.iget(189)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(190)==0&false)); i++) {{
                __CLR4_4_100ly8e8yn3.R.inc(191);elementData[i] = null;
            }
            }__CLR4_4_100ly8e8yn3.R.inc(192);modCount++;
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Returns a shallow copy of this map.
     *
     * @return a shallow copy of this map.
     */
    @Override
    public Object clone() {try{__CLR4_4_100ly8e8yn3.R.inc(193);
        __CLR4_4_100ly8e8yn3.R.inc(194);if ((((!pendingResize)&&(__CLR4_4_100ly8e8yn3.R.iget(195)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(196)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(197);return cloneImpl();
        } }else {{
            __CLR4_4_100ly8e8yn3.R.inc(198);synchronized (rehashMonitor) {
                __CLR4_4_100ly8e8yn3.R.inc(199);forceFinishResizing();
                __CLR4_4_100ly8e8yn3.R.inc(200);return cloneImpl();
            }
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    @SuppressWarnings("unchecked")
    public Object cloneImpl() {try{__CLR4_4_100ly8e8yn3.R.inc(201);
        __CLR4_4_100ly8e8yn3.R.inc(202);try {
            __CLR4_4_100ly8e8yn3.R.inc(203);PauselessHashMap<K, V> map = (PauselessHashMap<K, V>) super.clone();
            __CLR4_4_100ly8e8yn3.R.inc(204);map.keySet = null;
            __CLR4_4_100ly8e8yn3.R.inc(205);map.valuesCollection = null;
            __CLR4_4_100ly8e8yn3.R.inc(206);map.elementCount = 0;
            __CLR4_4_100ly8e8yn3.R.inc(207);map.elementData = newElementArray(elementData.length);
            __CLR4_4_100ly8e8yn3.R.inc(208);map.putAll(this);

            __CLR4_4_100ly8e8yn3.R.inc(209);return map;
        } catch (CloneNotSupportedException e) {
            __CLR4_4_100ly8e8yn3.R.inc(210);return null;
        }
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Computes the threshold for rehashing
     */
    public void computeThreshold() {try{__CLR4_4_100ly8e8yn3.R.inc(211);
        __CLR4_4_100ly8e8yn3.R.inc(212);Entry<K, V> array[] = ((((resizingIntoElementData != null) )&&(__CLR4_4_100ly8e8yn3.R.iget(213)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(214)==0&false))? resizingIntoElementData : elementData;
        __CLR4_4_100ly8e8yn3.R.inc(215);threshold = (int) (array.length * loadFactor);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Returns whether this map contains the specified key.
     *
     * @param key
     *            the key to search for.
     * @return {@code true} if this map contains the specified key,
     *         {@code false} otherwise.
     */
    @Override
    public boolean containsKey(Object key) {try{__CLR4_4_100ly8e8yn3.R.inc(216);
        __CLR4_4_100ly8e8yn3.R.inc(217);Entry<K, V> m = getEntry(key);
        __CLR4_4_100ly8e8yn3.R.inc(218);return m != null;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Returns whether this map contains the specified value.
     *
     * @param value
     *            the value to search for.
     * @return {@code true} if this map contains the specified value,
     *         {@code false} otherwise.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean containsValue(Object value) {try{__CLR4_4_100ly8e8yn3.R.inc(219);
        __CLR4_4_100ly8e8yn3.R.inc(220);if ((((value != null)&&(__CLR4_4_100ly8e8yn3.R.iget(221)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(222)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(223);for (int i = 0; (((i < elementData.length)&&(__CLR4_4_100ly8e8yn3.R.iget(224)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(225)==0&false)); i++) {{
                __CLR4_4_100ly8e8yn3.R.inc(226);Entry<K, V> entry = elementData[i];
                __CLR4_4_100ly8e8yn3.R.inc(227);while ((((entry != null)&&(__CLR4_4_100ly8e8yn3.R.iget(228)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(229)==0&false))) {{
                    __CLR4_4_100ly8e8yn3.R.inc(230);if ((((areEqualValues(value, entry.value))&&(__CLR4_4_100ly8e8yn3.R.iget(231)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(232)==0&false))) {{
                        __CLR4_4_100ly8e8yn3.R.inc(233);return true;
                    }
                    }__CLR4_4_100ly8e8yn3.R.inc(234);entry = entry.next;
                }
            }}
        }} }else {{
            __CLR4_4_100ly8e8yn3.R.inc(235);for (int i = 0; (((i < elementData.length)&&(__CLR4_4_100ly8e8yn3.R.iget(236)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(237)==0&false)); i++) {{
                __CLR4_4_100ly8e8yn3.R.inc(238);Entry<K, V> entry = elementData[i];
                __CLR4_4_100ly8e8yn3.R.inc(239);while ((((entry != null)&&(__CLR4_4_100ly8e8yn3.R.iget(240)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(241)==0&false))) {{
                    __CLR4_4_100ly8e8yn3.R.inc(242);if ((((entry.value == null)&&(__CLR4_4_100ly8e8yn3.R.iget(243)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(244)==0&false))) {{
                        __CLR4_4_100ly8e8yn3.R.inc(245);return true;
                    }
                    }__CLR4_4_100ly8e8yn3.R.inc(246);entry = entry.next;
                }
            }}
        }}

        // Look in the resizing into Entry store, if one exists:
        }__CLR4_4_100ly8e8yn3.R.inc(247);if ((((resizingIntoElementData == null)&&(__CLR4_4_100ly8e8yn3.R.iget(248)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(249)==0&false)))
            {__CLR4_4_100ly8e8yn3.R.inc(250);return false;

        }__CLR4_4_100ly8e8yn3.R.inc(251);if ((((value != null)&&(__CLR4_4_100ly8e8yn3.R.iget(252)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(253)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(254);for (int i = 0; (((i < resizingIntoElementData.length)&&(__CLR4_4_100ly8e8yn3.R.iget(255)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(256)==0&false)); i++) {{
                __CLR4_4_100ly8e8yn3.R.inc(257);Entry<K, V> entry = resizingIntoElementData[i];
                __CLR4_4_100ly8e8yn3.R.inc(258);while ((((entry != null)&&(__CLR4_4_100ly8e8yn3.R.iget(259)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(260)==0&false))) {{
                    __CLR4_4_100ly8e8yn3.R.inc(261);if ((((areEqualValues(value, entry.value))&&(__CLR4_4_100ly8e8yn3.R.iget(262)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(263)==0&false))) {{
                        __CLR4_4_100ly8e8yn3.R.inc(264);return true;
                    }
                    }__CLR4_4_100ly8e8yn3.R.inc(265);entry = entry.next;
                }
            }}
        }} }else {{
            __CLR4_4_100ly8e8yn3.R.inc(266);for (int i = 0; (((i < resizingIntoElementData.length)&&(__CLR4_4_100ly8e8yn3.R.iget(267)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(268)==0&false)); i++) {{
                __CLR4_4_100ly8e8yn3.R.inc(269);Entry<K, V> entry = resizingIntoElementData[i];
                __CLR4_4_100ly8e8yn3.R.inc(270);while ((((entry != null)&&(__CLR4_4_100ly8e8yn3.R.iget(271)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(272)==0&false))) {{
                    __CLR4_4_100ly8e8yn3.R.inc(273);if ((((entry.value == null)&&(__CLR4_4_100ly8e8yn3.R.iget(274)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(275)==0&false))) {{
                        __CLR4_4_100ly8e8yn3.R.inc(276);return true;
                    }
                    }__CLR4_4_100ly8e8yn3.R.inc(277);entry = entry.next;
                }
            }}
        }}

        }__CLR4_4_100ly8e8yn3.R.inc(278);return false;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Returns a set containing all of the mappings in this map. Each mapping is
     * an instance of {@link java.util.Map.Entry}. As the set is backed by this map,
     * changes in one will be reflected in the other.
     *
     * @return a set of the mappings.
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {try{__CLR4_4_100ly8e8yn3.R.inc(279);
        __CLR4_4_100ly8e8yn3.R.inc(280);return new HashMapEntrySet<K, V>(this);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Returns the value of the mapping with the specified key.
     *
     * @param key
     *            the key.
     * @return the value of the mapping with the specified key, or {@code null}
     *         if no mapping for the specified key is found.
     */
    @Override
    public V get(Object key) {try{__CLR4_4_100ly8e8yn3.R.inc(281);
        __CLR4_4_100ly8e8yn3.R.inc(282);Entry<K, V> m = getEntry(key);
        __CLR4_4_100ly8e8yn3.R.inc(283);if ((((m != null)&&(__CLR4_4_100ly8e8yn3.R.iget(284)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(285)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(286);return m.value;
        }
        }__CLR4_4_100ly8e8yn3.R.inc(287);return null;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    final Entry<K, V> getEntry(Object key) {try{__CLR4_4_100ly8e8yn3.R.inc(288);
        __CLR4_4_100ly8e8yn3.R.inc(289);Entry<K, V> m;
        __CLR4_4_100ly8e8yn3.R.inc(290);if ((((key == null)&&(__CLR4_4_100ly8e8yn3.R.iget(291)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(292)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(293);m = findNullKeyEntry();
        } }else {{
            __CLR4_4_100ly8e8yn3.R.inc(294);int hash = computeHashCode(key);
            __CLR4_4_100ly8e8yn3.R.inc(295);int index = hash & (elementData.length - 1);
            __CLR4_4_100ly8e8yn3.R.inc(296);m = findNonNullKeyEntry(key, index, hash);
        }
        }__CLR4_4_100ly8e8yn3.R.inc(297);return m;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    final Entry<K,V> findNonNullKeyEntry(Object key, int index, int keyHash) {try{__CLR4_4_100ly8e8yn3.R.inc(298);
        __CLR4_4_100ly8e8yn3.R.inc(299);Entry<K,V> m = findNonNullKeyEntryInElementData(key, index, keyHash);
        __CLR4_4_100ly8e8yn3.R.inc(300);if ((((m != null)&&(__CLR4_4_100ly8e8yn3.R.iget(301)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(302)==0&false)))
            {__CLR4_4_100ly8e8yn3.R.inc(303);return m;

        // Look in the resizing into Entry store, if one exists:
        }__CLR4_4_100ly8e8yn3.R.inc(304);if ((((resizingIntoElementData == null)&&(__CLR4_4_100ly8e8yn3.R.iget(305)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(306)==0&false)))
            {__CLR4_4_100ly8e8yn3.R.inc(307);return null;

        }__CLR4_4_100ly8e8yn3.R.inc(308);int indexInResizedArray = keyHash & (resizingIntoElementData.length - 1);
        __CLR4_4_100ly8e8yn3.R.inc(309);return findNonNullKeyEntryInResizingIntoElementData(key, indexInResizedArray, keyHash);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    @SuppressWarnings("unchecked")
    final Entry<K,V> findNonNullKeyEntryInElementData(Object key, int index, int keyHash) {try{__CLR4_4_100ly8e8yn3.R.inc(310);
        __CLR4_4_100ly8e8yn3.R.inc(311);return findNonNullKeyEntryInChain(key, elementData[index], keyHash);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    @SuppressWarnings("unchecked")
    final Entry<K,V> findNonNullKeyEntryInResizingIntoElementData(Object key, int index, int keyHash) {try{__CLR4_4_100ly8e8yn3.R.inc(312);
        __CLR4_4_100ly8e8yn3.R.inc(313);return findNonNullKeyEntryInChain(key, resizingIntoElementData[index], keyHash);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    final Entry<K,V> findNonNullKeyEntryInChain(Object key, Entry<K,V> chainHead, int keyHash) {try{__CLR4_4_100ly8e8yn3.R.inc(314);
        __CLR4_4_100ly8e8yn3.R.inc(315);while ((((chainHead != null
                && ((!chainHead.isValid()) || chainHead.origKeyHash != keyHash || !areEqualKeys(key, chainHead.key)))&&(__CLR4_4_100ly8e8yn3.R.iget(316)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(317)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(318);chainHead = chainHead.next;
        }
        }__CLR4_4_100ly8e8yn3.R.inc(319);return chainHead;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    final Entry<K,V> findNullKeyEntry() {try{__CLR4_4_100ly8e8yn3.R.inc(320);
        __CLR4_4_100ly8e8yn3.R.inc(321);Entry<K,V> m = findNullKeyEntryInElementData();
        __CLR4_4_100ly8e8yn3.R.inc(322);if ((((m != null)&&(__CLR4_4_100ly8e8yn3.R.iget(323)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(324)==0&false)))
            {__CLR4_4_100ly8e8yn3.R.inc(325);return m;

        // Look in the resizing into Entry store, if one exists:
        }__CLR4_4_100ly8e8yn3.R.inc(326);if ((((resizingIntoElementData == null)&&(__CLR4_4_100ly8e8yn3.R.iget(327)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(328)==0&false)))
            {__CLR4_4_100ly8e8yn3.R.inc(329);return null;
        }__CLR4_4_100ly8e8yn3.R.inc(330);return findNullKeyEntryInResizingIntoElementData();
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    @SuppressWarnings("unchecked")
    final Entry<K,V> findNullKeyEntryInElementData() {try{__CLR4_4_100ly8e8yn3.R.inc(331);
        __CLR4_4_100ly8e8yn3.R.inc(332);return findNullKeyEntryInChain(elementData[0]);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    @SuppressWarnings("unchecked")
    final Entry<K,V> findNullKeyEntryInResizingIntoElementData() {try{__CLR4_4_100ly8e8yn3.R.inc(333);
        __CLR4_4_100ly8e8yn3.R.inc(334);return findNullKeyEntryInChain(resizingIntoElementData[0]);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    final Entry<K,V> findNullKeyEntryInChain(Entry<K,V> chainHead) {try{__CLR4_4_100ly8e8yn3.R.inc(335);
        __CLR4_4_100ly8e8yn3.R.inc(336);while ((((chainHead != null && ((!chainHead.isValid()) || (chainHead.key != null)))&&(__CLR4_4_100ly8e8yn3.R.iget(337)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(338)==0&false)))
            {__CLR4_4_100ly8e8yn3.R.inc(339);chainHead = chainHead.next;
        }__CLR4_4_100ly8e8yn3.R.inc(340);return chainHead;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Returns whether this map is empty.
     *
     * @return {@code true} if this map has no elements, {@code false}
     *         otherwise.
     * @see #size()
     */
    @Override
    public boolean isEmpty() {try{__CLR4_4_100ly8e8yn3.R.inc(341);
        __CLR4_4_100ly8e8yn3.R.inc(342);return elementCount == 0;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Returns a set of the keys contained in this map. The set is backed by
     * this map so changes to one are reflected by the other. The set does not
     * support adding.
     *
     * @return a set of the keys.
     */
    @Override
    public Set<K> keySet() {try{__CLR4_4_100ly8e8yn3.R.inc(343);
        __CLR4_4_100ly8e8yn3.R.inc(344);if ((((keySet == null)&&(__CLR4_4_100ly8e8yn3.R.iget(345)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(346)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(347);keySet = new AbstractSet<K>() {
                @Override
                public boolean contains(Object object) {try{__CLR4_4_100ly8e8yn3.R.inc(348);
                    __CLR4_4_100ly8e8yn3.R.inc(349);return containsKey(object);
                }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

                @Override
                public int size() {try{__CLR4_4_100ly8e8yn3.R.inc(350);
                    __CLR4_4_100ly8e8yn3.R.inc(351);return PauselessHashMap.this.size();
                }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

                @Override
                public void clear() {try{__CLR4_4_100ly8e8yn3.R.inc(352);
                    __CLR4_4_100ly8e8yn3.R.inc(353);PauselessHashMap.this.clear();
                }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

                @Override
                public boolean remove(Object key) {try{__CLR4_4_100ly8e8yn3.R.inc(354);
                    __CLR4_4_100ly8e8yn3.R.inc(355);Entry<K, V> entry;
                    __CLR4_4_100ly8e8yn3.R.inc(356);if ((((!pendingResize)&&(__CLR4_4_100ly8e8yn3.R.iget(357)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(358)==0&false))) {{
                        __CLR4_4_100ly8e8yn3.R.inc(359);entry = PauselessHashMap.this.removeEntry(key);
                    } }else {{
                        __CLR4_4_100ly8e8yn3.R.inc(360);synchronized (rehashMonitor) {
                            __CLR4_4_100ly8e8yn3.R.inc(361);PauselessHashMap.this.forceFinishResizing();
                            __CLR4_4_100ly8e8yn3.R.inc(362);entry = PauselessHashMap.this.removeEntry(key);
                        }
                    }
                    }__CLR4_4_100ly8e8yn3.R.inc(363);return entry != null;
                }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

                @Override
                public Iterator<K> iterator() {try{__CLR4_4_100ly8e8yn3.R.inc(364);
                    __CLR4_4_100ly8e8yn3.R.inc(365);return new KeyIterator<K,V> (PauselessHashMap.this);
                }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
            };
        }
        }__CLR4_4_100ly8e8yn3.R.inc(366);return keySet;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Maps the specified key to the specified value.
     *
     * @param key
     *            the key.
     * @param value
     *            the value.
     * @return the value of any previous mapping with the specified key or
     *         {@code null} if there was no such mapping.
     */
    @Override
    public V put(K key, V value) {try{__CLR4_4_100ly8e8yn3.R.inc(367);
        __CLR4_4_100ly8e8yn3.R.inc(368);return putImpl(key, value);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    public V putImpl(K key, V value) {try{__CLR4_4_100ly8e8yn3.R.inc(369);
        __CLR4_4_100ly8e8yn3.R.inc(370);Entry<K,V> entry;
        __CLR4_4_100ly8e8yn3.R.inc(371);int index = 0;
        __CLR4_4_100ly8e8yn3.R.inc(372);int hash = 0;
        __CLR4_4_100ly8e8yn3.R.inc(373);V result = null; // Retain previous value to return to caller

        __CLR4_4_100ly8e8yn3.R.inc(374);if((((key == null)&&(__CLR4_4_100ly8e8yn3.R.iget(375)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(376)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(377);entry = findNullKeyEntryInElementData();
        } }else {{
            __CLR4_4_100ly8e8yn3.R.inc(378);hash = computeHashCode(key);
            __CLR4_4_100ly8e8yn3.R.inc(379);index = hash & (elementData.length - 1);
            __CLR4_4_100ly8e8yn3.R.inc(380);entry = findNonNullKeyEntryInElementData(key, index, hash);
        }

        }__CLR4_4_100ly8e8yn3.R.inc(381);if ((((resizingIntoElementData == null)&&(__CLR4_4_100ly8e8yn3.R.iget(382)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(383)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(384);if ((((entry == null)&&(__CLR4_4_100ly8e8yn3.R.iget(385)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(386)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(387);entry = createHashedEntry(key, index, hash);
                __CLR4_4_100ly8e8yn3.R.inc(388);entry.value = value;
                __CLR4_4_100ly8e8yn3.R.inc(389);modCount++;
                __CLR4_4_100ly8e8yn3.R.inc(390);if ((((++elementCount > threshold)&&(__CLR4_4_100ly8e8yn3.R.iget(391)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(392)==0&false))) {{
                    __CLR4_4_100ly8e8yn3.R.inc(393);rehash();
                }
                }__CLR4_4_100ly8e8yn3.R.inc(394);return null;
            }
            }__CLR4_4_100ly8e8yn3.R.inc(395);result = entry.value;
            __CLR4_4_100ly8e8yn3.R.inc(396);entry.value = value;
            __CLR4_4_100ly8e8yn3.R.inc(397);return result;
        }

        // Coordinate with the pending background resize:
        }__CLR4_4_100ly8e8yn3.R.inc(398);if ((((!indicatedObservedResizingIntoTable)&&(__CLR4_4_100ly8e8yn3.R.iget(399)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(400)==0&false))) {{
            // Use non-volatile boolean indicator to avoid reading or writing volatile one unless needed:
            __CLR4_4_100ly8e8yn3.R.inc(401);indicatedObservedResizingIntoTable = true;
            __CLR4_4_100ly8e8yn3.R.inc(402);observedResizingIntoTable = true;
        }

        }__CLR4_4_100ly8e8yn3.R.inc(403);if ((((entry != null)&&(__CLR4_4_100ly8e8yn3.R.iget(404)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(405)==0&false))) {{
            // (only) if we found an entry before, update it:
            __CLR4_4_100ly8e8yn3.R.inc(406);result = entry.value;
            __CLR4_4_100ly8e8yn3.R.inc(407);entry.value = value;

            // If an entry already exists for this key, make sure the key we use from now on
            // is the actual key instance found in the original matching entry, and not just
            // the input key that matches it. This makes sure no new key instances are
            // introduced into the map when non-inserting puts  happen during resizing.
            // If we need to create a new entry in the target array to satisfy the put,
            // we'll want to create it with the same key instance.
            __CLR4_4_100ly8e8yn3.R.inc(408);key = entry.key;

            // Need to force a StoreStore fence here to order against next update:
            __CLR4_4_100ly8e8yn3.R.inc(409);volatileUpdateIndicator.lazySet(true);
        }

        }__CLR4_4_100ly8e8yn3.R.inc(410);Entry<K,V> resizingIntoEntry;

        __CLR4_4_100ly8e8yn3.R.inc(411);if((((key == null)&&(__CLR4_4_100ly8e8yn3.R.iget(412)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(413)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(414);resizingIntoEntry = findNullKeyEntryInResizingIntoElementData();
        } }else {{
            // No need to re-compute hash here, the prior if (key != null) took care of it.
            // hash = computeHashCode(key);
            __CLR4_4_100ly8e8yn3.R.inc(415);index = hash & (resizingIntoElementData.length - 1);
            __CLR4_4_100ly8e8yn3.R.inc(416);resizingIntoEntry = findNonNullKeyEntryInResizingIntoElementData(key, index, hash);
        }

        }__CLR4_4_100ly8e8yn3.R.inc(417);if ((((resizingIntoEntry == null)&&(__CLR4_4_100ly8e8yn3.R.iget(418)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(419)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(420);resizingIntoEntry = createHashedEntry(key, index, hash);
            __CLR4_4_100ly8e8yn3.R.inc(421);if ((((entry == null)&&(__CLR4_4_100ly8e8yn3.R.iget(422)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(423)==0&false))) {{
                // We should only count this as an actual addition if no entry was
                // found in the elementData scan done earlier. If one was found there,
                // this insertion is simply covering a resizing race, and no actual
                // net new entries are being created.
                __CLR4_4_100ly8e8yn3.R.inc(424);modCount++;
                __CLR4_4_100ly8e8yn3.R.inc(425);elementCount++;
            }
        }} }else  {__CLR4_4_100ly8e8yn3.R.inc(426);if ((((result == null)&&(__CLR4_4_100ly8e8yn3.R.iget(427)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(428)==0&false))) {{
            // If no result value was retained from previous find, retain this one.
            __CLR4_4_100ly8e8yn3.R.inc(429);result = resizingIntoEntry.value;
        }

        }}__CLR4_4_100ly8e8yn3.R.inc(430);resizingIntoEntry.value = value;

        // backgroundResizeComplete is (very intentionally) not volatile, which means that we may
        // "take a while" before noticing a completed background resize operation. E.g., if put
        // does not insert, no forced barriers are sure to occur that would cause us to observe a new
        // backgroundResizeComplete value. However, since any insert (in this state) will use
        // a CAS operation, which has volatile read+write semantics, it will force this thread to
        // re-read backgroundResizeComplete after the insert.
        // We are therefore assured that at most one extra insert will happen before we notice this...
        __CLR4_4_100ly8e8yn3.R.inc(431);if ((((backgroundResizeComplete)&&(__CLR4_4_100ly8e8yn3.R.iget(432)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(433)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(434);finishResizing();
        }

        // Make sure to kick off a resize if needed:
        }__CLR4_4_100ly8e8yn3.R.inc(435);if ((((needToKickOffAnotherBackgroundResize)&&(__CLR4_4_100ly8e8yn3.R.iget(436)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(437)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(438);rehash();
        }

        }__CLR4_4_100ly8e8yn3.R.inc(439);return result;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    void forceFinishResizing() {try{__CLR4_4_100ly8e8yn3.R.inc(440);
        __CLR4_4_100ly8e8yn3.R.inc(441);while ((((pendingResize)&&(__CLR4_4_100ly8e8yn3.R.iget(442)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(443)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(444);try {
                __CLR4_4_100ly8e8yn3.R.inc(445);synchronized (rehashMonitor) {
                    __CLR4_4_100ly8e8yn3.R.inc(446);if ((((backgroundResizeComplete)&&(__CLR4_4_100ly8e8yn3.R.iget(447)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(448)==0&false))) {{
                        __CLR4_4_100ly8e8yn3.R.inc(449);finishResizing();
                    } }else {{
                        __CLR4_4_100ly8e8yn3.R.inc(450);if ((((resizingIntoElementData != null)&&(__CLR4_4_100ly8e8yn3.R.iget(451)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(452)==0&false))) {{
                            __CLR4_4_100ly8e8yn3.R.inc(453);observedResizingIntoTable = true;
                        }
                        }__CLR4_4_100ly8e8yn3.R.inc(454);if ((((needToKickOffAnotherBackgroundResize)&&(__CLR4_4_100ly8e8yn3.R.iget(455)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(456)==0&false))) {{
                            __CLR4_4_100ly8e8yn3.R.inc(457);rehash();
                        }
                        // Still in progress or pending, wait for a kick:
                        }__CLR4_4_100ly8e8yn3.R.inc(458);rehashMonitor.wait(1);
                    }
                }}
            } catch (InterruptedException e) {
            }
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    void finishResizing() {try{__CLR4_4_100ly8e8yn3.R.inc(459);
        __CLR4_4_100ly8e8yn3.R.inc(460);elementData = resizingIntoElementData;
        __CLR4_4_100ly8e8yn3.R.inc(461);resizingIntoElementData = null;
        __CLR4_4_100ly8e8yn3.R.inc(462);observedResizingIntoTable = false;
        __CLR4_4_100ly8e8yn3.R.inc(463);indicatedObservedResizingIntoTable = false;
        __CLR4_4_100ly8e8yn3.R.inc(464);backgroundResizeComplete = false;
        __CLR4_4_100ly8e8yn3.R.inc(465);pendingResize = false;
        __CLR4_4_100ly8e8yn3.R.inc(466);volatileUpdateIndicator.lazySet(false);
        // Kick off another resize if things have accumulated to that level:
        __CLR4_4_100ly8e8yn3.R.inc(467);if ((((elementCount > threshold)&&(__CLR4_4_100ly8e8yn3.R.iget(468)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(469)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(470);rehash();
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}


    @SuppressWarnings("unchecked")
    Entry<K, V> createEntry(K key, int index, V value) {try{__CLR4_4_100ly8e8yn3.R.inc(471);
        __CLR4_4_100ly8e8yn3.R.inc(472);Entry<K, V> entry = new Entry<K, V>(key, value);
        __CLR4_4_100ly8e8yn3.R.inc(473);entry.next = elementData[index];
        __CLR4_4_100ly8e8yn3.R.inc(474);elementData[index] = entry;
        __CLR4_4_100ly8e8yn3.R.inc(475);return entry;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    @SuppressWarnings("unchecked")
    Entry<K,V> createHashedEntry(K key, int index, int hash) {try{__CLR4_4_100ly8e8yn3.R.inc(476);
        __CLR4_4_100ly8e8yn3.R.inc(477);Entry<K,V> entry = new Entry<K,V>(key,hash);
        __CLR4_4_100ly8e8yn3.R.inc(478);if ((((resizingIntoElementData != null)&&(__CLR4_4_100ly8e8yn3.R.iget(479)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(480)==0&false))) {{
            // We can be racing with a background resize op. So a CAS is needed:
            __CLR4_4_100ly8e8yn3.R.inc(481);Entry<K,V> currentHead;
            __CLR4_4_100ly8e8yn3.R.inc(482);int indexInResizedArray = hash & (resizingIntoElementData.length - 1);
            __CLR4_4_100ly8e8yn3.R.inc(483);do {{
                __CLR4_4_100ly8e8yn3.R.inc(484);currentHead = resizingIntoElementData[indexInResizedArray];
                __CLR4_4_100ly8e8yn3.R.inc(485);entry.next = currentHead;
            } }while ((((!EntryArrayHelper.compareAndSet(resizingIntoElementData, indexInResizedArray, currentHead, entry))&&(__CLR4_4_100ly8e8yn3.R.iget(486)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(487)==0&false)));

            __CLR4_4_100ly8e8yn3.R.inc(488);return entry;
        }
        }__CLR4_4_100ly8e8yn3.R.inc(489);entry.next = elementData[index];
        __CLR4_4_100ly8e8yn3.R.inc(490);elementData[index] = entry;
        __CLR4_4_100ly8e8yn3.R.inc(491);return entry;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Copies all the mappings in the specified map to this map. These mappings
     * will replace all mappings that this map had for any of the keys currently
     * in the given map.
     *
     * @param map
     *            the map to copy mappings from.
     * @throws NullPointerException
     *             if {@code map} is {@code null}.
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {try{__CLR4_4_100ly8e8yn3.R.inc(492);
        __CLR4_4_100ly8e8yn3.R.inc(493);if ((((!map.isEmpty())&&(__CLR4_4_100ly8e8yn3.R.iget(494)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(495)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(496);putAllImpl(map);
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    public void putAllImpl(Map<? extends K, ? extends V> map) {try{__CLR4_4_100ly8e8yn3.R.inc(497);
        __CLR4_4_100ly8e8yn3.R.inc(498);if ((((resizingIntoElementData != null)&&(__CLR4_4_100ly8e8yn3.R.iget(499)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(500)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(501);if ((((backgroundResizeComplete)&&(__CLR4_4_100ly8e8yn3.R.iget(502)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(503)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(504);forceFinishResizing();
            }
        }}
        }__CLR4_4_100ly8e8yn3.R.inc(505);int capacity = elementCount + map.size();
        __CLR4_4_100ly8e8yn3.R.inc(506);if ((((capacity > threshold)&&(__CLR4_4_100ly8e8yn3.R.iget(507)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(508)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(509);doResize(capacity);
        }
        }__CLR4_4_100ly8e8yn3.R.inc(510);for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {{
            __CLR4_4_100ly8e8yn3.R.inc(511);putImpl(entry.getKey(), entry.getValue());
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    void rehash(int capacity) {try{__CLR4_4_100ly8e8yn3.R.inc(512);
        __CLR4_4_100ly8e8yn3.R.inc(513);if ((((needToKickOffAnotherBackgroundResize || !pendingResize)&&(__CLR4_4_100ly8e8yn3.R.iget(514)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(515)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(516);kickoffBackgroundResize(capacity);
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    void rehash() {try{__CLR4_4_100ly8e8yn3.R.inc(517);
        __CLR4_4_100ly8e8yn3.R.inc(518);if ((((needToKickOffAnotherBackgroundResize || !pendingResize)&&(__CLR4_4_100ly8e8yn3.R.iget(519)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(520)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(521);kickoffBackgroundResize(elementData.length);
        }
        // rehash(elementData.length);
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Removes the mapping with the specified key from this map.
     *
     * @param key
     *            the key of the mapping to remove.
     * @return the value of the removed mapping or {@code null} if no mapping
     *         for the specified key was found.
     */
    @Override
    public V remove(Object key) {try{__CLR4_4_100ly8e8yn3.R.inc(522);
        __CLR4_4_100ly8e8yn3.R.inc(523);if ((((!pendingResize)&&(__CLR4_4_100ly8e8yn3.R.iget(524)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(525)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(526);return removeImpl(key);
        }
        }__CLR4_4_100ly8e8yn3.R.inc(527);synchronized (rehashMonitor) {
            __CLR4_4_100ly8e8yn3.R.inc(528);forceFinishResizing();
            __CLR4_4_100ly8e8yn3.R.inc(529);return removeImpl(key);
        }
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    public V removeImpl(Object key) {try{__CLR4_4_100ly8e8yn3.R.inc(530);
        __CLR4_4_100ly8e8yn3.R.inc(531);Entry<K, V> entry = removeEntry(key);
        __CLR4_4_100ly8e8yn3.R.inc(532);if ((((entry != null)&&(__CLR4_4_100ly8e8yn3.R.iget(533)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(534)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(535);return entry.value;
        }
        }__CLR4_4_100ly8e8yn3.R.inc(536);return null;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /*
     * Remove the given entry from the hashmap.
     * Assumes that the entry is in the map.
     */

    @SuppressWarnings("unchecked")
    final void removeEntry(Entry<K, V> entry) {try{__CLR4_4_100ly8e8yn3.R.inc(537);
        __CLR4_4_100ly8e8yn3.R.inc(538);int index = entry.origKeyHash & (elementData.length - 1);
        __CLR4_4_100ly8e8yn3.R.inc(539);Entry<K, V> m = elementData[index];
        __CLR4_4_100ly8e8yn3.R.inc(540);if ((((m == entry)&&(__CLR4_4_100ly8e8yn3.R.iget(541)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(542)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(543);elementData[index] = entry.next;
        } }else {{
            __CLR4_4_100ly8e8yn3.R.inc(544);while (((((m != null) && (m.next != entry))&&(__CLR4_4_100ly8e8yn3.R.iget(545)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(546)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(547);m = m.next;
            }
            }__CLR4_4_100ly8e8yn3.R.inc(548);if ((((m != null)&&(__CLR4_4_100ly8e8yn3.R.iget(549)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(550)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(551);m.next = entry.next;
            }
        }}
        }__CLR4_4_100ly8e8yn3.R.inc(552);modCount++;
        __CLR4_4_100ly8e8yn3.R.inc(553);elementCount--;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    @SuppressWarnings("unchecked")
    final Entry<K, V> removeEntry(Object key) {try{__CLR4_4_100ly8e8yn3.R.inc(554);
        __CLR4_4_100ly8e8yn3.R.inc(555);int index = 0;
        __CLR4_4_100ly8e8yn3.R.inc(556);Entry<K, V> entryInElementData;
        __CLR4_4_100ly8e8yn3.R.inc(557);Entry<K, V> last = null;
        __CLR4_4_100ly8e8yn3.R.inc(558);if ((((key != null)&&(__CLR4_4_100ly8e8yn3.R.iget(559)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(560)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(561);int hash = computeHashCode(key);
            __CLR4_4_100ly8e8yn3.R.inc(562);index = hash & (elementData.length - 1);
            __CLR4_4_100ly8e8yn3.R.inc(563);entryInElementData = elementData[index];
            __CLR4_4_100ly8e8yn3.R.inc(564);while ((((entryInElementData != null &&
                    !(entryInElementData.origKeyHash == hash &&
                            areEqualKeys(key, entryInElementData.key)))&&(__CLR4_4_100ly8e8yn3.R.iget(565)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(566)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(567);last = entryInElementData;
                __CLR4_4_100ly8e8yn3.R.inc(568);entryInElementData = entryInElementData.next;
            }
        }} }else {{
            __CLR4_4_100ly8e8yn3.R.inc(569);entryInElementData = elementData[0];
            __CLR4_4_100ly8e8yn3.R.inc(570);while ((((entryInElementData != null && entryInElementData.key != null)&&(__CLR4_4_100ly8e8yn3.R.iget(571)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(572)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(573);last = entryInElementData;
                __CLR4_4_100ly8e8yn3.R.inc(574);entryInElementData = entryInElementData.next;
            }
        }}

        }__CLR4_4_100ly8e8yn3.R.inc(575);if ((((entryInElementData == null)&&(__CLR4_4_100ly8e8yn3.R.iget(576)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(577)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(578);return null;
        } }else {{
            __CLR4_4_100ly8e8yn3.R.inc(579);if ((((last == null)&&(__CLR4_4_100ly8e8yn3.R.iget(580)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(581)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(582);elementData[index] = entryInElementData.next;
            } }else {{
                __CLR4_4_100ly8e8yn3.R.inc(583);last.next = entryInElementData.next;
            }
            }__CLR4_4_100ly8e8yn3.R.inc(584);modCount++;
            __CLR4_4_100ly8e8yn3.R.inc(585);elementCount--;
            __CLR4_4_100ly8e8yn3.R.inc(586);return entryInElementData;
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Returns the number of elements in this map.
     *
     * @return the number of elements in this map.
     */
    @Override
    public int size() {try{__CLR4_4_100ly8e8yn3.R.inc(587);
        __CLR4_4_100ly8e8yn3.R.inc(588);return elementCount;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /**
     * Returns a collection of the values contained in this map. The collection
     * is backed by this map so changes to one are reflected by the other. The
     * collection supports remove, removeAll, retainAll and clear operations,
     * and it does not support add or addAll operations.
     * <p>
     * This method returns a collection which is the subclass of
     * AbstractCollection. The iterator method of this subclass returns a
     * "wrapper object" over the iterator of map's entrySet(). The {@code size}
     * method wraps the map's size method and the {@code contains} method wraps
     * the map's containsValue method.
     * <p>
     * The collection is created when this method is called for the first time
     * and returned in response to all subsequent calls. This method may return
     * different collections when multiple concurrent calls occur, since no
     * synchronization is performed.
     *
     * @return a collection of the values contained in this map.
     */
    @Override
    public Collection<V> values() {try{__CLR4_4_100ly8e8yn3.R.inc(589);
        __CLR4_4_100ly8e8yn3.R.inc(590);if ((((valuesCollection == null)&&(__CLR4_4_100ly8e8yn3.R.iget(591)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(592)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(593);valuesCollection = new AbstractCollection<V>() {
                @Override
                public boolean contains(Object object) {try{__CLR4_4_100ly8e8yn3.R.inc(594);
                    __CLR4_4_100ly8e8yn3.R.inc(595);return containsValue(object);
                }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

                @Override
                public int size() {try{__CLR4_4_100ly8e8yn3.R.inc(596);
                    __CLR4_4_100ly8e8yn3.R.inc(597);return PauselessHashMap.this.size();
                }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

                @Override
                public void clear() {try{__CLR4_4_100ly8e8yn3.R.inc(598);
                    __CLR4_4_100ly8e8yn3.R.inc(599);PauselessHashMap.this.clear();
                }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

                @Override
                public Iterator<V> iterator() {try{__CLR4_4_100ly8e8yn3.R.inc(600);
                    __CLR4_4_100ly8e8yn3.R.inc(601);return new ValueIterator<K,V> (PauselessHashMap.this);
                }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
            };
        }
        }__CLR4_4_100ly8e8yn3.R.inc(602);return valuesCollection;
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    public void writeObject(ObjectOutputStream stream) throws IOException {try{__CLR4_4_100ly8e8yn3.R.inc(603);
        __CLR4_4_100ly8e8yn3.R.inc(604);synchronized (rehashMonitor) {
            __CLR4_4_100ly8e8yn3.R.inc(605);forceFinishResizing();
            __CLR4_4_100ly8e8yn3.R.inc(606);stream.defaultWriteObject();
            __CLR4_4_100ly8e8yn3.R.inc(607);stream.writeInt(elementData.length);
            __CLR4_4_100ly8e8yn3.R.inc(608);stream.writeInt(elementCount);
            __CLR4_4_100ly8e8yn3.R.inc(609);Iterator<?> iterator = entrySet().iterator();
            __CLR4_4_100ly8e8yn3.R.inc(610);while ((((iterator.hasNext())&&(__CLR4_4_100ly8e8yn3.R.iget(611)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(612)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(613);Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
                __CLR4_4_100ly8e8yn3.R.inc(614);stream.writeObject(entry.key);
                __CLR4_4_100ly8e8yn3.R.inc(615);stream.writeObject(entry.value);
                __CLR4_4_100ly8e8yn3.R.inc(616);entry = entry.next;
            }
        }}
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    @SuppressWarnings("unchecked")
    public void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {try{__CLR4_4_100ly8e8yn3.R.inc(617);
        __CLR4_4_100ly8e8yn3.R.inc(618);stream.defaultReadObject();
        __CLR4_4_100ly8e8yn3.R.inc(619);int length = stream.readInt();
        __CLR4_4_100ly8e8yn3.R.inc(620);elementData = newElementArray(length);
        __CLR4_4_100ly8e8yn3.R.inc(621);elementCount = stream.readInt();
        __CLR4_4_100ly8e8yn3.R.inc(622);for (int i = elementCount; (((--i >= 0)&&(__CLR4_4_100ly8e8yn3.R.iget(623)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(624)==0&false));) {{
            __CLR4_4_100ly8e8yn3.R.inc(625);K key = (K) stream.readObject();
            __CLR4_4_100ly8e8yn3.R.inc(626);int index = ((((null == key) )&&(__CLR4_4_100ly8e8yn3.R.iget(627)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(628)==0&false))? 0 : (computeHashCode(key) & (length - 1));
            __CLR4_4_100ly8e8yn3.R.inc(629);createEntry(key, index, (V) stream.readObject());
        }
    }}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    /*
     * Contract-related functionality
     */
    static int computeHashCode(Object key) {try{__CLR4_4_100ly8e8yn3.R.inc(630);
        __CLR4_4_100ly8e8yn3.R.inc(631);return key.hashCode();
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    static boolean areEqualKeys(Object key1, Object key2) {try{__CLR4_4_100ly8e8yn3.R.inc(632);
        __CLR4_4_100ly8e8yn3.R.inc(633);return (key1 == key2) || key1.equals(key2);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    static boolean areEqualValues(Object value1, Object value2) {try{__CLR4_4_100ly8e8yn3.R.inc(634);
        __CLR4_4_100ly8e8yn3.R.inc(635);return (value1 == value2) || value1.equals(value2);
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}


    // InternalMapEntry was taken from Apache Harmony's java.util.MapEntry (which Java SE does not have).

    static class InternalMapEntry<K, V> implements Map.Entry<K, V>, Cloneable {

        K key;
        V value;

        InternalMapEntry(K theKey) {try{__CLR4_4_100ly8e8yn3.R.inc(636);
            __CLR4_4_100ly8e8yn3.R.inc(637);key = theKey;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        InternalMapEntry(K theKey, V theValue) {try{__CLR4_4_100ly8e8yn3.R.inc(638);
            __CLR4_4_100ly8e8yn3.R.inc(639);key = theKey;
            __CLR4_4_100ly8e8yn3.R.inc(640);value = theValue;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        public Object clone() {try{__CLR4_4_100ly8e8yn3.R.inc(641);
            __CLR4_4_100ly8e8yn3.R.inc(642);try {
                __CLR4_4_100ly8e8yn3.R.inc(643);return super.clone();
            } catch (CloneNotSupportedException e) {
                __CLR4_4_100ly8e8yn3.R.inc(644);return null;
            }
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        public boolean equals(Object object) {try{__CLR4_4_100ly8e8yn3.R.inc(645);
            __CLR4_4_100ly8e8yn3.R.inc(646);if ((((this == object)&&(__CLR4_4_100ly8e8yn3.R.iget(647)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(648)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(649);return true;
            }
            }__CLR4_4_100ly8e8yn3.R.inc(650);if ((((object instanceof Map.Entry)&&(__CLR4_4_100ly8e8yn3.R.iget(651)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(652)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(653);Map.Entry<?, ?> entry = (Map.Entry<?, ?>) object;
                __CLR4_4_100ly8e8yn3.R.inc(654);return ((((key == null )&&(__CLR4_4_100ly8e8yn3.R.iget(655)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(656)==0&false))? entry.getKey() == null : key.equals(entry
                        .getKey()))
                        && ((((value == null )&&(__CLR4_4_100ly8e8yn3.R.iget(657)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(658)==0&false))? entry.getValue() == null : value
                        .equals(entry.getValue()));
            }
            }__CLR4_4_100ly8e8yn3.R.inc(659);return false;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public K getKey() {try{__CLR4_4_100ly8e8yn3.R.inc(660);
            __CLR4_4_100ly8e8yn3.R.inc(661);return key;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public V getValue() {try{__CLR4_4_100ly8e8yn3.R.inc(662);
            __CLR4_4_100ly8e8yn3.R.inc(663);return value;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        public int hashCode() {try{__CLR4_4_100ly8e8yn3.R.inc(664);
            __CLR4_4_100ly8e8yn3.R.inc(665);return ((((key == null )&&(__CLR4_4_100ly8e8yn3.R.iget(666)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(667)==0&false))? 0 : key.hashCode())
                    ^ ((((value == null )&&(__CLR4_4_100ly8e8yn3.R.iget(668)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(669)==0&false))? 0 : value.hashCode());
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public V setValue(V object) {try{__CLR4_4_100ly8e8yn3.R.inc(670);
            __CLR4_4_100ly8e8yn3.R.inc(671);V result = value;
            __CLR4_4_100ly8e8yn3.R.inc(672);value = object;
            __CLR4_4_100ly8e8yn3.R.inc(673);return result;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @Override
        public String toString() {try{__CLR4_4_100ly8e8yn3.R.inc(674);
            __CLR4_4_100ly8e8yn3.R.inc(675);return key + "=" + value;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
    }

    static class EntryArrayHelper {
        // A static access helper for an Entry[] that uses Unsafe for speed. Provides method compatibility
        // with same-named version aimed for use with AtomicReferenceArray arrays and no Unsafe.

        static final Unsafe UNSAFE;
        static final int objectIndexScale;
        static final int arrayBaseOffset;

        static {try{__CLR4_4_100ly8e8yn3.R.inc(676);
            __CLR4_4_100ly8e8yn3.R.inc(677);UNSAFE = getUnsafe();
            __CLR4_4_100ly8e8yn3.R.inc(678);objectIndexScale = calculateShiftForScale(UNSAFE.arrayIndexScale(Object[].class));
            __CLR4_4_100ly8e8yn3.R.inc(679);arrayBaseOffset = UNSAFE.arrayBaseOffset(Object[].class);

        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public static int calculateShiftForScale(final int scale) {try{__CLR4_4_100ly8e8yn3.R.inc(680);
            __CLR4_4_100ly8e8yn3.R.inc(681);if ((((4 == scale)&&(__CLR4_4_100ly8e8yn3.R.iget(682)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(683)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(684);return 2;
            } }else {__CLR4_4_100ly8e8yn3.R.inc(685);if ((((8 == scale)&&(__CLR4_4_100ly8e8yn3.R.iget(686)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(687)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(688);return 3;
            } }else {{
                __CLR4_4_100ly8e8yn3.R.inc(689);throw new IllegalArgumentException("Unknown pointer size");
            }
        }}}finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        public static Unsafe getUnsafe() {try{__CLR4_4_100ly8e8yn3.R.inc(690);
            __CLR4_4_100ly8e8yn3.R.inc(691);try {
                __CLR4_4_100ly8e8yn3.R.inc(692);Field f = Unsafe.class.getDeclaredField("theUnsafe");
                __CLR4_4_100ly8e8yn3.R.inc(693);f.setAccessible(true);
                __CLR4_4_100ly8e8yn3.R.inc(694);return (Unsafe)f.get(null);
            } catch (Exception e) {
            }
            __CLR4_4_100ly8e8yn3.R.inc(695);return null;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        static boolean compareAndSet(Entry[] array, int index, Entry expected, Entry entry) {try{__CLR4_4_100ly8e8yn3.R.inc(696);
            __CLR4_4_100ly8e8yn3.R.inc(697);long offset = arrayBaseOffset + (index << objectIndexScale);
            __CLR4_4_100ly8e8yn3.R.inc(698);return UNSAFE.compareAndSwapObject(array, offset, expected, entry);
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
    }

    class Resizer implements Runnable {
        final PauselessHashMap<K, V> associatedMap;
        final int capacity;
        final boolean isSynchronous;
        long startTimeNsec = 0;

        Resizer(PauselessHashMap<K, V> hm, int capacity, boolean isSynchronous) {try{__CLR4_4_100ly8e8yn3.R.inc(699);
            __CLR4_4_100ly8e8yn3.R.inc(700);associatedMap = hm;
            __CLR4_4_100ly8e8yn3.R.inc(701);this.capacity = capacity;
            __CLR4_4_100ly8e8yn3.R.inc(702);this.isSynchronous = isSynchronous;
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        void insertUniqueEquivalentAtHeadOfBucket(Entry<K, V> existingEntry, int bucketIndex) {try{__CLR4_4_100ly8e8yn3.R.inc(703);
            __CLR4_4_100ly8e8yn3.R.inc(704);K key = existingEntry.key;
            __CLR4_4_100ly8e8yn3.R.inc(705);int keyHash = existingEntry.origKeyHash;
            __CLR4_4_100ly8e8yn3.R.inc(706);Entry<K, V> newEntry = new Entry<K, V>(key, existingEntry.value);
            __CLR4_4_100ly8e8yn3.R.inc(707);newEntry.setInvalid();
            __CLR4_4_100ly8e8yn3.R.inc(708);boolean keyFound = false;

            __CLR4_4_100ly8e8yn3.R.inc(709);Entry<K,V> targetBucketHead;
            __CLR4_4_100ly8e8yn3.R.inc(710);do {{
                __CLR4_4_100ly8e8yn3.R.inc(711);targetBucketHead = resizingIntoElementData[bucketIndex];
                __CLR4_4_100ly8e8yn3.R.inc(712);newEntry.next = targetBucketHead;

                __CLR4_4_100ly8e8yn3.R.inc(713);if ((((!isSynchronous)&&(__CLR4_4_100ly8e8yn3.R.iget(714)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(715)==0&false))) {{
                    // Verify the entry will be unique in the target bucket:

                    __CLR4_4_100ly8e8yn3.R.inc(716);if ((((cachedExpectedHeadsArray[bucketIndex] != targetBucketHead)&&(__CLR4_4_100ly8e8yn3.R.iget(717)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(718)==0&false))) {{
                        // If the head does is not the one we expect it to be (i.e. null or
                        // the one we put in there before) then the mutator has inserted
                        // something, and we need to actually scan the bucket to verify
                        // that an entry with the same key has not been inserted:

                        __CLR4_4_100ly8e8yn3.R.inc(719);if ((((key == null)&&(__CLR4_4_100ly8e8yn3.R.iget(720)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(721)==0&false))) {{
                            __CLR4_4_100ly8e8yn3.R.inc(722);keyFound = (findNullKeyEntryInChain(targetBucketHead) != null);
                        } }else {{
                            __CLR4_4_100ly8e8yn3.R.inc(723);keyFound = (findNonNullKeyEntryInChain(key, targetBucketHead, keyHash) != null);
                        }

                        // This key is already in there, and that entry takes precedence:
                        }__CLR4_4_100ly8e8yn3.R.inc(724);if ((((keyFound)&&(__CLR4_4_100ly8e8yn3.R.iget(725)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(726)==0&false)))
                            {__CLR4_4_100ly8e8yn3.R.inc(727);return;
                    }}
                }}

                // We are potentially racing against a putting mutator, so a CAS is needed:
            }} }while ((((!EntryArrayHelper.compareAndSet(resizingIntoElementData, bucketIndex, targetBucketHead, newEntry))&&(__CLR4_4_100ly8e8yn3.R.iget(728)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(729)==0&false)));

            __CLR4_4_100ly8e8yn3.R.inc(730);if ((((!isSynchronous)&&(__CLR4_4_100ly8e8yn3.R.iget(731)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(732)==0&false))) {{
                // Cache this entry as the expected head for this bucket:
                __CLR4_4_100ly8e8yn3.R.inc(733);cachedExpectedHeadsArray[bucketIndex] = newEntry;
            }

            // there is a StoreStore fence ahead of here due to the CAS above

            // From now on, puts to existing entry will be sure to change both entries
            }__CLR4_4_100ly8e8yn3.R.inc(734);newEntry.value = existingEntry.value; // Existing entry MAY have been changed by racing put() call.

            // Force a StoreStore fence:
            __CLR4_4_100ly8e8yn3.R.inc(735);volatileUpdateIndicator.lazySet(true);

            __CLR4_4_100ly8e8yn3.R.inc(736);newEntry.setValid();
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

        @SuppressWarnings("unchecked")
        public void run() {try{__CLR4_4_100ly8e8yn3.R.inc(737);
            __CLR4_4_100ly8e8yn3.R.inc(738);if ((((startTimeNsec == 0)&&(__CLR4_4_100ly8e8yn3.R.iget(739)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(740)==0&false))) {{
                __CLR4_4_100ly8e8yn3.R.inc(741);startTimeNsec = System.nanoTime();
            }

            }__CLR4_4_100ly8e8yn3.R.inc(742);synchronized (rehashMonitor) {
                __CLR4_4_100ly8e8yn3.R.inc(743);boolean boolval;

                // Fast forward to make room for at least the current count. Useful for cases
                // where inserts are happening so quickly that the capacity used to trigger
                // the resizer is stale by the time we get here...
                __CLR4_4_100ly8e8yn3.R.inc(744);int needed_capacity  = Math.max(capacity, (int)(elementCount / loadFactor));

                __CLR4_4_100ly8e8yn3.R.inc(745);needed_capacity = calculateCapacity(((((capacity == 0 )&&(__CLR4_4_100ly8e8yn3.R.iget(746)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(747)==0&false))? 1 : needed_capacity << 1));

                __CLR4_4_100ly8e8yn3.R.inc(748);if ((((resizingIntoElementData == null)&&(__CLR4_4_100ly8e8yn3.R.iget(749)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(750)==0&false))) {{
                    __CLR4_4_100ly8e8yn3.R.inc(751);resizingIntoElementData = newElementArray(needed_capacity);
                    __CLR4_4_100ly8e8yn3.R.inc(752);if ((((!isSynchronous)&&(__CLR4_4_100ly8e8yn3.R.iget(753)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(754)==0&false))) {{
                        __CLR4_4_100ly8e8yn3.R.inc(755);cachedExpectedHeadsArray = newElementArray(needed_capacity);
                    }
                }}

                }__CLR4_4_100ly8e8yn3.R.inc(756);int length = resizingIntoElementData.length;

                // Force a full fence:
                __CLR4_4_100ly8e8yn3.R.inc(757);boolval = volatileUpdateIndicator.get();
                __CLR4_4_100ly8e8yn3.R.inc(758);volatileUpdateIndicator.set(!boolval);

                __CLR4_4_100ly8e8yn3.R.inc(759);if ((((isSynchronous)&&(__CLR4_4_100ly8e8yn3.R.iget(760)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(761)==0&false))) {{
                    __CLR4_4_100ly8e8yn3.R.inc(762);observedResizingIntoTable = true;
                }

                }__CLR4_4_100ly8e8yn3.R.inc(763);if ((((!observedResizingIntoTable)&&(__CLR4_4_100ly8e8yn3.R.iget(764)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(765)==0&false))) {{
                    // This is effectively a spin-wait until the mutator has signaled that it has
                    // observed the new Element array.

                    __CLR4_4_100ly8e8yn3.R.inc(766);if (((((System.nanoTime() - startTimeNsec) > NSEC_TIME_TO_SPINWAIT_IN_RESIZER_BEFORE_GIVING_UP)&&(__CLR4_4_100ly8e8yn3.R.iget(767)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(768)==0&false))) {{
                        // stop spinning, and instead make a future mutator op kick off another resizer.
                        __CLR4_4_100ly8e8yn3.R.inc(769);needToKickOffAnotherBackgroundResize = true;
                        __CLR4_4_100ly8e8yn3.R.inc(770);return;
                    }

                    // We spin wait by scheduling ourselves for a new execution so as not to block other
                    // users of the executors thread pool.
                    }__CLR4_4_100ly8e8yn3.R.inc(771);backgroundResizers.schedule(this, 1, TimeUnit.MILLISECONDS);

                    __CLR4_4_100ly8e8yn3.R.inc(772);return;
                }

                }__CLR4_4_100ly8e8yn3.R.inc(773);for (int i = 0; (((i < elementData.length)&&(__CLR4_4_100ly8e8yn3.R.iget(774)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(775)==0&false)); i++) {{
                    __CLR4_4_100ly8e8yn3.R.inc(776);Entry<K, V> existingEntry = elementData[i];

                    __CLR4_4_100ly8e8yn3.R.inc(777);while ((((existingEntry != null)&&(__CLR4_4_100ly8e8yn3.R.iget(778)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(779)==0&false))) {{
                        __CLR4_4_100ly8e8yn3.R.inc(780);int bucketIndex = existingEntry.origKeyHash & (length - 1);

                        __CLR4_4_100ly8e8yn3.R.inc(781);insertUniqueEquivalentAtHeadOfBucket(existingEntry, bucketIndex);

                        __CLR4_4_100ly8e8yn3.R.inc(782);existingEntry = existingEntry.next;
                    }
                    }__CLR4_4_100ly8e8yn3.R.inc(783);elementData[i] = null;
                }

                // Force a StoreStore fence:
                }__CLR4_4_100ly8e8yn3.R.inc(784);volatileUpdateIndicator.lazySet(!boolval);

                // We don't need the expected head cache any more:
                __CLR4_4_100ly8e8yn3.R.inc(785);cachedExpectedHeadsArray = null;

                __CLR4_4_100ly8e8yn3.R.inc(786);computeThreshold();
                __CLR4_4_100ly8e8yn3.R.inc(787);backgroundResizeComplete = true;
                __CLR4_4_100ly8e8yn3.R.inc(788);rehashMonitor.notifyAll(); // Kick anyone waiting for the resize to complete...
            }
        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
    }

    static final long NSEC_TIME_TO_SPINWAIT_IN_RESIZER_BEFORE_GIVING_UP = 100 * 1000L * 1000L;

    static final int DEFAULT_NUMBER_OF_BACKGROUND_RESIZER_EXECUTOR_THREADS = 2;

    static final int SMALLEST_CAPACITY_TO_KICK_OFF_BACKGROUND_RESIZE_FOR = 256;

    static final AtomicInteger backgroundResizerthreadNumber = new AtomicInteger(0);

    static final ScheduledExecutorService backgroundResizers =
            Executors.newScheduledThreadPool(DEFAULT_NUMBER_OF_BACKGROUND_RESIZER_EXECUTOR_THREADS,
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable runnable) {try{__CLR4_4_100ly8e8yn3.R.inc(789);
                            __CLR4_4_100ly8e8yn3.R.inc(790);Thread thread = new Thread(runnable);
                            __CLR4_4_100ly8e8yn3.R.inc(791);thread.setName("PauselessHashMap-resizer-pool-thread-" +
                                    backgroundResizerthreadNumber.getAndIncrement());
                            __CLR4_4_100ly8e8yn3.R.inc(792);thread.setDaemon(true);
                            __CLR4_4_100ly8e8yn3.R.inc(793);return thread;
                        }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}
                    });

    final void kickoffBackgroundResize(int capacity) {try{__CLR4_4_100ly8e8yn3.R.inc(794);

        __CLR4_4_100ly8e8yn3.R.inc(795);if ((((capacity < SMALLEST_CAPACITY_TO_KICK_OFF_BACKGROUND_RESIZE_FOR)&&(__CLR4_4_100ly8e8yn3.R.iget(796)!=0|true))||(__CLR4_4_100ly8e8yn3.R.iget(797)==0&false))) {{
            __CLR4_4_100ly8e8yn3.R.inc(798);doResize(capacity);
            __CLR4_4_100ly8e8yn3.R.inc(799);return;
        }

        // Big enough to do in the background:
        }__CLR4_4_100ly8e8yn3.R.inc(800);pendingResize = true;
        __CLR4_4_100ly8e8yn3.R.inc(801);needToKickOffAnotherBackgroundResize = false;
        __CLR4_4_100ly8e8yn3.R.inc(802);backgroundResizers.execute(new Resizer(this, capacity, false /* non-synchronous */));

        /* the following commented out code is here to play games with timing, and make bugs
         * happen (or not) more or less often. It is going away once all the kinks are
         * worked out ;-)
         */
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

    final void doResize(int capacity) {try{__CLR4_4_100ly8e8yn3.R.inc(803);
        __CLR4_4_100ly8e8yn3.R.inc(804);Resizer resizer = new Resizer(this, capacity, true /* synchronous */);
        __CLR4_4_100ly8e8yn3.R.inc(805);pendingResize = true;
        __CLR4_4_100ly8e8yn3.R.inc(806);synchronized (rehashMonitor) {
            __CLR4_4_100ly8e8yn3.R.inc(807);resizer.run();
            // Synchronously finish resizing to avoid having others deal with it:
            __CLR4_4_100ly8e8yn3.R.inc(808);finishResizing();
        }
    }finally{__CLR4_4_100ly8e8yn3.R.flushNeeded();}}

}

