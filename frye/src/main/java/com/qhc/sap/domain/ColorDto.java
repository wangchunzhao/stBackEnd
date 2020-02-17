package com.qhc.sap.domain;

public class ColorDto {
	
	private String material;//物料号      prclass  sap_material_product_class:material_code
	private String productClass;//成品分组    prclass   sap_material_product_class:product_class
	
	private String colorClass;//颜色分组 coclass,sap_color_class:color_class
	private String colorCode;//颜色编码   coclass,sap_color_class:color_code
	private String powderMaterial; //喷粉材料编号 coclass,sap_color_class:color_material_code
	private String materialDesc;//物料描述（短文本）   coclass,sap_color_class:color_description
	
	private String paintingClass;//     paclass,sap_painting_class:painting_class
	private String className; //     paclass ,sap_painting_class:painting_parts
	private String powderMaterialbak;//     paclass
	
	private String productClassPam;//成品分组   pamapp,sap_product_class:product_class
	private String paintingClassPam;//喷粉分组   pamapp,sap_product_class:painting_class
	private String colorClassPam;//颜色分组   pamapp,sap_product_class:color_class
	private String colorCodePam;//颜色编码   pamapp,sap_product_class:default_color
	
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getProductClass() {
		return productClass;
	}
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	public String getColorClass() {
		return colorClass;
	}
	public void setColorClass(String colorClass) {
		this.colorClass = colorClass;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getPowderMaterial() {
		return powderMaterial;
	}
	public void setPowderMaterial(String powderMaterial) {
		this.powderMaterial = powderMaterial;
	}
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	public String getPaintingClass() {
		return paintingClass;
	}
	public void setPaintingClass(String paintingClass) {
		this.paintingClass = paintingClass;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPowderMaterialbak() {
		return powderMaterialbak;
	}
	public void setPowderMaterialbak(String powderMaterialbak) {
		this.powderMaterialbak = powderMaterialbak;
	}
	public String getProductClassPam() {
		return productClassPam;
	}
	public void setProductClassPam(String productClassPam) {
		this.productClassPam = productClassPam;
	}
	public String getPaintingClassPam() {
		return paintingClassPam;
	}
	public void setPaintingClassPam(String paintingClassPam) {
		this.paintingClassPam = paintingClassPam;
	}
	public String getColorClassPam() {
		return colorClassPam;
	}
	public void setColorClassPam(String colorClassPam) {
		this.colorClassPam = colorClassPam;
	}
	public String getColorCodePam() {
		return colorCodePam;
	}
	public void setColorCodePam(String colorCodePam) {
		this.colorCodePam = colorCodePam;
	}
	
	

}
