package com.ming.happyclub.common.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * @description: 异常日志提醒工具类
 * @date 2023/11/21
 * @version 1.0
 * @Replenishment: 使用邮箱推送Error日志信息
 */
public class AlarmUtil extends AppenderBase<ILoggingEvent> {
    private static final long INTERVAL = (long) 10 * 1000 * 60;
    private long lastAlarmTime = 0;

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        if (canAlarm()) {
            EmailUtil.sendMail(iLoggingEvent.getLoggerName(),
                    SpringUtil.getConfig("alarm.user", "xhhuiblog@163.com"),
                    iLoggingEvent.getFormattedMessage());
        }
    }

    private boolean canAlarm() {
        // 做一个简单的频率过滤,一分钟内只允许发送一条报警
        long now = System.currentTimeMillis();
        if (now - lastAlarmTime >= INTERVAL) {
            lastAlarmTime = now;
            return true;
        } else {
            return false;
        }
    }
}
