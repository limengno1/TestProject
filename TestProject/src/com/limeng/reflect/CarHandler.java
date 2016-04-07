package com.limeng.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CarHandler implements InvocationHandler {

	Car car;
	
	public CarHandler(Car car) {
		super();
		this.car = car;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("haha..");
		method.invoke(car,args);
		return null;
	}

}
