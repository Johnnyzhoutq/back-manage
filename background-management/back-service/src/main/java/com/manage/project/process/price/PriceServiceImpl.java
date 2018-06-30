package com.manage.project.process.price;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.manage.common.util.HttpClientUtil;
import com.manage.common.util.StringUtils;

/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service("priceService")
public class PriceServiceImpl implements IPriceService
{
	@Value("${system.config.service.address}")
	private String  address;
	
	private  static  final String  urlUpdatePrice = "/admin/updatePrice";

	
	/**
	 *  牌价--点击保存
	 */
	@Override
	public int savePrice(String price) {

        int count = 0;
        if (StringUtils.isNotNull(price))
        {
        	try {
        		JSONObject jsonParam = new JSONObject();
        		jsonParam.put("price", Integer.parseInt(price));
        		String string = HttpClientUtil.doPostJson(address+urlUpdatePrice, jsonParam.toJSONString(),new HashMap<String, String>());
        		System.out.println("今日牌价修改添加 ：  " + string);
        		return 1;
				
			} catch (Exception e) {
				 return count;
			}
        }
        
        return count;
	}

}
