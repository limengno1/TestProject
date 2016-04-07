package com.limeng.reflect;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class TestReflect {

	@Test
	public void testReflect() {
		Car car = new SUVCar();
		
		CarHandler carHander = new CarHandler(car);
		
		Car proxyCar = (Car) Proxy.newProxyInstance(car.getClass().getClassLoader(), car.getClass().getInterfaces(), carHander);
		
		proxyCar.drive("limeng");
	}
}
