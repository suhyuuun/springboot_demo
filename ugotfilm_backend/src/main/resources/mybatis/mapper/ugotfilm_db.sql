
-- ȸ�� ���� ���̺�
CREATE TABLE ugotfilm_user(
    usercode number,
    username varchar2(1000),
    password varchar2(1000),
    nickname varchar2(1000),
    joindate Date,
    gender varchar2(10),
    birth number,
    authRole varchar2(100)
);

-- ȸ�� �����ڵ� ������
CREATE SEQUENCE ugotfilm_user_usercode_seq START with 1 increment by 1 nocache nocycle;

-- ������ ������ ��ȭ ���
CREATE TABLE ugotfilm_movie_choice(
    usercode number,
    moviecode number,
    choicedate date
);

-- ������ ������ �ι� ���
CREATE TABLE ugotfilm_person_choice(
    usercode number,
    personcode number,
    choicedate date
);

-- ������ ������ �帣 ���
CREATE TABLE ugotfilm_genre_choice(
    usercode number,
    genrecode number,
    choicedate date
);

-- ��ȭ ���� ���� ���̺�
CREATE TABLE ugotfilm_movie(
    moviecode number,
    title varchar2(100),
    overview varchar2(1000),
    release_date date,
    vote_average varchar2(100),
    genre_ids varchar2(100),
    poster_url varchar2(100)

);

-- �ι� ���� ���� ���̺�
CREATE TABLE ugotfilm_person(
    personcode number,       -- id
    name varchar2(100),     -- name
    job varchar2(1000),     -- known_for_department
    profile_path varchar2(200) -- profile_path
);

-- �帣 ���� ���� ���̺�
CREATE TABLE ugotfilm_genre(
    genrecode number,       -- id
    name varchar2(100)     -- name
);

-- ����
-- ȸ�� ���� ����
insert INTO ugotfilm_user VALUES (1, 'user1', '0000', '����1', sysdate, '��', 2000);
insert INTO ugotfilm_user VALUES (2, 'user2', '0000', '����2', sysdate, '��', 1990);
insert INTO ugotfilm_user VALUES (3, 'user3', '0000', '����3', sysdate, '��', 2010);
commit;

-- ��ȭ ���� ����
insert INTO ugotfilm_movie VALUES (1, '�׽�Ʈ1', '��Ÿ�� ��ȭ����', sysdate, 4.5, 1, '������ ���1');    --�帣 1 ��Ÿ�� 2 ����, 3 ��ť
insert INTO ugotfilm_movie VALUES (2, '�׽�Ʈ2', '������ȭ', sysdate, 4.0, 2, '������ ���2');
insert INTO ugotfilm_movie VALUES (3, '�׽�Ʈ3', '��ť ��ȭ1', sysdate, 4.3, 3, '������ ���3');
insert INTO ugotfilm_movie VALUES (4, '�׽�Ʈ4', '��ť ��ȭ2', sysdate, 4.3, 3, '������ ���4');

-- �ι� ���� ����

-- �帣 ���� ����
insert INTO ugotfilm_genre VALUES (1, '��Ÿ��');
insert INTO ugotfilm_genre VALUES (2, '����');
insert INTO ugotfilm_genre VALUES (3, '��ť');

-- ���� ���� ����(��ȭ ���� �����ؼ� ������ �ȴ�)
insert INTO ugotfilm_movie_choice VALUES (1, 3, sysdate);
insert INTO ugotfilm_genre_choice VALUES (1, 2, sysdate);
insert INTO ugotfilm_person_choice VALUES (2, 3, sysdate);

commit;

select * from ugotfilm_movie_choice;
select * from ugotfilm_genre_choice;
select * from ugotfilm_person_choice;


-- �پ��� ������ ���� ����
--1�� ������ ���� ��ȣ�ϴ� ��ȭ �帣(���� Ŭ���� ��)
select g.name, u.* from (select genrecode, count(genrecode) as count from ugotfilm_genre_choice where usercode=1 group by genrecode) u 
left join ugotfilm_genre g on g.genrecode=u.genrecode order by count desc;


-- 1�� ������ Ŭ���� ��ȭ ����Ʈ(���� Ŭ���� ��)
select m.* from (select moviecode, count(moviecode) as count from ugotfilm_movie_choice where usercode=1 group by moviecode) u 
left join ugotfilm_movie m on m.moviecode=u.moviecode order by count desc;
