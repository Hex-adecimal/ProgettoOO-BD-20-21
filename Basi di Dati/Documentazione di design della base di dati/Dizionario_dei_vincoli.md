### Vincoli di integrità intrarelazionale
- Unique_Username : Non devono esistere più utenti con lo stesso username
- Unique_Email : Non devono esistere più utenti con la stessa email
- Unique_NameTest: Non devono esistere più test con lo stesso nome

### Vincoli di dominio
NOME VINCOLO | DESCRIZIONE
- Valid_Name : I nomi devono contenere solo caratteri compresi tra A-Z e a-z. Inoltre devono avere almeno 1 carattere e al più 35 caratteri.
- Valid_Test_Name : Il nome del test può contenere caratteri compresi tra A-Z e a-z, ed inoltre può contenere numeri 0-9, e spazi, può avere un numero di caratteri compreso tra 1 e 30.
- Valid_Email : La mail deve avere la forma di u@v.w con u, v, w stringhe non nulle 
- Valid_Starting_Date_Time : La data di inizio del test deve essere successiva al giorno in cui viene creato il test
- Valid_ClosingDateTime : La differenza tra ClosingDateTime e StartingDateTime deve essere maggiore o uguale di 10 minuti
- Valid_Max_Score : Il punteggio massimo deve essere maggiore di 0
- Valid_Max_Length : La lunghezza massima deve essere maggiore di 0
- Valid_Right_Answer : La risposta di una domanda multipla deve tra quelle possibili (Dominio = {'a', 'b', 'c', 'd'})
- Valid_Question : La domanda deve essere non nulla
- Strong_Password : La password deve essere composta da più di 8 caratteri, almeno una lettera, almeno un numero ed almeno carattere speciale
- Valid_CFU : Il numero di CFU deve essere compreso tra 1 e 20


//Non è stato imposto nessun vincolo sul punteggio "Score" delle risposte, per lasciare più libertà ai professori nella scelta dello stesso

Ecco la lista degli attributi parziali:
- ClosingDateTime, StartingDateTime (Se un professore vuole lasciare un fac-simile del test per un autovalutazione)
- AnswerC, AnswerD (per una domanda vero/falso)
- OpenAnswer.GivenAnswer (se lo studente non risponde alla domanda)
- ClosedAnswer.GivenAnswer (se lo studente non risponde alla domanda)

L'insieme complementare di questi attributi è da considerarsi un insieme di attributi totali.

Lista di valori di default degli attributi
- Revised = 0
- Score = 0
- TotalScore = 0


### Vincoli di chiave
- Student.StudentID è la chiave primaria dello studente

Per tutte le altre entità verrano creati attributi specifici per rendere unica ogni istanza.
