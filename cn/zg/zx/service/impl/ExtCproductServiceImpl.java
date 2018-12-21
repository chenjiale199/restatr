package cn.zg.zx.service.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.util.Arith;
import cn.zg.zx.dao.ExtCproductDao;
import cn.zg.zx.domain.ExtCproduct;
import cn.zg.zx.service.ExtCproductService;

@Service
public class ExtCproductServiceImpl implements ExtCproductService {

	@Autowired
	ExtCproductDao extCproductDao;
	
	public List<ExtCproduct> find(ExtCproduct extCproduct) {
		return extCproductDao.find(extCproduct);
	}

	public ExtCproduct get(Serializable id) {
		return extCproductDao.get(id);
	}

	public void insert(ExtCproduct extCproduct) {
		//页面虽然已经求出了总价，但后台也需要重新计算，从而确保数据库中的数据的准确和完整。
		//1.56*3=4.99999998
		extCproduct.setAmount(Arith.mul(extCproduct.getPrice(), extCproduct.getCnumber()));
		extCproductDao.insert(extCproduct);
	}

	public void update(ExtCproduct extCproduct) {
		extCproductDao.update(extCproduct);
	}

	public void delete(Serializable id) {
		extCproductDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		extCproductDao.delete(ids);
	}

}
