package mappers;

import com.example.smartschool.dto.TeacherDto;
import com.example.smartschool.mappers.TeacherMapper;
import com.example.smartschool.models.Teacher;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherMapperTest {
    private final TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);

    @Test
    public void testConvertDtoToEntity() {
        TeacherDto teacherDto = new TeacherDto("Lisa", "Kudrow", 667,"bachelor degree at Yonsei University");

        Long id = 13457L;

        Teacher teacher = teacherMapper.convertDtoToEntity(teacherDto, id);

        assertEquals(teacherDto.firstName(), teacher.getFirstName());
        assertEquals(teacherDto.lastName(), teacher.getLastName());
        assertEquals(teacherDto.teacherNum(), teacher.getTeacherNum());
        assertEquals(teacherDto.education(), teacher.getEducation());
    }
}
