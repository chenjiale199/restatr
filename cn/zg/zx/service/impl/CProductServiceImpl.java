package cn.zg.zx.service.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.util.Arith;
import cn.zg.zx.dao.CProductDao;
import cn.zg.zx.dao.ContractDao;
import cn.zg.zx.domain.CProduct;
import cn.zg.zx.domain.Contract;
import cn.zg.zx.service.CProductService;
import cn.zg.zx.service.ContractService;

@Service
public class CProductServiceImpl implements CProductService {

	@Autowired
	CProductDao cproductDao;
	
	public List<CProduct> find(CProduct cproduct) {
		return cproductDao.find(cproduct);
	}

	public CProduct get(Serializable id) {
		return cproductDao.get(id);
	}

	public void insert(CProduct cproduct) {
		//页面虽然已经求出了总价，但后台也需要重新计算，从而确保数据库中的数据的准确和完整。
		//1.56*3=4.99999998
		cproduct.setAmount(Arith.mul(cproduct.getPrice(), cproduct.getCnumber()));
		cproductDao.insert(cproduct);
	}

	public void update(CProduct cproduct) {
		cproductDao.update(cproduct);
	}

	public void delete(Serializable id) {
		cproductDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		cproductDao.delete(ids);
	}

}
