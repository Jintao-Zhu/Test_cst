package net.mooctest;

import static net.mooctest.MessageBuffer.KB;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Httpheaderstest {

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static PrintStream originalOut;

    @BeforeClass
    public static void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterClass
    public static void tearDownAndVerify() {
        System.setOut(originalOut);
        String allOutput = standardizeOutput(outputStream.toString());
        assertEquals(17, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Test
    public void testSocketProcessor() throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {

        Queue<Socket> socketQueue = new ArrayBlockingQueue(1024);
        Runnable socketAccepter = new SocketAccepter(8080, socketQueue);
        Thread thread11 = new Thread(socketAccepter);
        thread11.start();

        MessageBuffer readMessageBuffer = new MessageBuffer();
        MessageBuffer writeMessageBuffer = new MessageBuffer();
        IMessageReaderFactory messageReaderFactory = new HttpMessageReaderFactory(); // 自定义工厂

        SocketProcessor socketProcessor1 = new SocketProcessor(socketQueue, readMessageBuffer, writeMessageBuffer,messageReaderFactory, null);
        SocketProcessor socketProcessor2 = new SocketProcessor(socketQueue, readMessageBuffer, writeMessageBuffer,messageReaderFactory, null);
        Thread thread1 = new Thread(socketProcessor1);
        Thread thread2 = new Thread(socketProcessor2);
//        thread1.start();
//        thread2.start();

        ExecutorService executor = Executors.newFixedThreadPool(3); // 创建一个固定大小的线程池，最多同时运行3个任务
        executor.submit(thread1);
        executor.submit(thread2);

        executor.shutdown();
    }
    @Test
    public void testHttpMessageReader() throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        Selector selector = Selector.open();
        socketChannel.connect(new java.net.InetSocketAddress("www.bing.com", 80));
        socketChannel.configureBlocking(false);  // 设置为非阻塞模式
        socketChannel.register(selector, SelectionKey.OP_READ);

        Socket socket = new Socket(socketChannel);
        // 创建 ByteBuffer 用于读取数据
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        // 创建 ByteBuffer 用于写入数据
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        writeBuffer.put("Hello, Server!".getBytes());
        writeBuffer.flip();  // 切换为读模式
//
        // 调用 write 方法将数据发送到服务器
        socket.write(writeBuffer);
        socket.write(writeBuffer);

        int bytesRead = socket.read(readBuffer);
        if (bytesRead > 0) {
            readBuffer.flip();  // 切换为读模式
            while (readBuffer.hasRemaining()) {
                System.out.print((char) readBuffer.get());
            }
        } else if (bytesRead == -1) {
            System.out.println("End of stream reached.");
        }

        HttpMessageReader httpMessageReader = new HttpMessageReader();
        httpMessageReader.init(new MessageBuffer());

        httpMessageReader.read(socket, ByteBuffer.allocate(1024));
        System.out.println(httpMessageReader.getMessages());

        socketChannel.close();
    }
    @Test
    public void testHttpUtil() throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {

        byte[] GET = new byte[] {'G', 'E', 'T'};
        final byte[] POST = new byte[] {'P', 'O', 'S', 'T'};
        byte[] PUT = new byte[] {'P', 'U', 'T'};
        byte[] HEAD = new byte[] {'H', 'E', 'A', 'D'};
        byte[] DELETE = new byte[] {'D', 'E', 'L', 'E', 'T', 'E'};
        byte[] HOST = new byte[] {'H', 'o', 's', 't'};
        byte[] CONTENT_LENGTH = new byte[] {'C', 'o', 'n', 't', 'e', 'n', 't', '-', 'L', 'e', 'n', 'g', 't', 'h'};
        byte[] src = new byte[] {'C', 1, 'n', '\r', '\n'};
        HttpUtil httpUtil = new HttpUtil();

        System.out.println(httpUtil.parseHttpRequest(src, 0, 5, new HttpHeaders()));
        System.out.println(httpUtil.parseHttpRequest(src, 0, 3, new HttpHeaders()));
        System.out.println(HttpUtil.findNextLineBreak(src, 0, 5));
        System.out.println(HttpUtil.findNextLineBreak(src, 0, 3));
        System.out.println(HttpUtil.findNext(src, 0, 5, (byte) 1));
        System.out.println(HttpUtil.findNext(src, 0, 1, (byte) 1));

        HttpUtil.resolveHttpMethod(HEAD, 0, new HttpHeaders());
        HttpUtil.resolveHttpMethod(GET, 0, new HttpHeaders());
        HttpUtil.resolveHttpMethod(POST, 0, new HttpHeaders());
        HttpUtil.resolveHttpMethod(PUT, 0, new HttpHeaders());
        HttpUtil.resolveHttpMethod(CONTENT_LENGTH, 0, new HttpHeaders());
        HttpUtil.resolveHttpMethod(DELETE, 0, new HttpHeaders());
        HttpUtil.resolveHttpMethod(HOST, 0, new HttpHeaders());

        HttpUtil.matches(CONTENT_LENGTH, 0, CONTENT_LENGTH);
        HttpUtil.matches(CONTENT_LENGTH, 0, POST);
    }

    @Test
    public void testSocket() throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {

        SocketChannel socketChannel = SocketChannel.open();
        Selector selector = Selector.open();
        socketChannel.connect(new java.net.InetSocketAddress("www.baidu.com", 80));
        socketChannel.configureBlocking(false);  // 设置为非阻塞模式
        socketChannel.register(selector, SelectionKey.OP_READ);

        // 创建 Socket 对象
        Socket socket = new Socket(socketChannel);

        // 创建 ByteBuffer 用于读取数据
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        // 创建 ByteBuffer 用于写入数据
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        writeBuffer.put("Hello, Server!".getBytes());
        writeBuffer.flip();  // 切换为读模式

        // 调用 write 方法将数据发送到服务器
        socket.write(writeBuffer);
        socket.write(writeBuffer);

        // 调用 read 方法从服务器读取数据
        int bytesRead = socket.read(readBuffer);
        if (bytesRead > 0) {
            readBuffer.flip();  // 切换为读模式
            while (readBuffer.hasRemaining()) {
                System.out.print((char) readBuffer.get());
            }
        } else if (bytesRead == -1) {
            System.out.println("End of stream reached.");
        }

        // 关闭连接
        socketChannel.close();
    }
    @Test
    public void testQueueIntFlip() throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {

        QueueIntFlip queueIntFlip = new QueueIntFlip(10);
        queueIntFlip.put(2);
        queueIntFlip.put(4);
        queueIntFlip.put(6);
        queueIntFlip.put(8);
        queueIntFlip.put(10);
        queueIntFlip.put(new int[]{1, 3, 5, 7, 9}, 5);
        System.out.println(queueIntFlip.take());
        System.out.println(queueIntFlip.available());
        System.out.println(queueIntFlip.remainingCapacity());
        System.out.println(queueIntFlip.take(new int[5], 5));
        queueIntFlip.put(2);
        queueIntFlip.put(4);
        queueIntFlip.put(new int[]{1, 3, 5, 7, 9}, 5);
        System.out.println(queueIntFlip.take(new int[5], 5));
        queueIntFlip.take();
        queueIntFlip.put(6);

        queueIntFlip.flipped = true;
        System.out.println(queueIntFlip.take());
        System.out.println(queueIntFlip.available());
        System.out.println(queueIntFlip.remainingCapacity());
        System.out.println(queueIntFlip.take(new int[5], 5));
        queueIntFlip.put(2);
        queueIntFlip.put(4);
        queueIntFlip.put(6);
        queueIntFlip.reset();
        queueIntFlip.flipped = true;
        System.out.println(queueIntFlip.take());
        queueIntFlip.put(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10);

    }

    @Test
    public void testMessageBuffer() {
        MessageBuffer messageBuffer = new MessageBuffer();
        Message message = messageBuffer.getMessage();
        messageBuffer.expandMessage(message);
        message.capacity = 128  * KB;
        messageBuffer.expandMessage(message);
        message.capacity = 256  * KB;
        messageBuffer.expandMessage(message);

    }
}
