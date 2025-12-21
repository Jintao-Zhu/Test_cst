package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.*;

public class AlgAnimAppTest {

	Color c  = new Color(1, 2, 3);
	Color c1  = new Color(35, 24, 33);
	Color c2  = new Color(11, 2, 13);
	Color [] cs1 = {c1, c2, c};

	String [] s1 = {"play", "quit"};



	@Test
	public void test() {
		LFrame lfa = new LFrame(cs1, s1);



	}

}
