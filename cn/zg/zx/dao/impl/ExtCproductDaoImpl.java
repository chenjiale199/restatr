package cn.zg.zx.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.zg.zx.dao.ExtCproductDao;
import cn.zg.zx.domain.ExtCproduct;

@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao {

	public ExtCproductDaoImpl() {
		super.setNs("cn.zg.zx.mapper.ExtCproductMapper.");
	}

	public List<ExtCproduct> find(ExtCproduct entity) {
		return super.find(entity);
	}

	public ExtCproduct get(Serializable id) {
		return super.get(id);
	}

	public void insert(ExtCproduct entity) {
		super.insert(entity);
	}

	public void update(ExtCproduct entity) {
		super.update(entity);
	}

	public void delete(Serializable id) {
		super.delete(id);
	}

	public void delete(Serializable[] ids) {
		super.delete(ids);
	}

	public void deleteCascade(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs() + "deleteCascade", ids);
	}

}
