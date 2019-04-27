package com.happy.toast.dtos;

public class ToastCalDTO {
	private int    calid;
	private String caltitle;
	private String calcontent;
	private String userid;
	private String caltype;
		
	//?îî?è¥?ä∏
	public ToastCalDTO() {
		super();
	}
	
	// ?ûÑ?ãú ?Éù?Ñ±?ûê(?ÇòÏ§ëÏóê Î∞îÍ?Í≤?)
	public ToastCalDTO(int calid, String caltitle, String calcontent, String userid, String caltype) {
		super();
		this.calid = calid;
		this.caltitle = caltitle;
		this.calcontent = calcontent;
		this.userid = userid;
		this.caltype = caltype;
	}	

	public int getCalid() {
		return calid;
	}
	public void setCalid(int calid) {
		this.calid = calid;
	}
	public String getCaltitle() {
		return caltitle;
	}
	public void setCaltitle(String caltitle) {
		this.caltitle = caltitle;
	}
	public String getCalcontent() {
		return calcontent;
	}
	public void setCalcontent(String calcontent) {
		this.calcontent = calcontent;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCaltype() {
		return caltype;
	}
	public void setCaltype(String caltype) {
		this.caltype = caltype;
	}
	@Override
	public String toString() {
		return "ToastCalDTO [calid=" + calid + ", caltitle=" + caltitle + ", calcontent=" + calcontent + ", userid="
				+ userid + ", caltype=" + caltype + "]";
	}	
}
