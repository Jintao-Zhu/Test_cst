/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ *//* AlgAnimFrame.java */
package net.mooctest;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;

@java.lang.SuppressWarnings({"fallthrough"}) public class AlgAnimFrame extends Frame {public static class __CLR4_4_1jjlpnjv518{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,178,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    AlgAnimApp parentApp;
    URL sourceURL;
    String algname;
    AlgThread alg = null;
    int delay = 200;

    Panel drawingPanel;
    TextFrame tf;
    ComPanel cpanel;
    ControlPanel control_panel;
    Scrollbar vScrollbar;
    boolean step = false, stepWait = false;

    public AlgAnimFrame(AlgAnimApp parentApp, URL sourceURL) {try{__CLR4_4_1jjlpnjv518.R.inc(19);
        __CLR4_4_1jjlpnjv518.R.inc(20);this.parentApp = parentApp;
        __CLR4_4_1jjlpnjv518.R.inc(21);this.sourceURL = sourceURL;
        __CLR4_4_1jjlpnjv518.R.inc(22);this.algname = parentApp.getParameter("algname");

        __CLR4_4_1jjlpnjv518.R.inc(23);setTitle(algname);
        __CLR4_4_1jjlpnjv518.R.inc(24);alg = new AlgThread(this);

        // Arrange panels within frame
        __CLR4_4_1jjlpnjv518.R.inc(25);GridBagLayout gb = new GridBagLayout();
        __CLR4_4_1jjlpnjv518.R.inc(26);GridBagConstraints gc = new GridBagConstraints();
        __CLR4_4_1jjlpnjv518.R.inc(27);setLayout( gb );
        __CLR4_4_1jjlpnjv518.R.inc(28);gc.fill = GridBagConstraints.BOTH;
        __CLR4_4_1jjlpnjv518.R.inc(29);gc.gridwidth = GridBagConstraints.RELATIVE;
        /* Give any extra space to the animation panel */
        __CLR4_4_1jjlpnjv518.R.inc(30);gc.weighty = 1.0;
        __CLR4_4_1jjlpnjv518.R.inc(31);gc.weightx = 1.0;
        __CLR4_4_1jjlpnjv518.R.inc(32);gc.gridx = 0; __CLR4_4_1jjlpnjv518.R.inc(33);gc.gridy = 0;
        __CLR4_4_1jjlpnjv518.R.inc(34);gb.setConstraints( drawingPanel, gc );
        __CLR4_4_1jjlpnjv518.R.inc(35);add(drawingPanel);

        /* Source panel */
	__CLR4_4_1jjlpnjv518.R.inc(36);Panel p = new Panel();
        __CLR4_4_1jjlpnjv518.R.inc(37);tf = new TextFrame( sourceURL, sourceURL.getFile() );
	__CLR4_4_1jjlpnjv518.R.inc(38);vScrollbar = new Scrollbar(Scrollbar.VERTICAL);
	__CLR4_4_1jjlpnjv518.R.inc(39);vScrollbar.setValues(0, 10, 0, 90);
	__CLR4_4_1jjlpnjv518.R.inc(40);vScrollbar.setPageIncrement(10);
	__CLR4_4_1jjlpnjv518.R.inc(41);p.setLayout(new BorderLayout());
	__CLR4_4_1jjlpnjv518.R.inc(42);p.add("Center", tf);
	__CLR4_4_1jjlpnjv518.R.inc(43);p.add("East", vScrollbar);
	__CLR4_4_1jjlpnjv518.R.inc(44);p.validate();
	
        __CLR4_4_1jjlpnjv518.R.inc(45);gc.gridx = GridBagConstraints.RELATIVE; __CLR4_4_1jjlpnjv518.R.inc(46);gc.gridy = 0;
        /* The text panel's requirements are known and fixed */
        __CLR4_4_1jjlpnjv518.R.inc(47);gc.weightx = 1.0;
        __CLR4_4_1jjlpnjv518.R.inc(48);gc.gridwidth = GridBagConstraints.REMAINDER;
        __CLR4_4_1jjlpnjv518.R.inc(49);gb.setConstraints( p, gc );
        __CLR4_4_1jjlpnjv518.R.inc(50);add( p );
 
        /* Commentary panel */
        __CLR4_4_1jjlpnjv518.R.inc(51);gc.gridwidth = GridBagConstraints.RELATIVE;
        __CLR4_4_1jjlpnjv518.R.inc(52);gc.gridx = 0; __CLR4_4_1jjlpnjv518.R.inc(53);gc.gridy = GridBagConstraints.RELATIVE;
        __CLR4_4_1jjlpnjv518.R.inc(54);gc.weighty = 0.0;
        __CLR4_4_1jjlpnjv518.R.inc(55);gc.gridheight = GridBagConstraints.REMAINDER;
        __CLR4_4_1jjlpnjv518.R.inc(56);cpanel = new ComPanel( algname, 3 );
        __CLR4_4_1jjlpnjv518.R.inc(57);gb.setConstraints( cpanel, gc );
        __CLR4_4_1jjlpnjv518.R.inc(58);add( cpanel );
 
        /* Control panel */
        __CLR4_4_1jjlpnjv518.R.inc(59);control_panel = new ControlPanel(this, algname);
        __CLR4_4_1jjlpnjv518.R.inc(60);gc.gridx = gc.gridy = GridBagConstraints.RELATIVE;
        __CLR4_4_1jjlpnjv518.R.inc(61);gc.gridwidth = GridBagConstraints.REMAINDER;
        __CLR4_4_1jjlpnjv518.R.inc(62);gb.setConstraints( control_panel, gc );
        __CLR4_4_1jjlpnjv518.R.inc(63);add( control_panel );

        // generate initial data set
        __CLR4_4_1jjlpnjv518.R.inc(64);alg.generateData();

        __CLR4_4_1jjlpnjv518.R.inc(65);pack();
        __CLR4_4_1jjlpnjv518.R.inc(66);validate();
        __CLR4_4_1jjlpnjv518.R.inc(67);show();
    }finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}} // init()

    public Dimension preferredSize() {try{__CLR4_4_1jjlpnjv518.R.inc(68);
        __CLR4_4_1jjlpnjv518.R.inc(69);return new Dimension(920,600);
    }finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}}

    public boolean handleEvent(Event event) {try{__CLR4_4_1jjlpnjv518.R.inc(70);
        __CLR4_4_1jjlpnjv518.R.inc(71);if ((((event.id == Event.WINDOW_DESTROY)&&(__CLR4_4_1jjlpnjv518.R.iget(72)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(73)==0&false))) {{
            __CLR4_4_1jjlpnjv518.R.inc(74);if ((((alg != null)&&(__CLR4_4_1jjlpnjv518.R.iget(75)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(76)==0&false))) {{
              __CLR4_4_1jjlpnjv518.R.inc(77);alg.drawingPanel = null;
              __CLR4_4_1jjlpnjv518.R.inc(78);if ((((alg.isAlive())&&(__CLR4_4_1jjlpnjv518.R.iget(79)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(80)==0&false)))
                {__CLR4_4_1jjlpnjv518.R.inc(81);alg.stop();
            }}
            }__CLR4_4_1jjlpnjv518.R.inc(82);parentApp.start_button.enable();
            __CLR4_4_1jjlpnjv518.R.inc(83);dispose();
        } }else {__CLR4_4_1jjlpnjv518.R.inc(84);if ((((event.target == vScrollbar)&&(__CLR4_4_1jjlpnjv518.R.iget(85)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(86)==0&false))) {{
	    boolean __CLB4_4_1_bool0=false;__CLR4_4_1jjlpnjv518.R.inc(87);switch (event.id) {
		case Event.SCROLL_LINE_UP:if (!__CLB4_4_1_bool0) {__CLR4_4_1jjlpnjv518.R.inc(88);__CLB4_4_1_bool0=true;}
		case Event.SCROLL_LINE_DOWN:if (!__CLB4_4_1_bool0) {__CLR4_4_1jjlpnjv518.R.inc(89);__CLB4_4_1_bool0=true;}
		case Event.SCROLL_PAGE_UP:if (!__CLB4_4_1_bool0) {__CLR4_4_1jjlpnjv518.R.inc(90);__CLB4_4_1_bool0=true;}
		case Event.SCROLL_PAGE_DOWN:if (!__CLB4_4_1_bool0) {__CLR4_4_1jjlpnjv518.R.inc(91);__CLB4_4_1_bool0=true;}
		case Event.SCROLL_ABSOLUTE:if (!__CLB4_4_1_bool0) {__CLR4_4_1jjlpnjv518.R.inc(92);__CLB4_4_1_bool0=true;}
		    __CLR4_4_1jjlpnjv518.R.inc(93);int val = ((Scrollbar)(event.target)).getValue();
		    __CLR4_4_1jjlpnjv518.R.inc(94);int min = ((Scrollbar)(event.target)).getMinimum();
		    __CLR4_4_1jjlpnjv518.R.inc(95);int max = ((Scrollbar)(event.target)).getMaximum();
		    __CLR4_4_1jjlpnjv518.R.inc(96);tf.setStart(val*tf.n_lines/(max - min + 10));
		    //System.out.println(((Integer)event.arg).intValue());
		    __CLR4_4_1jjlpnjv518.R.inc(97);tf.repaint();
	    }
	}
        }}__CLR4_4_1jjlpnjv518.R.inc(98);return super.handleEvent(event);
    }finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}}

    public void setText( int n, String s ) {try{__CLR4_4_1jjlpnjv518.R.inc(99);
        __CLR4_4_1jjlpnjv518.R.inc(100);cpanel.setText( n, s );
    }finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}}
 
    public void Highlight( int n ) {try{__CLR4_4_1jjlpnjv518.R.inc(101);
        __CLR4_4_1jjlpnjv518.R.inc(102);tf.Highlight(n);
	__CLR4_4_1jjlpnjv518.R.inc(103);int numLineVisible = tf.size().height/tf.line_space;
	
	__CLR4_4_1jjlpnjv518.R.inc(104);if ( ((((n < (tf.start + 2)) || (n > (tf.start + numLineVisible - 2)))&&(__CLR4_4_1jjlpnjv518.R.iget(105)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(106)==0&false))) {{
	    __CLR4_4_1jjlpnjv518.R.inc(107);int max = vScrollbar.getMaximum();
	    __CLR4_4_1jjlpnjv518.R.inc(108);int min = vScrollbar.getMinimum();
	    __CLR4_4_1jjlpnjv518.R.inc(109);int startLine = n - numLineVisible/2;
	    __CLR4_4_1jjlpnjv518.R.inc(110);if ((((startLine > 0)&&(__CLR4_4_1jjlpnjv518.R.iget(111)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(112)==0&false))) {{
	    	__CLR4_4_1jjlpnjv518.R.inc(113);tf.setStart(startLine);
	    	__CLR4_4_1jjlpnjv518.R.inc(114);vScrollbar.setValue(startLine * (max - min) / tf.n_lines);
	    } }else {{
	    	__CLR4_4_1jjlpnjv518.R.inc(115);tf.setStart(0);
	    	__CLR4_4_1jjlpnjv518.R.inc(116);vScrollbar.setValue(0);
	    }
	}}
	}__CLR4_4_1jjlpnjv518.R.inc(117);try {
	    __CLR4_4_1jjlpnjv518.R.inc(118);Thread.sleep(delay/4);
	} catch (InterruptedException e) {}
    }finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}}

    public void restoreDrawingPanelColor() {try{__CLR4_4_1jjlpnjv518.R.inc(119);
        __CLR4_4_1jjlpnjv518.R.inc(120);alg.restoreDrawingPanelColor();
    }finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}}

    public void startAlg() {try{__CLR4_4_1jjlpnjv518.R.inc(121);
	__CLR4_4_1jjlpnjv518.R.inc(122);if ((((alg.isAlive() && !stepWait)&&(__CLR4_4_1jjlpnjv518.R.iget(123)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(124)==0&false))) {{
	    __CLR4_4_1jjlpnjv518.R.inc(125);alg.stop();
	    __CLR4_4_1jjlpnjv518.R.inc(126);alg = new AlgThread(this);
	    __CLR4_4_1jjlpnjv518.R.inc(127);alg.start();
	} }else {__CLR4_4_1jjlpnjv518.R.inc(128);if ((((alg.isAlive() && stepWait)&&(__CLR4_4_1jjlpnjv518.R.iget(129)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(130)==0&false))) {{
	    //alg.resume();
	    //System.out.println("alive");
	    __CLR4_4_1jjlpnjv518.R.inc(131);step = true;
	    __CLR4_4_1jjlpnjv518.R.inc(132);stepWait = false;
	} }else {{ // alg.isAlive() == false
	    __CLR4_4_1jjlpnjv518.R.inc(133);alg = new AlgThread(this);
	    __CLR4_4_1jjlpnjv518.R.inc(134);alg.start();
	}
	}}__CLR4_4_1jjlpnjv518.R.inc(135);if ((((!stepWait)&&(__CLR4_4_1jjlpnjv518.R.iget(136)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(137)==0&false))) {{
	    __CLR4_4_1jjlpnjv518.R.inc(138);control_panel.run_button.disable();
	    __CLR4_4_1jjlpnjv518.R.inc(139);control_panel.data_choice.disable();
	    __CLR4_4_1jjlpnjv518.R.inc(140);control_panel.stop_button.enable();
	    __CLR4_4_1jjlpnjv518.R.inc(141);control_panel.step_button.enable();
	}
    }}finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}}

    public void finishAlg() {try{__CLR4_4_1jjlpnjv518.R.inc(142);
        __CLR4_4_1jjlpnjv518.R.inc(143);control_panel.stop_button.disable();
        __CLR4_4_1jjlpnjv518.R.inc(144);control_panel.run_button.enable();
        __CLR4_4_1jjlpnjv518.R.inc(145);control_panel.data_choice.enable();
	__CLR4_4_1jjlpnjv518.R.inc(146);control_panel.step_button.enable(); __CLR4_4_1jjlpnjv518.R.inc(147);step = false;
        __CLR4_4_1jjlpnjv518.R.inc(148);alg.restoreDrawingPanelColor();
    }finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}}
    
    public void waitStep() {try{__CLR4_4_1jjlpnjv518.R.inc(149);
	__CLR4_4_1jjlpnjv518.R.inc(150);if ((((step)&&(__CLR4_4_1jjlpnjv518.R.iget(151)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(152)==0&false))) {{
	    __CLR4_4_1jjlpnjv518.R.inc(153);control_panel.setText("Click NEXT STEP...");
	    __CLR4_4_1jjlpnjv518.R.inc(154);control_panel.step_button.enable();
	    __CLR4_4_1jjlpnjv518.R.inc(155);control_panel.repaint();
	    __CLR4_4_1jjlpnjv518.R.inc(156);step = false; __CLR4_4_1jjlpnjv518.R.inc(157);stepWait = true;
	    __CLR4_4_1jjlpnjv518.R.inc(158);while ((((!step)&&(__CLR4_4_1jjlpnjv518.R.iget(159)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(160)==0&false))) {{
		__CLR4_4_1jjlpnjv518.R.inc(161);try {
		    __CLR4_4_1jjlpnjv518.R.inc(162);Thread.sleep(100);
		} catch (InterruptedException e) {}
	    }
	    }__CLR4_4_1jjlpnjv518.R.inc(163);if ((((!stepWait)&&(__CLR4_4_1jjlpnjv518.R.iget(164)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(165)==0&false)))
		{__CLR4_4_1jjlpnjv518.R.inc(166);step = false;
	}}
    }}finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}}
    
    public void setStep(boolean step) {try{__CLR4_4_1jjlpnjv518.R.inc(167);
	//setText(2, "Stepping through the next line(s)...");
	__CLR4_4_1jjlpnjv518.R.inc(168);this.step = step;
        __CLR4_4_1jjlpnjv518.R.inc(169);control_panel.stop_button.disable();
        __CLR4_4_1jjlpnjv518.R.inc(170);control_panel.run_button.enable();
        __CLR4_4_1jjlpnjv518.R.inc(171);control_panel.data_choice.enable();
	__CLR4_4_1jjlpnjv518.R.inc(172);if ((((step)&&(__CLR4_4_1jjlpnjv518.R.iget(173)!=0|true))||(__CLR4_4_1jjlpnjv518.R.iget(174)==0&false))) {{
	    __CLR4_4_1jjlpnjv518.R.inc(175);control_panel.step_button.disable();	
	    __CLR4_4_1jjlpnjv518.R.inc(176);stepWait = true;
	} }else 
	    {__CLR4_4_1jjlpnjv518.R.inc(177);stepWait = false;
    }}finally{__CLR4_4_1jjlpnjv518.R.flushNeeded();}}
} // class AlgAnimApp
