package com.limeng.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Ivocation implements InvocationHandler{

	private AbstractClass ac;
	
	public Ivocation(AbstractClass ac) {
		this.ac = ac;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		method.invoke(ac, args);
		return null;
	}

}
