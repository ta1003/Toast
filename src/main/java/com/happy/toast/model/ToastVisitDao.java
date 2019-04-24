package com.happy.toast.model;



import java.util.List;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.happy.toast.dtos.ToastVisitDTO;

@Repository
public class ToastVisitDao {

	private final String  NS= "com.happy.toast.visit.";
		
	
	public boolean insertVisit(ToastVisitDTO dto , SqlSessionTemplate sqlSession) throws Exception {
					
		boolean isc = false;	
		int cnt = sqlSession.insert(NS+"visitInsert", dto);
		isc = cnt > 0? true:false;
		return isc;				
	}

	public int getTodayCount(SqlSessionTemplate sqlSession) throws Exception{
		return sqlSession.selectOne(NS+"visitTodayCount");
	}

	public int getTotalCount(SqlSessionTemplate sqlSession) throws Exception{
		return sqlSession.selectOne(NS+"visitTotalCount");
	}

	public List<ToastVisitDTO> selDate(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList(NS+"selDate");
		
	}
	
	public String selBrowser(SqlSessionTemplate sqlSession,String browser){
		return sqlSession.selectOne(NS+"selBrowser",browser);
	}
	
	public String weeklyCount(SqlSessionTemplate sqlSession, Map<String, String> map) {
		return sqlSession.selectOne(NS+"weeklyCount", map);
	}
	
}
