
-- ����
drop table ugotfilm_notice_board;
drop sequence ugotfilm_notice_board_seq;
drop table ugotfilm_notice_comment;
drop sequence ugotfilm_notice_comment_seq;
--------------------------------------------------
-- ���� �Խ��� --------------------------------------
-- �����Խ��� ���̺�
create table ugotfilm_notice_board(
   	num number,                     --�۹�ȣ
   	writer number,            --�ۼ���(�����ڵ�)
	subject varchar2(1000),           --������
	reg_date date,                 --�ۼ���
	readcount number default 0,     --��ȸ��
	ref number,                     -- ��� ���� ����
	re_step number,                 -- 
	re_level number,                -- 
	content varchar2(4000),                --�۳���
	ip varchar2(20),                --ip
    upload varchar2(300)               --���ε� ����
);
-- �����Խ��� ������
create sequence ugotfilm_notice_board_seq start with 1 increment by 1 nocache nocycle;

-- �����Խ��� ����
insert into ugotfilm_notice_board 
values(ugotfilm_notice_board_seq.nextval, 1, '����1',sysdate,0,ugotfilm_notice_board_seq.nextval,
0,0,'���� �׽�Ʈ.......','127.0.0.1','sample.txt');

-- �����Խ��� ��� ���̺�
create table ugotfilm_notice_comment(            
        pnum number,                       
        num number,                        
        writer number,                       
        reg_date date,                                         
        subject varchar2(4000),               
        ip varchar2(20)                              
);

-- �����Խ��� ��� ������
create sequence ugotfilm_notice_comment_seq start with 1 increment by 1 nocache nocycle;

-- �����Խ��� ��� ����
INSERT INTO ugotfilm_notice_comment(pnum, num, writer, reg_date, subject, ip)
values(1, ugotfilm_notice_comment_seq.nextval, 1,sysdate,'��� �׽�Ʈ.......','127.0.0.1');

commit;

---Ȯ��
select * from ugotfilm_notice_board;
select * from ugotfilm_notice_comment;