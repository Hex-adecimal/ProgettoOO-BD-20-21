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

<img width="1230" alt="Schermata 2022-01-30 alle 12 28 05" src="https://user-images.githubusercontent.com/91316353/151697758-f4b0031b-8607-4fb4-a1f9-1cb4cecc521b.png">


# Diagramma di dettaglio delle classi nel dominio della soluzione
## Model
A partire dal diagramma del dominio del problema, sono stati specificati i tipi ad ogni attributo, e sono state creati i prototipi delle funzioni, che hanno sostituito le responsabilità. 

<img width="1168" alt="Schermata 2022-01-30 alle 12 44 40" src="https://user-images.githubusercontent.com/91316353/151698276-4b208fe7-bae7-49dd-a9c8-dea9e7bc81ae.png">


### Dizionario degli attributi
 Nome Classe | Attributi | Visibilità | Tipo   | Descrizione 
 ----------- | --------- | ---------- | -------| ----------- 
 Test        | Name      | Private | String | Il nome del test 
 Test        | CreationDate | Private | Date | 
 Test        | StartingDateTime | Private | DateTime |
 Test        | ClosingDateTime | Private | DateTime | 
 Test        | MinScore | Private | float | 
 // | // | // | // | // 
 Quiz        | Question  | Protected | String | La domanda del quiz
 // | // | // | // | //
 ClosedQuiz | AnswerA | Private | String | 
 ClosedQuiz | AnswerB | Private | String |
 ClosedQuiz | AnswerC | Private | String | 
 ClosedQuiz | AnswerD | Private | String | 
 ClosedQuiz | RightAnswer | Private | char | 
 ClosedQuiz | ScoreIfRight | Private | float | 
 ClosedQuiz | ScoreIfWrong | Private | float | 
 // | // | // | // | //
 OpenQuiz | MaxScore | Private | float | 
 OpenQuiz | MinScore | Private | float | 
 OpenQuiz | MaxLength | Private | int | 
 // | // | // | // | //
 ClosedAnswer | GivenAnswer | Private | char | 
 ClosedAnswer | Score | Private | float | 
 // | // | // | // | //
 OpenAnswer | GivenAnswer | Private | String | 
 OpenAnswer | Score | Private | char | 
 // | // | // | // | //
 TestTaken | Revised | Private | boolean | 
 TestTaken | Passed | Private | boolean |
 TestTaken | TotalScore | Private | float |
 // | // | // | // | //
 User | FirstName | Protected | String |
 User | LastName | Protected | String |
 User | Email | Protected | String |
 User | Username | Protected | String |
 User | Password | Protected | String |
 // | // | // | // | //
 Student | StudentID | Protected | String |
 // | // | // | // | //
 Class | Name | Private | String |
 Class | CFU | Private | int |
 Class | Year | Private | int |
 // | // | // | // | //
 Lecture | Title | Private | String |
 Lecture | Link | Private | String |
 
### Dizionario dei metodi 
 
## Controller

## GUI

# Sequence diagram (di due funzionalità scelte)
