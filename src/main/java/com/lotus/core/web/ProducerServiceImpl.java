package com.lotus.core.web;

import javax.annotation.Resource;
import javax.jms.*;


import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by wangyangyang on 16/12/8.
 */
@Service
public class ProducerServiceImpl implements ProducerService {

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final Object obj, final String id) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage((Serializable) obj);
                objectMessage.setJMSCorrelationID(id);
                return objectMessage;
            }
        });
    }
}
