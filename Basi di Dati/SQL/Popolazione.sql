-- //------------------------------------------------------- PROFESSOR ----------------------------------------------------------//
INSERT INTO PROFESSOR(FirstName, LastName, Email, Username, Pw) VALUES
	('Silvio', 'Barra', 'silvio.barra@unina.it', 'SilvioBarra', 'LaPasswordSegretaDelProfessore!1'),
  	('Porfirio', 'Tramontana', 'porfirio.tramontana@unina.it', 'PorfirioTramontana', 'LaPasswordSegretaDelProfessore!2'),
  	('Guglielmo', 'Tamburrini', 'guglielmo.tamburrini@unina.it', 'GuglielmoTamburrini', 'LaPasswordSegretaDelProfessore!3'),
  	('Fabio', 'Mogavero', 'fabio.mogavero@unina.it', 'FabioMogaver', 'LaPasswordSegretaDelProfessore!4'),
  	('Eleonora', 'Messina', 'eleonora.messina@unina.it', 'EleonoraMessina', 'LaPasswordSegretaDelProfessore!5'),
  	('Giovanni', 'Cutolo', 'giovanni.cutolo@unina.it', 'GiovanniCutolo', 'LaPasswordSegretaDelProfessore!6'),
  	('Francesca', 'Cioffi', 'francesca.cioffi@unina.it', 'FrancescaCioffi', 'LaPasswordSegretaDelProfessore!7'),
  	('Daniele', 'Castorina', 'daniele.castorina@unina.it', 'DanieleCastorina', 'LaPasswordSegretaDelProfessore!8'),
  	('Silvia', 'Rossi', 'silvia.rossi@unina.it', 'SilviaRossi', 'LaPasswordSegretaDelProfessore!9'),
  	('Francesco', 'Isgrò', 'francesc.isgro@unina.it', 'FrancescoIsgro', 'LaPasswordSegretaDelProfessore!10');
  
-- //------------------------------------------------------ STUDENT -------------------------------------------------------------// 
INSERT INTO STUDENT(FirstName, LastName, Email, Username, Pw) VALUES
  	('Francesco', 'Orlando', 'f.orlando@studenti.unina.it', 'Effeo', 'Giallo1_'),
  	('Alfredo', 'Laino', 'a.laino@studenti.unina.it', 'pino.pompino', 'Rosso2?'),
  	('Marco', 'Pastore', 'm.pastore@studenti.unina.it', 'marco_pastazio', 'BluElettrico$'),
  	('Giorgio', 'Longobardo', 'g.longobardo@studenti.unina.it', 'giovgio', 'RamarroMarron3?');
	
-- //------------------------------------------------------ CLASS ---------------------------------------------------------------// 
INSERT INTO CLASS(Name, Year, CFU, CodP) VALUES -- Aggiungere i codici prof
  	('Basi di dati', '2021', 9, ''),
	('Object orientation', '2021', 6, ''),
	('Elementi di informatica teorica', '2021', 6, ''),
	('Algoritmi e strutture dati', '2021', 9, ''),
	('Scientific computing', '2021', 6, ''),
	('Algebra', '2020', 9, ''),
	('Geometria', '2021', 6, ''),
	('Analisi I', '2021', 9, ''),
	('Architettura degli elaboratori', '2021', 9, ''),
	('Laboratorio di programmazione', '2021', 9, '');
  
-- //------------------------------------------------------ TAKE ----------------------------------------------------------------// 
INSERT INTO TAKE VALUES
	('', '')
  
-- //------------------------------------------------------ OPENANSWER ----------------------------------------------------------// 
INSERT INTO OPEN_ANSWER VALUES
	()

-- //------------------------------------------------------ CLOSEDANSWER --------------------------------------------------------// 
INSERT INTO CLOSED_ANSWER VALUES
	

-- //------------------------------------------------------ TESTTAKEN -----------------------------------------------------------// 
INSERT INTO TEST_TAKEN VALUES
	

-- //------------------------------------------------------ CLOSEDQUIZ ----------------------------------------------------------// 
INSERT INTO CLOSED_QUIZ(Question, AnswerA, AnswerB, AnswerC, AnswerD, RightAnswer, ScoreIfRight, ScoreIfWrong, CodTest) VALUES
	('Cosa sono le derivate parziali?', 'Qualcosa di bello', 'Qualcosa di carino', 'Qualcosa di brutto', 'Qualcosa di qualcosa', 'b', 1, 0, ''),
	

-- //------------------------------------------------------ OPENQUIZ ------------------------------------------------------------// 
INSERT INTO OPEN_QUIZ(Question, MaxScore, MinScore, MaxLength, CodTest) VALUES
	('Di che colore era il cavallo bianco di napoleone?', 1 , 0, 10, ''),
	

-- //------------------------------------------------------ LECTURE -------------------------------------------------------------// 
INSERT INTO LECTURE VALUES
	('Il metodo del gradiente', 'www.matlab.com', ''),
	

-- //------------------------------------------------------ TEST ---------------------------------------------------------------// 
INSERT INTO TEST(Name, CodP) VALUES
	('Prima prova intercorso di Geometria 2020-2021', ''),
	
	

