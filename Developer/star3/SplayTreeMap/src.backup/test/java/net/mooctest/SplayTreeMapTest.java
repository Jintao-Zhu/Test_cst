package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Test;

public class SplayTreeMapTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Integer integer0 = new Integer(0);
      SplayTreeMap.Entry splayTreeMap_Entry0 = new SplayTreeMap.Entry((Object) null, integer0, (SplayTreeMap.Entry) null);
      String string0 = splayTreeMap_Entry0.toString();
      assertEquals("null=0", string0);
  }
}
