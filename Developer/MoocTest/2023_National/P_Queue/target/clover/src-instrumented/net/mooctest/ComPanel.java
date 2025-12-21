/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ *//* ComPanel.java */
package net.mooctest;
import java.awt.*;
import java.applet.*;
import java.io.*;

public class ComPanel extends Panel {public static class __CLR4_4_1dhdhlpnjv527{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,497,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    TextField[] com;

    public ComPanel( String s, int n_lines ) {try{__CLR4_4_1dhdhlpnjv527.R.inc(485);
        __CLR4_4_1dhdhlpnjv527.R.inc(486);com = new TextField[n_lines];
	__CLR4_4_1dhdhlpnjv527.R.inc(487);setLayout(new GridLayout(0,1));
        __CLR4_4_1dhdhlpnjv527.R.inc(488);int i;
        //this.setBackground(Color.green);
        __CLR4_4_1dhdhlpnjv527.R.inc(489);for (i=0;(((i<n_lines)&&(__CLR4_4_1dhdhlpnjv527.R.iget(490)!=0|true))||(__CLR4_4_1dhdhlpnjv527.R.iget(491)==0&false));i++) {{
            __CLR4_4_1dhdhlpnjv527.R.inc(492);com[i] = new TextField(80);
            __CLR4_4_1dhdhlpnjv527.R.inc(493);add( com[i] );
        }
        }__CLR4_4_1dhdhlpnjv527.R.inc(494);com[0].setText("Running " + s );
    }finally{__CLR4_4_1dhdhlpnjv527.R.flushNeeded();}}

    public void setText( int line, String s ) {try{__CLR4_4_1dhdhlpnjv527.R.inc(495);
        __CLR4_4_1dhdhlpnjv527.R.inc(496);com[line].setText( s );
    }finally{__CLR4_4_1dhdhlpnjv527.R.flushNeeded();}}
}
