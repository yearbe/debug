package com.debug.play.bean;

import org.springframework.context.Lifecycle;
import org.springframework.context.Phased;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lyb
 * @since 2020-02-20
 */
//@Configuration
public class TestLife implements Lifecycle, Phased {
    /**
     * AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
     * // 这里会调用liftCycle的start和stop方法
     * context.start();
     * System.out.println(context.isRunning());
     * context.stop();
     * {@link Lifecycle} 接口会在application context调用对应的start, stop等方法调用
     */
    private boolean running = false;

    @Override
    public void start() {
        System.out.println("Spring start.");
        running = true;
    }

    @Override
    public void stop() {
        System.out.println("Spring stop.");
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        // phase值小的application context优先启动。
        return 0;
    }
}
