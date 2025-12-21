package net.mooctest;

import org.junit.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class DiffiTest {

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

    public DiffiTest() throws FileNotFoundException {
    }
    String dir1 = "src/test/resources/1.txt";
    String dir2 = "src/test/resources/2.txt";
    String dir3 = "src/test/resources/3.txt";
    String dir4 = "src/test/resources/output.txt";
    String dir5 = "src/test/resources/4.txt";
    String dir6 = "c/src/test/resources/14.txt";

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException {

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOUT = System.out;
        System.setOut(new PrintStream(outContent));


        RelativeString r = StringIncrementor.diff(dir1, dir2, true);
        RelativeString r2 = StringIncrementor.diff(dir3, dir2, true);
        RelativeString r3 = StringIncrementor.diff(dir3, dir4, true);

        FileOutputStream C = new FileOutputStream(dir5);
        r.bytesToStream(C);
//        try {
//            r.bytesToStream(null);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        Class<?> c = r.getClass();
        Method frs = c.getDeclaredMethod("bytessaved");
//		f.setAccessible(true);
        frs.setAccessible(true);
        frs.invoke(r); // This will be an array
        frs = c.getDeclaredMethod("getLength");
//		f.setAccessible(true);
        frs.setAccessible(true);
        frs.invoke(r); // This will be an array
        r.debugPrint();





        RelativeString rs = new RelativeString();
        rs.debugPrint();


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
        option2.parse("a");
//        option2
        c = option2.getClass();
        Field f2 = c.getDeclaredField("mhasArg");
//		f.setAccessible(true);
        f2.setAccessible(true);
        f2.set(option2, true); // This will be an array
        option2.getValue();
        option4.is("-a");
        assertTrue(option2.isIsSet());
        assertTrue(option2.isHasArg());





        //
        diffi1.parse(sa1);
        diffi2.parse(sa1);
        diffi3.parse(sa1);
        //
        eba1.setTrue(1,2 );
        assertFalse(eba1.isFalse(1,2 ));
        assertTrue(eba1.isFalse(2,1 ));
        assertEquals(11, eba1.left());
        //
        FileDiffPatch.createPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", true);
        FileDiffPatch.createPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", false);
        FileDiffPatch.createPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
        FileDiffPatch.createPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/3.txt", true);


//        try {
//            FileDiffPatch.createPatch("src/test/resources/111.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", true);
        FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", false);
        FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
        FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/3.txt", true);

//        try {
//            FileDiffPatch.doPatch("src/test/resources/111.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }



        StringIncrementor.diff("123", "1231", true);
//        try {
//            StringIncrementor.diff("123", "1231123111231231", true);
//        }catch ( Exception e){
//            System.out.println(e.getMessage());
//        }
        RelativeString rs1 = StringIncrementor.diff("adasda", "asdasdaadaasdasdaada");
        RelativeString rs2 = StringIncrementor.diff("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", "asdasdaadaasdasdaada", false);
        RelativeString rs3 = StringIncrementor.diff("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", "asdasdaadaasdasdaada", true);
        RelativeString rs4 = StringIncrementor.diff("adadadadaada", "daada", true);
        Class<?> cc = rs1.getClass();
        f2 = cc.getDeclaredField("links");
//		f.setAccessible(true);
        f2.setAccessible(true);
        Object value = f2.get(rs1);
        Object value1 = f2.get(rs2);
        Object value2 = f2.get(rs3);

        int sum = 0;
        Vector<StringLink> maskArray = (Vector<StringLink>) value;
        Vector<StringLink> maskArray1 = (Vector<StringLink>) value1;
        Vector<StringLink> maskArray2 = (Vector<StringLink>) value2;
        for (StringLink stringLink : maskArray) {
            sum += stringLink.getLen();
            sum += stringLink.getPosNew();
            sum += stringLink.getPosOrig();
        }
        for (StringLink stringLink : maskArray1) {
            sum += stringLink.getLen();
            sum += stringLink.getPosNew();
            sum += stringLink.getPosOrig();
        }
        for (StringLink stringLink : maskArray2) {
            sum += stringLink.getLen();
            sum += stringLink.getPosNew();
            sum += stringLink.getPosOrig();
        }
        assertEquals(112, sum);







        String sp1 = StringIncrementor.patch("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", r, false);
        String sp2 = StringIncrementor.patch("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", r, true);
//
        assertEquals("adasdaadasdaadasdaa2.txt", sp1);
        assertEquals("adasdaadasdaadasdaa2.txt", sp2);
//        try {
//            StringIncrementor.patch("adadadadaada", r, true);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

        diffi1.addOption(option1);
        for (Option option5 : diffi2.options) {
            System.out.println(option5.getValue());
        }
        assertEquals("d", diffi2.options.get(0).getValue());
        assertEquals("", diffi1.options.get(0).getValue());
        assertEquals("", diffi1.options.get(1).getValue());
        assertEquals("", diffi1.options.get(1).getValue());
        assertEquals("d", diffi1.options.get(0).getDefaultv());
        assertEquals("b", option1.getBeschriebung());
        assertEquals("c", option1.getDelim());
        assertEquals("a", option1.getName());
        assertEquals("b", option1.getBeschriebung());


//        System.out.println(outContent.toString());
//        assertThat(outContent.toString(), containsString("0 links, 0 absolute chars (8 Bytes ^= --8Bytes)"));
//        assertThat(outContent.toString(), containsString("Debugprint: 1 links, 5 absolute chars (25 Bytes ^= --1Bytes)"));
//        assertThat(outContent.toString(), containsString("{19: 0-->0} @ 0.0%"));
//        assertThat(outContent.toString(), containsString("{20: 36-->0} @ 0.0%"));
//        assertThat(outContent.toString(), containsString("Searching links..."));
//        assertThat(outContent.toString(), containsString("Adding Link: {19: 0-->0}"));
//        assertThat(outContent.toString(), containsString("[2.txt]"));
//        assertThat(outContent.toString(), containsString("Patch successfully applied in 0.0 seconds"));
//        assertThat(outContent.toString(), containsString("Creating index..."));
//        assertThat(outContent.toString(), containsString("Debugprint: 1 links, 5 absolute chars (25 Bytes ^= --1Bytes)"));
//        assertThat(outContent.toString(), containsString("Debugprint: 1 links, 5 absolute chars (25 Bytes ^= --1Bytes)"));
//        String [] s = outContent.toString().split("\n");
//        int num = 0;
//        int num1 = 0;
//        int num2 = 0;
//        int num3 = 0;
//        int num4 = 0;
//        int num5 = 0;
//        for (String s1 : s) {
//            if (s1.startsWith("Reading file A...")) num ++;
//            if (s1.startsWith("Reading file B..." )) num1 ++;
//            if (s1.startsWith("Looking for links...") ) num2 ++;
//            if (s1.startsWith("Reading Patch..." )) num3 ++;
//            if (s1.startsWith("Searching links...") ) num4 ++;
//            if (s1.startsWith("Creating index..." )) num5 ++;
//        }
//        assertEquals(8, num);
//        assertEquals(4, num1);
//        assertEquals(4, num2);
//        assertEquals(4, num3);
//        assertEquals(24, num4);
//        assertEquals(24, num5);

    }

}



