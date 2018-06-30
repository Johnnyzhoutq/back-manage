package com.manage.common.httpsignutil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.manage.common.constant.Constants;


public class HttpSignUtil {
	/** 
	* @Description:  
	* @author 周天琪 
	* @date 2018-06-05  
	*/
	public static Map<String,String> getMapParamsForHttpPost(Map<String, Object> paraMap) {
		String sign = getSignFromMap(paraMap);
		String signKey = Constants.ACCESS_KEY;
		String lvtukey = DigestUtils.md5Hex(sign+signKey);
		
		Iterator<Map.Entry<String, Object>> entries = paraMap.entrySet().iterator();
		Map<String,String> postParaMap = new HashMap<String, String>();
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			postParaMap.put(entry.getKey(), (String)entry.getValue());
		}
		postParaMap.put("lvtukey", lvtukey);
        
		return postParaMap;
	}
	
	/** 
	* @Description: 生成url 
	* @author 周天琪 
	* @date 2018-06-05  
	*/
	public static String genJinseApiUrl(String RequestUrl,Map<String, Object> paraMap) {
		String sign = getSignFromMap(paraMap);
		String lvtukey = DigestUtils.md5Hex(sign+"&secret_key="+Constants.SECRET_KEY);
		paraMap.put("sign", lvtukey);
		RequestUrl += getSignFromMap(paraMap);
		return RequestUrl;
	}
	
	/** 
	* @Description: 以a=1&b=2 形式拼接参数串 
	* @author 周天琪 
	* @date 2018-06-05  
	*/
	@SuppressWarnings("unchecked")
	public static String getSignFromMap(Map<String, Object> paraMap) {
		List<String> keys = new ArrayList<String>(paraMap.keySet());

	    Collections.sort(keys); //键值ASCII码递增排序
	
	    StringBuilder sb = new StringBuilder();
	    for (String key : keys) {
	        Object value = paraMap.get(key);
	        if (value == null) {
	            continue;
	        }
	        if (value.getClass().isArray()) {
	            for (int i = 0; i < Array.getLength(value); i++) {
	                String item = Array.get(value, i).toString();
	                sb.append(key).append('=').append(item).append('&');
	            }
	        } else if(value instanceof List) {
	            List<Object> items = (List<Object>) value;
	            for (Object item : items) {
	                sb.append(key).append('=').append(item.toString()).append('&');
	            }
	        } else {
	            String str = value.toString();
	            sb.append(key).append('=').append(str).append('&');
	        }
	    }
	    if (sb.length() > 0) {
	        sb.deleteCharAt(sb.length() - 1);
	    }
	    return sb.toString();
    }

}
