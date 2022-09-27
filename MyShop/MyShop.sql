-- SYS 관리자 계정
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 사용자 계정 생성
CREATE USER project1_hjh IDENTIFIED BY hjh1234;

-- 생성한 사용자 계정에 권한 부여
GRANT RESOURCE, CONNECT TO project1_hjh;

-- 테이블 스페이스 할당
ALTER USER project1_hjh DEFAULT TABLESPACE SYSTEM
QUOTA UNLIMITED ON SYSTEM;

-----------------------------------------

-- project1_hjh 계정
-- 회원 정보 테이블 생성
-- (회원번호)/아이디/비밀번호/이름/전화번호/주소/가입날짜(SYSDATE)/(탈퇴여부(N))

CREATE TABLE TB_USER(
	USER_NO NUMBER CONSTRAINT USER_NO_PK PRIMARY KEY,
	USER_ID VARCHAR2(20),
	USER_PW VARCHAR2(30) NOT NULL,
	USER_NAME VARCHAR2(30),
	USER_PHONE VARCHAR2(30),
	USER_ADDR VARCHAR2(70) NOT NULL,
	ENROLL_DATE DATE DEFAULT SYSDATE,
	SECESSION_FL CHAR(1) DEFAULT 'N' CHECK(SECESSION_FL IN ('Y', 'N')),
	CONSTRAINT USER_ID_NAME_PHONE_U UNIQUE(USER_ID, USER_NAME, USER_PHONE)
);

SELECT * FROM TB_USER;

COMMENT ON COLUMN TB_USER.USER_NO IS '회원 번호';
COMMENT ON COLUMN TB_USER.USER_ID IS '회원 아이디';
COMMENT ON COLUMN TB_USER.USER_PW IS '회원 비밀번호';
COMMENT ON COLUMN TB_USER.USER_NAME IS '회원 이름';
COMMENT ON COLUMN TB_USER.USER_PHONE IS '회원 전화번호';
COMMENT ON COLUMN TB_USER.USER_ADDR IS '회원 주소';
COMMENT ON COLUMN TB_USER.ENROLL_DATE IS '회원 가입 날짜';
COMMENT ON COLUMN TB_USER.SECESSION_FL IS '회원 탈퇴 여부';

-- SEQUENCE
CREATE SEQUENCE SEQ_USER_NO
NOCYCLE
NOCACHE;

-- 샘플 회원 INSERT 
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'asdf01', 'asdf01', '가나다', '010-1234-5678', 
	'서울시 중구 남대문로 120 대일빌딩 2층', DEFAULT, DEFAULT);

INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'aespa0', 'aespa1', '가리나', '010-1212-2323', 
	'서울시 중구 을지로 281', TO_DATE('2020-11-17 10:30:12', 'YYYY-MM-DD HH24:MI:SS'), DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'ive01', 'ive02', '장원일', '010-2112-1212', 
	'서울시 강남구 삼성로146길 4-5', TO_DATE('2021-12-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'ive03', 'ive04', '안무진', '010-1201-1111', 
	'서울시 강남구 삼성로146길 4-5', TO_DATE('2021-12-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'itzy01', 'itzy02', '황예지', '010-0212-0212', 
	'서울시 강동구 강동대로 205', TO_DATE('2019-02-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'itzy03', 'itzy04', '신유진', '010-1111-0212', 
	'서울시 강동구 강동대로 205', TO_DATE('2019-02-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'hunt01', 'hunt02', '김정재', '010-3333-1234', 
	'서울시 강남구 도산대로 430, 202호', TO_DATE('2022-08-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'hunt03', 'hunt04', '정좌성', '010-5678-9999', 
	'서울시 강남구 도산대로 430, 202호', TO_DATE('2022-08-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'omygirl', 'mimi0', '김미혜', '010-8989-9955', 
	'서울시 마포구 망원동 373-6', DEFAULT, DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'yena3', 'yena33', '최예나', '010-5999-5959', 
	'서울시 강남구 논현로 403', TO_DATE('2018-08-31 02:00:00', 'YYYY-MM-DD HH24:MI:SS'), DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'nayeon2', 'poppop', '임나연', '010-9999-9999', 
	'서울시 강동구 강동대로 205', TO_DATE('2022-09-23 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'binbin', 'bin11', '현민', '010-2345-7891', 
	'서울시 강남구 도산대로101길 30, 925빌딩 4층', DEFAULT, DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'gildong', 'gildong2', '홍길동', '010-9876-5432', 
	'서울시 중구 남대문로 120 대일빌딩 2층', DEFAULT, DEFAULT);
INSERT INTO TB_USER
VALUES(SEQ_USER_NO.NEXTVAL, 'songriver', 'river1', '손강', '010-5252-2525', 
	'서울시 강남구 언주로150길 34, 삼경빌딩 5층', DEFAULT, DEFAULT);

--DELETE FROM TB_USER
--WHERE USER_NAME = '임나연';

SELECT * FROM TB_USER;

--DROP TABLE TB_USER;
--DROP SEQUENCE SEQ_USER_NO;

COMMIT;

-- 회원가입(아이디, 비밀번호, 이름, 전화번호, 주소)
INSERT INTO TB_USER(USER_NO, USER_ID, USER_PW, USER_NAME, USER_PHONE, USER_ADDR)
VALUES(SEQ_USER_NO.NEXTVAL, 'user01', 'pass123', '유저일', '010-1111-1111', '서울시 샘플구 샘플동');
ROLLBACK;

-- 아이디 중복 확인
-- (중복되는 아이디가 입력되어도 탈퇴한 계정이면 중복 X)
SELECT COUNT(*) FROM TB_USER
WHERE USER_ID = 'asdf01'
AND SECESSION_FL = 'N';

-- 로그인
	SELECT USER_NO, USER_ID, USER_PW, USER_NAME, USER_PHONE, USER_ADDR, 
		TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') ENROLL_DATE
	FROM TB_USER
	WHERE USER_ID =?
	AND USER_PW=?
	AND SECESSION_FL = 'N';

-- 회원기능
-- 내 회원정보 조회
-- 아이디/비밀번호/이름/전화번호/주소/가입일
SELECT USER_ID, USER_PW, USER_NAME, USER_PHONE, USER_ADDR, ENROLL_DATE
FROM TB_USER
WHERE USER_ID = 'asdf01'
AND SECESSION_FL = 'N';

-- 비밀번호 수정
UPDATE TB_USER SET
USER_PW = '바꾼비밀번호'
WHERE USER_NO = 1
AND USER_PW ='현재비밀번호';

-- 회원정보 수정(전화번호, 주소)
UPDATE TB_USER SET
USER_PHONE = '010-1111-1111',
USER_ADDR ='서울시 수정구 수정동 수정한주소'
WHERE USER_NO = 1;

ROLLBACK;

-- 회원 탈퇴
	UPDATE TB_USER SET
	SECESSION_FL = 'Y'
	WHERE USER_NO = ?
	AND USER_PW = ?;

---------------------------------
-- 배송 테이블 생성
-- 배송코드/배송상태
CREATE TABLE TB_SHIPPING(
SHIPPING_CODE VARCHAR2(10) CONSTRAINT SHIPPING_CODE_PK PRIMARY KEY,
SHIPPING VARCHAR2(20)
);

COMMENT ON COLUMN TB_SHIPPING.SHIPPING_CODE IS '배송코드';
COMMENT ON COLUMN TB_SHIPPING.SHIPPING IS '배송상태';

INSERT INTO TB_SHIPPING VALUES('01', '결제 완료');
INSERT INTO TB_SHIPPING VALUES('02', '배송 준비중');
INSERT INTO TB_SHIPPING VALUES('03', '배송중');
INSERT INTO TB_SHIPPING VALUES('04', '배송 완료');
INSERT INTO TB_SHIPPING VALUES('05', '취소 처리중');
INSERT INTO TB_SHIPPING VALUES('06', '취소 완료');

SELECT * FROM TB_SHIPPING;

UPDATE TB_SHIPPING
SET SHIPPING = '배송 준비중'
WHERE SHIPPING_CODE = '02';
--DROP TABLE TB_SHIPPING;
--DELETE FROM TB_SHIPPING
--WHERE SHIPPING_CODE = 1;
COMMIT;
----------------------------------
-- 상품 테이블 생성
-- (상품번호)/상품명/옵션/가격

CREATE TABLE TB_SNACK(
	SNACK_NO NUMBER CONSTRAINT SNACK_NO_PK PRIMARY KEY,
	SNACK_NAME VARCHAR2(30) CONSTRAINT SNACK_NAME_U UNIQUE,
	PRICE NUMBER NOT NULL
);

-- 상품명에  UNIQUE 추가?

COMMENT ON COLUMN TB_SNACK.SNACK_NO IS '상품 번호';
COMMENT ON COLUMN TB_SNACK.SNACK_NAME IS '상품명';
COMMENT ON COLUMN TB_SNACK.PRICE IS '상품 금액';

CREATE SEQUENCE SEQ_SNACK_NO
NOCYCLE;

SELECT * FROM TB_SNACK;

--DROP TABLE TB_SNACK;
--DROP SEQUENCE SEQ_SNACK_NO;


-- 상품 샘플 데이터 INSERT
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '몽쉘 카카오맛', 3500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '포카칩 오리지널맛', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '포카칩 어니언맛', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '프링글스 치즈맛', 2000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '프링글스 오리지널맛', 2000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '꼬북칩', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '콘칩', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '웨하스 바닐라맛', 1000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '웨하스 딸기맛', 1000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '사또밥', 1100);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '고래밥', 1200);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '양파링', 1000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '초코송이', 1200);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '후렌치파이 딸기맛', 2000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '후렌치파이 사과맛', 2000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '썬칩', 1200);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '버터링', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '홈런볼 티라미수맛', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '홈런볼 초코맛', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '바나나킥', 1100);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '마이쮸 딸기맛', 1000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '마이쮸 복숭아맛', 1000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '마이쮸 포도맛', 1000);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '꼬깔콘 옥수수맛', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '꼬깔콘 콘소메맛', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '콘치즈', 1300);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '콘초코', 1300);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '팝콘 치즈맛', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '팝콘 고소한맛', 1500);
INSERT INTO TB_SNACK VALUES (SEQ_SNACK_NO.NEXTVAL, '새우깡', 1000);

