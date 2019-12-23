/**
 * 
 */
package com.qhc.order.domain;

/**
 * @author wang@dxc.com
 *
 */
public class CharacteristicDto {
	
	/* Id */
	private Integer id = null;

	/* ItemDetailId */
	private Integer itemDetailId = null;

	/* 选定的特征代码 */
	private String keyCode = null;

	/* 选定的特征值的代码 */
	private String valueCode = null;

	/* 可配置 */
	private Integer isConfigurable = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemDetailId() {
		return itemDetailId;
	}

	public void setItemDetailId(Integer itemDetailId) {
		this.itemDetailId = itemDetailId;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	public Integer getIsConfigurable() {
		return isConfigurable;
	}

	public void setIsConfigurable(Integer isConfigurable) {
		this.isConfigurable = isConfigurable;
	}
	
}

