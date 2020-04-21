/**
 * 
 */
package com.qhc.sap.entity;

/**
 * @author Walker
 *
 */ 
public class ClazzCharacteristicValueView {
	
	private int id;
	
	private String valueCode;
	
	private String valueName;
	
	private String classCode;
	
	private String keyCode;
	
	private String keyName;
    
	/*特征值*/
    private String valueId;
    
    /*默认特征值*/
    private String defaultValueId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValueId() {
    return valueId;
  }

  public void setValueId(String valueId) {
    this.valueId = valueId;
  }

  public String getDefaultValueId() {
    return defaultValueId;
  }

  public void setDefaultValueId(String defaultValueId) {
    this.defaultValueId = defaultValueId;
  }

  @Override
  public String toString() {
    return "ClazzCharacteristicValueView [id=" + id + ", valueCode=" + valueCode + ", valueName="
        + valueName + ", classCode=" + classCode + ", keyCode=" + keyCode + ", keyName=" + keyName
        + ", valueId=" + valueId + ", defaultValueId=" + defaultValueId + "]";
  }
}
