/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;

class ControlPanel extends Panel {public static class __CLR4_4_1dtdtlpnjv52d{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,599,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    AlgAnimFrame frame;
    Button run_button, stop_button, step_button, quit_button;
    Choice data_choice, delay_choice;
    int data_selected = 0;
    TextField mesg;

    public ControlPanel(AlgAnimFrame frame, String algname) {try{__CLR4_4_1dtdtlpnjv52d.R.inc(497);
	__CLR4_4_1dtdtlpnjv52d.R.inc(498);setBackground( Color.white );
	__CLR4_4_1dtdtlpnjv52d.R.inc(499);GridBagLayout gb = new GridBagLayout();
	__CLR4_4_1dtdtlpnjv52d.R.inc(500);GridBagConstraints gc = new GridBagConstraints();
	__CLR4_4_1dtdtlpnjv52d.R.inc(501);gc.insets = new Insets(5, 5, 5, 5);
	__CLR4_4_1dtdtlpnjv52d.R.inc(502);setLayout(gb);
	__CLR4_4_1dtdtlpnjv52d.R.inc(503);gc.fill = GridBagConstraints.BOTH;
	__CLR4_4_1dtdtlpnjv52d.R.inc(504);gc.weightx = 1.0;
	
        __CLR4_4_1dtdtlpnjv52d.R.inc(505);this.frame = frame;

        __CLR4_4_1dtdtlpnjv52d.R.inc(506);run_button = new Button("Run ");
	__CLR4_4_1dtdtlpnjv52d.R.inc(507);gb.setConstraints(run_button, gc);
        __CLR4_4_1dtdtlpnjv52d.R.inc(508);this.add(run_button);
	
	__CLR4_4_1dtdtlpnjv52d.R.inc(509);step_button = new Button("Next Step");
	__CLR4_4_1dtdtlpnjv52d.R.inc(510);gb.setConstraints(step_button, gc);
	__CLR4_4_1dtdtlpnjv52d.R.inc(511);this.add(step_button);

        __CLR4_4_1dtdtlpnjv52d.R.inc(512);stop_button = new Button("Stop");
        __CLR4_4_1dtdtlpnjv52d.R.inc(513);stop_button.disable();
	__CLR4_4_1dtdtlpnjv52d.R.inc(514);gb.setConstraints(stop_button, gc);
        __CLR4_4_1dtdtlpnjv52d.R.inc(515);this.add(stop_button);

        __CLR4_4_1dtdtlpnjv52d.R.inc(516);data_choice = new Choice();
        // assigning data sets
        __CLR4_4_1dtdtlpnjv52d.R.inc(517);for (int i = 0; (((i < frame.alg.dataSets.length)&&(__CLR4_4_1dtdtlpnjv52d.R.iget(518)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(519)==0&false)); i++)
            {__CLR4_4_1dtdtlpnjv52d.R.inc(520);data_choice.addItem(frame.alg.dataSets[i]);
	//data_choice.select(1);
	}__CLR4_4_1dtdtlpnjv52d.R.inc(521);gc.insets = new Insets(5, 5, 5, 30);
	__CLR4_4_1dtdtlpnjv52d.R.inc(522);gc.gridwidth = GridBagConstraints.REMAINDER;
	__CLR4_4_1dtdtlpnjv52d.R.inc(523);gb.setConstraints(data_choice, gc);
	__CLR4_4_1dtdtlpnjv52d.R.inc(524);this.add(data_choice);
	
	__CLR4_4_1dtdtlpnjv52d.R.inc(525);delay_choice = new Choice();
	__CLR4_4_1dtdtlpnjv52d.R.inc(526);delay_choice.addItem("Delay 200msec");
	__CLR4_4_1dtdtlpnjv52d.R.inc(527);delay_choice.addItem("Delay 400msec");
	__CLR4_4_1dtdtlpnjv52d.R.inc(528);delay_choice.addItem("Delay 600msec");
	__CLR4_4_1dtdtlpnjv52d.R.inc(529);delay_choice.addItem("Delay 800msec");
	__CLR4_4_1dtdtlpnjv52d.R.inc(530);delay_choice.addItem("Delay 1000msec");
	__CLR4_4_1dtdtlpnjv52d.R.inc(531);delay_choice.select(0);
	__CLR4_4_1dtdtlpnjv52d.R.inc(532);gc.gridwidth = GridBagConstraints.RELATIVE;
	__CLR4_4_1dtdtlpnjv52d.R.inc(533);gc.insets = new Insets(5, 5, 5, 100);
	__CLR4_4_1dtdtlpnjv52d.R.inc(534);gb.setConstraints(delay_choice, gc);	
	__CLR4_4_1dtdtlpnjv52d.R.inc(535);this.add(delay_choice);

	__CLR4_4_1dtdtlpnjv52d.R.inc(536);quit_button = new Button("Quit");
	__CLR4_4_1dtdtlpnjv52d.R.inc(537);gc.gridwidth = GridBagConstraints.REMAINDER;
	__CLR4_4_1dtdtlpnjv52d.R.inc(538);gc.insets = new Insets(5, 5, 5, 10);
	__CLR4_4_1dtdtlpnjv52d.R.inc(539);gb.setConstraints(quit_button, gc);
	__CLR4_4_1dtdtlpnjv52d.R.inc(540);this.add(quit_button);
	
	__CLR4_4_1dtdtlpnjv52d.R.inc(541);mesg = new TextField(50);
	__CLR4_4_1dtdtlpnjv52d.R.inc(542);mesg.disable();
	__CLR4_4_1dtdtlpnjv52d.R.inc(543);gc.insets = new Insets(5, 5, 5, 5);
	__CLR4_4_1dtdtlpnjv52d.R.inc(544);gb.setConstraints(mesg, gc);
	__CLR4_4_1dtdtlpnjv52d.R.inc(545);this.add(mesg);
    }finally{__CLR4_4_1dtdtlpnjv52d.R.flushNeeded();}} // ControlPanel()

    public boolean action(Event e, Object arg) {try{__CLR4_4_1dtdtlpnjv52d.R.inc(546);
        __CLR4_4_1dtdtlpnjv52d.R.inc(547);Object target = e.target;
 
        __CLR4_4_1dtdtlpnjv52d.R.inc(548);if ((((target == run_button)&&(__CLR4_4_1dtdtlpnjv52d.R.iget(549)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(550)==0&false))) {{
	    __CLR4_4_1dtdtlpnjv52d.R.inc(551);setText("Running algorithm continuously...");
            __CLR4_4_1dtdtlpnjv52d.R.inc(552);frame.startAlg();
            __CLR4_4_1dtdtlpnjv52d.R.inc(553);return true;
        } }else {__CLR4_4_1dtdtlpnjv52d.R.inc(554);if ((((target == stop_button)&&(__CLR4_4_1dtdtlpnjv52d.R.iget(555)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(556)==0&false))) {{
	    __CLR4_4_1dtdtlpnjv52d.R.inc(557);setText("Stop button pressed...");
	    __CLR4_4_1dtdtlpnjv52d.R.inc(558);frame.setStep(false);
            __CLR4_4_1dtdtlpnjv52d.R.inc(559);if ((((frame.alg.isAlive())&&(__CLR4_4_1dtdtlpnjv52d.R.iget(560)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(561)==0&false)))
                {__CLR4_4_1dtdtlpnjv52d.R.inc(562);frame.alg.stop();
            }__CLR4_4_1dtdtlpnjv52d.R.inc(563);frame.finishAlg();
            __CLR4_4_1dtdtlpnjv52d.R.inc(564);return true;
	} }else {__CLR4_4_1dtdtlpnjv52d.R.inc(565);if ((((target == step_button)&&(__CLR4_4_1dtdtlpnjv52d.R.iget(566)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(567)==0&false))) {{
	    __CLR4_4_1dtdtlpnjv52d.R.inc(568);setText("Executing next step...");
	    __CLR4_4_1dtdtlpnjv52d.R.inc(569);frame.setStep(true);
	    __CLR4_4_1dtdtlpnjv52d.R.inc(570);if ((((!frame.alg.isAlive())&&(__CLR4_4_1dtdtlpnjv52d.R.iget(571)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(572)==0&false)))
		{__CLR4_4_1dtdtlpnjv52d.R.inc(573);frame.startAlg();
	   // else
		//frame.alg.resume();
	    }__CLR4_4_1dtdtlpnjv52d.R.inc(574);return true;
	} }else {__CLR4_4_1dtdtlpnjv52d.R.inc(575);if ((((target == quit_button)&&(__CLR4_4_1dtdtlpnjv52d.R.iget(576)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(577)==0&false))) {{
	    __CLR4_4_1dtdtlpnjv52d.R.inc(578);frame.parentApp.start_button.enable();
	    __CLR4_4_1dtdtlpnjv52d.R.inc(579);frame.dispose();
        } }else {__CLR4_4_1dtdtlpnjv52d.R.inc(580);if ((((target instanceof Choice)&&(__CLR4_4_1dtdtlpnjv52d.R.iget(581)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(582)==0&false))) {{
	    __CLR4_4_1dtdtlpnjv52d.R.inc(583);if ((((target == data_choice)&&(__CLR4_4_1dtdtlpnjv52d.R.iget(584)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(585)==0&false))) {{
		__CLR4_4_1dtdtlpnjv52d.R.inc(586);data_selected = data_choice.getSelectedIndex();
		__CLR4_4_1dtdtlpnjv52d.R.inc(587);frame.alg.generateData();
		//setText("Data generated...");
	    } }else {__CLR4_4_1dtdtlpnjv52d.R.inc(588);if ((((target == delay_choice)&&(__CLR4_4_1dtdtlpnjv52d.R.iget(589)!=0|true))||(__CLR4_4_1dtdtlpnjv52d.R.iget(590)==0&false))) {{
		//System.out.println("delay");
		__CLR4_4_1dtdtlpnjv52d.R.inc(591);frame.delay = (delay_choice.getSelectedIndex() + 1) * 200;
		__CLR4_4_1dtdtlpnjv52d.R.inc(592);frame.alg.setDelay(frame.delay);
		__CLR4_4_1dtdtlpnjv52d.R.inc(593);setText("Animation delay now set to " + frame.delay + " msec...");
	    }
        }}}
        }}}}}__CLR4_4_1dtdtlpnjv52d.R.inc(594);return false;
    }finally{__CLR4_4_1dtdtlpnjv52d.R.flushNeeded();}} // action()

    public int getDataChoice() {try{__CLR4_4_1dtdtlpnjv52d.R.inc(595);
        __CLR4_4_1dtdtlpnjv52d.R.inc(596);return data_selected;
    }finally{__CLR4_4_1dtdtlpnjv52d.R.flushNeeded();}}

    public void setText(String str) {try{__CLR4_4_1dtdtlpnjv52d.R.inc(597);
	__CLR4_4_1dtdtlpnjv52d.R.inc(598);mesg.setText(str);
    }finally{__CLR4_4_1dtdtlpnjv52d.R.flushNeeded();}}
} // class ControlPanel
