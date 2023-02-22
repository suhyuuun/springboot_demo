package com.ugotfilm.choice.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ugotfilm.data.dto.MovieDTO;

@Repository
@Mapper
public interface ChoiceRepository {


	int exist(int moviecode);
	List<MovieDTO> genre(int usercode);
	List<MovieDTO> readcount(int usercode);
	List<MovieDTO> totalreadcount();
}
