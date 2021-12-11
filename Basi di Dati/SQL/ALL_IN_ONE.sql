-- //---------------------------------------------------------------------------------------------------------------------------//
-- //----CREAZIONE DEI DOMINI---------------------------------------------------------------------------------------------------//
-- //---------------------------------------------------------------------------------------------------------------------------//

-- Valid_Name : I nomi non devono contenere numeri e devono avere almeno 1 carattere e al più 35 caratteri.
CREATE DOMAIN PERSON_NAME AS VARCHAR(35)
  CHECK ( VALUE <> '' AND VALUE NOT SIMILAR TO '%[0-9]+%' );

-- Valid_Test_Name : Il nome del test può avere un numero di caratteri compreso tra 1 e 55.
CREATE DOMAIN TEST_NAME AS VARCHAR(55)
  CHECK ( VALUE <> '' );

-- Valid_Email : La mail deve avere la forma di u@v.w con u, v, w stringhe non nulle
CREATE DOMAIN EMAIL AS VARCHAR(254)
	CHECK ( VALUE LIKE '_%@_%._%' );

-- Strong_Password : La password deve essere composta da più di 8 caratteri, almeno una lettera, almeno un numero ed almeno carattere speciale (!"£$%&/()=_:;,.-+*#)
CREATE DOMAIN PASSWORD_D AS VARCHAR(128)
	CHECK ( VALUE LIKE '________%');
-- Condizioni da aggiungere al dominio PASSWORD_D in qualche modo: AND VALUE LIKE '%[a-z]%' AND VALUE LIKE '%[0-9]%' AND VALUE LIKE '%[!"£$%&/()=_:;,.-+*#]%'

-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili (Dominio = {'a', 'b', 'c', 'd'})
CREATE DOMAIN CLOSED_ANSWER_D AS CHAR(1)
	CHECK ( VALUE IN ('a', 'b', 'c', 'd') ); -- Questo vincolo verrà implementato ulteriormente tramite funzione

-- Valid_CFU : Il numero di CFU deve essere compreso tra 1 e 20
CREATE DOMAIN VALID_CFU AS INTEGER
	CHECK ( VALUE BETWEEN 1 AND 20 );

-- //-------------------------------------------------------------------------//
-- CREAZIONE DELLE TABELLE
-- //-------------------------------------------------------------------------//

CREATE TABLE PROFESSOR(
	CodP SERIAL NOT NULL,
	FirstName PERSON_NAME NOT NULL,
	LastName PERSON_NAME NOT NULL,
	Email EMAIL UNIQUE NOT NULL,
	Username VARCHAR(35) UNIQUE NOT NULL,
	Pw PASSWORD_D NOT NULL
);
-- Aggiunta del vincolo di chiave primaria
ALTER TABLE PROFESSOR
	ADD CONSTRAINT professor_pk PRIMARY KEY(CodP);

-- //-------------------------------------------------------------------------//
-- Tabella STUDENT
-- //-------------------------------------------------------------------------//

CREATE TABLE STUDENT(
    StudentID SERIAL NOT NULL,
    FirstName PERSON_NAME NOT NULL,
    LastName PERSON_NAME NOT NULL,
    Email EMAIL UNIQUE NOT NULL,
    Username VARCHAR(35) UNIQUE NOT NULL,
    Pw PASSWORD_D NOT NULL
);
-- Aggiunta del vincolo di chiave primaria
ALTER TABLE STUDENT
	ADD CONSTRAINT student_pk PRIMARY KEY(StudentID);

-- //-------------------------------------------------------------------------//
-- Tabella TEST
-- //-------------------------------------------------------------------------//

CREATE TABLE TEST(
	CodTest SERIAL NOT NULL,
	Name TEST_NAME UNIQUE NOT NULL,
	CreationDateTime TIMESTAMP DEFAULT LOCALTIMESTAMP,
	StartingDateTime TIMESTAMP,
	ClosingDateTime TIMESTAMP,
	MinScore DECIMAL(4,3),
	CodP SERIAL NOT NULL
);
-- Aggiunta del vincolo di chiave primaria, e della chiave esterna sulla tabella PROFESSOR
ALTER TABLE TEST
	ADD CONSTRAINT test_pk PRIMARY KEY(CodTest),
	ADD CONSTRAINT test_fk FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP);

