/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ *//* LFrame.java */
package net.mooctest;
import java.awt.*;

public class LFrame extends Frame {public static class __CLR4_4_1zrzrlpnjv53j{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,1320,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    public LFrame(Color[] colors, String[] strings) {try{__CLR4_4_1zrzrlpnjv53j.R.inc(1287);
		__CLR4_4_1zrzrlpnjv53j.R.inc(1288);add(new LFrame.Symbol(colors, strings));
		__CLR4_4_1zrzrlpnjv53j.R.inc(1289);setTitle("Legends");
		__CLR4_4_1zrzrlpnjv53j.R.inc(1290);pack();
		__CLR4_4_1zrzrlpnjv53j.R.inc(1291);validate();
		__CLR4_4_1zrzrlpnjv53j.R.inc(1292);show();
	}finally{__CLR4_4_1zrzrlpnjv53j.R.flushNeeded();}} // init()

		class Symbol extends Panel {
	Color[] col;
	String[] str;
	public Symbol( Color[] col, String[] str ) {try{__CLR4_4_1zrzrlpnjv53j.R.inc(1293);
	    __CLR4_4_1zrzrlpnjv53j.R.inc(1294);this.col = col;
	    __CLR4_4_1zrzrlpnjv53j.R.inc(1295);this.str = str;
	    __CLR4_4_1zrzrlpnjv53j.R.inc(1296);setBackground( Color.white );
	    __CLR4_4_1zrzrlpnjv53j.R.inc(1297);setFont( new Font("Dialog", Font.PLAIN, 12) );
	}finally{__CLR4_4_1zrzrlpnjv53j.R.flushNeeded();}}
	
	public void paint(Graphics g) {try{__CLR4_4_1zrzrlpnjv53j.R.inc(1298);
	    __CLR4_4_1zrzrlpnjv53j.R.inc(1299);for (int i = 0; (((i < col.length)&&(__CLR4_4_1zrzrlpnjv53j.R.iget(1300)!=0|true))||(__CLR4_4_1zrzrlpnjv53j.R.iget(1301)==0&false)); i++) {{
	    	__CLR4_4_1zrzrlpnjv53j.R.inc(1302);g.setColor( col[i] );
	    	__CLR4_4_1zrzrlpnjv53j.R.inc(1303);g.fillRect( 20, 15 + i*30, 10, 10 );
	    	__CLR4_4_1zrzrlpnjv53j.R.inc(1304);g.drawLine( 5, 20 + i*30, 45, 20 + i*30 );
	    }

	    }__CLR4_4_1zrzrlpnjv53j.R.inc(1305);g.setColor( Color.black );
	    __CLR4_4_1zrzrlpnjv53j.R.inc(1306);for (int i = 0; (((i < str.length)&&(__CLR4_4_1zrzrlpnjv53j.R.iget(1307)!=0|true))||(__CLR4_4_1zrzrlpnjv53j.R.iget(1308)==0&false)); i++) {{
		__CLR4_4_1zrzrlpnjv53j.R.inc(1309);g.drawString(str[i], 50, 25 + i*30);
	    }
	}}finally{__CLR4_4_1zrzrlpnjv53j.R.flushNeeded();}}

	public Dimension getPreferredSize() {try{__CLR4_4_1zrzrlpnjv53j.R.inc(1310);
	    __CLR4_4_1zrzrlpnjv53j.R.inc(1311);return new Dimension( 480, 320 );
	}finally{__CLR4_4_1zrzrlpnjv53j.R.flushNeeded();}}
    }

    public Dimension preferredSize() {try{__CLR4_4_1zrzrlpnjv53j.R.inc(1312);
        __CLR4_4_1zrzrlpnjv53j.R.inc(1313);return new Dimension(480,320);
    }finally{__CLR4_4_1zrzrlpnjv53j.R.flushNeeded();}}

    public boolean handleEvent(Event event) {try{__CLR4_4_1zrzrlpnjv53j.R.inc(1314);
        __CLR4_4_1zrzrlpnjv53j.R.inc(1315);if ((((event.id == Event.WINDOW_DESTROY)&&(__CLR4_4_1zrzrlpnjv53j.R.iget(1316)!=0|true))||(__CLR4_4_1zrzrlpnjv53j.R.iget(1317)==0&false))) {{
            __CLR4_4_1zrzrlpnjv53j.R.inc(1318);dispose();
        }
        }__CLR4_4_1zrzrlpnjv53j.R.inc(1319);return super.handleEvent(event);
    }finally{__CLR4_4_1zrzrlpnjv53j.R.flushNeeded();}}
} // class LFrame
