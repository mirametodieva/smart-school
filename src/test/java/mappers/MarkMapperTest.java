package mappers;

import com.example.smartschool.dto.MarkDto;
import com.example.smartschool.mappers.MarkMapper;
import com.example.smartschool.models.Mark;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkMapperTest {

    private final MarkMapper underTest = Mappers.getMapper(MarkMapper.class);

    @ParameterizedTest
    @MethodSource("paramProvider")
    void convertDtoToEntityTest(MarkDto dto, String[] emptyFields) {
        Mark result = underTest.convertDtoToEntity(dto, 1L);

        assertThat(result)
                .isNotNull()
                .hasNoNullFieldsOrPropertiesExcept("studentMark", "teacherMark");

    }

    private static Stream<Arguments> paramProvider() {
        return Stream.of(
                Arguments.of(
                        new MarkDto("A", "P.E"),
                        new String[]{"subjectName"}
                ),
                Arguments.of(
                        new MarkDto("F", "Math"),
                        new String[]{"value"}
                )
        );
    }
}