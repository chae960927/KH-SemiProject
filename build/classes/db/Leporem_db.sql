DROP TABLE L_INFO;
DROP TABLE L_CONS;
DROP TABLE L_REVIEW;
DROP TABLE L_PAY;
DROP TABLE L_NOTICE;
DROP TABLE L_SUPPORT;
DROP TABLE L_FAQ;
DROP TABLE L_REPORT;
DROP TABLE L_MEMBER;

CREATE TABLE L_MEMBER(
	USER_ID VARCHAR2(500) PRIMARY KEY,
	USER_PW VARCHAR2(500) NOT NULL,
	USER_NAME VARCHAR2(500) NOT NULL,
	NICK_NAME VARCHAR2(500) NOT NULL,
	USER_IMG VARCHAR2(1000),
	USER_ADDR VARCHAR2(4000) NOT NULL,
	USER_PHONE VARCHAR2(500) NOT NULL,
	USER_EMAIL VARCHAR2(500) NOT NULL,
	MEMBER VARCHAR2(200) NOT NULL CHECK(MEMBER IN('개인', '기업', '관리자')),
	BIZ_NUM VARCHAR2(500),
	USER_ADDR_DE VARCHAR2(500)
);
INSERT INTO L_MEMBER
VALUES('admin', 'admin', 'admin', 'admin', './images/fb.png', '강남구 테헤란로14길 남도빌딩', '010-1234-5678', 'admin@kh.ac.kr', '관리자', null);
SELECT * FROM L_MEMBER;

CREATE TABLE L_INFO(
	INFO_NO NUMBER PRIMARY KEY,
	INFO_WRITER VARCHAR2(500) NOT NULL,
	INFO_TITLE VARCHAR2(500) NOT NULL,
	INFO_CONTENT VARCHAR2(4000) NOT NULL,
	INFO_IMG VARCHAR2(1000),
	INFO_PUSH NUMBER DEFAULT 0,
	INFO_REGDATE DATE DEFAULT SYSDATE,
	CONSTRAINT INFO_WRITER_FK FOREIGN KEY(INFO_WRITER) REFERENCES L_MEMBER(USER_ID) ON DELETE CASCADE
);

CREATE TABLE L_CONS(
	CONS_NO NUMBER PRIMARY KEY,
	CONS_WRITER VARCHAR2(500) NOT NULL,
	CONS_TITLE VARCHAR2(500) NOT NULL,
	CONS_CONTENT VARCHAR2(4000) NOT NULL,
	CONS_IMG VARCHAR2(1000),
	CONS_PUSH NUMBER DEFAULT 0,
	CONS_REGDATE DATE DEFAULT SYSDATE,
	CONSTRAINT CONS_WRITER_FK FOREIGN KEY(CONS_WRITER) REFERENCES L_MEMBER(USER_ID) ON DELETE CASCADE
);
select * from L_CONS;

CREATE TABLE L_REVIEW(
	VIEW_NO NUMBER PRIMARY KEY,
	CONS_NO NUMBER NOT NULL,
	VIEW_WRITER VARCHAR2(500) NOT NULL,
	VIEW_NUM NUMBER NOT NULL,
	VIEW_REGDATE DATE NOT NULL,
	CONSTRAINT REVIEW_WRITER_FK FOREIGN KEY(VIEW_WRITER) REFERENCES L_MEMBER(USER_ID) ON DELETE CASCADE,
	CONSTRAINT REVIEW_CONS_NO_FK FOREIGN KEY(CONS_NO) REFERENCES L_CONS(CONS_NO) ON DELETE CASCADE
);

select * from L_REVIEW;
select count(view_no) from L_REVIEW;
SELECT * FROM L_REVIEW ORDER BY VIEW_REGDATE DESC;

select constraint_name, status from all_constraints where table_name = 'L_REVIEW';
SELECT * FROM ALL_CONSTRAINTS WHERE TABLE_NAME = 'L_REVIEW';
delete from ALL_CONSTRAINTS where constraint_name = 'KH.SYS_C007607';
rollback;

CREATE TABLE L_PAY(
	PAY_NO VARCHAR2(500) PRIMARY KEY,
	PAY_USER VARCHAR2(500) NOT NULL,
	PAY_COMPANY VARCHAR2(500) NOT NULL,
	PAY_NAME VARCHAR2(500) NOT NULL,
	PAY_MONEY NUMBER NOT NULL,
	PAY_INFO VARCHAR2(200) NOT NULL, --결제 수단
	PAY_REGDATE DATE DEFAULT SYSDATE,
	CONSTRAINT PAY_USER_FK FOREIGN KEY(PAY_USER) REFERENCES L_MEMBER(USER_ID) ON DELETE CASCADE
);
select * from L_PAY;

