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
- Valid_GivenAnswer: La lunghezza della risposta data NON deve superare MaxLength dell'OpenQuiz associato
- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili

### Operazioni automatiche
- Update_Closed_Quiz_Score : Correzione automatica delle domande a risposta chiusa.
