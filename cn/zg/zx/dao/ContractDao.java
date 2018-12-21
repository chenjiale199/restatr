package cn.zg.zx.dao;

import java.util.Map;

import cn.zg.zx.domain.Contract;
import cn.zg.zx.vo.ContractCombo;

public interface ContractDao extends BaseDao<Contract> {

	public int getContractNo(int year);
	public void changeState(Map<String,Object> map);
	public ContractCombo combo(String id);
}
