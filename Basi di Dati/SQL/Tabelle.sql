CREATE TABLE PROFESSOR
(
	CodP SERIAL NOT NULL,
	FirstName PERSON_NAME,
	LastName PERSON_NAME,
	Email EMAIL,
	Username VARCHAR(35),
	Pw PASSWORD_D,

	PRIMARY KEY(CodP)
);

CREATE TABLE STUDENT
(
	StudentID SERIAL NOT NULL,
	FirstName PERSON_NAME,
	LastName PERSON_NAME,
	Email EMAIL,
	Username VARCHAR(35),
	Pw PASSWORD_D,

	PRIMARY KEY(StudentID)
);

CREATE TABLE TEST
(
	CodTest SERIAL NOT NULL,
	Name TEST_NAME,
	CreationDate DATE,
	StartingDateTime TIMESTAMP,
	ClosingDateTime TIMESTAMP,
	MinScore DECIMAL(4,3),
	CodP SERIAL,

	PRIMARY KEY(CodTest),

	FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP)
);

CREATE TABLE LECTURE
(
	Title VARCHAR(30) NOT NULL UNIQUE,
	Link VARCHAR(512),
	CodP SERIAL,

	PRIMARY KEY(Title, CodP),

	FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP)
);

CREATE TABLE OPENQUIZ
(
	CodOQ SERIAL NOT NULL,
	Question VARCHAR(512) NOT NULL,
	MaxScore DECIMAL(4,3) NOT NULL,
	MinScore DECIMAL(4,3) NOT NULL,
	MaxLength INT,
	CodTest SERIAL NOT NULL,

	PRIMARY KEY(CodOQ),
	FOREIGN KEY(CodTest) REFERENCES TEST(CodTest)
);

CREATE TABLE CLOSEDQUIZ
(
	CodCQ SERIAL NOT NULL,
	Question VARCHAR(512) NOT NULL,
	AnswerA VARCHAR(128) NOT NULL,
	AnswerB VARCHAR(128) NOT NULL,
	AnswerC VARCHAR(128),
	AnswerD VARCHAR(128),
	RightAnswer CLOSED_ANSWER,
	ScoreIfRight DECIMAL(4,3) NOT NULL,
	ScoreIfWrong DECIMAL(4,3) NOT NULL,
	CodTest SERIAL NOT NULL,

	PRIMARY KEY(CodCQ),
	FOREIGN KEY(CodTest) REFERENCES TEST(CodTest)
);

CREATE TABLE CLASS
(
	CodC SERIAL NOT NULL,
	Name VARCHAR(50) NOT NULL UNIQUE,
	Year INT,
	CFU VALID_CFU,
	CodP SERIAL,

	PRIMARY KEY(CodC),

	FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP)
);

CREATE TABLE TAKE
(
	CodC SERIAL NOT NULL,
	StudentID SERIAL NOT NULL,

	PRIMARY KEY(CodC, StudentID),

	FOREIGN KEY(CodC) REFERENCES CLASS(CodC),
	FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID)
);

CREATE TABLE OPENANSWER
(
	CodOA SERIAL NOT NULL,
	GivenAnswer VARCHAR(1024),
	Score DECIMAL(4,3) DEFAULT 0,
	CodOQ SERIAL NOT NULL,
	CodTest SERIAL NOT NULL,
	StudentID SERIAL NOT NULL,

	PRIMARY KEY(CodOA),

	FOREIGN KEY(CodOQ) REFERENCES OPENQUIZ(CodOQ),
	FOREIGN KEY(CodTest) REFERENCES TEST(CodTest),
	FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID)
);

CREATE TABLE CLOSEDANSWER
(
	CodCA SERIAL NOT NULL,
	GivenAnswer CHAR,
	Score DECIMAL(4,3) DEFAULT 0,
	CodCQ SERIAL NOT NULL,
	CodTest SERIAL NOT NULL,
	StudentID SERIAL NOT NULL,

	PRIMARY KEY(CodCA),

	FOREIGN KEY(CodCQ) REFERENCES OPENQUIZ(CodOQ),
	FOREIGN KEY(CodTest) REFERENCES TEST(CodTest),
	FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID)
);

CREATE TABLE TESTTAKEN
(
	CodTest SERIAL NOT NULL,
	StudentID SERIAL NOT NULL,
	Revised BOOLEAN DEFAULT FALSE,
	Passed BOOLEAN,
	TotalScore DECIMAL(5,4) DEFAULT 0,

	PRIMARY KEY(CodTest, StudentID),

	FOREIGN KEY(CodTest) REFERENCES TEST(CodTest),
	FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID)
)