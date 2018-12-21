package cn.zg.zx.service;

import java.io.Serializable;
import java.util.List;

import cn.zg.zx.domain.CProduct;

public interface CProductService {
	public List<CProduct> find(CProduct cproduct);
	public CProduct get(Serializable id);
	public void insert(CProduct cproduct);
	public void update(CProduct cproduct);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
}
