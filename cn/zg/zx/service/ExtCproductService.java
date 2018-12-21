package cn.zg.zx.service;

import java.io.Serializable;
import java.util.List;

import cn.zg.zx.domain.ExtCproduct;

public interface ExtCproductService {
	public List<ExtCproduct> find(ExtCproduct cproduct);
	public ExtCproduct get(Serializable id);
	public void insert(ExtCproduct cproduct);
	public void update(ExtCproduct cproduct);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
}
