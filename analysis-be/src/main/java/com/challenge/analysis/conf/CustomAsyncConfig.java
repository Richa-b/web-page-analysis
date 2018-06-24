package com.challenge.analysis.conf;


import com.challenge.analysis.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class CustomAsyncConfig {
    private final Logger log = LoggerFactory.getLogger(CustomAsyncConfig.class);
    private static String ASYNC_LOG_PREFIX = "Async_Log";

    private static final String threadPrefixName = ASYNC_LOG_PREFIX;

    @Bean(name = "asyncExecutor")
    public TaskExecutor workExecutor() {
        log.info("-> ******* asyncExecutor ********");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix(threadPrefixName);
        threadPoolTaskExecutor.setCorePoolSize(ConfigUtil.CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(ConfigUtil.MAX_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(ConfigUtil.MAX_QUEUE_CAPACITY);
        threadPoolTaskExecutor.afterPropertiesSet();
        return threadPoolTaskExecutor;
    }
}
