- Studente
  - matricola (String) : 
  - nome (String) :
  - cognome (String) :
  - username (String) :
  - password (String) :


- Professore
  - nome (String) :
  - cognome (String) :
  - username (String) :
  - password (String) :


- QuizRispostaChiusa
  - domanda (String) :
  - a (String) :
  - b (String) :
  - c (String) :
  - d (String) :
  - rispostaCorretta (Enum) :
  - puntiCorretti (Int) :
  - puntiSbagliati (Int) :


- QuizRispostaAperta
  - domanda (String) :
  - maxLength (Int) :
  - puntiMax (Float) :
  - puntiMin (Float) :

- RispostaChiusa
  - rispostaData (Enum) :
  - punteggio (Float) :

- RispostaAperta
  - testo (String) :
  - punteggio (Float) :

- Corso
  - nome (String) :
  - anno (Date) :
  - cfu (Int) :

- Lezione
  - titolo (String) :
  - file (String) :

- Test
  - nome (String) :
  - dataCreazione (Date) :
  - dataOraInizio (Date) :
  - durata (Int) :

- TestSostenuto
  - corretto : boolean


