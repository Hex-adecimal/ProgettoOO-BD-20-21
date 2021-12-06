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
-- Questo vincolo verrà implementato tramite funzione

-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili (Dominio = {'a', 'b', 'c', 'd'})
CREATE DOMAIN CLOSED_ANSWER AS CHAR(1)
	CHECK ( VALUE IN ('a', 'b', 'c', 'd') ); -- Aggiungere il controllo per c e d
-- Questo vincolo verrà implementato tramite funzione

-- Valid_CFU : Il numero di CFU deve essere compreso tra 1 e 20
CREATE DOMAIN VALID_CFU AS INTEGER
	CHECK ( VALUE BETWEEN 1 AND 20 );

CREATE CONSTRAINT MaxLength_UpperBound
	CHECK ( VALUE BETWEEN 1 AND 1024 );

-- Valid_GivenAnswer: La lunghezza della risposta data NON deve superare MaxLength dell'OpenQuiz associato
CREATE ASSERTION Valid_GivenAnswer
	CHECK NOT EXISTS
		( )

- //---------------------------------------------------------------------------------------------------------------------------// 
- //---------------------------------------------------------------------------------------------------------------------------// 

-- Valid_Starting_Date_Time : La data di inizio del test deve essere successiva al giorno in cui viene creato il test
ALTER TABLE TEST
ADD CONSTRAINT Valid_Starting_DateTime 
	CHECK ( DATE(StartingDateTime) > CreationDate );
	
-- Valid_ClosingDateTime : La differenza tra ClosingDateTime e StartingDateTime deve essere maggiore o uguale di 10 minuti
ALTER TABLE TEST
ADD CONSTRAINT Valid_ClosingDateTime
	CHECK ( DATE_PART('minute', ClosingDateTime - StartingDateTime ) >= 10);
