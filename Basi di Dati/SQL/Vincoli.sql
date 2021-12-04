-- Valid_Name : I nomi devono contenere solo caratteri compresi tra A-Z e a-z. Inoltre devono avere almeno 1 carattere e al più 35 caratteri.
CREATE DOMAIN PERSON_NAME AS VARCHAR(35)
  CHECK ( VALUE <> '' );

-- Valid_Test_Name : Il nome del test può contenere caratteri compresi tra A-Z e a-z, ed inoltre può contenere numeri 0-9, e spazi, può avere un numero di caratteri compreso tra 1 e 30.
CREATE DOMAIN TEST_NAME AS VARCHAR(30)
  CHECK ( VALUE <> '' );
  
-- Valid_Starting_Date_Time : La data di inizio del test deve essere successiva al giorno in cui viene creato il test
CREATE CONSTRAINT Valid_Starting_DateTime 
	CHECK ( DATE(StartingDateTime) > CreationDate );
	
-- Valid_ClosingDateTime : La differenza tra ClosingDateTime e StartingDateTime deve essere maggiore o uguale di 10 minuti
CREATE CONSTRAINT Valid_ClosingDateTime
	CHECK ( DATEDIFF(minute, StartingDateTime, ClosingDateTime) >= 10);

-- Valid_Email : La mail deve avere la forma di u@v.w con u, v, w stringhe non nulle
CREATE DOMAIN EMAIL AS VARCHAR(254)
	CHECK ( VALUE LIKE '_%@_%._%' );
	
-- Strong_Password : La password deve essere composta da più di 8 caratteri, almeno una lettera, almeno un numero ed almeno carattere speciale (!"£$%&/()=_:;,.-+*#)
CREATE DOMAIN PASSWORD AS VARCHAR(128)
	CHECK ( VALUE LIKE '________' AND VALUE LIKE '%[a-z]%' AND VALUE LIKE '%[0-9]%' AND VALUE LIKE '%[!"£$%&/()=_:;,.-+*#]%');

-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili (Dominio = {'a', 'b', 'c', 'd'})
CREATE DOMAIN CLOSED_ANSWER AS CHAR(1)
	CHECK ( VALUE IN ('a', 'b', 'c', 'd') ); -- Aggiungere il controllo per c e d
	
-- Valid_CFU : Il numero di CFU deve essere compreso tra 1 e 20
CREATE DOMAIN CFU AS INTEGER
	CHECK ( VALUE BETWEEN 1 AND 20 );


