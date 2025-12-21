package net.mooctest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;

public class OokEngineTest{

  @Test(timeout = 4000)
  public void test(){
      OokEngine ookEngine = new OokEngine(0);
  }
}
