/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.*;

/**
 * Created by jjenkov on 16-10-2015.
 */
public class SocketProcessor implements Runnable {public static class __CLR4_4_1c8c8l9k8v0bs{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0044\u003a\u005c\u005c\u0053\u006f\u0066\u0074\u0077\u0061\u0072\u0065\u0073\u005c\u005c\u004a\u0061\u0076\u0061\u005c\u005c\u004a\u0061\u0076\u0061\u005f\u0070\u0072\u0061\u0063\u0074\u0069\u0063\u0065\u005c\u005c\u004e\u0049\u004f\u0053\u0065\u0072\u0076\u0065\u0072\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666462693740L,8589935092L,557,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    private Queue<Socket>  inboundSocketQueue   = null;

    private MessageBuffer  readMessageBuffer    = null; //todo   Not used now - but perhaps will be later - to check for space in the buffer before reading from sockets
    private MessageBuffer  writeMessageBuffer   = null; //todo   Not used now - but perhaps will be later - to check for space in the buffer before reading from sockets (space for more to write?)

    private IMessageReaderFactory messageReaderFactory = null;

    private Queue<Message> outboundMessageQueue = new LinkedList<>(); //todo use a better / faster queue.

    private Map<Long, Socket> socketMap         = new HashMap<>();

    private ByteBuffer readByteBuffer  = ByteBuffer.allocate(1024 * 1024);
    private ByteBuffer writeByteBuffer = ByteBuffer.allocate(1024 * 1024);
    private Selector   readSelector    = null;
    private Selector   writeSelector   = null;

    private IMessageProcessor messageProcessor = null;
    private WriteProxy        writeProxy       = null;

    private long              nextSocketId = 16 * 1024; //start incoming socket ids from 16K - reserve bottom ids for pre-defined sockets (servers).

    private Set<Socket> emptyToNonEmptySockets = new HashSet<>();
    private Set<Socket> nonEmptyToEmptySockets = new HashSet<>();


    public SocketProcessor(Queue<Socket> inboundSocketQueue, MessageBuffer readMessageBuffer, MessageBuffer writeMessageBuffer, IMessageReaderFactory messageReaderFactory, IMessageProcessor messageProcessor) throws IOException {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(440);
        __CLR4_4_1c8c8l9k8v0bs.R.inc(441);this.inboundSocketQueue = inboundSocketQueue;

        __CLR4_4_1c8c8l9k8v0bs.R.inc(442);this.readMessageBuffer    = readMessageBuffer;
        __CLR4_4_1c8c8l9k8v0bs.R.inc(443);this.writeMessageBuffer   = writeMessageBuffer;
        __CLR4_4_1c8c8l9k8v0bs.R.inc(444);this.writeProxy           = new WriteProxy(writeMessageBuffer, this.outboundMessageQueue);

        __CLR4_4_1c8c8l9k8v0bs.R.inc(445);this.messageReaderFactory = messageReaderFactory;

        __CLR4_4_1c8c8l9k8v0bs.R.inc(446);this.messageProcessor     = messageProcessor;

        __CLR4_4_1c8c8l9k8v0bs.R.inc(447);this.readSelector         = Selector.open();
        __CLR4_4_1c8c8l9k8v0bs.R.inc(448);this.writeSelector        = Selector.open();
    }finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}

