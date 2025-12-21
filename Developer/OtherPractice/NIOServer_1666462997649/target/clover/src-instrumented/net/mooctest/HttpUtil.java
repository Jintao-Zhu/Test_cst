/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import java.io.UnsupportedEncodingException;

/**
 * Created by jjenkov on 19-10-2015.
 */
@java.lang.SuppressWarnings({"fallthrough"}) public class HttpUtil {public static class __CLR4_4_1ttl9k8v07k{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0044\u003a\u005c\u005c\u0053\u006f\u0066\u0074\u0077\u0061\u0072\u0065\u0073\u005c\u005c\u004a\u0061\u0076\u0061\u005c\u005c\u004a\u0061\u0076\u0061\u005f\u0070\u0072\u0061\u0063\u0074\u0069\u0063\u0065\u005c\u005c\u004e\u0049\u004f\u0053\u0065\u0072\u0076\u0065\u0072\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666462693740L,8589935092L,157,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    private static final byte[] GET    = new byte[]{'G','E','T'};
    private static final byte[] POST   = new byte[]{'P','O','S','T'};
    private static final byte[] PUT    = new byte[]{'P','U','T'};
    private static final byte[] HEAD   = new byte[]{'H','E','A','D'};
    private static final byte[] DELETE = new byte[]{'D','E','L','E','T','E'};

    private static final byte[] HOST           = new byte[]{'H','o','s','t'};
    private static final byte[] CONTENT_LENGTH = new byte[]{'C','o','n','t','e','n','t','-','L','e','n','g','t','h'};

    public static int parseHttpRequest(byte[] src, int startIndex, int endIndex, HttpHeaders httpHeaders){try{__CLR4_4_1ttl9k8v07k.R.inc(29);


        /*
        int endOfHttpMethod = findNext(src, startIndex, endIndex, (byte) ' ');
        if(endOfHttpMethod == -1) return false;
        resolveHttpMethod(src, startIndex, httpHeaders);
        */

        //parse HTTP request line
        __CLR4_4_1ttl9k8v07k.R.inc(30);int endOfFirstLine = findNextLineBreak(src, startIndex, endIndex);
        __CLR4_4_1ttl9k8v07k.R.inc(31);if((((endOfFirstLine == -1)&&(__CLR4_4_1ttl9k8v07k.R.iget(32)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(33)==0&false))) {__CLR4_4_1ttl9k8v07k.R.inc(34);return -1;


        //parse HTTP headers
        }__CLR4_4_1ttl9k8v07k.R.inc(35);int prevEndOfHeader = endOfFirstLine + 1;
        __CLR4_4_1ttl9k8v07k.R.inc(36);int endOfHeader = findNextLineBreak(src, prevEndOfHeader, endIndex);

        __CLR4_4_1ttl9k8v07k.R.inc(37);while((((endOfHeader != -1 && endOfHeader != prevEndOfHeader + 1)&&(__CLR4_4_1ttl9k8v07k.R.iget(38)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(39)==0&false))){{    //prevEndOfHeader + 1 = end of previous header + 2 (+2 = CR + LF)

            __CLR4_4_1ttl9k8v07k.R.inc(40);if((((matches(src, prevEndOfHeader, CONTENT_LENGTH))&&(__CLR4_4_1ttl9k8v07k.R.iget(41)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(42)==0&false))){{
                __CLR4_4_1ttl9k8v07k.R.inc(43);try {
                    __CLR4_4_1ttl9k8v07k.R.inc(44);findContentLength(src, prevEndOfHeader, endIndex, httpHeaders);
                } catch (UnsupportedEncodingException e) {
                    __CLR4_4_1ttl9k8v07k.R.inc(45);e.printStackTrace();
                }
            }

            }__CLR4_4_1ttl9k8v07k.R.inc(46);prevEndOfHeader = endOfHeader + 1;
            __CLR4_4_1ttl9k8v07k.R.inc(47);endOfHeader = findNextLineBreak(src, prevEndOfHeader, endIndex);
        }

        }__CLR4_4_1ttl9k8v07k.R.inc(48);if((((endOfHeader == -1)&&(__CLR4_4_1ttl9k8v07k.R.iget(49)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(50)==0&false))){{
            __CLR4_4_1ttl9k8v07k.R.inc(51);return -1;
        }

        //check that byte array contains full HTTP message.
        }__CLR4_4_1ttl9k8v07k.R.inc(52);int bodyStartIndex = endOfHeader + 1;
        __CLR4_4_1ttl9k8v07k.R.inc(53);int bodyEndIndex  = bodyStartIndex + httpHeaders.contentLength;

        __CLR4_4_1ttl9k8v07k.R.inc(54);if((((bodyEndIndex <= endIndex)&&(__CLR4_4_1ttl9k8v07k.R.iget(55)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(56)==0&false))){{
            //byte array contains a full HTTP request
            __CLR4_4_1ttl9k8v07k.R.inc(57);httpHeaders.bodyStartIndex = bodyStartIndex;
            __CLR4_4_1ttl9k8v07k.R.inc(58);httpHeaders.bodyEndIndex   = bodyEndIndex;
            __CLR4_4_1ttl9k8v07k.R.inc(59);return bodyEndIndex;
        }


       }__CLR4_4_1ttl9k8v07k.R.inc(60);return -1;
    }finally{__CLR4_4_1ttl9k8v07k.R.flushNeeded();}}

    private static void findContentLength(byte[] src, int startIndex, int endIndex, HttpHeaders httpHeaders) throws UnsupportedEncodingException {try{__CLR4_4_1ttl9k8v07k.R.inc(61);
        __CLR4_4_1ttl9k8v07k.R.inc(62);int indexOfColon = findNext(src, startIndex, endIndex, (byte) ':');

        //skip spaces after colon
        __CLR4_4_1ttl9k8v07k.R.inc(63);int index = indexOfColon +1;
        __CLR4_4_1ttl9k8v07k.R.inc(64);while((((src[index] == ' ')&&(__CLR4_4_1ttl9k8v07k.R.iget(65)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(66)==0&false))){{
            __CLR4_4_1ttl9k8v07k.R.inc(67);index++;
        }

        }__CLR4_4_1ttl9k8v07k.R.inc(68);int valueStartIndex = index;
        __CLR4_4_1ttl9k8v07k.R.inc(69);int valueEndIndex   = index;
        __CLR4_4_1ttl9k8v07k.R.inc(70);boolean endOfValueFound = false;

        __CLR4_4_1ttl9k8v07k.R.inc(71);while((((index < endIndex && !endOfValueFound)&&(__CLR4_4_1ttl9k8v07k.R.iget(72)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(73)==0&false))){{
            boolean __CLB4_4_1_bool0=false;__CLR4_4_1ttl9k8v07k.R.inc(74);switch(src[index]){
                case '0' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(75);__CLB4_4_1_bool0=true;} __CLR4_4_1ttl9k8v07k.R.inc(76);;
                case '1' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(77);__CLB4_4_1_bool0=true;} __CLR4_4_1ttl9k8v07k.R.inc(78);;
                case '2' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(79);__CLB4_4_1_bool0=true;} __CLR4_4_1ttl9k8v07k.R.inc(80);;
                case '3' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(81);__CLB4_4_1_bool0=true;} __CLR4_4_1ttl9k8v07k.R.inc(82);;
                case '4' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(83);__CLB4_4_1_bool0=true;} __CLR4_4_1ttl9k8v07k.R.inc(84);;
                case '5' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(85);__CLB4_4_1_bool0=true;} __CLR4_4_1ttl9k8v07k.R.inc(86);;
                case '6' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(87);__CLB4_4_1_bool0=true;} __CLR4_4_1ttl9k8v07k.R.inc(88);;
                case '7' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(89);__CLB4_4_1_bool0=true;} __CLR4_4_1ttl9k8v07k.R.inc(90);;
                case '8' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(91);__CLB4_4_1_bool0=true;} __CLR4_4_1ttl9k8v07k.R.inc(92);;
                case '9' :if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(93);__CLB4_4_1_bool0=true;} { __CLR4_4_1ttl9k8v07k.R.inc(94);index++;  __CLR4_4_1ttl9k8v07k.R.inc(95);break; }

                default:if (!__CLB4_4_1_bool0) {__CLR4_4_1ttl9k8v07k.R.inc(96);__CLB4_4_1_bool0=true;} {
                    __CLR4_4_1ttl9k8v07k.R.inc(97);endOfValueFound = true;
                    __CLR4_4_1ttl9k8v07k.R.inc(98);valueEndIndex = index;
                }
            }
        }

        }__CLR4_4_1ttl9k8v07k.R.inc(99);httpHeaders.contentLength = Integer.parseInt(new String(src, valueStartIndex, valueEndIndex - valueStartIndex, "UTF-8"));

    }finally{__CLR4_4_1ttl9k8v07k.R.flushNeeded();}}


    public static int findNext(byte[] src, int startIndex, int endIndex, byte value){try{__CLR4_4_1ttl9k8v07k.R.inc(100);
        __CLR4_4_1ttl9k8v07k.R.inc(101);for(int index = startIndex; (((index < endIndex)&&(__CLR4_4_1ttl9k8v07k.R.iget(102)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(103)==0&false)); index++){{
            __CLR4_4_1ttl9k8v07k.R.inc(104);if((((src[index] == value)&&(__CLR4_4_1ttl9k8v07k.R.iget(105)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(106)==0&false))) {__CLR4_4_1ttl9k8v07k.R.inc(107);return index;
        }}
        }__CLR4_4_1ttl9k8v07k.R.inc(108);return -1;
    }finally{__CLR4_4_1ttl9k8v07k.R.flushNeeded();}}

    public static int findNextLineBreak(byte[] src, int startIndex, int endIndex) {try{__CLR4_4_1ttl9k8v07k.R.inc(109);
        __CLR4_4_1ttl9k8v07k.R.inc(110);for(int index = startIndex; (((index < endIndex)&&(__CLR4_4_1ttl9k8v07k.R.iget(111)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(112)==0&false)); index++){{
            __CLR4_4_1ttl9k8v07k.R.inc(113);if((((src[index] == '\n')&&(__CLR4_4_1ttl9k8v07k.R.iget(114)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(115)==0&false))){{
                __CLR4_4_1ttl9k8v07k.R.inc(116);if((((src[index - 1] == '\r')&&(__CLR4_4_1ttl9k8v07k.R.iget(117)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(118)==0&false))){{
                    __CLR4_4_1ttl9k8v07k.R.inc(119);return index;
                }
            }}}__CLR4_4_1ttl9k8v07k.R.inc(120);;
        }
        }__CLR4_4_1ttl9k8v07k.R.inc(121);return -1;
    }finally{__CLR4_4_1ttl9k8v07k.R.flushNeeded();}}

    public static void resolveHttpMethod(byte[] src, int startIndex, HttpHeaders httpHeaders){try{__CLR4_4_1ttl9k8v07k.R.inc(122);
        __CLR4_4_1ttl9k8v07k.R.inc(123);if((((matches(src, startIndex, GET))&&(__CLR4_4_1ttl9k8v07k.R.iget(124)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(125)==0&false))) {{
            __CLR4_4_1ttl9k8v07k.R.inc(126);httpHeaders.httpMethod = HttpHeaders.HTTP_METHOD_GET;
            __CLR4_4_1ttl9k8v07k.R.inc(127);return;
        }
        }__CLR4_4_1ttl9k8v07k.R.inc(128);if((((matches(src, startIndex, POST))&&(__CLR4_4_1ttl9k8v07k.R.iget(129)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(130)==0&false))){{
            __CLR4_4_1ttl9k8v07k.R.inc(131);httpHeaders.httpMethod = HttpHeaders.HTTP_METHOD_POST;
            __CLR4_4_1ttl9k8v07k.R.inc(132);return;
        }
        }__CLR4_4_1ttl9k8v07k.R.inc(133);if((((matches(src, startIndex, PUT))&&(__CLR4_4_1ttl9k8v07k.R.iget(134)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(135)==0&false))){{
            __CLR4_4_1ttl9k8v07k.R.inc(136);httpHeaders.httpMethod = HttpHeaders.HTTP_METHOD_PUT;
            __CLR4_4_1ttl9k8v07k.R.inc(137);return;
        }
        }__CLR4_4_1ttl9k8v07k.R.inc(138);if((((matches(src, startIndex, HEAD))&&(__CLR4_4_1ttl9k8v07k.R.iget(139)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(140)==0&false))){{
            __CLR4_4_1ttl9k8v07k.R.inc(141);httpHeaders.httpMethod = HttpHeaders.HTTP_METHOD_HEAD;
            __CLR4_4_1ttl9k8v07k.R.inc(142);return;
        }
        }__CLR4_4_1ttl9k8v07k.R.inc(143);if((((matches(src, startIndex, DELETE))&&(__CLR4_4_1ttl9k8v07k.R.iget(144)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(145)==0&false))){{
            __CLR4_4_1ttl9k8v07k.R.inc(146);httpHeaders.httpMethod = HttpHeaders.HTTP_METHOD_DELETE;
            __CLR4_4_1ttl9k8v07k.R.inc(147);return;
        }
    }}finally{__CLR4_4_1ttl9k8v07k.R.flushNeeded();}}

    public static boolean matches(byte[] src, int offset, byte[] value){try{__CLR4_4_1ttl9k8v07k.R.inc(148);
        __CLR4_4_1ttl9k8v07k.R.inc(149);for(int i=offset, n=0; (((n < value.length)&&(__CLR4_4_1ttl9k8v07k.R.iget(150)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(151)==0&false)); i++, n++){{
            __CLR4_4_1ttl9k8v07k.R.inc(152);if((((src[i] != value[n])&&(__CLR4_4_1ttl9k8v07k.R.iget(153)!=0|true))||(__CLR4_4_1ttl9k8v07k.R.iget(154)==0&false))) {__CLR4_4_1ttl9k8v07k.R.inc(155);return false;
        }}
        }__CLR4_4_1ttl9k8v07k.R.inc(156);return true;
    }finally{__CLR4_4_1ttl9k8v07k.R.flushNeeded();}}
}
