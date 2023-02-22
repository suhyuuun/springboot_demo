package com.ugotfilm.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugotfilm.data.dao.DataDao;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.PersonDTO;

@Service
public class DataServiceImp implements DataService{
	
	@Autowired
	private DataDao dao;

	public DataServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int existMovie(int moviecode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int existPerson(int personcode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveMovie(MovieDTO data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int savePerson(PersonDTO data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int choiceMovie(int usercode, int moviecode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int choicePerson(int usercode, int personcode) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
