/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

/**
 * Same as QueueFillCount, except that QueueFlip uses a flip flag to keep track of when the internal writePos has
 * "overflowed" (meaning it goes back to 0). Other than that, the two implementations are very similar in functionality.
 *
 * One additional difference is that QueueFlip has an available() method, where this is a public variable in
 * QueueFillCount.
 *
 * Created by jjenkov on 18-09-2015.
 */
public class QueueIntFlip {public static class __CLR4_4_17a7al9k8v09u{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0044\u003a\u005c\u005c\u0053\u006f\u0066\u0074\u0077\u0061\u0072\u0065\u0073\u005c\u005c\u004a\u0061\u0076\u0061\u005c\u005c\u004a\u0061\u0076\u0061\u005f\u0070\u0072\u0061\u0063\u0074\u0069\u0063\u0065\u005c\u005c\u004e\u0049\u004f\u0053\u0065\u0072\u0076\u0065\u0072\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1666462693740L,8589935092L,386,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    public int[] elements = null;

    public int capacity = 0;
    public int writePos = 0;
    public int readPos  = 0;
    public boolean flipped = false;

    public QueueIntFlip(int capacity) {try{__CLR4_4_17a7al9k8v09u.R.inc(262);
        __CLR4_4_17a7al9k8v09u.R.inc(263);this.capacity = capacity;
        __CLR4_4_17a7al9k8v09u.R.inc(264);this.elements = new int[capacity]; //todo get from TypeAllocator ?
    }finally{__CLR4_4_17a7al9k8v09u.R.flushNeeded();}}

    public void reset() {try{__CLR4_4_17a7al9k8v09u.R.inc(265);
        __CLR4_4_17a7al9k8v09u.R.inc(266);this.writePos = 0;
        __CLR4_4_17a7al9k8v09u.R.inc(267);this.readPos  = 0;
        __CLR4_4_17a7al9k8v09u.R.inc(268);this.flipped  = false;
    }finally{__CLR4_4_17a7al9k8v09u.R.flushNeeded();}}

