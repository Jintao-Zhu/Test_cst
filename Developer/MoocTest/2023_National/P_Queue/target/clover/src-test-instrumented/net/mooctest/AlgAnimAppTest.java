/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Vector;

public class AlgAnimAppTest {static class __CLR4_4_115i15ilpnjv557{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0050\u005f\u0051\u0075\u0065\u0075\u0065\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491092161L,8589935092L,1632,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
    AlgAnimApp dad = new AlgAnimApp();

    AlgAnimApp aaa1 = new AlgAnimApp();

    String filePath = "src/test/resources/1";
    // \u704f\u55d8\u6783\u6d60\u60f0\u77fe\u5bf0\u52ee\u6d46\u93b9\ue76d\u8d1fURL\u93cd\u714e\u7d21
    String fileURL = "file:///" + filePath.replace("\\", "/");

    URL url1;
    {try{__CLR4_4_115i15ilpnjv557.R.inc(1494);
        __CLR4_4_115i15ilpnjv557.R.inc(1495);try {
            __CLR4_4_115i15ilpnjv557.R.inc(1496);url1 = new URL(fileURL);
        } catch (MalformedURLException e) {
            __CLR4_4_115i15ilpnjv557.R.inc(1497);throw new RuntimeException(e);
        }
    }finally{__CLR4_4_115i15ilpnjv557.R.flushNeeded();}}

    String filePath1 = "src/test/resources/1";
    // \u704f\u55d8\u6783\u6d60\u60f0\u77fe\u5bf0\u52ee\u6d46\u93b9\ue76d\u8d1fURL\u93cd\u714e\u7d21
    String fileURL1 = "file:///" + filePath1.replace("\\", "/");
    //    https://github.com/bitcoinj/bitcoinj/blob/master/designdocs/Deterministic%20wallets.txt
    URL url2;
    {try{__CLR4_4_115i15ilpnjv557.R.inc(1498);
        __CLR4_4_115i15ilpnjv557.R.inc(1499);try {
            __CLR4_4_115i15ilpnjv557.R.inc(1500);url2 = new URL(fileURL1);
        } catch (MalformedURLException e) {
            __CLR4_4_115i15ilpnjv557.R.inc(1501);throw new RuntimeException(e);
        }
    }finally{__CLR4_4_115i15ilpnjv557.R.flushNeeded();}}


    URL url3;

    {try{__CLR4_4_115i15ilpnjv557.R.inc(1502);
        __CLR4_4_115i15ilpnjv557.R.inc(1503);try {
            __CLR4_4_115i15ilpnjv557.R.inc(1504);url3 = new URL("http://example.com/somefile.txt");
        } catch (MalformedURLException e) {
            __CLR4_4_115i15ilpnjv557.R.inc(1505);throw new RuntimeException(e);
        }
    }finally{__CLR4_4_115i15ilpnjv557.R.flushNeeded();}}



