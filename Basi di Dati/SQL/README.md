## Istruzioni per leggere ALL-IN-ONE
- Dalla riga 1 alla riga 30 viene mostrata la creazione dei **domini**;
- Dalla riga 30 alla riga 254 viene mostrata le creazione delle **tabelle**;
- Dalla riga 289 alla riga 364 viene mostrala la **popolazione** del database.

## Istruzioni per la creazione di un utente, e per la gestione dei privilegi
Per creare un nuovo utente si usa il comando:
```SQL
CREATE USER Professore PASSWORD 'LaPasswordSegretaPerIlProfessore'
```

Per garantire/revocare dei privilegi si usa il comando:
```SQL
GRANT privileges ON table TO user -- privilegies can be ALL
```
### Vincoli implementati tramite trigger e procedure
- Valid_GivenAnswer: La lunghezza della risposta data NON deve superare MaxLength dell'OpenQuiz associato.
- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili.
- Valid_Open_Score : Il punteggio dato alla risposta aperta, deve essere compreso tra maxScore e minScore.

### Operazioni automatiche
- Update_Closed_Quiz_Score : Correzione automatica delle domande a risposta chiusa.
- Evaluate_Total_Score : Quando viene corretta una risposta, viene aggiornato il risultato totale.
