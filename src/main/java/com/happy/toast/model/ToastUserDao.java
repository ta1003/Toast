package com.happy.toast.model;

import java.util.List;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.happy.toast.dtos.ToastUserDTO;

@Repository
public class ToastUserDao implements IToastUserDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static String NS="com.happy.toast.user.";
	
	@Override
	public List<ToastUserDTO> userSelectAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NS+"userSelectAll");
	}

	@Override
	public int userInsert(ToastUserDTO dto) {
		// TODO Auto-generated method stub	
		String passwordEncode = passwordEncoder.encode(dto.getPassword());
		dto.setPassword(passwordEncode);		
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

	@Override
	public boolean userPasswordChk(Map<String,String> map) {
		String password = sqlSession.selectOne(NS+"userPasswordChk", map.get("userid"));
		boolean result = passwordEncoder.matches(map.get("password"), password);
		return result;
	}

	@Override
	public boolean userBlock(Map<String, String> map) {
		// TODO Auto-generated method stub
		int n = sqlSession.update(NS+"userBlock", map);
		return n>0?true:false;
	}

	@Override
	public String userNicknameUpdateChk(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"userNicknameUpdateChk", map);
	}

	@Override
	public String userEmailUpdateChk(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"userEmailUpdateChk", map);
	}

	@Override
	public int userUpdate(ToastUserDTO dto) {
		String passwordEncode = passwordEncoder.encode(dto.getPassword());
		dto.setPassword(passwordEncode);
		return sqlSession.update(NS+"userUpdate", dto);
	}

}
