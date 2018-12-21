package cn.zg.zx.service.impl;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zg.zx.dao.CProductDao;
import cn.zg.zx.dao.ContractDao;
import cn.zg.zx.dao.ExtCproductDao;
import cn.zg.zx.domain.Contract;
import cn.zg.zx.service.ContractService;
import cn.zg.zx.vo.ContractCombo;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	ContractDao contractDao;
	@Autowired
	CProductDao cproductDao;
	@Autowired
	ExtCproductDao extCproduct; 
	
	public List<Contract> find(Contract contract) {
		return contractDao.find(contract);
	}
//多态类型选择范围广
	public Contract get(Serializable id) {
		return contractDao.get(id);
	}

	public void insert(Contract contract) {
		contract.setState(0);//将新增的合同设置为草稿
		contract.setId(UUID.randomUUID().toString());//生成UUID					
		contractDao.insert(contract);
	}

	public void update(Contract contract) {
		contractDao.update(contract);
	}

	public void delete(Serializable id) {
		contractDao.delete(id);
	}
//及联失败
	public void delete(Serializable[] ids) {
		extCproduct.deleteCascade(ids);
		cproductDao.deleteCascade(ids);
		contractDao.delete(ids);
	}
//用于自动生成合同号
	public String getContractNo() {
		int year=Calendar.getInstance().get(Calendar.YEAR)%100;
		int num=contractDao.getContractNo(year);
		DecimalFormat df=new DecimalFormat("0000");
		return year+"ZX"+df.format(num);
	}
//合同状态码 草稿 或者已上报 涉及级联删除
	public void changeState(Map<String, Object> map) {
		contractDao.changeState(map);
	}
//购销合同详情
	public ContractCombo combo(String id) {
		return contractDao.combo(id);
	}

}
