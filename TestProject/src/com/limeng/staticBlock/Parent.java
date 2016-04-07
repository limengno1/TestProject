package com.limeng.staticBlock;

public class Parent {

	protected static String wspath = "D:/Work";
	
	static {
		System.out.println("this is parent's static block...");
	} 
	
	public Parent() {
		System.out.println("Parent constructor method.");
	}
	
	public void print() {
		System.out.println("Parent print method.");
	}
	
	public static void staicPrint() {
		System.out.println("static print method.");
	}
}
