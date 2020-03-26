package com.ex.ex1.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ex.ex1.vo.Istores;
import com.ex.ex1.vo.Istores_info;


@Repository
public class IstoreDAO {

	@Autowired
	private SqlSession session;
	
	public int insert_istore(Istores istore) {
		int cnt = 0;
		try {
			IstoreMapper mapper = session.getMapper(IstoreMapper.class);
			cnt = mapper.insert_istore(istore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	public int insert_istore_info(Istores_info istore_info) {
		int cnt = 0;
		try {
			IstoreMapper mapper = session.getMapper(IstoreMapper.class);
			cnt = mapper.insert_istore_info(istore_info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
