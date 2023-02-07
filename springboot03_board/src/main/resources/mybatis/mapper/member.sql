CREATE TABLE tb_user(
    id number,
    username varchar2(20),
    password varchar2(20),
    email varchar2(50),
    authRole varchar2(30),
    createDate Date
);

CREATE SEQUENCE tb_user_id_seq 
 START WITH 1
 INCREMENT BY 1 
 NOCACHE 
 NOCYCLE;
 
INSERT INTO tb_user
VALUES(tb_user_id_seq.nextval, 'a1234', 'a1234','a1234@daum.net',null, sysdate);

SELECT * FROM tb_user;

COMMIT;