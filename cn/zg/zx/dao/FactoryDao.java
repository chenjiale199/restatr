package cn.zg.zx.dao;

import java.util.List;
import java.util.Map;

import cn.zg.zx.domain.Factory;

public interface FactoryDao extends BaseDao<Factory> {
	//扩充功能
	public void changeState(Map<String,Object> map);
	
	public List<Factory> combo();
}
