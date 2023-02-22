package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MovieDAO;
import com.example.dto.MovieDTO;

@Service
public class MovieServiceImp implements MovieService{
	
	@Autowired
	private MovieDAO movieDAO;
	
	public MovieServiceImp() {
	
	}

	@Override
	public List<MovieDTO> list() throws Exception {
		return movieDAO.movieList();
	}
	
	
}
