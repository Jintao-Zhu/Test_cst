/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import java.nio.ByteBuffer;

/**
 * Created by jjenkov on 16-10-2015.
 */
public class Message {public static class __CLR4_4_14d4dl9k8v08f{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0044\u003a\u005c\u005c\u0053\u006f\u0066\u0074\u0077\u0061\u0072\u0065\u0073\u005c\u005c\u004a\u0061\u0076\u0061\u005c\u005c\u004a\u0061\u0076\u0061\u005f\u0070\u0072\u0061\u0063\u0074\u0069\u0063\u0065\u005c\u005c\u004e\u0049\u004f\u0053\u0065\u0072\u0076\u0065\u0072\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666462693740L,8589935092L,193,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    private MessageBuffer messageBuffer = null;

    public long socketId = 0; // the id of source socket or destination socket, depending on whether is going in or out.

    public byte[] sharedArray = null;
    public int    offset      = 0; //offset into sharedArray where this message data starts.
    public int    capacity    = 0; //the size of the section in the sharedArray allocated to this message.
    public int    length      = 0; //the number of bytes used of the allocated section.

    public Object metaData    = null;

    public Message(MessageBuffer messageBuffer) {try{__CLR4_4_14d4dl9k8v08f.R.inc(157);
        __CLR4_4_14d4dl9k8v08f.R.inc(158);this.messageBuffer = messageBuffer;
    }finally{__CLR4_4_14d4dl9k8v08f.R.flushNeeded();}}

    /**
     * Writes data from the ByteBuffer into this message - meaning into the buffer backing this message.
     *
     * @param byteBuffer The ByteBuffer containing the message data to write.
     * @return
     */
    public int writeToMessage(ByteBuffer byteBuffer){try{__CLR4_4_14d4dl9k8v08f.R.inc(159);
        __CLR4_4_14d4dl9k8v08f.R.inc(160);int remaining = byteBuffer.remaining();

        __CLR4_4_14d4dl9k8v08f.R.inc(161);while((((this.length + remaining > capacity)&&(__CLR4_4_14d4dl9k8v08f.R.iget(162)!=0|true))||(__CLR4_4_14d4dl9k8v08f.R.iget(163)==0&false))){{
            __CLR4_4_14d4dl9k8v08f.R.inc(164);if((((!this.messageBuffer.expandMessage(this))&&(__CLR4_4_14d4dl9k8v08f.R.iget(165)!=0|true))||(__CLR4_4_14d4dl9k8v08f.R.iget(166)==0&false))) {{
                __CLR4_4_14d4dl9k8v08f.R.inc(167);return -1;
            }
        }}

        }__CLR4_4_14d4dl9k8v08f.R.inc(168);int bytesToCopy = Math.min(remaining, this.capacity - this.length);
        __CLR4_4_14d4dl9k8v08f.R.inc(169);byteBuffer.get(this.sharedArray, this.offset + this.length, bytesToCopy);
        __CLR4_4_14d4dl9k8v08f.R.inc(170);this.length += bytesToCopy;

        __CLR4_4_14d4dl9k8v08f.R.inc(171);return bytesToCopy;
    }finally{__CLR4_4_14d4dl9k8v08f.R.flushNeeded();}}




    /**
     * Writes data from the byte array into this message - meaning into the buffer backing this message.
     *
     * @param byteArray The byte array containing the message data to write.
     * @return
     */
    public int writeToMessage(byte[] byteArray){try{__CLR4_4_14d4dl9k8v08f.R.inc(172);
        __CLR4_4_14d4dl9k8v08f.R.inc(173);return writeToMessage(byteArray, 0, byteArray.length);
    }finally{__CLR4_4_14d4dl9k8v08f.R.flushNeeded();}}


    /**
     * Writes data from the byte array into this message - meaning into the buffer backing this message.
     *
     * @param byteArray The byte array containing the message data to write.
     * @return
     */
    public int writeToMessage(byte[] byteArray, int offset, int length){try{__CLR4_4_14d4dl9k8v08f.R.inc(174);
        __CLR4_4_14d4dl9k8v08f.R.inc(175);int remaining = length;

        __CLR4_4_14d4dl9k8v08f.R.inc(176);while((((this.length + remaining > capacity)&&(__CLR4_4_14d4dl9k8v08f.R.iget(177)!=0|true))||(__CLR4_4_14d4dl9k8v08f.R.iget(178)==0&false))){{
            __CLR4_4_14d4dl9k8v08f.R.inc(179);if((((!this.messageBuffer.expandMessage(this))&&(__CLR4_4_14d4dl9k8v08f.R.iget(180)!=0|true))||(__CLR4_4_14d4dl9k8v08f.R.iget(181)==0&false))) {{
                __CLR4_4_14d4dl9k8v08f.R.inc(182);return -1;
            }
        }}

        }__CLR4_4_14d4dl9k8v08f.R.inc(183);int bytesToCopy = Math.min(remaining, this.capacity - this.length);
        __CLR4_4_14d4dl9k8v08f.R.inc(184);System.arraycopy(byteArray, offset, this.sharedArray, this.offset + this.length, bytesToCopy);
        __CLR4_4_14d4dl9k8v08f.R.inc(185);this.length += bytesToCopy;
        __CLR4_4_14d4dl9k8v08f.R.inc(186);return bytesToCopy;
    }finally{__CLR4_4_14d4dl9k8v08f.R.flushNeeded();}}




    /**
     * In case the buffer backing the nextMessage contains more than one HTTP message, move all data after the first
     * message to a new Message object.
     *
     * @param message   The message containing the partial message (after the first message).
     * @param endIndex  The end index of the first message in the buffer of the message given as parameter.
     */
    public void writePartialMessageToMessage(Message message, int endIndex){try{__CLR4_4_14d4dl9k8v08f.R.inc(187);
        __CLR4_4_14d4dl9k8v08f.R.inc(188);int startIndexOfPartialMessage = message.offset + endIndex;
        __CLR4_4_14d4dl9k8v08f.R.inc(189);int lengthOfPartialMessage     = (message.offset + message.length) - endIndex;

        __CLR4_4_14d4dl9k8v08f.R.inc(190);System.arraycopy(message.sharedArray, startIndexOfPartialMessage, this.sharedArray, this.offset, lengthOfPartialMessage);
    }finally{__CLR4_4_14d4dl9k8v08f.R.flushNeeded();}}

    public int writeToByteBuffer(ByteBuffer byteBuffer){try{__CLR4_4_14d4dl9k8v08f.R.inc(191);
        __CLR4_4_14d4dl9k8v08f.R.inc(192);return 0;
    }finally{__CLR4_4_14d4dl9k8v08f.R.flushNeeded();}}



}
