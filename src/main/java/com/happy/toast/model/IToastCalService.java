package com.happy.toast.model;

import java.util.List;

import com.happy.toast.dtos.ToastCalDTO;

public interface IToastCalService {
	public int calInsert(ToastCalDTO dto);
	public int calDelete(int calid);
	public List<ToastCalDTO> calAllSelect();
	public ToastCalDTO calSelect();
	public int calCnt();
}
