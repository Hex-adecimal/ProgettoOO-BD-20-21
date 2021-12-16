-- //-------------------------------------------------------------------------//
-- CREAZIONE DEI DOMINI
-- //-------------------------------------------------------------------------//

-- Valid_Name : I nomi non devono contenere numeri e devono avere almeno 1
-- carattere e al più 35 caratteri.
CREATE DOMAIN PERSON_NAME AS VARCHAR(35)
	CHECK ( VALUE <> '' AND VALUE NOT SIMILAR TO '%[0-9]+%' );

-- Valid_Test_Name : Il nome del test può avere un numero di caratteri
-- compreso tra 1 e 55.
CREATE DOMAIN TEST_NAME AS VARCHAR(55)
	CHECK ( VALUE <> '' );

-- Valid_Email : La mail deve avere la forma di u@v.w con u, v, w
-- stringhe non nulle
CREATE DOMAIN EMAIL AS VARCHAR(254)
	CHECK ( VALUE LIKE '_%@_%._%' );

-- Strong_Password : La password deve essere composta da più di 8 caratteri,
-- almeno una lettera, almeno un numero ed almeno carattere speciale
-- (!"£$%&/()=_:;,.-+*#)
CREATE DOMAIN PASSWORD_D AS VARCHAR(128)
	CHECK (VALUE ~ '^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).*$'
		AND VALUE LIKE '________%');

-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle
-- possibili (Dominio = {'a', 'b', 'c', 'd'})
CREATE DOMAIN CLOSED_ANSWER_D AS CHAR(1)
	CHECK ( VALUE IN ('a', 'b', 'c', 'd') );
-- Questo vincolo verrà implementato ulteriormente tramite funzione

-- Valid_CFU : Il numero di CFU deve essere compreso tra 1 e 20
CREATE DOMAIN VALID_CFU AS INTEGER
	CHECK ( VALUE BETWEEN 1 AND 20 );

-- Dominio per i punteggi dei quiz
CREATE DOMAIN SCORE_D AS FLOAT ;

-- //-------------------------------------------------------------------------//
-- CREAZIONE DELLE TABELLE
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
-- Aggiunta del vincolo di chiave primaria
ALTER TABLE TEST
	ADD CONSTRAINT test_pk PRIMARY KEY(CodTest),
	-- Aggiunta del vincolo di chiave esterna sulla tabella PROFESSOR
	ADD CONSTRAINT test_fk FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP)
		-- Quando il codice del professore cambia, viene cambiato anche in TEST
		ON UPDATE CASCADE
		ON DELETE RESTRICT; --

-- Valid_Starting_Date_Time : La data di inizio del test deve essere
-- successiva al giorno in cui viene creato il test
ALTER TABLE TEST
ADD CONSTRAINT Valid_Starting_DateTime
	CHECK ( StartingDateTime > CreationDateTime );

-- Valid_ClosingDateTime : La differenza tra ClosingDateTime e StartingDateTime
-- deve essere maggiore o uguale di 10 minuti
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
-- Aggiunta del vincolo di chiave primaria
ALTER TABLE CLASS_T
	ADD CONSTRAINT class_pk PRIMARY KEY(CodC),
	-- Aggiunta del vincolo di chiave esterna sulla tabella PROFESSOR
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
-- Aggiunta del vincolo di chiave primaria
ALTER TABLE LECTURE
	ADD CONSTRAINT lecture_pk PRIMARY KEY(CodL),
	-- Aggiunta del vincolo di chiave esterna sulla tabella PROFESSOR
	ADD CONSTRAINT lecture_professor_fk
		FOREIGN KEY(CodP) REFERENCES PROFESSOR(CodP)
			ON UPDATE CASCADE
			ON DELETE RESTRICT,
	-- Aggiunta del vincolo di chiave esterna sulla tabella CLASS_T
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
-- Aggiunta del vincolo di chiave primaria
ALTER TABLE OPEN_QUIZ
	ADD CONSTRAINT open_quiz_pk PRIMARY KEY(CodOQ),
	-- Aggiunta del vincolo di chiave esterna sulla tabella TEST
	ADD CONSTRAINT open_quiz_fk FOREIGN KEY(CodTest) REFERENCES TEST(CodTest)
		ON UPDATE CASCADE
		ON DELETE RESTRICT;

