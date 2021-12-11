CREATE FUNCTION UCQS_function()
	RETURNS TRIGGER
AS
$$
	BEGIN
		IF EXISTS  (SELECT *
					FROM CLOSED_ANSWER AS CA, CLOSED_QUIZ AS CQ
					WHERE CA.CodCQ = CQ.CodCQ
						AND CA.CodCA = NEW.CodCA
						AND CA.GivenAnswer = CQ.RightAnswer) THEN
		
			UPDATE CLOSED_ANSWER
			SET Score = (SELECT ScoreIfRight
						 FROM CLOSED_ANSWER AS CA, CLOSED_QUIZ AS CQ
						 WHERE CA.CodCQ = CQ.CodCQ
							AND CA.CodCA = NEW.CodCA);
		
		ELSE

			UPDATE CLOSED_ANSWER
			SET Score = (SELECT ScoreIfWrong
						 FROM CLOSED_ANSWER AS CA, CLOSED_QUIZ AS CQ
						 WHERE CA.CodCQ = CQ.CodCQ
							AND CA.CodCA = NEW.CodCA);

		END IF;

		RETURN NEW;
	END; 
$$ 
LANGUAGE PLPGSQL;


CREATE TRIGGER Update_CQ_Score
AFTER INSERT ON CLOSED_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE UCQS_function();


-- Valid_GivenAnswer: La lunghezza della risposta data NON deve superare MaxLength dell'OpenQuiz associato

CREATE FUNCTION VGA_function()
	RETURNS TRIGGER
AS
$$
	BEGIN
		IF LENGTH(NEW.GivenAnswer) > (  SELECT MaxLength
										FROM OPEN_ANSWER AS OA, OPEN_QUIZ AS OQ
										WHERE OA.CodOQ = OQ.CodOQ
											AND OA.CodOA = NEW.CodOA ) THEN

			-- errore, fai qualcosa a riguardo
			RAISE EXCEPTION 'ERRORE! Risposta troppo lunga!';

		END IF;

		RETURN NEW;
	END; 
$$
LANGUAGE PLPGSQL;


CREATE TRIGGER Valid_GivenAnswer
AFTER INSERT ON OPEN_ANSWER
FOR EACH ROW
EXECUTE PROCEDURE VGA_function();