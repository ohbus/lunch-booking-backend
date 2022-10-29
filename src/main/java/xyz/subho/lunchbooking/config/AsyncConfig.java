package xyz.subho.lunchbooking.config;

import java.util.concurrent.Executor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
@Data
@AllArgsConstructor
public class AsyncConfig {

  private int corePoolSize;
  private int maxPoolSize;
  private int queueCapacity;
  private String threadNamePrefix;

  public AsyncConfig() {
    this.corePoolSize = 5000;
    this.maxPoolSize = 50000000;
    this.queueCapacity = 50000000;
    this.threadNamePrefix = "lunch-booking-async-thread";
  }

  @Bean(name = "taskExecutor")
  public Executor taskExecutor() {

    final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    executor.setCorePoolSize(this.corePoolSize);
    executor.setMaxPoolSize(this.maxPoolSize);
    executor.setQueueCapacity(this.queueCapacity);
    executor.setThreadNamePrefix(this.threadNamePrefix);
    executor.initialize();

    return executor;
  }
}