-- MaxLength_UpperBound : La possibilità della lughezza della risposta
-- aperta deve essere compresa tra 1 e 1024
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
-- Aggiunta della chiave primaria
ALTER TABLE TAKE
	ADD CONSTRAINT take_pk PRIMARY KEY(CodC, StudentID),
	-- Aggiunta del vincolo di chiave esterna sulla tabella CLASS_T
	ADD CONSTRAINT take_class_fk FOREIGN KEY(CodC) REFERENCES CLASS_T(CodC)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	-- Aggiunta del vincolo di chiave esterna sulla tabella STUDENT
	ADD CONSTRAINT take_student_fk
		FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID)
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
	-- Ogni studente non può consegnare più volte lo stesso test
	ADD CONSTRAINT unique_student_test UNIQUE(StudentID, CodTest),
	-- Aggiunta del vincolo di chiave esterna sulla tabella TEST
	ADD CONSTRAINT test_taken_test_fk
		FOREIGN KEY(CodTest) REFERENCES TEST(CodTest)
			ON UPDATE CASCADE
			ON DELETE RESTRICT,
	-- Aggiunta del vincolo di chiave esterna sulla tabella STUDENT
	ADD CONSTRAINT test_taken_student
		FOREIGN KEY(StudentID) REFERENCES STUDENT(StudentID)
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
-- Aggiunta della chiave primaria
ALTER TABLE OPEN_ANSWER
	ADD CONSTRAINT open_answer_pk PRIMARY KEY(CodOA),
	-- Aggiunta del vincolo di chiave esterna sulla tabella OPEN_QUIZ
	ADD CONSTRAINT open_answer_open_quiz_fk
		FOREIGN KEY(CodOQ) REFERENCES OPEN_QUIZ(CodOQ)
			ON UPDATE CASCADE
			ON DELETE RESTRICT,
	-- Aggiunta del vincolo di chiave esterna sulla tabella TEST_TAKEN
	ADD CONSTRAINT open_answer_test_taken_fk
		FOREIGN KEY(CodTest_Taken) REFERENCES TEST_TAKEN(CodTestTaken)
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
-- Aggiunta della chiave primaria
ALTER TABLE CLOSED_ANSWER
	ADD CONSTRAINT closed_answer_pk PRIMARY KEY(CodCA),
	-- Aggiunta del vincolo di chiave esterna sulla tabella CLOSED_QUIZ
	ADD CONSTRAINT closed_answer_closed_quiz_fk
		FOREIGN KEY(CodCQ) REFERENCES CLOSED_QUIZ(CodCQ)
			ON UPDATE CASCADE
			ON DELETE RESTRICT,
	-- Aggiunta del vincolo di chiave esterna sulla tabella TEST_TAKEN
	ADD CONSTRAINT closed_answer_test_taken_fk
		FOREIGN KEY(CodTest_Taken) REFERENCES TEST_TAKEN(CodTestTaken)
			ON UPDATE CASCADE
			ON DELETE RESTRICT;

-- //-------------------------------------------------------------------------//
-- Funzioni e Trigger
-- //-------------------------------------------------------------------------//

-- Update_Closed_Quiz_Score : Correzione automatica risposte chiuse
CREATE OR REPLACE FUNCTION UCQS_function() RETURNS TRIGGER AS $Update_CQ_Score$
DECLARE
    ScoreRight CLOSED_QUIZ.ScoreIfRight%TYPE;
    ScoreWrong CLOSED_QUIZ.ScoreIfWrong%TYPE;
    RA CLOSED_QUIZ.RightAnswer%TYPE; -- Risposta corretta
	info INT := 0; -- Flag che mi dice se posso eseguire le query
BEGIN
	-- Controllo che la query restituisca una sola tupla
	SELECT COUNT(*) INTO info
    FROM CLOSED_ANSWER CA JOIN CLOSED_QUIZ CQ ON CA.CodCQ = CQ.CodCQ
    WHERE CA.CodCA = NEW.CodCA;

	IF info = 1 THEN
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

		-- Aggiorno se la risposta è sbagliata
    	IF (NEW.GivenAnswer <> RA AND NEW.GivenAnswer IS NOT NULL) THEN
        	UPDATE CLOSED_ANSWER
        	SET Score = ScoreWrong
        	WHERE CodCA = NEW.CodCA;
    	END IF;
	END IF;
	RETURN NEW;

EXCEPTION
	WHEN OTHERS THEN
		RAISE NOTICE 'SQLSTATE : %', SQLSTATE;
		RETURN NULL;
END; $Update_CQ_Score$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER Update_CQ_Score
AFTER INSERT ON CLOSED_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE UCQS_function();

-- //-------------------------------------------------------------------------//
-- Valid_Right_Answer : La risposta di una domanda multipla deve
-- essere tra quelle possibili
CREATE OR REPLACE FUNCTION VRA_Function() RETURNS TRIGGER AS $Valid_Right_Answer$
DECLARE
	-- Indicano le rispettive risposte a quella domanda
	-- (se la risposta è opzionale resteranno NULL)
	flagC CLOSED_QUIZ.AnswerC%TYPE := NULL;
	flagD CLOSED_QUIZ.AnswerD%TYPE := NULL;
	info int := 0; -- Flag che mi dice se posso eseguire le query
