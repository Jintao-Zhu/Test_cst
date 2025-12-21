/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ *//* AlgThread.java */
package net.mooctest;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class AlgThread extends Thread {public static class __CLR4_4_14y4ylpnjv51r{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,410,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    int capacity = 7;
    static String[] dataSets = {"Queue Capacity 7", "Queue Capacity 15", 
				"Queue Capacity 31"};

    AlgAnimFrame frame;
    DrawingPanel drawingPanel;
    Node tree;
    Heap heap;

    public AlgThread(AlgAnimFrame frame) {try{__CLR4_4_14y4ylpnjv51r.R.inc(178);
        __CLR4_4_14y4ylpnjv51r.R.inc(179);this.frame = frame;

        __CLR4_4_14y4ylpnjv51r.R.inc(180);if ((((frame != null && frame.alg != null && 
            frame.alg.drawingPanel != null)&&(__CLR4_4_14y4ylpnjv51r.R.iget(181)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(182)==0&false))) {{
            // drawingPanel already created -> this constructor called from
            // clicking the run button -> use the generated data set
            __CLR4_4_14y4ylpnjv51r.R.inc(183);this.drawingPanel = frame.alg.drawingPanel;
	    __CLR4_4_14y4ylpnjv51r.R.inc(184);this.heap = frame.alg.heap;
	    __CLR4_4_14y4ylpnjv51r.R.inc(185);this.capacity = frame.alg.capacity;
        } }else {{
            // this constructor called from Frame constructor
            __CLR4_4_14y4ylpnjv51r.R.inc(186);drawingPanel = new DrawingPanel();
            __CLR4_4_14y4ylpnjv51r.R.inc(187);frame.drawingPanel = (Panel)this.drawingPanel;
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(188);heap = new Heap(drawingPanel, capacity); 
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(189);drawingPanel.drawHeap(heap);
        }
    }}finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public void setDelay(int delay) {try{__CLR4_4_14y4ylpnjv51r.R.inc(190);
	__CLR4_4_14y4ylpnjv51r.R.inc(191);drawingPanel.setDelay(delay);
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public void generateData() {try{__CLR4_4_14y4ylpnjv51r.R.inc(192);
        __CLR4_4_14y4ylpnjv51r.R.inc(193);int choice = frame.control_panel.getDataChoice();
        __CLR4_4_14y4ylpnjv51r.R.inc(194);if ((((choice == 0)&&(__CLR4_4_14y4ylpnjv51r.R.iget(195)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(196)==0&false))) {{
	    __CLR4_4_14y4ylpnjv51r.R.inc(197);capacity = 7;
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(198);heap = new Heap(drawingPanel, capacity); 
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(199);drawingPanel.drawHeap(heap);
        } }else {__CLR4_4_14y4ylpnjv51r.R.inc(200);if ((((choice == 1)&&(__CLR4_4_14y4ylpnjv51r.R.iget(201)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(202)==0&false))) {{
	    __CLR4_4_14y4ylpnjv51r.R.inc(203);capacity = 15;
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(204);heap = new Heap(drawingPanel, capacity); 
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(205);drawingPanel.drawHeap(heap);
	} }else {__CLR4_4_14y4ylpnjv51r.R.inc(206);if ((((choice == 2)&&(__CLR4_4_14y4ylpnjv51r.R.iget(207)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(208)==0&false))) {{
	    __CLR4_4_14y4ylpnjv51r.R.inc(209);capacity = 31;
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(210);heap = new Heap(drawingPanel, capacity); 
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(211);drawingPanel.drawHeap(heap);
	}
	/*-*/}}}__CLR4_4_14y4ylpnjv51r.R.inc(212);heap.initHeap();
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public int generateRandomData() {try{__CLR4_4_14y4ylpnjv51r.R.inc(213);
        __CLR4_4_14y4ylpnjv51r.R.inc(214);return (new Double(Math.random()*99).intValue() + 1);
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public boolean isInput() {try{__CLR4_4_14y4ylpnjv51r.R.inc(215);
	__CLR4_4_14y4ylpnjv51r.R.inc(216);return (generateRandomData() < 70);
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}
 
    public int[] setHeapWithSize(int[] a, int i) {try{__CLR4_4_14y4ylpnjv51r.R.inc(217);
	__CLR4_4_14y4ylpnjv51r.R.inc(218);int[] old = a;
	__CLR4_4_14y4ylpnjv51r.R.inc(219);a = new int[i];
	__CLR4_4_14y4ylpnjv51r.R.inc(220);for (int j = 0; (((j < i)&&(__CLR4_4_14y4ylpnjv51r.R.iget(221)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(222)==0&false)); j++)
	    {__CLR4_4_14y4ylpnjv51r.R.inc(223);a[j] = old[j];
	}__CLR4_4_14y4ylpnjv51r.R.inc(224);return a;
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}
/*--------------------------------------------------------------------*/
    public void animatePriorityQueue() {try{__CLR4_4_14y4ylpnjv51r.R.inc(225); /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(226);int line_num = 0;
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(227);frame.Highlight(line_num);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(228);frame.setText(0, "Executing priority Q animation demo..");
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(229);frame.setText(0, "This priority Q repeatedly receives random inputs and extracts outputs...");
	__CLR4_4_14y4ylpnjv51r.R.inc(230);int[] Q = new int[0];
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(231);frame.Highlight(line_num + 1);
	__CLR4_4_14y4ylpnjv51r.R.inc(232);while (true) {{
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(233);frame.Highlight(line_num + 2);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(234);frame.Highlight(line_num + 3);
	    __CLR4_4_14y4ylpnjv51r.R.inc(235);if ((((isInput() && Q.length < capacity)&&(__CLR4_4_14y4ylpnjv51r.R.iget(236)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(237)==0&false))) {{
	    	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(238);frame.Highlight(line_num + 4);
		/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(239);frame.setText(1, "Inserting into the queue...");
	    	__CLR4_4_14y4ylpnjv51r.R.inc(240);int input = generateRandomData(); 
	    	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(241);heap.addInput(input); __CLR4_4_14y4ylpnjv51r.R.inc(242);frame.Highlight(line_num + 5);
		__CLR4_4_14y4ylpnjv51r.R.inc(243);Q = addHeap(Q, input);
	    	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(244);frame.Highlight(line_num + 5);
	    } }else {__CLR4_4_14y4ylpnjv51r.R.inc(245);if ((((Q.length > 0)&&(__CLR4_4_14y4ylpnjv51r.R.iget(246)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(247)==0&false))) {{
	    	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(248);frame.Highlight(line_num + 6);
		/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(249);frame.setText(1, "Extracting item from queue...");
	    	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(250);frame.Highlight(line_num + 7);
		__CLR4_4_14y4ylpnjv51r.R.inc(251);Q = delHeap(Q);
	    	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(252);frame.Highlight(line_num + 8);
	    }
	    /*-*/}}__CLR4_4_14y4ylpnjv51r.R.inc(253);frame.Highlight(line_num + 8);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(254);frame.Highlight(line_num + 9); __CLR4_4_14y4ylpnjv51r.R.inc(255);frame.waitStep();
	}
    }}finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public int[] addHeap(int[] a, int i) {try{__CLR4_4_14y4ylpnjv51r.R.inc(256); /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(257);int line_num = 12;
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(258);frame.Highlight(line_num);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(259);frame.setText(1, "Addition to the heap...");
	__CLR4_4_14y4ylpnjv51r.R.inc(260);int[] result = new int[a.length + 1];
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(261);frame.Highlight(line_num + 1);
	__CLR4_4_14y4ylpnjv51r.R.inc(262);for (int k = 0; (((k < a.length)&&(__CLR4_4_14y4ylpnjv51r.R.iget(263)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(264)==0&false)); k++)
	    {__CLR4_4_14y4ylpnjv51r.R.inc(265);result[k] = a[k];
	}__CLR4_4_14y4ylpnjv51r.R.inc(266);result[a.length] = i;
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(267);frame.Highlight(line_num + 2);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(268);frame.Highlight(line_num + 3);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(269);frame.Highlight(line_num + 4); __CLR4_4_14y4ylpnjv51r.R.inc(270);heap.input2heap();
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(271);frame.Highlight(line_num + 5);
	__CLR4_4_14y4ylpnjv51r.R.inc(272);up(result, result.length);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(273);frame.Highlight(line_num + 5);

	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(274);frame.Highlight(line_num + 6);
	__CLR4_4_14y4ylpnjv51r.R.inc(275);return result;
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public void up(int[] a, int i) {try{__CLR4_4_14y4ylpnjv51r.R.inc(276); /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(277);int line_num = 21;
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(278);frame.Highlight(line_num); __CLR4_4_14y4ylpnjv51r.R.inc(279);heap.highlight(i-1); __CLR4_4_14y4ylpnjv51r.R.inc(280);heap.redraw();
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(281);frame.Highlight(line_num + 1);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(282);frame.setText(2, "Moving UP...");
	__CLR4_4_14y4ylpnjv51r.R.inc(283);if ((((parent(i) > 0)&&(__CLR4_4_14y4ylpnjv51r.R.iget(284)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(285)==0&false))) {{
	    __CLR4_4_14y4ylpnjv51r.R.inc(286);int p = parent(i);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(287);frame.Highlight(line_num + 2);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(288);heap.highlight(p-1); __CLR4_4_14y4ylpnjv51r.R.inc(289);heap.redraw();
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(290);frame.Highlight(line_num + 3);
	    __CLR4_4_14y4ylpnjv51r.R.inc(291);if ((((a[i-1] > a[p-1])&&(__CLR4_4_14y4ylpnjv51r.R.iget(292)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(293)==0&false))) {{
		// swap with parent and up parent
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(294);frame.setText(2, "Exchange with parent...");
		__CLR4_4_14y4ylpnjv51r.R.inc(295);int tmp = a[i-1]; __CLR4_4_14y4ylpnjv51r.R.inc(296);a[i-1] = a[p-1]; __CLR4_4_14y4ylpnjv51r.R.inc(297);a[p-1] = tmp;
	    	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(298);frame.Highlight(line_num + 5); 
		/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(299);heap.exchangeArrow(p-1,i-1);
		/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(300);heap.setNode(i-1, a[i-1]);
		/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(301);heap.setNode(p-1, a[p-1]);
		/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(302);heap.restore(i-1); __CLR4_4_14y4ylpnjv51r.R.inc(303);heap.restore(p-1); __CLR4_4_14y4ylpnjv51r.R.inc(304);heap.redraw();
	    	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(305);frame.Highlight(line_num + 6);
		__CLR4_4_14y4ylpnjv51r.R.inc(306);up(a, p);
	    	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(307);frame.Highlight(line_num + 6);
	    }
	    /*-*/}__CLR4_4_14y4ylpnjv51r.R.inc(308);frame.Highlight(line_num + 7);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(309);heap.restore(p-1);
	}
	/*-*/}__CLR4_4_14y4ylpnjv51r.R.inc(310);frame.Highlight(line_num + 8);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(311);heap.restore(i-1);
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public int[] delHeap(int[] a) {try{__CLR4_4_14y4ylpnjv51r.R.inc(312); /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(313);int line_num = 32;
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(314);frame.Highlight(line_num);__CLR4_4_14y4ylpnjv51r.R.inc(315);heap.addOutput(a[0]);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(316);frame.setText(1, "Delete from heap...");
	__CLR4_4_14y4ylpnjv51r.R.inc(317);a[0] = a[a.length-1];
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(318);frame.Highlight(line_num+1);
	__CLR4_4_14y4ylpnjv51r.R.inc(319);int[] result = setHeapWithSize(a, a.length-1);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(320);frame.Highlight(line_num+2);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(321);if ((((result.length>0)&&(__CLR4_4_14y4ylpnjv51r.R.iget(322)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(323)==0&false))) {__CLR4_4_14y4ylpnjv51r.R.inc(324);heap.moveLast2First();
	/*-*/}__CLR4_4_14y4ylpnjv51r.R.inc(325);heap.setHeap(result);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(326);frame.Highlight(line_num+3);
	__CLR4_4_14y4ylpnjv51r.R.inc(327);if ((((result.length > 0)&&(__CLR4_4_14y4ylpnjv51r.R.iget(328)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(329)==0&false))) {__CLR4_4_14y4ylpnjv51r.R.inc(330);heapify(result, 1);
	/*-*/}__CLR4_4_14y4ylpnjv51r.R.inc(331);frame.Highlight(line_num+3);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(332);frame.Highlight(line_num+4);
	__CLR4_4_14y4ylpnjv51r.R.inc(333);return result;
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public void heapify(int[] a, int i) {try{__CLR4_4_14y4ylpnjv51r.R.inc(334); /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(335);int line_num = 39;
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(336);frame.setText(1, "Heapifying ...");
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(337);frame.Highlight(line_num);__CLR4_4_14y4ylpnjv51r.R.inc(338);heap.highlight(i-1);__CLR4_4_14y4ylpnjv51r.R.inc(339);heap.redraw();
	__CLR4_4_14y4ylpnjv51r.R.inc(340);int l = left(i);/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(341);frame.Highlight(line_num+1);
	__CLR4_4_14y4ylpnjv51r.R.inc(342);int r = right(i);/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(343);frame.Highlight(line_num+2);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(344);frame.setText(1, "Left node = " + l + "; Right node = " + r);
	__CLR4_4_14y4ylpnjv51r.R.inc(345);int largest;
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(346);frame.Highlight(line_num+4);__CLR4_4_14y4ylpnjv51r.R.inc(347);heap.highlight(l-1);__CLR4_4_14y4ylpnjv51r.R.inc(348);heap.redraw();
	__CLR4_4_14y4ylpnjv51r.R.inc(349);if ( (((l <= heapSize(a) && a[l-1] > a[i-1] )&&(__CLR4_4_14y4ylpnjv51r.R.iget(350)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(351)==0&false))) {{
	    __CLR4_4_14y4ylpnjv51r.R.inc(352);largest = l;
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(353);frame.Highlight(line_num+5);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(354);frame.setText(2, "Left node is the largest...");
	} }else {{
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(355);frame.Highlight(line_num+6);
	    __CLR4_4_14y4ylpnjv51r.R.inc(356);largest = i;
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(357);frame.setText(2, "Current node is the largest...");
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(358);frame.Highlight(line_num+7);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(359);frame.Highlight(line_num+8);
	}
	/*-*/}__CLR4_4_14y4ylpnjv51r.R.inc(360);heap.restore(l-1);
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(361);frame.Highlight(line_num+9);__CLR4_4_14y4ylpnjv51r.R.inc(362);heap.highlight(r-1);__CLR4_4_14y4ylpnjv51r.R.inc(363);heap.redraw();
	__CLR4_4_14y4ylpnjv51r.R.inc(364);if ( (((r <= heapSize(a) && a[r-1] > a[largest-1] )&&(__CLR4_4_14y4ylpnjv51r.R.iget(365)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(366)==0&false))) {{
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(367);frame.Highlight(line_num+10);
	    __CLR4_4_14y4ylpnjv51r.R.inc(368);largest = r;
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(369);frame.setText(2, "Right node is the largest now...");
	}
	/*-*/}__CLR4_4_14y4ylpnjv51r.R.inc(370);heap.restore(r-1);__CLR4_4_14y4ylpnjv51r.R.inc(371);heap.redraw();
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(372);frame.Highlight(line_num+12);
	__CLR4_4_14y4ylpnjv51r.R.inc(373);if ((((largest != i)&&(__CLR4_4_14y4ylpnjv51r.R.iget(374)!=0|true))||(__CLR4_4_14y4ylpnjv51r.R.iget(375)==0&false))) {{
	    // exchange a[i], a[largest]
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(376);frame.setText(1, "Current node is not the largest...");
	/*-*/__CLR4_4_14y4ylpnjv51r.R.inc(377);frame.setText(2, "Exchanging current node with the largest...");
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(378);frame.Highlight(line_num + 14);
	    __CLR4_4_14y4ylpnjv51r.R.inc(379);int tmp = a[i-1]; __CLR4_4_14y4ylpnjv51r.R.inc(380);a[i-1] = a[largest-1]; __CLR4_4_14y4ylpnjv51r.R.inc(381);a[largest-1] = tmp;
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(382);frame.Highlight(line_num+15);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(383);heap.highlight(largest-1);__CLR4_4_14y4ylpnjv51r.R.inc(384);heap.redraw();
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(385);heap.exchangeArrow(i-1, largest-1);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(386);heap.setNode(i-1, a[i-1]);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(387);heap.setNode(largest-1, a[largest-1]);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(388);heap.restore(i-1); __CLR4_4_14y4ylpnjv51r.R.inc(389);heap.restore(largest-1); __CLR4_4_14y4ylpnjv51r.R.inc(390);heap.redraw();
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(391);frame.setText(2, "Heapifying the exchanged node...");
	    __CLR4_4_14y4ylpnjv51r.R.inc(392);heapify(a, largest);
	    /*-*/__CLR4_4_14y4ylpnjv51r.R.inc(393);frame.Highlight(line_num+15);
	}
	/*-*/}__CLR4_4_14y4ylpnjv51r.R.inc(394);heap.restore(i-1);__CLR4_4_14y4ylpnjv51r.R.inc(395);heap.redraw();
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public int heapSize(int[] a) {try{__CLR4_4_14y4ylpnjv51r.R.inc(396);
	__CLR4_4_14y4ylpnjv51r.R.inc(397);return a.length;
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public int parent(int i) {try{__CLR4_4_14y4ylpnjv51r.R.inc(398);
	__CLR4_4_14y4ylpnjv51r.R.inc(399);return (i/2);
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public int left(int i) {try{__CLR4_4_14y4ylpnjv51r.R.inc(400);
	__CLR4_4_14y4ylpnjv51r.R.inc(401);return (2*i);
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public int right(int i) {try{__CLR4_4_14y4ylpnjv51r.R.inc(402);
	__CLR4_4_14y4ylpnjv51r.R.inc(403);return (2*i + 1);
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}
//----
    public void run() {try{__CLR4_4_14y4ylpnjv51r.R.inc(404);
	__CLR4_4_14y4ylpnjv51r.R.inc(405);heap.initHeap();
	__CLR4_4_14y4ylpnjv51r.R.inc(406);animatePriorityQueue();

        // finish sorting
        __CLR4_4_14y4ylpnjv51r.R.inc(407);frame.finishAlg();
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}

    public void restoreDrawingPanelColor() {try{__CLR4_4_14y4ylpnjv51r.R.inc(408);
        //drawingPanel.restoreBinColor();
        __CLR4_4_14y4ylpnjv51r.R.inc(409);frame.repaint();
    }finally{__CLR4_4_14y4ylpnjv51r.R.flushNeeded();}}
}
