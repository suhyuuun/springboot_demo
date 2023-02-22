drop table t_member;

create table tb_user(
    id number,
    username varchar2(30),
    password varchar2(100),
    email varchar2(50),
    authRole varchar2(30),
    createDate Date
);

create sequence tb_user_id_seq
 start with 1 
INCREMENT by 1 
nocache 
nocycle;

select * from tb_user;

insert into tb_user
values(TB_USER_ID_SEQ.nextval, 'a1234', 'a1234', 'a1234@daum.net', null, sysdate);

commit;