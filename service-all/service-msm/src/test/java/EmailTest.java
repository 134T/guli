import com.msm.MsmApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/10 20:04
 * @Version: 11
 */
@SpringBootTest(classes = MsmApplication.class)
public class EmailTest {

    @Value("${spring.mail.username}")
    private String from;
    @Test
    public void testValue(){
        System.out.println(from);
    }
}
