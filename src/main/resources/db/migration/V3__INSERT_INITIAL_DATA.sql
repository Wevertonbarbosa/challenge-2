insert into person VALUES('fiap@teste.com', 'fiap');
insert into USERS VALUES(9999,1,'fiap','$2a$12$/m786MGbhS/B8uORGh7BV.ngE.e1nzf4gUGovKwaAnWShoizbEvz2','USER','fiap@teste.com');
INSERT INTO email (id, atc, bdy, ctg, dat, ibx, red, rcp, snd, sbj)
VALUES (100, 'attachment1.pdf', 'Body of the first email', 'Trabalho', CURRENT_TIMESTAMP, 1, 0, 'receiver1@example.com', 'sender1@example.com', 'Subject 1');

INSERT INTO email (id, atc, bdy, ctg, dat, ibx, red, rcp, snd, sbj)
VALUES (101, 'attachment2.pdf', 'Body of the second email', 'Social', CURRENT_TIMESTAMP, 1, 0, 'receiver2@example.com', 'sender2@example.com', 'Subject 2');

INSERT INTO email (id, atc, bdy, ctg, dat, ibx, red, rcp, snd, sbj)
VALUES (103, NULL, 'Body of the third email', 'Spam', CURRENT_TIMESTAMP, 1, 1, 'receiver3@example.com', 'sender3@example.com', 'Subject 3');

INSERT INTO email (id, atc, bdy, ctg, dat, ibx, red, rcp, snd, sbj)
VALUES (104, 'attachment4.zip', 'Body of the fourth email', 'Trabalho', CURRENT_TIMESTAMP, 1, 0, 'receiver4@example.com', 'sender4@example.com', 'Subject 4');

INSERT INTO email (id, atc, bdy, ctg, dat, ibx, red, rcp, snd, sbj)
VALUES (106, NULL, 'Body of the fifth email', 'Urgente', CURRENT_TIMESTAMP, 1, 1, 'receiver5@example.com', 'sender5@example.com', 'Subject 5');

INSERT INTO spam_words (wrd, trt_lvl) VALUES ('free', 50);
INSERT INTO spam_words (wrd, trt_lvl) VALUES ('win', 59);
INSERT INTO spam_words (wrd, trt_lvl) VALUES ('guarantee', 4);
INSERT INTO spam_words (wrd, trt_lvl) VALUES ('cash', 5);
INSERT INTO spam_words (wrd, trt_lvl) VALUES ('prize', 4);
INSERT INTO spam_words (wrd, trt_lvl) VALUES ('urgent', 3);
INSERT INTO spam_words (wrd, trt_lvl) VALUES ('offer', 80);
INSERT INTO spam_words (wrd, trt_lvl) VALUES ('click', 3);
INSERT INTO spam_words (wrd, trt_lvl) VALUES ('buy', 4);
INSERT INTO spam_words (wrd, trt_lvl) VALUES ('discount', 3);
