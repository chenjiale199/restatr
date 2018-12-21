package cn.zg.zx.vo;

import cn.zg.zx.domain.ExtCproduct;

public class ExtCproductView extends ExtCproduct {

	private String extCproductId;
	private FactoryView factory;

	public FactoryView getFactory() {
		return factory;
	}

	public void setFactory(FactoryView factory) {
		this.factory = factory;
	}

	public String getExtCproductId() {
		return extCproductId;
	}

	public void setExtCproductId(String extCproductId) {
		this.extCproductId = extCproductId;
	}
}
