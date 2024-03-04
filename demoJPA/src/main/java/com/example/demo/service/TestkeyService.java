package com.example.demo.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Testkey;
import com.example.demo.entity.TestkeyId;

public interface TestkeyService {
	
	// ** selectList
	List<Testkey> selectList();

	// ** selectOne
	Testkey selectOne(TestkeyId testid);

	// ** insert
	void save(Testkey entity);
	
	// ** UPDATE
	void updateCount(String id, int no, int count);
	
	// ** DUPLICATE KEY UPDATE 구문
	void dupUpdateCount(String id, int no, String name, int count);
	
	// ** default 메서드 활용 update
	void calcCount(String id, int no, int count);

	// ** delete
	void delete(TestkeyId testid);

} //class