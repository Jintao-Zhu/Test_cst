package net.mooctest;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HotelTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeClass
    public static void Test1() {
        System.out.println("Test before class");
    }

    @Before
    public void setUp() {
        // 初始化酒店房间数据
        System.out.println("Test before");
        Hotel.rooms.clear(); // 清空列表，确保每次测试数据干净
    }

    @After
    public void test2() {
        System.out.println("Test after");
    }

    @AfterClass
    public static void test3() {
        System.out.println("Test after class");
    }

    @Test
    public void testAddRoom() {
        Hotel.addRoom("Deluxe Room", 101, 3);
        assertEquals(1, Hotel.rooms.size());
        Room room = Hotel.rooms.get(0);
        assertEquals(101, room.getRoomCode());
        assertEquals("Deluxe Room", room.getType());
        assertEquals(3, room.getCapacity());
        assertEquals(280.0, room.getPrice(), 0.01); // 检查价格是否计算正确
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExistingRoom() {
        Hotel.addRoom("Standard Room", 102, 2);
        Hotel.addRoom("Standard Room", 102, 2); // 这行应抛出异常
    }

    @Test
    public void testQueryRoom() {
        Hotel.addRoom("Advanced Room", 102, 2);
        Hotel.addRoom("Advanced Room", 103, 2);
        Hotel.addRoom("Advanced Room", 104, 2);
        Hotel.queryRoom(103); // 打印房间信息
        assertEquals(102, Hotel.rooms.get(0).getRoomCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryNonExistentRoom() {
        Hotel.queryRoom(999); // 房间不存在，应该抛出异常
    }

    @Test
    public void testGetFreeRooms() {
        Hotel.addRoom("Deluxe Room", 201, 3);
        Hotel.addRoom("Standard Room", 203, 1);
        Hotel.addRoom("Advanced Room", 202, 4);
        Hotel.addRoom("Deluxe Room", 102, 3);
        Hotel.addRoom("Standard Room", 205, 1);
        RoomState booked = new BookedState();
        RoomState checkin = new BookedState();
        Hotel.rooms.get(0).setState(booked);
        Hotel.rooms.get(1).setState(checkin);
        List<Room> freeRooms = Hotel.getFreeRooms();
        assertEquals(3, freeRooms.size()); // 应该有两个空闲房间
    }

    @Test
    public void testRemoveRoomFromList() {
        Hotel.addRoom("Standard Room", 301, 2);
        Hotel.addRoom("Standard Room", 302, 2);
        Hotel.addRoom("Standard Room", 303, 2);
        Hotel.removeRoomFromList(302);
        assertEquals(2, Hotel.rooms.size());
        assertEquals(301, Hotel.rooms.get(0).getRoomCode());
    }

    @Test
    public void testPrintAllRoomsInfo() {
        System.setOut(new PrintStream(outputStream));
        Hotel.addRoom("Standard Room", 201, 3);
        Hotel.addRoom("Advanced Room", 202, 2);
        Hotel.addRoom("Deluxe Room", 203, 1);
        Hotel.printAllRoomsInfo();
        String output = outputStream.toString().trim();
        String expectedOutput = "RoomCode: 202\r\n" +
                "RoomType: Advanced Room\r\n" +
                "Capacity: 2\r\n" +
                "RoomState: Free\r\n" +
                "Price: 180.0\r\n" +
                "RoomCode: 203\r\n" +
                "RoomType: Deluxe Room\r\n" +
                "Capacity: 1\r\n" +
                "RoomState: Free\r\n" +
                "Price: 200.0\r\n" +
                "RoomCode: 201\r\n" +
                "RoomType: Standard Room\r\n" +
                "Capacity: 3\r\n" +
                "RoomState: Free\r\n" +
                "Price: 140.0";
        System.setOut(originalOut);
        assertEquals(expectedOutput, output);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentRoom() {
        Hotel.removeRoomFromList(999);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRoomCode() {
        Room room1 = new Room(101, "Deluxe Room", 2, 150.0);
        room1.setRoomCode(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRoomCode2() {
        Room room1 = new Room(101, "Deluxe Room", 2, 150.0);
        room1.setRoomCode(1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRoomCode3() {
        Room room1 = new Room(101, "Deluxe Room", 2, 150.0);
        room1.setRoomCode(200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacity() {
        Room room1 = new Room(101, "Deluxe Room", 2, 150.0);
        room1.setCapacity(1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacity2() {
        Room room1 = new Room(101, "Deluxe Room", 2, 150.0);
        room1.setCapacity(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetType() {
        Room room1 = new Room(101, "Deluxe Room", 2, 150.0);
        room1.setType("room");
    }

    @Test(expected = NullPointerException.class)
    public void testCheckIn() {
        Manager manager = new Manager();
        manager.checkIn(1, 101);
    }

    @Test()
    public void testCheckIn2() {
        Manager manager = new Manager();
        Hotel.rooms = new ArrayList<>();
        Room room = new Room(101, "Standard Room", 2, 100.0);
        Hotel.rooms.add(room);
        double price = manager.checkIn(7, 101);
        assertEquals(630, price, 0.001); // Expecting a 20% discount
    }

    @Test()
    public void testCheckIn3() {
        Manager manager = new Manager();
        Hotel.rooms = new ArrayList<>();
        Room room = new Room(101, "Standard Room", 2, 100.0);
        Hotel.rooms.add(room);
        double price = manager.checkIn(10, 101);
        assertEquals(800, price, 0.001); // Expecting a 20% discount
    }

    @Test()
    public void testCheckIn4() {
        Manager manager = new Manager();
        Hotel.rooms = new ArrayList<>();
        Room room = new Room(101, "Standard Room", 2, 100.0);
        Hotel.rooms.add(room);
        double price = manager.checkIn(3, 101);
        assertEquals(300.0, price, 0.001); // No discount
    }

    @Test()
    public void testCheckIn9() {
        Manager manager = new Manager();
        Hotel.rooms = new ArrayList<>();
        Room room = new Room(101, "Standard Room", 2, 100.0);
        Hotel.rooms.add(room);
        double price = manager.checkIn(4, 101);
        assertEquals(360.0, price, 0.001); // No discount
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCheckIn5() {
        Manager manager = new Manager();
        Hotel.rooms = new ArrayList<>();
        Room room = new Room(101, "Standard Room", 2, 100.0);
        Hotel.rooms.add(room);
        manager.checkIn(0, 101);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCheckIn6() {
        Manager manager = new Manager();
        Hotel.rooms = new ArrayList<>();
        Room room = new Room(101, "Standard Room", 2, 100.0);
        Hotel.rooms.add(room);
        manager.checkIn(-1, 101);
    }


    @Test(expected = NullPointerException.class)
    public void testCheckIn7() {
        Manager manager = new Manager();
        Hotel.rooms = new ArrayList<>();
        Room room = new Room(101, "Standard Room", 2, 100.0);
        Hotel.rooms.add(room);
        manager.checkIn(3, 999); // Room code 999 does not exist
    }


    @Test()
    public void testCheckIn8() {
        Manager manager = new Manager();
        Hotel.rooms = new ArrayList<>();
        Room room = new Room(101, "Standard Room", 2, 100.0);
        Hotel.rooms.add(room);
        double price = manager.checkIn(5, 101);
        assertEquals(450.0, price, 0.001); // Expecting a 10% discount
    }

    @Test()
    public void testIntToRoman() {
        Manager manager = new Manager();
        manager.transferPrice(-1);
        manager.transferPrice(10000);
        String output1 = manager.transferPrice(1234);
        String expected1 = "MCCXXXIV";
        assertEquals(expected1, output1);
        String output2 = manager.transferPrice(0);
        String expected2 = "";
        assertEquals(expected2, output2);
        String output3 = manager.transferPrice(3999);
        String expected3 = "MMMCMXCIX";
        assertEquals(expected3, output3);
    }

    @Test()
    public void testCreateOrder() {
        OrderItem orderItem1 = new OrderItem("name1", 100, 6);
        OrderItem orderItem2 = new OrderItem("name2", 100, 12);
        assertEquals("name1", orderItem1.getProductName());
        assertEquals(12, orderItem2.getCount());
        List<OrderItem> orderItems1 = new ArrayList<>();
        List<OrderItem> orderItems2 = new ArrayList<>();
        List<OrderItem> orderItems3 = new ArrayList<>();
        Order order1 = new Order(orderItems1);
        assertEquals(new ArrayList<>(), order1.getItems());
        Order.createOrder(orderItems1);
        orderItems2.add(orderItem1);
        Order.createOrder(orderItems2);
        orderItems3.add(orderItem2);
        Order.createOrder(orderItems3);
        Order.createOrder(orderItems1);
        System.setOut(new PrintStream(outputStream));
        Order.printOrders();
        String output = outputStream.toString().trim();
        String expectedOutput = "Order No.1\r\n" +
                "Order Total:.00\r\n" +
                "\r\n" +
                "Order No.2\r\n" +
                "name1\t90.00\t6\t540.00\r\n" +
                "Order Total:540.00\r\n" +
                "\r\n" +
                "Order No.3\r\n" +
                "name2\t80.00\t12\t960.00\r\n" +
                "Order Total:960.00\r\n" +
                "\r\n" +
                "Order No.4\r\n" +
                "Order Total:.00\r\n" +
                "\r\n" +
                "AllAmount: 1500.00";
        assertEquals(expectedOutput, output);
        System.setOut(originalOut);
        assertEquals(960, Order.searchMaxOrder().totalAmount(), 0.00001);
    }

    @Test()
    public void testMain() {
        System.setOut(new PrintStream(outputStream));
        String [] args = {"test"};
        Hotel.main(args);
        String output = outputStream.toString().trim(); // 去掉首尾空格
        String expectedOutput = "2147483647";
        assertEquals(expectedOutput, output);
    }

    @Test()
    public void testQueryRoom1() {
        System.setOut(new PrintStream(outputStream));
        Hotel.addRoom("Standard Room", 101, 2);
        Hotel.queryRoom(101);
        String output = outputStream.toString().trim(); // 去掉首尾空格
        String expectedOutput = "RoomCode: 101\r\n" +
                "RoomType: Standard Room\r\n" +
                "Capacity: 2\r\n" +
                "RoomState: Free\r\n" +
                "Price: 120.0";
        assertEquals(expectedOutput, output);
        System.setOut(originalOut);
    }

    @Test
    public void testAddProduct() {
        Shop shop = new Shop();
        Product product1 = new Product("a", 188, 3);
        Product product2 = new Product("b", 18, 10);
        Product product3 = new Product("c", 2, 100);
        Product product4 = new Product("d", 3, 100);
        Product product5 = new Product("e", 4, 100);
        shop.addProduct(product1);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product4);
        shop.addProduct(product5);
        shop.addProduct(product1);
        shop.addProduct(product2);
        int index = shop.addProduct(product3);
        assertEquals(2, index);
        System.out.println(shop.getAllProductsInfo());
    }

    @Test(expected = NullPointerException.class)
    public void testDeletProduct() {
        Shop shop = new Shop();
        assertEquals(0, shop.deletProduct("电吹风"));
        shop.deletProduct("电吹风2");
    }

    @Test()
    public void testDeletProduct1() {
        Shop shop = new Shop();
        Product product = new Product("a", 1, 1);
        shop.addProduct(product);
        assertEquals(0, shop.deletProduct("a"));
    }

    @Test()
    public void testSellProduct() {
        Shop shop = new Shop();
        Product product1 = new Product("a", 188, 3);
        Product product2 = new Product("b", 18, 10);
        Product product3 = new Product("c", 2, 100);
        Product product4 = new Product("d", 3, 100);
        Product product5 = new Product("e", 4, 100);
        shop.addProduct(product1);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product4);
        shop.addProduct(product5);
        int index = shop.sellProduct("a", 1);
        assertEquals(2, index);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellProduct2() {
        Shop shop = new Shop();
        Product product1 = new Product("a", 188, 3);
        Product product2 = new Product("b", 18, 10);
        Product product3 = new Product("c", 2, 100);
        Product product4 = new Product("d", 3, 100);
        Product product5 = new Product("e", 4, 100);
        shop.addProduct(product1);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product4);
        shop.addProduct(product5);
        shop.sellProduct("a", -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellProduct4() {
        Shop shop = new Shop();
        Product product1 = new Product("a", 188, 3);
        Product product2 = new Product("b", 18, 10);
        Product product3 = new Product("c", 2, 100);
        Product product4 = new Product("d", 3, 100);
        Product product5 = new Product("e", 4, 100);
        shop.addProduct(product1);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product4);
        shop.addProduct(product5);
        shop.sellProduct("a", 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSellProduct3() {
        Shop shop = new Shop();
        shop.sellProduct("a", 1);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateProduct1() {
        Shop shop = new Shop();
        shop.updateProduct("a", 1, 1);
    }

    @Test()
    public void testUpdateProduct2() {
        Shop shop = new Shop();
        Product product1 = new Product("a", 188, 3);
        Product product2 = new Product("b", 18, 10);
        Product product3 = new Product("c", 2, 100);
        Product product4 = new Product("d", 3, 100);
        Product product5 = new Product("e", 4, 100);
        shop.addProduct(product1);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product4);
        shop.addProduct(product5);
        Product product = shop.updateProduct("a", 1, 1);
        Product product6 = shop.getProductByName("a");
        shop.addProduct(product);
        assertEquals(product6, product);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateProduct3() {
        Shop shop = new Shop();
        shop.updateProduct("a2", 1, 1);
    }

    @Test()
    public void testUpdateProduct4() {
        Shop shop = new Shop();
        Product product1 = new Product("a", 188, 3);
        Product product2 = new Product("b", 18, 10);
        Product product3 = new Product("c", 2, 100);
        Product product4 = new Product("d", 3, 100);
        Product product5 = new Product("e", 4, 100);
        shop.addProduct(product1);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product4);
        shop.addProduct(product5);
        Product product = shop.updateProduct("a", 1, 1);
        shop.addProduct(product);
        Product product6 = shop.getProductByName("a");
        assertEquals(product6, product);
    }

    @Test()
    public void testGetProductByName() {
        Shop shop = new Shop();
        shop.getProductByName("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetName() {
        Product product = new Product("name", 100, 5);
        product.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetName1() {
        Product product = new Product("name", 100, 5);
        product.setName("@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetName2() {
        Product product = new Product("name", 100, 5);
        product.setName("[");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetName3() {
        Product product = new Product("name", 100, 5);
        product.setName("}");
    }

    @Test
    public void testSetName4() {
        Product product = new Product("name", 100, 5);
        product.setName("azAZ");
        assertEquals("azAZ", product.name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPrice() {
        Product product = new Product("name", 100, 5);
        product.setPrice(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPrice2() {
        Product product = new Product("name", 100, 5);
        product.setPrice(1.12333333);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCount() {
        Product product = new Product("name", 100, 5);
        product.setCount(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDiscount() {
        Product product = new Product("name", 100, 5);
        product.setDiscount(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDiscount2() {
        Product product = new Product("name", 100, 5);
        product.setDiscount(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDiscount3() {
        Product product = new Product("name", 100, 5);
        product.setDiscount(0.5555555555);
    }

    @Test
    public void testSellProducts() {
        ShopKeeper shopKeeper = new ShopKeeper();
        Shop shop = new Shop();
        Product product1 = new Product("a", 188, 3);
        Product product2 = new Product("b", 18, 10);
        Product product3 = new Product("c", 2, 100);
        Product product4 = new Product("d", 3, 100);
        Product product5 = new Product("e", 4, 100);
        shop.addProduct(product1);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product4);
        shop.addProduct(product5);
        shopKeeper.setShop(shop);
        Map<String, Integer> products = new HashMap<>();
        products.put("a", 1);
        products.put("b", 1);
        products.put("c", 1);
        shopKeeper.sellProducts(products);
    }

    @Test()
    public void testSellProducts2() {
        ShopKeeper shopKeeper = new ShopKeeper();
        Shop shop = new Shop();
        Product product1 = new Product("a", 188, 3);
        Product product2 = new Product("b", 18, 10);
        Product product3 = new Product("c", 2, 100);
        Product product4 = new Product("d", 3, 100);
        Product product5 = new Product("e", 4, 100);
        shop.addProduct(product1);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product4);
        shop.addProduct(product5);
        shopKeeper.setShop(shop);
        Map<String, Integer> products = new HashMap<>();
        products.put("aaa", 1);
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
    public void TestGetAllProductsInfo() {
        Shop shop = new Shop();
        Product product1 = new Product("a", 188, 3);
        Product product2 = new Product("b", 18, 10);
        Product product3 = new Product("c", 2, 100);
        Product product4 = new Product("d", 3, 100);
        Product product5 = new Product("e", 4, 100);
        shop.addProduct(product1);
        String output = shop.getAllProductsInfo();
        String expected = "No.1\r\n" +
                "Name：a\r\n" +
                "Price：188.00Yuan\r\n" +
                "Discount: No discount\r\n" +
                "Quantity：3\r\n";
        assertEquals(expected, output);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product4);
        shop.addProduct(product5);
        Product product6 = new Product("namename", 100, 1);
        Product product7 = new Product("name", 100, 1);
        product6.setDiscount(0.5);
        product7.setDiscount(0.5);
        shop.addProduct(product6);
        shop.addProduct(product7);
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

    @Test(expected = IllegalArgumentException.class)
    public void testSetPrice3() {
        Room room = new Room(101, "Standard Room", 2, 188);
        room.setPrice(0);
    }

    @Test()
    public void testSetPrice4() {
        Room room = new Room(101, "Standard Room", 2, 188);
        room.setPrice(123.34);
        assertEquals(123.34, room.getPrice(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPrice5() {
        Room room = new Room(101, "Standard Room", 2, 188);
        room.setPrice(123.45675);
    }

    @Test()
    public void testRoom() {
        Room room = new Room(101, "Standard Room", 2, 188);
        assertEquals("RoomState: Free", room.getState().toString());
    }

    @Test()
    public void testBook() {
        Room room = new Room(101, "Standard Room", 2, 188);
        RoomState roomState = new BookedState();
        room.setState(roomState);
        assertEquals("RoomState: Booked", room.getState().toString());
    }

    @Test()
    public void testUnsubscribe1() {
        System.setOut(new PrintStream(outputStream));
        Room room = new Room(101, "Standard Room", 2, 188);
        RoomState roomState = new BookedState();
        room.setState(roomState);
        room.unsubscribe();
        assertEquals("RoomState: Free", room.getState().toString());
        String output = outputStream.toString().trim();
        String expected = "Operation: Unsubscribe\r\n" +
                "Room: 101\r\n" +
                "Result: Success";
        assertEquals(expected, output);
        System.setOut(originalOut);
        assertEquals("RoomState: Free", room.getState().toString());
    }

    @Test()
    public void testCheckIn1() {
        System.setOut(new PrintStream(outputStream));
        Room room = new Room(101, "Standard Room", 2, 188);
        RoomState roomState = new BookedState();
        room.setState(roomState);
        room.checkIn();
        assertEquals("RoomState: Booked", roomState.toString());
        String output = outputStream.toString().trim();
        String expectedOutput = "Operation: Check In\r\n" +
                "Room: 101\r\n" +
                "Result: Success";
        System.setOut(originalOut);
        assertEquals(expectedOutput, output);
    }

    @Test()
    public void testCheckOut() {
        System.setOut(new PrintStream(outputStream));
        Room room = new Room(101, "Standard Room", 2, 188);
        RoomState roomState = new CheckInState();
        room.setState(roomState);
        assertEquals("RoomState: CheckIn", room.getState().toString());
        room.checkOut();
        assertEquals("RoomState: Free", room.getState().toString());
        String output = outputStream.toString().trim();
        String expectedOutput = "Operation: Check Out\r\n" +
                "Room: 101\r\n" +
                "Result: Success";
        System.setOut(originalOut);
        assertEquals(expectedOutput, output);
    }

    @Test()
    public void testFreeTimeState() {
        System.setOut(new PrintStream(outputStream));
        Room room = new Room(101, "Standard Room", 2, 188);
        RoomState roomState = new FreeTimeState();
        roomState.book(room);
        assertEquals("RoomState: Booked", room.getState().toString());
        roomState.checkIn(room);
        String output = outputStream.toString().trim();
        System.setOut(originalOut);
        String expectedOutput = "Operation: Book Room\r\n" +
                "Room: 101\r\n" +
                "Result: Success\r\n" +
                "Operation: Check In\r\n" +
                "Room: 101\r\n" +
                "Result: Success";
        assertEquals(expectedOutput, output);

    }

    @Test
    public void testGetPaymentPrice() {
        Product product = new Product("aa", 101, 2);
        product.setDiscount(0.5);
        assertEquals(50.5, product.getPaymentPrice(), 0.00001);
    }

}
