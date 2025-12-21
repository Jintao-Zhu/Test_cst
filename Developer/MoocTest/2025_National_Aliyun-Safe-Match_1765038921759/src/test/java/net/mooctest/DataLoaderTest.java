package net.mooctest;

/**
 * 
 * 开发者做题前，请仔细阅读以下说明：
 * 
 * 1、该测试类为测试类示例，不要求完全按照该示例类的格式；
 *	    考生可自行创建测试类，类名可自行定义，但需遵循JUnit命名规范，格式为xxxTest.java，提交类似test.java的文件名将因不符合语法而判0分！
 * 
 * 2、所有测试方法放在该顶层类中，不建议再创建内部类。若必需创建内部类，则需检查JUnit对于内部测试类的要求，并添加相关注释，否则将因无法执行而判0分！
 * 
 * 3、本比赛使用jdk1.8+JUnit4，未使用以上版本编写测试用例者，不再接受低分申诉；
 * 
 * 4、不要修改被测代码，若被测代码中包含数据库相关的操作，可使用mock测试；
 * 
 * 5、若测试用例需要生成本地文件，则需要使用相对路径；
 * 
 * 6、建议尽量避免卡点提交答案，尤其是两份报告的zip包。
 * 
 * */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataLoaderTest {

	@Test
	public void loadKeysMapMirrorsCsvContent() {
		Map<String, String> expected = readCsvAsMap("关键词名单.csv");
		Map<String, String> actual = DataLoader.loadKeysMap();
		assertFalse("keys map should not be empty", actual.isEmpty());
		assertEquals("CSV content must be loaded exactly", expected, actual);
		assertEquals("taobao", actual.get("浙网文[2013]0268-027号"));
	}

	@Test
	public void loadAllWhiteListCoversFullDataset() {
		Map<String, String> expected = readCsvAsMap("所有白名单.csv");
		Map<String, String> actual = DataLoader.loadAllWhiteList();
		assertEquals(expected.size(), actual.size());
		assertEquals(expected, actual);
		assertEquals("www.baidu.com", actual.get("百度"));
		assertEquals("qq.com", actual.get("腾讯网"));
	}

	@Test
	public void loadHotWhiteListProvidesDefensiveCopy() {
		Map<String, String> first = DataLoader.loadHotWhiteList();
		assertFalse(first.isEmpty());
		assertEquals("sina", first.get("新浪"));
		first.clear();
		Map<String, String> second = DataLoader.loadHotWhiteList();
		assertFalse("second call should re-load data", second.isEmpty());
		assertEquals("sina", second.get("新浪网"));
	}

	@Test
	public void privateLoadMapHandlesMissingFile() throws Exception {
		Method loadMap = DataLoader.class.getDeclaredMethod("_loadMap", String.class);
		loadMap.setAccessible(true);
		@SuppressWarnings("unchecked")
		Map<String, String> empty = (Map<String, String>) loadMap.invoke(null, "does-not-exist.csv");
		assertTrue("Missing file should return empty map", empty.isEmpty());
	}

	@Test
	public void loadDomainsMatchesDataFile() {
		Set<String> expected = readDomains();
		Set<String> actual = DataLoader.loadDomains();
		assertEquals(expected, actual);
		assertTrue(actual.contains("com"));
		assertTrue(actual.contains("cn"));
	}

	@Test
	public void loadDomainsGracefullyHandlesMissingFile() throws IOException {
		Path domainFile = Paths.get(DataLoader.FISH_BASE_FILE_PATH, "顶级域名.csv");
		assertTrue("Domain file must exist for the test setup", Files.exists(domainFile));
		Path backup = domainFile.resolveSibling(domainFile.getFileName().toString() + ".bak_test");
		if (Files.exists(backup)) {
			Files.delete(backup);
		}
		Files.move(domainFile, backup, StandardCopyOption.REPLACE_EXISTING);
		try {
			Set<String> loaded = DataLoader.loadDomains();
			assertTrue("When file is missing, loadDomains should fall back to empty set", loaded.isEmpty());
		} finally {
			Files.move(backup, domainFile, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	private Map<String, String> readCsvAsMap(String fileName) {
		Path path = Paths.get(DataLoader.FISH_BASE_FILE_PATH, fileName);
		Map<String, String> map = new LinkedHashMap<>();
		try (CSVParser parser = new CSVParser(Files.newBufferedReader(path, StandardCharsets.UTF_8), CSVFormat.DEFAULT)) {
			parser.forEach(record -> map.put(record.get(0), record.get(1)));
		} catch (IOException e) {
			fail("Unable to read test CSV: " + fileName + ", " + e.getMessage());
		}
		return map;
	}

	private Set<String> readDomains() {
		Path path = Paths.get(DataLoader.FISH_BASE_FILE_PATH, "顶级域名.csv");
		Set<String> domains = new LinkedHashSet<>();
		try (CSVParser parser = new CSVParser(Files.newBufferedReader(path, StandardCharsets.UTF_8), CSVFormat.DEFAULT)) {
			parser.forEach(record -> domains.add(record.get(0)));
		} catch (IOException e) {
			fail("Unable to read domain CSV: " + e.getMessage());
		}
		return domains;
	}
}
