package net.mooctest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NaiveBayesClassifierTest{

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
        assertEquals(944, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Test()
    public void testNaiveBayesClassifier() throws NoSuchFieldException, IllegalAccessException {
        NaiveBayesClassifier<String, String> classifier = new NaiveBayesClassifier<String, String>();

        System.out.println(classifier.featureCount("nan", "positive"));

        classifier.decrementCategory("none");
        classifier.decrementFeature("good", "positive");
        classifier.incrementCategory("none");
        classifier.incrementCategory("none");
        classifier.incrementFeature("good", "positive");
        classifier.incrementFeature("good", "positive");
        classifier.decrementCategory("none");
        classifier.decrementCategory("none");
        classifier.decrementFeature("good", "positive");
        classifier.decrementFeature("good", "positive");

        Collection<String> features = new ArrayList<>();
        System.out.println(classifier.classify(features));
        System.out.println(classifier.classifyDetailed(features));

        classifier.learn("positive",  Arrays.asList("good", "nice", "excellent", "awesome"));
        classifier.learn("negative",  Arrays.asList("bad", "terrible", "poor", "awful"));
        classifier.learn("neutral",  Arrays.asList("okay", "average", "mediocre", "fine"));

        features =  Arrays.asList("good", "awesome");
        System.out.println(classifier.classify(features));
        System.out.println(classifier.classifyDetailed(features));

        features =  Arrays.asList("good", "poor");
        System.out.println(classifier.classify(features));
        System.out.println(classifier.classifyDetailed(features));

        features =  Arrays.asList("unknown", "feature");
        System.out.println(classifier.classify(features));
        System.out.println(classifier.classifyDetailed(features));

        System.out.println(classifier.getFeatures());
        System.out.println(classifier.getMemoryCapacity());
        classifier.setMemoryCapacity(10);
        classifier.decrementCategory("none");
        classifier.decrementFeature("good", "positive");
        System.out.println(classifier.featureCount("nan", "positive"));

        IFeatureProbability calculator = new IFeatureProbability<String, String>() {
            @Override
            public float featureProbability(String feature, String category) {
                return 0.5f;
            }
        };
        System.out.println(classifier.featureWeighedAverage("nan", "positive", calculator));
        System.out.println(classifier.featureWeighedAverage("nan", "positive", calculator, 2));
        System.out.println(classifier.categoryCount("nan"));
        System.out.println(classifier.featureProbability("nan", "nan"));

        classifier.setMemoryCapacity(1);
        classifier.learn("positive", Arrays.asList("good"));
        classifier.learn("negative", Arrays.asList("bad"));
        System.out.println(classifier.featureCount("good", "positive"));
        System.out.println(classifier.categoryCount("positive"));
        System.out.println(classifier.featureCount("bad", "negative"));
        System.out.println(classifier.categoryCount("negative"));

        classifier = new NaiveBayesClassifier<>();
        classifier.learn("positive", Arrays.asList("good"));
        classifier.decrementFeature("nonexistentFeature", "positive");

        Classification<String, String> classification = classifier.classify(Arrays.asList("good"));
        System.out.println(classification.getProbability());

        classifier = new NaiveBayesClassifier<>();
        Dictionary<String, Integer> featureDictionary = new Hashtable<>();
        featureDictionary.put("good", 1);
        Field field = Classifier.class.getDeclaredField("featureCountPerCategory");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        Dictionary<String, Dictionary<String, Integer>> featureCountPerCategory =
                (Dictionary<String, Dictionary<String, Integer>>) field.get(classifier);
        featureCountPerCategory.put("positive", featureDictionary);
        classifier.decrementFeature("good", "positive");
    }

    @Test
    public void testDecrementFeature() {
        NaiveBayesClassifier<String, String> classifier = new NaiveBayesClassifier<>();
        classifier.setMemoryCapacity(1);
        classifier.learn("positive", Arrays.asList("good"));
        assertEquals(1, classifier.featureCount("good", "positive"));
        assertEquals(1, classifier.categoryCount("positive"));
        classifier.learn("negative", Arrays.asList("bad"));
        assertEquals(0, classifier.featureCount("good", "positive"));
        assertEquals(0, classifier.categoryCount("positive"));
        assertEquals(1, classifier.featureCount("bad", "negative"));
        assertEquals(1, classifier.categoryCount("negative"));
    }

    @Test
    public void testProbability() {
        NaiveBayesClassifier<String, String> classifier = new NaiveBayesClassifier<>();
        classifier.learn("positive", Arrays.asList("good", "nice", "excellent"));
        classifier.learn("negative", Arrays.asList("bad", "terrible", "poor"));
        Classification<String, String> classificationPositive = classifier.classify(Arrays.asList("good", "nice"));
        Classification<String, String> classificationNegative = classifier.classify(Arrays.asList("bad", "terrible"));
        assertNotNull(classificationPositive);
        assertNotNull(classificationNegative);
        assertEquals("positive", classificationPositive.getCategory());
        assertEquals("negative", classificationNegative.getCategory());
        assertTrue(classificationPositive.getProbability() > 0.0f);
        assertTrue(classificationNegative.getProbability() > 0.0f);
    }

}
