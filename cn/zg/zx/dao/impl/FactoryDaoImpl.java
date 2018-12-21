package cn.zg.zx.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.zg.zx.dao.FactoryDao;
import cn.zg.zx.domain.Factory;

@Repository
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao {

	//继承BaseDaoImpl的目的是：为了能偶使用父类非私有方法。实现FactoryDao目的是，为了实现扩展功能
	
	public FactoryDaoImpl() {
		this.setNs("cn.zg.zx.mapper.FactoryMapper.");
	}

	public List<Factory> find(Factory entity) {
		return super.find(entity);
	}

	public Factory get(Serializable id) {
		return super.get(id);
	}

	public void insert(Factory entity) {
		super.insert(entity);
	}

	public void update(Factory entity) {
		super.update(entity);
	}

	public void delete(Serializable id) {
		super.delete(id);
	}

	public void delete(Serializable[] ids) {
		super.delete(ids);
	}

	public void changeState(Map<String, Object> map) {
		super.getSqlSession().update(super.getNs() + "changeState", map);
	}

	public List<Factory> combo() {
		return super.getSqlSession().selectList(super.getNs()+"combo");
	}


}
