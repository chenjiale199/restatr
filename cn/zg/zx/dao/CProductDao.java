package cn.zg.zx.dao;

import java.io.Serializable;

import cn.zg.zx.domain.CProduct;
import cn.zg.zx.domain.Contract;

public interface CProductDao extends BaseDao<CProduct> {

	public void deleteCascade(Serializable[] ids);
}