-- Valid_Starting_Date_Time : La data di inizio del test deve essere successiva al giorno in cui viene creato il test
ALTER TABLE TEST
ADD CONSTRAINT Valid_Starting_DateTime
	CHECK ( StartingDateTime > CreationDateTime );

-- Valid_ClosingDateTime : La differenza tra ClosingDateTime e StartingDateTime deve essere maggiore o uguale di 10 minuti
ALTER TABLE TEST
ADD CONSTRAINT Valid_ClosingDateTime
	CHECK ( DATE_PART('minute', ClosingDateTime - StartingDateTime ) >= 10);

-- //-------------------------------------------------------------------------//
-- TABELLA CLASS_T
-- //-------------------------------------------------------------------------//

CREATE TABLE CLASS_T(
    CodC SERIAL NOT NULL,
    Name VARCHAR(50) NOT NULL UNIQUE,
    Year INT DEFAULT DATE_PART('year', LOCALTIMESTAMP),
    CFU VALID_CFU NOT NULL,
    CodP SERIAL
);
-- Aggiunta del vincolo di chiave primaria, e della chiave esterna sulla tabella PROFESSOR
ALTER TABLE CLASS_T
    ADD CONSTRAINT class_pk PRIMARY KEY(CodC),
    ADD CONSTRAINT class_fk FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP);

-- //-------------------------------------------------------------------------//
-- TABELLA LECTURE
-- //-------------------------------------------------------------------------//

CREATE TABLE LECTURE(
    CodL SERIAL NOT NULL,
    Title VARCHAR(30) NOT NULL UNIQUE,
    Link VARCHAR(512),
    CodP SERIAL NOT NULL,
    CodC SERIAL NOT NULL
);
-- Aggiunta del vincolo di chiave primaria, e delle chiavi esterne sulle tabelle PROFESSOR e CLASS_T
ALTER TABLE LECTURE
	ADD CONSTRAINT lecture_pk PRIMARY KEY(CodL),
    ADD CONSTRAINT lecture_professor_fk FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP),
    ADD CONSTRAINT lecture_class_fk FOREIGN KEY(CodC) REFERENCES CLASS_T(CodC);

-- //-------------------------------------------------------------------------//
-- TABELLA OPEN_QUIZ
-- //-------------------------------------------------------------------------//

CREATE TABLE OPEN_QUIZ(
    CodOQ SERIAL NOT NULL,
	Question VARCHAR(512) NOT NULL,
	MaxScore DECIMAL(4,3) NOT NULL,
	MinScore DECIMAL(4,3) NOT NULL,
	MaxLength INT DEFAULT 1024,
	CodTest SERIAL NOT NULL
);
-- Aggiunta del vincolo di chiave primaria, e della chiave esterna sulla tabella TEST
ALTER TABLE OPEN_QUIZ
    ADD CONSTRAINT open_quiz_pk PRIMARY KEY(CodOQ),
    ADD CONSTRAINT open_quiz_fk FOREIGN KEY(CodTest) REFERENCES TEST(CodTest);

-- MaxLength_UpperBound : La possibilità della lughezza della risposta aperta deve essere compresa tra 1 e 1024
ALTER TABLE OPEN_QUIZ
ADD CONSTRAINT MaxLength_UpperBound
	CHECK ( MaxLength BETWEEN 1 AND 1024 );

-- //-------------------------------------------------------------------------//
-- TABELLA CLOSED_QUIZ
-- //-------------------------------------------------------------------------//

CREATE TABLE CLOSED_QUIZ
(
	CodCQ SERIAL NOT NULL,
	Question VARCHAR(512) NOT NULL,
	AnswerA VARCHAR(128) NOT NULL,
	AnswerB VARCHAR(128) NOT NULL,
	AnswerC VARCHAR(128),
	AnswerD VARCHAR(128),
	RightAnswer CLOSED_ANSWER_D NOT NULL,
	ScoreIfRight DECIMAL(4,3) NOT NULL,
	ScoreIfWrong DECIMAL(4,3) NOT NULL,
	CodTest SERIAL NOT NULL
);
-- Aggiunta della chiave primaria, e della chiave esterna sulla tabella TEST
ALTER TABLE CLOSED_QUIZ
    ADD CONSTRAINT closed_quiz_pk PRIMARY KEY(CodCQ),
    ADD CONSTRAINT closed_quiz_fk FOREIGN KEY(CodTest) REFERENCES TEST(CodTest);

