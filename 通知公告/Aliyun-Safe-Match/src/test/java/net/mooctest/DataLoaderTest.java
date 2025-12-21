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

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataLoaderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
	}
	@Test
	public void testZhushiUtilAll() {
		assertEquals("AC", ZhushiUtil.removeZhushi("A<!--c1-->B/*c2*/C"));
		assertEquals("Helloorld", ZhushiUtil.removeZhushi1("Hello<!--comment-->World"));
		assertEquals("1", ZhushiUtil.removeZhushi1("1<!--a-->2<!--b-->3"));
		assertEquals("Start <!-- not closed", ZhushiUtil.removeZhushi1("Start <!-- not closed"));
		assertEquals("XYZ", ZhushiUtil.removeZhushi2("X/*aa*/Y/*bb*/Z"));
		String input6 = "ABC /* no end";
		assertEquals(input6, ZhushiUtil.removeZhushi2(input6));
		// ---------- 4. 测试 removeZhushi3：// 注释 ----------
		String input7 =
				"111//comment\n222";
		String expected7 =
				"11122";
		assertEquals(expected7, ZhushiUtil.removeZhushi3(input7));
		// http:// 的误判现象（按照代码逻辑应该删除 // 后到换行）
		String input8 = "Visit http://example.com\nDone";
		String expected8 = "Visit http:one";
		assertEquals(expected8, ZhushiUtil.removeZhushi3(input8));
		// 多行注释
		String input9 =
				"1//a\n2//b\n3";
		String expected9 =
				"1";
		assertEquals(expected9, ZhushiUtil.removeZhushi3(input9));
		// ---------- 5. 无注释 ----------
		String input10 = "NoCommentHere";
		assertEquals(input10, ZhushiUtil.removeZhushi(input10));
		assertEquals(input10, ZhushiUtil.removeZhushi1(input10));
		assertEquals(input10, ZhushiUtil.removeZhushi2(input10));
		assertEquals(input10, ZhushiUtil.removeZhushi3(input10));
	}

	@Test
	public void testXnTool() {
		// Test isBasic
		assertTrue(XnTool.isBasic('a'));
		assertFalse(XnTool.isBasic('中'));
		// Test digit2codepoint
		try {
			assertEquals('a', XnTool.digit2codepoint(0));
			assertEquals('z', XnTool.digit2codepoint(25));
			assertEquals('0', XnTool.digit2codepoint(26));
			assertEquals('9', XnTool.digit2codepoint(35));
		} catch (Exception e) {
			fail("Should not throw exception");
		}

		try {
			XnTool.digit2codepoint(36);
			fail("Should throw exception");
		} catch (Exception e) {
			assertEquals("BAD_INPUT", e.getMessage());
		}
		// Test codepoint2digit
		try {
			assertEquals(26, XnTool.codepoint2digit('0'));
			assertEquals(35, XnTool.codepoint2digit('9'));
			assertEquals(0, XnTool.codepoint2digit('a'));
			assertEquals(25, XnTool.codepoint2digit('z'));
		} catch (Exception e) {
			fail("Should not throw exception");
		}
		try {
			XnTool.codepoint2digit('A'); // 'A' - 'a' < 0? No, 'A' is 65, 'a' is 97. 65-97 = -32.
		} catch (Exception e) {
			assertEquals("BAD_INPUT", e.getMessage());
		}
		// Test encode
		try {
			// Basic string
			assertEquals("abc-", XnTool.encode("abc"));
			assertEquals("fiqs8s", XnTool.encode("中国"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Encode failed");
		}
		// Test decode
		try {
			assertEquals("abc", XnTool.decode("abc-"));
			assertEquals("中国", XnTool.decode("fiqs8s"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Test decode exceptions
		try {
			XnTool.decode("xn--abc"); // Contains non-basic char? No, - is basic.
		} catch (Exception e) {}

		try {
			XnTool.decode("中国"); // Non-basic input
			fail("Should throw exception");
		} catch (Exception e) {
			assertEquals("BAD_INPUT", e.getMessage());
		}
		// Test adapt
		// Just coverage
		XnTool.adapt(100, 2, true);
		XnTool.adapt(100, 2, false);
	}

	@Test
	public void testLoadAllWhiteList() {
		Map<String, String> map = DataLoader.loadAllWhiteList();
		assertNotNull(map);
		assertTrue(map.size() > 0);
		assertTrue(map.containsKey("百度"));
		assertEquals("www.baidu.com", map.get("百度"));
	}

	@Test
	public void testLoadHotWhiteList() {
		Map<String, String> map = DataLoader.loadHotWhiteList();
		assertNotNull(map);
		assertTrue(map.size() > 0);
		assertTrue(map.containsKey("新浪"));
		assertEquals("sina", map.get("新浪"));
	}

	@Test
	public void testLoadDomains() {
		Set<String> domains = DataLoader.loadDomains();
		assertNotNull(domains);
		assertTrue(domains.size() > 0);
		assertTrue(domains.contains("com"));
	}

	@Test
	public void testLoadMapFileNotFound() throws Exception {
		Method method = DataLoader.class.getDeclaredMethod("_loadMap", String.class);
		method.setAccessible(true);
		Map<String, String> map = (Map<String, String>) method.invoke(null, "non_existent_file.csv");
		assertNotNull(map);
		assertTrue(map.isEmpty());
	}

	@Test
	public void testChineseDns() {
		assertEquals("baidu", ChineseDns.getDomainByChineseName("百度"));
		assertEquals("sina", ChineseDns.getDomainByChineseName("新浪"));
		assertNull(ChineseDns.getDomainByChineseName(null));
		assertNull(ChineseDns.getDomainByChineseName("中国早教网"));
		assertNull(ChineseDns.getDomainByChineseName("美橙互联"));
		assertNull(ChineseDns.getDomainByChineseName("建站之星"));
		assertNull(ChineseDns.getDomainByChineseName("ecshop"));
		assertNull(ChineseDns.getDomainByChineseName("机械科学研究总院"));
		assertNull(ChineseDns.getDomainByChineseName("户户通"));
		assertNull(ChineseDns.getDomainByChineseName("某某有限公司"));
		assertNull(ChineseDns.getDomainByChineseName("某某大学"));
		assertEquals("baidu", ChineseDns.getDomainByChineseName("百度官网"));
		assertEquals("baidu", ChineseDns.getDomainByChineseName("百度首页"));
		assertNull(ChineseDns.getDomainByChineseName("A官网"));
		assertNull(ChineseDns.getDomainByChineseName("未知网站"));
	}

	@Test
	public void testPageInfo() {
		// getDomainByHost
		assertEquals("baidu", PageInfo.getDomainByHost("www.baidu.com"));
		assertEquals("sina", PageInfo.getDomainByHost("sina.com.cn"));
		assertEquals("localhost", PageInfo.getDomainByHost("localhost"));
		assertEquals("", PageInfo.getDomainByHost("com")); // com is in domains
		assertEquals("", PageInfo.getDomainByHost(null));
		assertEquals("", PageInfo.getDomainByHost(""));

		// getChineseInHost
		assertEquals("中国", PageInfo.getChineseInHost("xn--fiqs8s.com"));
		assertEquals("", PageInfo.getChineseInHost("www.baidu.com"));
		assertEquals("", PageInfo.getChineseInHost(null));
		assertEquals("", PageInfo.getChineseInHost(""));

		// getCharset
		assertEquals("utf-8", PageInfo.getCharset("<html></html>"));
		assertEquals("utf-8", PageInfo.getCharset("<html><meta charset=\"gbk\"></html>"));
		assertEquals("utf-8", PageInfo.getCharset("<html><meta charset=\"invalid\"></html>"));
	}

	@Test
	public void testDomainConflictChecker() {
		// 1. Host is IP
		PageInfo p1 = new PageInfo("http://127.0.0.1", "<html></html>");
		assertEquals(Result.UNKNOWN, DomainConflictChecker.check(p1));

		// 2. Host is xn--
		PageInfo p2 = new PageInfo("http://xn--fiqs8s.com", "<html></html>");
		assertEquals(Result.UNKNOWN, DomainConflictChecker.check(p2));

		// 3. Host ends with .com
		PageInfo p3 = new PageInfo("http://www.test.com", "<html></html>");
		assertEquals(Result.UNKNOWN, DomainConflictChecker.check(p3));

		// 4. Host is .org (not excluded)
		// domain "test" (4). href domain "test" (4). 4<=4 -> UNKNOWN.
		String htmlSafe = "<html><body><a href='http://www.test.org/index.html'>首页</a></body></html>";
		PageInfo p4 = new PageInfo("http://www.test.org", htmlSafe);
		assertEquals(Result.UNKNOWN, DomainConflictChecker.check(p4));

		// domain "verylongdomain" (14). href domain "short" (5). 14<=5 false. Not equal -> FISH.
		String htmlFish = "<html><body><a href='http://www.short.com/index.html'>首页</a></body></html>";
		PageInfo p5 = new PageInfo("http://www.verylongdomain.org", htmlFish);
		assertEquals(Result.FISH, DomainConflictChecker.check(p5));

		// Ignored domains (qq.com)
		String htmlQq = "<html><body><a href='http://www.qq.com/index.html'>首页</a></body></html>";
		PageInfo p6 = new PageInfo("http://www.test.org", htmlQq);
		assertEquals(Result.UNKNOWN, DomainConflictChecker.check(p6)); // Ignored, so loop finishes -> UNKNOWN

		// Not in SELF_KEYS
		String htmlOther = "<html><body><a href='http://www.other.com/index.html'>Other</a></body></html>";
		PageInfo p7 = new PageInfo("http://www.test.org", htmlOther);
		assertEquals(Result.UNKNOWN, DomainConflictChecker.check(p7)); // Not checked -> UNKNOWN
	}

	@Test
	public void testFishFilter() {
		// moniWellKnown
		// url contains "taobao" -> true
		assertTrue(FishFilter.isFish("http://www.taobao-fake.com", "<html></html>"));

		// domain equals "taobao" -> false (whitelisted in moniWellKnown loop)
		// host "www.taobao.com" ends with .com. domain "taobao" len 6.
		// .com and len <= 7 -> returns false.
		assertFalse(FishFilter.isFish("http://www.taobao.com", "<html></html>"));

		// domain similar to well known: "ta0ba0" (0 vs o)
		assertTrue(FishFilter.isFish("http://www.ta0ba0.com", "<html></html>"));

		// Blacklist domain "1212-tmall"
		assertTrue(FishFilter.isFish("http://www.1212-tmall.com", "<html></html>"));

		// .pw host
		// html contains "淘宝网" -> true
		assertTrue(FishFilter.isFish("http://www.test.pw", "<html>淘宝网</html>"));

		// Host suffix
		// .cn -> false
		assertFalse(FishFilter.isFish("http://www.test.cn", "<html></html>"));

		// Domain length
		// len <= 6 -> true (if not caught by previous checks)
		// "abc.com" -> domain "abc" (3) -> true.
		assertTrue(FishFilter.isFish("http://www.abc.net", "<html></html>")); // .net not excluded

		// "121mai"
		assertFalse(FishFilter.isFish("http://www.121mai.com", "<html></html>"));
	}

	@Test
	public void testDecoder() {
		// decodeBASE64
		String original = "Hello World";
		String encoded = java.util.Base64.getEncoder().encodeToString(original.getBytes(java.nio.charset.StandardCharsets.UTF_8));
		assertEquals(original, Decoder.decodeBASE64(encoded));
		assertNull(Decoder.decodeBASE64(null));
		// Invalid Base64
		assertEquals("InvalidBase64!", Decoder.decodeBASE64("InvalidBase64!")); // Should return original string on exception

		// decodeRot13
		assertEquals("nop", Decoder.decodeRot13("abc"));
		assertEquals("NOP", Decoder.decodeRot13("ABC"));
		assertEquals("abc", Decoder.decodeRot13("nop"));
		assertEquals("ABC", Decoder.decodeRot13("NOP"));
		assertEquals("123", Decoder.decodeRot13("123"));

		// decodeURL
		try {
			String urlEncoded = java.net.URLEncoder.encode("中国", "gb2312");
			assertEquals("中国", Decoder.decodeURL(urlEncoded));
		} catch (Exception e) {
			fail("Encoding failed");
		}
		assertEquals("Invalid%URL", Decoder.decodeURL("Invalid%URL")); // Should return original on exception

		// decodeGzip
		try {
			java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
			java.util.zip.GZIPOutputStream gzipOut = new java.util.zip.GZIPOutputStream(baos);
			gzipOut.write(original.getBytes());
			gzipOut.close();
			byte[] compressed = baos.toByteArray();
			assertEquals(original, Decoder.decodeGzip(compressed));
		} catch (Exception e) {
			fail("Gzip failed");
		}
		assertNull(Decoder.decodeGzip(new byte[]{1, 2, 3})); // Invalid gzip
	}

	@Test
	public void testTextChecker() {
		// Simple dangerous keyword
		assertTrue(TextChecker.isExecutable("eval($_POST['cmd'])"));

		// String concatenation
		assertTrue(TextChecker.isExecutable("ev\" . \"al($_POST['cmd'])"));

		// Base64 decode
		assertTrue(TextChecker.isExecutable("base64_decode('abc')"));

		// Regex match
		assertTrue(TextChecker.isExecutable("function_exists('exec')"));

		// chr() replacement
		// chr(101) -> 'e'. chr(118) -> 'v'. chr(97) -> 'a'. chr(108) -> 'l'.
		// eval
		assertTrue(TextChecker.isExecutable("chr(101).chr(118).chr(97).chr(108)($_POST['cmd'])"));

		// Variable replacement
		// $a='eval'; $a(...)
		assertTrue(TextChecker.isExecutable("$a='eval'; $a($_POST['cmd'])"));

		// Comments removal
		assertTrue(TextChecker.isExecutable("/* comment */ eval($_POST['cmd'])"));

		// Safe text
		assertFalse(TextChecker.isExecutable("echo 'hello world';"));
	}

	@Test
	public void testTitleDivider() {
		// Test getKeyWords
		List<String> keywords = TitleDivider.getKeyWords("支付宝 - 网上支付");
		assertTrue(keywords.contains("支付宝"));

		// Test getWhatIs ("A是B")
		List<String> keywords2 = TitleDivider.getKeyWords("灵风易商是支付宝代理商");
		assertTrue(keywords2.contains("灵风易商"));

		// Test getFirstChineseWords
		List<String> keywords3 = TitleDivider.getKeyWords("重庆市那美品牌管理有限公司");
		assertTrue(keywords3.contains("重庆市那美品牌管理有限公司"));

		// Test split by - or _
		List<String> keywords4 = TitleDivider.getKeyWords("Part1_Part2-Part3");
		// Assuming parts are treated as sentences and processed

		// Test main (coverage)
		TitleDivider.main(new String[]{});
	}

	@Test
	public void testPageKeyChecker() {
		// 1. Keyword match (KEY_TO_DOMAIN)
		// "taobao" -> "taobao" (in DataLoader keys)
		// If html contains "浙B2-20080224" (mapped to taobao in DataLoader)
		// And domain is "taobao" -> GOOD
		PageInfo p1 = new PageInfo("http://www.taobao.com", "<html>浙B2-20080224</html>");
		// domain of www.taobao.com is taobao (if .com is in domains list)
		// DataLoader.loadDomains() loads "com".
		// So domain is "taobao".
		// KEY_TO_DOMAIN has "浙B2-20080224" -> "taobao".
		// Match -> GOOD.
		assertEquals(Result.GOOD, PageKeyChecker.check(p1));

		// Mismatch
		PageInfo p2 = new PageInfo("http://www.fake.com", "<html>浙B2-20080224</html>");
		assertEquals(Result.FISH, PageKeyChecker.check(p2));

		// 2. Title match
		// ChineseDns.getDomainByChineseName("百度") -> "baidu"
		// Title: "百度"
		// Domain: "baidu" -> GOOD
		PageInfo p3 = new PageInfo("http://www.baidu.com", "<html><title>百度</title></html>");
		assertEquals(Result.GOOD, PageKeyChecker.check(p3));

		// Title mismatch
		PageInfo p4 = new PageInfo("http://www.fake.com", "<html><title>百度</title></html>");
		assertEquals(Result.GOOD, PageKeyChecker.check(p4));

		// 3. Copyright match
		// "百度版权所有" -> "百度" -> "baidu"
		PageInfo p5 = new PageInfo("http://www.baidu.com", "<html><body>百度版权所有</body></html>");
		assertEquals(Result.UNKNOWN, PageKeyChecker.check(p5));

		// 4. Content "Welcome" match
		// "欢迎来到百度" -> "百度" -> "baidu"
		PageInfo p6 = new PageInfo("http://www.baidu.com", "<html><body>欢迎来到百度</body></html>");
		assertEquals(Result.GOOD, PageKeyChecker.check(p6));

		// "百度欢迎您"
		PageInfo p6b = new PageInfo("http://www.baidu.com", "<html><body>百度欢迎您</body></html>");
		assertEquals(Result.UNKNOWN, PageKeyChecker.check(p6b));

		// "版权所有：百度"
		PageInfo p5b = new PageInfo("http://www.baidu.com", "<html><body>版权所有：百度</body></html>");
		assertEquals(Result.UNKNOWN, PageKeyChecker.check(p5b));

		// 5. Domain length check in checkByPageKey
		// If domain length <= realDomain length -> GOOD
		// realDomain "baidu" (5).
		// domain "test" (4).
		// "百度" -> "baidu".
		PageInfo p7 = new PageInfo("http://www.test.com", "<html><title>百度</title></html>");
		assertEquals(Result.GOOD, PageKeyChecker.check(p7));

		// 6. Domain ends with realDomain
		// realDomain "baidu".
		// domain "vipbaidu".
		PageInfo p8 = new PageInfo("http://www.vipbaidu.com", "<html><title>百度</title></html>");
		assertEquals(Result.GOOD, PageKeyChecker.check(p8));

		// 7. Unknown result
		PageInfo p9 = new PageInfo("http://www.unknown.com", "<html></html>");
		assertEquals(Result.UNKNOWN, PageKeyChecker.check(p9));
	}

}
