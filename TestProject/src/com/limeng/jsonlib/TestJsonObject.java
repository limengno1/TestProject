package com.limeng.jsonlib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class TestJsonObject {

	/**
	 * Description: <br> 
	 * 测试 accumulate方法
	 * 累加，追加
	 * 简单数据:简单类型，String
	 *  
	 * @author limeng<br>
	 * @taskId <br> <br>
	 */ 
	@Test
	public void test1() {
		JSONObject jo = new JSONObject();
		jo.put("accumulate1", 111);
		jo.accumulate("accumulate1", 222);
		jo.accumulate("accumulate1", "abcd");
		
		System.out.println(jo.toString());
	}
	
	@Test
	public void test2() {
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		ja.add(0, JSONObject.fromObject("{'accumulate2':222}"));
		
		jo.accumulate("accumulate", ja);
		jo.accumulate("accumulate", JSONObject.fromObject("{'accumulate2':333}"));
		
		System.out.println(jo.toString());
	}
	
	@Test
	public void test3() {
		JSONObject jo = new JSONObject();
		jo.put("name", "limeng");
		jo.put("address", "板桥新城");
		
		JSONObject jo2 = new JSONObject();
		jo2.put("name", "limeng2");
		jo2.put("address", "板桥新城金地自在城");
		jo2.element("interest", JSONObject.fromObject("{'balls':'basketball', 'food':'many'}"));
		
		JSONArray ja = new JSONArray();
		ja.add(0,jo);
		ja.add(1,jo2);
		
		ja.addAll(JSONArray.fromObject("[{'balls':'basketball3', 'food':'many3'}, {'balls':'basketball2', 'food':'many2'}]"));
		
		System.out.println(ja.toString());
	}
}
