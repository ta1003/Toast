package com.happy.toast.model;

import java.util.List;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happy.toast.dtos.ToastUserDTO;

@Repository
public class ToastUserDao implements IToastUserDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String NS="com.happy.toast.user.";
	
	@Override
	public List<ToastUserDTO> userSelectAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NS+"userSelectAll");
	}

	@Override
	public int userInsert(ToastUserDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"userInsert", dto);
	}

	@Override
	public ToastUserDTO userSelectOne(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"userSelectOne", map);
	}

	@Override
	public String userIdChk(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"userIdChk",userid);
	}

	@Override
	public String userNicknameChk(String userNickname) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"userNicknameChk", userNickname);
	}

	@Override
	public String userEmailChk(String userEmail) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"userEmailChk", userEmail);
	}

}