BEGIN

	-- Controllo che la query restituisca una sola tupla
	SELECT COUNT(*) INTO info -- Conto * perchè null non viene contato
	FROM CLOSED_QUIZ
	WHERE CodCQ = NEW.CodCQ;

	IF info = 1 THEN
		-- Prendo il valore della risposta C
		SELECT AnswerC INTO flagC
		FROM CLOSED_QUIZ
		WHERE CodCQ = NEW.CodCQ;

		-- Se è stata inserita la risposta C ma non era una risposta possibile
		IF NEW.GivenAnswer = 'c' AND flagC IS NULL THEN
			RAISE EXCEPTION USING ERRCODE='E000C';
		END IF;
	END IF;

	-- Controllo di sicurezza sul numero di tuple
	SELECT COUNT(*) INTO info -- Conto * perchè null non viene contato
	FROM CLOSED_QUIZ
	WHERE CodCQ = NEW.CodCQ;
	IF info = 1 THEN
		-- Prendo il valore della risposta D
		SELECT AnswerD INTO flagD
		FROM CLOSED_QUIZ
		WHERE CodCQ = NEW.CodCQ;

		-- Se è stata inserita la risposta D ma non era una risposta possibile
		IF NEW.GivenAnswer = 'd' AND flagD IS NULL THEN
			RAISE EXCEPTION USING ERRCODE='E000D';
		END IF;
	END IF;

	RETURN NEW;

EXCEPTION
  	WHEN SQLSTATE 'E000C' THEN -- E000C errore per c
		DELETE FROM CLOSED_ANSWER WHERE CodCA = NEW.CodCA;
		RAISE NOTICE 'La risposta "C" non è tra quelle possibili ';
		RETURN NULL;

	WHEN SQLSTATE 'E000D' THEN -- E000D errore per d
		DELETE FROM CLOSED_ANSWER WHERE CodCA = NEW.CodCA;
		RAISE NOTICE 'La risposta "D" non è tra quelle possibili ';
		RETURN NULL;

	WHEN OTHERS THEN
		RAISE NOTICE 'SQLSTATE : %', SQLSTATE;
		RETURN NULL;
END; $Valid_Right_Answer$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER Valid_Right_Answer
AFTER INSERT ON CLOSED_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE VRA_Function();

-- //-------------------------------------------------------------------------//
-- Valid_GivenAnswer: La lunghezza della risposta data NON deve superare
-- MaxLength dell'OpenQuiz associato
CREATE OR REPLACE FUNCTION VGA_function() RETURNS TRIGGER AS $$
DECLARE
	info INT := 0;
	len INT;
BEGIN
	-- Controllo sul numero di tuple
	SELECT COUNT(MaxLength) INTO info
	FROM OPEN_ANSWER AS OA, OPEN_QUIZ AS OQ
	WHERE OA.CodOQ = OQ.CodOQ AND OA.CodOA = NEW.CodOA;
	IF info = 1 THEN
		-- Cerco la massima lunghezza della risposta
		SELECT MaxLength INTO len
		FROM OPEN_ANSWER AS OA, OPEN_QUIZ AS OQ
		WHERE OA.CodOQ = OQ.CodOQ AND OA.CodOA = NEW.CodOA;

		IF LENGTH(NEW.GivenAnswer) > len THEN
			RAISE EXCEPTION USING ERRCODE='T00LG';
		END IF;
	ELSE
		RAISE EXCEPTION USING ERRCODE='SF001';
	END IF;

	RETURN NEW;

EXCEPTION
	WHEN SQLSTATE 'T00LG' THEN
		DELETE FROM OPEN_ANSWER WHERE CodOA = NEW.CodOA;
		RAISE NOTICE 'ERRORE! Risposta troppo lunga!';
		RETURN NULL;

	WHEN SQLSTATE 'SF001' THEN
		RAISE NOTICE 'Errore nel numero di tuple nella select';
		RETURN NULL;
END; $$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER Valid_GivenAnswer
AFTER INSERT ON OPEN_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE VGA_function();

-- //-------------------------------------------------------------------------//
-- Evaluate_Total_Score : Quando viene corretta una risposta,
-- viene aggiornato il risultato totale.
CREATE OR REPLACE FUNCTION ETS_function() RETURNS TRIGGER AS $Evaluate_Total_Score$
BEGIN
	UPDATE TEST_TAKEN
	SET TotalScore = TotalScore + NEW.Score - OLD.Score
	WHERE NEW.CodTest_Taken = CodTestTaken;
	RETURN NEW;
END; $Evaluate_Total_Score$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER Evaluate_Total_Score_Open
AFTER UPDATE OF Score ON OPEN_ANSWER
-- Update_Closed_Quiz_Score si occupa dell'update dell'attributo
FOR EACH ROW
EXECUTE PROCEDURE ETS_function();

CREATE OR REPLACE TRIGGER Evaluate_Total_Score_Closed
AFTER UPDATE OF Score ON CLOSED_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE ETS_function();

-- //-------------------------------------------------------------------------//
-- Test_Is_Passed : Quando il total score supera il min score,
-- allora lo studente ha passato il test
-- Ogni volta che viene cambiato il total score, controllo se il test è stato passato
-- Uno studente può aver passato un test, anche se non è stato ancora corretto dal prof
-- Potrebbe bastare rispondere alle domande a risposta chiusa
CREATE OR REPLACE FUNCTION TIP_function() RETURNS TRIGGER AS $Test_Is_Passed$
DECLARE
    tts CURSOR IS
        SELECT *
        FROM TEST_TAKEN
        WHERE TotalScore IS NOT NULL;
    hatepostgre TEST.MinScore%TYPE;
