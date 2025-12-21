/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
import java.util.BitSet;

/*
 * Integer bloom filter- used for test whether an integer element is a member of a set or not.
 * @author Lidan Hifi
 */
public class IntegerBloomFilter {public static class __CLR4_4_12s2sm7es041g{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0031\u0032\u0033\u005c\u005c\u0031\u0031\u0039\u005c\u005c\u0042\u0050\u006c\u0075\u0073\u0054\u0072\u0065\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1740142646498L,8589935092L,146,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
	// filter set
	private BitSet bitset;
	// filter set size
	private final int bitSetSize;
	// bits per element that is stored in the structure
	private final int bitsPerElement;
	// expected elements to be added
	private final int expectedNumberOfElements;
	// total items actually added
	private int totalElements;
	// hash functions count
	private final int k; 
	// hash params
	private final int hashParam1, hashParam2; 

	/**
	 * Constructs an empty Bloom filter. The total length of the Bloom filter will be
	 * c*n.
	 *
	 * @param bitsPerElement is the number of bits used per element.
	 * @param expectedNumberOfElements is the expected number of elements the filter will contain.
	 * @param totalHashFunctions is the number of hash functions used.
	 */
	public IntegerBloomFilter(int bitsPerElement, int expectedNumberOfElements, int totalHashFunctions) {try{__CLR4_4_12s2sm7es041g.R.inc(100);
		__CLR4_4_12s2sm7es041g.R.inc(101);this.expectedNumberOfElements = expectedNumberOfElements;
		__CLR4_4_12s2sm7es041g.R.inc(102);this.k = totalHashFunctions;
		__CLR4_4_12s2sm7es041g.R.inc(103);this.bitsPerElement = bitsPerElement;
		__CLR4_4_12s2sm7es041g.R.inc(104);this.bitSetSize = bitsPerElement * expectedNumberOfElements;
		__CLR4_4_12s2sm7es041g.R.inc(105);totalElements = 0;
		__CLR4_4_12s2sm7es041g.R.inc(106);this.bitset = new BitSet(bitSetSize);
		__CLR4_4_12s2sm7es041g.R.inc(107);this.hashParam1 = (int)(Math.random() * 100);
		__CLR4_4_12s2sm7es041g.R.inc(108);this.hashParam2 = (int)(Math.random() * 100);
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}
	
	/*
	 * constructs a new Bloom Filter for a given false positive probability and expected number of elements
	 * it is based on the formula -ln(p)/(ln2)^2 that represents how many bits we need per element,
	 * and the formula -ln(p)/ln2 that represents how many hash functions we need.
	 * @param falsePositiveProbability probability for false positive
	 * @param expectedNumberOfElements is the expected number of elements the filter will contain.
	 */
	public IntegerBloomFilter(double falsePositiveProbability, int expectedNumberOfElements) {
		this((int)Math.ceil(-(Math.log(falsePositiveProbability) / (Math.log(2) * Math.log(2)))), expectedNumberOfElements, (int)Math.ceil(-(Math.log(falsePositiveProbability)) / Math.log(2)));__CLR4_4_12s2sm7es041g.R.inc(110);try{__CLR4_4_12s2sm7es041g.R.inc(109);
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}

	/*
	 * get the expected false positive probability, based on the expected number of elements that configured on initialization.
	 * this probability is constant during the filter's life.
	 * @return the expected false positive probability
	 */
	public double getExpectedFalsePositiveProbability() {try{__CLR4_4_12s2sm7es041g.R.inc(111);
		__CLR4_4_12s2sm7es041g.R.inc(112);return getFalsePositiveProbability(expectedNumberOfElements);
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}

	/*
	 * get the expected false positive probability, based on the given number of elements
	 * @return the expected false positive probability for the given number of elements
	 */
	public double getFalsePositiveProbability(double elementsNumber) {try{__CLR4_4_12s2sm7es041g.R.inc(113);
		__CLR4_4_12s2sm7es041g.R.inc(114);return Math.pow(1 - Math.exp(-k * (double)elementsNumber / (double)bitSetSize), k);
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}

	/*
	 * get the current false positive probability, based on the total number of elements that actually added to the filter
	 * this probability is variable during the filter's life, and increases each time an element is added
	 * @return the current false positive probability
	 */
	public double getCurrentFalsePositiveProbability() {try{__CLR4_4_12s2sm7es041g.R.inc(115);
		__CLR4_4_12s2sm7es041g.R.inc(116);return getFalsePositiveProbability(totalElements);
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}

	/*
	 * clear the filter
	 */
	public void clear() {try{__CLR4_4_12s2sm7es041g.R.inc(117);
		__CLR4_4_12s2sm7es041g.R.inc(118);bitset.clear();
		__CLR4_4_12s2sm7es041g.R.inc(119);totalElements = 0;
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}

	/*
	 * calculates the hash functions (it generates the function based on the hash params that configured randomly in the filter initialization 
	 * @param data the given key for calculation
	 * @param hashes how many hash functions is needed to be calculated
	 * @return integer array that contains the results of the functions. need to operate mod function on each result, based on the filter set size.
	 */
	private int[] createHashes(int data, int hashes) {try{__CLR4_4_12s2sm7es041g.R.inc(120);
		__CLR4_4_12s2sm7es041g.R.inc(121);int[] hashValues = new int[hashes];
		
		__CLR4_4_12s2sm7es041g.R.inc(122);for (int i = 0; (((i < hashes)&&(__CLR4_4_12s2sm7es041g.R.iget(123)!=0|true))||(__CLR4_4_12s2sm7es041g.R.iget(124)==0&false)); i++) {{
			__CLR4_4_12s2sm7es041g.R.inc(125);hashValues[i] = ((i + hashParam1) * data + hashParam2) % 701;
		}
		
		}__CLR4_4_12s2sm7es041g.R.inc(126);return hashValues;
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}

	/*
	 * add an element to the filter
	 * @param element the given element for insertion
	 */
	public void add(int element) {try{__CLR4_4_12s2sm7es041g.R.inc(127);
		__CLR4_4_12s2sm7es041g.R.inc(128);int[] hashes = createHashes(element, k);

		__CLR4_4_12s2sm7es041g.R.inc(129);for (int hash : hashes)
			{__CLR4_4_12s2sm7es041g.R.inc(130);bitset.set(Math.abs(hash % bitSetSize), true);
		
		}__CLR4_4_12s2sm7es041g.R.inc(131);totalElements++;
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}
	
	/*
	 * test whether the given element is in the set or not
	 * @param element the given element for query
	 * @return true if the given element may be in the set (or maybe false positive), false if not (and it is 100% not).
	 */
	public boolean contains(int element) {try{__CLR4_4_12s2sm7es041g.R.inc(132);
		__CLR4_4_12s2sm7es041g.R.inc(133);int[] hashes = createHashes(element, k);
		
		__CLR4_4_12s2sm7es041g.R.inc(134);for (int hash : hashes) {{
			__CLR4_4_12s2sm7es041g.R.inc(135);if ((((!bitset.get(Math.abs(hash % bitSetSize)))&&(__CLR4_4_12s2sm7es041g.R.iget(136)!=0|true))||(__CLR4_4_12s2sm7es041g.R.iget(137)==0&false)))
				{__CLR4_4_12s2sm7es041g.R.inc(138);return false;
		}}
		
		}__CLR4_4_12s2sm7es041g.R.inc(139);return true;
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}
	
	/*
	 * get how many bits in the filter represents an element in the set
	 * @return bits per element
	 */
	public int getBitsPerElement() {try{__CLR4_4_12s2sm7es041g.R.inc(140);
		__CLR4_4_12s2sm7es041g.R.inc(141);return bitsPerElement;
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}
	
	/*
	 * get the filter set size
	 * @return the filter set size
	 */
	public int getFilterSize() {try{__CLR4_4_12s2sm7es041g.R.inc(142);
		__CLR4_4_12s2sm7es041g.R.inc(143);return bitSetSize;
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}
	
	/*
	 * get the total hash functions of the filter
	 * @return the total hash function of the filter
	 */
	public int getTotalHashFunctions() {try{__CLR4_4_12s2sm7es041g.R.inc(144);
		__CLR4_4_12s2sm7es041g.R.inc(145);return k;
	}finally{__CLR4_4_12s2sm7es041g.R.flushNeeded();}}
}
