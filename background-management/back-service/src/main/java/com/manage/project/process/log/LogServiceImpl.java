package com.manage.project.process.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.manage.common.util.HttpClientUtil;
import com.manage.framework.web.page.TableDataInfo;
import com.manage.project.process.person.LogInfo;

/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service("logService")
public class LogServiceImpl implements ILogService
{
	
	@Value("${system.config.service.address}")
	private String  address;
	
	
	private  static  final String  urlFindUserLog = "/admin/findUserLog";

	
	/**
	 *  日志查询列表
	 */
	@Override
	public TableDataInfo findUserLog(String page, String pagesize,String userid, String type, String searchValue) {
		TableDataInfo pageInfo = new TableDataInfo();
		
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("pagesize", Integer.parseInt(pagesize));
		jsonParam.put("page", Integer.parseInt(page));
		jsonParam.put("userid", Integer.parseInt(userid));
		jsonParam.put("searchValue", searchValue);
		try {
			if(StringUtil.isNotEmpty(type)) {
				jsonParam.put("type", Integer.parseInt(type));
			}
			String resultStr = HttpClientUtil.doPostJson(address+urlFindUserLog, jsonParam.toJSONString(),new HashMap<String, String>());
			JSONObject json = JSONObject.parseObject(resultStr);
			
			JSONObject pagedata = json.getJSONObject("data");
			JSONArray jsonArray = pagedata.getJSONArray("data");
			long total = pagedata.getLong("total");
			 
			List<LogInfo> logInfoList = new ArrayList<LogInfo>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				LogInfo logInfo = new LogInfo();
				logInfo.setUserId(obj.getString("user_id"));
				logInfo.setServiceName(obj.getString("service_name"));
				logInfo.setServiceType(obj.getString("service_type"));
				logInfo.setCoinNum(obj.getString("coin_num"));
				logInfo.setTime(obj.getString("time"));
				logInfoList.add(logInfo);
			}
			pageInfo.setTotal(total);
			pageInfo.setRows(logInfoList);
			
			return pageInfo;
		} catch (Exception e) {
			return null;
		}
	}
}
