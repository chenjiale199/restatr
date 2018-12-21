package cn.zg.zx.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.zg.zx.dao.CProductDao;
import cn.zg.zx.dao.ContractDao;
import cn.zg.zx.domain.CProduct;
import cn.zg.zx.domain.Contract;

@Repository
public class CProductDaoImpl extends BaseDaoImpl<CProduct> implements CProductDao {

	public CProductDaoImpl() {
		super.setNs("cn.zg.zx.mapper.CProductMapper.");
	}

	public List<CProduct> find(CProduct entity) {
		return super.find(entity);
	}

	public CProduct get(Serializable id) {
		return super.get(id);
	}

	public void insert(CProduct entity) {
		super.insert(entity);
	}

	public void update(CProduct entity) {
		super.update(entity);
	}

	public void delete(Serializable id) {
		super.delete(id);
	}

	public void delete(Serializable[] ids) {
		super.delete(ids);
	}

	public void deleteCascade(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+"deleteCascade",ids);
	}

}
