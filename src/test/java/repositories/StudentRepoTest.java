package repositories;

import com.example.smartschool.models.Grade;
import com.example.smartschool.models.Student;
import com.example.smartschool.repositories.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class StudentRepoTest {
    @Autowired
    private StudentRepo studentRepo;

    @Test
    public void testFindStudentByStudentNum() {
        int studentNum = 717;
        Student student = new Student();
        student.setStudentNum(studentNum);
        studentRepo.save(student);

        Optional<Student> foundStudent = studentRepo.findStudentByStudentNum(studentNum);

        assertTrue(foundStudent.isPresent());
        assertEquals(studentNum, foundStudent.get().getStudentNum());
    }

    @Test
    public void testFindStudentsByGradeStudent() {
        Grade grade = new Grade();
        Student student1 = new Student();
        Student student2 = new Student();
        student1.setGradeStudent(grade);
        student2.setGradeStudent(grade);
        studentRepo.saveAll(List.of(student1, student2));

        List<Student> students = studentRepo.findStudentsByGradeStudent(grade);

        assertEquals(2, students.size());
        assertTrue(students.stream().allMatch(s -> s.getGradeStudent().equals(grade)));
    }

    @Test
    @DirtiesContext
    public void testUpdateGrade() {
        Grade newGrade = new Grade();
        Student student = new Student();
        studentRepo.save(student);

        studentRepo.updateGrade(newGrade, student.getId());

        Student updatedStudent = studentRepo.findById(student.getId()).orElse(null);
        assertEquals(newGrade, updatedStudent.getGradeStudent());
    }
}
