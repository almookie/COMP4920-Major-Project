
//	Init New System
Markbook markbook = new Markbook();

//	Create(get) School from Markbook
School school = markbook.getSchool();
school.setName("Fun School");

//	Populate School with Grades
int startYear = 7;
int gradYear = 2016;
int num_grades = 5;

for (int i = 0; i < 5; i++) {
	school.addGrade(new Grade(startYear, gradYear));
  	startYear++;
  	gradYear++;
}

//	Populate Grades with Subjects
subjName[] = {
  	"English",
  	"Mathematics",
  	"Science",
  	"History",
  	"Art",
  	"Geography",
  	"Design and Technology"
}

subjCode[] = {
  "EN",
  "MA",
  "SC",
  "HS",
  "AT",
  "GE",
  "DT"
}

int i = 0;
for (Grade g : school.getGradeList()) {
  for (Subject name : subjName) {
  		g.addSubject(new Subject(name, subjCode[i])); 
    	i++;
  }
}
//	Populate Subjects with Class
//	Start at 6000 to not confuse with studentID
int classID = 6000;
for (Grade g : school.getGradeList()) {
  for(Subject s : g.getSubjects()) {
    //	Create two classes per Subject
    s.addClass(new Class(classID))
    classID++;
    s.addClass(new Class(classID))
    classID++;
  }
}

//	Create Students
int studentID = 0;
for (Grade g : school.getGradeList()) {
  for (int i = 0; i < 10; i ++) {
	Student s = new Student(givenName, surname, studentID, g);
    
    //	Link Student to Grade
    g.addStudent(s);
    
    //	Link Student to Classes
    for (Subject s : g.getSubjects()) {
      	classList = s.getClasses();
      
      //	Seperating students into two classes
      if(s.getStudentID % 2 != 0) {
		classList[0].addStudent(s);
        s.addClass(classList[0]);
      } else {
        classList[1].addStudent(s);
        s.addClass(classList[1]);
      }
  	}
  }
}

//	Create Demo Assessments
for (Grade g : school.getGradeList()) {
  for(Subject s : g.getSubjects()) {
    for (Class c : s.getClasses()) {
      
      //	Creating two demo assessments
      Assessment ass1 = new Assessment(assName1);
      Assessment ass2 = new Assessment(assName2);
      
      c.addAssessment(ass1);
      c.addAssessment(ass2);
      
      //	Adding Random Mark to each Assessment per Student
      for(Assessment ass : c.getAssessments()) {
        for (Student s : c.getStudents()) {
         	ass.addMark(s, rand());
        }
      }
    }
  }
