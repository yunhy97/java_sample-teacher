package com.sample.school.service;

import com.sample.school.repository.CourseRepository;
import com.sample.school.repository.ProfessorRepository;
import com.sample.school.repository.RegistrationRepository;
import com.sample.school.repository.StudentRepository;
import com.sample.school.repository.SubjectRepository;
import com.sample.school.vo.Course;
import com.sample.school.vo.Professor;
import com.sample.school.vo.Registration;
import com.sample.school.vo.Student;
import com.sample.school.vo.Subject;
import com.sample.school.repository.StudentRepository;

public class SchoolServiceImple implements SchoolService {

	private CourseRepository courseRepo = new CourseRepository();
	private ProfessorRepository professorRepo = new ProfessorRepository();
	private RegistrationRepository registrationRepo = new RegistrationRepository();
	private SubjectRepository subjectRepo = new SubjectRepository();
	private StudentRepository studentRepo = new StudentRepository();
	
	@Override
	public void addNewProfessor(Professor professor) {
		//이메일이 동일한 교수정보는 등록될 수 없다.
		Professor[] professors = professorRepo.getAllProfessor();
		
		boolean isExist = false;
		for (Professor p : professors) {
			if (p.getEmail().equals(professor.getEmail())) {
				isExist = true;
				break;
			}
		}
		
		if (isExist) {
			System.out.println("동일한 이메일을 가진 교수가 이미 등록되어 있습니다.");
		} else {
			professorRepo.insertProfessor(professor);
		}
		
	}
	@Override
	public void addNewSubject(Subject subject) {
		//과목명이 동일한 과목정보는 등록될 수 없다.
		Subject[] subjects = subjectRepo.getAllSubject();
		
		boolean isExist = false;
		for(Subject s : subjects) {
			if(s.getTitle().equals(subject.getTitle())){
				isExist = true;
				break;
			}
		}
		if(isExist) {
			System.out.println("동일한 과목명을 가진 과목정보가 이미 등록되어 있습니다.");
		} else {
			subjectRepo.insertSubject(subject);
		}
	}
	
	@Override
	public void displaySubjectProfessor(int professorNo) {
		//그 교수가 소속된 학과의 과목만 화면에 출력한다.
		Professor professor = professorRepo.getProfessorByNo(professorNo);
		
		if(professor == null) {
			System.out.println("교수정보를 찾지 못했습니다.");
			return;
		}
		
		Subject[] subject = subjectRepo.getAllSubject();
		for(int i=0; i<subject.length; i++) {
			if(subject[i] == null) {
				System.out.println("과목정보를 찾지 못했습니다.");
				return;
			} 
			if(subject[i].getDept().equals(professor.getDept())) {
				System.out.println("과목번호: " + subject[i].getNo());
				System.out.println("과 목 명: " + subject[i].getTitle());
				System.out.println("-----------------------------");
			}
		}
	}
	
	@Override
	public void insertCourse(Course course) {
		// 그 교수가 소속된 학과에서 개설된 과목에 대해서만 신규 과정을 등록할 수 있다.
		Professor professor = professorRepo.getProfessorByNo(course.getProfessorNo());
		Subject subject = subjectRepo.getSubjectByNo(course.getSubjectNo());
		
		if(subject == null || professor == null) {
			System.out.println("정보를 찾지 못했습니다.");
			return;
		}
		if(professor.getDept().equals(subject.getDept())) {
			courseRepo.insertCourse(course);
		}
	}
	
