package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.Assessment;
import main.Student;

public class StatisticsTest {

	//	20 Marks [0-50]
	double[] test_marks_list1 = {
		26, 9, 33, 38, 14, 18, 16, 49, 25, 5, 
		29, 45, 50, 23, 49, 49, 24, 21, 48, 20
	};
	
	//	100 Marks [0-100]
	double[] test_marks_list2 = {
		36, 27, 57, 2, 97, 19, 97, 45, 29, 71, 
		65, 15, 91, 62, 22, 71, 85, 25, 99, 48, 
		12, 59, 11, 75, 81, 48, 38, 69, 36, 9, 
		9, 25, 37, 50, 89, 38, 12, 14, 57, 23, 
		70, 44, 81, 8, 97, 44, 93, 56, 37, 31, 
		73, 92, 38, 75, 15, 11, 36, 51, 87, 70, 
		53, 71, 31, 10, 8, 23, 54, 65, 12, 94, 
		93, 63, 3, 88, 83, 63, 74, 15, 20, 55, 
		25, 56, 19, 44, 67, 73, 93, 95, 44, 40, 
		75, 34, 100, 14, 23, 97, 40, 1, 38, 71
	};
	
	//	300 Marks [0-17]
	double[] test_marks_list3 = {
		13, 7, 13, 11, 7, 1, 10, 10, 17, 1, 
		9, 1, 5, 9, 12, 2, 5, 2, 9, 4, 
		12, 14, 6, 12, 5, 17, 17, 2, 16, 0, 
		6, 11, 13, 3, 3, 0, 1, 9, 3, 1, 
		5, 7, 2, 3, 4, 6, 4, 8, 15, 8, 
		7, 5, 2, 10, 17, 11, 1, 12, 9, 2, 
		12, 4, 8, 8, 9, 14, 7, 15, 9, 11, 
		1, 17, 17, 11, 9, 13, 7, 9, 0, 2, 
		1, 16, 3, 16, 8, 12, 0, 15, 2, 14, 
		7, 10, 13, 14, 17, 16, 9, 5, 17, 3, 
		2, 16, 2, 16, 15, 9, 16, 2, 9, 4, 
		15, 17, 13, 13, 8, 5, 11, 0, 16, 2, 
		1, 14, 17, 2, 0, 7, 16, 12, 0, 13, 
		9, 1, 16, 2, 16, 14, 4, 6, 7, 13, 
		7, 15, 13, 15, 2, 7, 8, 11, 11, 15, 
		11, 6, 17, 2, 4, 2, 17, 9, 3, 12, 
		1, 16, 6, 10, 7, 4, 9, 7, 8, 11, 
		6, 5, 8, 13, 11, 13, 15, 2, 15, 1, 
		16, 1, 13, 1, 16, 6, 10, 11, 13, 13, 
		1, 3, 12, 7, 7, 10, 14, 3, 6, 2, 
		5, 6, 3, 15, 6, 4, 1, 0, 12, 15, 
		15, 9, 8, 17, 10, 2, 3, 17, 10, 15, 
		7, 1, 0, 0, 12, 5, 9, 15, 11, 9, 
		0, 9, 10, 8, 0, 13, 9, 9, 6, 9, 
		1, 10, 4, 8, 2, 0, 5, 16, 2, 1, 
		1, 6, 10, 11, 3, 8, 15, 8, 8, 8, 
		2, 9, 2, 3, 15, 12, 3, 8, 11, 2, 
		11, 15, 14, 8, 11, 1, 12, 17, 15, 11, 
		16, 3, 15, 2, 14, 16, 7, 17, 5, 9,
		6, 11, 8, 7, 6, 0, 15, 7, 13, 1
	};
	
	@Test
	//	test 1 (80% weigh)
	public void test1() {
		ArrayList<Student> studentList1 = new ArrayList<Student>();
		Assessment test_assessment1 = new Assessment("test_assessment1", 0.8, studentList1);
		
		for(int i = 0; i < test_marks_list1.length; i++) {
			Student stu = new Student(i, "fname_" + i, "sname_" + i);
			studentList1.add(stu);
			test_assessment1.addMark(stu, test_marks_list1[i]);
		}
		
		test_assessment1.updateStatistics();
		
		assertEquals(29.55, test_assessment1.getMean(), 0.1);
		assertEquals(49, test_assessment1.getMode().get(0), 0);
		assertEquals(25.5, test_assessment1.getMedian(), 0);
		assertEquals(45, test_assessment1.getRange(), 0);
		assertEquals(14.27, test_assessment1.getStandardDeviation(), 1);

	}
	
	@Test
	//	test 2 (7% weigh)
	public void test2() {
		ArrayList<Student> studentList2 = new ArrayList<Student>();
		Assessment test_assessment2 = new Assessment("test_assessment2", 0.07, studentList2);
		
		for(int i = 0; i < test_marks_list2.length; i++) {
			Student stu = new Student(i, "fname_" + i, "sname_" + i);
			studentList2.add(stu);
			test_assessment2.addMark(stu, test_marks_list2[i]);
		}
		
		test_assessment2.updateStatistics();
		
		assertEquals(49.91, test_assessment2.getMean(), 0.1);
		assertEquals(38, test_assessment2.getMode().get(0), 0);
		assertEquals(44, test_assessment2.getMode().get(1), 0);
		assertEquals(71, test_assessment2.getMode().get(2), 0);
		assertEquals(97, test_assessment2.getMode().get(3), 0);
		assertEquals(48, test_assessment2.getMedian(), 0);
		assertEquals(99, test_assessment2.getRange(), 0);
		assertEquals(29.09, test_assessment2.getStandardDeviation(), 1);
		
	}

	@Test
	//		test 3 (45% weigh)
	//		Few Missing Marks
	public void test3() {
		ArrayList<Student> studentList3 = new ArrayList<Student>();
		Assessment test_assessment3 = new Assessment("test_assessment3", 0.45, studentList3);
		
		for(int i = 0; i < test_marks_list3.length; i++) {
			Student stu = new Student(i, "fname_" + i, "sname_" + i);
			studentList3.add(stu);
			test_assessment3.addMark(stu, test_marks_list3[i]);
		}
		
		test_assessment3.updateStatistics();
		
		assertEquals(8.34, test_assessment3.getMean(), 0.1);
		assertEquals(2, test_assessment3.getMode().get(0), 0);
		assertEquals(8, test_assessment3.getMedian(), 0);
		assertEquals(17, test_assessment3.getRange(), 0);
		assertEquals(5.24, test_assessment3.getStandardDeviation(), 1);

	}
}
