drop table appendix_tbl;
drop table reserve_tbl;
drop table rental_tbl;
drop table user_tbl;
drop table book_tbl;
drop table classification_tbl;

CREATE TABLE system.user_tbl (
    user_id       INTEGER NOT NULL,
    user_name     VARCHAR2(45 BYTE),
    user_identity VARCHAR2(45 BYTE),
    user_phone    VARCHAR2(45 BYTE),
    user_addr     VARCHAR2(45 BYTE),
    user_grade    VARCHAR2(45 BYTE),
    CONSTRAINT pk_user_id PRIMARY KEY ( user_id )
);

CREATE TABLE system.classification_tbl (
    classification_id   INTEGER NOT NULL,
    classification_name VARCHAR2(45 BYTE),
    CONSTRAINT classification_tbl_pk PRIMARY KEY ( classification_id )
);

CREATE TABLE system.book_tbl (
    book_code         INTEGER NOT NULL,
    classification_id INTEGER NOT NULL,
    book_author       VARCHAR2(45 BYTE),
    book_name         VARCHAR2(45 BYTE),
    publisher         VARCHAR2(45 BYTE),
    isreserve          NUMBER,
    CONSTRAINT book_tbl_pk PRIMARY KEY ( book_code ),
    CONSTRAINT book_tbl_classification_tbl_fk FOREIGN KEY ( classification_id )
        REFERENCES system.classification_tbl ( classification_id )
            ON DELETE CASCADE
);

CREATE TABLE system.appendix_tbl (
    appendix_id   INTEGER NOT NULL,
    book_code     INTEGER NOT NULL,
    appendix_name VARCHAR2(45 BYTE),
    CONSTRAINT appendix_tbl_pk PRIMARY KEY ( appendix_id ),
    CONSTRAINT appendix_tbl_book_tbl_fk FOREIGN KEY ( book_code )
        REFERENCES system.book_tbl ( book_code )
            ON DELETE CASCADE
);

CREATE TABLE system.rental_tbl (
    rental_id INTEGER NOT NULL,
    book_code INTEGER NOT NULL,
    user_id   INTEGER NOT NULL,
    CONSTRAINT rental_tbl_pk PRIMARY KEY ( rental_id ),
    CONSTRAINT rental_tbl_book_tbl_fk FOREIGN KEY ( book_code )
        REFERENCES system.book_tbl ( book_code )
            ON DELETE CASCADE,
    CONSTRAINT rental_tbl_user_tbl_fk FOREIGN KEY ( user_id )
        REFERENCES system.user_tbl ( user_id )
            ON DELETE CASCADE
);

CREATE TABLE system.reserve_tbl (
    rental_id    INTEGER NOT NULL,
    user_id      INTEGER NOT NULL,
    reserve_order VARCHAR2(45 BYTE),
    CONSTRAINT reserve_tbl_pk PRIMARY KEY ( rental_id,
                                           user_id ),
    CONSTRAINT reserve_tbl_rental_tbl_fk FOREIGN KEY ( rental_id )
        REFERENCES system.rental_tbl ( rental_id )
            ON DELETE CASCADE,
    CONSTRAINT reserve_tbl_user_tbl_fk FOREIGN KEY ( user_id )
        REFERENCES system.user_tbl ( user_id )
            ON DELETE CASCADE
);
-- user_tbl 기본값 삽입
INSERT INTO system.user_tbl (user_id, user_name, user_identity, user_phone, user_addr, user_grade)
VALUES (1, '홍길동', 'ID12345', '010-1234-5678', '서울시 강남구', 'ROLE_ADMIN');

-- classification_tbl 기본값 삽입
INSERT INTO system.classification_tbl (classification_id, classification_name)
VALUES (1, '논문');

-- book_tbl 기본값 삽입
INSERT INTO system.book_tbl (book_code, classification_id, book_author, book_name, publisher, isreserve)
VALUES (1001, 1, '이문열', '삼국지', '민음사', 0);

-- appendix_tbl 기본값 삽입
INSERT INTO system.appendix_tbl (appendix_id, book_code, appendix_name)
VALUES (2001, 1001, '부록1');

-- rental_tbl 기본값 삽입
INSERT INTO system.rental_tbl (rental_id, book_code, user_id)
VALUES (3001, 1001, 1);

-- reserve_tbl 기본값 삽입
INSERT INTO system.reserve_tbl (rental_id, user_id, reserve_order)
VALUES (3001, 1, '1순위');

select * from reserve_tbl;

INSERT INTO system.reserve_tbl (rental_id, user_id, reserve_order)
VALUES (3002, 2, '2순위');