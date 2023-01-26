package backend_todolist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import backend_todolist.dto.TodoDTO;

@Mapper //알아서 select한 결과가 들어감, 관계설정을 완료해줌
@Repository
public interface TodoDAO {
	public List<TodoDTO> getTOdoList() throws Exception;

}
