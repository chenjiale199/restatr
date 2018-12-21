package cn.zg.zx.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.zg.zx.dao.ContractDao;
import cn.zg.zx.domain.Contract;
import cn.zg.zx.vo.ContractCombo;

@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao {

	public ContractDaoImpl() {
		super.setNs("cn.zg.zx.mapper.ContractMapper.");
	}

	public List<Contract> find(Contract entity) {
		return super.find(entity);
	}

	public Contract get(Serializable id) {
		return super.get(id);
	}

	public void insert(Contract entity) {
		super.insert(entity);
	}

	public void update(Contract entity) {
		super.update(entity);
	}

	public void delete(Serializable id) {
		super.delete(id);
	}

	public void delete(Serializable[] ids) {
		super.delete(ids);
	}

	public int getContractNo(int year) {
		return super.getSqlSession().selectOne(this.getNs()+"getContractNo",year);
	}

	public void changeState(Map<String, Object> map) {
		super.getSqlSession().update(this.getNs()+"changeState",map);
	}

	public ContractCombo combo(String id) {
		return super.getSqlSession().selectOne(this.getNs()+"combo",id);
	}

}
