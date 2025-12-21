/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjenkov on 18-10-2015.
 */
public class HttpMessageReader implements IMessageReader {public static class __CLR4_4_100l9k8v06b{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0044\u003a\u005c\u005c\u0053\u006f\u0066\u0074\u0077\u0061\u0072\u0065\u0073\u005c\u005c\u004a\u0061\u0076\u0061\u005c\u005c\u004a\u0061\u0076\u0061\u005f\u0070\u0072\u0061\u0063\u0074\u0069\u0063\u0065\u005c\u005c\u004e\u0049\u004f\u0053\u0065\u0072\u0076\u0065\u0072\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666462693740L,8589935092L,26,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    private MessageBuffer messageBuffer    = null;

    private List<Message> completeMessages = new ArrayList<Message>();
    private Message       nextMessage      = null;

    public HttpMessageReader() {try{__CLR4_4_100l9k8v06b.R.inc(0);
    }finally{__CLR4_4_100l9k8v06b.R.flushNeeded();}}

    @Override
    public void init(MessageBuffer readMessageBuffer) {try{__CLR4_4_100l9k8v06b.R.inc(1);
        __CLR4_4_100l9k8v06b.R.inc(2);this.messageBuffer        = readMessageBuffer;
        __CLR4_4_100l9k8v06b.R.inc(3);this.nextMessage          = messageBuffer.getMessage();
        __CLR4_4_100l9k8v06b.R.inc(4);this.nextMessage.metaData = new HttpHeaders();
    }finally{__CLR4_4_100l9k8v06b.R.flushNeeded();}}

    @Override
    public void read(Socket socket, ByteBuffer byteBuffer) throws IOException {try{__CLR4_4_100l9k8v06b.R.inc(5);
        __CLR4_4_100l9k8v06b.R.inc(6);int bytesRead = socket.read(byteBuffer);
        __CLR4_4_100l9k8v06b.R.inc(7);byteBuffer.flip();

        __CLR4_4_100l9k8v06b.R.inc(8);if((((byteBuffer.remaining() == 0)&&(__CLR4_4_100l9k8v06b.R.iget(9)!=0|true))||(__CLR4_4_100l9k8v06b.R.iget(10)==0&false))){{
            __CLR4_4_100l9k8v06b.R.inc(11);byteBuffer.clear();
            __CLR4_4_100l9k8v06b.R.inc(12);return;
        }

        }__CLR4_4_100l9k8v06b.R.inc(13);this.nextMessage.writeToMessage(byteBuffer);

        __CLR4_4_100l9k8v06b.R.inc(14);int endIndex = HttpUtil.parseHttpRequest(this.nextMessage.sharedArray, this.nextMessage.offset, this.nextMessage.offset + this.nextMessage.length, (HttpHeaders) this.nextMessage.metaData);
        __CLR4_4_100l9k8v06b.R.inc(15);if((((endIndex != -1)&&(__CLR4_4_100l9k8v06b.R.iget(16)!=0|true))||(__CLR4_4_100l9k8v06b.R.iget(17)==0&false))){{
            __CLR4_4_100l9k8v06b.R.inc(18);Message message = this.messageBuffer.getMessage();
            __CLR4_4_100l9k8v06b.R.inc(19);message.metaData = new HttpHeaders();

            __CLR4_4_100l9k8v06b.R.inc(20);message.writePartialMessageToMessage(nextMessage, endIndex);

            __CLR4_4_100l9k8v06b.R.inc(21);completeMessages.add(nextMessage);
            __CLR4_4_100l9k8v06b.R.inc(22);nextMessage = message;
        }
        }__CLR4_4_100l9k8v06b.R.inc(23);byteBuffer.clear();
    }finally{__CLR4_4_100l9k8v06b.R.flushNeeded();}}


    @Override
    public List<Message> getMessages() {try{__CLR4_4_100l9k8v06b.R.inc(24);
        __CLR4_4_100l9k8v06b.R.inc(25);return this.completeMessages;
    }finally{__CLR4_4_100l9k8v06b.R.flushNeeded();}}

}
