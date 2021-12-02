- TEST(**Name**, CreationDate, StartingDateTime, Duration, MinScore, CodP)
  - CodP -> PROFSSOR.CodP
- PROFESSOR(**CodP**, FirstName, LastName, Email, Username, Password)
- LECTURE(**Title**, Link, **CodP**)
  - CodP -> PROFESSOR.CodP
- OPENQUIZ(**CodOQ**, Question, MaxScore, MinScore, MaxLength, NameTest)
  - NameTest -> TEST.NameTest
- CLOSEDQUIZ(**CodCQ**, Question, AnswerA, AnswerB, AnswerC, AnswerD, RightAnswer, ScoreIfRight, ScoreIfWrong, NameTest)
  - NameTest -> TEST.NameTest
- CLASS(**CodC**, Name, Year, CFU, CodP)
  - CodP -> PROFESSOR.CodP
- TAKE(**CodC, StudentID**)
  - CodC -> CLASS.CodC
  - StudentID -> STUDENT.StudentID
- OPENANSWER(**CodOA**, GivenAnswer, Score, CodOQ, NameTest, StudentID)
  - CodOQ -> OPENQUIZ.CodOQ
  - NameTest -> TESTTAKEN.NameTest
  - StudentID -> TESTTAKEN.StudentID
- CLOSEDANSWER(**CodCA**, GivenAnswer, Score, CodCQ, NameTest, StudentID)
  - CodCQ -> CLOSEDQUIZ.CodCQ
  - NameTest -> TESTTAKEN.NameTest
  - StudentID -> TESTTAKEN.StudentID
- STUDENT(**StudentID**, FirstName, LastName, Email, Username, Password)
- TESTTAKEN(**NameTest, StudentID**, Revised)
  - NameTest -> TEST.NameTest
  - StudentID -> STUDENT.StudentID 

//codice classe e non tutte pk perchè dopo la chiave va memoriazzata in take
// In ClosedAnswer e OpenAnswer cosa conviene? più chiavi o no?
