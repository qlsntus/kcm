package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.JoDTO;



public interface JoService {

	// ** selectList
	List<JoDTO> selectList();

	// ** selectOne
	JoDTO selectOne(JoDTO dto);

	// ** Insert
	int insert(JoDTO dto);

	// ** Update
	int update(JoDTO dto);

	// ** Delete
	int delete(JoDTO dto);

}