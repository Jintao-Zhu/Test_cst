package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.mooctest.ITClocks;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class ITClocksTest {

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
        assertEquals(619, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Test
    public void testLessThanEquals() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Event event1 = new LeafEvent(1);
        Event event4 = new LeafEvent(0);
        Event event7 = new LeafEvent(-1);
        Event event8 = new LeafEvent(5);
        Event event2 = new NonLeafEvent(0, new LeafEvent(1), new LeafEvent(5));
        Event event5 = new NonLeafEvent(0, new LeafEvent(1), new LeafEvent(0));
        Event event3 = new NonLeafEvent(0, new LeafEvent(2), new LeafEvent(3));
        Event event6 = new NonLeafEvent(0, new LeafEvent(0), new LeafEvent(4));
        Event event9 = new NonLeafEvent(10, new LeafEvent(0), event6);
        Event event91 = new NonLeafEvent(10, new LeafEvent(10), new LeafEvent(10));
        Event event10 = new NonLeafEvent(-10, event9, new LeafEvent(3));
        Event event11 = new NonLeafEvent(1, event1, event2);

        System.out.println(ITClocks.isConcurrent(event1, event2));
        System.out.println(ITClocks.isConcurrent(event2, event1));
        System.out.println(ITClocks.isConcurrent(event3, event2));
        System.out.println(ITClocks.isConcurrent(event2, event3));
        System.out.println(ITClocks.isConcurrent(event3, event4));
        System.out.println(ITClocks.isConcurrent(event4, event3));
        System.out.println(ITClocks.isConcurrent(event1, event4));
        System.out.println(ITClocks.isConcurrent(event4, event1));
        System.out.println(ITClocks.isConcurrent(event1, event1));
        System.out.println(ITClocks.isConcurrent(event2, event2));
        System.out.println(ITClocks.isConcurrent(event9, event91));
        System.out.println(ITClocks.isConcurrent(event91, event9));

        System.out.println(event1.join(event4));
        System.out.println(event1.join(event2));
        System.out.println(event1.equals(1));
        System.out.println(event1.equals(event1));
        System.out.println(event1.equals(event4));
        System.out.println(event1.equals(event7));
        System.out.println(event1.equals(event8));
        System.out.println(event1.equals(event9));
        System.out.println(event1.equals(event10));
        System.out.println(event4.leq(event1));
        System.out.println(event1.hashCode());
        System.out.println(event1.leq(event6));
        System.out.println(event1.getLeft());
        System.out.println(event1.getRight());
        System.out.println(event1.normalize());

        System.out.println(event2.leq(event1));
        System.out.println(event2.leq(event3));
        System.out.println(event2.leq(event4));
        System.out.println(event2.leq(event5));
        System.out.println(event2.leq(event6));
        System.out.println(event2.leq(event7));
        System.out.println(event5.leq(event2));
        System.out.println(event5.leq(event8));
        System.out.println(event5.leq(event9));
        System.out.println(event5.leq(event10));
        System.out.println(event2.join(event3));
        System.out.println(event2.join(event1));
        System.out.println(event2.equals(1));
        System.out.println(event2.equals(event2));
        System.out.println(event2.equals(event3));
        System.out.println(event2.equals(event5));
        System.out.println(event2.equals(event6));
        System.out.println(event2.min());
        System.out.println(event2.max());
        System.out.println(event2.maxDepth());
        System.out.println(event2.lift(1));
        System.out.println(event2.sink(1));
        System.out.println(event2.normalize());
        System.out.println(event9.normalize());
        System.out.println(event10.normalize());

        Stamp stamp1 = new Stamp();
        Stamp stamp2 = new Stamp(new LeafID(2), event1);
        Stamp stamp21 = new Stamp(new LeafID(2), event2);
        Stamp stamp3 = new Stamp(new NonLeafID(new LeafID(0), new LeafID(4)), event2);
        ID id = new LeafID(0);
        ID id1 = new LeafID(1);
        ID id2 = new LeafID(2);

        System.out.println(id1.isOne());
        System.out.println(id.sum(id1));
        try {
            System.out.println(id1.sum(id2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(id.equals(1));
        System.out.println(id.equals(id));
        System.out.println(id.equals(id1));
        System.out.println(id.hashCode());
        System.out.println(id.split());
        System.out.println(id.isOne());
        System.out.println(id.getLeft());
        System.out.println(id.getRight());
        System.out.println(id.normalize());

        Event event = new LeafEvent();
        Stamp stamp4 = new Stamp(id, event);

        System.out.println(stamp1.send());
        System.out.println(stamp1.receive(stamp4));
        System.out.println(stamp1.sync(stamp4));
        System.out.println(stamp1.leq(stamp4));

        System.out.println(ITClocks.lessThanEquals(stamp1, stamp2));
        System.out.println(ITClocks.lessThanEquals(stamp2, stamp1));
        System.out.println(ITClocks.lessThanEquals(stamp3, stamp2));
        System.out.println(ITClocks.lessThanEquals(stamp3, stamp1));
        System.out.println(ITClocks.happensBefore(stamp1, stamp2));
        System.out.println(ITClocks.happensBefore(stamp2, stamp1));

        System.out.println(stamp3.event());
        System.out.println(stamp3.equals(stamp2));
        System.out.println(stamp3.equals(stamp3));
        System.out.println(stamp3.equals(2));
        System.out.println(stamp2.equals(stamp21));
        System.out.println(stamp3.hashCode());

        ID id3 = new NonLeafID(new LeafID(0), new LeafID(4));
        ID id4 = new NonLeafID(new LeafID(1), new LeafID(2));
        ID id5 = new NonLeafID(new LeafID(0), new LeafID(1));
        ID id6 = new NonLeafID(new LeafID(1), new LeafID(0));
        ID id7 = new NonLeafID(new LeafID(1), new LeafID(1));
        ID id8 = new NonLeafID(new LeafID(-1), new LeafID(-1));
        System.out.println(Filler.fill(id3, event1));
        System.out.println(Filler.fill(id4, event2));
        System.out.println(Filler.fill(id5, event2));

        Grower.grow(id5, event1);
        Grower.grow(id6, event1);
        try {
            Grower.grow(id3, event2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Grower.grow(id7, event1);
//        Grower.grow(id7, event11);
        try {
            Grower.grow(new LeafID(1), event3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(id4.equals(id5));
        System.out.println(id4.equals(id6));
        System.out.println(id7.equals(id5));
        System.out.println(id7.equals(id7));
        try {
            System.out.println(id3.sum(id2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(id3.sum(new LeafID(0)));
        System.out.println(id3.normalize());
        System.out.println(id3.split());
        System.out.println(id6.split());
        System.out.println(id6.normalize());
        System.out.println(id7.split());
        System.out.println(id7.normalize());
        System.out.println(new NonLeafID(new LeafID(0), new LeafID(0)).normalize());
        System.out.println(id3.isOne());
        try {
            id3.sum(id5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            id3.sum(id6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            id3.sum(id7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            id3.sum(id2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            id3.sum(id1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Method mtdlift = ITClocks.class.getDeclaredMethod("lift", Event.class);
        mtdlift.setAccessible(true);
        System.out.println(mtdlift.invoke(ITClocks.class, event1));
        System.out.println(mtdlift.invoke(ITClocks.class, event2));
        Method mtdterleft = ITClocks.class.getDeclaredMethod("tryLeft", Event.class);
        mtdterleft.setAccessible(true);
        System.out.println(mtdterleft.invoke(ITClocks.class, event1));
        System.out.println(mtdterleft.invoke(ITClocks.class, event2));
        Method mtdtryright = ITClocks.class.getDeclaredMethod("tryRight", Event.class);
        mtdtryright.setAccessible(true);
        System.out.println(mtdtryright.invoke(ITClocks.class, event1));
        System.out.println(mtdtryright.invoke(ITClocks.class, event2));

    }

}
