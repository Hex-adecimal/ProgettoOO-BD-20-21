### Istruzioni per leggere ALL-IN-ONE
- [Dalla riga 1 alla riga 40 viene mostrata la creazione dei **domini**;](https://github.com/Hex-adecimal/ProgettoOO-BD-20-21/blob/cc936872ba0799da14243d160b088b82b70e3a3f/Basi%20di%20Dati/SQL/ALL_IN_ONE.sql#L2)
- [Dalla riga 40 alla riga 290 viene mostrata le creazione delle **tabelle**;](https://github.com/Hex-adecimal/ProgettoOO-BD-20-21/blob/cc936872ba0799da14243d160b088b82b70e3a3f/Basi%20di%20Dati/SQL/ALL_IN_ONE.sql#L41)
- [Dalla riga 290 alla riga 523 vengono mostrati i **trigger** e le **procedure** implementate;](https://github.com/Hex-adecimal/ProgettoOO-BD-20-21/blob/cc936872ba0799da14243d160b088b82b70e3a3f/Basi%20di%20Dati/SQL/ALL_IN_ONE.sql#L290)
- [Dalla riga 675 alla riga 885 viene mostrala la **popolazione** del database.](https://github.com/Hex-adecimal/ProgettoOO-BD-20-21/blob/cc936872ba0799da14243d160b088b82b70e3a3f/Basi%20di%20Dati/SQL/ALL_IN_ONE.sql#L676)

### Istruzioni per la creazione di un utente, e per la gestione dei privilegi
```SQL
CREATE USER Professore PASSWORD 'LeUltimeCinqueCifreDelPiGreco'; -- Per creare un nuovo utente
GRANT privilegies ON table TO user -- Per garantire/revocare dei privilegi
-- privilegies can be ALL
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

### Eccezioni 
- E000C : Eccezione nel caso in cui l'utente abbia risposta 'c' ad una domanda a risposta multipla, quando quella non è presente tra le possibili risposte;
- E000D : Eccezione nel caso in cui l'utente abbia risposta 'd' ad una domanda a risposta multipla, quando quella non è presente tra le possibili risposte;
- T00LG : Eccezione nel caso in cui l'utente abbia risposto con un numero di caratteri maggiori di quelli consentiti dalla domanda;
- SF001 : Eccezione se la select restituisce più di una tupla;
- SNIMM : Eccezione se il voto inserito dal professore, per una domanda a risposta aperta, non è compreso tra quelli consentiti;
