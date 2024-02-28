package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;


//** JPA CRUD 구현
//=> Entity 와 DTO를 용도별로 분리해서 사용
//  dtoToEntity() 와  entityToDto() 이러한 메서드추가
//  즉, default 메서드로 작성
public interface GuestbookService {
	
	// ** JPA Pageable 을 이용한 Page
	PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO);
	// => JAP CRUD 구현
	List<Guestbook> selectList();
	Guestbook selectOne(Long gno);
	Long register(GuestbookDTO dto); //insert,update 모두사용, key를 return
	void delete(Long gno);
	
	//=> dtoToEntity()
	// insert, update 위해 주로 사용되므로 redDate,modDate는 제외됨
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
				.gno(dto.getGno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();
		return entity;
	}
	
	//=> entityToDto()
	// 결과 출력시  주로 사용되므로 redDate,modDate 포함됨
	default GuestbookDTO entityToDto(Guestbook entity) {
		return GuestbookDTO.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
	}
	
}//interface