SELECT * FROM TB_SNACK;

COMMIT;

----------------------------------
-- 주문 내역 테이블 생성
-- 주문번호/(회원번호)/주문날짜/(상품번호)/(상품명)/수량/(주문금액)/배송코드/(배송주소)/(취소여부(N))

CREATE TABLE TB_ORDER(
	ORDER_NO NUMBER CONSTRAINT ORDER_NO_PK PRIMARY KEY,
	USER_NO NUMBER CONSTRAINT USER_NO_FK REFERENCES TB_USER(USER_NO),
	ORDER_DATE DATE DEFAULT SYSDATE,
	SNACK_NO NUMBER CONSTRAINT SNACK_NO_FK REFERENCES TB_SNACK(SNACK_NO),
	QUANTITY NUMBER NOT NULL,	
	SHIPPING_CODE VARCHAR2(10) REFERENCES TB_SHIPPING(SHIPPING_CODE), -- DEFAULT '01',
	WITHDRAW CHAR(1) DEFAULT 'N' CHECK(WITHDRAW IN ('Y', 'N'))
);

COMMENT ON COLUMN TB_ORDER.ORDER_NO IS '주문 번호';
COMMENT ON COLUMN TB_ORDER.USER_NO IS '주문한 회원 번호';
COMMENT ON COLUMN TB_ORDER.ORDER_DATE IS '주문 날짜';
COMMENT ON COLUMN TB_ORDER.SNACK_NO IS '상품 번호';
COMMENT ON COLUMN TB_ORDER.QUANTITY IS '주문 수량';
COMMENT ON COLUMN TB_ORDER.SHIPPING_CODE IS '배송 코드';
COMMENT ON COLUMN TB_ORDER.WITHDRAW IS '주문 취소 여부';

--DROP TABLE TB_ORDER;
DROP SEQUENCE SEQ_ORDER_NO;


-- 주문 번호 시퀀스
-- 날짜+넘버링
CREATE SEQUENCE SEQ_ORDER_NO
NOCYCLE;

ALTER TABLE TB_ORDER MODIFY SHIPPING_CODE DEFAULT '01';

