package com.min.edu.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dtos.ToastCalDTO;

@Repository
public class ToastCalDao implements IToastCalDao{

	@Autowired
	private SqlSession sqlSession;
	
	private static String NS = "com.min.edu.cal.";
	
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
	public List<ToastCalDTO> calAllSelect() {
		// TODO Auto-generated method stub
		return null;
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
