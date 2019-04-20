package com.happy.toast.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happy.toast.dtos.ToastScheduleDTO;

@Repository
public class ToastScheduleDao implements IToastScheduleDao{

	@Autowired
	private SqlSession sqlSession;
	
	private static String NS = "com.happy.toast.schedule.";
	
	
	@Override
	public List<ToastScheduleDTO> scheduleSelectAll(String calid) {		
		return sqlSession.selectList(NS+"scheduleSelectAll",calid);
	}

	@Override
	public int scheduleInsert(ToastScheduleDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"scheduleInsert", dto);
	}

	@Override
	public int scheduleUpdate(ToastScheduleDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.update(NS+"scheduleUpdate", dto);
	}

	@Override
	public int scheduleDelete(String scheduleid) {
		// TODO Auto-generated method stub
		return sqlSession.delete(NS+"scheduleDelete", scheduleid);
	}

}
