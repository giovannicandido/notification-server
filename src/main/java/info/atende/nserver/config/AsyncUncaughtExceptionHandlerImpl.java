package info.atende.nserver.config;

import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * Quando uma exceção é lançada em um method asynchronous
 * @author Giovanni Silva
 */
public class AsyncUncaughtExceptionHandlerImpl implements AsyncUncaughtExceptionHandler {
    Logger logger = Logger.getLogger(AsyncUncaughtExceptionHandlerImpl.class);
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        logger.error(ex.getMessage(), ex);
    }
}