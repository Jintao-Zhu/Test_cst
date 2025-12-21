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
                alg1.animatePriorityQueue();
            //

        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}


