package com.manage.project.process.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manage.common.util.HttpClientUtil;
import com.manage.framework.web.page.TableDataInfo;

/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service("personService")
public class PersonServiceImpl implements IPersonService
{
	@Value("${system.config.service.address}")
	private String  address;
	
	/**
	 *  获取用户列表接口
	 */
	private  static  final String  urlGetAllPersonInfo = "/admin/findUserList";
	
	
	/**
	 *  查询业绩接口
	 */
	private  static  final String  urlgetAchievementDataByUserId = "/admin/getAchievementDataByUserId";

	
	/**
	 *  查询用户数据列表
	 */
	public TableDataInfo selectAllPersonList(String page, String pagesize, String searchValue) {
		TableDataInfo pageInfo = new TableDataInfo();
		
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("pagesize", Integer.parseInt(pagesize));
		jsonParam.put("page", Integer.parseInt(page));
		jsonParam.put("searchValue", searchValue);
		String resultStr = HttpClientUtil.doPostJson(address+urlGetAllPersonInfo, jsonParam.toJSONString(),new HashMap<String, String>());
		JSONObject json = JSONObject.parseObject(resultStr);
		
		JSONObject pagedata = json.getJSONObject("data");
		JSONArray jsonArray = pagedata.getJSONArray("data");
		long total = pagedata.getLong("total");
		 
		List<Person> personList = new ArrayList<Person>();
		try {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				Person person = new Person();
				person.setUserId(obj.getString("id"));
				person.setEmail(obj.getString("email"));
				person.setMobile(obj.getString("mobile"));
				person.setUserName(obj.getString("user_name"));
				person.setCreateTime(obj.getString("create_time"));
				personList.add(person);
			}
		} catch (Exception e) {
			
		}
		pageInfo.setTotal(total);
		pageInfo.setRows(personList);
		
		return pageInfo;
	}

	
	
	/**
	 *   点击业绩弹框数据展示 
	 */
	@Override
	public Achievement getAchievementDataByUserId(String userId) {
		
		try {
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("userid", userId);
			String resultStr = HttpClientUtil.doPostJson(address+urlgetAchievementDataByUserId, jsonParam.toJSONString(),new HashMap<String, String>());
		    JSONObject json = JSONObject.parseObject(resultStr);
		    JSONObject data = json.getJSONObject("data");
		   
		    JSONObject leftdata = data.getJSONObject("leftdata");
		    String keyongSuanli = leftdata.getString("keyongSuanli");
		    String zongSuanli = leftdata.getString("zongSuanli");
		    String chiyouSuanli = leftdata.getString("chiyouSuanli");
		    
		    JSONArray jsonArray = leftdata.getJSONArray("list");
		    /*for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				String userName = obj.getString("userName");
				
			}*/
		    
		    JSONObject obj = (JSONObject) jsonArray.get(0);
			String userName = obj.getString("userName");
			String level = obj.getString("level");
			String totalIncome = obj.getString("totalIncome");
			AchievementDate achievementData = new AchievementDate();
			achievementData.setKeyongSuanli(keyongSuanli);
			achievementData.setZongSuanli(zongSuanli);
			achievementData.setChiyouSuanli(chiyouSuanli);
			achievementData.setUserName(userName);
			achievementData.setLevel(level);
			achievementData.setTotalIncome(totalIncome);
			List<AchievementDate> leftDataList = new ArrayList<AchievementDate>();
			leftDataList.add(achievementData);
			
			
			//  rightdata 数据
			JSONObject rightdata = data.getJSONObject("rightdata");
		    String keyongSuanlix = leftdata.getString("keyongSuanli");
		    String zongSuanlix = leftdata.getString("zongSuanli");
		    String chiyouSuanlix = leftdata.getString("chiyouSuanli");
		    
		    JSONArray jsonArrayx = rightdata.getJSONArray("list");
		    /*for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				String userName = obj.getString("userName");
				
			}*/
		    
		    JSONObject objx = (JSONObject) jsonArrayx.get(0);
			String userNamex = objx.getString("userName");
			String levelx = objx.getString("level");
			String totalIncomex = objx.getString("totalIncome");
			AchievementDate achievementDatax = new AchievementDate();
			achievementDatax.setKeyongSuanli(keyongSuanlix);
			achievementDatax.setZongSuanli(zongSuanlix);
			achievementDatax.setChiyouSuanli(chiyouSuanlix);
			achievementDatax.setUserName(userNamex);
			achievementDatax.setLevel(levelx);
			achievementDatax.setTotalIncome(totalIncomex);
			List<AchievementDate> rightDataList = new ArrayList<AchievementDate>();
			rightDataList.add(achievementDatax);
			
			Achievement achievement = new Achievement();
			achievement.setLeftDataList(leftDataList);
			achievement.setRightDataList(rightDataList);
			return achievement;
			
		} catch (Exception e) {
			return null;
		}
		
	}
}
