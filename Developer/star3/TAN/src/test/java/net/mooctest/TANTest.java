package net.mooctest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class TANTest{
  @Test(timeout = 4000)
  public void test() {
      ArrayList<String[]> arrayList0 = new ArrayList<String[]>(0);
      String[] stringArray = new String[7];
      arrayList0.add(stringArray);
      TAN tAN0 = new TAN(arrayList0);
  }
}
