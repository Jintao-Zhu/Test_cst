package net.mooctest;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        RelativeString r = StringIncrementor.diff(dir1, dir2, true);
        RelativeString r2 = StringIncrementor.diff(dir3, dir2, true);
        RelativeString r3 = StringIncrementor.diff(dir3, dir4, true);

        FileOutputStream C = new FileOutputStream(dir5);
        r.bytesToStream(C);
        try {
            r.bytesToStream(null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
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


        try {
            System.out.println(option1.getValue());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(option2.getValue());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
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


        try {
            FileDiffPatch.createPatch("src/test/resources/111.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", true);
        FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt", false);
        FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
        FileDiffPatch.doPatch("src/test/resources/1.txt", "src/test/resources/2.txt", "src/test/resources/3.txt", true);

        try {
            FileDiffPatch.doPatch("src/test/resources/111.txt", "src/test/resources/2.txt", "src/test/resources/output.txt");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



        StringIncrementor.diff("123", "1231", true);
        try {
            StringIncrementor.diff("123", "1231123111231231", true);
        }catch ( Exception e){
            System.out.println(e.getMessage());
        }
        StringIncrementor.diff("adasda", "asdasdaadaasdasdaada");
        StringIncrementor.diff("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", "asdasdaadaasdasdaada", false);
        StringIncrementor.diff("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", "asdasdaadaasdasdaada", true);
        StringIncrementor.diff("adadadadaada", "daada", true);




        StringIncrementor.patch("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", r, false);
        StringIncrementor.patch("adasdaadasdaadasdaadasdaadasdaadasdaasdasdaadaasdasdaada", r, true);
//        try {
//            StringIncrementor.patch("adadadadaada", r, true);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }


    }

}



