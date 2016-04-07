package com.limeng.subfill;

import org.junit.Test;

public class TestSubFill {

	@Test
	public void test1() {
		int len = 21;
		String defaultValue = "123456789,123456789,123456789";
		System.out.println(defaultValue.substring(0, len-1));
	}
}