-- //-------------------------------------------------------------------------//
-- TABELLA TAKE
-- //-------------------------------------------------------------------------//

CREATE TABLE TAKE(
    CodC SERIAL NOT NULL,
	StudentID SERIAL NOT NULL
);
-- Aggiunta della chiave primaria, delle chiavi esterne su CLASS_T e STUDENT
ALTER TABLE TAKE
    ADD CONSTRAINT take_pk PRIMARY KEY(CodC, StudentID),
    ADD CONSTRAINT take_class_fk FOREIGN KEY(CodC) REFERENCES CLASS_T(CodC),
    ADD CONSTRAINT take_student_fk FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID);

-- //-------------------------------------------------------------------------//
-- TABELLA TEST_TAKEN
-- //-------------------------------------------------------------------------//

CREATE TABLE TEST_TAKEN(
    CodTestTaken SERIAL NOT NULL,
    CodTest SERIAL NOT NULL,
    StudentID SERIAL NOT NULL,
    Revised BOOLEAN DEFAULT FALSE,
    Passed BOOLEAN,
    TotalScore DECIMAL(5,4) DEFAULT 0
);
-- Aggiunta della chiave primaria, e delle chiavi esterne su TEST e STUDENT
ALTER TABLE TEST_TAKEN
    ADD CONSTRAINT test_taken_pk PRIMARY KEY(CodTestTaken),
    ADD CONSTRAINT unique_student_test UNIQUE(StudentID, CodTest),
    ADD CONSTRAINT test_taken_test_fk FOREIGN KEY(CodTest) REFERENCES TEST(CodTest),
    ADD CONSTRAINT test_taken_student FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID);

-- //-------------------------------------------------------------------------//
-- TABELLA OPEN_ANSWER
-- //-------------------------------------------------------------------------//

CREATE TABLE OPEN_ANSWER(
    CodOA SERIAL NOT NULL,
    GivenAnswer VARCHAR(1024),
    Score DECIMAL(4,3) DEFAULT 0,
    CodOQ SERIAL NOT NULL,
    CodTest_Taken SERIAL NOT NULL,
    StudentID_Taking SERIAL NOT NULL
);
-- Aggiunta della chiave primaria, e delle chiavi esterne su OPEN_QUIZ e TEST_TAKEN
ALTER TABLE OPEN_ANSWER
    ADD CONSTRAINT open_answer_pk PRIMARY KEY(CodOA),
    ADD CONSTRAINT open_answer_open_quiz_fk FOREIGN KEY(CodOQ) REFERENCES OPEN_QUIZ(CodOQ),
    ADD CONSTRAINT open_answer_test_taken_fk FOREIGN KEY(CodTest_Taken) REFERENCES TEST_TAKEN(CodTestTaken);

-- //-------------------------------------------------------------------------//
-- TABELLA CLOSED_ANSWER
-- //-------------------------------------------------------------------------//

CREATE TABLE CLOSED_ANSWER(
    CodCA SERIAL NOT NULL,
    GivenAnswer CHAR,
	Score DECIMAL(4,3) DEFAULT 0,
	CodCQ SERIAL NOT NULL,
	CodTest_Taken SERIAL NOT NULL,
	StudentID_Taking SERIAL NOT NULL
);
-- Aggiunta della chiave primaria, e delle chiavi esterne su OPEN_QUIZ e TEST_TAKEN
ALTER TABLE CLOSED_ANSWER
    ADD CONSTRAINT closed_answer_pk PRIMARY KEY(CodCA),
    ADD CONSTRAINT closed_answer_closed_quiz_fk FOREIGN KEY(CodCQ) REFERENCES CLOSED_QUIZ(CodCQ),
    ADD CONSTRAINT closed_answer_test_taken_fk FOREIGN KEY(CodTest_Taken) REFERENCES TEST_TAKEN(CodTestTaken);
