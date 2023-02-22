package com.ugotfilm.notice.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ugotfilm.notice.dto.BoardDTO;
import com.ugotfilm.notice.dto.PageDTO;
import com.ugotfilm.notice.repository.BoardService;

// http://localhost:8090/board/list


//@Controller
//@CrossOrigin("*")
//@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private PageDTO pdto;
	
	private int currentPage;
	
	@Value("${spring.servlet.multipart.location}")
	private String filePath;
	
	public BoardController() {
	
	}
	
	public void setService(BoardService service) {
		this.service = service;
	}
	
	@GetMapping("/board/list/{currentPage}")
	public Map<String, Object> listMethod(@PathVariable("currentPage") int currentPage,  PageDTO pv) {
		Map<String, Object> map = new HashMap<>();
		int totalRecord = service.countProcess();
		if(totalRecord >= 1) {
			if(pv.getCurrentPage() == 0) 
				this.currentPage = 1;
			else
				this.currentPage = pv.getCurrentPage();
			
			this.pdto = new PageDTO(currentPage, totalRecord);
			List<BoardDTO> aList=service.listProcess(this.pdto);
			map.put("aList", aList);
			map.put("pv", this.pdto);
			
		}
		return map;
	}//end listMethod()
	
	
	@GetMapping(value="/board/write")
	public ModelAndView writeMethod(BoardDTO dto, PageDTO pv, ModelAndView mav) {
		if(dto.getRef() !=0) {	//답변글이면
			mav.addObject("currentPage", pv.getCurrentPage());
			mav.addObject("dto", dto);
		}
		mav.setViewName("board/write");
		return mav;
	}//end writeMethod()
	
	//RequestBody : json => 자바객체
	//ResponseBody : 자바객체 => json
	//@PathVariable : /board/list/:num => /board/list/1 => /board/list/{num}
	//@RequestParam : /board/list?name=value => /board/list?num=1 => /board/list
	//multipart/form-data : @RequestBody 선언없이 그냥 받음 BoardDTO dto

	@PostMapping(value="/board/write")
	public String writeProMethod(BoardDTO dto, PageDTO pv, HttpServletRequest request) throws IllegalStateException, IOException {
		MultipartFile file = dto.getFilename();
		if(file!=null && !file.isEmpty()) {
			UUID random = saveCopyFile(file);
			dto.setUpload(random+"_"+file.getOriginalFilename());
			// d:\\download\\temp 경로에 첨부파일 저장
			file.transferTo(new File(random+"_"+file.getOriginalFilename()));
		}
		dto.setIp(request.getRemoteAddr());
		
		service.insertProcess(dto);

		//답변글이면
		if(dto.getRef() !=0) {
			return  String.valueOf(pv.getCurrentPage());
			//return "redirect:/board/list/courrentPage=" + pv.getCurrentPage();
		}else {	//제목글
			return String.valueOf(1);
			//return "redirect:/board/list/1";
		}
	}//end writeProMethod()
	
	@GetMapping(value="/board/update/{num}")
	public BoardDTO updateMethod(@PathVariable("num") int num) {
		System.out.println("update : " + num);
		return service.updateSelectProcess(num);
	}//end updateMethod()
	
	@PutMapping(value="/board/update" )
	public void updateProMethod(BoardDTO dto, HttpServletRequest request) throws IllegalStateException, IOException {
		MultipartFile file = dto.getFilename();
		if(file!=null && !file.isEmpty()) {
			UUID random = saveCopyFile(file);
			dto.setUpload(random+"_"+file.getOriginalFilename());
			// d:\\download\\temp 경로에 첨부파일 저장
			file.transferTo(new File(random+"_"+file.getOriginalFilename()));
		}
		dto.setIp(request.getRemoteAddr());
		
		service.updateProcess(dto, filePath);
	}//end updateProMethod()
	
	@DeleteMapping(value="/board/delete/{num}")
	public void deleteMethod(@PathVariable("num") int num, HttpServletRequest request) {
		
		service.deleteProcess(num, filePath);
		
//		int totalRecord = service.countProcess();
//		this.pdto = new PageDTO(this.currentPage, totalRecord);
		
		
	}//end deleteMethod()
	
	private UUID saveCopyFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		
		System.out.println("fileName: " + fileName );
		//중복파일명을 처리하기 위해 난수 발생
		UUID random = UUID.randomUUID();
		
		File fe = new File(filePath);
		if(!fe.exists()) {
			fe.mkdir();
		}
		
		File ff = new File(filePath, random + "_" + fileName);
		
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return random;
	}//end saveCopyFile()

//	private String urlPath(HttpServletRequest request) {
//		String root = request.getSession().getServletContext().getRealPath("/");
//		//D:\HOJIN\spring_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring_08_board
//		System.out.println("root:" + root);
//		String saveDirectory = root + "temp" + File.separator;
//		return saveDirectory;
//	}//end urlPath
	
	@GetMapping("/board/view/{num}")
	public BoardDTO viewMethod(@PathVariable("num") int num) {
		return service.contentProcess(num);
	}//viewMethod()
	

	@GetMapping("/board/contentdownload/{filename}")
	public ResponseEntity<Resource> downMethod(@PathVariable("filename") String filename) throws IOException {
	  String fileName = filename.substring(filename.indexOf("_") + 1);
	//파일명이 한글일때 인코딩 작업을 한다.
			String str = URLEncoder.encode(fileName, "UTF-8"); 
			
			//원본파일명에서 공백이 있을 때, +로 표시가 되므로 공백으로 처리해줌
			str = str.replaceAll("\\+","%20");
			System.out.println("str : " + str);
			Path path = Paths.get(filePath+"\\"+filename);
			Resource resource = new InputStreamResource(Files.newInputStream(path));
			
			System.out.println("resource:" + resource.getFilename());
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+str+";")
					.body(resource);
	}// end downMethod()

	
}//end class
