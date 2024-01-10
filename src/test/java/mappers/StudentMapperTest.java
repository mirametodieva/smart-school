package mappers;

import com.example.smartschool.dto.StudentDto;
import com.example.smartschool.mappers.StudentMapper;
import com.example.smartschool.models.Student;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StudentMapperTest {
    private final StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

    @Test
    public void testConvertDtoToEntity() {
        StudentDto studentDto = new StudentDto("John", "Cena", 997, "787-563-4390");

        Long id = 123L;

        Student student = studentMapper.convertDtoToEntity(studentDto, id);

        assertEquals(studentDto.firstName(), student.getFirstName());
        assertEquals(studentDto.lastName(), student.getLastName());
        assertEquals(studentDto.studentNum(), student.getStudentNum());
        assertEquals(studentDto.parentPhone(), student.getParentPhone());
    }
}
