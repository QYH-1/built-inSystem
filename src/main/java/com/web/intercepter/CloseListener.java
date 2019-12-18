package com.web.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/12/12 10:11
 * @describe 监听结束
 */
public class CloseListener implements ApplicationListener<ContextClosedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        logger.info("程序停止--");
    }
}
