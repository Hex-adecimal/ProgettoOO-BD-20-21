- TEST(**NameTest**, CreationDate, BeginDate, TestDuration, CodP)
  - CodP -> PROFSSOR.CodP
- PROFESSOR(**CodP**, FirstName, LastName, Email, Username, Password)
- LECTURE(**Title**, Link, **CodP**)
  - CodP -> PROFESSOR.CodP
- OPENQUIZ(**CodOQ**, Question, MaxScore, MinScore, MaxLength, NameTest)
  - NameTest -> TEST.NameTest
- CLOSEDQUIZ(**CodCQ**, Question, AnswerA, AnswerB, AnswerC, AnswerD, RightAnswer, ScoreIfRight, ScoreIfWrong, NameTest)
  - NameTest -> TEST.NameTest
- CLASS(**CodC**, Name, Year, CFU, **CodP**)
  - CodP -> PROFESSOR.CodP
- OPENANSWER(**CodOA**, Answer, Score, CodOQ, NameTest, StudentID)
  - CodOQ -> OPENQUIZ.CodOQ
  - NameTest -> TESTTAKEN.NameTest
  - StudentID -> TESTTAKEN.StudentID
- CLOSEDANSWER(**CodCA**, Answer, Score, CodCQ, NameTest, StudentID)
  - CodCQ -> CLOSEDQUIZ.CodCQ
  - NameTest -> TESTTAKEN.NameTest
  - StudentID -> TESTTAKEN.StudentID
- STUDENT(**StudentID**, FirstName, LastName, Email, Username, Password)
- TESTTAKEN(**NameTest, StudentID**)
  - NameTest -> TEST.NameTest
  - StudentID -> STUDENT.StudentID 