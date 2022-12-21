import edu.zut.PaymentApplication;
import edu.zut.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Author: roydon - 2022/12/21
 **/
@SpringBootTest(classes = PaymentApplication.class)
public class OrderTests {

    @Resource
    private OrderMapper orderMapper;

    @Test
    void OrderTests() {
        orderMapper.updateState(1024260, 1, "2022-12-21 17:15:48", "2022122122001474080501720529");
    }
}
