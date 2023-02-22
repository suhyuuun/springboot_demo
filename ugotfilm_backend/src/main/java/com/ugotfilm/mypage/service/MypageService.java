package com.ugotfilm.mypage.service;

import java.util.List;

import com.ugotfilm.data.dto.GenreDTO;


public interface MypageService {
	
	//wordcloud
	public List<GenreDTO> wordcloudProcess(int usercode);
}
