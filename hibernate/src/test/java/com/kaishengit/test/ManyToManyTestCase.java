package com.kaishengit.test;

import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.Teacher;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ManyToManyTestCase {

    /*
        最佳实践：
        1. 保存时让一方放弃关系维护
     */

    @Test
    public void testSave() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher teacher1 = new Teacher();
        teacher1.setTeaname("T1");

        Teacher teacher2 = new Teacher();
        teacher2.setTeaname("T2");

        Student student1 = new Student();
        student1.setStuname("S1");

        Student student2 = new Student();
        student2.setStuname("S2");

        Set<Teacher> teacherSet = new HashSet<>();
        teacherSet.add(teacher1);
        teacherSet.add(teacher2);

        student1.setTeacherSet(teacherSet);
        student2.setTeacherSet(teacherSet);

        /*Set<Student> studentSet = new HashSet<>();
        studentSet.add(student1);
        studentSet.add(student2);

        teacher1.setStudentSet(studentSet);
        teacher2.setStudentSet(studentSet);*/


        session.save(student1);
        session.save(student2);

        session.save(teacher1);
        session.save(teacher2);


        session.getTransaction().commit();
    }

    @Test
    public void testFindTeacher() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher teacher = (Teacher) session.get(Teacher.class,25);
        System.out.println(teacher.getTeaname());

        Set<Student> studentSet = teacher.getStudentSet();
        for (Student student : studentSet) {
            System.out.println(student.getId() + " : " + student.getStuname());
        }



        session.getTransaction().commit();
    }

}
