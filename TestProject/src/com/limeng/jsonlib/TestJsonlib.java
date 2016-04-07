package com.limeng.jsonlib;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class TestJsonlib {

	@Test
	public void testJsonFromMap() {
		Map map = new HashMap();
		map.put("source", "ZSMART");
		map.put("type", "SMS");
		JSONArray jsonArray = JSONArray.fromObject(map);
		System.out.println(jsonArray);
	}
	
	@Test
	public void testJsonFromString() {
		String jsonStr = "{'source': 'ZSMART','requestId': 'ZSMART20151029114108326',"
						+ "'type': 'SMS',"
						+ "'message': 'Testing',"
						+ "'msisdnList': [{'msisdn': '60168388777'},{'msisdn':'60168388999'}]}";
		
		String jsonStr2 = "{'tranId':'235685432695773','statusCode': '0','status': 'SUCCESS'}" ;
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonStr2);
		JSONObject jo = jsonArray.getJSONObject(0);
		
		System.out.println(jo.toString());
		System.out.println(jo.get("statusCode"));
		System.out.println(jo.get("status"));
	}
}
