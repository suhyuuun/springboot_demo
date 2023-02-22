package com.ugotfilm.choice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ugotfilm.data.dto.MovieDTO;

@Repository
@Mapper
public interface ChoiceDao {


	int exist(int moviecode);
	List<MovieDTO> genre(int usercode);
	List<MovieDTO> readcount(int usercode);
	List<MovieDTO> totalreadcount();
}
