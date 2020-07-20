package com.sample.school;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;

import com.sample.school.service.SchoolService;
import com.sample.school.service.SchoolServiceImple;
import com.sample.school.vo.Course;
import com.sample.school.vo.Professor;
import com.sample.school.vo.Registration;
import com.sample.school.vo.Student;
import com.sample.school.vo.Subject;
import com.sample.school.vo.User;

public class SchoolApp {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	private static int nextInt() {
		try {
			String text = in.readLine();
			return Integer.parseInt(text);
		} catch (IOException e) {
			return 0;
		}
	}
	
	private static String next() {
		try {
			return in.readLine();
		} catch (IOException e) {
			return "";
		}
	}
	
	public static void main(String[] args) {
		SchoolService service = new SchoolServiceImple();
		
		while (true) {
			System.out.println("#####  #####");
			System.out.println("===========================================");
			System.out.println("1.교수 2.학생  0.종료");
			System.out.println("===========================================");
			
			System.out.print("메뉴를 선택하세요: ");
			int menuNo = nextInt();
			
			if(menuNo == 1) {
				System.out.println("===========================================================================");
				System.out.println("1.교수등록  2.과목등록  3.과목조회  4.개설과정등록  5.개설과정 조회  6.과정 신청자 조회  7.성적입력");
				System.out.println("===========================================================================");
				
				System.out.print("<교수>의 메뉴를 선택하세요: ");
				int menuNo2 = nextInt();
				
				if(menuNo2 == 1) {
					Professor professor = new Professor();
					System.out.println("[신규교수 등록]");
					System.out.print("이  름 : ");
					professor.setName(next());
					System.out.print("이메일 : ");
					professor.setEmail(next());
					System.out.print("학  과 : ");
					professor.setDept(next());
					System.out.print("직  위 : ");
					professor.setPosition(next());
					System.out.print("급  여 : ");
					professor.setSalary(nextInt());
					
					service.addNewProfessor(professor);
					
				}else if(menuNo2 == 2) {
					Subject subject = new Subject();
					System.out.println("[신규과목 등록]");
					
					System.out.print("과목명 : ");
					subject.setTitle(next());
					System.out.print("학  과 : ");
					subject.setDept(next());
					System.out.print("학  점 : ");
					subject.setCredit(nextInt());
					
					service.addNewSubject(subject);
					
				}else if(menuNo2 == 3) {
					System.out.println("[과목조회]");
					System.out.print("교수번호 : ");
					
					service.displaySubjectProfessor(nextInt());
					
				}else if(menuNo2 == 4) {
					Course course = new Course();
					System.out.println("[신규 개설과정 등록]");
					System.out.print("과 정 명 : ");
					course.setName(next());
					System.out.print("과목번호 : ");
					course.setSubjectNo(nextInt());
					System.out.print("담당교수번호 : ");
					course.setProfessorNo(nextInt());
					System.out.print("정    원 : ");
					course.setQuota(nextInt());
					
					service.insertCourse(course);
					
				}else if(menuNo2 == 5) {
					System.out.println("[개설과정 조회]");
					System.out.print("담당교수번호 : ");
					service.displayCourse(nextInt());
					//일단 여기까지
				}else if(menuNo2 == 6) {
					
					System.out.println("[개설과정 신청자 조회]");
					System.out.print("교수번호 : ");
					int professorNo = nextInt();
					System.out.print("개설과정번호 : ");
					int courseNo = nextInt();
					
					service.displayStudentRegistration(professorNo, courseNo);
				
				}else if(menuNo2 == 7) {
					System.out.println("[성적입력]");
					System.out.print("교수번호 : ");
					int no = nextInt();
					System.out.print("수강신청번호 : ");
					int courseNo = nextInt();
					System.out.print("성    적 : ");
					int score = nextInt();
					
				}
			}else if(menuNo == 2) {
				
				System.out.println("===========================================================================");
				System.out.println("1.학생등록  2.과목조회  3.개설과정 조회  4.수강신청  5.수강신청 현황 조회  6.수강신청 취소  7.성적조회");
				System.out.println("===========================================================================");
				
				System.out.print("<학생>의 메뉴를 선택하세요: ");
				int menuNo3 = nextInt();
				
				if(menuNo3 == 1) {
					Student student = new Student();
					System.out.println("[신규학생등록]");
					System.out.print("이  름 : ");
					student.setName(next());
					System.out.print("이메일 : ");
					student.setEmail(next());
					System.out.print("학  과 : ");
					student.setDept(next());
					System.out.print("학  년 : ");
					student.setGrade(nextInt());
					service.addNewStudent(student);
					System.out.println("###등록되었습니다.");
				}else if(menuNo3 == 2) {
					
					System.out.println("[과목조회]");
					System.out.print("학생번호 : ");
					service.displayStudent(nextInt());
					System.out.println("###조회되었습니다.");
				}else if(menuNo3 == 3) {
					System.out.println("[개설과정 조회]");
					System.out.print("학생번호 : ");
					service.displayStudentCourse(nextInt());
					System.out.println("###조회되었습니다.");
				}else if(menuNo3 == 4) {
					System.out.println("[수강신청]");
					System.out.print("학생번호 : ");
					int studentNo =nextInt();
					System.out.print("개설과정번호 : ");
					int courseNo = nextInt();
					
					service.settingRegistration(studentNo, courseNo);
					System.out.println("###신청되었습니다.");
				}else if(menuNo3 == 5) {
					System.out.println("[수강신청 현황 조회]");
					System.out.print("학생번호 : ");
					service.displayNowRegistration(nextInt());
					System.out.println("###조회되었습니다.");
				}else if(menuNo3 == 6) {
					System.out.println("[수강신청 취소]");
					System.out.print("학생번호 : ");
					int studentNo = nextInt();
					System.out.print("수강신청번호 : ");
					int registrationNo = nextInt();
					System.out.println("###취소되었습니다.");
					service.cancelRegistration(studentNo, registrationNo);
				}else if(menuNo3 == 7) {
					System.out.println("[성적조회]");
					System.out.print("학생번호 : ");
					service.displayscoreRegistration(nextInt());
					System.out.println("###조회되었습니다.");
				}
			}else if(menuNo == 0) {
				
			}
		}
		
	}
	
}
