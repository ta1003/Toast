package com.happy.toast.dtos;

import java.io.Serializable;
import java.util.Date;

public class ToastVisitDTO implements Serializable {


	private static final long serialVersionUID = -3054506510206141004L;

	private int seq;
	private String vdate;
	private String userid;
	private String browser;
	
	
	public ToastVisitDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ToastVisitDTO(int seq, String date) {
		super();
		this.seq = seq;
		this.vdate = date;
	}
	
	
	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getVdate() {
		return vdate;
	}


	public void setVdate(String vdate) {
		this.vdate = vdate;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	
	public String getBrowser() {
		
		return browser;
	}


	public void setBrowser(String browser) {
		this.browser = browser;
	}


	@Override
	public String toString() {
		return "VisitDto [seq=" + seq + ", vdate=" + vdate + ", userid=" + userid + ", browser=" + browser + "]";
	}


	
	
	
	
	
	
	
}
