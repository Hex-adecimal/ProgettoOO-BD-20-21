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
A partire dal diagramma del dominio del problema, sono stati specificati i tipi ad ogni attributo, e sono state creati i prototipi delle funzioni, che hanno sostituito le responsabilità. 

Aggiungere il diagramma qui.

## Dizionario
- 

# Sequence diagram (di due funzionalità scelte)
