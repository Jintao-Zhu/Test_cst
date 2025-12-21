package net.mooctest;

import org.junit.*;

import static org.junit.Assert.*;

public class HeapTest {

    @BeforeClass
    public static void test001() {
        System.out.println("BeforeClass Test");
    }

    @Before
    public void test002() {
        System.out.println("Before Test");
    }

    @AfterClass
    public static void test003() {
        System.out.println("AfterClass Test");
    }

    @After
    public void test004() {
        System.out.println("After");
    }

    @Test
    public void testPush() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("heap item");
        Heap_Item<String> heap_item2 = new Heap_Item<>(heap_item1);

        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>();
        assertNull(pairing_heap.getRoot());
        assertEquals("heap item",pairing_heap.push(heap_item1).getData());
        assertEquals("heap item",pairing_heap.push(heap_item2).getData());
    }

    @Test
    public void testPop() {
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>();
        assertNull(pairing_heap.pop());
    }

    @Test
    public void testPop_heap_item() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("heap item");
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        assertEquals("heap item",pairing_heap.pop());
    }

    @Test
    public void testPop_heap_item1() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("heap item");
        Heap_Item<String> heap_item2 = new Heap_Item<>(heap_item1);
        heap_item1.setLeftSon(heap_item2);
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        pairing_heap.pop();
        assertEquals("heap item",pairing_heap.pop());
        assertNull(heap_item1.getLeftSon().getAncestor());
    }

    @Test
    public void testPop_heap_item2() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("heap item1");
        Heap_Item<String> heap_item3 = new Heap_Item<>("heap item3");
        Heap_Item<String> heap_item2 = new Heap_Item<>(heap_item1);
        heap_item1.setLeftSon(heap_item2);
        heap_item1.getLeftSon().setRightSon(heap_item3);
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        assertEquals("heap item1", pairing_heap.pop());
        assertEquals("heap item1", heap_item1.getLeftSon().getLeftSon().getAncestor().getData());
    }

    @Test
    public void testMulti_heaps_merge() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("heap item1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("heap item2");
        Heap_Item<String> heap_item3 = new Heap_Item<>("heap item3");
        Heap_Item<String> heap_item4 = new Heap_Item<>("heap item4");
        heap_item1.setLeftSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        heap_item1.getLeftSon().setRightSon(heap_item3);
        heap_item1.getLeftSon().setLeftSon(heap_item4);
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        assertEquals("heap item1",pairing_heap.pop());
        assertNull(heap_item2.getRightSon());
        assertNull(heap_item2.getAncestor());
        assertEquals("heap item2", heap_item2.getLeftSon().getAncestor().getData());
    }

    @Test
    public void testPair() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("6");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        Heap_Item<String> heap_item3 = new Heap_Item<>("3");
        Heap_Item<String> heap_item4 = new Heap_Item<>("4");
        Heap_Item<String> heap_item5 = new Heap_Item<>("5");
        Heap_Item<String> heap_item6 = new Heap_Item<>("1");
        Heap_Item<String> heap_item7 = new Heap_Item<>("7");
        Heap_Item<String> heap_item8 = new Heap_Item<>("8");
        heap_item1.setLeftSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        heap_item2.setLeftSon(heap_item3);
        heap_item3.setAncestor(heap_item2);
        heap_item2.setRightSon(heap_item4);
        heap_item4.setAncestor(heap_item2);
        Pairing_Heap<String> pairing_heap1 = new Pairing_Heap<>(heap_item1);
        heap_item4.setLeftSon(heap_item5);
        heap_item5.setAncestor(heap_item4);
        heap_item4.setRightSon(heap_item6);
        heap_item6.setAncestor(heap_item4);
        Pairing_Heap<String> pairing_heap2 = new Pairing_Heap<>(heap_item1);
        heap_item6.setLeftSon(heap_item7);
        heap_item7.setAncestor(heap_item6);
        heap_item6.setRightSon(heap_item8);
        heap_item8.setAncestor(heap_item6);
        Pairing_Heap<String> pairing_heap3 = new Pairing_Heap<>(heap_item1);
        assertEquals("6",pairing_heap1.pop());
        assertEquals("6",pairing_heap2.pop());
        assertEquals("6",pairing_heap3.pop());
        assertEquals("1", pairing_heap1.getRoot().getData());
        assertEquals("2", pairing_heap2.getRoot().getData());
        assertEquals("4", heap_item3.getAncestor().getData());
        assertNull(heap_item2.getRightSon());
        assertEquals("4", heap_item5.getAncestor().getData());
        heap_item2.removeChild("3");
        assertEquals("2", pairing_heap3.pop());
        assertEquals("7", heap_item4.getAncestor().getData());
    }

    @Test
    public void testPair2() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("6");
        Heap_Item<String> heap_item2 = new Heap_Item<>("4");
        Heap_Item<String> heap_item3 = new Heap_Item<>("3");
        Heap_Item<String> heap_item4 = new Heap_Item<>("2");
        Heap_Item<String> heap_item5 = new Heap_Item<>("5");
        heap_item1.setLeftSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        heap_item2.setLeftSon(heap_item3);
        heap_item3.setAncestor(heap_item2);
        heap_item2.setRightSon(heap_item4);
        heap_item4.setAncestor(heap_item2);
        heap_item4.setLeftSon(heap_item5);
        heap_item5.setAncestor(heap_item4);
        Pairing_Heap<String> pairing_heap3 = new Pairing_Heap<>(heap_item1);
        assertEquals("6",pairing_heap3.pop());
        assertEquals("4",heap_item4.getLeftSon().getData());
        assertEquals("2",heap_item2.getAncestor().getData());
        assertEquals("5",heap_item2.getRightSon().getData());
        assertEquals("4",heap_item5.getAncestor().getData());
    }

    @Test
    public void testPair3() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("6");
        Heap_Item<String> heap_item2 = new Heap_Item<>("4");
        Heap_Item<String> heap_item3 = new Heap_Item<>("3");
        Heap_Item<String> heap_item4 = new Heap_Item<>("2");
        heap_item1.setLeftSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        heap_item2.setLeftSon(heap_item3);
        heap_item3.setAncestor(heap_item2);
        heap_item2.setRightSon(heap_item4);
        heap_item4.setAncestor(heap_item2);
        Pairing_Heap<String> pairing_heap3 = new Pairing_Heap<>(heap_item1);
        assertEquals("6",pairing_heap3.pop());
        assertEquals("4",heap_item4.getLeftSon().getData());
        assertEquals("2",heap_item2.getAncestor().getData());
    }

    @Test
    public void testPush1() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>();
        assertNull(pairing_heap.getRoot());
        pairing_heap.push(heap_item1);
    }

    @Test
    public void testPeek() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("heap item6");
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        assertEquals("heap item6", pairing_heap.peek());
    }

    @Test
    public void testCheckPriority() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("heap item6");
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        pairing_heap.checkPriority(heap_item1);
    }

    @Test
    public void testCheckPriority1() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("me");
        Heap_Item<String> heap_item2 = new Heap_Item<>("cl");
        Heap_Item<String> heap_item3 = new Heap_Item<>("cr");
        Heap_Item<String> heap_item4 = new Heap_Item<>("f");
        heap_item1.setLeftSon(heap_item2);
        heap_item1.setRightSon(heap_item3);
        heap_item4.setLeftSon(heap_item1);
        heap_item1.setAncestor(heap_item4);
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        pairing_heap.checkPriority(heap_item1);
        assertEquals("cr", heap_item2.getRightSon().getData());
        assertEquals("f", heap_item2.getAncestor().getData());
        assertEquals("cl", heap_item3.getAncestor().getData());
    }

    @Test
    public void testCheckPriority2() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("AA");
        Heap_Item<String> heap_item2 = new Heap_Item<>("cl");
        Heap_Item<String> heap_item3 = new Heap_Item<>("cr");
        heap_item1.setLeftSon(heap_item2);
        heap_item1.setRightSon(heap_item3);
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        pairing_heap.checkPriority(heap_item1);
    }
    @Test
    public void testCheckPriority3() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("me");
        Heap_Item<String> heap_item2 = new Heap_Item<>("cl");
        Heap_Item<String> heap_item3 = new Heap_Item<>("cr");
        heap_item1.setLeftSon(heap_item2);
        heap_item1.setRightSon(heap_item3);
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        pairing_heap.checkPriority(heap_item1);
        assertNull(heap_item1.getLeftSon());
        assertNull(heap_item1.getRightSon());
        assertNull(heap_item2.getAncestor());
        assertNull(heap_item3.getAncestor());
    }

    @Test
    public void testCheckPriority4() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("Me");
        Heap_Item<String> heap_item2 = new Heap_Item<>("ce");
        Heap_Item<String> heap_item3 = new Heap_Item<>("cr");
        Heap_Item<String> heap_item4 = new Heap_Item<>("ff");
        heap_item1.setLeftSon(heap_item2);
        heap_item1.setRightSon(heap_item3);
        heap_item4.setLeftSon(heap_item1);
        heap_item1.setAncestor(heap_item4);
        Pairing_Heap<String> pairing_heap = new Pairing_Heap<>(heap_item1);
        pairing_heap.checkPriority(heap_item1);
        assertEquals("Me", heap_item1.getAncestor().getData());
        assertEquals("cr", heap_item1.getRightSon().getData());
    }

    @Test
    public void testGetMultiWayAncestor() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        assertNull(heap_item1.getMultiWayAncestor());
    }

    @Test
    public void testGetMultiWayAncestor1() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        Heap_Item<String> heap_item3 = new Heap_Item<>("3");
        Heap_Item<String> heap_item4 = new Heap_Item<>("4");
        heap_item1.setRightSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        heap_item1.setAncestor(heap_item3);
        heap_item3.setRightSon(heap_item1);
        heap_item3.setAncestor(heap_item4);
        heap_item4.setLeftSon(heap_item3);
        System.out.println(heap_item2.getMultiWayAncestor());
    }

    @Test
    public void testGetMultiWayAncestor2() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        Heap_Item<String> heap_item3 = new Heap_Item<>("3");
        heap_item1.setRightSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        heap_item1.setAncestor(heap_item3);
        heap_item3.setLeftSon(heap_item1);
        assertEquals("3", heap_item2.getMultiWayAncestor().getData());
    }

    @Test
    public void testGetMultiWayAncestor3() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        heap_item1.setRightSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        assertNull(heap_item1.getMultiWayAncestor());
    }

    @Test
    public void testGetSonByData() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        heap_item1.setRightSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        assertEquals("2", heap_item1.getSonByData("2").getData());
    }

    @Test
    public void testGetSonByData1() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        heap_item1.setLeftSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        assertEquals("2", heap_item1.getSonByData("2").getData());
    }

    @Test
    public void testGetSonByData2() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        heap_item1.setLeftSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        assertNull(heap_item1.getSonByData("3"));
    }


    @Test
    public void testRemoveChild() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        Heap_Item<String> heap_item3 = new Heap_Item<>("3");
        heap_item1.setLeftSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        heap_item1.setRightSon(heap_item3);
        heap_item3.setAncestor(heap_item1);
        heap_item1.removeChild("2");
        heap_item1.removeChild("3");
        assertNull(heap_item1.getLeftSon());
        assertNull(heap_item1.getRightSon());
    }

    @Test
    public void testReplaceChild() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        Heap_Item<String> heap_item3 = new Heap_Item<>("3");
        heap_item1.setRightSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        assertTrue(heap_item1.replaceChild("2", heap_item3));
    }

    @Test
    public void testReplaceChild2() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        Heap_Item<String> heap_item2 = new Heap_Item<>("2");
        Heap_Item<String> heap_item3 = new Heap_Item<>("3");
        heap_item1.setLeftSon(heap_item2);
        heap_item2.setAncestor(heap_item1);
        assertTrue(heap_item1.replaceChild("2", heap_item3));
    }

    @Test
    public void testReplaceChild1() {
        Heap_Item<String> heap_item1 = new Heap_Item<>("1");
        assertFalse(heap_item1.replaceChild("2", heap_item1));
    }


}