	@Override
	public void displayCourse(int professorNo) {
		// 그 교수가 개설한 과정만 화면에 출력한다.
		Professor professor = professorRepo.getProfessorByNo(professorNo);
	
		if(professor == null) {
			System.out.println("교수정보를 찾지 못했습니다.");
			return;
		}
		
		Course[] courses = courseRepo.getAllCourse();
		for(int i=0; i<courses.length; i++) {
			if(courses[i] == null) {
				System.out.println("과정정보를 찾지 못했습니다.");
				return;
			}
			if(courses[i].getProfessorNo() == professor.getNo()) {
				System.out.println("과목번호\t과정명\t과목번호\t담당 교수번호\t과정 신청 정원");
				System.out.print(courses[i].getNo()+"\t");
				System.out.print(courses[i].getName()+"\t");
				System.out.print(courses[i].getSubjectNo()+"\t");
				System.out.print(courses[i].getProfessorNo()+"\t");
				System.out.println(courses[i].getQuota());
				System.out.println("-----------------------------");
			}
		}
	}
	
	@Override
	public void displayStudentRegistration(int professorNo, int courseNo) {
		// 개설과정의 담당교수와 입력한 교수번호가 일치하는 경우만 해당 과정 신청자를 조회해서 화면에 출력한다.
		//교수번호로 교수정보를 조회한다.
		//개설과정번호에 해당하는 개설과정정보를 조회한다.
		//모든 수강신청정보를 조회한다.
		//조회된 모든 수강신청정보에서 신청정보의 개설과정번호와 전달받은 개설번호가 일치하고, 담당교수번호와 전달받은 교수번호가 일치하는 수강신청정보의 학생번호를 조회한다
		//조회된 학생번호에 해당하는 학생정보를 조회해서 화면의 학생이름, 학과, 학년을 출력한다.
//		Course course = courseRepo.getCourseByNo(professorNo);
//		Registration regist = registrationRepo.getRegistrationByNo(courseNo);
//		
//		if(course == null) {
//			
//		}
//		Professor[] professors = professorRepo.getAllProfessor();
//		
//		for(int i=0; i<professors.length; i++) {
//			if(professors[i] == null) {
//				return;
//			}
//			if(professors[i].getNo() == course.getProfessorNo()) {
//				System.out.println("신청 학생번호\t개설과정 번호\t취소여부\t과정성적");
//				System.out.print(regist.getStudentNo()+"\t");
//			}
//		}
		
	}
	
	@Override
	public void scoreRegistration(Registration resgistration) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addNewStudent(Student student) {
		studentRepo.insertStudent(student);	
	}
	@Override
	public void displayStudent(int studentNo) {
		// 학생번호에 해당하는 학생정보를 조회한다.
		//학생정보에서 학과를 조회한다.
		//모든 과목정보를 조회한다.
		//조회된 모든 과목에서 학생의 학과와 일치하는 학과를 가진 과목만 출력한다.
		Student student = studentRepo.getStudentByNo(studentNo);
		System.out.println("과목번호\t과정명\t\t\t학과\t\t학점");
		if(student == null) {
			System.out.println("학생정보를 찾지 못했습니다.");
			return;
		}
		Subject[] subject = subjectRepo.getAllSubject();
		for(int i=0; i<subject.length; i++) {
			if(subject[i] == null) {
				System.out.println("과목정보를 찾지 못했습니다.");
				return;
			}
			if(subject[i].getDept().equals(student.getDept())) {
				
				System.out.print(subject[i].getNo()+"\t");
				System.out.print(subject[i].getTitle()+"\t\t");
				System.out.print(subject[i].getDept()+"\t  ");
				System.out.println(subject[i].getCredit());
				
			}
		}	
		System.out.println("-----------------------------");
	}
	
