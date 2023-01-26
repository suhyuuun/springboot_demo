package com.example.demo.backend_todolist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.backend_todolist.dto.TodoDTO;
import com.example.demo.backend_todolist.service.TodoService;

//http://localhost:8090/todo/all


//@RestController = @Controller + @ResponseBody

@RestController
//@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;

	public TodoController() {
		System.out.println("controller");
	}
	
	@GetMapping("/")
	public String process() {
		return "hello";
	}
	
	//@ResponseBody //List<TodoDTO>가 json방식으로 넘어감
	@GetMapping("/todo/all")
	public List<TodoDTO> getList() throws Exception{
		System.out.println("all");
		return  todoService.search();
	}
}
