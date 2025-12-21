/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import org.junit.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class DiffiTest {static class __CLR4_4_1bfbflpnjvwmz{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_4_1();if(2019101123313948L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.4.1#2019101123313948,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u0043\u003a\u005c\u005c\u006d\u006f\u006f\u0063\u0074\u0065\u0073\u0074\u005c\u005c\u0070\u0072\u006f\u006a\u0065\u0063\u0074\u0073\u005c\u005c\u0038\u0035\u0037\u0033\u005c\u005c\u0031\u0031\u0037\u0038\u0033\u0030\u005c\u005c\u0044\u0069\u0066\u0066\u0069\u005c\u005c\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1701491127793L,8589935092L,570,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_4_1_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

    Option option = new Option(null);
    Option option1 = new Option("a", "b", true, "c", "d");
    Option option2 = new Option("", "b", false,"c", "d" );
    Option option3 = new Option("-java");
    Option option4 = new Option("");
//    Option option = new Option(null);
//    Option option = new Option(null);


    Diffi diffi = new Diffi(option);
    Diffi diffi1 = new Diffi(option1);
    Diffi diffi2 = new Diffi(option2);
    Diffi diffi3 = new Diffi(option3);
    Diffi diffi4 = new Diffi(option4);

    ExtBoolArray eba1= new ExtBoolArray(13);

    ExtBoolArray eba2 = new ExtBoolArray(0);

    ExtBoolArray eba3 = new ExtBoolArray(20);

    String sa1 [] = {"a", "go", ".", "-ac"};

    public DiffiTest() throws FileNotFoundException {try{__CLR4_4_1bfbflpnjvwmz.R.inc(411);
    }finally{__CLR4_4_1bfbflpnjvwmz.R.flushNeeded();}}
    String dir1 = "src/test/resources/1.txt";
    String dir2 = "src/test/resources/2.txt";
    String dir3 = "src/test/resources/3.txt";
    String dir4 = "src/test/resources/output.txt";
    String dir5 = "src/test/resources/4.txt";
    String dir6 = "c/src/test/resources/14.txt";

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException {__CLR4_4_1bfbflpnjvwmz.R.globalSliceStart(getClass().getName(),412);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_4_1zi4lt3bg();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_4_1bfbflpnjvwmz.R.rethrow($CLV_t2$);}finally{__CLR4_4_1bfbflpnjvwmz.R.globalSliceEnd(getClass().getName(),"net.mooctest.DiffiTest.test",__CLR4_4_1_TEST_NAME_SNIFFER.getTestName(),412,$CLV_p$,$CLV_t$);}}private void  __CLR4_4_1zi4lt3bg() throws NoSuchFieldException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException{try{__CLR4_4_1bfbflpnjvwmz.R.inc(412);

        __CLR4_4_1bfbflpnjvwmz.R.inc(413);final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        __CLR4_4_1bfbflpnjvwmz.R.inc(414);PrintStream oldOUT = System.out;
        __CLR4_4_1bfbflpnjvwmz.R.inc(415);System.setOut(new PrintStream(outContent));


        __CLR4_4_1bfbflpnjvwmz.R.inc(416);RelativeString r = StringIncrementor.diff(dir1, dir2, true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(417);RelativeString r2 = StringIncrementor.diff(dir3, dir2, true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(418);RelativeString r3 = StringIncrementor.diff(dir3, dir4, true);

        __CLR4_4_1bfbflpnjvwmz.R.inc(419);FileOutputStream C = new FileOutputStream(dir5);
        __CLR4_4_1bfbflpnjvwmz.R.inc(420);r.bytesToStream(C);
//        try {
//            r.bytesToStream(null);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        __CLR4_4_1bfbflpnjvwmz.R.inc(421);Class<?> c = r.getClass();
        __CLR4_4_1bfbflpnjvwmz.R.inc(422);Method frs = c.getDeclaredMethod("bytessaved");
//		f.setAccessible(true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(423);frs.setAccessible(true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(424);frs.invoke(r); // This will be an array
        __CLR4_4_1bfbflpnjvwmz.R.inc(425);frs = c.getDeclaredMethod("getLength");
//		f.setAccessible(true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(426);frs.setAccessible(true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(427);frs.invoke(r); // This will be an array
        __CLR4_4_1bfbflpnjvwmz.R.inc(428);r.debugPrint();





        __CLR4_4_1bfbflpnjvwmz.R.inc(429);RelativeString rs = new RelativeString();
        __CLR4_4_1bfbflpnjvwmz.R.inc(430);rs.debugPrint();


//        try {
//            System.out.println(option1.getValue());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(option2.getValue());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        __CLR4_4_1bfbflpnjvwmz.R.inc(431);option2.parse("a");
//        option2
        __CLR4_4_1bfbflpnjvwmz.R.inc(432);c = option2.getClass();
        __CLR4_4_1bfbflpnjvwmz.R.inc(433);Field f2 = c.getDeclaredField("mhasArg");
//		f.setAccessible(true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(434);f2.setAccessible(true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(435);f2.set(option2, true); // This will be an array
        __CLR4_4_1bfbflpnjvwmz.R.inc(436);option2.getValue();
        __CLR4_4_1bfbflpnjvwmz.R.inc(437);option4.is("-a");
        __CLR4_4_1bfbflpnjvwmz.R.inc(438);assertTrue(option2.isIsSet());
        __CLR4_4_1bfbflpnjvwmz.R.inc(439);assertTrue(option2.isHasArg());





        //
        __CLR4_4_1bfbflpnjvwmz.R.inc(440);diffi1.parse(sa1);
        __CLR4_4_1bfbflpnjvwmz.R.inc(441);diffi2.parse(sa1);
        __CLR4_4_1bfbflpnjvwmz.R.inc(442);diffi3.parse(sa1);
        //
        __CLR4_4_1bfbflpnjvwmz.R.inc(443);eba1.setTrue(1,2 );
        __CLR4_4_1bfbflpnjvwmz.R.inc(444);assertFalse(eba1.isFalse(1,2 ));
        __CLR4_4_1bfbflpnjvwmz.R.inc(445);assertTrue(eba1.isFalse(2,1 ));
        __CLR4_4_1bfbflpnjvwmz.R.inc(446);assertEquals(11, eba1.left());
        //
        __CLR4_4_1bfbflpnjvwmz.R.inc(447);FileDiffPatch.createPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(448);FileDiffPatch.createPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", false);
        __CLR4_4_1bfbflpnjvwmz.R.inc(449);FileDiffPatch.createPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
        __CLR4_4_1bfbflpnjvwmz.R.inc(450);FileDiffPatch.createPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/3.txt", true);


//        try {
//            FileDiffPatch.createPatch("src/test/resources/111.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        __CLR4_4_1bfbflpnjvwmz.R.inc(451);FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(452);FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", false);
        __CLR4_4_1bfbflpnjvwmz.R.inc(453);FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
        __CLR4_4_1bfbflpnjvwmz.R.inc(454);FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/3.txt", true);

//        try {
//            FileDiffPatch.doPatch("src/test/resources/111.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }



        __CLR4_4_1bfbflpnjvwmz.R.inc(455);StringIncrementor.diff("123", "1231", true);
//        try {
//            StringIncrementor.diff("123", "1231123111231231", true);
//        }catch ( Exception e){
//            System.out.println(e.getMessage());
//        }
        __CLR4_4_1bfbflpnjvwmz.R.inc(456);RelativeString rs1 = StringIncrementor.diff("adasda", "asdasdaadaasdasdaada");
        __CLR4_4_1bfbflpnjvwmz.R.inc(457);RelativeString rs2 = StringIncrementor.diff("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", "asdasdaadaasdasdaada", false);
        __CLR4_4_1bfbflpnjvwmz.R.inc(458);RelativeString rs3 = StringIncrementor.diff("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", "asdasdaadaasdasdaada", true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(459);RelativeString rs4 = StringIncrementor.diff("adadadadaada", "daada", true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(460);Class<?> cc = rs1.getClass();
        __CLR4_4_1bfbflpnjvwmz.R.inc(461);f2 = cc.getDeclaredField("links");
//		f.setAccessible(true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(462);f2.setAccessible(true);
        __CLR4_4_1bfbflpnjvwmz.R.inc(463);Object value = f2.get(rs1);
        __CLR4_4_1bfbflpnjvwmz.R.inc(464);Object value1 = f2.get(rs2);
        __CLR4_4_1bfbflpnjvwmz.R.inc(465);Object value2 = f2.get(rs3);

        __CLR4_4_1bfbflpnjvwmz.R.inc(466);rs1.debugPrint();
        __CLR4_4_1bfbflpnjvwmz.R.inc(467);rs2.debugPrint();
        __CLR4_4_1bfbflpnjvwmz.R.inc(468);rs3.debugPrint();
        __CLR4_4_1bfbflpnjvwmz.R.inc(469);rs4.debugPrint();


        __CLR4_4_1bfbflpnjvwmz.R.inc(470);int sum = 0;
        __CLR4_4_1bfbflpnjvwmz.R.inc(471);Vector<StringLink> maskArray = (Vector<StringLink>) value;
        __CLR4_4_1bfbflpnjvwmz.R.inc(472);Vector<StringLink> maskArray1 = (Vector<StringLink>) value1;
        __CLR4_4_1bfbflpnjvwmz.R.inc(473);Vector<StringLink> maskArray2 = (Vector<StringLink>) value2;
        __CLR4_4_1bfbflpnjvwmz.R.inc(474);for (StringLink stringLink : maskArray) {{
            __CLR4_4_1bfbflpnjvwmz.R.inc(475);sum += stringLink.getLen();
            __CLR4_4_1bfbflpnjvwmz.R.inc(476);sum += stringLink.getPosNew();
            __CLR4_4_1bfbflpnjvwmz.R.inc(477);sum += stringLink.getPosOrig();
        }
        }__CLR4_4_1bfbflpnjvwmz.R.inc(478);for (StringLink stringLink : maskArray1) {{
            __CLR4_4_1bfbflpnjvwmz.R.inc(479);sum += stringLink.getLen();
            __CLR4_4_1bfbflpnjvwmz.R.inc(480);sum += stringLink.getPosNew();
            __CLR4_4_1bfbflpnjvwmz.R.inc(481);sum += stringLink.getPosOrig();
        }
        }__CLR4_4_1bfbflpnjvwmz.R.inc(482);for (StringLink stringLink : maskArray2) {{
            __CLR4_4_1bfbflpnjvwmz.R.inc(483);sum += stringLink.getLen();
            __CLR4_4_1bfbflpnjvwmz.R.inc(484);sum += stringLink.getPosNew();
            __CLR4_4_1bfbflpnjvwmz.R.inc(485);sum += stringLink.getPosOrig();
        }
        }__CLR4_4_1bfbflpnjvwmz.R.inc(486);assertEquals(112, sum);







        __CLR4_4_1bfbflpnjvwmz.R.inc(487);String sp1 = StringIncrementor.patch("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", r, false);
        __CLR4_4_1bfbflpnjvwmz.R.inc(488);String sp2 = StringIncrementor.patch("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", r, true);
//
        __CLR4_4_1bfbflpnjvwmz.R.inc(489);assertEquals("adasdaadasdaadasdaa2.txt", sp1);
        __CLR4_4_1bfbflpnjvwmz.R.inc(490);assertEquals("adasdaadasdaadasdaa2.txt", sp2);
//        try {
//            StringIncrementor.patch("adadadadaada", r, true);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }debugPrint

        __CLR4_4_1bfbflpnjvwmz.R.inc(491);diffi1.addOption(option1);
        __CLR4_4_1bfbflpnjvwmz.R.inc(492);for (Option option5 : diffi2.options) {{
            __CLR4_4_1bfbflpnjvwmz.R.inc(493);System.out.println(option5.getValue());
        }
        }__CLR4_4_1bfbflpnjvwmz.R.inc(494);assertEquals("d", diffi2.options.get(0).getValue());
        __CLR4_4_1bfbflpnjvwmz.R.inc(495);assertEquals("", diffi1.options.get(0).getValue());
        __CLR4_4_1bfbflpnjvwmz.R.inc(496);assertEquals("", diffi1.options.get(1).getValue());
        __CLR4_4_1bfbflpnjvwmz.R.inc(497);assertEquals("", diffi1.options.get(1).getValue());
        __CLR4_4_1bfbflpnjvwmz.R.inc(498);assertEquals("d", diffi1.options.get(0).getDefaultv());
        __CLR4_4_1bfbflpnjvwmz.R.inc(499);assertEquals("b", option1.getBeschriebung());
        __CLR4_4_1bfbflpnjvwmz.R.inc(500);assertEquals("c", option1.getDelim());
        __CLR4_4_1bfbflpnjvwmz.R.inc(501);assertEquals("a", option1.getName());
        __CLR4_4_1bfbflpnjvwmz.R.inc(502);assertEquals("b", option1.getBeschriebung());


        __CLR4_4_1bfbflpnjvwmz.R.inc(503);System.out.println(outContent.toString());
        __CLR4_4_1bfbflpnjvwmz.R.inc(504);assertThat(outContent.toString(), containsString("0 links, 0 absolute chars (8 Bytes ^= --8Bytes)"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(505);assertThat(outContent.toString(), containsString("Debugprint: 1 links, 5 absolute chars (25 Bytes ^= --1Bytes)"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(506);assertThat(outContent.toString(), containsString("{19: 0-->0} @ 0.0%"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(507);assertThat(outContent.toString(), containsString("{20: 36-->0} @ 0.0%"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(508);assertThat(outContent.toString(), containsString("Searching links..."));
        __CLR4_4_1bfbflpnjvwmz.R.inc(509);assertThat(outContent.toString(), containsString("Adding Link: {19: 0-->0}"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(510);assertThat(outContent.toString(), containsString("[2.txt]"));
//        assertThat(outContent.toString(), containsString("Patch successfully applied in 0.0 seconds"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(511);assertThat(outContent.toString(), containsString("Creating index..."));
        __CLR4_4_1bfbflpnjvwmz.R.inc(512);assertThat(outContent.toString(), containsString("Debugprint: 1 links, 5 absolute chars (25 Bytes ^= --1Bytes)"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(513);assertThat(outContent.toString(), containsString("Debugprint: 1 links, 5 absolute chars (25 Bytes ^= --1Bytes)"));
                __CLR4_4_1bfbflpnjvwmz.R.inc(514);assertThat(outContent.toString(), containsString("[asdasdaadaasdasdaada]"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(515);assertThat(outContent.toString(), containsString("(0) {20: 36-->0}"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(516);assertThat(outContent.toString(), containsString("Adding Link: {19: 0-->0}"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(517);assertThat(outContent.toString(), containsString("d"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(518);assertThat(outContent.toString(), containsString("[daada]"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(519);assertThat(outContent.toString(), containsString("[]"));
        __CLR4_4_1bfbflpnjvwmz.R.inc(520);assertThat(outContent.toString(), containsString("Debugprint: 0 links, 5 absolute chars (13 Bytes ^= --8Bytes)"));



        __CLR4_4_1bfbflpnjvwmz.R.inc(521);String [] s = outContent.toString().split("\n");
        __CLR4_4_1bfbflpnjvwmz.R.inc(522);int num = 0;
        __CLR4_4_1bfbflpnjvwmz.R.inc(523);int num1 = 0;
        __CLR4_4_1bfbflpnjvwmz.R.inc(524);int num2 = 0;
        __CLR4_4_1bfbflpnjvwmz.R.inc(525);int num3 = 0;
        __CLR4_4_1bfbflpnjvwmz.R.inc(526);int num4 = 0;
        __CLR4_4_1bfbflpnjvwmz.R.inc(527);int num5 = 0;
        __CLR4_4_1bfbflpnjvwmz.R.inc(528);for (String s1 : s) {{
            __CLR4_4_1bfbflpnjvwmz.R.inc(529);if ((((s1.startsWith("Reading file A..."))&&(__CLR4_4_1bfbflpnjvwmz.R.iget(530)!=0|true))||(__CLR4_4_1bfbflpnjvwmz.R.iget(531)==0&false))) {__CLR4_4_1bfbflpnjvwmz.R.inc(532);num ++;
            }__CLR4_4_1bfbflpnjvwmz.R.inc(533);if ((((s1.startsWith("Reading file B..." ))&&(__CLR4_4_1bfbflpnjvwmz.R.iget(534)!=0|true))||(__CLR4_4_1bfbflpnjvwmz.R.iget(535)==0&false))) {__CLR4_4_1bfbflpnjvwmz.R.inc(536);num1 ++;
            }__CLR4_4_1bfbflpnjvwmz.R.inc(537);if ((((s1.startsWith("Looking for links...") )&&(__CLR4_4_1bfbflpnjvwmz.R.iget(538)!=0|true))||(__CLR4_4_1bfbflpnjvwmz.R.iget(539)==0&false))) {__CLR4_4_1bfbflpnjvwmz.R.inc(540);num2 ++;
            }__CLR4_4_1bfbflpnjvwmz.R.inc(541);if ((((s1.startsWith("Reading Patch..." ))&&(__CLR4_4_1bfbflpnjvwmz.R.iget(542)!=0|true))||(__CLR4_4_1bfbflpnjvwmz.R.iget(543)==0&false))) {__CLR4_4_1bfbflpnjvwmz.R.inc(544);num3 ++;
            }__CLR4_4_1bfbflpnjvwmz.R.inc(545);if ((((s1.startsWith("Searching links...") )&&(__CLR4_4_1bfbflpnjvwmz.R.iget(546)!=0|true))||(__CLR4_4_1bfbflpnjvwmz.R.iget(547)==0&false))) {__CLR4_4_1bfbflpnjvwmz.R.inc(548);num4 ++;
            }__CLR4_4_1bfbflpnjvwmz.R.inc(549);if ((((s1.startsWith("Creating index..." ))&&(__CLR4_4_1bfbflpnjvwmz.R.iget(550)!=0|true))||(__CLR4_4_1bfbflpnjvwmz.R.iget(551)==0&false))) {__CLR4_4_1bfbflpnjvwmz.R.inc(552);num5 ++;
        }}
        }__CLR4_4_1bfbflpnjvwmz.R.inc(553);assertEquals(8, num);
        __CLR4_4_1bfbflpnjvwmz.R.inc(554);assertEquals(4, num1);
        __CLR4_4_1bfbflpnjvwmz.R.inc(555);assertEquals(4, num2);
        __CLR4_4_1bfbflpnjvwmz.R.inc(556);assertEquals(4, num3);
        __CLR4_4_1bfbflpnjvwmz.R.inc(557);assertEquals(24, num4);
        __CLR4_4_1bfbflpnjvwmz.R.inc(558);assertEquals(24, num5);

        __CLR4_4_1bfbflpnjvwmz.R.inc(559);sum = 0;
        __CLR4_4_1bfbflpnjvwmz.R.inc(560);for (StringLink stringLink : maskArray1) {{
            __CLR4_4_1bfbflpnjvwmz.R.inc(561);byte [] a = stringLink.toBytes();
            __CLR4_4_1bfbflpnjvwmz.R.inc(562);for (byte b : a) {{
                __CLR4_4_1bfbflpnjvwmz.R.inc(563);sum += b;
            }
        }}
        }__CLR4_4_1bfbflpnjvwmz.R.inc(564);assertEquals(56, sum);
        __CLR4_4_1bfbflpnjvwmz.R.inc(565);for (StringLink stringLink : maskArray2) {{
            __CLR4_4_1bfbflpnjvwmz.R.inc(566);byte [] a = stringLink.toBytes();
            __CLR4_4_1bfbflpnjvwmz.R.inc(567);for (byte b : a) {{
                __CLR4_4_1bfbflpnjvwmz.R.inc(568);sum += b;
            }
        }}
        }__CLR4_4_1bfbflpnjvwmz.R.inc(569);assertEquals(112, sum);

    }finally{__CLR4_4_1bfbflpnjvwmz.R.flushNeeded();}}

}