CREATE TABLE L_NOTICE(
	NOTICE_NO NUMBER PRIMARY KEY,
	NOTICE_CAT VARCHAR2(500) NOT NULL CHECK(NOTICE_CAT IN('공지', '이벤트')),
	NOTICE_WRITER VARCHAR2(500) NOT NULL,
	NOTICE_TITLE VARCHAR2(500) NOT NULL,
	NOTICE_CONTENT VARCHAR2(4000) NOT NULL,
	NOTICE_PUSH NUMBER NOT NULL,
	NOTICE_REGDATE DATE DEFAULT SYSDATE,
	CONSTRAINT NOTICE_WRITER_FK FOREIGN KEY(NOTICE_WRITER) REFERENCES L_MEMBER(USER_ID) ON DELETE CASCADE
);

select * from L_NOTICE;

CREATE TABLE L_SUPPORT(
	SUP_NO NUMBER PRIMARY KEY,
	GROUP_NO NUMBER DEFAULT 1,
	GROUP_SQ NUMBER DEFAULT 1,
	TITLE_TAB NUMBER DEFAULT 0,
	SUP_WRITER VARCHAR2(500) NOT NULL,
	SUP_TITLE VARCHAR2(500) NOT NULL,
	SUP_CONTENT VARCHAR2(4000) NOT NULL,
	SUP_PUSH NUMBER NOT NULL,
	SUP_REGDATE DATE DEFAULT SYSDATE,
	CONSTRAINT SUPPORT_WRITER_FK FOREIGN KEY(SUP_WRITER) REFERENCES L_MEMBER(USER_ID) ON DELETE CASCADE
);

SELECT * FROM L_SUPPORT WHERE GROUP_NO = (SELECT GROUP_NO FROM L_SUPPORT WHERE SUP_WRITER = '1111') ORDER BY GROUP_NO DESC, GROUP_SQ;

INSERT INTO L_SUPPORT VALUES((SELECT COUNT(SUP_NO) FROM L_SUPPORT) + 1, (SELECT COUNT(GROUP_NO) FROM L_SUPPORT) + 1, 1, 0, '3333', '채팅문의', '채팅이 이상하게 되는데요', 0, SYSDATE);

CREATE TABLE L_FAQ(
	FAQ_NO NUMBER PRIMARY KEY,
	FAQ_TITLE VARCHAR2(500) NOT NULL,
	FAQ_CONTENT VARCHAR2(4000) NOT NULL
);

select * from L_FAQ;

CREATE TABLE L_REPORT(
	REP_NO NUMBER PRIMARY KEY,
	REP_NAME VARCHAR2(500) NOT NULL,
	REP_ID VARCHAR2(500) NOT NULL,
	REP_MEMBER VARCHAR2(500) NOT NULL,
	REP_CONTENT VARCHAR2(4000) NOT NULL,
	REP_RESON VARCHAR2(2000) NOT NULL,
	REP_PUSH NUMBER NOT NULL,
	REP_REGDATE DATE DEFAULT SYSDATE,
	REP_LISTNUMBER NUMBER NOT NULL, --게시판 내 글번호
	REP_BOARD NUMBER NOT NULL,      --게시판 유형
	CONSTRAINT REPORT_NAME_FK FOREIGN KEY(REP_NAME) REFERENCES L_MEMBER(USER_ID) ON DELETE CASCADE
);

select * from L_REPORT;

CREATE TABLE L_CALENDAR(
	CAL_NUM NUMBER PRIMARY KEY,
	CAL_TITLE VARCHAR2(2000) NOT NULL,
	CAL_START VARCHAR2(100) NOT NULL,
	CAL_END VARCHAR2(100) NOT NULL,
	CAL_USER VARCHAR2(500) NOT NULL,
	CONSTRAINT CAL_USER_FK FOREIGN KEY(CAL_USER) REFERENCES L_MEMBER(USER_ID) ON DELETE CASCADE
);

select * from L_CALENDAR;
select max(CAL_NUM) from L_CALENDAR;
delete from L_CALENDAR;

CREATE TABLE L_CHAT(
	CHAT_NUM NUMBER NOT NULL,
	FROM_USER VARCHAR2(500) NOT NULL,
	TO_USER VARCHAR2(500) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	CHAT_TIME DATE DEFAULT SYSDATE
);
drop table L_CHAT;
select * from L_CHAT;
delete from L_CHAT;