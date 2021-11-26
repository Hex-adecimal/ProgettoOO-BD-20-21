Si sviluppi un sistema informativo, composto da una base di dati relazionale e da un applicativo Java dotato di GUI (Swing o JavaFX) per l’e-learning che 
consenta di gestire test basati su quiz.

I test possono essere inseriti nel sistema da un **insegnante**, che si registrerà inserendo il proprio nome e cognome, oltre che una login e una password.

Un **test** consiste di un insieme di **quiz** che deve essere fissato alla creazione del test. Ogni **test** è caratterizzato da un **nome univoco** che lo identifica.

Esistono **due tipi di quiz**: quiz ‘a risposta multipla’ e quiz ‘a risposta aperta’. 

Un quiz a **risposta multipla** è caratterizzato da una **domanda** (espressa tramite un breve testo), un elenco di **possibili risposte** (ognuna delle quali descritta da un breve testo ma una sola delle quali è da ritenersi corretta), il **punteggio** da assegnare in caso di risposta esatta e il punteggio (eventualmente anche negativo) da assegnare in caso di risposta errata.

Un quiz a **risposta aperta** è caratterizzato, oltre che da un breve testo che descrive la **domanda** posta, dalla **massima lunghezza** prevista per il testo di **risposta** e dai **punteggio** minimo e massimo che l’insegnante potrà assegnare in base alla correttezza della risposta.

Anche gli **studenti** si registreranno al sistema immettendo il proprio nome e cognome oltre che una login e password.

Uno studente può sostenere un test scegliendolo dall’elenco dei test inseriti, fornendo una risposta tra quelle proposte per ogni quiz a risposta multipla 
e un testo per ogni quiz a risposta aperta.

L’**insegnante** che ha creato il test ha il compito di **valutare** la correttezza di tutte le risposte ai quiz a **risposta aperta** degli studenti che hanno sostenuto il test **assegnando un punteggio** compreso tra il minimo e il massimo previsti per quel quiz, a in modo che il sistema possa calcolare il numero esatto di risposte corrette fornite dallo studente per quel test. Lo studente può stampare l’insieme di tutti i risultati dei test che ha valutato.

Lo studente può consultare il punteggio ottenuto dal suo test solo dal momento in cui l’insegnante ha completato la valutazione.
