package net.mooctest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;

public class NaiveBayesClassifierTest{

  @Test(timeout = 4000)
  public void test0() {
      NaiveBayesClassifier<Object, Object> naiveBayesClassifier = new NaiveBayesClassifier<Object, Object>();
  }
}