-- 샘플 주문 데이터 입력
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 1, TO_DATE('2022-08-22 20:00:00', 'YYYY-MM-DD HH24:MI:SS'), 6, 10, '05', 'Y');
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 1, TO_DATE('2022-08-22 21:00:00', 'YYYY-MM-DD HH24:MI:SS'), 6, 20, '04', DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 1, TO_DATE('2022-08-23 20:00:00', 'YYYY-MM-DD HH24:MI:SS'), 30, 5, '04', DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 5, TO_DATE('2022-09-01 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 3, '04', DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 5, TO_DATE('2022-09-01 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 1, '04', DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 5, TO_DATE('2022-09-02 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 5, 2, '04', DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 5, TO_DATE('2022-09-02 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 22, 5, '04', DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 5, TO_DATE('2022-09-03 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 11, 1, '04', DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 6, TO_DATE('2022-09-10 20:00:00', 'YYYY-MM-DD HH24:MI:SS'), 8, 2, '05', 'Y');
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 6, TO_DATE('2022-09-11 20:00:00', 'YYYY-MM-DD HH24:MI:SS'), 14, 5, '05', 'Y');
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 1, TO_DATE('2022-09-20 20:00:00', 'YYYY-MM-DD HH24:MI:SS'), 10, 3, DEFAULT, DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 2, TO_DATE('2022-09-21 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 22, 10, DEFAULT, DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 11, TO_DATE('2022-09-22 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 28, 2, DEFAULT, DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 1, TO_DATE('2022-09-22 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), 29, 3, DEFAULT, DEFAULT);
INSERT INTO TB_ORDER
VALUES (SEQ_ORDER_NO.NEXTVAL, 1, DEFAULT, 17, 1, DEFAULT, DEFAULT);

SELECT * FROM TB_ORDER;
SELECT * FROM TB_SNACK;
SELECT * FROM TB_USER;

--DELETE FROM TB_ORDER
--WHERE ORDERR_NO = 6;

COMMIT;

-- 상품 목록 조회
SELECT SNACK_NO, SNACK_NAME, PRICE
FROM TB_SNACK;

-- 주문하기(상품번호 입력받아 주문)
INSERT INTO TB_ORDER(ORDER_NO, USER_NO, SNACK_NO, QUANTITY)
VALUES (SEQ_ORDER_NO.NEXTVAL, 1, 31, 1);

-- 주문 내역 메뉴
-- 전체 주문 내역 조회
SELECT ORDER_NO, USER_NO, USER_NAME, ORDER_DATE, SNACK_NAME, QUANTITY, 
(QUANTITY*PRICE) PRICE, SHIPPING, WITHDRAW
FROM TB_ORDER
JOIN TB_USER USING(USER_NO)
JOIN TB_SNACK USING(SNACK_NO)
JOIN TB_SHIPPING USING(SHIPPING_CODE)
ORDER BY 1 DESC;


-- 내 주문 내역 조회
-- 주문번호/주문날짜/상품명/수량/가격/배송상태/(취소여부(N))
SELECT ORDER_NO, ORDER_DATE, SNACK_NAME, QUANTITY, 
	(QUANTITY*PRICE) PRICE, SHIPPING
FROM TB_ORDER
JOIN TB_SNACK USING(SNACK_NO)
JOIN TB_SHIPPING USING(SHIPPING_CODE)
WHERE USER_NO = 1
AND WITHDRAW = 'N'
ORDER BY 1 DESC;

-- 주문수량 변경 (주문번호 입력받아 수량 변경)(배송 상태가 결제완료 상태여야 함)
UPDATE TB_ORDER SET
QUANTITY = 1
WHERE ORDER_NO = 12
AND SHIPPING_CODE = '01'
AND WITHDRAW = 'N';

ROLLBACK;
COMMIT;

-- 주문 취소 (주문번호 입력받아 주문 취소)(배송 상태가 출고 전이어야 함)
UPDATE TB_ORDER SET
WITHDRAW = 'Y',
SHIPPING_CODE = '05'
WHERE ORDER_NO = 2
AND WITHDRAW = 'N';
--
SELECT * FROM TB_ORDER;

-- 내 취소 내역 조회 (취소여부 Y인 주문만 출력)
SELECT ORDER_NO, ORDER_DATE, SNACK_NAME, QUANTITY, 
	(QUANTITY*PRICE) PRICE, SHIPPING
FROM TB_ORDER
JOIN TB_SNACK USING(SNACK_NO)
JOIN TB_SHIPPING USING(SHIPPING_CODE)
WHERE USER_NO = 6
AND WITHDRAW = 'Y'
ORDER BY 1 DESC ;

SELECT ORDER_NO, ORDER_DATE, SNACK_NAME, QUANTITY, 
	(QUANTITY*PRICE) PRICE, SHIPPING
FROM TB_ORDER
JOIN TB_SNACK USING(SNACK_NO)
JOIN TB_SHIPPING USING(SHIPPING_CODE)
WHERE USER_NO = 1
AND WITHDRAW = 'N'
AND SHIPPING_CODE = '01'
ORDER BY 1 DESC;