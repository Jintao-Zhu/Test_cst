/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by jjenkov on 16-10-2015.
 */
public class Socket {public static class __CLR4_4_1b4b4l9k8v0at{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0044\u003a\u005c\u005c\u0053\u006f\u0066\u0074\u0077\u0061\u0072\u0065\u0073\u005c\u005c\u004a\u0061\u0076\u0061\u005c\u005c\u004a\u0061\u0076\u0061\u005f\u0070\u0072\u0061\u0063\u0074\u0069\u0063\u0065\u005c\u005c\u004e\u0049\u004f\u0053\u0065\u0072\u0076\u0065\u0072\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666462693740L,8589935092L,425,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    public long socketId;

    public SocketChannel  socketChannel = null;
    public IMessageReader messageReader = null;
    public MessageWriter  messageWriter = null;

    public boolean endOfStreamReached = false;

    public Socket() {try{__CLR4_4_1b4b4l9k8v0at.R.inc(400);
    }finally{__CLR4_4_1b4b4l9k8v0at.R.flushNeeded();}}

    public Socket(SocketChannel socketChannel) {try{__CLR4_4_1b4b4l9k8v0at.R.inc(401);
        __CLR4_4_1b4b4l9k8v0at.R.inc(402);this.socketChannel = socketChannel;
    }finally{__CLR4_4_1b4b4l9k8v0at.R.flushNeeded();}}

    public int read(ByteBuffer byteBuffer) throws IOException {try{__CLR4_4_1b4b4l9k8v0at.R.inc(403);
        __CLR4_4_1b4b4l9k8v0at.R.inc(404);int bytesRead = this.socketChannel.read(byteBuffer);
        __CLR4_4_1b4b4l9k8v0at.R.inc(405);int totalBytesRead = bytesRead;

        __CLR4_4_1b4b4l9k8v0at.R.inc(406);while((((bytesRead > 0)&&(__CLR4_4_1b4b4l9k8v0at.R.iget(407)!=0|true))||(__CLR4_4_1b4b4l9k8v0at.R.iget(408)==0&false))){{
            __CLR4_4_1b4b4l9k8v0at.R.inc(409);bytesRead = this.socketChannel.read(byteBuffer);
            __CLR4_4_1b4b4l9k8v0at.R.inc(410);totalBytesRead += bytesRead;
        }
        }__CLR4_4_1b4b4l9k8v0at.R.inc(411);if((((bytesRead == -1)&&(__CLR4_4_1b4b4l9k8v0at.R.iget(412)!=0|true))||(__CLR4_4_1b4b4l9k8v0at.R.iget(413)==0&false))){{
            __CLR4_4_1b4b4l9k8v0at.R.inc(414);this.endOfStreamReached = true;
        }

        }__CLR4_4_1b4b4l9k8v0at.R.inc(415);return totalBytesRead;
    }finally{__CLR4_4_1b4b4l9k8v0at.R.flushNeeded();}}

    public int write(ByteBuffer byteBuffer) throws IOException{try{__CLR4_4_1b4b4l9k8v0at.R.inc(416);
        __CLR4_4_1b4b4l9k8v0at.R.inc(417);int bytesWritten      = this.socketChannel.write(byteBuffer);
        __CLR4_4_1b4b4l9k8v0at.R.inc(418);int totalBytesWritten = bytesWritten;

        __CLR4_4_1b4b4l9k8v0at.R.inc(419);while((((bytesWritten > 0 && byteBuffer.hasRemaining())&&(__CLR4_4_1b4b4l9k8v0at.R.iget(420)!=0|true))||(__CLR4_4_1b4b4l9k8v0at.R.iget(421)==0&false))){{
            __CLR4_4_1b4b4l9k8v0at.R.inc(422);bytesWritten = this.socketChannel.write(byteBuffer);
            __CLR4_4_1b4b4l9k8v0at.R.inc(423);totalBytesWritten += bytesWritten;
        }

        }__CLR4_4_1b4b4l9k8v0at.R.inc(424);return totalBytesWritten;
    }finally{__CLR4_4_1b4b4l9k8v0at.R.flushNeeded();}}


}