BEGIN
    -- Scorro tutti i test_taken, e vedo quali hanno totalscore superiore al minscore
    FOR i IN tts LOOP
        SELECT MinScore INTO hatepostgre FROM TEST WHERE CodTest = i.CodTest; -- Prendo il rispettivo MinScore

        -- Se il punteggio totale è maggiore del minimo, o Se non c'è, il test è banalmente passato
        IF i.TotalScore >= hatepostgre OR hatepostgre IS NULL THEN
            UPDATE TEST_TAKEN SET Passed = true WHERE CodTestTaken = i.CodTestTaken;
        ELSE --i.TotalScore < hatepostgre
            UPDATE TEST_TAKEN SET Passed = false WHERE CodTestTaken = i.CodTestTaken;
        END IF;
    END LOOP;

	RETURN NULL;
END; $Test_Is_Passed$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER Test_Is_Passed AFTER UPDATE OF TotalScore ON TEST_TAKEN
EXECUTE PROCEDURE TIP_function();

-- //-------------------------------------------------------------------------//
-- Valid_Open_Score : Il punteggio dato alla risposta aperta,
-- deve essere compreso tra maxScore e minScore.
CREATE OR REPLACE FUNCTION VOS_function() RETURNS TRIGGER AS $Valid_Open_Score$
DECLARE
	min OPEN_QUIZ.MinScore%TYPE;
	max OPEN_QUIZ.MaxScore%TYPE;
	info INT := 0;
BEGIN
	-- Controllo sul numero di tuple
	SELECT COUNT(*) INTO info
	FROM OPEN_QUIZ
	WHERE CodOQ = NEW.CodOQ;
	IF info = 1 THEN
		-- Prendo il minimo ed il massimo
		SELECT MinScore, MaxScore INTO min, max
		FROM OPEN_QUIZ
		WHERE CodOQ = NEW.CodOQ;

		--RAISE NOTICE 'Value: % %', min, max;

		IF NEW.Score NOT BETWEEN min AND max THEN
			RAISE EXCEPTION USING ERRCODE='SNIMM';
		END IF;
	ELSE
		RAISE EXCEPTION USING ERRCODE='SF001';
	END IF;

	RETURN NEW;
EXCEPTION
	WHEN SQLSTATE 'SNIMM' THEN
		UPDATE OPEN_ANSWER SET Score = 0 WHERE CodOA = NEW.CodOA;
		RAISE NOTICE 'Il punteggio deve essere compreso tra i valori fissati!';
		RETURN NULL;

	WHEN SQLSTATE 'SF001' THEN
		ROLLBACK;
		RAISE NOTICE 'Errore nel numero di tuple nella select';
		RETURN NULL;
END; $Valid_Open_Score$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER Valid_Open_Score
AFTER UPDATE ON OPEN_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE VOS_function();

-- Unique_Username : Non devono esistere più utenti con lo stesso username
-- Unique_Email : Non devono esistere più utenti con la stessa email
-- //-------------------------------------------------------------------------//
-- si può usare anche TG_TABLE_NAME
-- Data type name; the name of the table that caused the trigger invocation.
CREATE OR REPLACE FUNCTION UU_function() RETURNS TRIGGER AS $$
DECLARE
	ris VARCHAR(1000);
	tempUsername VARCHAR(1000);
	tempEmail VARCHAR(1000);
	stmt VARCHAR(1000);
	x VARCHAR(1000);
	tab VARCHAR(1000);
	tab2 VARCHAR(1000);
