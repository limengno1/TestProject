package com.limeng.jsonlib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * <Description> <br> 
 *  json字符串：
 *  	1.键值对必须为String字符串
 *  	2.值不能为空
 * @author limeng<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年11月25日 <br>
 * @since V7.3<br>
 * @see com.limeng.jsonlib <br>
 */
public class TestOldParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonStr = "'source':'zlx_dependprod4templet',"
				+	"'requestId':'2',"
				+	"'msisdnList':[{'msisdn':'9'},{'msisdn':'380903'}]}";
		
		System.out.println(parseJsonBody(jsonStr));
	}
	/**
     * Description: <br>
     * 
     * @author libin<br>
     * @taskId <br>
     * @param shorMessage <br>
     * @return <br>
     */
    public static String parseJsonBody(String shorMessage) {

        try {
            StringBuffer sb = new StringBuffer();
            if (!shorMessage.startsWith("{")) {
                sb.append("{").append(shorMessage).append("}");
            }
            else {
                sb.append(shorMessage);
            }
            JSONArray ja1 = new JSONArray();
            ja1.add(sb.toString());

            JSONObject jo = ja1.getJSONObject(0);

            // network需要特殊处理
            if (jo.containsKey("network")) {
                jo.element("network", "[\"" + jo.getString("network") + "\"]");
            }

            return jo.toString();
        }
        catch (Exception ex) {
            System.out.println(ex);
            return "{}";
        }

    }
}
