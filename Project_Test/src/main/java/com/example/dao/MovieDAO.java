package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.dto.MovieDTO;

@Mapper
@Repository
public interface MovieDAO {
	public List<MovieDTO> movieList() throws Exception;
}
