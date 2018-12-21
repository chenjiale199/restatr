package cn.zg.zx.vo;

import java.util.Date;
import java.util.List;

public class ContractCombo {
	private String id;
	private List<CProductCombo> cproducts;
	private String importNum;
	private String offeror;
	private String contractNo;
	private String inputBy;
	private Date signingDate;
	private String checkBy;
	private String inspector;
	private String remark;
	private String crequest;
	private String printStyle;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CProductCombo> getCproducts() {
		return cproducts;
	}
	public void setCproducts(List<CProductCombo> cproducts) {
		this.cproducts = cproducts;
	}
	public String getImportNum() {
		return importNum;
	}
	public void setImportNum(String importNum) {
		this.importNum = importNum;
	}
	public String getOfferor() {
		return offeror;
	}
	public void setOfferor(String offeror) {
		this.offeror = offeror;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getInputBy() {
		return inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	public String getCheckBy() {
		return checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCrequest() {
		return crequest;
	}
	public void setCrequest(String crequest) {
		this.crequest = crequest;
	}
	public String getPrintStyle() {
		return printStyle;
	}
	public void setPrintStyle(String printStyle) {
		this.printStyle = printStyle;
	}
}
