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
      
    }

}



