package net.mooctest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ShopTest {

	@Test(timeout = 4000)
	public void test1() {
		PrintStream stream = System.out;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		Shop shop = new Shop();
		Product product0 = new Product("a", 188, 100);
		Product product1 = new Product("b", 1, 10);
		Product product2 = new Product("c", 2, 2000);
		Product product3 = new Product("d", 5, 30);
		Product product4 = new Product("e", 20, 100);
		Product product5 = new Product("f", 188, 5);
		Product product6 = new Product("g", 7, 110);

		assertEquals(shop.addProduct(product1), 1, 0.0001);
		assertEquals(shop.addProduct(product2), 2, 0.0001);
		assertEquals(shop.addProduct(product3), 3, 0.0001);
		assertEquals(shop.addProduct(product4), 4, 0.0001);
		assertEquals(shop.addProduct(product5), 5, 0.0001);
		assertEquals(shop.addProduct(product6), 6, 0.0001);
		assertEquals(shop.addProduct(product3), 2, 0.0001);

		System.out.println(shop.getAllProductsInfo());

		assertEquals(shop.deletProduct("b"), 0);
		assertEquals(shop.deletProduct("e"), 3);
		assertEquals(shop.sellProduct("c", 2), 1998);
		assertEquals(shop.sellProduct("d", 30), 30);
		assertEquals(shop.sellProduct("d", 29), 1);

		Product p1 = shop.updateProduct("d", 10, 0.8);
		assertEquals(p1.name, "d");
		assertEquals(p1.price, 10, 0.0001);
		assertEquals(p1.discount, 0.8, 0.0001);
		assertEquals(p1.count, 1);
		Product p2 = shop.updateProduct("c", 30, 0.5);
		assertEquals(p2.name, "c");
		assertEquals(p2.price, 30, 0.0001);
		assertEquals(p2.discount, 0.5, 0.0001);
		assertEquals(p2.count, 1998);

		Product p3 = shop.getProductByName("d");
		assertEquals(p3.name, "d");
		assertEquals(p3.price, 10, 0.0001);
		assertEquals(p3.discount, 0.8, 0.0001);
		assertEquals(p3.count, 1);
		Product p4 = shop.getProductByName("c");
		assertEquals(p4.name, "c");
		assertEquals(p4.price, 30, 0.0001);
		assertEquals(p4.discount, 0.5, 0.0001);
		assertEquals(p4.count, 1998);

		assertNull(shop.getProductByName("awger"));

		try {
			shop.deletProduct("h");
		} catch (Exception e) {
		}
		try {
			shop.sellProduct("d", 1);
		} catch (Exception e) {
		}
		try {
			shop.sellProduct("d", 2);
		} catch (Exception e) {
		}
		try {
			shop.sellProduct("h", 2);
		} catch (Exception e) {
		}
		try {
			shop.sellProduct("d", -1);
		} catch (Exception e) {
		}
		try {
			shop.sellProduct("d", 0);
		} catch (Exception e) {
		}
		try {
			shop.updateProduct("dfs", 1, 3);
		} catch (Exception e) {
		}

		shop.addProduct(product0);
		System.out.println(shop.getAllProductsInfo());

		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setShop(shop);
		Map<String, Integer> map = new HashMap<>();
		Map<String, Integer> map1 = new HashMap<>();
		map.put("a", 3);
		map.put("c", 100);
		map.put("f", 5);
		shopKeeper.sellProducts(map);
		assertEquals(Order.orders.size(), 1);
		shopKeeper.sellProducts(map1);
		shopKeeper.showAllProducts();

		Product product7 = new Product("cc", 100, 100);
		shop.addProduct(product7);
		ShopKeeper shopKeeper1 = new ShopKeeper();
		shopKeeper1.setShop(shop);
		Map<String, Integer> map2 = new HashMap<>();
		map2.put("cc", 3);
		shopKeeper1.sellProducts(map2);
		shopKeeper1.showAllProducts();
		map2.put("cc", 8);
		shopKeeper1.sellProducts(map2);
		shopKeeper1.showAllProducts();

		List<OrderItem> items = new ArrayList<>();
		for (String productName : map.keySet()) {
			Product product = shop.getProductByName(productName);
			items.add(new OrderItem(productName, product.getPaymentPrice(), map.get(productName)));
		}

		Order order = new Order(items);
		Order.printOrders();
		order.printOrderDetails();

		Order order2 = Order.searchMaxOrder();
		assertEquals(order2.getItems().get(0).getProductName(), "a");
		assertEquals(order2.getItems().get(1).getProductName(), "c");
		assertEquals(order2.getItems().get(0).getAmount(), 451.2, 0.001);
		assertEquals(order2.getItems().get(1).getAmount(), 1200, 0.0001);
		assertEquals(order2.getItems().get(0).getPaymentPrice(), 150.4, 0.0001);
		assertEquals(order2.getItems().get(1).getPaymentPrice(), 12.0, 0.0001);
		assertEquals(order2.getItems().get(0).getCount(), 3);

		OrderItem orderItem = order2.getItems().get(1);
		orderItem.setCount(20);
		assertEquals(orderItem.getCount(), 20);
		orderItem.setProductName("aaa");
		assertEquals(orderItem.getProductName(), "aaa");
		orderItem.setPaymentPrice(293.234);
		assertEquals(orderItem.getPaymentPrice(), 293.234, 0.001);

		try {
			product0.setPrice(0);
		} catch (Exception e) {
		}
		try {
			product0.setPrice(-1);
		} catch (Exception e) {
		}
		try {
			product0.setPrice(1.233);
		} catch (Exception e) {
		}
		try {
			product0.setPrice(1.2323);
		} catch (Exception e) {
		}

		product0.setPrice(1.23);
		assertEquals(product0.getPaymentPrice(), 1.23, 0.0001);

		try {
			product0.setDiscount(0);
		} catch (Exception e) {
		}
		try {
			product0.setDiscount(-0.23);
		} catch (Exception e) {
		}
		try {
			product0.setDiscount(1.02);
		} catch (Exception e) {
		}
		try {
			product0.setDiscount(0.989);
		} catch (Exception e) {
		}

		product0.setDiscount(0.98);
		assertEquals(1.2054, product0.getPaymentPrice(), 0.0001);

		try {
			product0.setName("3");
		} catch (Exception e) {
		}
		try {
			product0.setName("`");
		} catch (Exception e) {
		}
		try {
			product0.setName(" ");
		} catch (Exception e) {
		}
		try {
			product0.setName("&");
		} catch (Exception e) {
		}
		try {
			product0.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		} catch (Exception e) {
		}
		try {
			product0.setName("^");
		} catch (Exception e) {
		}
		try {
			product0.setName("|");
		} catch (Exception e) {
		}
		product0.setName("A");

		product0.getInfo().subSequence(5, 6);

		String string = outputStream.toString();

		string = string.replaceAll("\\s", "");

		String string2 = "No.1Name：bPrice：1.00YuanDiscount:NodiscountQuantity：10No.2Name：cPrice："
				+ "2.00YuanDiscount:NodiscountQuantity：2000No.3Name：dPrice：5.00YuanDiscount:NodiscountQuantity："
				+ "60No.4Name：gPrice：7.00YuanDiscount:NodiscountQuantity：110No.5Name：ePrice：20.00YuanDiscount:"
				+ "NodiscountQuantity：100No.6Name：fPrice：188.00YuanDiscount:NodiscountQuantity：5No.1Name：gPrice："
				+ "7.00YuanDiscount:NodiscountQuantity：110No.2Name：dPrice：10.00YuanDiscount:80%Quantity：1No.3Name："
				+ "cPrice：30.00YuanDiscount:50%Quantity：1998No.4Name：aPrice：188.00YuanDiscount:NodiscountQuantity："
				+ "100No.5Name：fPrice：188.00YuanDiscount:NodiscountQuantity：5SelldSuccessfully:a*3SelldSuccessfully:"
				+ "c*100SelldFailed:Quantityshouldlargerthan0:0No.1Name：gPrice：7.00YuanDiscount:NodiscountQuantity："
				+ "110No.2Name：dPrice：10.00YuanDiscount:80%Quantity：1No.3Name：cPrice：30.00YuanDiscount:50%Quantity："
				+ "1898No.4Name：aPrice：188.00YuanDiscount:NodiscountQuantity：97No.5Name：fPrice：188.00YuanDiscount:"
				+ "NodiscountQuantity：5SelldSuccessfully:cc*3No.1Name：gPrice：7.00YuanDiscount:NodiscountQuantity："
				+ "110No.2Name：dPrice：10.00YuanDiscount:80%Quantity：1No.3Name：cPrice：30.00YuanDiscount:50%Quantity："
				+ "1898No.4Name：ccPrice：100.00YuanDiscount:NodiscountQuantity：97No.5Name：aPrice：188.00YuanDiscount:"
				+ "NodiscountQuantity：97No.6Name：fPrice：188.00YuanDiscount:NodiscountQuantity：5SelldSuccessfully:cc*"
				+ "8No.1Name：gPrice：7.00YuanDiscount:NodiscountQuantity：110No.2Name：dPrice：10.00YuanDiscount:80%Quantity："
				+ "1No.3Name：cPrice：30.00YuanDiscount:50%Quantity：1898No.4Name：ccPrice：100.00YuanDiscount:NodiscountQuantity："
				+ "89No.5Name：aPrice：188.00YuanDiscount:NodiscountQuantity：97No.6Name：fPrice：188.00YuanDiscount:Nodiscou"
				+ "ntQuantity：5OrderNo.1a150.403451.20c12.001001200.00OrderTotal:1651.20OrderNo.2cc100.003300.00OrderTotal:3"
				+ "00.00OrderNo.3cc90.008720.00OrderTotal:720.00AllAmount:2671.20a188.003564.00c15.001001500.00f188.005940.00OrderTotal:3004.00";

		string = string.replaceAll("\\p{C}", "");
		string = string.replaceAll("[^0-9]", "");

		string2 = string2.replaceAll("\\p{C}", "");
		string2 = string2.replaceAll("[^0-9]", "");

		assertEquals(string, string2);

		System.setOut(stream);
		
	}
}
