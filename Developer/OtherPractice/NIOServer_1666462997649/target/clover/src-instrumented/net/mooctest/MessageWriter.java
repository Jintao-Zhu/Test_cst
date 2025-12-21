/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjenkov on 21-10-2015.
 */
public class MessageWriter {public static class __CLR4_4_16o6ol9k8v09e{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0044\u003a\u005c\u005c\u0053\u006f\u0066\u0074\u0077\u0061\u0072\u0065\u0073\u005c\u005c\u004a\u0061\u0076\u0061\u005c\u005c\u004a\u0061\u0076\u0061\u005f\u0070\u0072\u0061\u0063\u0074\u0069\u0063\u0065\u005c\u005c\u004e\u0049\u004f\u0053\u0065\u0072\u0076\u0065\u0072\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666462693740L,8589935092L,262,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    private List<Message> writeQueue   = new ArrayList<>();
    private Message  messageInProgress = null;
    private int      bytesWritten      =    0;

    public MessageWriter() {try{__CLR4_4_16o6ol9k8v09e.R.inc(240);
    }finally{__CLR4_4_16o6ol9k8v09e.R.flushNeeded();}}

    public void enqueue(Message message) {try{__CLR4_4_16o6ol9k8v09e.R.inc(241);
        __CLR4_4_16o6ol9k8v09e.R.inc(242);if((((this.messageInProgress == null)&&(__CLR4_4_16o6ol9k8v09e.R.iget(243)!=0|true))||(__CLR4_4_16o6ol9k8v09e.R.iget(244)==0&false))){{
            __CLR4_4_16o6ol9k8v09e.R.inc(245);this.messageInProgress = message;
        } }else {{
            __CLR4_4_16o6ol9k8v09e.R.inc(246);this.writeQueue.add(message);
        }
    }}finally{__CLR4_4_16o6ol9k8v09e.R.flushNeeded();}}

    public void write(Socket socket, ByteBuffer byteBuffer) throws IOException {try{__CLR4_4_16o6ol9k8v09e.R.inc(247);
        __CLR4_4_16o6ol9k8v09e.R.inc(248);byteBuffer.put(this.messageInProgress.sharedArray, this.messageInProgress.offset + this.bytesWritten, this.messageInProgress.length - this.bytesWritten);
        __CLR4_4_16o6ol9k8v09e.R.inc(249);byteBuffer.flip();

        __CLR4_4_16o6ol9k8v09e.R.inc(250);this.bytesWritten += socket.write(byteBuffer);
        __CLR4_4_16o6ol9k8v09e.R.inc(251);byteBuffer.clear();

        __CLR4_4_16o6ol9k8v09e.R.inc(252);if((((bytesWritten >= this.messageInProgress.length)&&(__CLR4_4_16o6ol9k8v09e.R.iget(253)!=0|true))||(__CLR4_4_16o6ol9k8v09e.R.iget(254)==0&false))){{
            __CLR4_4_16o6ol9k8v09e.R.inc(255);if((((this.writeQueue.size() > 0)&&(__CLR4_4_16o6ol9k8v09e.R.iget(256)!=0|true))||(__CLR4_4_16o6ol9k8v09e.R.iget(257)==0&false))){{
                __CLR4_4_16o6ol9k8v09e.R.inc(258);this.messageInProgress = this.writeQueue.remove(0);
            } }else {{
                __CLR4_4_16o6ol9k8v09e.R.inc(259);this.messageInProgress = null;
                //todo unregister from selector
            }
        }}
    }}finally{__CLR4_4_16o6ol9k8v09e.R.flushNeeded();}}

    public boolean isEmpty() {try{__CLR4_4_16o6ol9k8v09e.R.inc(260);
        __CLR4_4_16o6ol9k8v09e.R.inc(261);return this.writeQueue.isEmpty() && this.messageInProgress == null;
    }finally{__CLR4_4_16o6ol9k8v09e.R.flushNeeded();}}

}
