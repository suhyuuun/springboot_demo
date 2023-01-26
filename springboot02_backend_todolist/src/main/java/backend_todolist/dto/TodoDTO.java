package backend_todolist.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
CREATE TABLE todolist(
id number PRIMARY KEY,
completed number(1) DEFAULT 0,
todoname VARCHAR2(100) NOT NULL);

CREATE SEQUENCE todo_id_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

INSERT INTO todolist  VALUES(todo_id_seq.nextval, 0, '여행');

COMMIT;

SELECT * FROM todolist;
*/



/*
 * @Data : @Getter, @Setter, @ToString, @EqualAndHashCode, @RequestedAtrgusConstructor을 합쳐놓은 어노테이션이다.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@Data -> Data 사용하기보다는 필요한 것들만 사용하기를 권장
public class TodoDTO {
	private int id;
	private int completed;
	private String todoname;
	
}