	@Override
	public void displayStudentCourse(int studentNo) {
		// 학생번호에 해당하는 학생정보를 조회한다.
		//학생의 학과명를 조회한다.
		//모든 개설과정 정보를 조회한다.
		//조회된 모든 개설과정의 각 과목번호를 조회한다. 
		//각 과목에 해당하는 과목정보를 조회한다. 
		//과목정보의 학과와 학생의 학과가 일치하는 경우 개설과정 정보를 출력한다.
		Student student = studentRepo.getStudentByNo(studentNo);
		System.out.println("과목번호\t과정명\t\t과목번호\t담당 교수번호\t과정 신청 정원");
		if(student == null) {
			System.out.println("학생정보를 찾지 못했습니다.");
			return;
		}
		
		Course[] courses = courseRepo.getAllCourse();
		for(int i=0; i<courses.length; i++) {
			if(courses[i] == null) {
				System.out.println("과정정보를 찾지 못했습니다.");
				return;
			}
			Subject subject = subjectRepo.getSubjectByNo(courses[i].getSubjectNo());
			if(subject.getDept().equals(student.getDept())) {
				System.out.print(courses[i].getNo()+"\t");
				System.out.print(courses[i].getName()+"\t");
				System.out.print(courses[i].getSubjectNo()+"\t");
				System.out.print(courses[i].getProfessorNo()+"\t\t");
				System.out.println(courses[i].getQuota());
				
			}
		}
		System.out.println("-----------------------------------------------");
	}
	
	@Override
	public void settingRegistration(int studentNo, int courseNo) {
		Student student = studentRepo.getStudentByNo(studentNo);
		Course course = courseRepo.getCourseByNo(courseNo);
		
		if(student == null || course == null) {
			System.out.println("학생정보와 과정정보를 찾지 못했습니다.");
			return;
		}
		Subject subject = subjectRepo.getSubjectByNo(course.getSubjectNo());
		if(subject.getDept().equals(student.getDept())) {
			Registration regist = new Registration();
			regist.setStudentNo(studentNo);
			regist.setCourseNo(courseNo);
			
			registrationRepo.insertRegistration(regist);
		}
	}
	@Override
	public void displayNowRegistration(int studentNo) {
		// 모든 수강신청정보를 조회한다.
		//조회된 모든 수강신청정보의 학생번호와 전달받은 학생번호를 비교해서 일치하면 
		//수강신청번호, 개설과정명, 취소여부를 출력한다
		
		Registration[] regist = registrationRepo.getAllRegistrationis();
		
		System.out.println("수강신청번호\t개설과정명\t\t취소여부");
		for(int i=0; i<regist.length; i++) {
			Registration registration = regist[i];
			
			Course course = courseRepo.getCourseByNo(registration.getCourseNo()); 
			if(studentNo == registration.getStudentNo()) {
				
				System.out.print(regist[i].getNo()+"\t\t");
				System.out.print(course.getName()+"\t  ");
				System.out.println(regist[i].isCancel());
				
			}
		}
		System.out.println("---------------------------------");
	}
	
	@Override
	public void cancelRegistration(int studentNo, int no) {
		// 수강신청번호에 해당하는 수강신청정보를 조회한다.
		// 조회된 수강신청정보의 학생번호와 전달받은 학생번호가 일치하면 취소여부를 true로 설정한다.
		Registration regist = registrationRepo.getRegistrationByNo(no);
		
		if(studentNo == regist.getStudentNo()) {
			regist.setCancel(true);
		}
	}
	
	@Override
	public void displayscoreRegistration(int studentNo) {
		// 모든 수강신청정보를 조회한다.
		//조회된 모든 수강신청정보에서 각각의 학생번호와 전달받은 학생번호가 일치하는 경우
		//수강신청정보의 개설과정번호로 개설과정정보를 조회한다.
		Registration[] regist = registrationRepo.getAllRegistrationis();
		System.out.println("개설과정번호   신청학생번호   신청과정번호   취소여부   과정성적");
		for(int i=0; i<regist.length; i++) {
			if(studentNo == regist[i].getStudentNo()) {
				Registration re =registrationRepo.getRegistrationByNo(regist[i].getNo());
				System.out.print(re.getNo()+ "\t  ");
				System.out.print(re.getStudentNo()+ "\t    ");
				System.out.print(re.getCourseNo()+ "\t");
				System.out.print(re.isCancel()+ "\t ");
				System.out.println(re.getScore());
			}
		}
	}
}
