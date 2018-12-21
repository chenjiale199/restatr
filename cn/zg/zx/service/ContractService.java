package cn.zg.zx.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.zg.zx.domain.Contract;
import cn.zg.zx.vo.ContractCombo;

public interface ContractService {
	public List<Contract> find(Contract contract);
	public Contract get(Serializable id);
	public void insert(Contract contract);
	public void update(Contract contract);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	public String getContractNo();
	public void changeState(Map<String,Object> map);
	public ContractCombo combo(String id);
}
