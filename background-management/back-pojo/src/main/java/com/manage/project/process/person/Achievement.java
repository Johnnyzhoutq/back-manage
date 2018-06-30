package com.manage.project.process.person;

import java.util.ArrayList;
import java.util.List;

import com.manage.project.base.pojo.BaseEntity;

public class Achievement extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8192051152516013119L;

	
	private List<AchievementDate> leftDataList = new ArrayList<AchievementDate>();
	
	private List<AchievementDate> rightDataList = new ArrayList<AchievementDate>();
	


	public List<AchievementDate> getLeftDataList() {
		return leftDataList;
	}

	public void setLeftDataList(List<AchievementDate> leftDataList) {
		this.leftDataList = leftDataList;
	}

	public List<AchievementDate> getRightDataList() {
		return rightDataList;
	}

	public void setRightDataList(List<AchievementDate> rightDataList) {
		this.rightDataList = rightDataList;
	}

}
