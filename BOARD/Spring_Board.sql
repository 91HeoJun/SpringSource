create table spring_board(
	bno number(10,0),
	title varchar2(200) not null,
	content varchar2(2000) not null,
	writer varchar2(20) not null,
	regdate date default sysdate,
	updatedate date default sysdate
);

alter table spring_board add constraint pk_spring_board primary key(bno);

create sequence seq_board;

select * from spring_board;

select * from member;

-- page 나누기 : rownum
select rownum, bno, title from spring_board where rownum <=10;

-- 가장 최신글 10개 가져오기
select rownum, bno, title 
from (select bno, title from spring_board order by bno desc)
where rownum<=10;

select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum, bno, title 
from spring_board
where rownum<=10;


-- 1page => 가장 최신글 10개
-- 방법 1
select rn, bno, title
from(select rownum rn, bno, title 
	 from (select bno, title from spring_board order by bno desc)
	 where rownum<=10)
where rn>0;

-- 방법 2
select rn, bno, title
from(select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum rn, bno, title 
	from spring_board
	where rownum<=10)
where rn>0;

-- 2page => 1page 다음 최신글 10개 ...
-- 방법 1
select rn, bno, title
from(select rownum rn, bno, title 
	 from (select bno, title from spring_board order by bno desc)
	 where rownum<=20)
where rn>10;

-- 방법 2
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where rownum<=20)
where rn>10;

-- 페이지 번호가 넘어오는 경우
-- 1 => 10(page번호*현재 페이지의 게시물 수), 0(page번호-1)*현재 페이지의 게시물 수
-- 2 => 20, 10
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where rownum<=10)
where rn>0;


-- 더미 삽입
insert into spring_board(bno, title, content, writer)
	(select seq_board.nextval, title, content, writer from spring_board);
	
select count(*) from spring_board;


-- 검색
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where title like '%더미%' and rownum<=10)
where rn>0;

-- 제목 || 내용
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where (title like '%더미%' or content like '%더미%') and rownum<=10)
where rn>0;

-- 제목 || 내용 || 작성자
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board) */ rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where (title like '%더미%' or content like '%더미%' or writer like '%더미%') and rownum<=10)
where rn>0;

