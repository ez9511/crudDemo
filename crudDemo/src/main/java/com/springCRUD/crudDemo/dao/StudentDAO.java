package com.springCRUD.crudDemo.dao;

import com.springCRUD.crudDemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    public void save(Student student);

    public Student findById(Integer id);

    public List<Student> findAll();

    public List<Student> findByFirstName(String lastName);

    public void updateStudent(Student student);

    public int updateByQuery();

    public void deleteStudent(int id);

    public int deleteAll();
}
