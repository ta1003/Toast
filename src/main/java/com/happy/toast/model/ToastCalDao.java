package com.happy.toast.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happy.toast.dtos.ToastCalDTO;

@Repository
public class ToastCalDao implements IToastCalDao{

	@Autowired
	private SqlSession sqlSession;
	
	private static String NS = "com.happy.toast.cal.";
	
	@Override
	public int calInsert(ToastCalDTO dto) {
		// TODO Auto-generated method stub
		System.out.println(sqlSession);
		return sqlSession.insert(NS+"calInsert", dto);
	}

	@Override
	public int calDelete(int calid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ToastCalDTO> calAllSelect(Map<String,String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NS+"calAllSelect",map);
	}

	@Override
	public ToastCalDTO calSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calCnt() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"calCnt");
	}

}
