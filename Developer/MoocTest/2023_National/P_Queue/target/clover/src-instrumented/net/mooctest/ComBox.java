/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ *//* ComBox.java */
package net.mooctest;
import java.awt.*;
import java.lang.*;

/**
 * This class composes a commentary box to be drawn on any drawing panel/canvas.
 * The width of the commentary box will be determined by the length of the
 * text string being displayed.
 */
public class ComBox {public static class __CLR4_4_1bebelpnjv522{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,485,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    /**
     * The text string to be displayed on the commentary box.
     * @see String
     */
    public String str;
    /**
     * Top left position of the commentary box. This attribute consists
     * of two components: <b>x</b> and <b>y</b> based on the Point class
     * in <code>java.awt.*</code>.
     * @see Point
     */
    public Point topLeft;
    int height, width;
    Font font;
    int[] xPts, yPts;
    int nPts;
    /**
     * Background color of the commentary box specified by <code>
     * java.awt.Color</code>.
     * @see Color
     */
    public Color bg;
    /**
     * Foreground color (text color) of the commentary box specified by <code>
     * java.awt.Color</code>.
     * @see Color
     */
    public Color fg;

    /**
     * Creates a commentary box with its topleft corner at (topLeftX, topLeftY)
     * and commentary string specified by the third <code>String</code>
     * parameter. By default, the background color is yellow and
     * the text is displayed in blue.
     * @param topLeftX Integer value specifying the horizontal position of the
     * topleft corner of the commentary box.
     * @param topLeftY Integer value specifying the vertical position of the
     * topleft corner of the commentary box.
     * @param str The text string to be displayed on the commentary box.
     */
    public ComBox(int topLeftX, int topLeftY, String str, Font font) {try{__CLR4_4_1bebelpnjv522.R.inc(410);
	__CLR4_4_1bebelpnjv522.R.inc(411);this.topLeft = new Point(topLeftX, topLeftY);
	__CLR4_4_1bebelpnjv522.R.inc(412);this.str = str;
	__CLR4_4_1bebelpnjv522.R.inc(413);this.fg = Color.blue;
	__CLR4_4_1bebelpnjv522.R.inc(414);this.bg = Color.yellow;
	__CLR4_4_1bebelpnjv522.R.inc(415);this.font = font;
	__CLR4_4_1bebelpnjv522.R.inc(416);height = 30;
	__CLR4_4_1bebelpnjv522.R.inc(417);width = 7*str.length() + 6;
	__CLR4_4_1bebelpnjv522.R.inc(418);nPts = 8;
	__CLR4_4_1bebelpnjv522.R.inc(419);xPts = new int[nPts];
	__CLR4_4_1bebelpnjv522.R.inc(420);yPts = new int[nPts];
	__CLR4_4_1bebelpnjv522.R.inc(421);xPts[0] = topLeft.x + 4; __CLR4_4_1bebelpnjv522.R.inc(422);xPts[1] = topLeft.x + width - 4;
	__CLR4_4_1bebelpnjv522.R.inc(423);xPts[2] = xPts[3] = topLeft.x + width;
	__CLR4_4_1bebelpnjv522.R.inc(424);xPts[4] = xPts[1]; __CLR4_4_1bebelpnjv522.R.inc(425);xPts[5] = xPts[0];
	__CLR4_4_1bebelpnjv522.R.inc(426);xPts[6] = xPts[7] = topLeft.x;
	__CLR4_4_1bebelpnjv522.R.inc(427);yPts[0] = yPts[1] = topLeft.y;
	__CLR4_4_1bebelpnjv522.R.inc(428);yPts[2] = topLeft.y + 4;
	__CLR4_4_1bebelpnjv522.R.inc(429);yPts[3] = topLeft.y + height - 4;
	__CLR4_4_1bebelpnjv522.R.inc(430);yPts[4] = yPts[5] = topLeft.y + height;
	__CLR4_4_1bebelpnjv522.R.inc(431);yPts[6] = yPts[3];
	__CLR4_4_1bebelpnjv522.R.inc(432);yPts[7] = yPts[2];
    }finally{__CLR4_4_1bebelpnjv522.R.flushNeeded();}}

