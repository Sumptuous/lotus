package com.lotus.core.web;

import com.lotus.core.bean.BuyCart;
import com.lotus.core.bean.order.Order;
import com.lotus.core.service.order.OrderService;
import org.codehaus.jackson.map.ObjectMapper;
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

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message) {
        try {
            if (message.getJMSCorrelationID().equals("buyCart")){
                ObjectMessage objectMessage = (ObjectMessage) message;
                BuyCart buyCart = (BuyCart) objectMessage.getObject();
                orderService.addOrder(buyCart);
            }

            if (message.getJMSCorrelationID().equals("buyCartText")){
                TextMessage textMessage = (TextMessage) message;
                BuyCart buyCart = mapper.readValue(textMessage.getText(), BuyCart.class);
                orderService.addOrder(buyCart);
            }
            LOG.info("============ Consumer receive message successfully. ==========");
        } catch (Exception e) {
            LOG.error("Failed to receive message. " + e);
        }
    }
}
