package com.example.demo.backend_todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.backend_todolist.dao.TodoDAO;
import com.example.demo.backend_todolist.dto.TodoDTO;

@Service
public class TodoServiceImp implements TodoService{
	
	@Autowired
	private TodoDAO todoDAO; //TodoDAO에 mapping된 내용들 todoDAO에 자동 생성
	
	public TodoServiceImp() {

	}

	@Override
	public List<TodoDTO> search() throws Exception {
		return todoDAO.getTodoList();
	}

	@Override
	public int insert(TodoDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