    /**
     * Creates a commentary box with its topleft corner at (topLeftX, topLeftY)
     * and commentary string specified by the third <code>String</code>
     * parameter, using the foreground and background colors indicated by
     * the fourth and fifth parameters. 
     * 
     * @param topLeftX Integer value specifying the horizontal position of the
     * topleft corner of the commentary box.
     * @param topLeftY Integer value specifying the vertical position of the
     * topleft corner of the commentary box.
     * @param str The text string to be displayed on the commentary box.
     * @param fg Color of the text in the commentary box.
     * @param bg Color of the commentary box background.
     */
    public ComBox(int topLeftX, int topLeftY, String str, Color fg, Color bg,
		Font font) {try{__CLR4_4_1bebelpnjv522.R.inc(433);
	__CLR4_4_1bebelpnjv522.R.inc(434);this.topLeft = new Point(topLeftX, topLeftY);
	__CLR4_4_1bebelpnjv522.R.inc(435);this.str = str;
	__CLR4_4_1bebelpnjv522.R.inc(436);this.fg = fg;
	__CLR4_4_1bebelpnjv522.R.inc(437);this.bg = bg;
	__CLR4_4_1bebelpnjv522.R.inc(438);this.font = font;
	__CLR4_4_1bebelpnjv522.R.inc(439);height = 30;
	__CLR4_4_1bebelpnjv522.R.inc(440);width = 7*str.length() + 6;
	__CLR4_4_1bebelpnjv522.R.inc(441);nPts = 8;
	__CLR4_4_1bebelpnjv522.R.inc(442);xPts = new int[nPts];
	__CLR4_4_1bebelpnjv522.R.inc(443);yPts = new int[nPts];
	__CLR4_4_1bebelpnjv522.R.inc(444);xPts[0] = topLeft.x + 4; __CLR4_4_1bebelpnjv522.R.inc(445);xPts[1] = topLeft.x + width - 4;
	__CLR4_4_1bebelpnjv522.R.inc(446);xPts[2] = xPts[3] = topLeft.x + width;
	__CLR4_4_1bebelpnjv522.R.inc(447);xPts[4] = xPts[1]; __CLR4_4_1bebelpnjv522.R.inc(448);xPts[5] = xPts[0];
	__CLR4_4_1bebelpnjv522.R.inc(449);xPts[6] = xPts[7] = topLeft.x;
	__CLR4_4_1bebelpnjv522.R.inc(450);yPts[0] = yPts[1] = topLeft.y;
	__CLR4_4_1bebelpnjv522.R.inc(451);yPts[2] = topLeft.y + 4;
	__CLR4_4_1bebelpnjv522.R.inc(452);yPts[3] = topLeft.y + height - 4;
	__CLR4_4_1bebelpnjv522.R.inc(453);yPts[4] = yPts[5] = topLeft.y + height;
	__CLR4_4_1bebelpnjv522.R.inc(454);yPts[6] = yPts[3];
	__CLR4_4_1bebelpnjv522.R.inc(455);yPts[7] = yPts[2];
    }finally{__CLR4_4_1bebelpnjv522.R.flushNeeded();}}

