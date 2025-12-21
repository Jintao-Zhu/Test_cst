package net.mooctest;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;

public class ProductTest {

	@BeforeClass
	public static void test1() {
//		PrintStream stream = System.out;
		System.out.println("Testbeforeclass");
	}
	@AfterClass
	public static void test2() {
		System.out.println("TestAfterClass");
	}
	@Before
	public void test3() {
		System.out.println("TestBefore");
	}
	@After
	public void test4() {
		System.out.println("TestAfter");
	}

	// 测试getAllProductsInfo方法
	@Test
	public void testAddProduct() {
		Shop shop = new Shop();
		Product product1 = new Product(6, "56789", "健力宝", 4, 100, ProductEnum.DRINK, 1);
		Product product2 = new Product(7, "123456789", "电吹风", 188, 3, ProductEnum.ELECTRONICS, 1);
		Product product3 = new Product(8, "123456789", "电吹风2", 188, 3, ProductEnum.ELECTRONICS, 1);
		shop.addProduct(product1);
		shop.addProduct(product2);
		int index = shop.addProduct(product3);
		assertEquals(6, index);
		System.out.println(shop.getAllProductsInfo());
	}

	// 测试deletProduct方法
	@Test(expected = NullPointerException.class)
	public void testDeletProduct() {
		Shop shop = new Shop();
		assertEquals(0, shop.deletProduct("电吹风"));
		shop.deletProduct("电吹风2");
	}

	// 测试deletProduct方法
	@Test()
	public void testSellProduct() {
		Shop shop = new Shop();
		int index = shop.sellProduct("电吹风", 1);
		assertEquals(2, index);
	}

	// 测试deletProduct方法
	@Test(expected = IllegalArgumentException.class)
	public void testSellProduct2() {
		Shop shop = new Shop();
		shop.sellProduct("电吹风", -10);
	}
	// 测试deletProduct方法
	@Test(expected = IllegalArgumentException.class)
	public void testSellProduct4() {
		Shop shop = new Shop();
		shop.sellProduct("电吹风", 10);
	}

	// 测试deletProduct方法
	@Test(expected = NullPointerException.class)
	public void testSellProduct3() {
		Shop shop = new Shop();
		shop.sellProduct("电吹风2", 1);
	}

	// 测试updateProduct方法
	@Test(expected = NullPointerException.class)
	public void testUpdateProduct1() {
		Shop shop = new Shop();
		shop.updateProduct("电吹风2", 1, 1);
	}

	// 测试updateProduct方法
	@Test()
	public void testUpdateProduct2() {
		Shop shop = new Shop();
		Product product1 = shop.getProductByName("电吹风");
		Product product = shop.updateProduct("电吹风", 1, 1);
		assertEquals(product1, product);
	}

	// 测试updateProduct方法
	@Test(expected = NullPointerException.class)
	public void testUpdateProduct3() {
		Shop shop = new Shop();
		shop.updateProduct("电吹风2", 1, 1, ProductEnum.ELECTRONICS);
	}

	// 测试updateProduct方法
	@Test()
	public void testUpdateProduct4() {
		Shop shop = new Shop();
		Product product1 = shop.getProductByName("电吹风");
		Product product = shop.updateProduct("电吹风", 1, 1, ProductEnum.ELECTRONICS);
		assertEquals(product1, product);
	}

	// 测试getProductByName方法
	@Test()
	public void testGetProductByName() {
		Shop shop = new Shop();
		shop.getProductByName("test");
	}

	// 测试getProductByName方法
	@Test(expected = IllegalArgumentException.class)
	public void testSetName() {
		Product product = new Product("name", 100, 5);
		product.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}

	// 测试getProductByName方法
	@Test(expected = IllegalArgumentException.class)
	public void testSetPrice() {
		Product product = new Product("name", 100, 5);
		product.setPrice(-5);
	}

	// 测试getProductByName方法
	@Test(expected = IllegalArgumentException.class)
	public void testSetPrice2() {
		Product product = new Product("name", 100, 5);
		product.setPrice(1.12333333);
	}

	// 测试getProductByName方法
	@Test(expected = IllegalArgumentException.class)
	public void testSetCount() {
		Product product = new Product("name", 100, 5);
		product.setCount(-5);
	}

	// 测试getProductByName方法
	@Test(expected = IllegalArgumentException.class)
	public void testSetDiscount() {
		Product product = new Product("name", 100, 5);
		product.setDiscount(-5);
	}

	// 测试getProductByName方法
	@Test(expected = IllegalArgumentException.class)
	public void testSetDiscount2() {
		Product product = new Product("name", 100, 5);
		product.setDiscount(5);
	}

