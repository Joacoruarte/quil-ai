package redrock.rhino.quilai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.vectorstore.pgvector.autoconfigure.PgVectorStoreAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
        PgVectorStoreAutoConfiguration.class
})
class QuiLiaApplicationTests {

    @Test
    void contextLoads() {
    }

}
