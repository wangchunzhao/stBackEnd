/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wang@dxc.com
 *
 */
public class BomExplosion {
	
	private List<Bom> boms;
	private double priceGap;

	public Boolean fillIn(List<Bom> src,List<Bom> targ) {
		
		if(this.verify(src, targ)) {
			boms = new ArrayList<Bom>();
			boms.addAll(src);
			//
			for(Bom bom:boms) {
				bom.setIsMarkedByDefault(1);
			}
			//
			for(Bom tbom:targ){
				if(tbom.getIsMarked()>0) {
					int myindex = boms.indexOf(tbom);
					Bom temp = boms.get(myindex);
					temp.setIsMarkedByConfig(1);;
					temp.setPrice(tbom.getPrice());
					temp.setIsMarked(1);;
				}
			}
			this.calPriceGap();
			return true;
		}
		return false;
	}
	private void calPriceGap() {
		for(Bom bom:boms) {
			if(bom.getIsMarkedByDefault()>0) {
				Bom peer = getConfigPeerBom(bom);
				
				BigDecimal n = new BigDecimal(peer.getPrice());
				BigDecimal o = new BigDecimal(bom.getPrice());
				
				this.priceGap  += n.subtract(o).doubleValue();
			}
		}
	}
	
	private Bom getConfigPeerBom(Bom bom){
		Set<Bom> children = new HashSet<Bom>();
		String parentCode = null;
		for(Bom bo:boms) {
			if(bo.getCode().equals(bom.getParentCode())) {
				parentCode = bo.getCode();
				break;
			}
		}
		if(parentCode!=null) {
			for(Bom bo:boms) {
				if(bo.getParentCode().equals(parentCode)) {
					if(bo.getIsMarkedByConfig()>0) {
						return bo;
					}
				}
			}
		}
		return null;
	}
	
	private boolean verify(List<Bom> srcList,List<Bom> targList) {
		if(srcList.size()!=targList.size())
			return false;
		for(Bom srcBom:srcList) {
			if(!targList.contains(srcBom))
				return false;
		}
		return true;
	}

}