    @Test
    public void test1() throws MalformedURLException {__CLR4_4_115i15ilpnjv557.R.globalSliceStart(getClass().getName(),1506);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_14e6beu15u();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_115i15ilpnjv557.R.rethrow($CLV_t2$);}finally{__CLR4_4_115i15ilpnjv557.R.globalSliceEnd(getClass().getName(),"net.mooctest.AlgAnimAppTest.test1",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),1506,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_14e6beu15u() throws MalformedURLException{try{__CLR4_4_115i15ilpnjv557.R.inc(1506);
//        System.out.println(url1);
//
//
//        dad.fn_label = filePath;
//        dad.button_label = "name";
//        dad.init();
//
//
//
//        AlgAnimFrame aan1 = new AlgAnimFrame(dad, url3);
//        AlgAnimFrame aan2 = new AlgAnimFrame(null, url2);

    }finally{__CLR4_4_115i15ilpnjv557.R.flushNeeded();}}

    @Test
    public void test() throws MalformedURLException {__CLR4_4_115i15ilpnjv557.R.globalSliceStart(getClass().getName(),1507);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1zi4lt315v();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_115i15ilpnjv557.R.rethrow($CLV_t2$);}finally{__CLR4_4_115i15ilpnjv557.R.globalSliceEnd(getClass().getName(),"net.mooctest.AlgAnimAppTest.test",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),1507,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1zi4lt315v() throws MalformedURLException{try{__CLR4_4_115i15ilpnjv557.R.inc(1507);
        __CLR4_4_115i15ilpnjv557.R.inc(1508);System.setProperty("java.awt.headless", "true");


        __CLR4_4_115i15ilpnjv557.R.inc(1509);try {

            __CLR4_4_115i15ilpnjv557.R.inc(1510);AlgAnimApp app = new AlgAnimApp() {
                public String getParameter(String name) {try{__CLR4_4_115i15ilpnjv557.R.inc(1511);
//                    if ("filename".equals(name)) {
//                        return "src/test/resources/2.txt"; // \u6769\u6b13\u5677\u6434\u65c7\ue1da\u93c4\ue21b\u504d\u93af\u5ba0\ue6e6\u9354\u72ba\u6d47\u9428\u52ec\u6783\u6d60\u8dfa\u6095
//                    } else if ("buttonname".equals(name)) {
//                        return ""; // \u93b8\u5910\u6333\u6d93\u5a44\u6b91\u93c2\u56e7\u74e7
//                    }
                    __CLR4_4_115i15ilpnjv557.R.inc(1512);return null;
                }finally{__CLR4_4_115i15ilpnjv557.R.flushNeeded();}}

                public URL getCodeBase() {try{__CLR4_4_115i15ilpnjv557.R.inc(1513);
                    __CLR4_4_115i15ilpnjv557.R.inc(1514);try {
                        __CLR4_4_115i15ilpnjv557.R.inc(1515);return new URL("http://example.com/"); // \u934b\u56ea\ue195\u9428\u52eb\u7180\u7ead\ufffd URL
                    } catch (MalformedURLException e) {
                        __CLR4_4_115i15ilpnjv557.R.inc(1516);throw new RuntimeException(e);
                    }
                }finally{__CLR4_4_115i15ilpnjv557.R.flushNeeded();}}
            };

            // \u9352\u6fc6\ue750\u9356\ufffd Applet
//            app.init();

            // \u59af\u2103\u5ad9\u9410\u7470\u56ae\u93b8\u5910\u6333\u9428\u52eb\u59e9\u6d63\ufffd
//            app.action(new Event(app.start_button, 0, null), null);
//            app.action(new Event("", 0, null), null);

            __CLR4_4_115i15ilpnjv557.R.inc(1517);AlgAnimFrame aaf = new AlgAnimFrame(app, new URL("http://example.com/"));


            __CLR4_4_115i15ilpnjv557.R.inc(1518);try {
                __CLR4_4_115i15ilpnjv557.R.inc(1519);AlgThread alg = new AlgThread(null);
            } catch (Exception e) {
                __CLR4_4_115i15ilpnjv557.R.inc(1520);System.out.println(e.getMessage());
            }
            __CLR4_4_115i15ilpnjv557.R.inc(1521);AlgThread alg1 = new AlgThread(aaf);


            __CLR4_4_115i15ilpnjv557.R.inc(1522);try {
                __CLR4_4_115i15ilpnjv557.R.inc(1523);aaf.handleEvent(null);
            } catch (Exception e) {
                __CLR4_4_115i15ilpnjv557.R.inc(1524);System.out.println(e.getMessage());
            }
            __CLR4_4_115i15ilpnjv557.R.inc(1525);aaf.Highlight(-20);
            __CLR4_4_115i15ilpnjv557.R.inc(1526);aaf.Highlight(200);
            __CLR4_4_115i15ilpnjv557.R.inc(1527);aaf.Highlight(0);
            __CLR4_4_115i15ilpnjv557.R.inc(1528);aaf.Highlight(2);

//            aaf.stepWait = false;
//            aaf.startAlg();
//            aaf.stepWait = true;
//            aaf.startAlg();
//
//            aaf.finishAlg();
//
//            aaf.step = false;
//            aaf.waitStep();
            __CLR4_4_115i15ilpnjv557.R.inc(1529);aaf.step = true;
            __CLR4_4_115i15ilpnjv557.R.inc(1530);aaf.setStep(true);
            __CLR4_4_115i15ilpnjv557.R.inc(1531);aaf.step = false;
            __CLR4_4_115i15ilpnjv557.R.inc(1532);aaf.setStep(false);
            //
            __CLR4_4_115i15ilpnjv557.R.inc(1533);alg1.generateData();
            //\u74a7\u5241\u63e3\u741b\ufffd
            //
            __CLR4_4_115i15ilpnjv557.R.inc(1534);assertFalse(alg1.isAlive());
            __CLR4_4_115i15ilpnjv557.R.inc(1535);assertFalse(alg1.isDaemon());
            __CLR4_4_115i15ilpnjv557.R.inc(1536);assertFalse(alg1.isInterrupted());
//            \u6d93\u5b2e\u6f70\u93c4\ue21e\u6ba2\u93c8\u8679\u6b91\u951b\u5b8essert\u6d93\u5d84\u7c21
//                assertFalse(alg1.isInput());

            //
            __CLR4_4_115i15ilpnjv557.R.inc(1537);int[] tempArr = {1, 2, 3};
            __CLR4_4_115i15ilpnjv557.R.inc(1538);int[] tempArr1 = {1};
            __CLR4_4_115i15ilpnjv557.R.inc(1539);alg1.setHeapWithSize(tempArr, 0);
            __CLR4_4_115i15ilpnjv557.R.inc(1540);alg1.setHeapWithSize(tempArr, 2);
            //
//            alg1.animatePriorityQueue();
            //
            __CLR4_4_115i15ilpnjv557.R.inc(1541);alg1.addHeap(tempArr1, 1);

            //
            __CLR4_4_115i15ilpnjv557.R.inc(1542);alg1.delHeap(tempArr1);


            //
//                alg1.run();
            //
            __CLR4_4_115i15ilpnjv557.R.inc(1543);alg1.restoreDrawingPanelColor();


            __CLR4_4_115i15ilpnjv557.R.inc(1544);Heap h1 = alg1.heap;
            __CLR4_4_115i15ilpnjv557.R.inc(1545);Heap h2 = alg1.heap;
            __CLR4_4_115i15ilpnjv557.R.inc(1546);Heap h3 = alg1.heap;
            __CLR4_4_115i15ilpnjv557.R.inc(1547);h1.setInput(tempArr1);
            __CLR4_4_115i15ilpnjv557.R.inc(1548);h1.addInput(1);
            __CLR4_4_115i15ilpnjv557.R.inc(1549);h1.delFirstInput();

            //
            __CLR4_4_115i15ilpnjv557.R.inc(1550);h1.heapArray.add(new Node("java"));
            __CLR4_4_115i15ilpnjv557.R.inc(1551);assertEquals(false, h1.heapArray.isEmpty());
            __CLR4_4_115i15ilpnjv557.R.inc(1552);h1.addOutput(1);
            __CLR4_4_115i15ilpnjv557.R.inc(1553);h1.addOutput(0);
            __CLR4_4_115i15ilpnjv557.R.inc(1554);h2.heapArray.add(new Node("java"));
            __CLR4_4_115i15ilpnjv557.R.inc(1555);h2.nodeList.add(new Node("python"));
//                h2.addOutput(0);
            __CLR4_4_115i15ilpnjv557.R.inc(1556);h2.moveLast2First();
            __CLR4_4_115i15ilpnjv557.R.inc(1557);h2.addOutput(0);
            __CLR4_4_115i15ilpnjv557.R.inc(1558);h2.heapArray.add(new Node("java"));
            __CLR4_4_115i15ilpnjv557.R.inc(1559);h3.nodeList.add(new Node("python"));
            __CLR4_4_115i15ilpnjv557.R.inc(1560);h3.nodeList.add(new Node("java"));
            __CLR4_4_115i15ilpnjv557.R.inc(1561);h3.nodeList.add(new Node("python"));
            __CLR4_4_115i15ilpnjv557.R.inc(1562);h3.nodeList.add(new Node("python"));
            __CLR4_4_115i15ilpnjv557.R.inc(1563);h3.moveLast2First();

            __CLR4_4_115i15ilpnjv557.R.inc(1564);h3.restore(0);
            __CLR4_4_115i15ilpnjv557.R.inc(1565);h3.highlight(0);
            __CLR4_4_115i15ilpnjv557.R.inc(1566);h3.exchangeArrow(0, 0);
            __CLR4_4_115i15ilpnjv557.R.inc(1567);try {
                __CLR4_4_115i15ilpnjv557.R.inc(1568);h3.exchangeArrow(0, 1);
            }catch (Exception e){}

            __CLR4_4_115i15ilpnjv557.R.inc(1569);h3.input2heap();

            __CLR4_4_115i15ilpnjv557.R.inc(1570);int [] temp = new int[30];
            __CLR4_4_115i15ilpnjv557.R.inc(1571);h3.setHeap(temp);
            __CLR4_4_115i15ilpnjv557.R.inc(1572);Node node1 = new Node();
            __CLR4_4_115i15ilpnjv557.R.inc(1573);Node node2 = new Node(node1);
            __CLR4_4_115i15ilpnjv557.R.inc(1574);h3.treeWidth(node1);
            __CLR4_4_115i15ilpnjv557.R.inc(1575);h3.leftMostPosn(node2);
            __CLR4_4_115i15ilpnjv557.R.inc(1576);h3.moveNode(node1, 0, 0);



            __CLR4_4_115i15ilpnjv557.R.inc(1577);try{
                __CLR4_4_115i15ilpnjv557.R.inc(1578);h3.treeWidth(node2);
            }catch (Exception e){
                __CLR4_4_115i15ilpnjv557.R.inc(1579);System.out.println(e.getMessage());
            }
            __CLR4_4_115i15ilpnjv557.R.inc(1580);try{
                __CLR4_4_115i15ilpnjv557.R.inc(1581);h3.rightMostPosn(node2);
            }catch (Exception e){
                __CLR4_4_115i15ilpnjv557.R.inc(1582);System.out.println(e.getMessage());
            }
            __CLR4_4_115i15ilpnjv557.R.inc(1583);try{
                __CLR4_4_115i15ilpnjv557.R.inc(1584);h3.moveNode(node2, 0, 0);
            }catch (Exception e){
                __CLR4_4_115i15ilpnjv557.R.inc(1585);System.out.println(e.getMessage());
            }
            __CLR4_4_115i15ilpnjv557.R.inc(1586);Event event1 = new Event(2, Event.SCROLL_LINE_UP, null);
            __CLR4_4_115i15ilpnjv557.R.inc(1587);Event event2 = new Event(2, Event.SCROLL_LINE_DOWN, null);
            __CLR4_4_115i15ilpnjv557.R.inc(1588);Event event3 = new Event(2, Event.SCROLL_PAGE_UP, null);
            __CLR4_4_115i15ilpnjv557.R.inc(1589);Event event4 = new Event(2, Event.SCROLL_PAGE_DOWN, null);
            __CLR4_4_115i15ilpnjv557.R.inc(1590);Event event5 = new Event(2, Event.SCROLL_ABSOLUTE, null);
            __CLR4_4_115i15ilpnjv557.R.inc(1591);Event event6 = new Event(2, Event.WINDOW_DESTROY, null);
            __CLR4_4_115i15ilpnjv557.R.inc(1592);Event event7 = new Event(2, Event.WINDOW_DESTROY, true);
            __CLR4_4_115i15ilpnjv557.R.inc(1593);Event event8 = new Event(2, 0, true);
            __CLR4_4_115i15ilpnjv557.R.inc(1594);event1.target = aaf.vScrollbar;
            __CLR4_4_115i15ilpnjv557.R.inc(1595);event2.target = aaf.vScrollbar;
            __CLR4_4_115i15ilpnjv557.R.inc(1596);event3.target = aaf.vScrollbar;
            __CLR4_4_115i15ilpnjv557.R.inc(1597);event4.target = aaf.vScrollbar;
            __CLR4_4_115i15ilpnjv557.R.inc(1598);event5.target = aaf.vScrollbar;
            __CLR4_4_115i15ilpnjv557.R.inc(1599);event8.target = aaf.vScrollbar;
            __CLR4_4_115i15ilpnjv557.R.inc(1600);aaf.handleEvent(event1);
            __CLR4_4_115i15ilpnjv557.R.inc(1601);aaf.handleEvent(event2);
            __CLR4_4_115i15ilpnjv557.R.inc(1602);aaf.handleEvent(event3);
            __CLR4_4_115i15ilpnjv557.R.inc(1603);aaf.handleEvent(event4);
            __CLR4_4_115i15ilpnjv557.R.inc(1604);aaf.handleEvent(event5);
            __CLR4_4_115i15ilpnjv557.R.inc(1605);aaf.handleEvent(event6);
            __CLR4_4_115i15ilpnjv557.R.inc(1606);aaf.handleEvent(event7);
            __CLR4_4_115i15ilpnjv557.R.inc(1607);aaf.handleEvent(event8);

            __CLR4_4_115i15ilpnjv557.R.inc(1608);aaf.alg = null;
            __CLR4_4_115i15ilpnjv557.R.inc(1609);aaf.handleEvent(event6);
            __CLR4_4_115i15ilpnjv557.R.inc(1610);aaf.drawingPanel = null;
            __CLR4_4_115i15ilpnjv557.R.inc(1611);AlgThread algAdd = new AlgThread(aaf);
            __CLR4_4_115i15ilpnjv557.R.inc(1612);ComBox cb = new ComBox(1, 1,  "2", new Font("1", 1,1));
            __CLR4_4_115i15ilpnjv557.R.inc(1613);cb.setText("da");

            __CLR4_4_115i15ilpnjv557.R.inc(1614);ControlPanel cp = new ControlPanel(aaf, "2");
            __CLR4_4_115i15ilpnjv557.R.inc(1615);event1.target = cp.run_button;
            __CLR4_4_115i15ilpnjv557.R.inc(1616);try {
                __CLR4_4_115i15ilpnjv557.R.inc(1617);cp.action(event1, "ada");
            }catch (Exception e){
                __CLR4_4_115i15ilpnjv557.R.inc(1618);System.out.println(e.getMessage());
            }
            __CLR4_4_115i15ilpnjv557.R.inc(1619);event1.target = cp.stop_button;
            __CLR4_4_115i15ilpnjv557.R.inc(1620);try {
                __CLR4_4_115i15ilpnjv557.R.inc(1621);cp.action(event1, "ada");
            }catch (Exception e){
                __CLR4_4_115i15ilpnjv557.R.inc(1622);System.out.println(e.getMessage());
            }
            __CLR4_4_115i15ilpnjv557.R.inc(1623);event1.target = cp.step_button;

            __CLR4_4_115i15ilpnjv557.R.inc(1624);try {
                __CLR4_4_115i15ilpnjv557.R.inc(1625);cp.action(event1, "ada");
            }catch (Exception e){
                __CLR4_4_115i15ilpnjv557.R.inc(1626);System.out.println(e.getMessage());
            }
            __CLR4_4_115i15ilpnjv557.R.inc(1627);event1.target = cp.quit_button;

            __CLR4_4_115i15ilpnjv557.R.inc(1628);try {
                __CLR4_4_115i15ilpnjv557.R.inc(1629);cp.action(event1, "ada");
            }catch (Exception e){
                __CLR4_4_115i15ilpnjv557.R.inc(1630);System.out.println(e.getMessage());
            }


        } catch (Exception e) {
            __CLR4_4_115i15ilpnjv557.R.inc(1631);e.printStackTrace();
        }





    }finally{__CLR4_4_115i15ilpnjv557.R.flushNeeded();}}
}


