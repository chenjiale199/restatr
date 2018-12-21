package cn.zg.zx.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.zx.dao.FactoryDao;
import cn.zg.zx.domain.Factory;
import cn.zg.zx.service.FactoryService;

@Service
public class FactoryServiceImpl implements FactoryService {

	@Autowired
	FactoryDao factoryDao;
	
	public List<Factory> find(Factory factory) {
		return factoryDao.find(factory);
	}

	public Factory get(Serializable id) {
		return factoryDao.get(id);
	}

	public void insert(Factory factory) {
		factory.setState(1);//默认设置新增工厂状态为正常。
		factoryDao.insert(factory);
	}

	public void update(Factory factory) {
		factoryDao.update(factory);
	}

	public void delete(Serializable id) {
		factoryDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		factoryDao.delete(ids);
	}

	public void changeState(Map<String, Object> map) {
		factoryDao.changeState(map);
	}

	public List<Factory> combo() {
		return factoryDao.combo();
	}

}
