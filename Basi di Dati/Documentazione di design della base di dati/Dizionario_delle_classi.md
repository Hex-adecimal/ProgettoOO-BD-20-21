- Student
  - StudentID (String) : 
  - FirstName (String) :
  - LastName (String) :
  - Username (String) :
  - Password (String) :


- Professor
  - FirstName (String) : 
  - LastName (String) : 
  - Username (String) : 
  - Password (String) : 


- ClosedQuiz
  - Question (String) : 
  - AnswerA (String) : Una possibile risposta 
  - AnswerB (String) : Una possibile risposta 
  - AnswerC (String) : Una possibile risposta 
  - AnswerD (String) : Una possibile risposta 
  - RightAnswer (Enum) : La risposta corretta tra le quattro possibili
  - ScoreIfRight (Int) : Il punteggio che si ottiene rispondendo correttamente
  - ScoreIfWrong (Int) : Il punteggio che si ottiene non rispondendo correttamente


- OpenQuiz
  - Question (String) : 
  - MaxScore (Float) : Il massimo punteggio che si può ottenere rispondendo alla domanda
  - MinScore (Float) : Il minimo punteggio che si può ottenere rispondendo correttamente alla domanda
  - MaxLength (Int) : La massima lunghezza della risposta

- ClosedAnswer
  - GivenAnswer (Enum) : La risposta, tra le quattro possibili, data dallo studente
  - Score (Float) : Il punteggio che lo studente ha ottenuto

- OpenAnswer
  - GivenAnswer (String) : La risposta data dallo studente
  - Score (Float) : Il punteggio che lo studente ha ottenuto 

- Class
  - Name (String) : Il nome del corso
  - Year (Date) : L'anno accademico in cui il corso è svolto
  - CFU (Int) : Il numero di Crediti Formativi Universitari dedicati al corso

- Lecture
  - Title (String) : Il titolo della lezione
  - Link (String) : Un link ad un sito esterno dove è presente del materiale didattico rigardante la lezione

- Test
  - NameTest (String) : L'identificativo del test
  - CreationDate (Date) : La data in cui il test viene creato
  - StartingDateTime (Datetime) : La data e l'ora di inizio del test 
  - Duration (Int) : La durata del test
  - MinScore (Float) : Il punteggio minimo per passare il test

- TestTaken
  - Revised (Boolean) : Un valore che indica se il test è stato corretto dal professore oppure no
