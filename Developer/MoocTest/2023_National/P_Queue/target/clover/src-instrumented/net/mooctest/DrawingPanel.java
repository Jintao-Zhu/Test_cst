/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

public class DrawingPanel extends Panel {public static class __CLR4_4_1gngnlpnjv52i{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,652,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    int panel_height = 100;
    int panel_width = 100;
    int offset = 10;
    int pref_height = 200;
    int pref_width = 250;
    int delayDuration = 200;
    FontMetrics fm;
    Dimension offscreensize = null;
    Image offscreen = null;
    Graphics offGraphics = null;

    Font bigFont, smallFont, tinyFont, hugeFont, fixFont;
    Heap heap;

    public DrawingPanel() {try{__CLR4_4_1gngnlpnjv52i.R.inc(599);
        __CLR4_4_1gngnlpnjv52i.R.inc(600);smallFont = new Font("Dialog", Font.PLAIN, 10);
        __CLR4_4_1gngnlpnjv52i.R.inc(601);fm = this.getFontMetrics(smallFont);
	__CLR4_4_1gngnlpnjv52i.R.inc(602);bigFont = new Font("Dialog", Font.PLAIN, 12);
	__CLR4_4_1gngnlpnjv52i.R.inc(603);hugeFont = new Font("Dialog", Font.PLAIN, 14);
	__CLR4_4_1gngnlpnjv52i.R.inc(604);fixFont = new Font("Courier", Font.PLAIN, 12);
	__CLR4_4_1gngnlpnjv52i.R.inc(605);tinyFont = new Font("Dialog", Font.PLAIN, 8);
	__CLR4_4_1gngnlpnjv52i.R.inc(606);setBackground(Color.white);
	__CLR4_4_1gngnlpnjv52i.R.inc(607);panel_height = size().height;
	__CLR4_4_1gngnlpnjv52i.R.inc(608);panel_width = size().width;
	__CLR4_4_1gngnlpnjv52i.R.inc(609);heap = null;
    }finally{__CLR4_4_1gngnlpnjv52i.R.flushNeeded();}} // DrawingPanel() 
    
    public void shortDelay() {try{__CLR4_4_1gngnlpnjv52i.R.inc(610);
	__CLR4_4_1gngnlpnjv52i.R.inc(611);try {
	    __CLR4_4_1gngnlpnjv52i.R.inc(612);Thread.sleep(delayDuration/3);
	} catch (InterruptedException e) {}
    }finally{__CLR4_4_1gngnlpnjv52i.R.flushNeeded();}}

    public void delay() {try{__CLR4_4_1gngnlpnjv52i.R.inc(613);
	__CLR4_4_1gngnlpnjv52i.R.inc(614);try {
	    __CLR4_4_1gngnlpnjv52i.R.inc(615);Thread.sleep(delayDuration);
	} catch (InterruptedException e) {}
    }finally{__CLR4_4_1gngnlpnjv52i.R.flushNeeded();}}

    public void setDelay(int delay) {try{__CLR4_4_1gngnlpnjv52i.R.inc(616);
	__CLR4_4_1gngnlpnjv52i.R.inc(617);this.delayDuration = delay;
    }finally{__CLR4_4_1gngnlpnjv52i.R.flushNeeded();}}
    
    public void update(Graphics g) {try{__CLR4_4_1gngnlpnjv52i.R.inc(618);
        __CLR4_4_1gngnlpnjv52i.R.inc(619);Dimension d = size();
        __CLR4_4_1gngnlpnjv52i.R.inc(620);if ((((d.width < 1 || d.height < 1)&&(__CLR4_4_1gngnlpnjv52i.R.iget(621)!=0|true))||(__CLR4_4_1gngnlpnjv52i.R.iget(622)==0&false)))
            {__CLR4_4_1gngnlpnjv52i.R.inc(623);return;

        }__CLR4_4_1gngnlpnjv52i.R.inc(624);if (((((offscreen == null) || (d.width != offscreensize.width) ||
                (d.height != offscreensize.height))&&(__CLR4_4_1gngnlpnjv52i.R.iget(625)!=0|true))||(__CLR4_4_1gngnlpnjv52i.R.iget(626)==0&false))) {{
            __CLR4_4_1gngnlpnjv52i.R.inc(627);offscreen = createImage(d.width, d.height);
            __CLR4_4_1gngnlpnjv52i.R.inc(628);offscreensize = d;
            __CLR4_4_1gngnlpnjv52i.R.inc(629);offGraphics = offscreen.getGraphics();
        }

        }__CLR4_4_1gngnlpnjv52i.R.inc(630);offGraphics.setColor(getBackground());
        __CLR4_4_1gngnlpnjv52i.R.inc(631);offGraphics.fillRect(0, 0, d.width, d.height);
        __CLR4_4_1gngnlpnjv52i.R.inc(632);offGraphics.setFont(smallFont);
        __CLR4_4_1gngnlpnjv52i.R.inc(633);fm = offGraphics.getFontMetrics();
        __CLR4_4_1gngnlpnjv52i.R.inc(634);paint(offGraphics);
        __CLR4_4_1gngnlpnjv52i.R.inc(635);g.drawImage(offscreen, 0, 0, null);
    }finally{__CLR4_4_1gngnlpnjv52i.R.flushNeeded();}}

    public void drawHeap(Heap heap) {try{__CLR4_4_1gngnlpnjv52i.R.inc(636);
	__CLR4_4_1gngnlpnjv52i.R.inc(637);this.heap = heap;
	__CLR4_4_1gngnlpnjv52i.R.inc(638);repaint();
    }finally{__CLR4_4_1gngnlpnjv52i.R.flushNeeded();}}

    public Heap getHeap() {try{__CLR4_4_1gngnlpnjv52i.R.inc(639);
	__CLR4_4_1gngnlpnjv52i.R.inc(640);return this.heap;
    }finally{__CLR4_4_1gngnlpnjv52i.R.flushNeeded();}}

    public void paint(Graphics g) {try{__CLR4_4_1gngnlpnjv52i.R.inc(641);
	__CLR4_4_1gngnlpnjv52i.R.inc(642);panel_height = size().height;
	__CLR4_4_1gngnlpnjv52i.R.inc(643);panel_width = size().width;

	__CLR4_4_1gngnlpnjv52i.R.inc(644);if ((((heap != null)&&(__CLR4_4_1gngnlpnjv52i.R.iget(645)!=0|true))||(__CLR4_4_1gngnlpnjv52i.R.iget(646)==0&false)))
	    {__CLR4_4_1gngnlpnjv52i.R.inc(647);heap.drawTree(g);

        }__CLR4_4_1gngnlpnjv52i.R.inc(648);g.setColor( Color.black );
        __CLR4_4_1gngnlpnjv52i.R.inc(649);g.drawRect( 1, 1, panel_width-2, panel_height-2 );
    }finally{__CLR4_4_1gngnlpnjv52i.R.flushNeeded();}} // paint()

    public Dimension getPreferredSize() {try{__CLR4_4_1gngnlpnjv52i.R.inc(650);
        __CLR4_4_1gngnlpnjv52i.R.inc(651);return new Dimension( pref_width, pref_height );
    }finally{__CLR4_4_1gngnlpnjv52i.R.flushNeeded();}} // getPreferredSize()
} // class DrawingPanel
