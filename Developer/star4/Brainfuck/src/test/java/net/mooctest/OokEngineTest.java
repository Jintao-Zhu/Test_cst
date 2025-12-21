package net.mooctest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.*;
import java.lang.reflect.Field;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.script.ScriptContext;

public class OokEngineTest{

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static PrintStream originalOut;

    @BeforeClass
    public static void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterClass
    public static void tearDownAndVerify() {
        System.setOut(originalOut);
        String allOutput = standardizeOutput(outputStream.toString());
        assertEquals(4498, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Test
    public void testOokEngine() throws Exception {
        OokEngine ookEngine = new OokEngine(1);
        ookEngine.interpret("Ook");
        ookEngine = new OokEngine(10, new PrintStream(System.out));
        ookEngine.interpret("Ookookook");
        ookEngine = new OokEngine(9);
        ookEngine.interpret("Ook. Ook?");
        ookEngine.interpret("Ook? Ook.");
        ookEngine.interpret("-Ook? Ook.");
        ookEngine.interpret("Ook. Ook.");
        ookEngine.interpret("Ook! Ook!");
        ookEngine.interpret("Ook! Ook.");
        ookEngine.interpret("Ook. Ook?Ook? Ook.-Ook? Ook.Ook. Ook.Ook! Ook!Ook! Ook.Ook. Ook?");
        ByteArrayInputStream in = new ByteArrayInputStream("A".getBytes()); // Simulated input 'A'
        ookEngine = new OokEngine(1, System.out, in);
        ookEngine.interpret("Ook. Ook!");
        ookEngine = new OokEngine(10);
        try {
            ookEngine.interpret("Ook. Ook?Ook? Ook.-Ook? Ook.Ook. Ook." +
                    "Ook! Ook!Ook! Ook?Ook! Ook?Ook? Ook!Ook. Ook?");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            ookEngine.interpret("Ook. Ook?Ook? Ook.-Ook? Ook.Ook. Ook." +
                    "Ook! Ook!Ook! Ook?Ook? Ook!Ook. Ook?");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            ookEngine.interpret("Ook. Ook?Ook? Ook.-Ook? Ook.Ook. Ook." +
                    "Ook! Ook!Ook! Ook?Ook? Ook!Ook? Ook!Ook. Ook?");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Field field = OokEngine.class.getSuperclass().getSuperclass().getDeclaredField("data");
        field.setAccessible(true);
        field.set(ookEngine, new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
        ookEngine.interpret("Ook! Ook?");
        ookEngine.interpret("Ook? Ook!");
        Field field1 = OokEngine.class.getSuperclass().getSuperclass().getDeclaredField("charPointer");
        field1.setAccessible(true);
        field1.set(ookEngine, 0);
        try {
            field.set(ookEngine, new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
            ookEngine.interpret("Ook. Ook?Ook? Ook.-Ook? Ook.Ook. Ook.Ook! Ook?Ook. Ook." +
                    "Ook? Ook!Ook! Ook?Ook. Ook?");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testTrollScriptEngine() throws Exception {
        TrollScriptEngine trollScriptEngine = new TrollScriptEngine(3);
        try {
            trollScriptEngine.interpret("tro");
            trollScriptEngine.interpret("ooo");
            trollScriptEngine.interpret("ool");
            trollScriptEngine.interpret("olo");
            trollScriptEngine.interpret("oll");
            trollScriptEngine.interpret("loo");
            trollScriptEngine.interpret("lol");
            trollScriptEngine.interpret("llo");
            trollScriptEngine.interpret("lll");
            trollScriptEngine.interpret("ll.");
            trollScriptEngine.interpret("troooooololoollloollolllll.");
            trollScriptEngine.interpret("abc");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ByteArrayInputStream in = new ByteArrayInputStream("A".getBytes()); // Simulated input 'A'
        trollScriptEngine = new TrollScriptEngine(3, System.out, in);
        trollScriptEngine.interpret("troooolol");
        trollScriptEngine = new TrollScriptEngine(8, System.out);
        try {
            trollScriptEngine.interpret("trollolllooooollloooollo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Field field = TrollScriptEngine.class.getSuperclass().getDeclaredField("data");
        field.setAccessible(true);
        try {
            field.set(trollScriptEngine, new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
            trollScriptEngine.interpret("trollolllooooollloooollo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        field.set(trollScriptEngine, new byte[]{1});
        trollScriptEngine.interpret("trollooooool");
        field.set(trollScriptEngine, new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
        try {
            trollScriptEngine.interpret("troooooololoololllloo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testBrainfuckEngine() throws Exception {
        BrainfuckEngine brainfuckEngine = new BrainfuckEngine(3);
        brainfuckEngine.interpret(">");
        brainfuckEngine.interpret("+");
        brainfuckEngine.interpret("-");
        brainfuckEngine.interpret(".");
        brainfuckEngine.interpret("abc");
        brainfuckEngine.interpret("[>>+<<-]>>[[<+<+>>-]]<<");
        try {
            brainfuckEngine.interpret("<");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            brainfuckEngine.interpret(">>>>>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            brainfuckEngine.interpret("][1]]=]]");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        brainfuckEngine = new BrainfuckEngine(3);
        try {
            brainfuckEngine.interpret(">>>>>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            brainfuckEngine.interpret("][]]]=]]");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        brainfuckEngine = new BrainfuckEngine(3, new PrintStream(System.out));
        Field field = BrainfuckEngine.class.getDeclaredField("data");
        field.setAccessible(true);
        field.set(brainfuckEngine, new byte[]{1, 1, 1});
        brainfuckEngine.interpret("[");
        File tempFile = File.createTempFile("brainfuck_test", ".bf");
        tempFile.deleteOnExit();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("+.");
        }
        brainfuckEngine.interpret(tempFile);
        ByteArrayInputStream in = new ByteArrayInputStream("A".getBytes()); // Simulated input 'A'
        BrainfuckEngine engine = new BrainfuckEngine(10, System.out, in);
        engine.interpret(",");
        System.out.println(engine.data[0]);
    }

}
