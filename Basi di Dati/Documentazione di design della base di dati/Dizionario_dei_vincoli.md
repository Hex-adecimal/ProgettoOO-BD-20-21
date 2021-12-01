### Vincoli di integrità interreferenziale
- Unique_Username : Non devono esistere più utenti con lo stesso username
- Unique_Email : Non devono esistere più utenti con la stessa email


### Vincoli di dominio
NOME VINCOLO | DESCRIZIONE
- Valid_Name : Deve contenere solo caratteri compresi tra A-Z e a-z
- Valid_Email : La mail deve avere la forma di u@v.w con u, v, w stringhe non nulle 
- Valid_Test_Date : La data deve essere maggiore-uguale ad oggi
- Valid_Duration : La durata deve essere maggiore-uguale di 10 minuti
- Valid_Max_Score : Il punteggio massimo deve essere maggiore di 0
- Valid_Max_Length : La lunghezza massima deve essere maggiore di 0
- Valid_Answer : La risposta di una domanda multipla deve tra quelle possibili
- Valid_Question : La domanda deve essere non nulla
- Strong_Password : La password deve essere composta da più di 8 caratteri, almeno una lettera, almeno un numero ed almeno carattere speciale
- Valid_CFU : Il numero di CFU deve essere compreso tra 1 e 20

//Non è stato imposto nessun vincolo sul punteggio "Score" delle risposte, per lasciare più libertà ai professori nella scelta dello stesso

Ecco la lista degli attributi parziali:
- BeginDate, TestDuration (Se un professore vuole lasciare un fac-simile del test per un autovalutazione)
- AnswerC, AnswerD (per una domanda vero/falso)
- OpenAnswer.Answer 
- ClosedAnswer.Answer (se lo studente non risponde alla domanda)

L'insieme complementare di questi attributi è da considerarsi un insieme di attributi totali.

Lista di valori di default degli attributi
- Correct = 0
- "Everyone.Score" = 0

### Vincoli di chiave
- Student.StudentID è la chiave primaria dello studente
- Test.Name è la chiave primaria del test

Per tutte le altre entità verrano creati attributi specifici per rendere unica ogni istanza.
