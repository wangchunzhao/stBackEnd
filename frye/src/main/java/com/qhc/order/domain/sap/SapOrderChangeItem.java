package com.qhc.order.domain.sap;

public class SapOrderChangeItem extends SapOrderItem {
  // Reason of rejection/拒绝原因
  private String abgru;

  public String getAbgru() {
    return abgru;
  }

  public void setAbgru(String abgru) {
    this.abgru = abgru;
  }
}
