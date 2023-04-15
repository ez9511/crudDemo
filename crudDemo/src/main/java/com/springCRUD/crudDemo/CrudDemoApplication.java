package com.springCRUD.crudDemo;

import com.springCRUD.crudDemo.dao.StudentDAO;
import com.springCRUD.crudDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			try {
				//createStudent(studentDAO);
				createMultipleStudent(studentDAO);
				//findStudentById(studentDAO, 5000);
				//findByQuery(studentDAO);
				//updateOneStudent(studentDAO, 5000);
				//studentDAO.updateByQuery();
				//deleteOneStudent(studentDAO, 5000);
				//deleteAllStudent(studentDAO);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		};
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		Student one = new Student("John", "Wick", "johnwick@gmail.com");
		Student two = new Student("Kujo", "Jotaro", "jojo@mail.com");
		Student three = new Student("Sett", "LOL", "sett@mail.com");
		studentDAO.save(one);
		studentDAO.save(two);
		studentDAO.save(three);
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		int count = studentDAO.deleteAll();
		System.out.println(count + " student deleted");
	}

	private void deleteOneStudent(StudentDAO studentDAO, int id) {
		studentDAO.deleteStudent(id);
		findStudentById(studentDAO, id);
	}

	private void updateOneStudent(StudentDAO studentDAO, int id) {
		Student student = studentDAO.findById(id);
		student.setEmail("junwei@gmail.com");
		studentDAO.updateStudent(student);
		findStudentById(studentDAO, id);
	}

	private void findByQuery(StudentDAO studentDAO) {
		List<Student> list = studentDAO.findByFirstName("Jun");
		for (Student s : list) {
			System.out.println(s.toString());
		}
	}

	private void createStudent(StudentDAO studentDAO) {
		Student temp = new Student("Jun", "Wei", "junwei@gmail.com");
		System.out.println("Student created: " + temp.toString());
		studentDAO.save(temp);
		System.out.println("Student saved: " + temp.toString());
		System.out.println("Generated id: " + temp.getId());
	}

	private void findStudentById(StudentDAO studentDAO, int id) {
		Student temp = studentDAO.findById(id);
		if (temp != null) {
			System.out.println("Student found:\n" + temp.toString());
		} else {
			System.out.println("Student not found by id: " + id);
		}
	}

}
