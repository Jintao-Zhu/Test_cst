/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ *//******************************************************************************
 *  Compilation:  javac StdOut.java
 *  Execution:    java StdOut
 *  Dependencies: none
 *
 *  Writes data of various types to standard output.
 *
 ******************************************************************************/

package net.mooctest;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 *  This class provides methods for printing strings and numbers to standard output.
 *  <p>
 *  <b>Getting started.</b>
 *  To use this class, you must have {@code StdOut.class} in your
 *  Java classpath. If you used our autoinstaller, you should be all set.
 *  Otherwise, download
 *  <a href = "https://introcs.cs.princeton.edu/java/stdlib/StdOut.java">StdOut.java</a>
 *  and put a copy in your working directory.
 *  <p>
 *  Here is an example program that uses {@code StdOut}:
 *  <pre>
 *   public class TestStdOut {
 *       public static void main(String[] args) {
 *           int a = 17;
 *           int b = 23;
 *           int sum = a + b;
 *           StdOut.println("Hello, World");
 *           StdOut.printf("%d + %d = %d\n", a, b, sum);
 *       }
 *   }
 *  </pre>
 *  <p>
 *  <b>Differences with System.out.</b>
 *  The behavior of {@code StdOut} is similar to that of {@link System#out},
 *  but there are a few technical differences:
 *  <ul>
 *  <li> {@code StdOut} coerces the character-set encoding to UTF-8,
 *       which is a standard character encoding for Unicode.
 *  <li> {@code StdOut} coerces the locale to {@link Locale#US},
 *       for consistency with {@link StdIn}, {@link Double#parseDouble(String)},
 *       and floating-point literals.
 *  <li> {@code StdOut} <em>flushes</em> standard output after each call to
 *       {@code print()} so that text will appear immediately in the terminal.
 *  </ul>
 *  <p>
 *  <b>Reference.</b>
 *  For additional documentation,
 *  see <a href="https://introcs.cs.princeton.edu/15inout">Section 1.5</a> of
 *  <em>Computer Science: An Interdisciplinary Approach</em>
 *  by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class StdOut {public static class __CLR4_4_1dldllpnrio8c{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0036\u0030\u0032\u005c\u005c\u0031\u0033\u0034\u0034\u0038\u0034\u005c\u005c\u0042\u0053\u0054\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701503946885L,8589935092L,551,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    // force Unicode UTF-8 encoding; otherwise it's system dependent
    private static final String CHARSET_NAME = "UTF-8";

    // assume language = English, country = US for consistency with StdIn
    private static final Locale LOCALE = Locale.US;

    // send output here
    private static PrintWriter out;

    // this is called before invoking any methods
    static {try{__CLR4_4_1dldllpnrio8c.R.inc(489);
        __CLR4_4_1dldllpnrio8c.R.inc(490);try {
            __CLR4_4_1dldllpnrio8c.R.inc(491);out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET_NAME), true);
        }
        catch (UnsupportedEncodingException e) {
            __CLR4_4_1dldllpnrio8c.R.inc(492);System.out.println(e);
        }
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

    // don't instantiate
    private StdOut() {try{__CLR4_4_1dldllpnrio8c.R.inc(493); }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Closes standard output.
     * @deprecated Calling close() permanently disables standard output;
     *             subsequent calls to StdOut.println() or System.out.println()
     *             will no longer produce output on standard output.
     */
    @Deprecated
    public static void close() {try{__CLR4_4_1dldllpnrio8c.R.inc(494);
        __CLR4_4_1dldllpnrio8c.R.inc(495);out.close();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Terminates the current line by printing the line-separator string.
     */
    public static void println() {try{__CLR4_4_1dldllpnrio8c.R.inc(496);
        __CLR4_4_1dldllpnrio8c.R.inc(497);out.println();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints an object to this output stream and then terminates the line.
     *
     * @param x the object to print
     */
    public static void println(Object x) {try{__CLR4_4_1dldllpnrio8c.R.inc(498);
        __CLR4_4_1dldllpnrio8c.R.inc(499);out.println(x);
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a boolean to standard output and then terminates the line.
     *
     * @param x the boolean to print
     */
    public static void println(boolean x) {try{__CLR4_4_1dldllpnrio8c.R.inc(500);
        __CLR4_4_1dldllpnrio8c.R.inc(501);out.println(x);
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a character to standard output and then terminates the line.
     *
     * @param x the character to print
     */
    public static void println(char x) {try{__CLR4_4_1dldllpnrio8c.R.inc(502);
        __CLR4_4_1dldllpnrio8c.R.inc(503);out.println(x);
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a double to standard output and then terminates the line.
     *
     * @param x the double to print
     */
    public static void println(double x) {try{__CLR4_4_1dldllpnrio8c.R.inc(504);
        __CLR4_4_1dldllpnrio8c.R.inc(505);out.println(x);
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints an integer to standard output and then terminates the line.
     *
     * @param x the integer to print
     */
    public static void println(float x) {try{__CLR4_4_1dldllpnrio8c.R.inc(506);
        __CLR4_4_1dldllpnrio8c.R.inc(507);out.println(x);
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints an integer to standard output and then terminates the line.
     *
     * @param x the integer to print
     */
    public static void println(int x) {try{__CLR4_4_1dldllpnrio8c.R.inc(508);
        __CLR4_4_1dldllpnrio8c.R.inc(509);out.println(x);
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a long to standard output and then terminates the line.
     *
     * @param x the long to print
     */
    public static void println(long x) {try{__CLR4_4_1dldllpnrio8c.R.inc(510);
        __CLR4_4_1dldllpnrio8c.R.inc(511);out.println(x);
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a short integer to standard output and then terminates the line.
     *
     * @param x the short to print
     */
    public static void println(short x) {try{__CLR4_4_1dldllpnrio8c.R.inc(512);
        __CLR4_4_1dldllpnrio8c.R.inc(513);out.println(x);
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a byte to standard output and then terminates the line.
     * <p>
     * To write binary data, see {@link BinaryStdOut}.
     *
     * @param x the byte to print
     */
    public static void println(byte x) {try{__CLR4_4_1dldllpnrio8c.R.inc(514);
        __CLR4_4_1dldllpnrio8c.R.inc(515);out.println(x);
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Flushes standard output.
     */
    public static void print() {try{__CLR4_4_1dldllpnrio8c.R.inc(516);
        __CLR4_4_1dldllpnrio8c.R.inc(517);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints an object to standard output and flushes standard output.
     * 
     * @param x the object to print
     */
    public static void print(Object x) {try{__CLR4_4_1dldllpnrio8c.R.inc(518);
        __CLR4_4_1dldllpnrio8c.R.inc(519);out.print(x);
        __CLR4_4_1dldllpnrio8c.R.inc(520);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a boolean to standard output and flushes standard output.
     * 
     * @param x the boolean to print
     */
    public static void print(boolean x) {try{__CLR4_4_1dldllpnrio8c.R.inc(521);
        __CLR4_4_1dldllpnrio8c.R.inc(522);out.print(x);
        __CLR4_4_1dldllpnrio8c.R.inc(523);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a character to standard output and flushes standard output.
     * 
     * @param x the character to print
     */
    public static void print(char x) {try{__CLR4_4_1dldllpnrio8c.R.inc(524);
        __CLR4_4_1dldllpnrio8c.R.inc(525);out.print(x);
        __CLR4_4_1dldllpnrio8c.R.inc(526);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a double to standard output and flushes standard output.
     * 
     * @param x the double to print
     */
    public static void print(double x) {try{__CLR4_4_1dldllpnrio8c.R.inc(527);
        __CLR4_4_1dldllpnrio8c.R.inc(528);out.print(x);
        __CLR4_4_1dldllpnrio8c.R.inc(529);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a float to standard output and flushes standard output.
     * 
     * @param x the float to print
     */
    public static void print(float x) {try{__CLR4_4_1dldllpnrio8c.R.inc(530);
        __CLR4_4_1dldllpnrio8c.R.inc(531);out.print(x);
        __CLR4_4_1dldllpnrio8c.R.inc(532);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints an integer to standard output and flushes standard output.
     * 
     * @param x the integer to print
     */
    public static void print(int x) {try{__CLR4_4_1dldllpnrio8c.R.inc(533);
        __CLR4_4_1dldllpnrio8c.R.inc(534);out.print(x);
        __CLR4_4_1dldllpnrio8c.R.inc(535);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a long integer to standard output and flushes standard output.
     * 
     * @param x the long integer to print
     */
    public static void print(long x) {try{__CLR4_4_1dldllpnrio8c.R.inc(536);
        __CLR4_4_1dldllpnrio8c.R.inc(537);out.print(x);
        __CLR4_4_1dldllpnrio8c.R.inc(538);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a short integer to standard output and flushes standard output.
     * 
     * @param x the short integer to print
     */
    public static void print(short x) {try{__CLR4_4_1dldllpnrio8c.R.inc(539);
        __CLR4_4_1dldllpnrio8c.R.inc(540);out.print(x);
        __CLR4_4_1dldllpnrio8c.R.inc(541);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a byte to standard output and flushes standard output.
     *
     * @param x the byte to print
     */
    public static void print(byte x) {try{__CLR4_4_1dldllpnrio8c.R.inc(542);
        __CLR4_4_1dldllpnrio8c.R.inc(543);out.print(x);
        __CLR4_4_1dldllpnrio8c.R.inc(544);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a formatted string to standard output, using the specified format
     * string and arguments, and then flushes standard output.
     *
     *
     * @param format the <a href = "http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args   the arguments accompanying the format string
     */
    public static void printf(String format, Object... args) {try{__CLR4_4_1dldllpnrio8c.R.inc(545);
        __CLR4_4_1dldllpnrio8c.R.inc(546);out.printf(LOCALE, format, args);
        __CLR4_4_1dldllpnrio8c.R.inc(547);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}

   /**
     * Prints a formatted string to standard output, using the locale and
     * the specified format string and arguments; then flushes standard output.
     *
     * @param locale the locale
     * @param format the <a href = "http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args   the arguments accompanying the format string
     */
    public static void printf(Locale locale, String format, Object... args) {try{__CLR4_4_1dldllpnrio8c.R.inc(548);
        __CLR4_4_1dldllpnrio8c.R.inc(549);out.printf(locale, format, args);
        __CLR4_4_1dldllpnrio8c.R.inc(550);out.flush();
    }finally{__CLR4_4_1dldllpnrio8c.R.flushNeeded();}}
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
