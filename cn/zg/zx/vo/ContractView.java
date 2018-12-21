package cn.zg.zx.vo;

import java.util.List;

import cn.zg.zx.domain.Contract;

public class ContractView extends Contract {

	private static final long serialVersionUID = 1L;
	private List<CProductView> cproducts;

	public List<CProductView> getCproducts() {
		return cproducts;
	}

	public void setCproducts(List<CProductView> cproducts) {
		this.cproducts = cproducts;
	}
}