	// 测试getProductByName方法
	@Test(expected = IllegalArgumentException.class)
	public void testSetDiscount3() {
		Product product = new Product("name", 100, 5);
		product.setDiscount(0.5555555555);
	}

	// 测试getProductByName方法
	@Test()
	public void testCreateOrder() {
		OrderItem orderItem1 = new OrderItem("name1", 100, 6);
		OrderItem orderItem2 = new OrderItem("name2", 100, 12);

		List<OrderItem> orderItems1 = new ArrayList<>();
		List<OrderItem> orderItems2 = new ArrayList<>();
		List<OrderItem> orderItems3 = new ArrayList<>();

		Order order1 = new Order(orderItems1);
		order1.getItems();
		Order.createOrder(orderItems1);

		orderItems2.add(orderItem1);
		Order.createOrder(orderItems2);

		orderItems3.add(orderItem2);
		Order.createOrder(orderItems3);
		Order.createOrder(orderItems1);

		Order.printOrders();
		Order.searchMaxOrder();
	}

	@Test
	public void testSellProducts() {
		ShopKeeper shopKeeper = new ShopKeeper();

		Map<String, Integer> products = new HashMap<>();
		products.put("电吹风", 1);
		products.put("矿泉水", 1);
		products.put("冰红茶", 1);

		shopKeeper.sellProducts(products);

	}

	@Test()
	public void testSellProducts2() {
		ShopKeeper shopKeeper = new ShopKeeper();

		Map<String, Integer> products = new HashMap<>();
		products.put("电吹风1", 1);

		try {
			shopKeeper.sellProducts(products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test()
	public void testGetProductName() {
		OrderItem orderItem1 = new OrderItem("name1", 100, 6);
		assertEquals("name1", orderItem1.getProductName());
	}

	@Test()
	public void TestGetCount() {
		OrderItem orderItem1 = new OrderItem("name1", 100, 6);
		assertEquals(6, orderItem1.getCount());
	}

	@Test()
	public void TestFormatDouble() {
		OrderItem.formatDouble(1.11);
	}

	@Test()
	public void TestGetId() {
		Product product1 = new Product(6, "56789", "健力宝", 4, 100, ProductEnum.DRINK, 1);
		assertEquals(6, product1.getId());
	}
	@Test()
	public void TestGetPid() {
		Product product1 = new Product(6, "56789", "健力宝", 4, 100, ProductEnum.DRINK, 1);
		assertEquals("56789", product1.getPid());
	}

	@Test()
	public void TestGetName() {
		Product product1 = new Product(6, "56789", "健力宝", 4, 100, ProductEnum.DRINK, 1);
		assertEquals("健力宝", product1.getName());
	}

	@Test()
	public void TestGetPrice() {
		Product product1 = new Product(6, "56789", "健力宝", 4, 100, ProductEnum.DRINK, 1);
		assertEquals(4, product1.getPrice(), 0.00001);
	}

	@Test()
	public void TestGetCount2() {
		Product product1 = new Product(6, "56789", "健力宝", 4, 100, ProductEnum.DRINK, 1);
		assertEquals(100, product1.getCount());
	}

	@Test()
	public void TestGetProductEnum() {
		Product product1 = new Product(6, "56789", "健力宝", 4, 100, ProductEnum.DRINK, 1);
		assertEquals(ProductEnum.DRINK, product1.getProductEnum());
	}

	@Test()
	public void TestGetDiscount() {
		Product product1 = new Product(6, "56789", "健力宝", 4, 100, ProductEnum.DRINK, 1);
		assertEquals(1, product1.getDiscount(), 0.00001);
	}

	@Test()
	public void TestGetProductEnumName() {
		ProductEnum productEnum = ProductEnum.BOOK;
		assertEquals("书籍", productEnum.getProductEnumName());
	}

	@Test()
	public void TestMain() {
		String[] args = {"test"};
		Shop.main(args);
	}

	@Test()
	public void TestGetAllProductsInfo() {
		Shop shop = new Shop();
		Product product5 = new Product(5, "56789", "健力宝", 4, 100, ProductEnum.DRINK, 1);
		shop.addProduct(product5);
		shop.getAllProductsInfo();

	}

	@Test()
	public void TestShowAllProducts() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.showAllProducts();
	}

	@Test()
	public void TestSellProducts() {
		ShopKeeper shopKeeper = new ShopKeeper();
		Map<String, Integer> products = new HashMap<>();
		shopKeeper.sellProducts(products);
		shopKeeper.showAllProducts();
	}

}
