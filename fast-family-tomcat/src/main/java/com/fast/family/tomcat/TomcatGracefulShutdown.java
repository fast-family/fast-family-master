package com.fast.family.tomcat;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TomcatGracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {


    private volatile Connector connector;

    private final TomcatGracefulShutdownProperties tomcatGracefulShutdownProperties;

    public TomcatGracefulShutdown(TomcatGracefulShutdownProperties tomcatGracefulShutdownProperties) {
        this.tomcatGracefulShutdownProperties = tomcatGracefulShutdownProperties;
    }

    @Override
    public void customize(Connector connector) {
        if (connector == null) {
            log.info("We are running unit test");
            return;
        }
        final Executor executor = connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            log.info("executor is ThreadPoolExecutor");
            final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
            if (threadPoolExecutor.isTerminated()) {
                log.info("thread pool executor has terminated");
            } else {
                LocalDateTime startShutdown = LocalDateTime.now();
                LocalDateTime stopShutdown = LocalDateTime.now();

                try {
                    threadPoolExecutor.shutdown();
                    if (!threadPoolExecutor
                            .awaitTermination(tomcatGracefulShutdownProperties.getWaitTime(), TimeUnit.SECONDS)) {
                        log.warn("Tomcat thread pool did not shut down gracefully within"
                                + tomcatGracefulShutdownProperties.getWaitTime() + " second(s). Proceeding with force shutdown");
                        threadPoolExecutor.shutdownNow();
                    } else {
                        log.info("Tomcat thread pool is empty,we stop now");
                    }
                } catch (Exception e) {
                    log.error("The await termination has been interrupted : " + e.getMessage());
                    Thread.currentThread().interrupt();
                    ;
                } finally {
                    final long seconds = Duration.between(startShutdown, stopShutdown).getSeconds();
                    log.info("Shutdown performed in " + seconds + " second(s)");
                }

            }
        }

    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

    }
}
