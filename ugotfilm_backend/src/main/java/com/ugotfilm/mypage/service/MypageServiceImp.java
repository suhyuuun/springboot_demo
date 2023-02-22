package com.ugotfilm.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.mypage.dao.MypageDao;

@Service
public class MypageServiceImp implements MypageService{

	@Autowired
	private MypageDao dao;
	

	@Override
	public List<GenreDTO> wordcloudProcess(int usercode) {
		return dao.wordcloud(usercode);
	}

}
