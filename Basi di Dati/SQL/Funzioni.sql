-- Il vincolo Strong_Password (La password deve essere composta da più di 8 caratteri, almeno una lettera, almeno un numero ed almeno carattere speciale) 
-- Implementiamo il vincolo tramite un costrutto Trigger-Function
CREATE FUNCTION Function_Strong_Password() RETURNS TRIGGER LANGUAGE PLPGSQL AS $$
BEGIN 
	IF NEW.Pw IS NOT LIKE '%[a-z]%' OR NEW.Pw IS NOT LIKE '%[A-Z]%' OR NEW.Pw IS NOT LIKE '%[0-9]%' OR NEW.Pw IS NOT LIKE '%[!"£$%&/()=?_:;,.-]%' THEN 
		DELETE FROM STUDENT WHERE Pw = NEW.Pw;
	END IF;
END; $$

CREATE TRIGGER Trigger_Strong_Password
AFTER INSERT ON STUDENT OR INSERT ON PROFESSOR
FOR EACH ROW 
EXECUTE PROCEDURE Function_Strong_Password

-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili (Dominio = {'a', 'b', 'c', 'd'})
CREATE DOMAIN CLOSED_ANSWER AS CHAR(1)
	CHECK ( VALUE IN ('a', 'b', 'c', 'd') ); -- Aggiungere il controllo per c e d
-- Questo vincolo verrà implementato tramite funzione


