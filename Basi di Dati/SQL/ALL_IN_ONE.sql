-- //-------------------------------------------------------------------------//
-- CREAZIONE DEI DOMINI
-- //-------------------------------------------------------------------------//

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
	CHECK (VALUE ~ '^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).*$' AND VALUE LIKE '________%');

-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili (Dominio = {'a', 'b', 'c', 'd'})
CREATE DOMAIN CLOSED_ANSWER_D AS CHAR(1)
	CHECK ( VALUE IN ('a', 'b', 'c', 'd') ); -- Questo vincolo verrà implementato ulteriormente tramite funzione

-- Valid_CFU : Il numero di CFU deve essere compreso tra 1 e 20
CREATE DOMAIN VALID_CFU AS INTEGER
	CHECK ( VALUE BETWEEN 1 AND 20 );

-- Dominio per i punteggi dei quiz
CREATE DOMAIN SCORE_D AS FLOAT ;

-- //-------------------------------------------------------------------------//
-- CREAZIONE DELLE TABELLE
-- //-------------------------------------------------------------------------//

-- //-------------------------------------------------------------------------//
-- Tabella PROFESSOR

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

CREATE TABLE TEST(
	CodTest SERIAL NOT NULL,
	Name TEST_NAME UNIQUE NOT NULL,
	CreationDateTime TIMESTAMP DEFAULT LOCALTIMESTAMP,
	StartingDateTime TIMESTAMP,
	ClosingDateTime TIMESTAMP,
	MinScore SCORE_D,
	CodP SERIAL NOT NULL
);
-- Aggiunta del vincolo di chiave primaria, e della chiave esterna sulla tabella PROFESSOR
ALTER TABLE TEST
	ADD CONSTRAINT test_pk PRIMARY KEY(CodTest),
	ADD CONSTRAINT test_fk FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

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
	ADD CONSTRAINT class_fk FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

-- //-------------------------------------------------------------------------//
-- TABELLA LECTURE

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
	ADD CONSTRAINT lecture_professor_fk FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	ADD CONSTRAINT lecture_class_fk FOREIGN KEY(CodC) REFERENCES CLASS_T(CodC)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

-- //-------------------------------------------------------------------------//
-- TABELLA OPEN_QUIZ

CREATE TABLE OPEN_QUIZ(
	CodOQ SERIAL NOT NULL,
	Question VARCHAR(512) NOT NULL,
	MaxScore SCORE_D NOT NULL,
	MinScore SCORE_D NOT NULL,
	MaxLength INT DEFAULT 1024,
	CodTest SERIAL NOT NULL
);
-- Aggiunta del vincolo di chiave primaria, e della chiave esterna sulla tabella TEST
ALTER TABLE OPEN_QUIZ
	ADD CONSTRAINT open_quiz_pk PRIMARY KEY(CodOQ),
	ADD CONSTRAINT open_quiz_fk FOREIGN KEY(CodTest) REFERENCES TEST(CodTest)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

-- MaxLength_UpperBound : La possibilità della lughezza della risposta aperta deve essere compresa tra 1 e 1024
ALTER TABLE OPEN_QUIZ
	ADD CONSTRAINT MaxLength_UpperBound
		CHECK ( MaxLength BETWEEN 1 AND 1024 );

-- //-------------------------------------------------------------------------//
-- TABELLA CLOSED_QUIZ

CREATE TABLE CLOSED_QUIZ(
	CodCQ SERIAL NOT NULL,
	Question VARCHAR(512) NOT NULL,
	AnswerA VARCHAR(128) NOT NULL,
	AnswerB VARCHAR(128) NOT NULL,
	AnswerC VARCHAR(128),
	AnswerD VARCHAR(128),
	RightAnswer CLOSED_ANSWER_D NOT NULL,
	ScoreIfRight SCORE_D NOT NULL,
	ScoreIfWrong SCORE_D NOT NULL,
	CodTest SERIAL NOT NULL
);
-- Aggiunta della chiave primaria, e della chiave esterna sulla tabella TEST
ALTER TABLE CLOSED_QUIZ
	ADD CONSTRAINT closed_quiz_pk PRIMARY KEY(CodCQ),
	ADD CONSTRAINT closed_quiz_fk FOREIGN KEY(CodTest) REFERENCES TEST(CodTest)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

