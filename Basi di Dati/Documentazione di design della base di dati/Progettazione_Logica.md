- TEST(**CodTest**, Name, CreationDate, StartingDateTime, ClosingDateTime, MinScore, CodP)
  - CodP -> PROFESSOR.CodP


- PROFESSOR(**CodP**, FirstName, LastName, Email, Username, Password)


- LECTURE(**Title**, Link, **CodP**)
  - CodP -> PROFESSOR.CodP


- OPENQUIZ(**CodOQ**, Question, MaxScore, MinScore, MaxLength, CodTest)
  - CodTest -> TEST.CodTest


- CLOSEDQUIZ(**CodCQ**, Question, AnswerA, AnswerB, AnswerC, AnswerD, RightAnswer, ScoreIfRight, ScoreIfWrong, CodTest)
  - CodTest -> TEST.CodTest


- CLASS(**CodC**, Name, Year, CFU, CodP)
  - CodP -> PROFESSOR.CodP


- TAKE(**CodC, StudentID**)
  - CodC -> CLASS.CodC
  - StudentID -> STUDENT.StudentID


- OPENANSWER(**CodOA**, GivenAnswer, Score, CodOQ, CodTest, StudentID)
  - CodOQ -> OPENQUIZ.CodOQ
  - CodTest -> TESTTAKEN.CodTest
  - StudentID -> TESTTAKEN.StudentID


- CLOSEDANSWER(**CodCA**, GivenAnswer, Score, CodCQ, CodTest, StudentID)
  - CodCQ -> CLOSEDQUIZ.CodCQ
  - CodTest -> TESTTAKEN.NameTest
  - StudentID -> TESTTAKEN.StudentID


- STUDENT(**StudentID**, FirstName, LastName, Email, Username, Password)


- TESTTAKEN(**CodTest, StudentID**, Revised, Passed, TotalScore)
  - CodTest -> TEST.CodTest
  - StudentID -> STUDENT.StudentID 


