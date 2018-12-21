package cn.zg.zx.vo;

public class CProductCombo {

	private String productId;
	private String productNo;
	private FactoryCombo factory;
	private String productImage;
	private String productDesc;
	private Short cnumber;
	public String packingUnit;
	public Short price;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public FactoryCombo getFactory() {
		return factory;
	}
	public void setFactory(FactoryCombo factory) {
		this.factory = factory;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Short getCnumber() {
		return cnumber;
	}
	public void setCnumber(Short cnumber) {
		this.cnumber = cnumber;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public Short getPrice() {
		return price;
	}
	public void setPrice(Short price) {
		this.price = price;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
}