-- //-------------------------------------------------------------------------//
-- TABELLA TAKE

CREATE TABLE TAKE(
	CodC SERIAL NOT NULL,
	StudentID SERIAL NOT NULL
);
-- Aggiunta della chiave primaria, delle chiavi esterne su CLASS_T e STUDENT
ALTER TABLE TAKE
	ADD CONSTRAINT take_pk PRIMARY KEY(CodC, StudentID),
	ADD CONSTRAINT take_class_fk FOREIGN KEY(CodC) REFERENCES CLASS_T(CodC)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	ADD CONSTRAINT take_student_fk FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

-- //-------------------------------------------------------------------------//
-- TABELLA TEST_TAKEN

CREATE TABLE TEST_TAKEN(
	CodTestTaken SERIAL NOT NULL,
	CodTest SERIAL NOT NULL,
	StudentID SERIAL NOT NULL,
	Revised BOOLEAN DEFAULT FALSE,
	Passed BOOLEAN,
	TotalScore SCORE_D DEFAULT 0
);
-- Aggiunta della chiave primaria, e delle chiavi esterne su TEST e STUDENT
ALTER TABLE TEST_TAKEN
	ADD CONSTRAINT test_taken_pk PRIMARY KEY(CodTestTaken),
	ADD CONSTRAINT unique_student_test UNIQUE(StudentID, CodTest),
	ADD CONSTRAINT test_taken_test_fk FOREIGN KEY(CodTest) REFERENCES TEST(CodTest)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	ADD CONSTRAINT test_taken_student FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

-- //-------------------------------------------------------------------------//
-- TABELLA OPEN_ANSWER

CREATE TABLE OPEN_ANSWER(
	CodOA SERIAL NOT NULL,
	GivenAnswer VARCHAR(1024),
	Score SCORE_D DEFAULT 0,
	CodOQ SERIAL NOT NULL,
	CodTest_Taken SERIAL NOT NULL
);
-- Aggiunta della chiave primaria, e delle chiavi esterne su OPEN_QUIZ e TEST_TAKEN
ALTER TABLE OPEN_ANSWER
	ADD CONSTRAINT open_answer_pk PRIMARY KEY(CodOA),
	ADD CONSTRAINT open_answer_open_quiz_fk FOREIGN KEY(CodOQ) REFERENCES OPEN_QUIZ(CodOQ)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	ADD CONSTRAINT open_answer_test_taken_fk FOREIGN KEY(CodTest_Taken) REFERENCES TEST_TAKEN(CodTestTaken)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

-- //-------------------------------------------------------------------------//
-- TABELLA CLOSED_ANSWER

CREATE TABLE CLOSED_ANSWER(
	CodCA SERIAL NOT NULL,
	GivenAnswer CHAR,
	Score SCORE_D DEFAULT 0,
	CodCQ SERIAL NOT NULL,
	CodTest_Taken SERIAL NOT NULL
);
-- Aggiunta della chiave primaria, e delle chiavi esterne su OPEN_QUIZ e TEST_TAKEN
ALTER TABLE CLOSED_ANSWER
	ADD CONSTRAINT closed_answer_pk PRIMARY KEY(CodCA),
	ADD CONSTRAINT closed_answer_closed_quiz_fk FOREIGN KEY(CodCQ) REFERENCES CLOSED_QUIZ(CodCQ)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	ADD CONSTRAINT closed_answer_test_taken_fk FOREIGN KEY(CodTest_Taken) REFERENCES TEST_TAKEN(CodTestTaken)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

-- //-------------------------------------------------------------------------//
-- Funzioni e Trigger
-- //-------------------------------------------------------------------------//

-- //-------------------------------------------------------------------------//
-- Update_Closed_Quiz_Score : Correzione automatica delle domande a risposta chiusa
CREATE FUNCTION UCQS_function() RETURNS TRIGGER AS $Update_CQ_Score$
DECLARE
    ScoreRight CLOSED_QUIZ.ScoreIfRight%TYPE;
    ScoreWrong CLOSED_QUIZ.ScoreIfWrong%TYPE;
    RA CLOSED_QUIZ.RightAnswer%TYPE;
