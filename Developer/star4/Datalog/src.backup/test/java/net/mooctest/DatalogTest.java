package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatalogTest {

	@Test
	public void test(){
		Argument[] a=new Argument[1];
		a[0]=Argument.value(new Value("value"));
		Predicate p=new Predicate("pre");
		Datalog f=new Datalog(p, a);
		assertArrayEquals(a, f.getArguments());
	}
	
}
