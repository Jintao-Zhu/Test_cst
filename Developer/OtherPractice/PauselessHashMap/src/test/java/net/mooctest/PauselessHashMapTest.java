package net.mooctest;

import static net.mooctest.PauselessHashMap.SMALLEST_CAPACITY_TO_KICK_OFF_BACKGROUND_RESIZE_FOR;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.mooctest.PauselessHashMap.Resizer;

import java.io.*;
import java.lang.reflect.Field;
import java.net.PasswordAuthentication;
import java.util.*;

public class PauselessHashMapTest {

//	private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//	private static PrintStream originalOut;
//
//	@BeforeClass
//	public static void setUp() {
//		originalOut = System.out;
//		System.setOut(new PrintStream(outputStream));
//	}
//
//	@AfterClass
//	public static void tearDownAndVerify() {
//		System.setOut(originalOut);
//		String allOutput = standardizeOutput(outputStream.toString());
//		assertEquals(174, allOutput.length(), 20);
//	}
//
//	private static String standardizeOutput(String output) {
//		return output
//				.replaceAll("\n", "")
//				.replaceAll("\\s", "")
//				.replaceAll("\\p{C}", "")
//				.replaceAll("[^a-zA-Z0-9]", "");
//	}

	@Test
	public void testPut() {
		PauselessHashMap map = new PauselessHashMap();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		System.out.println(PauselessHashMap.calculateCapacity(1 << 30));
		System.out.println(PauselessHashMap.calculateCapacity(0));
		System.out.println(PauselessHashMap.calculateCapacity(1 << 30 - 1));
		map.clear();
		System.out.println(map.size());
	}
	@Test
	public void testPauselessHashMap() {
		try {
			new PauselessHashMap(-1, -1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			new PauselessHashMap(1, -1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testClear() throws IllegalAccessException, NoSuchFieldException {
		PauselessHashMap map = new PauselessHashMap();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.clone();

		PauselessHashMap.Entry entry = map.findNullKeyEntry();
		System.out.println(entry);

		map.kickoffBackgroundResize(SMALLEST_CAPACITY_TO_KICK_OFF_BACKGROUND_RESIZE_FOR);
		map.clone();

		System.out.println(map.isEmpty());
//		map.pendingResize = true;

//		Field field = PauselessHashMap.class.getDeclaredField("rehashMonitor");
//		field.setAccessible(true);
//		field.set(map, 123);
		map.needToKickOffAnotherBackgroundResize = true;

		map.clear();

		System.out.println(map.isEmpty());

	}
	@Test
	public void testPauselessHashMap1() {
		PauselessHashMap.Entry[] entries = new PauselessHashMap.Entry[1];
		PauselessHashMap map = new PauselessHashMap(1);
		PauselessHashMap map1 = new PauselessHashMap(map);
		map.resizingIntoElementData = entries;
		System.out.println(map.containsValue(1));
		map.put(1, 1);
		map.rehash(1);
//		map.keySet().remove(1);

		System.out.println(map.containsKey(1));
		System.out.println(map.containsValue(1));
		System.out.println(map.containsValue(null));
		entries.clone();
		System.out.println(map.containsValue(2));

	}
	@Test
	public void testPauselessHashMap2() {
		PauselessHashMap.Entry[] entries = new PauselessHashMap.Entry[1];
		PauselessHashMap map = new PauselessHashMap(1);
		map.resizingIntoElementData = entries;
		System.out.println(map.containsValue(0));
		map.put(1, 1);
		System.out.println(map.get(1));
		System.out.println(map.get(null));
		System.out.println(map.get(2));
	}
	@Test
	public void testKeySet() {
		PauselessHashMap.Entry[] entries = new PauselessHashMap.Entry[1];
		PauselessHashMap map = new PauselessHashMap(1);

		Thread thread = new Thread(Resizer.class.getName());
		thread.start();

		map.resizingIntoElementData = entries;
		map.put(1, 1);

		map.backgroundResizeComplete = true;
		map.needToKickOffAnotherBackgroundResize = true;

		map.put(1, 1);
		map.put(2, 2);
		map.kickoffBackgroundResize(SMALLEST_CAPACITY_TO_KICK_OFF_BACKGROUND_RESIZE_FOR);

		Set set = map.keySet();
		Set set1 = map.keySet();
		Iterator iterator = set.iterator();
		System.out.println(set.contains(1));
		System.out.println(set.contains(3));
		System.out.println(set.contains(null));
		set.remove(1);
		set.clear();
		System.out.println(set.size());
		System.out.println(iterator.hasNext());
		set1.remove(1);
	}
	@Test
	public void testPut1() {
		PauselessHashMap.Entry[] entries = new PauselessHashMap.Entry[1];
		PauselessHashMap map = new PauselessHashMap(1);
		map.resizingIntoElementData = entries;
		map.put(null, 1);
		map.put(1, null);
		map.put(1, 2);
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
	}
	@Test
	public void testBuild() {
		PauselessHashMap map = new PauselessHashMap();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		Map map1 = new HashMap();
		map1.put(1, 1);
		map1.put(2, 2);
		map1.put(3, 3);
		map.putAll(map1);
		map.putAll(new HashMap());
		map.remove(1);
		map.removeEntry(1);
	}
	@Test
	public void testResizer() {
		PauselessHashMap map = new PauselessHashMap();
		Resizer resizer = map.new Resizer(map, 2, false);
		Resizer resizer1 = map.new Resizer(map, 1, true);
		new Thread(resizer, "Resizer1").start();
		new Thread(resizer1, "Resizer3").start();
		new Thread(resizer, "Resizer2").start();
		new Thread(resizer1, "Resizer4").start();

		new Thread(resizer, "Resizer5").getState();
		new Thread(resizer, "Resizer5").currentThread().getPriority();
		new Thread(resizer, "Resizer5").currentThread().setPriority(1);

	}
	@Test
	public void testForceFinishResizing() {
		Map.Entry entry = new HashMap.SimpleEntry(1, 1);
		PauselessHashMap map = new PauselessHashMap(3,4);
		PauselessHashMap map1 = new PauselessHashMap(3,4);
		PauselessHashMap.HashMapEntrySet set = new PauselessHashMap.HashMapEntrySet(map);
		set.remove(entry);
		System.out.println(set.size());
		System.out.println(set.contains(entry));
		System.out.println(set.contains(1));
		PauselessHashMap.Entry entries = map.removeEntry(entry);
//		map1.removeEntry(map.getEntry(entry.getKey()));
	}
	@Test
	public void testForceFinishResizing1() {
		Map.Entry entry = new HashMap.SimpleEntry(1, 1);
		PauselessHashMap map = new PauselessHashMap(3,4);
		PauselessHashMap.HashMapEntrySet set = new PauselessHashMap.HashMapEntrySet(map);
//		map.removeEntry(null);
	}
	@Test
	public void testClone() throws IOException {
		PauselessHashMap map = new PauselessHashMap(1);
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.forceFinishResizing();
		map.createEntry(1, 1, '1');
		map.createEntry(null, 1, 1);
		map.resizingIntoElementData = new PauselessHashMap.Entry[1];
		map.putAllImpl(new HashMap());
		map.backgroundResizeComplete = true;
		map.putAllImpl(new HashMap());

		map.remove(1);
		map.pendingResize = true;
		map.remove(1);
		map.removeEntry(new PauselessHashMap.Entry[1]);

		Collection collection = map.values();
		System.out.println(collection.contains(1));
		System.out.println(collection.size());
		collection.clear();
		System.out.println(collection.iterator());

		map.kickoffBackgroundResize(SMALLEST_CAPACITY_TO_KICK_OFF_BACKGROUND_RESIZE_FOR);

		PauselessHashMap.EntryArrayHelper helper = new PauselessHashMap.EntryArrayHelper();
		System.out.println(helper.calculateShiftForScale(8));
		try {
			helper.calculateShiftForScale(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testInternalMapEntry() throws IOException, ClassNotFoundException {

		PauselessHashMap map = new PauselessHashMap(1);
		PauselessHashMap.InternalMapEntry entry = new PauselessHashMap.InternalMapEntry(1, 1);

		map.put(1, 2);
		map.put(2, 1);
		map.put(3, 3);

		//序列化
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/test/resources/obj.txt"));
		//调用自定义的writeObject()方法
		out.writeObject(map);
		out.close();

//		map.writeObject(out);

		entry.setValue(2);
		entry.getKey();
		entry.getValue();
		entry.setValue(1);
		entry.equals(entry);
		entry.clone();
		entry.equals(new PauselessHashMap.InternalMapEntry(1, 1));
		entry.equals(new PauselessHashMap.InternalMapEntry(1, 2));
		entry.equals(new PauselessHashMap.InternalMapEntry(2, 1));
		entry.equals(new PauselessHashMap.InternalMapEntry(2, 2));
		entry.equals(1);
		System.out.println(entry.hashCode());
		System.out.println(entry.toString());


		//反序列化
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/test/resources/obj.txt"));
		//调用自定义的readObject()方法
		PauselessHashMap map1 = (PauselessHashMap) in.readObject();
		in.close();

		System.out.println("size" + map1.size());

		map.removeEntry(entry);
	}

	@Test
	public void testClear1() {
		PauselessHashMap map = new PauselessHashMap();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.kickoffBackgroundResize(SMALLEST_CAPACITY_TO_KICK_OFF_BACKGROUND_RESIZE_FOR);
		map.clear();
	}
	@Test
	public void testRehash() {
		PauselessHashMap map = new PauselessHashMap();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.needToKickOffAnotherBackgroundResize = true;
		map.pendingResize = true;
		map.rehash();
		map.rehash(1);
		map.needToKickOffAnotherBackgroundResize = false;
		map.pendingResize = true;
		map.rehash();
		map.rehash(1);
		map.needToKickOffAnotherBackgroundResize = true;
		map.pendingResize = false;
		map.rehash();
		map.rehash(1);
		map.needToKickOffAnotherBackgroundResize = false;
		map.pendingResize = false;
		map.rehash();
		map.rehash(1);
	}
	@Test
	public void testEqual() {
		System.out.println(PauselessHashMap.areEqualKeys(1, 1));
		System.out.println(PauselessHashMap.areEqualKeys(1, 2));
		System.out.println(PauselessHashMap.areEqualKeys(new Character('A'), new Character('A')));
		System.out.println(PauselessHashMap.areEqualKeys(new Character('A'), new Character('B')));
		System.out.println(PauselessHashMap.areEqualValues(new Character('A'), new Character('A')));
		System.out.println(PauselessHashMap.areEqualValues(new Character('A'), new Character('B')));
		System.out.println(PauselessHashMap.areEqualValues(1, 1));
		System.out.println(PauselessHashMap.areEqualValues(1, 2));
	}
	@Test
	public void testKeyIterator() {
		PauselessHashMap map = new PauselessHashMap();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		PauselessHashMap.KeyIterator keyIterator =
				new PauselessHashMap.KeyIterator(map);
		PauselessHashMap.KeyIterator keyIterator1 =
				new PauselessHashMap.KeyIterator(new PauselessHashMap<>());
		keyIterator.next();
		System.out.println(keyIterator1.hasNext());
		try {
			keyIterator.remove();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			keyIterator1.remove();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testValueIterator() {
		PauselessHashMap map = new PauselessHashMap();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		PauselessHashMap.ValueIterator valueIterator =
				new PauselessHashMap.ValueIterator(map);
		PauselessHashMap.ValueIterator valueIterator1 =
				new PauselessHashMap.ValueIterator(new PauselessHashMap());
		valueIterator.next();
		System.out.println(valueIterator1.hasNext());
		try {
			valueIterator.remove();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			valueIterator1.remove();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testHashMapEntrySet() {
		PauselessHashMap map = new PauselessHashMap();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		PauselessHashMap.HashMapEntrySet set =
				new PauselessHashMap.HashMapEntrySet(map);
		PauselessHashMap.HashMapEntrySet set1 =
				new PauselessHashMap.HashMapEntrySet(new PauselessHashMap());
		set.remove(1);
		map.kickoffBackgroundResize(SMALLEST_CAPACITY_TO_KICK_OFF_BACKGROUND_RESIZE_FOR);
		set1.remove(1);
		System.out.println(set.size());
		System.out.println(set1.size());
		System.out.println(set.contains(1));
		System.out.println(set1.contains(1));
		System.out.println(set.contains(new HashMap.SimpleEntry(1, 1)));
		System.out.println(set1.contains(new HashMap.SimpleEntry(1, 1)));
		System.out.println(set.contains(new HashMap.SimpleEntry(1, 2)));
		System.out.println(set1.contains(new HashMap.SimpleEntry(1, 2)));
		System.out.println(set.contains(new HashMap.SimpleEntry(2, 1)));
		System.out.println(set1.contains(new HashMap.SimpleEntry(2, 1)));
		System.out.println(set.contains(new HashMap.SimpleEntry(2, 2)));
		System.out.println(set1.contains(new HashMap.SimpleEntry(2, 2)));
		System.out.println(set.hashMap());
		set.clear();
		set1.clear();
	}

}
