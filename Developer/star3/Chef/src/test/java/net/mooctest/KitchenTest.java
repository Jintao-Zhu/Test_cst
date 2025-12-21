package net.mooctest;

import org.junit.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.*;

public class KitchenTest {

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
        assertEquals(0, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    private void assertThrows(Runnable r, Class<? extends Throwable> exception) {
        try {
            r.run();
            fail();
        } catch (Exception e) {
            assertTrue(exception.isAssignableFrom(e.getClass()));
        }
    }

    @Test
    public void testKitchen() throws Exception {

        String [] str = {
//                "Recipe: Classic Pancakes1.\n\n",
//                "Recipe: Classic Pancakes1.\n\n" +
//                        "Recipe: Classic Pancakes2.\n\n",
//                "Recipe: Classic Pancakes1.\n\n" +
//                        "Recipe: Classic Pancakes2.\n\n" +
//                        "Recipe: Classic Pancakes3.\n\n",
//                "Recipe: Classic Pancakes1.\n",
//                        "Method.\n" +
//                        "Take flour from refrigerator.\n\n" +
//                        "Method.\n" +
//                        "Take flour from refrigerator.\n\n" +
//                        "Pre-heat oven.\n\n",
//                "Recipe: Classic Pancakes1.\n",
//                        "Method.\n" +
//                        "Take flour from refrigerator.\n" +
//                        "Pre-heat oven.\n\n",
//                "Serves 4.\n",
//                "Recipe: Classic Pancakes1.\n\n" +
//                        "Pre-heat oven to 180 .\n\n" +
//                        "Cooking time: 20 minutes\n\n",
//                "Recipe: Classic Pancakes1.\n\n" +
//                        "Cooking time: 20 minutes\n\n" +
//                        "Ingredients.\n" +
//                        "200 g flour\n\n",
                "Recipe\n\n" +
                "Classic Pancakes1.\n\n" +
                "Ingredients.\n" +
                "200 g flour\n" +
                "100 ml milk\n" +
                "2 eggs\n" +
                "2 tomatoes\n" +
                "50 g sugar\n" +
                "50 g beef\n" +
                "10 g butter\n\n" +
//                "pinch of s6alt\n\n" +
                "Cooking time: 20 minutes\n\n" +
                "Pre-heat oven to 180 gas mark.\n\n" +
                "Method.\n" +
                "Take flour from refrigerator.\n" +
                "Put flour into the 1st mixing bowl.\n" +
                "Fold flour into the 1st mixing bowl.\n" +
                "Add dry ingredients to mixing bowl.\n" +
                "Add beef into 1st mixing bowl.\n" +
                "Combine beef from 1st mixing bowl.\n" +
                "Divide beef into 1st mixing bowl.\n" +
                "Remove beef to 1st mixing bowl.\n" +
                "Liquefy contents of the 1st mixing bowl.\n" +
                "Liquefy tomatoes.\n" +
                "Stir the 1st mixing bowl for 2 minutes.\n" +
                "Stir milk into the 2nd mixing bowl.\n" +
                "Mix the 1st mixing bowl well.\n" +
                "Clean 1st mixing bowl.\n" +
                "Pour contents of the 1st mixing bowl into the 1st baking dish.\n" +
                "Refrigerate for 2 hours.\n" +
                "Serve with maple syrup.\n" +
                "Serve the client until night.\n" +
                "Serve the client.\n\n" +
                "Serves 4."
        };

        File file = new File("src/test/resources/test.txt");
        FileWriter writer = new FileWriter("src/test/resources/test.txt");
        writer.write(str[0]);

        writer.close();

        Chef chef = new Chef("src/test/resources/test.txt");

        String data = "1 2 3 4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        chef.bake();

//            try {
//                Chef chef = new Chef("test.txt");
//                chef.bake();
//                System.out.println(chef.toString());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }

    }

    @Test
    public void testKitchen2() throws Exception {
        String recipe =
                "Method.\n" +
                "Take flour from refrigerator.\n";
        HashMap<String, Recipe> recipes = new HashMap<String, Recipe>();
        Recipe mainrecipe = new Recipe("title");

        mainrecipe.setMethod(recipe);
        final Kitchen kitchen = new Kitchen(recipes, mainrecipe);


        try {
            kitchen.cook();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ArrayList<Method> methods = kitchen.recipe.getMethods();
        HashMap<String,Ingredient> ingredients = mainrecipe.getIngredients();
        Scanner input = new Scanner(System.in);
        int i = 0;
        while (i < methods.size()) {
            Method m = methods.get(i);
            switch (m.type) {
                case Take:
                    System.out.println(m.type);
            }
//            switch (m.type) {
//                case Take :
//                    ingredients.get(m.ingredient).setAmount(input.nextInt());
//                    break;
//            }

//            System.out.println(kitchen.recipe.getIngredients().get(methods.get(i).ingredient));
            i = i + 1;
        }
        try {
            kitchen.cook();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