BEGIN
    -- Salvo i valori
    SELECT ScoreIfRight, ScoreIfWrong, RightAnswer
    INTO ScoreRight, ScoreWrong, RA
    FROM CLOSED_ANSWER CA JOIN CLOSED_QUIZ CQ ON CA.CodCQ = CQ.CodCQ
    WHERE CA.CodCA = NEW.CodCA;

    IF (NEW.GivenAnswer = RA) THEN -- Aggiorno se la risposta è corretta
        UPDATE CLOSED_ANSWER
        SET Score = ScoreRight
        WHERE CodCA = NEW.CodCA;
	END IF;

    IF (NEW.GivenAnswer <> RA AND NEW.GivenAnswer IS NOT NULL) THEN -- Aggiorno se la risposta è sbagliata
        UPDATE CLOSED_ANSWER
        SET Score = ScoreWrong
        WHERE CodCA = NEW.CodCA;
    END IF;
	RETURN NULL;
END;
$Update_CQ_Score$ LANGUAGE plpgsql;

CREATE TRIGGER Update_CQ_Score AFTER INSERT ON CLOSED_ANSWER
FOR EACH ROW EXECUTE PROCEDURE UCQS_function(); -- Forse non c'è bisgno del for each row

-- //-------------------------------------------------------------------------//
-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili
CREATE FUNCTION VRA_Function() RETURNS TRIGGER AS $Valid_Right_Answer$
DECLARE
	-- Indicano le rispettive risposte a quella domanda
	-- (se la risposta è opzionale resteranno NULL)
	flagC CLOSED_QUIZ.AnswerC%TYPE := NULL;
	flagD CLOSED_QUIZ.AnswerD%TYPE := NULL;
BEGIN

	-- Prendo il valore della risposta C
	SELECT AnswerC INTO flagC
	FROM CLOSED_QUIZ
	WHERE CodCQ = NEW.CodCQ;

	IF NEW.GivenAnswer = 'c' AND flagC IS NULL THEN
		DELETE FROM CLOSED_ANSWER WHERE CodCA = NEW.CodCA;
		RAISE EXCEPTION 'La risposta data non è tra quelle possibili ';
	END IF;

	-- Prendo il valore della risposta D
	SELECT AnswerD INTO flagD
	FROM CLOSED_QUIZ
	WHERE CodCQ = NEW.CodCQ;

	IF NEW.GivenAnswer = 'd' AND flagD IS NULL THEN
		DELETE FROM CLOSED_ANSWER WHERE CodCA = NEW.CodCA;
		RAISE EXCEPTION 'La risposta data non è tra quelle possibili ';
	END IF;

	RETURN NULL;
END;
$Valid_Right_Answer$ LANGUAGE plpgsql;

CREATE TRIGGER Valid_Right_Answer AFTER INSERT ON CLOSED_ANSWER
FOR EACH ROW EXECUTE PROCEDURE VRA_Function();

-- //-------------------------------------------------------------------------//
-- Valid_GivenAnswer: La lunghezza della risposta data NON deve superare MaxLength dell'OpenQuiz associato
CREATE FUNCTION VGA_function() RETURNS TRIGGER AS $$
BEGIN
	IF LENGTH(NEW.GivenAnswer) > (	SELECT MaxLength
					FROM OPEN_ANSWER AS OA, OPEN_QUIZ AS OQ
					WHERE OA.CodOQ = OQ.CodOQ
						AND OA.CodOA = NEW.CodOA ) THEN

		DELETE FROM OPEN_ANSWER WHERE CodOA = NEW.CodOA;
		RAISE EXCEPTION 'ERRORE! Risposta troppo lunga!';

	END IF;

	RETURN NEW;
END; $$ LANGUAGE PLPGSQL;

CREATE TRIGGER Valid_GivenAnswer
AFTER INSERT ON OPEN_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE VGA_function();

