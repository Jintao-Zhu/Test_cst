/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
import java.awt.*;
import java.util.*;

class Heap extends Node {public static class __CLR4_4_1i4i4lpnjv538{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491091874L,8589935092L,1287,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    Font bigFont, smallFont, tinyFont, hugeFont, fixFont;
    DrawingPanel drawingPanel;
    Node tree, posn;
    Vector nodeList, posnList, inputList, outputList, heapArray;
    int max_node_per_level, depth;
    Node movingNode;
    boolean exchanging;
    Point[] aPoints;
    Color darkgreen = new Color(0, 140, 0);
    Color darkRed = new Color(140, 0, 0);
    Color darkBlue = new Color(0, 0, 140);
    ComBox runningCom;

    public Heap(DrawingPanel drawingPanel, int max_size) {try{__CLR4_4_1i4i4lpnjv538.R.inc(652);
	__CLR4_4_1i4i4lpnjv538.R.inc(653);this.bigFont = drawingPanel.bigFont;
	__CLR4_4_1i4i4lpnjv538.R.inc(654);this.smallFont = drawingPanel.smallFont;
	__CLR4_4_1i4i4lpnjv538.R.inc(655);this.tinyFont = drawingPanel.tinyFont;
	__CLR4_4_1i4i4lpnjv538.R.inc(656);this.hugeFont = drawingPanel.hugeFont;
	__CLR4_4_1i4i4lpnjv538.R.inc(657);this.fixFont = drawingPanel.fixFont;
	__CLR4_4_1i4i4lpnjv538.R.inc(658);this.drawingPanel = drawingPanel;
	__CLR4_4_1i4i4lpnjv538.R.inc(659);this.tree = null;
	__CLR4_4_1i4i4lpnjv538.R.inc(660);nodeList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(661);posnList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(662);inputList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(663);heapArray = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(664);outputList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(665);calNodesCoord(max_size);
	__CLR4_4_1i4i4lpnjv538.R.inc(666);hideMovingNode();
	__CLR4_4_1i4i4lpnjv538.R.inc(667);exchanging = false;
	__CLR4_4_1i4i4lpnjv538.R.inc(668);runningCom = null;
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void redraw() {try{__CLR4_4_1i4i4lpnjv538.R.inc(669);
	__CLR4_4_1i4i4lpnjv538.R.inc(670);drawingPanel.repaint();
	__CLR4_4_1i4i4lpnjv538.R.inc(671);drawingPanel.delay();
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void initHeap() {try{__CLR4_4_1i4i4lpnjv538.R.inc(672);
	__CLR4_4_1i4i4lpnjv538.R.inc(673);this.tree = null;
	__CLR4_4_1i4i4lpnjv538.R.inc(674);nodeList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(675);inputList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(676);heapArray = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(677);outputList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(678);exchanging = false;
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void setInput(int[] a) {try{__CLR4_4_1i4i4lpnjv538.R.inc(679);
	__CLR4_4_1i4i4lpnjv538.R.inc(680);inputList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(681);for (int i = 0; (((i < a.length)&&(__CLR4_4_1i4i4lpnjv538.R.iget(682)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(683)==0&false)); i++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(684);Node node = new Node(a[i]);
	    __CLR4_4_1i4i4lpnjv538.R.inc(685);node.x = drawingPanel.offset + 30 + (i+1)*22;
	    __CLR4_4_1i4i4lpnjv538.R.inc(686);node.y = bottomMostPosn(posn) + 85;
	    __CLR4_4_1i4i4lpnjv538.R.inc(687);inputList.addElement(node);
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(688);redraw();
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void addInput(int i) {try{__CLR4_4_1i4i4lpnjv538.R.inc(689);
	__CLR4_4_1i4i4lpnjv538.R.inc(690);Node node = new Node(i);
	__CLR4_4_1i4i4lpnjv538.R.inc(691);node.x = drawingPanel.panel_width - drawingPanel.offset - 50;
	__CLR4_4_1i4i4lpnjv538.R.inc(692);node.y = bottomMostPosn(posn) + 50;
	__CLR4_4_1i4i4lpnjv538.R.inc(693);inputList.addElement(node);
	__CLR4_4_1i4i4lpnjv538.R.inc(694);runningCom = new ComBox(node.x - 120, node.y + 50,
		"input arriving...", Color.blue, Color.yellow, fixFont );
	__CLR4_4_1i4i4lpnjv538.R.inc(695);redraw();
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void delFirstInput() {try{__CLR4_4_1i4i4lpnjv538.R.inc(696);
	__CLR4_4_1i4i4lpnjv538.R.inc(697);inputList.removeElementAt(0);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void addOutput(int i) {try{__CLR4_4_1i4i4lpnjv538.R.inc(698);
	__CLR4_4_1i4i4lpnjv538.R.inc(699);Node node = new Node(i);
	__CLR4_4_1i4i4lpnjv538.R.inc(700);node.y = drawingPanel.offset*2;
	__CLR4_4_1i4i4lpnjv538.R.inc(701);node.x = ((Node)posnList.firstElement()).x;

	__CLR4_4_1i4i4lpnjv538.R.inc(702);runningCom = new ComBox(150, node.y + 20,
		"Extracting output...", Color.blue, Color.yellow, fixFont );

	__CLR4_4_1i4i4lpnjv538.R.inc(703);movingNode = new Node(-1);
	__CLR4_4_1i4i4lpnjv538.R.inc(704);int srcX = movingNode.x = drawingPanel.offset + 20;
	__CLR4_4_1i4i4lpnjv538.R.inc(705);int srcY = movingNode.y = ((Node)posnList.firstElement()).y;
	__CLR4_4_1i4i4lpnjv538.R.inc(706);if ((((!heapArray.isEmpty())&&(__CLR4_4_1i4i4lpnjv538.R.iget(707)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(708)==0&false))) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(709);Node heapNode;
	    __CLR4_4_1i4i4lpnjv538.R.inc(710);if ((((!nodeList.isEmpty())&&(__CLR4_4_1i4i4lpnjv538.R.iget(711)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(712)==0&false)))
		{__CLR4_4_1i4i4lpnjv538.R.inc(713);heapNode = (Node)nodeList.firstElement();
	    }else {{
		__CLR4_4_1i4i4lpnjv538.R.inc(714);heapNode = (Node)posnList.firstElement();
		__CLR4_4_1i4i4lpnjv538.R.inc(715);movingNode.setWeight(i);
		__CLR4_4_1i4i4lpnjv538.R.inc(716);movingNode.x = heapNode.x;
		__CLR4_4_1i4i4lpnjv538.R.inc(717);movingNode.y = heapNode.y;
	    }

	    }__CLR4_4_1i4i4lpnjv538.R.inc(718);Node arrayNode = (Node)heapArray.firstElement();

	    __CLR4_4_1i4i4lpnjv538.R.inc(719);if ((((!nodeList.isEmpty())&&(__CLR4_4_1i4i4lpnjv538.R.iget(720)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(721)==0&false))) {{
	    	__CLR4_4_1i4i4lpnjv538.R.inc(722);heapNode.setRightNode(null); 
		__CLR4_4_1i4i4lpnjv538.R.inc(723);heapNode.setLeftNode(null);
	    }
	    }__CLR4_4_1i4i4lpnjv538.R.inc(724);int srcHx = heapNode.x;
	    __CLR4_4_1i4i4lpnjv538.R.inc(725);int srcAy = arrayNode.y;
	    __CLR4_4_1i4i4lpnjv538.R.inc(726);for (int k = 0; (((k < 5)&&(__CLR4_4_1i4i4lpnjv538.R.iget(727)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(728)==0&false)); k++) {{
		__CLR4_4_1i4i4lpnjv538.R.inc(729);if ((((!nodeList.isEmpty())&&(__CLR4_4_1i4i4lpnjv538.R.iget(730)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(731)==0&false)))
		    {__CLR4_4_1i4i4lpnjv538.R.inc(732);heapNode.x += (srcX - srcHx)/5;
		}else
		    {__CLR4_4_1i4i4lpnjv538.R.inc(733);movingNode.x += (srcX - srcHx)/5;
		}__CLR4_4_1i4i4lpnjv538.R.inc(734);arrayNode.y += (srcY - srcAy)/5;
		__CLR4_4_1i4i4lpnjv538.R.inc(735);redraw();
	    }
	}}
	
	}__CLR4_4_1i4i4lpnjv538.R.inc(736);if ((((!nodeList.isEmpty())&&(__CLR4_4_1i4i4lpnjv538.R.iget(737)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(738)==0&false)))
	    {__CLR4_4_1i4i4lpnjv538.R.inc(739);nodeList.removeElementAt(0);
	}__CLR4_4_1i4i4lpnjv538.R.inc(740);if ((((!heapArray.isEmpty())&&(__CLR4_4_1i4i4lpnjv538.R.iget(741)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(742)==0&false)))
	    {__CLR4_4_1i4i4lpnjv538.R.inc(743);heapArray.removeElementAt(0);
	}__CLR4_4_1i4i4lpnjv538.R.inc(744);movingNode.setWeight(i);
	__CLR4_4_1i4i4lpnjv538.R.inc(745);movingNode.x = srcX;
	__CLR4_4_1i4i4lpnjv538.R.inc(746);movingNode.y = srcY;
	__CLR4_4_1i4i4lpnjv538.R.inc(747);redraw();
	__CLR4_4_1i4i4lpnjv538.R.inc(748);int destX = node.x;
	__CLR4_4_1i4i4lpnjv538.R.inc(749);int destY = node.y;
	__CLR4_4_1i4i4lpnjv538.R.inc(750);for (int k = 0; (((k < 3)&&(__CLR4_4_1i4i4lpnjv538.R.iget(751)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(752)==0&false)); k++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(753);movingNode.x += (destX - srcX)/3;
	    __CLR4_4_1i4i4lpnjv538.R.inc(754);redraw();
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(755);movingNode.x = destX;
	__CLR4_4_1i4i4lpnjv538.R.inc(756);for (int k = 0; (((k < 5)&&(__CLR4_4_1i4i4lpnjv538.R.iget(757)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(758)==0&false)); k++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(759);movingNode.y += (destY - srcY)/5;
	    __CLR4_4_1i4i4lpnjv538.R.inc(760);redraw();
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(761);hideMovingNode();

	__CLR4_4_1i4i4lpnjv538.R.inc(762);runningCom = null;
	__CLR4_4_1i4i4lpnjv538.R.inc(763);redraw();
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void moveLast2First() {try{__CLR4_4_1i4i4lpnjv538.R.inc(764);
	__CLR4_4_1i4i4lpnjv538.R.inc(765);movingNode = (Node)nodeList.lastElement();
	__CLR4_4_1i4i4lpnjv538.R.inc(766);runningCom = new ComBox(movingNode.x + 30, movingNode.y,
		"Moving last node to root...", Color.blue, Color.yellow, 
		fixFont );
	__CLR4_4_1i4i4lpnjv538.R.inc(767);if ((((nodeList.size() > 2)&&(__CLR4_4_1i4i4lpnjv538.R.iget(768)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(769)==0&false))) {{
	    //remove this node from parent
	    __CLR4_4_1i4i4lpnjv538.R.inc(770);int p = parent(nodeList.size()+1);
	    __CLR4_4_1i4i4lpnjv538.R.inc(771);if (((((nodeList.size()+1) == right(p))&&(__CLR4_4_1i4i4lpnjv538.R.iget(772)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(773)==0&false)))
	        {__CLR4_4_1i4i4lpnjv538.R.inc(774);((Node)nodeList.elementAt(p-2)).setRightNode(null);
	    }else
	        {__CLR4_4_1i4i4lpnjv538.R.inc(775);((Node)nodeList.elementAt(p-2)).setLeftNode(null);
	}}

	//remove this node
	}__CLR4_4_1i4i4lpnjv538.R.inc(776);nodeList.removeElementAt(nodeList.size()-1);

	//lastHeapArray
	__CLR4_4_1i4i4lpnjv538.R.inc(777);Node lastHeapArray = (Node)heapArray.lastElement();
	

	// move node down;
	__CLR4_4_1i4i4lpnjv538.R.inc(778);if ((((!nodeList.isEmpty())&&(__CLR4_4_1i4i4lpnjv538.R.iget(779)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(780)==0&false))) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(781);movingNode.y += 17; __CLR4_4_1i4i4lpnjv538.R.inc(782);lastHeapArray.y += 17; __CLR4_4_1i4i4lpnjv538.R.inc(783);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(784);movingNode.y += 17; __CLR4_4_1i4i4lpnjv538.R.inc(785);lastHeapArray.y += 17; __CLR4_4_1i4i4lpnjv538.R.inc(786);redraw();
	}

	}__CLR4_4_1i4i4lpnjv538.R.inc(787);int destHeapArrayX = drawingPanel.offset + 20;
	__CLR4_4_1i4i4lpnjv538.R.inc(788);int srcHeapArrayX = lastHeapArray.x;
	__CLR4_4_1i4i4lpnjv538.R.inc(789);int destX = drawingPanel.offset + 25;
	__CLR4_4_1i4i4lpnjv538.R.inc(790);int srcX = movingNode.x;
	__CLR4_4_1i4i4lpnjv538.R.inc(791);for (int i = 0; (((i < 5)&&(__CLR4_4_1i4i4lpnjv538.R.iget(792)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(793)==0&false)); i++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(794);movingNode.x += (destX - srcX)/5;
	    __CLR4_4_1i4i4lpnjv538.R.inc(795);lastHeapArray.x += (destHeapArrayX - srcHeapArrayX)/15; 
	    __CLR4_4_1i4i4lpnjv538.R.inc(796);redraw();
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(797);movingNode.x = destX;
	__CLR4_4_1i4i4lpnjv538.R.inc(798);int destY = ((Node)posnList.firstElement()).y;
	__CLR4_4_1i4i4lpnjv538.R.inc(799);int srcY = movingNode.y;
	__CLR4_4_1i4i4lpnjv538.R.inc(800);for (int i = 0; (((i < 5)&&(__CLR4_4_1i4i4lpnjv538.R.iget(801)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(802)==0&false)); i++) {{
	__CLR4_4_1i4i4lpnjv538.R.inc(803);destY = ((Node)posnList.firstElement()).y;
	    __CLR4_4_1i4i4lpnjv538.R.inc(804);movingNode.y += (destY - srcY)/5;
	    __CLR4_4_1i4i4lpnjv538.R.inc(805);lastHeapArray.x += (destHeapArrayX - srcHeapArrayX)/15; 
	    __CLR4_4_1i4i4lpnjv538.R.inc(806);redraw();
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(807);movingNode.y = destY;
	__CLR4_4_1i4i4lpnjv538.R.inc(808);destX = ((Node)posnList.firstElement()).x;
	__CLR4_4_1i4i4lpnjv538.R.inc(809);srcX = movingNode.x;
	__CLR4_4_1i4i4lpnjv538.R.inc(810);for (int i = 0; (((i < 5)&&(__CLR4_4_1i4i4lpnjv538.R.iget(811)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(812)==0&false)); i++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(813);movingNode.x += (destX - srcX)/5;
	    __CLR4_4_1i4i4lpnjv538.R.inc(814);lastHeapArray.x += (destHeapArrayX - srcHeapArrayX)/15; 
	    __CLR4_4_1i4i4lpnjv538.R.inc(815);redraw();
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(816);movingNode.x = destX;
	__CLR4_4_1i4i4lpnjv538.R.inc(817);lastHeapArray.x = destHeapArrayX;
	__CLR4_4_1i4i4lpnjv538.R.inc(818);if ((((!nodeList.isEmpty())&&(__CLR4_4_1i4i4lpnjv538.R.iget(819)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(820)==0&false))) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(821);lastHeapArray.y -= 17; __CLR4_4_1i4i4lpnjv538.R.inc(822);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(823);lastHeapArray.y -= 17;
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(824);heapArray.removeElementAt(heapArray.size()-1);
	__CLR4_4_1i4i4lpnjv538.R.inc(825);heapArray.insertElementAt(lastHeapArray, 0);

	__CLR4_4_1i4i4lpnjv538.R.inc(826);nodeList.insertElementAt(movingNode, 0);
	__CLR4_4_1i4i4lpnjv538.R.inc(827);if ((((nodeList.size() > 1)&&(__CLR4_4_1i4i4lpnjv538.R.iget(828)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(829)==0&false)))
	    {__CLR4_4_1i4i4lpnjv538.R.inc(830);movingNode.setLeftNode((Node)nodeList.elementAt(1));
	}__CLR4_4_1i4i4lpnjv538.R.inc(831);if ((((nodeList.size() > 2)&&(__CLR4_4_1i4i4lpnjv538.R.iget(832)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(833)==0&false)))
	    {__CLR4_4_1i4i4lpnjv538.R.inc(834);movingNode.setRightNode((Node)nodeList.elementAt(2));
	
	}__CLR4_4_1i4i4lpnjv538.R.inc(835);hideMovingNode();
	__CLR4_4_1i4i4lpnjv538.R.inc(836);runningCom = null;
	__CLR4_4_1i4i4lpnjv538.R.inc(837);redraw();
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void restore(int i) {try{__CLR4_4_1i4i4lpnjv538.R.inc(838);
	__CLR4_4_1i4i4lpnjv538.R.inc(839);if ((((i < nodeList.size())&&(__CLR4_4_1i4i4lpnjv538.R.iget(840)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(841)==0&false))) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(842);((Node)nodeList.elementAt(i)).highlight = false;
	    __CLR4_4_1i4i4lpnjv538.R.inc(843);((Node)heapArray.elementAt(i)).highlight = false;
	}
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void highlight(int i) {try{__CLR4_4_1i4i4lpnjv538.R.inc(844);
	__CLR4_4_1i4i4lpnjv538.R.inc(845);if ((((i < nodeList.size())&&(__CLR4_4_1i4i4lpnjv538.R.iget(846)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(847)==0&false))) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(848);((Node)nodeList.elementAt(i)).highlight = true;
	    __CLR4_4_1i4i4lpnjv538.R.inc(849);((Node)heapArray.elementAt(i)).highlight = true;
	}
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    private void hideMovingNode() {try{__CLR4_4_1i4i4lpnjv538.R.inc(850);
	__CLR4_4_1i4i4lpnjv538.R.inc(851);movingNode = new Node(-1);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void exchangeArrow(int i, int j) {try{__CLR4_4_1i4i4lpnjv538.R.inc(852);
	// pre-condition: i+1 is parent, j+1 is child
	__CLR4_4_1i4i4lpnjv538.R.inc(853);Node child = (Node)nodeList.elementAt(j);
	__CLR4_4_1i4i4lpnjv538.R.inc(854);Node parent = (Node)nodeList.elementAt(i);

	__CLR4_4_1i4i4lpnjv538.R.inc(855);exchanging = true;

	__CLR4_4_1i4i4lpnjv538.R.inc(856);runningCom = new ComBox(child.x + 20, child.y - 10,
		"Swapping...", Color.blue, Color.yellow, fixFont);

	// check if is left or right child
	__CLR4_4_1i4i4lpnjv538.R.inc(857);if (((((j+1)==left(i+1))&&(__CLR4_4_1i4i4lpnjv538.R.iget(858)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(859)==0&false))) {{
	    // assign points: Point[] aPoints 
	    __CLR4_4_1i4i4lpnjv538.R.inc(860);aPoints = new Point[2];
	    __CLR4_4_1i4i4lpnjv538.R.inc(861);aPoints[0] = new Point(child.x + 2, child.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(862);aPoints[1] = new Point(child.x + 2, parent.y + 15);
	    __CLR4_4_1i4i4lpnjv538.R.inc(863);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(864);aPoints = new Point[3];
	    __CLR4_4_1i4i4lpnjv538.R.inc(865);aPoints[0] = new Point(child.x + 2, child.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(866);aPoints[1] = new Point(child.x + 2, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(867);aPoints[2] = new Point(parent.x - 8, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(868);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(869);aPoints = new Point[3];
	    __CLR4_4_1i4i4lpnjv538.R.inc(870);aPoints[0] = new Point(child.x + 2, child.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(871);aPoints[1] = new Point(child.x + 2, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(872);aPoints[2] = new Point(parent.x, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(873);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(874);aPoints = new Point[2];
	    __CLR4_4_1i4i4lpnjv538.R.inc(875);aPoints[0] = new Point(parent.x, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(876);aPoints[1] = new Point(child.x + 6, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(877);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(878);aPoints = new Point[3];
	    __CLR4_4_1i4i4lpnjv538.R.inc(879);aPoints[0] = new Point(parent.x, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(880);aPoints[1] = new Point(child.x + 2, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(881);aPoints[2] = new Point(child.x + 2, parent.y + 25);
	    __CLR4_4_1i4i4lpnjv538.R.inc(882);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(883);aPoints[0] = new Point(parent.x, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(884);aPoints[1] = new Point(child.x + 2, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(885);aPoints[2] = new Point(child.x + 2, child.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(886);redraw();
	} }else {{ // right child
	    __CLR4_4_1i4i4lpnjv538.R.inc(887);aPoints = new Point[2];
	    __CLR4_4_1i4i4lpnjv538.R.inc(888);aPoints[0] = new Point(child.x + 17, child.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(889);aPoints[1] = new Point(child.x + 17, parent.y + 15);
	    __CLR4_4_1i4i4lpnjv538.R.inc(890);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(891);aPoints = new Point[3];
	    __CLR4_4_1i4i4lpnjv538.R.inc(892);aPoints[0] = new Point(child.x + 17, child.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(893);aPoints[1] = new Point(child.x + 17, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(894);aPoints[2] = new Point(parent.x + 25, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(895);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(896);aPoints = new Point[3];
	    __CLR4_4_1i4i4lpnjv538.R.inc(897);aPoints[0] = new Point(child.x + 17, child.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(898);aPoints[1] = new Point(child.x + 17, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(899);aPoints[2] = new Point(parent.x + 20, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(900);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(901);aPoints = new Point[2];
	    __CLR4_4_1i4i4lpnjv538.R.inc(902);aPoints[0] = new Point(parent.x + 20, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(903);aPoints[1] = new Point(child.x + 12, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(904);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(905);aPoints = new Point[3];
	    __CLR4_4_1i4i4lpnjv538.R.inc(906);aPoints[0] = new Point(parent.x + 20, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(907);aPoints[1] = new Point(child.x + 17, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(908);aPoints[2] = new Point(child.x + 17, child.y - 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(909);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(910);aPoints[0] = new Point(parent.x + 20, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(911);aPoints[1] = new Point(child.x + 17, parent.y + 10);
	    __CLR4_4_1i4i4lpnjv538.R.inc(912);aPoints[2] = new Point(child.x + 17, child.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(913);redraw();
	}

	}__CLR4_4_1i4i4lpnjv538.R.inc(914);Node array1 = (Node)heapArray.elementAt(i);
	__CLR4_4_1i4i4lpnjv538.R.inc(915);Node array2 = (Node)heapArray.elementAt(j);
	__CLR4_4_1i4i4lpnjv538.R.inc(916);Point orig1 = new Point(array1.x, array1.y);
	__CLR4_4_1i4i4lpnjv538.R.inc(917);Point orig2 = new Point(array2.x, array2.y);
	__CLR4_4_1i4i4lpnjv538.R.inc(918);boolean adj = (Math.abs(i-j) == 1);
	__CLR4_4_1i4i4lpnjv538.R.inc(919);if ((((!adj)&&(__CLR4_4_1i4i4lpnjv538.R.iget(920)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(921)==0&false))) {__CLR4_4_1i4i4lpnjv538.R.inc(922);array1.y +=17; }__CLR4_4_1i4i4lpnjv538.R.inc(923);array2.y -= 17; __CLR4_4_1i4i4lpnjv538.R.inc(924);redraw();
	__CLR4_4_1i4i4lpnjv538.R.inc(925);if ((((!adj)&&(__CLR4_4_1i4i4lpnjv538.R.iget(926)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(927)==0&false))) {__CLR4_4_1i4i4lpnjv538.R.inc(928);array1.y +=17; }__CLR4_4_1i4i4lpnjv538.R.inc(929);array2.y -= 17; __CLR4_4_1i4i4lpnjv538.R.inc(930);redraw();

	__CLR4_4_1i4i4lpnjv538.R.inc(931);for (int k = 0; (((k < 4)&&(__CLR4_4_1i4i4lpnjv538.R.iget(932)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(933)==0&false)); k++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(934);array1.x += (orig2.x - orig1.x)/4;
	    __CLR4_4_1i4i4lpnjv538.R.inc(935);array2.x += (orig1.x - orig2.x)/4;
	    __CLR4_4_1i4i4lpnjv538.R.inc(936);redraw();
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(937);array1.x = orig2.x;
	__CLR4_4_1i4i4lpnjv538.R.inc(938);array2.x = orig1.x;
	__CLR4_4_1i4i4lpnjv538.R.inc(939);if ((((!adj)&&(__CLR4_4_1i4i4lpnjv538.R.iget(940)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(941)==0&false))) {__CLR4_4_1i4i4lpnjv538.R.inc(942);array1.y -=17; }__CLR4_4_1i4i4lpnjv538.R.inc(943);array2.y += 17; __CLR4_4_1i4i4lpnjv538.R.inc(944);redraw();

	__CLR4_4_1i4i4lpnjv538.R.inc(945);int tmpWeight = array1.getWeight();
	__CLR4_4_1i4i4lpnjv538.R.inc(946);array1.setWeight(array2.getWeight());
	__CLR4_4_1i4i4lpnjv538.R.inc(947);array2.setWeight(tmpWeight);
	__CLR4_4_1i4i4lpnjv538.R.inc(948);array1.x = orig1.x; __CLR4_4_1i4i4lpnjv538.R.inc(949);array1.y = orig1.y;
	__CLR4_4_1i4i4lpnjv538.R.inc(950);array2.x = orig2.x; __CLR4_4_1i4i4lpnjv538.R.inc(951);array2.y = orig2.y;
	__CLR4_4_1i4i4lpnjv538.R.inc(952);runningCom = null;
	__CLR4_4_1i4i4lpnjv538.R.inc(953);redraw();

	__CLR4_4_1i4i4lpnjv538.R.inc(954);exchanging = false;
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void input2heap() {try{__CLR4_4_1i4i4lpnjv538.R.inc(955);
	__CLR4_4_1i4i4lpnjv538.R.inc(956);int inputNum = inputList.size();
	__CLR4_4_1i4i4lpnjv538.R.inc(957);for (int i = 0; (((i < inputNum)&&(__CLR4_4_1i4i4lpnjv538.R.iget(958)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(959)==0&false)); i++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(960);Node node = (Node)inputList.firstElement();
	    __CLR4_4_1i4i4lpnjv538.R.inc(961);delFirstInput();
	    __CLR4_4_1i4i4lpnjv538.R.inc(962);movingNode = node;

	    __CLR4_4_1i4i4lpnjv538.R.inc(963);Node newNode = new Node(movingNode.getWeight());
	    __CLR4_4_1i4i4lpnjv538.R.inc(964);newNode.x = drawingPanel.offset + 20 + heapArray.size() * 22;
	    __CLR4_4_1i4i4lpnjv538.R.inc(965);movingNode.y = bottomMostPosn(posn) + 50;

	__CLR4_4_1i4i4lpnjv538.R.inc(966);runningCom = new ComBox(newNode.x, node.y + 50,
		"Inserting input into heap...", Color.blue, Color.yellow,
		fixFont);

	    __CLR4_4_1i4i4lpnjv538.R.inc(967);movingNode.x -= 15; __CLR4_4_1i4i4lpnjv538.R.inc(968);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(969);movingNode.x -= 15; __CLR4_4_1i4i4lpnjv538.R.inc(970);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(971);movingNode.x -= 15; __CLR4_4_1i4i4lpnjv538.R.inc(972);redraw();

	    __CLR4_4_1i4i4lpnjv538.R.inc(973);Node arrayNode = new Node(movingNode.getWeight());
	    __CLR4_4_1i4i4lpnjv538.R.inc(974);arrayNode.x = movingNode.x;
	    __CLR4_4_1i4i4lpnjv538.R.inc(975);arrayNode.y = movingNode.y;
	    __CLR4_4_1i4i4lpnjv538.R.inc(976);heapArray.addElement(arrayNode);

	    __CLR4_4_1i4i4lpnjv538.R.inc(977);Node destNode = (Node)posnList.elementAt(nodeList.size());
	    __CLR4_4_1i4i4lpnjv538.R.inc(978);int destX = destNode.x;
	    __CLR4_4_1i4i4lpnjv538.R.inc(979);int destY = destNode.y;
	    __CLR4_4_1i4i4lpnjv538.R.inc(980);int sourceX = movingNode.x;

	    __CLR4_4_1i4i4lpnjv538.R.inc(981);movingNode.y -= 20; __CLR4_4_1i4i4lpnjv538.R.inc(982);redraw();
	    __CLR4_4_1i4i4lpnjv538.R.inc(983);movingNode.y -= 20; __CLR4_4_1i4i4lpnjv538.R.inc(984);redraw();

	    __CLR4_4_1i4i4lpnjv538.R.inc(985);for (int l = 0; (((l < 5)&&(__CLR4_4_1i4i4lpnjv538.R.iget(986)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(987)==0&false)); l++) {{
		__CLR4_4_1i4i4lpnjv538.R.inc(988);movingNode.x += (destX - sourceX)/5;
		__CLR4_4_1i4i4lpnjv538.R.inc(989);arrayNode.x += (newNode.x - sourceX)/5;
	    	__CLR4_4_1i4i4lpnjv538.R.inc(990);redraw();
	    }
	    }__CLR4_4_1i4i4lpnjv538.R.inc(991);movingNode.x = destX;
	    __CLR4_4_1i4i4lpnjv538.R.inc(992);arrayNode.x = newNode.x;

	    __CLR4_4_1i4i4lpnjv538.R.inc(993);redraw();

	    __CLR4_4_1i4i4lpnjv538.R.inc(994);int srcY = node.y;
	    __CLR4_4_1i4i4lpnjv538.R.inc(995);for (int k = 0; (((k < 5)&&(__CLR4_4_1i4i4lpnjv538.R.iget(996)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(997)==0&false)); k++) {{
		__CLR4_4_1i4i4lpnjv538.R.inc(998);movingNode.y += (destY - srcY)/5;
		__CLR4_4_1i4i4lpnjv538.R.inc(999);redraw();
	    }
	    }__CLR4_4_1i4i4lpnjv538.R.inc(1000);hideMovingNode();
	    __CLR4_4_1i4i4lpnjv538.R.inc(1001);node.x = destX; __CLR4_4_1i4i4lpnjv538.R.inc(1002);node.y = destY;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1003);insert(node);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1004);runningCom = null;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1005);redraw();
	}
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void setHeap(int[] a) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1006);
	__CLR4_4_1i4i4lpnjv538.R.inc(1007);if ((((a.length > posnList.size())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1008)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1009)==0&false)))
	    {__CLR4_4_1i4i4lpnjv538.R.inc(1010);calNodesCoord(a.length);
	}__CLR4_4_1i4i4lpnjv538.R.inc(1011);tree = null;
	__CLR4_4_1i4i4lpnjv538.R.inc(1012);nodeList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(1013);for (int i = 0; (((i < a.length)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1014)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1015)==0&false)); i++)
	    {__CLR4_4_1i4i4lpnjv538.R.inc(1016);insert(new Node(a[i]));
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}} // setData()

    private void insert(Node node) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1017);
	__CLR4_4_1i4i4lpnjv538.R.inc(1018);nodeList.addElement(node);
	__CLR4_4_1i4i4lpnjv538.R.inc(1019);if ((((posnList.size() >= nodeList.size())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1020)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1021)==0&false))) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(1022);node.x = ((Node)posnList.elementAt(nodeList.size()-1)).x;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1023);node.y = ((Node)posnList.elementAt(nodeList.size()-1)).y;
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(1024);if ((((tree == null)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1025)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1026)==0&false))) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(1027);tree = node;
	} }else {{
	    // locate blank branch to link to this node
	    __CLR4_4_1i4i4lpnjv538.R.inc(1028);for (int i = 0; (((i < nodeList.size()-1)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1029)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1030)==0&false)); i++) {{
		__CLR4_4_1i4i4lpnjv538.R.inc(1031);Node n = (Node)nodeList.elementAt(i);
		__CLR4_4_1i4i4lpnjv538.R.inc(1032);if ((((n.getLeftNode() == null)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1033)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1034)==0&false))) {{
		    __CLR4_4_1i4i4lpnjv538.R.inc(1035);n.setLeftNode(node);
		    __CLR4_4_1i4i4lpnjv538.R.inc(1036);break;
		} }else {__CLR4_4_1i4i4lpnjv538.R.inc(1037);if ((((n.getRightNode() == null)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1038)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1039)==0&false))) {{
		    __CLR4_4_1i4i4lpnjv538.R.inc(1040);n.setRightNode(node);
		    __CLR4_4_1i4i4lpnjv538.R.inc(1041);break;
	   	}
	    }}}
	}}
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public int heapSize() {try{__CLR4_4_1i4i4lpnjv538.R.inc(1042);
	__CLR4_4_1i4i4lpnjv538.R.inc(1043);return nodeList.size();
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void setNode(int i, int val) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1044);
	__CLR4_4_1i4i4lpnjv538.R.inc(1045);((Node)nodeList.elementAt(i)).setWeight(val);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    private void calNodesCoord(int size) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1046);
	// count max number of nodes needed
	__CLR4_4_1i4i4lpnjv538.R.inc(1047);int i =  0; __CLR4_4_1i4i4lpnjv538.R.inc(1048);int sum = power(2, i++);
	__CLR4_4_1i4i4lpnjv538.R.inc(1049);while ((((sum < size)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1050)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1051)==0&false)))
	    {__CLR4_4_1i4i4lpnjv538.R.inc(1052);sum += power(2, i++);
	}__CLR4_4_1i4i4lpnjv538.R.inc(1053);max_node_per_level = power(2, i-1);
	__CLR4_4_1i4i4lpnjv538.R.inc(1054);depth = i-1;

	__CLR4_4_1i4i4lpnjv538.R.inc(1055);int max_nodes = sum;
	// insert nodes with no value into position tree
	__CLR4_4_1i4i4lpnjv538.R.inc(1056);Node backTree = tree; // backup current tree
	__CLR4_4_1i4i4lpnjv538.R.inc(1057);Vector backNodeList = nodeList; __CLR4_4_1i4i4lpnjv538.R.inc(1058);nodeList = new Vector(); 
	__CLR4_4_1i4i4lpnjv538.R.inc(1059);posnList = new Vector();
	__CLR4_4_1i4i4lpnjv538.R.inc(1060);for (int j = 0; (((j < max_nodes)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1061)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1062)==0&false)); j++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(1063);Node node = new Node(-1);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1064);insert(node);
	}
	}__CLR4_4_1i4i4lpnjv538.R.inc(1065);posn = tree;
	__CLR4_4_1i4i4lpnjv538.R.inc(1066);posnList = nodeList;
	__CLR4_4_1i4i4lpnjv538.R.inc(1067);tree = backTree;
	__CLR4_4_1i4i4lpnjv538.R.inc(1068);nodeList = backNodeList;
	

	// assign Y
	__CLR4_4_1i4i4lpnjv538.R.inc(1069);int curDepth = 0; __CLR4_4_1i4i4lpnjv538.R.inc(1070);sum = power(2, 0);
	__CLR4_4_1i4i4lpnjv538.R.inc(1071);int offset = drawingPanel.offset + 30;
	//int yStart = drawingPanel.panel_height/2 -100 - depth*20;
	__CLR4_4_1i4i4lpnjv538.R.inc(1072);int yStart = 100;
	__CLR4_4_1i4i4lpnjv538.R.inc(1073);for (int j = 0; (((j < posnList.size())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1074)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1075)==0&false)); j++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(1076);Node node = (Node)posnList.elementAt(j);

	    __CLR4_4_1i4i4lpnjv538.R.inc(1077);if ( ((((j+1) > sum)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1078)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1079)==0&false))) {{
		__CLR4_4_1i4i4lpnjv538.R.inc(1080);curDepth++; __CLR4_4_1i4i4lpnjv538.R.inc(1081);sum += power(2, curDepth);
	    }
	    }__CLR4_4_1i4i4lpnjv538.R.inc(1082);node.depth = curDepth;

	    __CLR4_4_1i4i4lpnjv538.R.inc(1083);node.y = yStart + curDepth*40;

	    // assign X for bottom level
	    __CLR4_4_1i4i4lpnjv538.R.inc(1084);if ((((curDepth == depth)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1085)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1086)==0&false))) {{
		__CLR4_4_1i4i4lpnjv538.R.inc(1087);node.x = 22 + offset;
		__CLR4_4_1i4i4lpnjv538.R.inc(1088);offset = node.x;
	    }
	}}

	// assign remaining X
	}__CLR4_4_1i4i4lpnjv538.R.inc(1089);for (int j = posnList.size() - 1; (((j >= 0)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1090)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1091)==0&false)); j--) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(1092);Node node = (Node)posnList.elementAt(j);

	    __CLR4_4_1i4i4lpnjv538.R.inc(1093);if ((((node.depth == depth)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1094)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1095)==0&false)))
		{__CLR4_4_1i4i4lpnjv538.R.inc(1096);continue;

	    }__CLR4_4_1i4i4lpnjv538.R.inc(1097);if ((((node.getLeftNode() != null && node.depth == depth-1)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1098)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1099)==0&false))) {{
		__CLR4_4_1i4i4lpnjv538.R.inc(1100);node.x = node.getLeftNode().x + 12;
	    } }else {__CLR4_4_1i4i4lpnjv538.R.inc(1101);if ((((node.getLeftNode() != null && 
		node.getRightNode() != null)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1102)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1103)==0&false))) {{
		__CLR4_4_1i4i4lpnjv538.R.inc(1104);node.x = (node.getLeftNode().x + node.getRightNode().x)/2;
	    } }else {{
		// both children null -> can only at depth - 1
		__CLR4_4_1i4i4lpnjv538.R.inc(1105);int l = left(j+1);
	    }
	}}}
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    /* ------------------------ Utils -------------------- */

    public int power(int num, int pow) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1106);
	__CLR4_4_1i4i4lpnjv538.R.inc(1107);int result = 1;
	__CLR4_4_1i4i4lpnjv538.R.inc(1108);for (int i = 0; (((i < pow)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1109)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1110)==0&false)); i++)
	    {__CLR4_4_1i4i4lpnjv538.R.inc(1111);result *= num;
	}__CLR4_4_1i4i4lpnjv538.R.inc(1112);return result;
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public int parent(int i) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1113);
	__CLR4_4_1i4i4lpnjv538.R.inc(1114);return (i/2);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public int left(int i) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1115);
        __CLR4_4_1i4i4lpnjv538.R.inc(1116);return (2*i);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public int right(int i) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1117);
        __CLR4_4_1i4i4lpnjv538.R.inc(1118);return (2*i + 1);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}
 
    /* ------------------------ Graphical Primitives ------------------ */

    public void drawTree(Graphics g) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1119);
	__CLR4_4_1i4i4lpnjv538.R.inc(1120);for (int i = 0; (((i < inputList.size())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1121)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1122)==0&false)); i++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(1123);drawLeafNode(g, (Node)inputList.elementAt(i));
	}

	}__CLR4_4_1i4i4lpnjv538.R.inc(1124);for (int i = 0; (((i < heapSize())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1125)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1126)==0&false)); i++) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(1127);Node node = (Node)nodeList.elementAt(i);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1128);drawNode(g, node);
	}

	}__CLR4_4_1i4i4lpnjv538.R.inc(1129);for (int i = 0; (((i < outputList.size())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1130)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1131)==0&false)); i++)
	    {__CLR4_4_1i4i4lpnjv538.R.inc(1132);drawLeafNode(g, (Node)outputList.elementAt(i));

	}__CLR4_4_1i4i4lpnjv538.R.inc(1133);if ((((movingNode.getWeight() > -1)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1134)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1135)==0&false)))
	    {__CLR4_4_1i4i4lpnjv538.R.inc(1136);drawNode(g, movingNode);

        }__CLR4_4_1i4i4lpnjv538.R.inc(1137);for (int i = 0; (((i < heapArray.size())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1138)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1139)==0&false)); i++) {{
            __CLR4_4_1i4i4lpnjv538.R.inc(1140);Node node = (Node)heapArray.elementAt(i);
            __CLR4_4_1i4i4lpnjv538.R.inc(1141);drawArrayNode(g, node.x, node.y, node);
        }

	}__CLR4_4_1i4i4lpnjv538.R.inc(1142);if ((((exchanging)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1143)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1144)==0&false)))
	    {__CLR4_4_1i4i4lpnjv538.R.inc(1145);drawArrow(g, aPoints);

	// draw tree box
	}__CLR4_4_1i4i4lpnjv538.R.inc(1146);new ComBox( ((Node)posnList.firstElement()).x + 60,
		((Node)posnList.firstElement()).y - 30, 
		"tree representation of the same heap",
		Color.white, darkRed, fixFont).draw(g);

	// draw array box
	__CLR4_4_1i4i4lpnjv538.R.inc(1147);new ComBox( drawingPanel.offset + 20,
		bottomMostPosn(posn) + 120,
		"array representation of the heap",
		Color.white, darkRed, fixFont).draw(g);

	// draw input box
	__CLR4_4_1i4i4lpnjv538.R.inc(1148);new ComBox( drawingPanel.panel_width - 50, 
			bottomMostPosn(posn) + 50, "Input",
		Color.white, darkBlue, fixFont ).draw(g);

	// draw output box
	__CLR4_4_1i4i4lpnjv538.R.inc(1149);new ComBox( ((Node)posnList.firstElement()).x - 15,
		10, "Output",
		Color.white, darkBlue, fixFont).draw(g);

	__CLR4_4_1i4i4lpnjv538.R.inc(1150);if ((((runningCom != null)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1151)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1152)==0&false)))
	    {__CLR4_4_1i4i4lpnjv538.R.inc(1153);runningCom.draw(g);

	}__CLR4_4_1i4i4lpnjv538.R.inc(1154);new ComBox(drawingPanel.offset + 20, drawingPanel.panel_height - 50,
"this demo will run non-stop until the 'stop' or 'quit' button is pressed", 
		Color.blue, Color.pink, fixFont).draw(g);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void drawArrow(Graphics g, Point[] points) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1155);
	// remember: no drawPolyline in jdk1.0.*
	__CLR4_4_1i4i4lpnjv538.R.inc(1156);int[] axPoints = new int[3], ayPoints = new int[3];
	__CLR4_4_1i4i4lpnjv538.R.inc(1157);g.setColor( Color.magenta );
	__CLR4_4_1i4i4lpnjv538.R.inc(1158);if ((((points.length < 3)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1159)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1160)==0&false))) {{
	  __CLR4_4_1i4i4lpnjv538.R.inc(1161);Point endArrow = points[1], startArrow = points[0];
	  __CLR4_4_1i4i4lpnjv538.R.inc(1162);if ((((endArrow.y == startArrow.y)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1163)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1164)==0&false))) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(1165);g.drawLine(startArrow.x, startArrow.y, endArrow.x, endArrow.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1166);axPoints[0] = endArrow.x; __CLR4_4_1i4i4lpnjv538.R.inc(1167);ayPoints[0] = endArrow.y;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1168);int unit = (endArrow.x - startArrow.x)/
			Math.abs(endArrow.x - startArrow.x);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1169);axPoints[1] = endArrow.x - unit*2;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1170);ayPoints[1] = endArrow.y - 2;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1171);axPoints[2] = endArrow.x - unit*2;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1172);ayPoints[2] = endArrow.y + 2;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1173);g.fillPolygon(axPoints, ayPoints, 3);
	  } }else {__CLR4_4_1i4i4lpnjv538.R.inc(1174);if ((((endArrow.x == startArrow.x)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1175)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1176)==0&false))) {{
	    __CLR4_4_1i4i4lpnjv538.R.inc(1177);g.drawLine(startArrow.x, startArrow.y, endArrow.x, endArrow.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1178);axPoints[0] = endArrow.x; __CLR4_4_1i4i4lpnjv538.R.inc(1179);ayPoints[0] = endArrow.y;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1180);int unit = (endArrow.y - startArrow.y)/
			Math.abs(endArrow.y - startArrow.y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1181);axPoints[1] = endArrow.x - 1;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1182);ayPoints[1] = endArrow.y - unit*2;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1183);axPoints[2] = endArrow.x + 2;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1184);ayPoints[2] = endArrow.y - unit*2;
	    __CLR4_4_1i4i4lpnjv538.R.inc(1185);g.fillPolygon(axPoints, ayPoints, 3);
	  }
	}}} }else {__CLR4_4_1i4i4lpnjv538.R.inc(1186);if ((((points.length == 3)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1187)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1188)==0&false))) {{
	    // draw a line for the first and second points then use
	    // drawArrow to draw the remaining segment
	    __CLR4_4_1i4i4lpnjv538.R.inc(1189);g.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1190);Point[] line = new Point[2];
	    __CLR4_4_1i4i4lpnjv538.R.inc(1191);line[0] = points[1];
	    __CLR4_4_1i4i4lpnjv538.R.inc(1192);line[1] = points[2];
	    __CLR4_4_1i4i4lpnjv538.R.inc(1193);drawArrow(g, line);
	}
    }}}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public int treeWidth(Node node) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1194);
        __CLR4_4_1i4i4lpnjv538.R.inc(1195);return (rightMostPosn(node) - leftMostPosn(node));
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}
 
    public int leftMostPosn(Node node) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1196);
        __CLR4_4_1i4i4lpnjv538.R.inc(1197);if ((((node.isLeaf())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1198)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1199)==0&false)))
            {__CLR4_4_1i4i4lpnjv538.R.inc(1200);return node.x;
        }else
            {__CLR4_4_1i4i4lpnjv538.R.inc(1201);return leftMostPosn(node.getLeftNode());
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}
 
    public int rightMostPosn(Node node) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1202);
        __CLR4_4_1i4i4lpnjv538.R.inc(1203);if ((((node.isLeaf())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1204)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1205)==0&false)))
            {__CLR4_4_1i4i4lpnjv538.R.inc(1206);return (node.x + 20);
        }else
            {__CLR4_4_1i4i4lpnjv538.R.inc(1207);return rightMostPosn(node.getRightNode());
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}
 
    public int treeHeight(Node node) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1208);
        __CLR4_4_1i4i4lpnjv538.R.inc(1209);return (bottomMostPosn(node) - node.y);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}
 
    public int bottomMostPosn(Node node) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1210);
        __CLR4_4_1i4i4lpnjv538.R.inc(1211);if ((((node.isLeaf())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1212)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1213)==0&false)))
            {__CLR4_4_1i4i4lpnjv538.R.inc(1214);return (node.y + 30);
        }else {{
            __CLR4_4_1i4i4lpnjv538.R.inc(1215);int rightBottom = bottomMostPosn(node.getRightNode());
            __CLR4_4_1i4i4lpnjv538.R.inc(1216);int leftBottom = bottomMostPosn(node.getLeftNode());
            __CLR4_4_1i4i4lpnjv538.R.inc(1217);return ((((rightBottom > leftBottom )&&(__CLR4_4_1i4i4lpnjv538.R.iget(1218)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1219)==0&false))? rightBottom : leftBottom);
        }
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void drawLeafNode(Graphics g, Node node) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1220);
        __CLR4_4_1i4i4lpnjv538.R.inc(1221);int x = node.x; __CLR4_4_1i4i4lpnjv538.R.inc(1222);int y = node.y;
        __CLR4_4_1i4i4lpnjv538.R.inc(1223);int weight = node.getWeight();
        __CLR4_4_1i4i4lpnjv538.R.inc(1224);if ((((node.highlight)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1225)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1226)==0&false)))
            {__CLR4_4_1i4i4lpnjv538.R.inc(1227);g.setColor(Color.black);
        }else
            {__CLR4_4_1i4i4lpnjv538.R.inc(1228);g.setColor( Color.blue );
        }__CLR4_4_1i4i4lpnjv538.R.inc(1229);g.fillRect(x, y, 20, 30);
	__CLR4_4_1i4i4lpnjv538.R.inc(1230);g.setColor(Color.black);
	__CLR4_4_1i4i4lpnjv538.R.inc(1231);g.drawRect(x, y, 20, 30);
        __CLR4_4_1i4i4lpnjv538.R.inc(1232);g.setFont(hugeFont);
        __CLR4_4_1i4i4lpnjv538.R.inc(1233);g.setColor( Color.yellow );
        __CLR4_4_1i4i4lpnjv538.R.inc(1234);g.drawString(""+weight, x+2, y+25);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void drawArrayNode(Graphics g, int x, int y, Node node) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1235);
        __CLR4_4_1i4i4lpnjv538.R.inc(1236);int weight = node.getWeight();
        __CLR4_4_1i4i4lpnjv538.R.inc(1237);if ((((node.highlight)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1238)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1239)==0&false)))
            {__CLR4_4_1i4i4lpnjv538.R.inc(1240);g.setColor(Color.black);
        }else
            {__CLR4_4_1i4i4lpnjv538.R.inc(1241);g.setColor( Color.red );
        }__CLR4_4_1i4i4lpnjv538.R.inc(1242);g.fillRect(x, y, 20, 30);
	__CLR4_4_1i4i4lpnjv538.R.inc(1243);g.setColor(Color.black);
	__CLR4_4_1i4i4lpnjv538.R.inc(1244);g.drawRect(x, y, 20, 30);
        __CLR4_4_1i4i4lpnjv538.R.inc(1245);g.setFont(hugeFont);
        __CLR4_4_1i4i4lpnjv538.R.inc(1246);g.setColor( Color.yellow );
        __CLR4_4_1i4i4lpnjv538.R.inc(1247);g.drawString(""+weight, x+2, y+25);
    }finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}
 
    public void drawNode(Graphics g, Node node) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1248);
        __CLR4_4_1i4i4lpnjv538.R.inc(1249);if ((((node.highlight)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1250)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1251)==0&false)))
            {__CLR4_4_1i4i4lpnjv538.R.inc(1252);g.setColor(Color.black);
        }else
            {__CLR4_4_1i4i4lpnjv538.R.inc(1253);g.setColor( darkgreen );
        }__CLR4_4_1i4i4lpnjv538.R.inc(1254);g.fillRect(node.x, node.y, 20, 30);
	__CLR4_4_1i4i4lpnjv538.R.inc(1255);g.setColor(Color.black);
	__CLR4_4_1i4i4lpnjv538.R.inc(1256);g.drawRect(node.x, node.y, 20, 30);
        __CLR4_4_1i4i4lpnjv538.R.inc(1257);g.setColor( Color.white );
	__CLR4_4_1i4i4lpnjv538.R.inc(1258);g.setFont( hugeFont );
        __CLR4_4_1i4i4lpnjv538.R.inc(1259);g.drawString(""+node.getWeight(), node.x + 2, node.y+25);
 
        // draw links to children
	__CLR4_4_1i4i4lpnjv538.R.inc(1260);if ((((node.getLeftNode() != null)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1261)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1262)==0&false))) {{
            __CLR4_4_1i4i4lpnjv538.R.inc(1263);g.setColor( Color.black );
            __CLR4_4_1i4i4lpnjv538.R.inc(1264);g.drawLine(node.x + 6, node.y + 30,
                node.getLeftNode().x + 10, node.getLeftNode().y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1265);if ((((node.highlightLeft)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1266)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1267)==0&false))) {{
                __CLR4_4_1i4i4lpnjv538.R.inc(1268);g.drawLine(node.x + 5, node.y + 30,
                    node.getLeftNode().x + 9, node.getLeftNode().y);
                __CLR4_4_1i4i4lpnjv538.R.inc(1269);g.drawLine(node.x + 4, node.y + 30,
                    node.getLeftNode().x + 8, node.getLeftNode().y);
	    }
	}}

	}__CLR4_4_1i4i4lpnjv538.R.inc(1270);if ((((node.getRightNode() != null)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1271)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1272)==0&false))) {{
            __CLR4_4_1i4i4lpnjv538.R.inc(1273);g.drawLine(node.x + 14, node.y + 30,
                node.getRightNode().x + 10, node.getRightNode().y);
	    __CLR4_4_1i4i4lpnjv538.R.inc(1274);if ((((node.highlightRight)&&(__CLR4_4_1i4i4lpnjv538.R.iget(1275)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1276)==0&false))) {{
                __CLR4_4_1i4i4lpnjv538.R.inc(1277);g.drawLine(node.x + 15, node.y + 30,
                    node.getRightNode().x + 11, node.getRightNode().y);
                __CLR4_4_1i4i4lpnjv538.R.inc(1278);g.drawLine(node.x + 16, node.y + 30,
                    node.getRightNode().x + 12, node.getRightNode().y);
	    }
	}}
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}

    public void moveNode(Node node, int dx, int dy) {try{__CLR4_4_1i4i4lpnjv538.R.inc(1279);
        __CLR4_4_1i4i4lpnjv538.R.inc(1280);node.x += dx;
        __CLR4_4_1i4i4lpnjv538.R.inc(1281);node.y += dy;
        __CLR4_4_1i4i4lpnjv538.R.inc(1282);if ((((!node.isLeaf())&&(__CLR4_4_1i4i4lpnjv538.R.iget(1283)!=0|true))||(__CLR4_4_1i4i4lpnjv538.R.iget(1284)==0&false))) {{
            __CLR4_4_1i4i4lpnjv538.R.inc(1285);moveNode(node.getLeftNode(), dx, dy);
            __CLR4_4_1i4i4lpnjv538.R.inc(1286);moveNode(node.getRightNode(), dx, dy);
        }
    }}finally{__CLR4_4_1i4i4lpnjv538.R.flushNeeded();}}
}
