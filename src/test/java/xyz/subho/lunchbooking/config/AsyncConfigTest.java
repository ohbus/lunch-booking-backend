package xyz.subho.lunchbooking.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AsyncConfig.class})
@ExtendWith(SpringExtension.class)
class AsyncConfigTest {
    @Autowired
    private AsyncConfig asyncConfig;

    /**
     * Method under test: {@link AsyncConfig#taskExecutor()}
     */
    @Test
    void testTaskExecutor() {
        assertTrue(asyncConfig.taskExecutor() instanceof ThreadPoolTaskExecutor);
    }
}