-- //-------------------------------------------------------------------------//
-- Evaluate_Total_Score : Quando viene corretta una risposta, viene aggiornato il risultato totale.
CREATE FUNCTION ETS_function() RETURNS TRIGGER AS $Evaluate_Total_Score$
BEGIN
	UPDATE TEST_TAKEN
	SET TotalScore = TotalScore + NEW.Score - OLD.Score
	WHERE NEW.CodTest_Taken = CodTestTaken;
	RETURN NEW;
END; $Evaluate_Total_Score$ LANGUAGE PLPGSQL;

CREATE TRIGGER Evaluate_Total_Score_Open
AFTER UPDATE OF Score ON OPEN_ANSWER  -- Update_Closed_Quiz_Score si occupa dell'update dell'attributo
FOR EACH ROW
EXECUTE PROCEDURE ETS_function();

CREATE TRIGGER Evaluate_Total_Score_Closed
AFTER UPDATE OF Score ON CLOSED_ANSWER  -- Update_Closed_Quiz_Score si occupa dell'update dell'attributo
FOR EACH ROW
EXECUTE PROCEDURE ETS_function();

-- //-------------------------------------------------------------------------//
-- Valid_Open_Score : Il punteggio dato alla risposta aperta, deve essere compreso tra maxScore e minScore.
CREATE FUNCTION VOS_function() RETURNS TRIGGER AS $Valid_Open_Score$
DECLARE
	min OPEN_QUIZ.MinScore%TYPE;
	max OPEN_QUIZ.MaxScore%TYPE;
BEGIN
	SELECT MinScore, MaxScore INTO min, max
	FROM OPEN_QUIZ
	WHERE CodOQ = NEW.CodOQ;

	--RAISE NOTICE 'Value: % %', min, max;

	IF NEW.Score NOT BETWEEN min AND max THEN
		UPDATE OPEN_ANSWER SET Score = 0 WHERE CodOA = NEW.CodOA;
		RAISE EXCEPTION 'Il punteggio deve essere compreso tra i valori fissati!';
	END IF;
	RETURN NEW;
END; $Valid_Open_Score$ LANGUAGE PLPGSQL;

CREATE TRIGGER Valid_Open_Score
AFTER UPDATE ON OPEN_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE VOS_function();

-- //-------------------------------------------------------------------------//
-- POPOLAZIONE
-- //-------------------------------------------------------------------------//

-- //------------------------------ PROFESSOR --------------------------------//
INSERT INTO PROFESSOR VALUES
	(1, 'Silvio', 'Barra', 'silvio.barra@unina.it', 'SilvioBarra', 'LaPasswordSegretaDelProfessore!1'),
	(2, 'Porfirio', 'Tramontana', 'porfirio.tramontana@unina.it', 'PorfirioTramontana', 'LaPasswordSegretaDelProfessore!2'),
	(3, 'Guglielmo', 'Tamburrini', 'guglielmo.tamburrini@unina.it', 'GuglielmoTamburrini', 'LaPasswordSegretaDelProfessore!3'),
	(4, 'Fabio', 'Mogavero', 'fabio.mogavero@unina.it', 'FabioMogavero', 'LaPasswordSegretaDelProfessore!4'),
	(5, 'Eleonora', 'Messina', 'eleonora.messina@unina.it', 'EleonoraMessina', 'LaPasswordSegretaDelProfessore!5'),
	(6, 'Giovanni', 'Cutolo', 'giovanni.cutolo@unina.it', 'GiovanniCutolo', 'LaPasswordSegretaDelProfessore!6'),
	(7, 'Francesca', 'Cioffi', 'francesca.cioffi@unina.it', 'FrancescaCioffi', 'LaPasswordSegretaDelProfessore!7'),
	(8, 'Daniele', 'Castorina', 'daniele.castorina@unina.it', 'DanieleCastorina', 'LaPasswordSegretaDelProfessore!8'),
	(9, 'Silvia', 'Rossi', 'silvia.rossi@unina.it', 'SilviaRossi', 'LaPasswordSegretaDelProfessore!9'),
	(10, 'Francesco', 'Isgrò', 'francesco.isgro@unina.it', 'FrancescoIsgro', 'LaPasswordSegretaDelProfessore!10');

