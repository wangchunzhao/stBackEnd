/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wang@dxc.com
 *
 */
public class BomExplosion {
	private List<Bom> src;
	private List<Bom> tag;
	private BigDecimal priceGap;
	private String rootBomCode;
	public List<Bom> getSrc() {
		return src;
	}
	public void setSrc(List<Bom> src) {
		this.src = src;
	}
	public List<Bom> getTag() {
		return tag;
	}
	public void setTag(List<Bom> tag) {
		this.tag = tag;
	}
	public BigDecimal getPriceGap() {
		if(this.priceGap==null)
			this.cal();
		return priceGap;
	}
	public String getRootBomCode() {
		if (this.rootBomCode==null)
			this.cal();
		return rootBomCode;
	}
	private void cal() {
		String scrRoot = this.findRoot(this.getSrc());
		String tagRoot = this.findRoot(this.getTag());
		if(scrRoot!=null && tagRoot!=null) {
			if(scrRoot.equals(tagRoot)) {
				this.rootBomCode = scrRoot;
			}
		}
		
		
		
	}
	private Set<String> findLeaf(final List<Bom> boms) {
		return null;
	}
	private String findRoot(final List<Bom> boms) {
		String root = null;
		Set<String> bomCode = new HashSet<String>();
		Set<String> parBomCode = new HashSet<String>();
		for(Bom bom:boms) {
			bomCode.add(bom.getCode());
			parBomCode.add(bom.getParentCode());
		}
		for(String p:parBomCode ) {
			if(!bomCode.contains(p)) {
				if(root==null)
					root = p;
				else
					return null;
			}
		}
		return root;
	}

}
