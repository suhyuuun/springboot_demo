package com.ugotfilm.mypage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ugotfilm.data.dto.GenreDTO;

@Mapper
@Repository
public interface MypageDao {

	//선호 배우 리스트
	
	//선호 감독 리스트
	
	//선호 장르 리스트
	
	
	//wordcloud
	public List<GenreDTO> wordcloud(int usercode);
}
