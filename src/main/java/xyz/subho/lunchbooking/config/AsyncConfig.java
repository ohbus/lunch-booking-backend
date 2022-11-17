/*
 * Lunch Booking - Lunch Booking REST Application
 * Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
