/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

/**
 * A shared buffer which can contain many messages inside. A message gets a section of the buffer to use. If the
 * message outgrows the section in size, the message requests a larger section and the message is copied to that
 * larger section. The smaller section is then freed again.
 *
 *
 * Created by jjenkov on 18-10-2015.
 */
public class MessageBuffer {public static class __CLR4_4_15d5dl9k8v08y{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0044\u003a\u005c\u005c\u0053\u006f\u0066\u0074\u0077\u0061\u0072\u0065\u0073\u005c\u005c\u004a\u0061\u0076\u0061\u005c\u005c\u004a\u0061\u0076\u0061\u005f\u0070\u0072\u0061\u0063\u0074\u0069\u0063\u0065\u005c\u005c\u004e\u0049\u004f\u0053\u0065\u0072\u0076\u0065\u0072\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666462693740L,8589935092L,240,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    public static int KB = 1024;
    public static int MB = 1024 * KB;

    private static final int CAPACITY_SMALL  =   4  * KB;
    private static final int CAPACITY_MEDIUM = 128  * KB;
    private static final int CAPACITY_LARGE  = 1024 * KB;

    //package scope (default) - so they can be accessed from unit tests.
    byte[]  smallMessageBuffer  = new byte[1024 *   4 * KB];   //1024 x   4KB messages =  4MB.
    byte[]  mediumMessageBuffer = new byte[128  * 128 * KB];   // 128 x 128KB messages = 16MB.
    byte[]  largeMessageBuffer  = new byte[16   *   1 * MB];   //  16 *   1MB messages = 16MB.

    QueueIntFlip smallMessageBufferFreeBlocks  = new QueueIntFlip(1024); // 1024 free sections
    QueueIntFlip mediumMessageBufferFreeBlocks = new QueueIntFlip(128);  // 128  free sections
    QueueIntFlip largeMessageBufferFreeBlocks  = new QueueIntFlip(16);   // 16   free sections

    //todo make all message buffer capacities and block sizes configurable
    //todo calculate free block queue sizes based on capacity and block size of buffers.

    public MessageBuffer() {try{__CLR4_4_15d5dl9k8v08y.R.inc(193);
        //add all free sections to all free section queues.
        __CLR4_4_15d5dl9k8v08y.R.inc(194);for(int i=0; (((i<smallMessageBuffer.length)&&(__CLR4_4_15d5dl9k8v08y.R.iget(195)!=0|true))||(__CLR4_4_15d5dl9k8v08y.R.iget(196)==0&false)); i+= CAPACITY_SMALL){{
            __CLR4_4_15d5dl9k8v08y.R.inc(197);this.smallMessageBufferFreeBlocks.put(i);
        }
        }__CLR4_4_15d5dl9k8v08y.R.inc(198);for(int i=0; (((i<mediumMessageBuffer.length)&&(__CLR4_4_15d5dl9k8v08y.R.iget(199)!=0|true))||(__CLR4_4_15d5dl9k8v08y.R.iget(200)==0&false)); i+= CAPACITY_MEDIUM){{
            __CLR4_4_15d5dl9k8v08y.R.inc(201);this.mediumMessageBufferFreeBlocks.put(i);
        }
        }__CLR4_4_15d5dl9k8v08y.R.inc(202);for(int i=0; (((i<largeMessageBuffer.length)&&(__CLR4_4_15d5dl9k8v08y.R.iget(203)!=0|true))||(__CLR4_4_15d5dl9k8v08y.R.iget(204)==0&false)); i+= CAPACITY_LARGE){{
            __CLR4_4_15d5dl9k8v08y.R.inc(205);this.largeMessageBufferFreeBlocks.put(i);
        }
    }}finally{__CLR4_4_15d5dl9k8v08y.R.flushNeeded();}}

