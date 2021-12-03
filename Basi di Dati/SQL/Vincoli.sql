-- VINCOLI DI DOMINIO

-- Vincolo Valid_Name : I nomi devono contenere solo caratteri compresi tra A-Z e a-z. Inoltre devono avere al più 35 caratteri.
CREATE DOMAIN TYPE_NAME AS VARCHAR(35)
	CONSTRAINT Valid_Name
		CHECK ((VALUE <> '') AND ???)

-- Vincolo - Valid_Test_Name : Il nome del test può contenere caratteri compresi tra A-Z e a-z, ed inoltre può contenere numeri 0-9, e spazi, può avere un numero di caratteri compreso tra 1 e 30.

-- Valid_Email : La mail deve avere la forma di u@v.w con u, v, w stringhe non nulle 

-- Valid_Starting_Date_Time : La data di inizio del test deve essere successiva al giorno in cui viene creato il test

-- Valid_Duration : La durata deve essere maggiore-uguale di 10 minuti
CREATE DOMAIN TYPE_DURATION AS INTEGER
	NOT NULL
	CONSTRAINT Valid_Duration
		CHECK (VALUE > 9);

-- Valid_Max_Score : Il punteggio massimo deve essere maggiore di 0
CREATE TYPE_MAXSCORE AS DECIMAL(3,2)
	NOT NULL
	CONSTRAINT Valid_Max_Score
		CHECK (VALUE > 0);

-- Valid_Max_Length : La lunghezza massima deve essere maggiore di 0
CREATE DOMAIN TYPE_MAXLENGTH AS INTEGER
	NOT NULL
	CONSTRAINT Valid_Max_Length
		CHECK (VALUE > 0)M

-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili

-- Valid_Question : La domanda deve essere non nulla
CREATE DOMAIN TYPE_QUESTION AS VARCHAR(100)
	NOT NULL
	CONSTRAINT Valid_Question
		CHECK (VALUE <> '');

-- Strong_Password : La password deve essere composta da più di 8 caratteri, almeno una lettera, almeno un numero ed almeno carattere speciale

-- Valid_CFU : Il numero di CFU deve essere compreso tra 1 e 20
CREATE DOMAIN TYPE_CFU AS INTEGER
	CONSTRAINT Valid_CFU
		CHECK (VALUE BETWEEN 1 AND 20);
