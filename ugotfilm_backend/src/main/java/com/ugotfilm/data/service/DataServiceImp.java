package com.ugotfilm.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugotfilm.data.dao.DataDao;
import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.CastDTO;
import com.ugotfilm.data.dto.CrewDTO;

@Service
public class DataServiceImp implements DataService{

	@Autowired
	private DataDao dao;
	
	public DataServiceImp() {
	
	}
	
	@Override
	public int saveMovieProcess(MovieDTO data) {
		return dao.saveMovie(data);
	}
	
	@Override
	public int saveCrewProcess(CrewDTO data) {
		return dao.saveCrew(data);
	}

	@Override
	public List<CastDTO> saveCastProcess() {
		return dao.saveCast();
	}

	@Override
	public List<GenreDTO> saveGenreProcess() {
		return dao.saveGenre();
	}

	@Override
	public int choiceMovieProcess(int usercode, int moviecode) {
		return dao.choiceMovie(usercode, moviecode);
	}

	@Override
	public int choiceCastProcess(int usercode, int castcode) {
		return dao.choiceCast(usercode, castcode);
	}

	
	@Override
	public int choiceCrewProcess(int usercode, int crewcode) {
		return dao.choiceCrew(usercode, crewcode);
	}

	@Override
	public int choiceGenreProcess(int usercode, int genrecode) {
		return dao.choiceGenre(usercode, genrecode);
	}
}
