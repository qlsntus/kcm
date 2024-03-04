package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Testkey;
import com.example.demo.entity.TestkeyId;
import com.example.demo.repository.TestkeyRepository;

import lombok.RequiredArgsConstructor;

//** JPA 복합키 실습 (@IdClass 방법)

@Service
@RequiredArgsConstructor 
public class TestkeyServiceImpl implements TestkeyService {

    private final TestkeyRepository repository;

    @Override
    public List<Testkey> selectList() {
    	return repository.findAll();
    }
    
    @Override
    public Testkey selectOne(TestkeyId testid) {
    	Optional<Testkey> result = repository.findById(testid);
    	if ( result.isPresent() ) return result.get();
    	else return null;
    }
    
    // => insert
    @Override
    public void save(Testkey entity) {
        repository.save(entity); // 저장 또는 수정
    }
    
	// => UPDATE
    @Override
    public void updateCount(String id, int no, int count) {
    	repository.updateCount(id, no, count);
    }
    
	// ** DUPLICATE KEY UPDATE 구문
    @Override
    public void dupUpdateCount(String id, int no, String name, int count) {
    	repository.dupUpdateCount(id, no, name, count);
    }
    
    // ** default 메서드 활용 update
    @Override
    public void calcCount(String id, int no, int count) {
    	repository.calcCount(id, no, count);
    }
    
    @Override
    public void delete(TestkeyId testid) {
    	repository.deleteById(testid);
    }

} //class
