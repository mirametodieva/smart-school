package repositories;

import com.example.smartschool.models.Subject;
import com.example.smartschool.models.Teacher;
import com.example.smartschool.repositories.TeacherRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TeacherRepoTest {
    @Autowired
    private TeacherRepo teacherRepo;

    @Test
    public void testFindTeacherByTeacherNum() {
        int teacherNum = 117;
        Teacher teacher = new Teacher();
        teacher.setTeacherNum(teacherNum);
        teacherRepo.save(teacher);

        Optional<Teacher> foundTeacher = teacherRepo.findTeacherByTeacherNum(teacherNum);

        assertTrue(foundTeacher.isPresent());
        assertEquals(teacherNum, foundTeacher.get().getTeacherNum());
    }

    @Test
    public void testFindTeachersBySubjectTeacher() {
        Subject subject = new Subject();
        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();
        teacher1.setSubjectTeacher(subject);
        teacher2.setSubjectTeacher(subject);
        teacherRepo.saveAll(List.of(teacher1, teacher2));

        List<Teacher> teachers = teacherRepo.findTeachersBySubjectTeacher(subject);

        assertEquals(2, teachers.size());
        assertTrue(teachers.stream().allMatch(t -> t.getSubjectTeacher().equals(subject)));
    }

    @Test
    @DirtiesContext
    public void testUpdateSubject() {
        Subject newSubject = new Subject();
        int teacherNum = 118;
        Teacher teacher = new Teacher();
        teacher.setTeacherNum(teacherNum);
        teacherRepo.save(teacher);

        teacherRepo.updateSubject(newSubject, teacherNum);

        Teacher updatedTeacher = teacherRepo.findTeacherByTeacherNum(teacherNum).orElse(null);
        assertEquals(newSubject, updatedTeacher.getSubjectTeacher());
    }
}
