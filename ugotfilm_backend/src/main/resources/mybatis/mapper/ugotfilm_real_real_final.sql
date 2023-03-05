-- 회원 정보 테이블
drop table ugotfilm_user;

create table ugotfilm_user(
	usercode number primary key, -- 유저 고유 번호
	username varchar2(1000), -- 유저 아이디
	password varchar2(1000), -- 유저 비밀번호
	nickname varchar2(1000), -- 유저 닉네임
	joindate Date, -- 유저 가입날짜
	gender varchar2(10), -- 유저 성별 '남', '여'
	birth	number, -- 유저 출생년도
	authRole varchar2(100) -- 유저 포지션
);

-- 회원 유저코드 시퀀스
DROP SEQUENCE ugotfilm_user_usercode_seq;

create sequence ugotfilm_user_usercode_seq start with 1 increment by 1 nocache nocycle;

-- insert 추후 수정

SELECT * FROM ugotfilm_user;

commit;

-- 영화 정보 테이블
drop table ugotfilm_movie;

create table ugotfilm_movie(
	moviecode NUMBER, -- 영화 고유 번호
	title varchar2(300), -- 영화 제목
	poster_url varchar2(100) -- 영화 포스터 url
);

commit;

SELECT * FROM ugotfilm_movie;


-- 장르 정보 테이블
drop table ugotfilm_genre;

create table ugotfilm_genre(
	genrecode NUMBER PRIMARY KEY, -- 장르 코드
	name varchar2(100) -- 장르 이름
);

insert into ugotfilm_genre VALUES (28, '액션');
insert into ugotfilm_genre VALUES (12, '모험');
insert into ugotfilm_genre VALUES (16, '애니메이션');
insert into ugotfilm_genre VALUES (35, '코미디');
insert into ugotfilm_genre VALUES (53, '스릴러');
insert into ugotfilm_genre VALUES (80, '범죄');
insert into ugotfilm_genre VALUES (18, '드라마');
insert into ugotfilm_genre VALUES (99, '다큐멘터리');
insert into ugotfilm_genre VALUES (10751, '가족');
insert into ugotfilm_genre VALUES (14, '판타지');
insert into ugotfilm_genre VALUES (36, '역사');
insert into ugotfilm_genre VALUES (27, '공포');
insert into ugotfilm_genre VALUES (10402, '음악');
insert into ugotfilm_genre VALUES (9648, '미스터리');
insert into ugotfilm_genre VALUES (10749, '로맨스');
insert into ugotfilm_genre VALUES (878, 'SF');
insert into ugotfilm_genre VALUES (10752, '전쟁');
insert into ugotfilm_genre VALUES (37, '서부');

commit;

SELECT * FROM ugotfilm_genre;

-- 배우 정보 저장 테이블
DROP TABLE ugotfilm_cast;

CREATE TABLE ugotfilm_cast(
	personcode NUMBER, -- 사람 고유 번호
	name varchar2(100), -- 사람 이름
	profile_url varchar2(100) -- 사람 이미지
);

commit;

SELECT * FROM ugotfilm_cast;

-- 감독 정보 테이블
drop table ugotfilm_director;

create table ugotfilm_director(
	personcode NUMBER, -- 사람 고유 번호
	name varchar2(100), -- 사람 이름
	profile_url varchar2(100) -- 사람 이미지 url
);

commit;

SELECT * FROM ugotfilm_director;


-- 유저 선택 기록 (영화)
drop table ugotfilm_movie_choice;

create table ugotfilm_movie_choice(
	usercode number, -- 유저 번호
	moviecode number, -- 영화 번호
	choice_date date -- 선택 날짜
);

commit;

SELECT * FROM ugotfilm_movie_choice;


-- 유저 선택 기록 (감독)
drop table ugotfilm_director_choice;

create table ugotfilm_director_choice(
	usercode number, -- 유저 번호
	personcode number, -- 사람 번호
	choice_date date -- 선택 날짜
);

commit;

SELECT * FROM ugotfilm_director_choice;

-- 유저 선택 기록 (배우)
drop table ugotfilm_cast_choice;

create table ugotfilm_cast_choice(
	usercode number, -- 유저 번호
	personcode number, -- 사람 번호
	choice_date date -- 선택 날짜
);

commit;

SELECT * FROM ugotfilm_cast_choice;


-- 유저 선택 기록 (장르)
drop table ugotfilm_genre_choice;

create table ugotfilm_genre_choice(
	usercode number, -- 유저 번호
	genrecode number, -- 장르 번호
	choice_date date -- 선택 날짜
);

commit;

SELECT * FROM ugotfilm_genre_choice;

-- 한줄평 테이블
create table ugotfilm_movie_comment(            
        pnum number,                       
        num number,                        
        writer number,                       
        reg_date date,                                         
        subject varchar2(4000),               
        ip varchar2(20)                              
);

-- 한줄평 시퀀스
create sequence ugotfilm_movie_comment_seq start with 1 increment by 1 nocache nocycle;

commit;

select * from ugotfilm_movie_comment;