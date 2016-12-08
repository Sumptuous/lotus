package com.lotus.core.web;

import com.lotus.core.bean.BuyCart;
import com.lotus.core.bean.order.Order;
import com.lotus.core.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by wangyangyang on 16/12/8.
 */
public class ConsumerMessageListener implements MessageListener {

    @Resource
    private OrderService orderService;

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerMessageListener.class);

    @Override
    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        if (message instanceof ObjectMessage){
            try {
                ObjectMessage objectMessage = (ObjectMessage) message;
                if (message.getJMSCorrelationID().equals("buyCart")){
                    BuyCart buyCart = (BuyCart) objectMessage.getObject();
                    orderService.addOrder(buyCart);
                }
                LOG.info("============ Consumer receive message successfully. ==========");
            } catch (JMSException e) {
                LOG.error("Failed to receive message. " + e.getMessage());
            }
        }
    }
}
