package cn.zg.zx.vo;

import java.util.List;

import cn.zg.zx.domain.CProduct;

public class CProductView extends CProduct{

	private String productId;
	private List<ExtCproductView> extCproducts;
	private FactoryView factory;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public List<ExtCproductView> getExtCproducts() {
		return extCproducts;
	}
	public void setExtCproducts(List<ExtCproductView> extCproducts) {
		this.extCproducts = extCproducts;
	}
	public FactoryView getFactory() {
		return factory;
	}
	public void setFactory(FactoryView factory) {
		this.factory = factory;
	}
}
