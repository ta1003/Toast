package com.happy.toast.dtos;

public class ToastScheduleDTO {
	
	private String scheduleid;
	private String calid;
	private String title;
	private String content;
	private char isallday;
	private String startday;
	private String endday;
	private String color;
	private String state;
	private String scheduletype;
	
	public ToastScheduleDTO() {
		
	}
	
	public ToastScheduleDTO(String scheduleid, String calid, String title, String content, char isallday,
			String startday, String endday, String color, String state, String scheduletype) {
		super();
		this.scheduleid = scheduleid;
		this.calid = calid;
		this.title = title;
		this.content = content;
		this.isallday = isallday;
		this.startday = startday;
		this.endday = endday;
		this.color = color;
		this.state = state;
		this.scheduletype = scheduletype;
	}

	public String getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}

	public String getCalid() {
		return calid;
	}

	public void setCalid(String calid) {
		this.calid = calid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public char getIsallday() {
		return isallday;
	}

	public void setIsallday(char isallday) {
		this.isallday = isallday;
	}

	public String getStartday() {
		return startday;
	}

	public void setStartday(String startday) {
		this.startday = startday;
	}

	public String getEndday() {
		return endday;
	}

	public void setEndday(String endday) {
		this.endday = endday;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getScheduletype() {
		return scheduletype;
	}

	public void setScheduletype(String scheduletype) {
		this.scheduletype = scheduletype;
	}

	@Override
	public String toString() {
		return "ToastScheduleDTO [scheduleid=" + scheduleid + ", calid=" + calid + ", title=" + title + ", content="
				+ content + ", isallday=" + isallday + ", startday=" + startday + ", endday=" + endday + ", color="
				+ color + ", state=" + state + ", scheduletype=" + scheduletype + "]";
	}	
}