    public int available() {try{__CLR4_4_17a7al9k8v09u.R.inc(269);
        __CLR4_4_17a7al9k8v09u.R.inc(270);if((((!flipped)&&(__CLR4_4_17a7al9k8v09u.R.iget(271)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(272)==0&false))){{
            __CLR4_4_17a7al9k8v09u.R.inc(273);return writePos - readPos;
        }
        }__CLR4_4_17a7al9k8v09u.R.inc(274);return capacity - readPos + writePos;
    }finally{__CLR4_4_17a7al9k8v09u.R.flushNeeded();}}

    public int remainingCapacity() {try{__CLR4_4_17a7al9k8v09u.R.inc(275);
        __CLR4_4_17a7al9k8v09u.R.inc(276);if((((!flipped)&&(__CLR4_4_17a7al9k8v09u.R.iget(277)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(278)==0&false))){{
            __CLR4_4_17a7al9k8v09u.R.inc(279);return capacity - writePos;
        }
        }__CLR4_4_17a7al9k8v09u.R.inc(280);return readPos - writePos;
    }finally{__CLR4_4_17a7al9k8v09u.R.flushNeeded();}}

    public boolean put(int element){try{__CLR4_4_17a7al9k8v09u.R.inc(281);
        __CLR4_4_17a7al9k8v09u.R.inc(282);if((((!flipped)&&(__CLR4_4_17a7al9k8v09u.R.iget(283)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(284)==0&false))){{
            __CLR4_4_17a7al9k8v09u.R.inc(285);if((((writePos == capacity)&&(__CLR4_4_17a7al9k8v09u.R.iget(286)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(287)==0&false))){{
                __CLR4_4_17a7al9k8v09u.R.inc(288);writePos = 0;
                __CLR4_4_17a7al9k8v09u.R.inc(289);flipped = true;

                __CLR4_4_17a7al9k8v09u.R.inc(290);if((((writePos < readPos)&&(__CLR4_4_17a7al9k8v09u.R.iget(291)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(292)==0&false))){{
                    __CLR4_4_17a7al9k8v09u.R.inc(293);elements[writePos++] = element;
                    __CLR4_4_17a7al9k8v09u.R.inc(294);return true;
                } }else {{
                    __CLR4_4_17a7al9k8v09u.R.inc(295);return false;
                }
            }} }else {{
                __CLR4_4_17a7al9k8v09u.R.inc(296);elements[writePos++] = element;
                __CLR4_4_17a7al9k8v09u.R.inc(297);return true;
            }
        }} }else {{
            __CLR4_4_17a7al9k8v09u.R.inc(298);if((((writePos < readPos )&&(__CLR4_4_17a7al9k8v09u.R.iget(299)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(300)==0&false))){{
                __CLR4_4_17a7al9k8v09u.R.inc(301);elements[writePos++] = element;
                __CLR4_4_17a7al9k8v09u.R.inc(302);return true;
            } }else {{
                __CLR4_4_17a7al9k8v09u.R.inc(303);return false;
            }
        }}
    }}finally{__CLR4_4_17a7al9k8v09u.R.flushNeeded();}}

    public int put(int[] newElements, int length){try{__CLR4_4_17a7al9k8v09u.R.inc(304);
        __CLR4_4_17a7al9k8v09u.R.inc(305);int newElementsReadPos = 0;
        __CLR4_4_17a7al9k8v09u.R.inc(306);if((((!flipped)&&(__CLR4_4_17a7al9k8v09u.R.iget(307)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(308)==0&false))){{
            //readPos lower than writePos - free sections are:
            //1) from writePos to capacity
            //2) from 0 to readPos

            __CLR4_4_17a7al9k8v09u.R.inc(309);if((((length <= capacity - writePos)&&(__CLR4_4_17a7al9k8v09u.R.iget(310)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(311)==0&false))){{
                //new elements fit into top of elements array - copy directly
                __CLR4_4_17a7al9k8v09u.R.inc(312);for(; (((newElementsReadPos < length)&&(__CLR4_4_17a7al9k8v09u.R.iget(313)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(314)==0&false)); newElementsReadPos++){{
                    __CLR4_4_17a7al9k8v09u.R.inc(315);this.elements[this.writePos++] = newElements[newElementsReadPos];
                }

                }__CLR4_4_17a7al9k8v09u.R.inc(316);return newElementsReadPos;
            } }else {{
                //new elements must be divided between top and bottom of elements array

                //writing to top
                __CLR4_4_17a7al9k8v09u.R.inc(317);for(;(((this.writePos < capacity)&&(__CLR4_4_17a7al9k8v09u.R.iget(318)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(319)==0&false)); this.writePos++){{
                    __CLR4_4_17a7al9k8v09u.R.inc(320);this.elements[this.writePos] = newElements[newElementsReadPos++];
                }

                //writing to bottom
                }__CLR4_4_17a7al9k8v09u.R.inc(321);this.writePos = 0;
                __CLR4_4_17a7al9k8v09u.R.inc(322);this.flipped  = true;
                __CLR4_4_17a7al9k8v09u.R.inc(323);int endPos = Math.min(this.readPos, length - newElementsReadPos);
                __CLR4_4_17a7al9k8v09u.R.inc(324);for(; (((this.writePos < endPos)&&(__CLR4_4_17a7al9k8v09u.R.iget(325)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(326)==0&false)); this.writePos++){{
                    __CLR4_4_17a7al9k8v09u.R.inc(327);this.elements[writePos] = newElements[newElementsReadPos++];
                }


                }__CLR4_4_17a7al9k8v09u.R.inc(328);return newElementsReadPos;
            }

        }} }else {{
            //readPos higher than writePos - free sections are:
            //1) from writePos to readPos

            __CLR4_4_17a7al9k8v09u.R.inc(329);int endPos = Math.min(this.readPos, this.writePos + length);

            __CLR4_4_17a7al9k8v09u.R.inc(330);for(; (((this.writePos < endPos)&&(__CLR4_4_17a7al9k8v09u.R.iget(331)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(332)==0&false)); this.writePos++){{
                __CLR4_4_17a7al9k8v09u.R.inc(333);this.elements[this.writePos] = newElements[newElementsReadPos++];
            }

            }__CLR4_4_17a7al9k8v09u.R.inc(334);return newElementsReadPos;
        }
    }}finally{__CLR4_4_17a7al9k8v09u.R.flushNeeded();}}


    public int take() {try{__CLR4_4_17a7al9k8v09u.R.inc(335);
        __CLR4_4_17a7al9k8v09u.R.inc(336);if((((!flipped)&&(__CLR4_4_17a7al9k8v09u.R.iget(337)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(338)==0&false))){{
            __CLR4_4_17a7al9k8v09u.R.inc(339);if((((readPos < writePos)&&(__CLR4_4_17a7al9k8v09u.R.iget(340)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(341)==0&false))){{
                __CLR4_4_17a7al9k8v09u.R.inc(342);return elements[readPos++];
            } }else {{
                __CLR4_4_17a7al9k8v09u.R.inc(343);return -1;
            }
        }} }else {{
            __CLR4_4_17a7al9k8v09u.R.inc(344);if((((readPos == capacity)&&(__CLR4_4_17a7al9k8v09u.R.iget(345)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(346)==0&false))){{
                __CLR4_4_17a7al9k8v09u.R.inc(347);readPos = 0;
                __CLR4_4_17a7al9k8v09u.R.inc(348);flipped = false;

                __CLR4_4_17a7al9k8v09u.R.inc(349);if((((readPos < writePos)&&(__CLR4_4_17a7al9k8v09u.R.iget(350)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(351)==0&false))){{
                    __CLR4_4_17a7al9k8v09u.R.inc(352);return elements[readPos++];
                } }else {{
                    __CLR4_4_17a7al9k8v09u.R.inc(353);return -1;
                }
            }} }else {{
                __CLR4_4_17a7al9k8v09u.R.inc(354);return elements[readPos++];
            }
        }}
    }}finally{__CLR4_4_17a7al9k8v09u.R.flushNeeded();}}

    public int take(int[] into, int length){try{__CLR4_4_17a7al9k8v09u.R.inc(355);
        __CLR4_4_17a7al9k8v09u.R.inc(356);int intoWritePos = 0;
        __CLR4_4_17a7al9k8v09u.R.inc(357);if((((!flipped)&&(__CLR4_4_17a7al9k8v09u.R.iget(358)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(359)==0&false))){{
            //writePos higher than readPos - available section is writePos - readPos

            __CLR4_4_17a7al9k8v09u.R.inc(360);int endPos = Math.min(this.writePos, this.readPos + length);
            __CLR4_4_17a7al9k8v09u.R.inc(361);for(; (((this.readPos < endPos)&&(__CLR4_4_17a7al9k8v09u.R.iget(362)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(363)==0&false)); this.readPos++){{
                __CLR4_4_17a7al9k8v09u.R.inc(364);into[intoWritePos++] = this.elements[this.readPos];
            }
            }__CLR4_4_17a7al9k8v09u.R.inc(365);return intoWritePos;
        } }else {{
            //readPos higher than writePos - available sections are top + bottom of elements array

            __CLR4_4_17a7al9k8v09u.R.inc(366);if((((length <= capacity - readPos)&&(__CLR4_4_17a7al9k8v09u.R.iget(367)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(368)==0&false))){{
                //length is lower than the elements available at the top of the elements array - copy directly
                __CLR4_4_17a7al9k8v09u.R.inc(369);for(; (((intoWritePos < length)&&(__CLR4_4_17a7al9k8v09u.R.iget(370)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(371)==0&false)); intoWritePos++){{
                    __CLR4_4_17a7al9k8v09u.R.inc(372);into[intoWritePos] = this.elements[this.readPos++];
                }

                }__CLR4_4_17a7al9k8v09u.R.inc(373);return intoWritePos;
            } }else {{
                //length is higher than elements available at the top of the elements array
                //split copy into a copy from both top and bottom of elements array.

                //copy from top
                __CLR4_4_17a7al9k8v09u.R.inc(374);for(; (((this.readPos < capacity)&&(__CLR4_4_17a7al9k8v09u.R.iget(375)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(376)==0&false)); this.readPos++){{
                    __CLR4_4_17a7al9k8v09u.R.inc(377);into[intoWritePos++] = this.elements[this.readPos];
                }

                //copy from bottom
                }__CLR4_4_17a7al9k8v09u.R.inc(378);this.readPos = 0;
                __CLR4_4_17a7al9k8v09u.R.inc(379);this.flipped = false;
                __CLR4_4_17a7al9k8v09u.R.inc(380);int endPos = Math.min(this.writePos, length - intoWritePos);
                __CLR4_4_17a7al9k8v09u.R.inc(381);for(; (((this.readPos < endPos)&&(__CLR4_4_17a7al9k8v09u.R.iget(382)!=0|true))||(__CLR4_4_17a7al9k8v09u.R.iget(383)==0&false)); this.readPos++){{
                    __CLR4_4_17a7al9k8v09u.R.inc(384);into[intoWritePos++] = this.elements[this.readPos];
                }

                }__CLR4_4_17a7al9k8v09u.R.inc(385);return intoWritePos;
            }
        }}
    }}finally{__CLR4_4_17a7al9k8v09u.R.flushNeeded();}}

}
