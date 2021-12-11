-- //------------------------------------------------------- PROFESSOR ----------------------------------------------------------//
INSERT INTO PROFESSOR VALUES
	(1, 'Silvio', 'Barra', 'silvio.barra@unina.it', 'SilvioBarra', 'LaPasswordSegretaDelProfessore!1'),
  	(2, 'Porfirio', 'Tramontana', 'porfirio.tramontana@unina.it', 'PorfirioTramontana', 'LaPasswordSegretaDelProfessore!2'),
  	(3, 'Guglielmo', 'Tamburrini', 'guglielmo.tamburrini@unina.it', 'GuglielmoTamburrini', 'LaPasswordSegretaDelProfessore!3'),
  	(4, 'Fabio', 'Mogavero', 'fabio.mogavero@unina.it', 'FabioMogaver', 'LaPasswordSegretaDelProfessore!4'),
  	(5, 'Eleonora', 'Messina', 'eleonora.messina@unina.it', 'EleonoraMessina', 'LaPasswordSegretaDelProfessore!5'),
  	(6, 'Giovanni', 'Cutolo', 'giovanni.cutolo@unina.it', 'GiovanniCutolo', 'LaPasswordSegretaDelProfessore!6'),
  	(7, 'Francesca', 'Cioffi', 'francesca.cioffi@unina.it', 'FrancescaCioffi', 'LaPasswordSegretaDelProfessore!7'),
  	(8, 'Daniele', 'Castorina', 'daniele.castorina@unina.it', 'DanieleCastorina', 'LaPasswordSegretaDelProfessore!8'),
  	(9, 'Silvia', 'Rossi', 'silvia.rossi@unina.it', 'SilviaRossi', 'LaPasswordSegretaDelProfessore!9'),
  	(10, 'Francesco', 'Isgrò', 'francesc.isgro@unina.it', 'FrancescoIsgro', 'LaPasswordSegretaDelProfessore!10');
  
-- //------------------------------------------------------ STUDENT -------------------------------------------------------------// 
INSERT INTO STUDENT(FirstName, LastName, Email, Username, Pw) VALUES
  	(1, 'Francesco', 'Orlando', 'f.orlando@studenti.unina.it', 'Effeo', 'Giallo1_'),
  	(2, 'Alfredo', 'Laino', 'a.laino@studenti.unina.it', 'pino.pompino', 'Rosso2?'),
  	(3, 'Marco', 'Pastore', 'm.pastore@studenti.unina.it', 'marco_pastazio', 'BluElettrico$'),
  	(4, 'Giorgio', 'Longobardo', 'g.longobardo@studenti.unina.it', 'giovgio', 'RamarroMarron3?');
	
-- //------------------------------------------------------ CLASS ---------------------------------------------------------------// 
INSERT INTO CLASS VALUES 
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
  
-- //------------------------------------------------------ TAKE ----------------------------------------------------------------// 
INSERT INTO TAKE VALUES
	(1, 1),
	(3, 6),
	(2, 5);
  
-- //------------------------------------------------------ OPENANSWER ----------------------------------------------------------// 
--INSERT INTO OPEN_ANSWER VALUES
	

-- //------------------------------------------------------ CLOSEDANSWER --------------------------------------------------------// 
--INSERT INTO CLOSED_ANSWER VALUES
	

-- //------------------------------------------------------ TESTTAKEN -----------------------------------------------------------// 
--INSERT INTO TEST_TAKEN VALUES
	

-- //------------------------------------------------------ CLOSEDQUIZ ----------------------------------------------------------// 
INSERT INTO CLOSED_QUIZ(Question, AnswerA, AnswerB, AnswerC, AnswerD, RightAnswer, ScoreIfRight, ScoreIfWrong, CodTest) VALUES
	('Cos è un sistema lineare?', 'Qualcosa di bello', 'Qualcosa di carino', 'Qualcosa di brutto', 'Qualcosa di qualcosa', 'b', 1, 0, 1),
	('Cos è un autovettore?', 'Un vettore che va in auto', 'Un vettore che dopo una trasformazione lineare viene solo scalato', 'Non lo so', 'Per me è la cipolla', 'b', 1, 0, 1),
	('Un cerchio è uno spazio vettoriale?', 'Si', 'No', 'b', 1, 0, 1);
	
-- //------------------------------------------------------ OPENQUIZ ------------------------------------------------------------// 
INSERT INTO OPEN_QUIZ(Question, MaxScore, MinScore, MaxLength, CodTest) VALUES
	('Di che colore era il cavallo bianco di napoleone?', 1 , 0, 10, 1),
	('Cos è uno spazio vettoriale?', 1 , 0, 10, 1);

-- //------------------------------------------------------ LECTURE -------------------------------------------------------------// 
INSERT INTO LECTURE VALUES
	(1, 'Il metodo del gradiente', 'www.matlab.com', 5, 5);
	

-- //------------------------------------------------------ TEST ---------------------------------------------------------------// 
INSERT INTO TEST(CodTest, Name, CodP) VALUES
	(1, 'Prima prova intercorso di Geometria 2020-2021', 7);
	
	

