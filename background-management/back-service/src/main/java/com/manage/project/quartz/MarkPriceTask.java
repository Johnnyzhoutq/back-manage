package com.manage.project.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.manage.common.util.HttpClientUtil;
import com.manage.project.business.dao.MarkPriceMapper;
import com.manage.project.business.pojo.MarkPrice;

/**
 *  牌价管理的定时任务--获取当日牌价进行修改
 * @author hht
 *
 */
@Component("markPriceTask")
public class MarkPriceTask
{
	
	@Value("${system.config.service.address}")
	private String  address;
	
	/**
	 *  修改牌价接口
	 */
	private  static  final String  urlUpdatePrice = "/admin/updatePrice";
	

	@Autowired
	private MarkPriceMapper markPriceMapper;

    public void updatePrice()
    {
    	Date currentTime = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	String dateString = formatter.format(currentTime);
    	
    	MarkPrice selectResult = markPriceMapper.selectMarkPriceBySelectDate(dateString);
    	JSONObject jsonParam = new JSONObject();
    	String price = selectResult.getMarkPrice();
    	if(StringUtil.isNotEmpty(price)) {
    		jsonParam.put("price", Integer.parseInt(price));
    		HttpClientUtil.doPostJson(address+urlUpdatePrice, jsonParam.toJSONString(),new HashMap<String, String>());	
    	}
    }
}
