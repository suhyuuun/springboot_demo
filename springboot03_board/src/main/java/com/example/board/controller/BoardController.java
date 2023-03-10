package com.example.board.controller;

import java.io.File;
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
//import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageDTO;
import com.example.board.service.BoardService;

// http://localhost:8090/board/list

//@CrossOrigin("*")
@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
public class BoardController {

	@Autowired
	private BoardService service;
	
	@Autowired
	private PageDTO pdto;
	
	private int currentPage ;
	
	@Value("${spring.servlet.multipart.location}")
	private String filePath;
	

	public BoardController() {

	}

	public void setService(BoardService service) {
		this.service = service;
	}

	@RequestMapping("/board/list/{currentPage}")
	public Map<String, Object> listMethod(@PathVariable("currentPage") int currentPage, PageDTO pv) {
//		System.out.println("currentPage: " + currentPage);
		Map<String, Object> map = new HashMap<>();
		int totalRecord = service.countProcess();
		if (totalRecord >= 1) {
			if (pv.getCurrentPage() == 0)
				this.currentPage = 1;
			else
				this.currentPage = pv.getCurrentPage();

			this.pdto = new PageDTO(this.currentPage, totalRecord);
			List<BoardDTO> aList = service.listProcess(this.pdto);

//			System.out.println("aList: " + aList);
			map.put("aList",aList);
			map.put("pv", this.pdto);
		}

		System.out.println(map.get("aList"));
		return map;
	}// end listMethod()

	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public ModelAndView writeMethod(BoardDTO dto, PageDTO pv, ModelAndView mav) {
		if (dto.getRef() != 0) { // ???????????????
			mav.addObject("currentPage", pv.getCurrentPage());
			mav.addObject("dto", dto);
		}
		mav.setViewName("board/write");
		return mav;
	}// end writeMethod() ????????????
	
	//RequestBody : json => ????????????
	//ResponseBody : ???????????? => json
	//@PathVariable : /board/list/:num => /board/list/1 => /board/list/{num}
	//@RequestParam : /board/list?name=value => /board/list?num=1 => board/list
	//multipart/form-data : @RequestBody ???????????? ?????? ?????? BoardDTO dto

	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String writeProMethod(BoardDTO dto, PageDTO pv, HttpServletRequest request) throws IllegalStateException, IOException {
		MultipartFile file = dto.getFilename();
		if (file != null && !file.isEmpty()) {
			UUID random = saveCopyFile(file);
			dto.setUpload(random + "_" + file.getOriginalFilename());
			// c:\\download\\temp ????????? ???????????? ??????
			file.transferTo(new File(random + "_" + file.getOriginalFilename()));
		}

		dto.setIp(request.getRemoteAddr());	

		service.insertProcess(dto);

		// ???????????????
		if (dto.getRef() != 0) {
//			return "redirect:/board/list/" + pv.getCurrentPage();
			return String.valueOf(pv.getCurrentPage());
		} else { // ?????????
//			return "redirect:/board/list/1";
			return String.valueOf(1);
		}
	}// end writeProMethod()
	
	
	@RequestMapping(value="/board/update/{num}", method=RequestMethod.GET)
	public BoardDTO updateMethod(@PathVariable("num") int num) {
		return service.updateSelectProcess(num);
	}//end updateMethod()
	
	@RequestMapping(value="/board/update", method=RequestMethod.PUT) //post?????? put?????? ??????
	public void updateProMethod(BoardDTO dto, HttpServletRequest request) throws IllegalStateException, IOException {
//		System.out.printf("num: %d, content:%s, email:%s\n" ,dto.getNum(),dto.getContent(), dto.getEmail());
		MultipartFile file = dto.getFilename();
		if(file != null && !file.isEmpty()) {
			UUID random = saveCopyFile(file);
			dto.setUpload(random + "_" + file.getOriginalFilename());
			// c:\\download\\temp ????????? ???????????? ??????
			file.transferTo(new File(random + "_" + file.getOriginalFilename()));
		}
		service.updateProcess(dto, filePath);
	}//end updateProMethod
	
	
	@RequestMapping(value="/board/delete/{num}", method=RequestMethod.DELETE)
	public void deleteMethod(@PathVariable("num") int num, HttpServletRequest request) {
		service.deleteProcess(num, filePath);


	}//end deleteMethod()
	
	
	// ????????????
	private UUID saveCopyFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();

		// ?????????????????? ???????????? ?????? ?????? ??????
		UUID random = UUID.randomUUID();

//		File fe = new File(urlPath(request));
//		if (!fe.exists()) {
//			fe.mkdir();
//		}

//		File ff = new File(urlPath(request), random + "_" + fileName);
		File ff = new File(filePath, random + "_" + fileName);

		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return random;
	}// end saveCopyFile()

	private String urlPath(HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		// D:\big_study\spring_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring_08_board
		System.out.println("root:" + root);
		String saveDirectory = root + "temp" + File.separator;
		return saveDirectory;
	}// end urlPath()

	@RequestMapping("/board/view/{num}")
	public BoardDTO viewMethod(@PathVariable("num") int num) {
		return service.contentProcess(num);
	}// end viewMethod()

	@ResponseBody
	@RequestMapping("/board/contentdownload/{filename}")
	public ResponseEntity<Resource> downMethod(@PathVariable("filename") String filename) throws IOException {
	  String fileName = filename.substring(filename.indexOf("_") + 1);
	//???????????? ???????????? ????????? ????????? ??????.
			String str = URLEncoder.encode(fileName, "UTF-8"); 
			
			//????????????????????? ????????? ?????? ???, +??? ????????? ????????? ???????????? ????????????
			str = str.replaceAll("\\+","%20");
			Path path = Paths.get(filePath+"\\"+filename);
			Resource resource = new InputStreamResource(Files.newInputStream(path));
			
			System.out.println("resource:" + resource.getFilename());
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+str+";")
					.body(resource);
	}// end downMethod()

}// end class
