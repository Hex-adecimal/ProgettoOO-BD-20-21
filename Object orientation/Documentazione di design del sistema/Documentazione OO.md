# Diagramma delle classi del dominio del problema
A partire dal diagramma delle classi, per ottenere il class diagram del dominio del problema, sono state aggiunte le seguenti responsabilità:
- **User:**
  - registerUser() : Rappresenta l'azione di un utente generico (professore o studente) di registrarsi al sistema.
- **Student:**
  - turnInTest() : Rappresenta l'azione dello studente di eseguire un test;
  - viewTestResult() : Rappresenta l'azione dello studente di visualizzare il risultato di un test, è possibile solo se il professore ha corretto quel test.
- **Professor:**
  - createTest() : Rappresenta l'azione del professore di creare un test;
  - reviseOpenAnswer() : Rappresenta l'azione del professore di correggere una domanda a risposta aperta;
  - createClass() : Rappresenta l'azione del professore di creare una classe;
  - createLecture() : Rappresenta l'azione del professore di creare una lezione.
- **ClosedAnswer:**
  - reviseClosedAnswer() : Rappresenta l'azione del sistema di correggere automaticamente le domande a risposta chiusa.

Inoltre la visibilità della maggior parte degli attributi è stata messa privata, tranne per gli attributi di **User** e **Quiz**, essendo due generalizzazioni. Verrano creati degli appositi "getter & setter" per l'accesso degli attributi.
Infine è stato reso il modello più simile alla realtà aggiungento le composizioni tra **OpenAnswer, ClosedAnswer** e **TestTaken**.

<img width="1349" alt="Schermata 2022-02-05 alle 17 32 26" src="https://user-images.githubusercontent.com/91316353/152650437-59f9b909-1e32-4075-930c-aa1010bbf4e5.png">


# Diagramma di dettaglio delle classi nel dominio della soluzione
## Model
A partire dal diagramma del dominio del problema, sono stati specificati i tipi ad ogni attributo, e sono state creati i prototipi delle funzioni, che hanno sostituito le responsabilità. 

<img width="1194" alt="Schermata 2022-02-05 alle 17 42 56" src="https://user-images.githubusercontent.com/91316353/152650557-4b0d0bd8-9625-4a27-b271-f25ceb8b4a2e.png">

### Dizionario degli attributi
 Nome Classe | Attributo | Visibilità | Tipo   | Descrizione 
 ----------- | --------- | ---------- | -------| ----------- 
 Test        | Name      | Private | String | Il nome del test 
 Test        | CreationDate | Private | Date | 
 Test        | StartingDateTime | Private | DateTime | 
 Test        | ClosingDateTime | Private | DateTime | 
 Test        | MinScore | Private | float | 
 --  
 Quiz        | Question  | Protected | String | La domanda del quiz
 -- 
 ClosedQuiz | AnswerA | Private | String | 
 ClosedQuiz | AnswerB | Private | String |
 ClosedQuiz | AnswerC | Private | String | 
 ClosedQuiz | AnswerD | Private | String | 
 ClosedQuiz | RightAnswer | Private | char | 
 ClosedQuiz | ScoreIfRight | Private | float | 
 ClosedQuiz | ScoreIfWrong | Private | float | 
 -- 
 OpenQuiz | MaxScore | Private | float | 
 OpenQuiz | MinScore | Private | float | 
 OpenQuiz | MaxLength | Private | int | 
 -- 
 ClosedAnswer | GivenAnswer | Private | char | 
 ClosedAnswer | Score | Private | float | 
 -- 
 OpenAnswer | GivenAnswer | Private | String | 
 OpenAnswer | Score | Private | float | 
 -- 
 TestTaken | Revised | Private | boolean | 
 TestTaken | Passed | Private | boolean |
 TestTaken | TotalScore | Private | float |
 -- 
 User | FirstName | Protected | String |
 User | LastName | Protected | String |
 User | Email | Protected | String |
 User | Username | Protected | String |
 User | Password | Protected | String |
 -- 
 Student | StudentID | private | String |
 -- 
 Class | Name | Private | String |
 Class | CFU | Private | int |
 Class | Year | Private | int |
 -- 
 Lecture | Title | Private | String |
 Lecture | Link | Private | String |

I tipi dei metodi "create" sono stati scelti interi, per poter riportare eventuali errori.

## DAO *
Per le interfacce DAO sarà presente un interfaccia per ogni classe del model, anche se nell'implementazione delle DAO tramite PostgreSQL le classi User e Quiz non verranno implementate, dato che il database PostgreSQL che viene utilizzato non sfrutta la funzionalità NoSql, ma solo Sql. 

Tutte le interfacce conterranno dei metodi per la lettura e modifica di ogni colonna nel database, che rappresenterà un rispettivo attributo della rispettiva classe nel model. 

Inoltre appunto verranno implementate le responsabilità, presenti nel class diagram della soluzione, che riguardano la comunicazione con il database:
### Dizionario dei metodi DAO
 Nome Classe | Metodo | Visibilità | Tipo | Parametri
 ----------- | --------- | ---------- | -------| ----------- 
 User | registerUser | Public | int | String firstName, String lastName, String email, String username, String Password
 Professor | createTest | Public | int | String name, DateTime startingDateTime, DateTime closingDateTime, float minScore
 Professor | reviseOpenAnswer | Public | Void | OpenAnswer answer
 Professor | createClass | Public | int | String name, int CFU
 Professor | createLecture | Public | int | String title, String link
 Student | turnInTest | Public | int | TestTaken test
 Student | viewTestResult | Public | Void | TestTaken test
 ClosedAnswer | reviseClosedAnswer | Void | ClosedQuiz quiz

<img width="1001" alt="Schermata 2022-02-08 alle 20 01 28" src="https://user-images.githubusercontent.com/91316353/153057508-f165bd4b-abc7-4ac2-88ea-438c456f6a11.png">

## Controller

## GUI

# Sequence diagram (di due funzionalità scelte)
