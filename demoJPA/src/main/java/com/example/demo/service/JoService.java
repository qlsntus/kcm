package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.JoDTO;
import com.example.demo.entity.Jo;



public interface JoService {

	// ** selectList
	List<Jo> selectList();

	// ** selectOne
	Jo selectOne(int jno);

	// ** Insert,Update
	Jo save(Jo entity);

	// ** Delete
	void deleteById(int jno);

	

}