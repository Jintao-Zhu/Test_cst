/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// int deleteMin( )--> Return and remove smallest item
// int findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// boolean isFull( )      --> Return true if full; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws Overflow if capacity exceeded

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinaryHeap
{public static class __CLR4_4_100m7es0e9k{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0031\u0032\u0033\u005c\u005c\u0031\u0031\u0039\u005c\u005c\u0042\u0069\u006e\u0061\u0072\u0079\u0048\u0065\u0061\u0070\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1740142660130L,8589935092L,80,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    //@ invariant wellFormed();
    /**
     * Construct the binary heap.
     */
    public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );__CLR4_4_100m7es0e9k.R.inc(1);try{__CLR4_4_100m7es0e9k.R.inc(0);
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    //@ requires capacity > 0;
    //@ ensures isEmpty();
    public BinaryHeap( int capacity )
    {try{__CLR4_4_100m7es0e9k.R.inc(2);
        __CLR4_4_100m7es0e9k.R.inc(3);currentSize = 0;
        __CLR4_4_100m7es0e9k.R.inc(4);array = new int[ capacity + 1 ];
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     * @exception Overflow if container is full.
     */
    public void insert( int x ) throws Overflow
    {try{__CLR4_4_100m7es0e9k.R.inc(5);
        __CLR4_4_100m7es0e9k.R.inc(6);if( (((isFull( ) )&&(__CLR4_4_100m7es0e9k.R.iget(7)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(8)==0&false)))
            {__CLR4_4_100m7es0e9k.R.inc(9);throw new Overflow( );

        // Percolate up
        }__CLR4_4_100m7es0e9k.R.inc(10);int hole = ++currentSize;
        __CLR4_4_100m7es0e9k.R.inc(11);for( ; (((hole > 1 && x< array[ hole / 2 ])&&(__CLR4_4_100m7es0e9k.R.iget(12)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(13)==0&false)); hole /= 2 )
            {__CLR4_4_100m7es0e9k.R.inc(14);array[ hole ] = array[ hole / 2 ];
        }__CLR4_4_100m7es0e9k.R.inc(15);array[ hole ] = x;
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or null, if empty.
     */
    public int findMin( )
    {try{__CLR4_4_100m7es0e9k.R.inc(16);
        __CLR4_4_100m7es0e9k.R.inc(17);if( (((isEmpty( ) )&&(__CLR4_4_100m7es0e9k.R.iget(18)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(19)==0&false)))
            {__CLR4_4_100m7es0e9k.R.inc(20);return -1;
        }__CLR4_4_100m7es0e9k.R.inc(21);return array[ 1 ];
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}
    boolean wellFormed() {try{__CLR4_4_100m7es0e9k.R.inc(22);
        __CLR4_4_100m7es0e9k.R.inc(23);if((((array==null)&&(__CLR4_4_100m7es0e9k.R.iget(24)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(25)==0&false))) {{//array!=null
            __CLR4_4_100m7es0e9k.R.inc(26);return false;
        }
        }__CLR4_4_100m7es0e9k.R.inc(27);if((((currentSize<0 || currentSize>=array.length)&&(__CLR4_4_100m7es0e9k.R.iget(28)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(29)==0&false))) {{//currentSize>=0; currentSize<array.length;
            __CLR4_4_100m7es0e9k.R.inc(30);return false;
        }
        }__CLR4_4_100m7es0e9k.R.inc(31);for(int i=1; (((i<currentSize)&&(__CLR4_4_100m7es0e9k.R.iget(32)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(33)==0&false)); i++) {{
            __CLR4_4_100m7es0e9k.R.inc(34);if((((i*2 <= currentSize && array[i]>array[2*i])&&(__CLR4_4_100m7es0e9k.R.iget(35)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(36)==0&false))) {{
                __CLR4_4_100m7es0e9k.R.inc(37);return false;
            }
            }__CLR4_4_100m7es0e9k.R.inc(38);if((((i*2 + 1<= currentSize && array[i]>array[2*i+1])&&(__CLR4_4_100m7es0e9k.R.iget(39)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(40)==0&false))) {{
                __CLR4_4_100m7es0e9k.R.inc(41);return false;
            }
        }}
        }__CLR4_4_100m7es0e9k.R.inc(42);return true;
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}
    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or null, if empty.
     */
    public int deleteMin( )
    {try{__CLR4_4_100m7es0e9k.R.inc(43);
        __CLR4_4_100m7es0e9k.R.inc(44);if( (((isEmpty( ) )&&(__CLR4_4_100m7es0e9k.R.iget(45)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(46)==0&false)))
            {__CLR4_4_100m7es0e9k.R.inc(47);return -1;

        }__CLR4_4_100m7es0e9k.R.inc(48);int minItem = findMin( );
        __CLR4_4_100m7es0e9k.R.inc(49);array[ 1 ] = array[ currentSize-- ];
        __CLR4_4_100m7es0e9k.R.inc(50);percolateDown( 1 );

        __CLR4_4_100m7es0e9k.R.inc(51);return minItem;
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    public void buildHeap( )
    {try{__CLR4_4_100m7es0e9k.R.inc(52);
        __CLR4_4_100m7es0e9k.R.inc(53);for( int i = currentSize / 2; (((i > 0)&&(__CLR4_4_100m7es0e9k.R.iget(54)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(55)==0&false)); i-- )
            {__CLR4_4_100m7es0e9k.R.inc(56);percolateDown( i );
    }}finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {try{__CLR4_4_100m7es0e9k.R.inc(57);
        __CLR4_4_100m7es0e9k.R.inc(58);return currentSize == 0;
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}

    /**
     * Test if the priority queue is logically full.
     * @return true if full, false otherwise.
     */
    public boolean isFull( )
    {try{__CLR4_4_100m7es0e9k.R.inc(59);
        __CLR4_4_100m7es0e9k.R.inc(60);return currentSize == array.length - 1;
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}

    /**
     * Make the priority queue logically empty.
     */
    //@ ensures isEmpty();
    public void makeEmpty( )
    {try{__CLR4_4_100m7es0e9k.R.inc(61);
        __CLR4_4_100m7es0e9k.R.inc(62);currentSize = 0;
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}

    private static final int DEFAULT_CAPACITY = 100;

    private int currentSize;      // Number of elements in heap
    private int [ ] array; // The heap array

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {try{__CLR4_4_100m7es0e9k.R.inc(63);
        __CLR4_4_100m7es0e9k.R.inc(64);int child;
        __CLR4_4_100m7es0e9k.R.inc(65);int tmp = array[ hole ];

        __CLR4_4_100m7es0e9k.R.inc(66);for( ; (((hole * 2 <= currentSize)&&(__CLR4_4_100m7es0e9k.R.iget(67)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(68)==0&false)); hole = child )
        {{
        	__CLR4_4_100m7es0e9k.R.inc(69);child = hole * 2;

            __CLR4_4_100m7es0e9k.R.inc(70);if( (((child != currentSize &&
                    array[ child + 1 ]< array[ child ]  )&&(__CLR4_4_100m7es0e9k.R.iget(71)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(72)==0&false)))
                    {__CLR4_4_100m7es0e9k.R.inc(73);child++;
            }__CLR4_4_100m7es0e9k.R.inc(74);if( (((array[ child ]< tmp )&&(__CLR4_4_100m7es0e9k.R.iget(75)!=0|true))||(__CLR4_4_100m7es0e9k.R.iget(76)==0&false)))
                {__CLR4_4_100m7es0e9k.R.inc(77);array[ hole ] = array[ child ];
            }else
                 {__CLR4_4_100m7es0e9k.R.inc(78);break;
        }}
         }__CLR4_4_100m7es0e9k.R.inc(79);array[ hole ] = tmp;
    }finally{__CLR4_4_100m7es0e9k.R.flushNeeded();}}


}
