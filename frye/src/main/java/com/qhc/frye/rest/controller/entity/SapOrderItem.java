package com.qhc.frye.rest.controller.entity;

public class SapOrderItem {
	// Ship-to PO item/送达方-采购订单编号项目 -- Selling tool 行号
	private Integer posnr;
	// Material Number/物料编码 -- Selling tool 专用号
	private String matnr;
	// Target quantity/数量 -- Selling tool 数量
	private Integer zmeng;
	// Req.dlv.date/请求发货日期 -- Selling tool 要求发货日期
	// 格式：20191101
	private String edatu;
	// Item category/行项目类别	 -- Selling tool 行项目类别
	private String pstyv;
	// Item usage/项目用途 -- Selling tool 行项目用途
	private String vkaus;
	// Ship-to address/送达方地址 -- Selling tool 到货地址
	private String street;
	// Province/省 -- Selling tool 省
	private String region;
	// City/市 -- Selling tool 市
	private String city1;
	// District/区 -- Selling tool 区
	private String city2;
	// B2C note/B2C备注 -- Selling tool B2C备注
	private String vbbp0006;
	// Survey info. for item /调研表基本信息 -- Selling tool 调研表基本信息：商标要求，木包装要求，铭牌要求，电路图要求。
	private String vbbpz121;
	// Special note/特殊备注 -- Selling tool 特殊备注
	private String vbbpz117;
	// Color option/颜色可选项 -- Selling tool 颜色可选项信息
	private String vbbpz120;
	// Customer special request/客户物料说明 -- Selling tool 备注
	private String vbbp0007;
}
