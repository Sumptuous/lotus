package com.lotus.core.web;

import javax.jms.Destination;
import javax.jms.ObjectMessage;

/**
 * Created by wangyangyang on 16/12/8.
 */
public interface ProducerService {

    /**
     * 发送消息
     * @param destination
     * @param obj
     */
    void sendMessage(Destination destination, final Object obj, final String id);
}
