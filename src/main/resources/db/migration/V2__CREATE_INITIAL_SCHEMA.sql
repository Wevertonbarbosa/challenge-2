CREATE TABLE email (
    id NUMBER(20) PRIMARY KEY NOT NULL,
    snd VARCHAR2(100) NOT NULL,
    bdy VARCHAR2(200) NOT NULL,
    sbj VARCHAR2(100) NOT NULL,
    rcp VARCHAR2(100) NOT NULL,
    atc  VARCHAR2(100),
    ctg VARCHAR2(100),
    dat TIMESTAMP NOT NULL,
    red NUMBER(1) NOT NULL,
    ibx NUMBER(1) NOT NULL
);

CREATE TABLE person (
    eml VARCHAR2(255) PRIMARY KEY,
    nam VARCHAR2(255) NOT NULL
);


CREATE TABLE cc_email(
    id_eml INTEGER NOT NULL,
    cc_eml  VARCHAR2(30) NOT NULL,
    FOREIGN KEY (id_eml) REFERENCES email(id)
);

CREATE TABLE preference(
    email VARCHAR2(255) NOT NULL,
    thm VARCHAR2(10) NOT NULL,
    fnt_siz INTEGER NOT NULL,
    FOREIGN KEY (email) REFERENCES person(eml)
);

CREATE TABLE users (
    id_use NUMBER(20) PRIMARY KEY,
    act NUMBER(1) NOT NULL,
    log VARCHAR2(50) NOT NULL,
    pwd VARCHAR2(255) NOT NULL,
    role VARCHAR2(50) DEFAULT 'USER',
    id_per VARCHAR2(255) NOT NULL,
    FOREIGN KEY (id_per) REFERENCES person(eml)
);

CREATE TABLE spam_words(
wrd VARCHAR2(20) PRIMARY KEY NOT NULL,
trt_lvl INTEGER NOT NULL
);