BEGIN
	tab := TG_ARGV[0];
	x := TG_ARGV[1];

	IF tab = 'PROFESSOR' THEN

		tab2 := 'STUDENT';

		IF X = 'Username' THEN

			SELECT P.Username
			INTO tempUsername
			FROM PROFESSOR AS P
			WHERE P.CodP = NEW.CodP;

			stmt := concat(stmt, 'SELECT Username FROM ', tab2, ' WHERE Username = ''', tempUsername, '''');

		ELSIF x = 'Email' THEN

			SELECT P.Email
			INTO tempEmail
			FROM PROFESSOR AS P
			WHERE P.CodP = NEW.CodP;

			stmt := concat(stmt, 'SELECT Email FROM ', tab2, ' WHERE Email = ''', tempEmail, '''');

		END IF;

	ELSIF tab = 'STUDENT' THEN

		tab2 := 'PROFESSOR';

		IF x = 'Username' THEN

			SELECT S.Username
			INTO tempUsername
			FROM STUDENT AS S
			WHERE S.StudentID = NEW.StudentID;

			stmt := concat(stmt, 'SELECT Username FROM ', tab2, ' WHERE Username = ''', tempUsername, '''');

		ELSIF x = 'Email' THEN

			SELECT S.Email
			INTO tempEmail
			FROM STUDENT AS S
			WHERE S.StudentID = NEW.StudentID;

			stmt := concat(stmt, 'SELECT Email FROM ', tab2, ' WHERE Email = ''', tempEmail, '''');

		END IF;

	END IF;

	EXECUTE stmt INTO ris;

	IF ris = tempUsername THEN

		stmt := concat('DELETE FROM ', tab, ' AS T WHERE T.Username = ''', tempUsername, '''');
		EXECUTE stmt;
		RAISE NOTICE 'Username già esistente!';

	ELSIF ris = tempEmail THEN

		stmt := concat('DELETE FROM ', tab, ' AS T WHERE T.Email = ''', tempEmail, '''');
		EXECUTE stmt;
		RAISE NOTICE 'Email già utilizzata da un altro utente!';

	END IF;

	RETURN NULL;

END; $$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER unique_student_username
AFTER INSERT OR UPDATE OF Username ON STUDENT
FOR EACH ROW
EXECUTE PROCEDURE UU_function('STUDENT', 'Username');

CREATE OR REPLACE TRIGGER unique_student_email
AFTER INSERT OR UPDATE OF Email ON STUDENT
FOR EACH ROW
EXECUTE PROCEDURE UU_function('STUDENT', 'Email');

CREATE OR REPLACE TRIGGER unique_professor_username
AFTER INSERT OR UPDATE OF Username ON PROFESSOR
FOR EACH ROW
EXECUTE PROCEDURE UU_function('PROFESSOR', 'Username');

CREATE OR REPLACE TRIGGER unique_professor_email
AFTER INSERT OR UPDATE OF Email ON PROFESSOR
FOR EACH ROW
EXECUTE PROCEDURE UU_function('PROFESSOR', 'Email');

-- //-------------------------------------------------------------------------//
-- Has_Been_Revised : Quando il professore ha corretto tutte le domande a
-- risposta aperta di un test, allora viene aggiornato l'attributo Revised in test taken
-- Se in partenza il cursore è null, non c'è nessuna domanda a risposta aperta
-- LOGICA SBAGLIATA, NON CI SONO RISPOSTE MESSE A NULL, E NON SI POSSONO METTERE SE NO
-- L ALTRO TRIGGER SI INCAZZA
/*CREATE OR REPLACE FUNCTION HBR_function() RETURNS TRIGGER AS $Has_Been_Revised$
DECLARE
    cur_score CURSOR FOR
        SELECT *
        FROM OPEN_ANSWER
        WHERE CodTest_Taken = NEW.CodTest_Taken;
    info INT := 0;
BEGIN

    -- Scorro tutte le risposte
    FOR i IN cur_score LOOP
		RAISE NOTICE 'score = %', i.score;
        -- Se uno degli score è null, allora non è stata corretta quella risposta
        IF i.Score IS NULL THEN
            info := 1;
            EXIT;
        END IF;
    END LOOP;

    -- Se tutte le risposte sono state corrette, allora il test è stato corretto
    IF info = 0 THEN
        UPDATE TEST_TAKEN
        SET Revised = true
        WHERE CodTestTaken = NEW.CodTest_Taken;
    END IF;

	RETURN NEW;
END; $Has_Been_Revised$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER Has_Been_Revised AFTER UPDATE OF Score ON OPEN_ANSWER
EXECUTE PROCEDURE HBR_function(); -- Se il professore corregge

CREATE OR REPLACE TRIGGER Has_Been_Revised AFTER INSERT ON CLOSED_ANSWER
EXECUTE PROCEDURE HBR_function(); -- Se ci sono solo domande a risposta chiusa
*/

CREATE OR REPLACE PROCEDURE
revise_function(ctest TEST.CodTest%TYPE, cprof PROFESSOR.CodP%TYPE) AS $$
DECLARE rowtest TEST%ROWTYPE;
BEGIN
	SELECT * INTO rowtest FROM TEST WHERE CodTest = ctest;
	IF rowtest.CodP = cprof THEN
		UPDATE TEST_TAKEN SET Passed = true WHERE CodTest = ctest;
	ELSE
		RAISE NOTICE 'Hai inserito un test non tuo';
	END IF;
END; $$ LANGUAGE PLPGSQL;
-- //-------------------------------------------------------------------------//
-- POPOLAZIONE
-- //-------------------------------------------------------------------------//

-- //------------------------------ PROFESSOR --------------------------------//
INSERT INTO PROFESSOR(FirstName, LastName, Email, Username, Pw) VALUES
	('Silvio', 'Barra', 'silvio.barra@unina.it', 'SilvioBarra', 'LaPasswordSegretaDelProfessore!1'),
	('Porfirio', 'Tramontana', 'porfirio.tramontana@unina.it', 'PorfirioTramontana', 'LaPasswordSegretaDelProfessore!2'),
	('Guglielmo', 'Tamburrini', 'guglielmo.tamburrini@unina.it', 'GuglielmoTamburrini', 'LaPasswordSegretaDelProfessore!3'),
	('Fabio', 'Mogavero', 'fabio.mogavero@unina.it', 'FabioMogavero', 'LaPasswordSegretaDelProfessore!4'),
	('Eleonora', 'Messina', 'eleonora.messina@unina.it', 'EleonoraMessina', 'LaPasswordSegretaDelProfessore!5'),
	('Giovanni', 'Cutolo', 'giovanni.cutolo@unina.it', 'GiovanniCutolo', 'LaPasswordSegretaDelProfessore!6'),
	('Francesca', 'Cioffi', 'francesca.cioffi@unina.it', 'FrancescaCioffi', 'LaPasswordSegretaDelProfessore!7'),
	('Daniele', 'Castorina', 'daniele.castorina@unina.it', 'DanieleCastorina', 'LaPasswordSegretaDelProfessore!8'),
	('Silvia', 'Rossi', 'silvia.rossi@unina.it', 'SilviaRossi', 'LaPasswordSegretaDelProfessore!9'),
	('Francesco', 'Isgrò', 'francesco.isgro@unina.it', 'FrancescoIsgro', 'LaPasswordSegretaDelProfessore!10');

-- //------------------------------ STUDENT ----------------------------------//
INSERT INTO STUDENT(FirstName, LastName, Email, Username, Pw) VALUES
	('Francesco', 'Orlando', 'f.orlando@studenti.unina.it', 'Effeo', 'Giallo1_!'),
	('Alfredo', 'Laino', 'a.laino@studenti.unina.it', 'pino.pompino', 'RossoCarminio2?!'),
	('Marco', 'Pastore', 'm.pastore@studenti.unina.it', 'marco_pastazio', 'BluElettrico6$!'),
	('Giorgio', 'Longobardo', 'g.longobardo@studenti.unina.it', 'giovgio', 'RamarroMarron3?!');

-- //------------------------------ TEST -------------------------------------//
INSERT INTO TEST(Name, MinScore, CodP) VALUES
	('Prova intercorso di Basi di dati 2021-2022', 18, 1),
	('Prova intercorso di Object orientation', 18, 2),
	('Prova intercorso di Elementi di Informatica teorica', 15, 3),
	('Prova intercorso di Algoritmi e Strutture dati', 30, 4),
	('Prova intercorso di Scientific Computing', 18, 5),
	('Prova intercorso di Algebra', 20, 6),
	('Prova intercorso di Geometria 2020-2021', 4, 7),
	('Prova intercorso di Analisi matematica I', 18, 8),
	('Prova intercorso di Architettura degli elaboratori', 4, 9),
	('Prova intercorso di Laboratorio di Programmazione', 18, 10);

INSERT INTO TEST(Name, CodP) VALUES
	('Seconda prova intercorso di Geometria 2020-2021', 5);

-- //------------------------------ CLASS_T ----------------------------------//
INSERT INTO CLASS_T(Name, Year, CFU, CodP) VALUES
	('Basi di dati', '2021', 9, 1),
	('Object orientation', '2021', 6, 2),
	('Elementi di informatica teorica', '2021', 6, 3),
	('Algoritmi e strutture dati', '2021', 9, 4),
	('Scientific computing', '2021', 6, 5),
	('Algebra', '2020', 9, 6),
	('Geometria', '2021', 6, 7),
	('Analisi I', '2021', 9, 8),
	('Architettura degli elaboratori', '2021', 9, 9),
	('Laboratorio di programmazione', '2021', 9, 10);

 -- //------------------------------ LECTURE ----------------------------------//
INSERT INTO LECTURE(Title, Link, CodP, CodC) VALUES
	('Il metodo del gradiente', 'www.matlab.com', 5, 5),
	('L01', 'https://www.w3schools.com/sql/default.asp', 1, 1),
	('L02', 'https://www.w3schools.com/sql/default.asp', 1, 1),
	('L03', 'https://www.w3schools.com/sql/default.asp', 1, 1),
	('L05', 'https://www.w3schools.com/sql/default.asp', 1, 1),
	('L06', 'https://www.w3schools.com/sql/default.asp', 1, 1),
	('L07', 'https://www.w3schools.com/sql/default.asp', 1, 1),
	('L08 (DDL-SQL)', 'https://www.w3schools.com/sql/default.asp', 1, 1),
	('L09 (AR)', 'https://www.w3schools.com/sql/default.asp', 1, 1),
	('L10 (AR)', 'https://www.w3schools.com/sql/default.asp', 1, 1);

-- //------------------------------ OPENQUIZ ---------------------------------//
INSERT INTO OPEN_QUIZ(Question, MaxScore, MinScore, MaxLength, CodTest) VALUES
	('Che cosa sono DDL, DQL, DML e DCL?', 2, 0, 100, 1),
	('Che cos è una vista?', 2, 0, 100, 1),
	('Che cos è un Design Pattern? Quali design pattern conosci?', 2, 0, 100, 2),
	('Come funziona il garbage collector in Java?', 2, 0, 100, 2),
	('Che cos è un linguaggio context-free?', 2, 0, 100, 3),
	('Descrivere il programma della macchina universale', 2, 0, 100, 3),
	('Esibire l algoritmo di visita in ampiezza di un grafo', 2, 0, 100, 4),
	('Descrivere il funzionamento dell Quick sort', 2, 0, 100, 4),
	('Di che tipo è la matrice per eseguire l interpolazione tramite spline?', 2, 0, 100, 5),
	('Che metodi conosci per risolvere equazioni differenziali in MATLAB?', 2, 0, 100, 5),
	('Quando un anello Zm è un campo?', 2, 0, 100, 6),
	('Come si trovano tutte le radici di un polinomio monico in Q[x]?', 2, 0, 100, 6),
	('Enunciare il teorema spettrale', 2 , 0, 100, 7),
	('Cos è uno spazio vettoriale?', 2 , 0, 100, 7),
	('Calcolare il seguente limite: lim x -> -infty (Mia voglia di popolare un database)', 2, 0, 100, 8),
	('Dimostrare che in R derivabile implica differenziabile e viceversa', 2, 0, 100, 8),
	('Che cos è un flip flop?', 2, 0, 100, 9),
	('Descrivere le architetture RAM', 2, 0, 100, 9),
	('Qual è la differenza tra un header file ed una libreria?', 2, 0, 120, 10),
	('Come si alloca una matrice dinamicamente?', 2, 0, 100, 10);

-- //------------------------------ CLOSEDQUIZ -------------------------------//
INSERT INTO CLOSED_QUIZ(Question, AnswerA, AnswerB, AnswerC, AnswerD, RightAnswer, ScoreIfRight, ScoreIfWrong, CodTest) VALUES
	('Che cos è una query?', 'Un interrogazione al database', 'Una preghiera al database', 'Non lo so', 'Vabbe ci vediamo a settembre', 'd', 1, 0, 1),
	('Che tipo di linguaggio è SQL?', 'Dichiarativo', 'Procedurale', 'Funzionale', 'Ad oggetti', 'a', 1, 0, 1),
	('Che metodo ci permette di costruire un oggetto in java?', 'Distruttore', 'malloc()', 'Costruttore', 'Per me è la cipolla', 'd', 1, 0, 2),
	('Cosa significa DAO?', 'decentralized autonomous organization', 'Data access object', 'Destroy and override', 'Delete access object', 'b', 1, 0, 2),
	('Che cos è un DFA?', 'Determinate finite automaton', 'Dervation financial atomata', 'Darling fancy automata', 'DOOM FINE AAAAAAAAAAA!!', 'a', 1, 0, 3),
	('Qual è la definizione di funzione parzialmente calcolabile?', 'Boh', 'Una funzione parziale tale che esiste un programma che la calcola', 'Una funzione tale che non esiste un programma che la calcola', 'Una funzione che converge per ogni input', 'b', 1, 0, 3),
	('Qual è la definizione di albero RED-BLACK?', 'Un albero rosso e nero', 'Un albero milanista', 'Un albero che raccoglie cotone', 'La definizione è un po lunga da mettere, clicca D', 'd', 1, 0, 4),
	('Quando due nodi u, v sono adiacenti?', 'se (u,v) in V', 'se (u,v) in G', 'se (u,v) in E', 'se sono abbastanza vicini da scaldarsi d inverno', 'c', 1, 0, 4),
	('Qual è la funzione di MATLAB che trova gli zeri di un equazione non lineare?', 'polyval', 'b/A', 'plot tanto si vede ad occhio', 'fzero', 'd', 1, 0, 5),
	('Quale di questi modi permette di ottenere un approssimazione del pi greco?', 'Sommatoria infinita troncata quando si raggiunge una certa tolleranza', 'basta scrivere pi', 'pi = 3 = e', 'plot', 'c', 1, 0, 5),
	('Quali sono le ultime cinque cifre del pi greco?', '12345', '98765', '10293', 'Inserire i tuoi dati della carta di credito', 'd', 1, 0, 6),
	('Quante radici ha x^2 - 1 in Z12?', '1', '2', '3', '4', 'd', 1, 0, 6),
	('Cos è un sistema lineare?', 'Qualcosa di bello', 'Qualcosa di carino', 'Qualcosa di brutto', 'Qualcosa di qualcosa', 'b', 1, 0, 7),
	('Cos è un autovettore?', 'Un vettore che va in auto', 'Un vettore che dopo una trasformazione lineare viene solo scalato', 'Non lo so', 'Meno male che vado a casa a mangiare pasta e faggioli', 'b', 1, 0, 7),
	('Qual è il significato geometrico della derivata?', 'La retta tangente in un punto', 'Il limite del rapporto incrementale', 'Instantenius rate of change', 'L area sotto la curva', 'a', 1, 0, 8),
	('Qual è il significato geometrico dell integrale di Reimann?', 'L area sotto al grafico', 'La retta tangente in un punto', 'Mi sto iniziando a scocciare', 'Di popolare il database', 'a', 1, 0, 8),
	('Che cos è "la memoria tampone"?', 'La cache', 'La ram', 'La rom', 'La memoria virtuale', 'a', 1, 0, 9),
	('Dove si presenta un bottleneck?', 'Tra la ram e il monitor', 'La cpu e la presa di corrente', 'Tra la cpu e la ram', 'Tra i led rgb e il monitor 144 hz', 'c', 1, 0, 9),
	('Come si scrive la funzione che alloca memoria dinamicamente?', 'mulloc', 'free', 'malloc', 'memoryallocapls', 'c', 1, 0, 10),
	('Come si scrive la funzione che dealloca la memoria allocata dinamicamente?', 'IWantToBreakFree', 'free', 'malloc', 'freehugs', 'a', 1, 0, 10);

INSERT INTO CLOSED_QUIZ(Question, AnswerA, AnswerB, RightAnswer, ScoreIfRight, ScoreIfWrong, CodTest) VALUES
	('Un cerchio è uno spazio vettoriale?', 'Si', 'No', 'b', 1, 0, 7),
	('Di che colore era il cavallo bianco di napoleone?', 'Vero', 'Falso', 'b', 1, 0, 7);

-- //------------------------------ TAKE -------------------------------------//
INSERT INTO TAKE VALUES
	(1, 1),
	(1, 3), -- 3 è lo studente marco pastore
	(2, 3),
	(3, 3),
	(4, 3),
	(5, 3),
	(6, 3),
	(7, 3),
	(8, 3),
	(9, 3),
	(10, 3),
	(5, 2);

 -- //------------------------------ TESTTAKEN --------------------------------//
INSERT INTO TEST_TAKEN(CodTest, StudentID) VALUES
	(1, 3),
	(2, 3),
	(3, 3),
	(4, 3),
	(5, 3),
	(6, 3),
	(7, 3),
	(8, 3),
	(9, 3),
	(10, 3),
	(1, 1),
	(11, 3),
	(10, 1);

-- //------------------------------ OPENANSWER -------------------------------//
INSERT INTO OPEN_ANSWER(GivenAnswer, CodOQ, CodTest_Taken) VALUES
	('Data definition language, Data query language, Data manipulation language, Data control language', 1, 1),
	('Una vista è una query memorizzata all interno del database', 2, 1),
	('I design pattern sono dei modelli standard per risolvere problemi, MVC, DAO, Composite e Observer', 3, 2),
	('Quando nessun oggetto fa riferimento ad x, il garbage collector dealloca x', 4, 2),
	('Un linguaggio context free è l insieme di parole derivate da una grammatica context free ', 5, 3),
	('//', 6, 3),
	('//', 7, 4),
	('//', 8, 4),
	('E una matrice tridiagonale e a diagonale strettamente dominante', 9, 5),
	('Il metodo di eulero', 10, 5),
	('Quando m è primo', 11, 6),
	('Tramite il Teorema di Eisenstein o scomponendo il polinomio in fattori', 12, 6),
	('//', 13, 7),
	('//', 14, 7),
	('Tende a meno infinito', 15, 8),
	('//', 16, 8),
	('Qualcosa che fa flip e poi flop', 17, 9),
	('//', 18, 9),
	('Un header file contiene le dichiarazioni delle funzioni e dei tipi. Una libreria contiene il corpo delle funzioni.', 19, 10),
	('//', 20, 10);

-- //------------------------------ CLOSEDANSWER -----------------------------//
INSERT INTO CLOSED_ANSWER(GivenAnswer, CodCQ, CodTest_Taken) VALUES
    ('a', 1, 1),
	('b', 2, 1),
	('c', 3, 2),
	('d', 4, 2),
	('a', 5, 3),
	('b', 6, 3),
	('c', 7, 4),
	('d', 8, 4),
	('a', 9, 5),
	('b', 10, 5),
	('c', 11, 6),
	('d', 12, 6),
	('a', 13, 7),
	('b', 14, 7),
	('c', 15, 8),
	('d', 16, 8),
	('a', 17, 9),
	('b', 18, 9),
	('c', 19, 10),
	('d', 20, 10);

-- Ecco delle insert che violano i vincoli scritti da noi
-- INSERT INTO CLOSED_ANSWER(GivenAnswer, CodCQ, CodTest_Taken) VALUES ('c', 22, 7); -- Valid_Right_Answer

-- INSERT INTO OPEN_ANSWER(GivenAnswer, CodOQ, CodTest_Taken) -- Valid_GivenAnswer
-- VALUES ('Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
--	tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
--	quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
--	Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
--	Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 1, 11);

-- UPDATE OPEN_ANSWER SET Score = 69*420 WHERE CodOA = 17;  -- Valid_Open_Score

-- INSERT INTO STUDENT(FirstName, LastName, Email, Username, Pw) VALUES ('Alpha', 'Beta', 'a@b.c', 'FrancescaCioffi', 'MyPw!123');

-- Queste permettono di vedere alcuni trigger
-- UPDATE OPEN_ANSWER SET SCORE = 2 WHERE CODOA = 17 OR CODOA = 18;
-- INSERT CLOSED_ANSWER(GivenAnswer, CodCQ, CodTest_Taken) VALUES
	--('a', 19, 13),
	--('a', 20, 13);
