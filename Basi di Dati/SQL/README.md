### Istruzioni per leggere ALL-IN-ONE
- [Dalla riga 1 alla riga 40 viene mostrata la creazione dei **domini**;](https://github.com/Hex-adecimal/ProgettoOO-BD-20-21/blob/707d655233e6059dbd0195f489efd60f9d80267c/Basi%20di%20Dati/SQL/ALL_IN_ONE.sql#L2)
- [Dalla riga 40 alla riga 290 viene mostrata le creazione delle **tabelle**;](https://github.com/Hex-adecimal/ProgettoOO-BD-20-21/blob/707d655233e6059dbd0195f489efd60f9d80267c/Basi%20di%20Dati/SQL/ALL_IN_ONE.sql#L41)
- [Dalla riga 290 alla riga 523 vengono mostrati i **trigger** e le **procedure** implementate;](https://github.com/Hex-adecimal/ProgettoOO-BD-20-21/blob/707d655233e6059dbd0195f489efd60f9d80267c/Basi%20di%20Dati/SQL/ALL_IN_ONE.sql#L290)
- [Dalla riga 675 alla riga 885 viene mostrala la **popolazione** del database.](https://github.com/Hex-adecimal/ProgettoOO-BD-20-21/blob/707d655233e6059dbd0195f489efd60f9d80267c/Basi%20di%20Dati/SQL/ALL_IN_ONE.sql#L744)

### Istruzioni per la creazione di un utente, e per la gestione dei privilegi
```SQL
CREATE USER Professore PASSWORD 'LeUltimeCinqueCifreDelPiGreco'; -- Per creare un nuovo utente
GRANT privilegies ON table TO user -- Per garantire/revocare dei privilegi
-- privilegies can be ALL
GRANT ALL ON class_t TO Professore;
GRANT ALL ON closed_answer TO Professore;
GRANT ALL ON closed_quiz TO Professore;
GRANT ALL ON lecture TO Professore;
GRANT ALL ON open_answer TO Professore;
GRANT ALL ON open_quiz TO Professore;
GRANT ALL ON professor TO Professore;
GRANT ALL ON student TO Professore;
GRANT ALL ON take TO Professore;
GRANT ALL ON test TO Professore;
GRANT ALL ON test_taken TO Professore;
```
### Vincoli implementati tramite trigger e procedure
- Valid_GivenAnswer: La lunghezza della risposta data NON deve superare MaxLength dell'OpenQuiz associato.
- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili.
- Valid_Open_Score : Il punteggio dato alla risposta aperta, deve essere compreso tra maxScore e minScore.
- Unique_Username : Non devono esistere più utenti con lo stesso username.
- Unique_Email : Non devono esistere più utenti con la stessa email.

### Operazioni automatiche
- Update_Closed_Quiz_Score : Correzione automatica delle domande a risposta chiusa.
- Evaluate_Total_Score : Quando viene corretta una risposta, viene aggiornato il risultato totale.
- Test_Is_Passed : Quando il total score supera il min score, allora lo studente ha passato il test.

### Funzioni e procedure
- revise_function:  Inserito  un  test,  ed  un  professore,  ”committa”  tutti i  testtaken,  di  quel  test  (ovviamente  se  il  test  è  stato  creato  da  quel professore);
- username_for_student: Inserito un nuovo Username ed uno StudentID,cambia l’username se nessun altro utente sta usando quell’username;
- email_for_student:  Inserita un nuova Email ed uno StudentID, cambiala mail se nessun altro utente sta usando quella mail;
- username_for_professor:   Inserito  un  nuovo  Username  ed  un  CodP, cambia l’username se nessun altro utente sta usando quell’username;
- email_for_professor:  Inserita un nuova Email ed un CodP, cambia la mail se nessun altro utente sta usando quella mail;
- getStudent:  Dato in input lo Username di uno studente restituisce il rispettivo StudentID;
- getProfessor:  Dato in input lo Username di un professore restituisce il rispettivo CodP;
- getTest:  Dato in input il nome di un Test restituisce il rispettivo CodTest;
- getLecture: Dato il titolo di una lezione, restituisce il codice della stessa;

### Eccezioni 
- E000C(Errore 000 C) : Eccezione nel caso in cui l’utente abbia risposta’c’ ad una domanda a risposta multipla, quando quella non è presente trale possibili risposte;
- E000D(Errore 000 D) : Eccezione nel caso in cui l’utente abbia risposta’d’ ad una domanda a risposta multipla, quando quella non è presente trale possibili risposte;
- T00LG(Too long) :  Eccezione nel caso in cui l’utente abbia risposto con un numero di caratteri maggiori di quelli consentiti dalla domanda;
- SF001(Select  fault  001)  :  Eccezione  se  la  select  restituisce  più  di  una tupla;
- SNIMM(Score not in min max) :  Eccezione se il voto inserito dal professore,  per  una  domanda  a  risposta  aperta,  non  è  compreso  tra  quelli consentiti;
- UNALP(UserName ALready exists in Professor): Eccezione lanciata nel caso in cui uno Studente vuole inserire un username già esistente nella base di dati, in particolare tra i Professori.
- EMALP(EMail  ALready  exists  in  Professor):   Eccezione  lanciata  nel caso in cui uno Studente vuole inserire una Email già esistente nella base di dati, in particolare tra i Professori;
- UNALS(UserName ALready exists in Student): Eccezione lanciata se un professore vuole inserire un username che già appartiene ad uno studente.
- EMALS(EMail  ALready  exists  in  Student):  Eccezione  lanciata  se  un professore vuole inserire un email che già appartiene ad uno studente
