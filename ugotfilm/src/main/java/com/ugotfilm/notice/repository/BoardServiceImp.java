package com.ugotfilm.notice.repository;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugotfilm.notice.repository.BoardDAO;
import com.ugotfilm.notice.dto.BoardDTO;
import com.ugotfilm.notice.dto.PageDTO;


@Service
public class BoardServiceImp implements BoardService{
	@Autowired
	private BoardDAO dao;
	
	public BoardServiceImp() {
	
	}

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public int countProcess() {
		return dao.count();
	}

	@Override
	public List<BoardDTO> listProcess(PageDTO pv) {
		return dao.list(pv);
	}

	@Override
	public void insertProcess(BoardDTO dto) {
		
		//답변글이면
		if(dto.getRef()!=0) {
			dao.reStepCount(dto);
			dto.setRe_step(dto.getRe_step() + 1);
			dto.setRe_level(dto.getRe_level() + 1);
		}
		
		dao.save(dto);
	}

	@Override
	public BoardDTO contentProcess(int num) {
		dao.readCount(num);
		return dao.content(num);
	}

	@Override
	public void reStepProcess(BoardDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardDTO updateSelectProcess(int num) {
		return dao.content(num);
	}

	@Override
	public void updateProcess(BoardDTO dto, String urlpath) {
		String filename = dto.getUpload();
		
		//수정한 파일이 있으면
		if(filename != null) {
			String path = dao.getFile(dto.getNum());
			//기존 첨부파일이 있으면
			if(path!=null) {
				File file = new File(urlpath, path);
				file.delete();
			}
			
		}
		
		dao.update(dto);
		
	}

	@Override
	public void deleteProcess(int num, String urlpath) {
		String path = dao.getFile(num);
		
		//num컬럼에 해당하는 첨부파일이 있으면 
		if(path!=null) {
			System.out.println("path : " + path);
			File fe = new File(urlpath, path);
			fe.delete();
		}
		
		dao.delete(num);
	}

	@Override
	public String fileSelectprocess(int num) {
		return dao.getFile(num);
	}

	
	
	
}//end class
