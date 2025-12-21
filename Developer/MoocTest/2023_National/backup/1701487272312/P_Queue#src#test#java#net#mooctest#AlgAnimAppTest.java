package net.mooctest;
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

public class AlgAnimAppTest {
    AlgAnimApp dad = new AlgAnimApp();

    AlgAnimApp aaa1 = new AlgAnimApp();

    String filePath = "src/test/resources/1";
    // 将文件路径转换为URL格式
    String fileURL = "file:///" + filePath.replace("\\", "/");

    URL url1;
    {
        try {
            url1 = new URL(fileURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    String filePath1 = "src/test/resources/1";
    // 将文件路径转换为URL格式
    String fileURL1 = "file:///" + filePath1.replace("\\", "/");
    //    https://github.com/bitcoinj/bitcoinj/blob/master/designdocs/Deterministic%20wallets.txt
    URL url2;
    {
        try {
            url2 = new URL(fileURL1);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    URL url3;

    {
        try {
            url3 = new URL("http://example.com/somefile.txt");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void test1() throws MalformedURLException {
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

    }

    @Test
    public void test() throws MalformedURLException {
        System.setProperty("java.awt.headless", "true");


        try {

            AlgAnimApp app = new AlgAnimApp() {
                public String getParameter(String name) {
//                    if ("filename".equals(name)) {
//                        return "src/test/resources/2.txt"; // 这里应该是您想要加载的文件名
//                    } else if ("buttonname".equals(name)) {
//                        return ""; // 按钮上的文字
//                    }
                    return null;
                }

                public URL getCodeBase() {
                    try {
                        return new URL("http://example.com/"); // 假设的基础 URL
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            // 初始化 Applet
//            app.init();

            // 模拟点击按钮的动作
//            app.action(new Event(app.start_button, 0, null), null);
//            app.action(new Event("", 0, null), null);

            AlgAnimFrame aaf = new AlgAnimFrame(app, new URL("http://example.com/"));


            try {
                AlgThread alg = new AlgThread(null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            AlgThread alg1 = new AlgThread(aaf);


            try {
                aaf.handleEvent(null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            aaf.Highlight(-20);
            aaf.Highlight(200);
            aaf.Highlight(0);
            aaf.Highlight(2);

//            aaf.stepWait = false;
//            aaf.startAlg();
//            aaf.stepWait = true;
//            aaf.startAlg();
//
//            aaf.finishAlg();
//
//            aaf.step = false;
//            aaf.waitStep();
            aaf.step = true;
            aaf.setStep(true);
            aaf.step = false;
            aaf.setStep(false);
            //
            alg1.generateData();
            //赶紧补
            //
            assertFalse(alg1.isAlive());
            assertFalse(alg1.isDaemon());
            assertFalse(alg1.isInterrupted());
//            下面是随机的，assert不了
//                assertFalse(alg1.isInput());

            //
            int[] tempArr = {1, 2, 3};
            int[] tempArr1 = {1};
            alg1.setHeapWithSize(tempArr, 0);
            alg1.setHeapWithSize(tempArr, 2);
            //
//            alg1.animatePriorityQueue();
            //
            alg1.addHeap(tempArr1, 1);

            //
            alg1.delHeap(tempArr1);


            //
//                alg1.run();
            //
            alg1.restoreDrawingPanelColor();


            Heap h1 = alg1.heap;
            Heap h2 = alg1.heap;
            Heap h3 = alg1.heap;
            h1.setInput(tempArr1);
            h1.addInput(1);
            h1.delFirstInput();

            //
            h1.heapArray.add(new Node("java"));
            assertEquals(false, h1.heapArray.isEmpty());
            h1.addOutput(1);
            h1.addOutput(0);
            h2.heapArray.add(new Node("java"));
            h2.nodeList.add(new Node("python"));
//                h2.addOutput(0);
            h2.moveLast2First();
            h2.addOutput(0);
            h2.heapArray.add(new Node("java"));
            h3.nodeList.add(new Node("python"));
            h3.nodeList.add(new Node("java"));
            h3.nodeList.add(new Node("python"));
            h3.nodeList.add(new Node("python"));
            h3.moveLast2First();

            h3.restore(0);
            h3.highlight(0);
            h3.exchangeArrow(0, 0);
            try {
                h3.exchangeArrow(0, 1);
            }catch (Exception e){}

            h3.input2heap();

            int [] temp = new int[30];
            h3.setHeap(temp);
            Node node1 = new Node();
            Node node2 = new Node(node1);
            h3.treeWidth(node1);
            h3.leftMostPosn(node2);
            h3.moveNode(node1, 0, 0);



            try{
                h3.treeWidth(node2);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            try{
                h3.rightMostPosn(node2);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            try{
                h3.moveNode(node2, 0, 0);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            Event event1 = new Event(2, Event.SCROLL_LINE_UP, null);
            Event event2 = new Event(2, Event.SCROLL_LINE_DOWN, null);
            Event event3 = new Event(2, Event.SCROLL_PAGE_UP, null);
            Event event4 = new Event(2, Event.SCROLL_PAGE_DOWN, null);
            Event event5 = new Event(2, Event.SCROLL_ABSOLUTE, null);
            Event event6 = new Event(2, Event.WINDOW_DESTROY, null);
            Event event7 = new Event(2, Event.WINDOW_DESTROY, true);
            Event event8 = new Event(2, 0, true);
            event1.target = aaf.vScrollbar;
            event2.target = aaf.vScrollbar;
            event3.target = aaf.vScrollbar;
            event4.target = aaf.vScrollbar;
            event5.target = aaf.vScrollbar;
            event8.target = aaf.vScrollbar;
            aaf.handleEvent(event1);
            aaf.handleEvent(event2);
            aaf.handleEvent(event3);
            aaf.handleEvent(event4);
            aaf.handleEvent(event5);
            aaf.handleEvent(event6);
            aaf.handleEvent(event7);
            aaf.handleEvent(event8);

            aaf.alg = null;
            aaf.handleEvent(event6);
            aaf.drawingPanel = null;
            AlgThread algAdd = new AlgThread(aaf);


            



        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}