-- //------------------------------ STUDENT ----------------------------------//
INSERT INTO STUDENT VALUES
	(1, 'Francesco', 'Orlando', 'f.orlando@studenti.unina.it', 'Effeo', 'Giallo1_'),
	(2, 'Alfredo', 'Laino', 'a.laino@studenti.unina.it', 'pino.pompino', 'RossoCarminio2?'),
	(3, 'Marco', 'Pastore', 'm.pastore@studenti.unina.it', 'marco_pastazio', 'BluElettrico6$'),
	(4, 'Giorgio', 'Longobardo', 'g.longobardo@studenti.unina.it', 'giovgio', 'RamarroMarron3?');

-- //------------------------------ TEST -------------------------------------//
INSERT INTO TEST(CodTest, Name, CodP) VALUES
	(1, 'Prima prova intercorso di Geometria 2020-2021', 7);

-- //------------------------------ CLASS_T ----------------------------------//
INSERT INTO CLASS_T VALUES
	(1, 'Basi di dati', '2021', 9, 1),
	(2, 'Object orientation', '2021', 6, 2),
	(3, 'Elementi di informatica teorica', '2021', 6, 3),
	(4, 'Algoritmi e strutture dati', '2021', 9, 4),
	(5, 'Scientific computing', '2021', 6, 5),
	(6, 'Algebra', '2020', 9, 6),
	(7, 'Geometria', '2021', 6, 7),
	(8, 'Analisi I', '2021', 9, 8),
	(9, 'Architettura degli elaboratori', '2021', 9, 9),
	(10, 'Laboratorio di programmazione', '2021', 9, 10);

 -- //------------------------------ LECTURE ----------------------------------//
INSERT INTO LECTURE VALUES
	(1, 'Il metodo del gradiente', 'www.matlab.com', 5, 5);

-- //------------------------------ OPENQUIZ ---------------------------------//
INSERT INTO OPEN_QUIZ(Question, MaxScore, MinScore, MaxLength, CodTest) VALUES
	('Di che colore era il cavallo bianco di napoleone?', 1 , 0, 10, 1),
	('Cos è uno spazio vettoriale?', 1 , 0, 100, 1);

-- //------------------------------ CLOSEDQUIZ -------------------------------//
INSERT INTO CLOSED_QUIZ(Question, AnswerA, AnswerB, AnswerC, AnswerD, RightAnswer, ScoreIfRight, ScoreIfWrong, CodTest) VALUES
	('Cos è un sistema lineare?', 'Qualcosa di bello', 'Qualcosa di carino', 'Qualcosa di brutto', 'Qualcosa di qualcosa', 'b', 1, 0, 1),
	('Cos è un autovettore?', 'Un vettore che va in auto', 'Un vettore che dopo una trasformazione lineare viene solo scalato', 'Non lo so', 'Per me è la cipolla', 'b', 1, 0, 1);
INSERT INTO CLOSED_QUIZ(CodCQ, Question, AnswerA, AnswerB, RightAnswer, ScoreIfRight, ScoreIfWrong, CodTest) VALUES
	(3, 'Un cerchio è uno spazio vettoriale?', 'Si', 'No', 'b', 1, 0, 1);

-- //------------------------------ TAKE -------------------------------------//
INSERT INTO TAKE VALUES
	(1, 1),
	(6, 3),
	(5, 2);

 -- //------------------------------ TESTTAKEN --------------------------------//
INSERT INTO TEST_TAKEN(CodTestTaken, CodTest, StudentID) VALUES
	(1, 1, 3),
	(2, 1, 1);

-- //------------------------------ OPENANSWER -------------------------------//
INSERT INTO OPEN_ANSWER(GivenAnswer, CodOQ, CodTest_Taken) VALUES
	('Not white', 1, 1),
	('Uno spazio vettoriale è una struttura algebrica V, K, +, * con 5 proprietà ...', 2, 1);

-- //------------------------------ CLOSEDANSWER -----------------------------//
INSERT INTO CLOSED_ANSWER(GivenAnswer, CodCQ, CodTest_Taken) VALUES
    ('b', 3, 1),
    ('c', 2, 2);
