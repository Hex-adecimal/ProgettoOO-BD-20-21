-- Quando viene inserito un Test, viene automaticamente aggiornato il campo CreationDate;
-- Quando viene inserita una Class, viene automaticamente aggiornato il campo Year all'anno corrente;

-- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili (Dominio = {'a', 'b', 'c', 'd'})
CREATE DOMAIN CLOSED_ANSWER AS CHAR(1)
	CHECK ( VALUE IN ('a', 'b', 'c', 'd') ); -- Aggiungere il controllo per c e d
-- Questo vincolo verrà implementato tramite funzione

-- //-------------------------------------------------------------------------//

-- Valid_GivenAnswer: La lunghezza della risposta data NON deve superare MaxLength dell'OpenQuiz associato
CREATE FUNCTION VGA_function() RETURNS TRIGGER AS $$
BEGIN
	IF LENGTH(NEW.GivenAnswer) > (  SELECT MaxLength
									FROM OPEN_ANSWER AS OA, OPEN_QUIZ AS OQ
									WHERE OA.CodOQ = OQ.CodOQ
										AND OA.CodOA = NEW.CodOA ) THEN

			-- errore, fai qualcosa a riguardo
		RAISE EXCEPTION 'ERRORE! Risposta troppo lunga!';

	END IF;

	RETURN NEW;
END; $$ LANGUAGE PLPGSQL;

CREATE TRIGGER Valid_GivenAnswer
AFTER INSERT ON OPEN_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE VGA_function();