    public Message getMessage() {try{__CLR4_4_15d5dl9k8v08y.R.inc(206);
        __CLR4_4_15d5dl9k8v08y.R.inc(207);int nextFreeSmallBlock = this.smallMessageBufferFreeBlocks.take();

        __CLR4_4_15d5dl9k8v08y.R.inc(208);if((((nextFreeSmallBlock == -1)&&(__CLR4_4_15d5dl9k8v08y.R.iget(209)!=0|true))||(__CLR4_4_15d5dl9k8v08y.R.iget(210)==0&false))) {__CLR4_4_15d5dl9k8v08y.R.inc(211);return null;

        }__CLR4_4_15d5dl9k8v08y.R.inc(212);Message message = new Message(this);       //todo get from Message pool - caps memory usage.

        __CLR4_4_15d5dl9k8v08y.R.inc(213);message.sharedArray = this.smallMessageBuffer;
        __CLR4_4_15d5dl9k8v08y.R.inc(214);message.capacity    = CAPACITY_SMALL;
        __CLR4_4_15d5dl9k8v08y.R.inc(215);message.offset      = nextFreeSmallBlock;
        __CLR4_4_15d5dl9k8v08y.R.inc(216);message.length      = 0;

        __CLR4_4_15d5dl9k8v08y.R.inc(217);return message;
    }finally{__CLR4_4_15d5dl9k8v08y.R.flushNeeded();}}

    public boolean expandMessage(Message message){try{__CLR4_4_15d5dl9k8v08y.R.inc(218);
        __CLR4_4_15d5dl9k8v08y.R.inc(219);if((((message.capacity == CAPACITY_SMALL)&&(__CLR4_4_15d5dl9k8v08y.R.iget(220)!=0|true))||(__CLR4_4_15d5dl9k8v08y.R.iget(221)==0&false))){{
            __CLR4_4_15d5dl9k8v08y.R.inc(222);return moveMessage(message, this.smallMessageBufferFreeBlocks, this.mediumMessageBufferFreeBlocks, this.mediumMessageBuffer, CAPACITY_MEDIUM);
        } }else {__CLR4_4_15d5dl9k8v08y.R.inc(223);if((((message.capacity == CAPACITY_MEDIUM)&&(__CLR4_4_15d5dl9k8v08y.R.iget(224)!=0|true))||(__CLR4_4_15d5dl9k8v08y.R.iget(225)==0&false))){{
            __CLR4_4_15d5dl9k8v08y.R.inc(226);return moveMessage(message, this.mediumMessageBufferFreeBlocks, this.largeMessageBufferFreeBlocks, this.largeMessageBuffer, CAPACITY_LARGE);
        } }else {{
            __CLR4_4_15d5dl9k8v08y.R.inc(227);return false;
        }
    }}}finally{__CLR4_4_15d5dl9k8v08y.R.flushNeeded();}}

    private boolean moveMessage(Message message, QueueIntFlip srcBlockQueue, QueueIntFlip destBlockQueue, byte[] dest, int newCapacity) {try{__CLR4_4_15d5dl9k8v08y.R.inc(228);
        __CLR4_4_15d5dl9k8v08y.R.inc(229);int nextFreeBlock = destBlockQueue.take();
        __CLR4_4_15d5dl9k8v08y.R.inc(230);if((((nextFreeBlock == -1)&&(__CLR4_4_15d5dl9k8v08y.R.iget(231)!=0|true))||(__CLR4_4_15d5dl9k8v08y.R.iget(232)==0&false))) {__CLR4_4_15d5dl9k8v08y.R.inc(233);return false;

        }__CLR4_4_15d5dl9k8v08y.R.inc(234);System.arraycopy(message.sharedArray, message.offset, dest, nextFreeBlock, message.length);

        __CLR4_4_15d5dl9k8v08y.R.inc(235);srcBlockQueue.put(message.offset); //free smaller block after copy

        __CLR4_4_15d5dl9k8v08y.R.inc(236);message.sharedArray = dest;
        __CLR4_4_15d5dl9k8v08y.R.inc(237);message.offset      = nextFreeBlock;
        __CLR4_4_15d5dl9k8v08y.R.inc(238);message.capacity    = newCapacity;
        __CLR4_4_15d5dl9k8v08y.R.inc(239);return true;
    }finally{__CLR4_4_15d5dl9k8v08y.R.flushNeeded();}}





}
