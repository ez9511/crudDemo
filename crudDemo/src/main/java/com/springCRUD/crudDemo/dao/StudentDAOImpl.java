package com.springCRUD.crudDemo.dao;

import com.springCRUD.crudDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> queryResult = entityManager.createQuery(
                "FROM Student", Student.class);
        List<Student> list = queryResult.getResultList();
        return list;
    }

    @Override
    public List<Student> findByFirstName(String lastName) {
        TypedQuery<Student> queryResult = entityManager.createQuery(
                "FROM Student Where firstName =:theData1 order by lastName desc"
                , Student.class);
        queryResult.setParameter("theData1", lastName);
        List<Student> list = queryResult.getResultList();
        return list;
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public int updateByQuery() {
        int count = entityManager.createQuery("UPDATE Student SET email='test' WHERE firstName='Jun'").executeUpdate();
        return count;
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        Student student = findById(id);
        if (student != null) {
            entityManager.remove(student);
            System.out.println("student removed");
        } else {
            System.out.println("unable to delete since instance is null.");
        }

    }

    @Override
    @Transactional
    public int deleteAll() {
        return entityManager.createQuery("Delete From Student").executeUpdate();
    }
}
