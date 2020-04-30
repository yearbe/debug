package app.z3_data_access_object.dao;

import java.util.List;

import app.z3_data_access_object.entity.Student;

public interface StudentDao {
    public List<Student> getAllStudents();

    public Student getStudent(int rollNo);

    public void updateStudent(Student student);

    public void deleteStudent(Student student);
}