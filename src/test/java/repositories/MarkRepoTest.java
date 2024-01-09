package repositories;


import com.example.smartschool.SmartSchoolApplication;
import com.example.smartschool.models.Mark;
import com.example.smartschool.repositories.MarkRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
@ContextConfiguration(classes={SmartSchoolApplication.class})
@DataJpaTest
@Sql(
        scripts={"classpath:/resources/sql/mark_data.sql"}
)
public class MarkRepoTest {
    @Autowired
    private MarkRepo markRepo;

    @Test
    void testFindMarksByTeacherMarkSuccess() {
        Optional<Mark> result = markRepo.findById(Long.valueOf(111));
        assertThat(result.get().getValue())
                .isNotNull()
                .isEqualTo("A");
    }
}
