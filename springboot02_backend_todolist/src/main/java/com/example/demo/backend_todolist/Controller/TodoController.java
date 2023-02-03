package com.example.demo.backend_todolist.Controller;

import java.nio.charset.Charset;
//import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.backend_todolist.dto.TodoDTO;
import com.example.demo.backend_todolist.service.TodoService;

//http://localhost:8090/todo/all

//@RestController = @Controller + @ResponseBody

@CrossOrigin("*")
//@CrossOrigin(origins = {"http://localhost:3000"})// 여러개 사용할 때는 배열 사용{}
@RestController // 선언되어있는 것들을 데이터로만 보내겠다.
//@Controller
public class TodoController {

	@Autowired
	private TodoService todoService; // 생성자에 autowired 넣어주기

	public TodoController() {
		System.out.println("controller");
	}

	@GetMapping("/")
	public String process() {
		return "hello";
	}

	// http://localhost:8090/todo/all
	// @ResponseBody //List<TodoDTO>가 json방식으로 넘어감
	@GetMapping("/todo/all")
	public List<TodoDTO> getList() throws Exception {
		System.out.println("all");
		return todoService.search();
	}//end getList

	// http://localhost:8090/todo
	@PostMapping("/todo")
	public ResponseEntity<Object> postTodo(@RequestBody TodoDTO dto) throws Exception {
		System.out.println(dto.getTodoname());
		int chk = todoService.insert(dto);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		HashMap<String, String> map = new HashMap<>();
		map.put("createDate", new Date().toString());
		map.put("message", "insert OK");

		if (chk >= 1) {
//			ResponseEntity<Object>(body, headers, status)
//			return new ResponseEntity<Object>(HttpStatus.OK); //이런 형태 많이 사용
//			return new ResponseEntity<Object>(header, HttpStatus.OK);
//			return new ResponseEntity<Object>(map, header, HttpStatus.OK); //이런 형태 많이 사용
//			return new ResponseEntity<Object>(header, HttpStatus.OK); //HashMap 사용시 header 혼자 못보냄
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
		}
	}//end postTodo
	
	//todo?id=a&complete=0 와 다름 -> requestParam
	// http://localhost:8090/todo/1/0
	//pathVariable 사용하는 경우는 /사용하여 param값을 넘겨줄 때 사용
	@PutMapping("/todo/{id}/{completed}")
	public void putTodo(@PathVariable("id") int id, @PathVariable("completed") int completed) throws Exception{
		System.out.printf("id=%d, completed=%d\n", id, completed);
		TodoDTO dto = new TodoDTO();
		dto.setId(id);
		dto.setCompleted(completed==0? 1 : 0);
		todoService.update(dto);
	}//end putTodo
	
	// http://localhost:8090/todo/1
	@DeleteMapping("/todo/{id}")
	public void deleteTodo(@PathVariable("id") int id) throws Exception{
		System.out.println("id: " + id);
		todoService.delete(id);
	}

}//end class
