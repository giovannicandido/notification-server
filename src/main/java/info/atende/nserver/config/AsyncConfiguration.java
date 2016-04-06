package info.atende.nserver.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * Configura o uso de methods asynchronous
 * @author Giovanni Silva
 */
@Configuration
@EnableAsync
public class AsyncConfiguration extends AsyncConfigurationProperties implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(getCorePoolSize());
        taskExecutor.setMaxPoolSize(getMaxPoolSize());
        taskExecutor.setQueueCapacity(getQueueCapacity());
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandlerImpl();
    }
}