    /**
     * Set the text string to be displayed on the commentary box.
     * @param str Text string in the form of class String
     */
    public void setText(String str) {try{__CLR4_4_1bebelpnjv522.R.inc(456);
	__CLR4_4_1bebelpnjv522.R.inc(457);this.str = str;
        __CLR4_4_1bebelpnjv522.R.inc(458);width = 7*str.length() + 6;
        __CLR4_4_1bebelpnjv522.R.inc(459);xPts[0] = topLeft.x + 4; __CLR4_4_1bebelpnjv522.R.inc(460);xPts[1] = topLeft.x + width - 4;
        __CLR4_4_1bebelpnjv522.R.inc(461);xPts[2] = xPts[3] = topLeft.x + width;
        __CLR4_4_1bebelpnjv522.R.inc(462);xPts[4] = xPts[1]; __CLR4_4_1bebelpnjv522.R.inc(463);xPts[5] = xPts[0];
        __CLR4_4_1bebelpnjv522.R.inc(464);xPts[6] = xPts[7] = topLeft.x;
        __CLR4_4_1bebelpnjv522.R.inc(465);yPts[0] = yPts[1] = topLeft.y;
        __CLR4_4_1bebelpnjv522.R.inc(466);yPts[2] = topLeft.y + 4;
        __CLR4_4_1bebelpnjv522.R.inc(467);yPts[3] = topLeft.y + height - 4;
        __CLR4_4_1bebelpnjv522.R.inc(468);yPts[4] = yPts[5] = topLeft.y + height;
        __CLR4_4_1bebelpnjv522.R.inc(469);yPts[6] = yPts[3];
        __CLR4_4_1bebelpnjv522.R.inc(470);yPts[7] = yPts[2];
    }finally{__CLR4_4_1bebelpnjv522.R.flushNeeded();}}

    /**
     * Set the top left position of the commentary box.
     * @param topLeftX Horizontal position of the top left corner.
     * @param topLeftY Vertical position of the top left corner.
     */
    public void setTopLeft(int topLeftX, int topLeftY) {try{__CLR4_4_1bebelpnjv522.R.inc(471);
	__CLR4_4_1bebelpnjv522.R.inc(472);this.topLeft = new Point(topLeftX, topLeftY);
    }finally{__CLR4_4_1bebelpnjv522.R.flushNeeded();}}

    /**
     * Set the background color of the commentary box.
     * @param col Background color, instance of java.awt.color
     */
    public void setBackground(Color col) {try{__CLR4_4_1bebelpnjv522.R.inc(473);
	__CLR4_4_1bebelpnjv522.R.inc(474);this.bg = col;
    }finally{__CLR4_4_1bebelpnjv522.R.flushNeeded();}}

    /**
     * Set the text color of the commentary box.
     * @param col Foreground color, instance of java.awt.color
     */
    public void setColor(Color col) {try{__CLR4_4_1bebelpnjv522.R.inc(475);
	__CLR4_4_1bebelpnjv522.R.inc(476);this.fg = col;
    }finally{__CLR4_4_1bebelpnjv522.R.flushNeeded();}}

    /**
     * This method is to be called from the <code>paint(Graphics g)</code>
     * method of the drawing panel/canvas. It uses the graphical context
     * of the drawing panel/canvas to construct the commentary box on
     * the drawing panel/canvas.
     * @param g Graphical context from the <code>paint(Graphics g)</code>
     * method of the drawing panel/canvas.
     * @see Graphics
     */
    public void draw(Graphics g) {try{__CLR4_4_1bebelpnjv522.R.inc(477);
	__CLR4_4_1bebelpnjv522.R.inc(478);g.setColor(bg);
	__CLR4_4_1bebelpnjv522.R.inc(479);g.fillPolygon(xPts, yPts, nPts);

	__CLR4_4_1bebelpnjv522.R.inc(480);g.setColor(Color.black);
	__CLR4_4_1bebelpnjv522.R.inc(481);g.drawPolygon(xPts, yPts, nPts);

	__CLR4_4_1bebelpnjv522.R.inc(482);g.setColor(fg);
	__CLR4_4_1bebelpnjv522.R.inc(483);g.setFont(font);
	__CLR4_4_1bebelpnjv522.R.inc(484);g.drawString(str, topLeft.x + 4, topLeft.y + height - 10);
    }finally{__CLR4_4_1bebelpnjv522.R.flushNeeded();}}
}
