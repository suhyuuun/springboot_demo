package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.MovieDTO;

@Service
public interface MovieService {
	public List<MovieDTO> list() throws Exception;
}
