package cn.zg.zx.dao;

import java.io.Serializable;

import cn.zg.zx.domain.ExtCproduct;

public interface ExtCproductDao extends BaseDao<ExtCproduct> {

	public void deleteCascade(Serializable[] ids);
}