    public void run() {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(449);
        __CLR4_4_1c8c8l9k8v0bs.R.inc(450);while(true){{
            __CLR4_4_1c8c8l9k8v0bs.R.inc(451);try{
                __CLR4_4_1c8c8l9k8v0bs.R.inc(452);executeCycle();
            } catch(IOException e){
                __CLR4_4_1c8c8l9k8v0bs.R.inc(453);e.printStackTrace();
            }

            __CLR4_4_1c8c8l9k8v0bs.R.inc(454);try {
                __CLR4_4_1c8c8l9k8v0bs.R.inc(455);Thread.sleep(100);
            } catch (InterruptedException e) {
                __CLR4_4_1c8c8l9k8v0bs.R.inc(456);e.printStackTrace();
            }
        }
    }}finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}


    public void executeCycle() throws IOException {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(457);
        __CLR4_4_1c8c8l9k8v0bs.R.inc(458);takeNewSockets();
        __CLR4_4_1c8c8l9k8v0bs.R.inc(459);readFromSockets();
        __CLR4_4_1c8c8l9k8v0bs.R.inc(460);writeToSockets();
    }finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}


    public void takeNewSockets() throws IOException {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(461);
        __CLR4_4_1c8c8l9k8v0bs.R.inc(462);Socket newSocket = this.inboundSocketQueue.poll();

        __CLR4_4_1c8c8l9k8v0bs.R.inc(463);while((((newSocket != null)&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(464)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(465)==0&false))){{
            __CLR4_4_1c8c8l9k8v0bs.R.inc(466);newSocket.socketId = this.nextSocketId++;
            __CLR4_4_1c8c8l9k8v0bs.R.inc(467);newSocket.socketChannel.configureBlocking(false);

            __CLR4_4_1c8c8l9k8v0bs.R.inc(468);newSocket.messageReader = this.messageReaderFactory.createMessageReader();
            __CLR4_4_1c8c8l9k8v0bs.R.inc(469);newSocket.messageReader.init(this.readMessageBuffer);

            __CLR4_4_1c8c8l9k8v0bs.R.inc(470);newSocket.messageWriter = new MessageWriter();

            __CLR4_4_1c8c8l9k8v0bs.R.inc(471);this.socketMap.put(newSocket.socketId, newSocket);

            __CLR4_4_1c8c8l9k8v0bs.R.inc(472);SelectionKey key = newSocket.socketChannel.register(this.readSelector, SelectionKey.OP_READ);
            __CLR4_4_1c8c8l9k8v0bs.R.inc(473);key.attach(newSocket);

            __CLR4_4_1c8c8l9k8v0bs.R.inc(474);newSocket = this.inboundSocketQueue.poll();
        }
    }}finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}


    public void readFromSockets() throws IOException {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(475);
        __CLR4_4_1c8c8l9k8v0bs.R.inc(476);int readReady = this.readSelector.selectNow();

        __CLR4_4_1c8c8l9k8v0bs.R.inc(477);if((((readReady > 0)&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(478)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(479)==0&false))){{
            __CLR4_4_1c8c8l9k8v0bs.R.inc(480);Set<SelectionKey> selectedKeys = this.readSelector.selectedKeys();
            __CLR4_4_1c8c8l9k8v0bs.R.inc(481);Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            __CLR4_4_1c8c8l9k8v0bs.R.inc(482);while((((keyIterator.hasNext())&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(483)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(484)==0&false))) {{
                __CLR4_4_1c8c8l9k8v0bs.R.inc(485);SelectionKey key = keyIterator.next();

                __CLR4_4_1c8c8l9k8v0bs.R.inc(486);readFromSocket(key);

                __CLR4_4_1c8c8l9k8v0bs.R.inc(487);keyIterator.remove();
            }
            }__CLR4_4_1c8c8l9k8v0bs.R.inc(488);selectedKeys.clear();
        }
    }}finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}

    private void readFromSocket(SelectionKey key) throws IOException {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(489);
        __CLR4_4_1c8c8l9k8v0bs.R.inc(490);Socket socket = (Socket) key.attachment();
        __CLR4_4_1c8c8l9k8v0bs.R.inc(491);socket.messageReader.read(socket, this.readByteBuffer);

        __CLR4_4_1c8c8l9k8v0bs.R.inc(492);List<Message> fullMessages = socket.messageReader.getMessages();
        __CLR4_4_1c8c8l9k8v0bs.R.inc(493);if((((fullMessages.size() > 0)&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(494)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(495)==0&false))){{
            __CLR4_4_1c8c8l9k8v0bs.R.inc(496);for(Message message : fullMessages){{
                __CLR4_4_1c8c8l9k8v0bs.R.inc(497);message.socketId = socket.socketId;
                __CLR4_4_1c8c8l9k8v0bs.R.inc(498);this.messageProcessor.process(message, this.writeProxy);  //the message processor will eventually push outgoing messages into an IMessageWriter for this socket.
            }
            }__CLR4_4_1c8c8l9k8v0bs.R.inc(499);fullMessages.clear();
        }

        }__CLR4_4_1c8c8l9k8v0bs.R.inc(500);if((((socket.endOfStreamReached)&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(501)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(502)==0&false))){{
            __CLR4_4_1c8c8l9k8v0bs.R.inc(503);System.out.println("Socket closed: " + socket.socketId);
            __CLR4_4_1c8c8l9k8v0bs.R.inc(504);this.socketMap.remove(socket.socketId);
            __CLR4_4_1c8c8l9k8v0bs.R.inc(505);key.attach(null);
            __CLR4_4_1c8c8l9k8v0bs.R.inc(506);key.cancel();
            __CLR4_4_1c8c8l9k8v0bs.R.inc(507);key.channel().close();
        }
    }}finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}


    public void writeToSockets() throws IOException {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(508);

        // Take all new messages from outboundMessageQueue
        __CLR4_4_1c8c8l9k8v0bs.R.inc(509);takeNewOutboundMessages();

        // Cancel all sockets which have no more data to write.
        __CLR4_4_1c8c8l9k8v0bs.R.inc(510);cancelEmptySockets();

        // Register all sockets that *have* data and which are not yet registered.
        __CLR4_4_1c8c8l9k8v0bs.R.inc(511);registerNonEmptySockets();

        // Select from the Selector.
        __CLR4_4_1c8c8l9k8v0bs.R.inc(512);int writeReady = this.writeSelector.selectNow();

        __CLR4_4_1c8c8l9k8v0bs.R.inc(513);if((((writeReady > 0)&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(514)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(515)==0&false))){{
            __CLR4_4_1c8c8l9k8v0bs.R.inc(516);Set<SelectionKey>      selectionKeys = this.writeSelector.selectedKeys();
            __CLR4_4_1c8c8l9k8v0bs.R.inc(517);Iterator<SelectionKey> keyIterator   = selectionKeys.iterator();

            __CLR4_4_1c8c8l9k8v0bs.R.inc(518);while((((keyIterator.hasNext())&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(519)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(520)==0&false))){{
                __CLR4_4_1c8c8l9k8v0bs.R.inc(521);SelectionKey key = keyIterator.next();

                __CLR4_4_1c8c8l9k8v0bs.R.inc(522);Socket socket = (Socket) key.attachment();

                __CLR4_4_1c8c8l9k8v0bs.R.inc(523);socket.messageWriter.write(socket, this.writeByteBuffer);

                __CLR4_4_1c8c8l9k8v0bs.R.inc(524);if((((socket.messageWriter.isEmpty())&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(525)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(526)==0&false))){{
                    __CLR4_4_1c8c8l9k8v0bs.R.inc(527);this.nonEmptyToEmptySockets.add(socket);
                }

                }__CLR4_4_1c8c8l9k8v0bs.R.inc(528);keyIterator.remove();
            }

            }__CLR4_4_1c8c8l9k8v0bs.R.inc(529);selectionKeys.clear();

        }
    }}finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}

    private void registerNonEmptySockets() throws ClosedChannelException {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(530);
        __CLR4_4_1c8c8l9k8v0bs.R.inc(531);for(Socket socket : emptyToNonEmptySockets){{
            __CLR4_4_1c8c8l9k8v0bs.R.inc(532);socket.socketChannel.register(this.writeSelector, SelectionKey.OP_WRITE, socket);
        }
        }__CLR4_4_1c8c8l9k8v0bs.R.inc(533);emptyToNonEmptySockets.clear();
    }finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}

    private void cancelEmptySockets() {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(534);
        __CLR4_4_1c8c8l9k8v0bs.R.inc(535);for(Socket socket : nonEmptyToEmptySockets){{
            __CLR4_4_1c8c8l9k8v0bs.R.inc(536);SelectionKey key = socket.socketChannel.keyFor(this.writeSelector);

            __CLR4_4_1c8c8l9k8v0bs.R.inc(537);key.cancel();
        }
        }__CLR4_4_1c8c8l9k8v0bs.R.inc(538);nonEmptyToEmptySockets.clear();
    }finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}

    private void takeNewOutboundMessages() {try{__CLR4_4_1c8c8l9k8v0bs.R.inc(539);
        __CLR4_4_1c8c8l9k8v0bs.R.inc(540);Message outMessage = this.outboundMessageQueue.poll();
        __CLR4_4_1c8c8l9k8v0bs.R.inc(541);while((((outMessage != null)&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(542)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(543)==0&false))){{
            __CLR4_4_1c8c8l9k8v0bs.R.inc(544);Socket socket = this.socketMap.get(outMessage.socketId);

            __CLR4_4_1c8c8l9k8v0bs.R.inc(545);if((((socket != null)&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(546)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(547)==0&false))){{
                __CLR4_4_1c8c8l9k8v0bs.R.inc(548);MessageWriter messageWriter = socket.messageWriter;
                __CLR4_4_1c8c8l9k8v0bs.R.inc(549);if((((messageWriter.isEmpty())&&(__CLR4_4_1c8c8l9k8v0bs.R.iget(550)!=0|true))||(__CLR4_4_1c8c8l9k8v0bs.R.iget(551)==0&false))){{
                    __CLR4_4_1c8c8l9k8v0bs.R.inc(552);messageWriter.enqueue(outMessage);
                    __CLR4_4_1c8c8l9k8v0bs.R.inc(553);nonEmptyToEmptySockets.remove(socket);
                    __CLR4_4_1c8c8l9k8v0bs.R.inc(554);emptyToNonEmptySockets.add(socket);    //not necessary if removed from nonEmptyToEmptySockets in prev. statement.
                } }else{{
                   __CLR4_4_1c8c8l9k8v0bs.R.inc(555);messageWriter.enqueue(outMessage);
                }
            }}

            }__CLR4_4_1c8c8l9k8v0bs.R.inc(556);outMessage = this.outboundMessageQueue.poll();
        }
    }}finally{__CLR4_4_1c8c8l9k8v0bs.R.flushNeeded();}}

}
