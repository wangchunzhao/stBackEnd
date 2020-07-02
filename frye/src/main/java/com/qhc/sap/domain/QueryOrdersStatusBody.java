package com.qhc.sap.domain;

public class QueryOrdersStatusBody {
	
    private String VBELN;//订单合同号
	
	private String UPDKZ;//状态   U是订单变更，I是新建订单

	public String getVBELN() {
		return VBELN;
	}

	public void setVBELN(String vBELN) {
		VBELN = vBELN;
	}

	public String getUPDKZ() {
		return UPDKZ;
	}

	public void setUPDKZ(String uPDKZ) {
		UPDKZ = uPDKZ;
	}
	
	

}
