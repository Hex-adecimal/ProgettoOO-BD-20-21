-- Strong_Password : La password deve essere composta da più di 8 caratteri, almeno una lettera, almeno un numero ed almeno carattere speciale (!"£$%&/()=_:;,.-+*#)
CREATE DOMAIN PASSWORD_D AS VARCHAR(128)
	CHECK ( VALUE LIKE '________%');
-- Condizioni da aggiungere al dominio PASSWORD_D in qualche modo: AND VALUE LIKE '%[a-z]%' AND VALUE LIKE '%[0-9]%' AND VALUE LIKE '%[!"£$%&/()=_:;,.-+*#]%'
-- Questo vincolo verrà implementato tramite funzione

-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili (Dominio = {'a', 'b', 'c', 'd'})
CREATE DOMAIN CLOSED_ANSWER AS CHAR(1)
	CHECK ( VALUE IN ('a', 'b', 'c', 'd') ); -- Aggiungere il controllo per c e d
-- Questo vincolo verrà implementato tramite funzione
