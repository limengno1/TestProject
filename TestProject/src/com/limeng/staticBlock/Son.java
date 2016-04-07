package com.limeng.staticBlock;

/** 
 * <Description> <br> 
 * 子类继承父类创建子类时会先加载父类的static代码库
 * 加载顺序：父类静态代码块 --> 子类静态代码块  --> 父类构造方法 --> 子类构造方法
 * 
 * 
 * 打印结果如下：
 * 	this is parent's static block...
	this is son's static block...
	Parent constructor method.
	son's constructor method.
	Son's print method.

 *  
 * @author limeng<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年11月16日 <br>
 * @since V7.3<br>
 * @see com.limeng.staticBlock <br>
 */
public class Son extends Parent {
	
	public static void main(String args[]) {
		Parent parent = new Parent();
		parent.print();
		
		Son son = new Son();
		son.print();
	}
	
	static {
		System.out.println("this is son's static block...");
	} 
	
	public Son() {
		System.out.println("son's constructor method.");
	}
	
	public void print() {
		System.out.println("Son's print method.");
	}
}
