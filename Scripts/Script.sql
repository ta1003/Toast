CREATE TABLE TOASTCAL(
	CALID NUMBER(20),
	CALTITLE VARCHAR2(50),
	CALCONTENT VARCHAR2(200),
	USERID VARCHAR2(20),
	CALTYPE VARCHAR2(10),
	PRIMARY KEY(CALID)
);

CREATE SEQUENCE TOASTCAL_SEQ START WITH 1 INCREMENT BY 1;

INSERT INTO TOASTCAL 
	VALUES(TOASTCAL_SEQ.NEXTVAL,
			'4월달시간표','으아아아','HAPPY','month');
		
CREATE TABLE VISIT(
	SEQ NUMBER NOT NULL,
	VDATE DATE NOT NULL, -- 접속 시간(로그인 시간)
	USERID VARCHAR2(20), -- 로그인 ID
	BROWSER VARCHAR2(300), -- 접속 브라우저(헤더정보)
	CONSTRAINT VISIT_PK PRIMARY KEY(SEQ)
);

CREATE SEQUENCE VISIT_SEQ START WITH 1 INCREMENT BY 1;
			
CREATE TABLE TOASTUSER(
 USERID VARCHAR2(20) NOT NULL,
 PASSWORD VARCHAR2(20) NOT NULL,
 NICKNAME VARCHAR2(50) NOT NULL,
 ADDRESS VARCHAR2(100),
 PHONE VARCHAR2(20),
 EMAIL VARCHAR2(50) NOT NULL,
 AUTH  CHAR(1) NOT NULL,
 REGDATE DATE NOT NULL,
 PRIMARY KEY(USERID) 
);