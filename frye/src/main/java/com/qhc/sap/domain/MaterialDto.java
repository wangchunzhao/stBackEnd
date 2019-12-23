/**
 * 
 */
package com.qhc.sap.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wang@dxc.com
 *
 */
public class MaterialDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4087877900159424776L;
	
	public final static String MATERIAL_CODE = "59870645008146f9938f7e8718031778";
	
	private String code;//物料号,专用号
	private String description;//物料名称，规格型号
	private boolean isConfigurable;//是否为可配置物料
	private boolean isPurchased;//属性
	private String unitCode;//计量单位代码
	private String unitName;//计量单位名称
	
	private double acturalPrice;//产品实卖价
	private double transcationPrice;//转移价
	private double acturalPricaOfOptional;//可选项实卖价
	private double transcationPriceOfOptional;//可选项转移价
	private double b2cPriceEstimated;//B2C评估价
	private double b2cCostOfEstimated;//B2C评估成本
	private double retailPrice;//市场零售价
	private double discount;//折扣
	
	private int period;//生产、采购周期
	private Date deliveryDate;//最早交货时间

	private Date produceDate;//生产开始时间
	private Date onStoreDate;//入库时间
	private double standardPrice;//标准价
	private String groupCode;//物料分组code
	private String groupName;//物料分组名称
	private String stGroupCode;//销售工具物料分组code
	private String stGroupName;//销售工具物料分组名称
	private String clazzCode;//物料分类代码
	private String clazzName;//物料分类
	
	//need by bayern
	private Date optTime;
	private double materialSize;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isConfigurable() {
		return isConfigurable;
	}
	public void setConfigurable(boolean isConfigurable) {
		this.isConfigurable = isConfigurable;
	}
	public boolean isPurchased() {
		return isPurchased;
	}
	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public double getActuralPrice() {
		return acturalPrice;
	}
	public void setActuralPrice(double acturalPrice) {
		this.acturalPrice = acturalPrice;
	}
	public double getTranscationPrice() {
		return transcationPrice;
	}
	public void setTranscationPrice(double transcationPrice) {
		this.transcationPrice = transcationPrice;
	}
	public double getActuralPricaOfOptional() {
		return acturalPricaOfOptional;
	}
	public void setActuralPricaOfOptional(double acturalPricaOfOptional) {
		this.acturalPricaOfOptional = acturalPricaOfOptional;
	}
	public double getTranscationPriceOfOptional() {
		return transcationPriceOfOptional;
	}
	public void setTranscationPriceOfOptional(double transcationPriceOfOptional) {
		this.transcationPriceOfOptional = transcationPriceOfOptional;
	}
	public double getB2CPriceEstimated() {
		return b2cPriceEstimated;
	}
	public void setB2CPriceEstimated(double b2cPriceEstimated) {
		b2cPriceEstimated = b2cPriceEstimated;
	}
	public double getB2CCostOfEstimated() {
		return b2cCostOfEstimated;
	}
	public void setB2CCostOfEstimated(double b2cCostOfEstimated) {
		b2cCostOfEstimated = b2cCostOfEstimated;
	}
	public double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	public Date getOnStoreDate() {
		return onStoreDate;
	}
	public void setOnStoreDate(Date onStoreDate) {
		this.onStoreDate = onStoreDate;
	}
	public double getStandardPrice() {
		return standardPrice;
	}
	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}
	public Date getOptTime() {
		if(optTime==null)
			optTime = new Date();
		return optTime;
	}
	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getClazzCode() {
		return clazzCode;
	}
	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public double getMaterialSize() {
		return materialSize;
	}
	public void setMaterialSize(double materialSize) {
		this.materialSize = materialSize;
	}
	
	
	
}

          