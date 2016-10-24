package com.lotus.core.sys.task;

import com.lotus.core.bean.enums.Flag;
import com.lotus.core.bean.order.Order;
import com.lotus.core.query.order.OrderQuery;
import com.lotus.core.service.order.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 取消过期未付款的订单
 * @author wyy
 */
@Service
public class CancelOrderJob implements Job{

    @Autowired
    private OrderService orderService;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setIsPay(1);
        orderQuery.setIsValid(Flag.Y.getValue());
        final List<Order> orders = orderService.getOrderList(orderQuery);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Queue<Order> queue = new ConcurrentLinkedQueue<Order>();
        queue.addAll(orders);

        try {
            for (int i=0; i<10; i++){
                executorService.submit(new Runnable() {
                    Order order = null;
                    public void run() {
                        while ((order = queue.poll()) != null){
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MINUTE, -1);
                            Date expireTime = calendar.getTime();
                            if (expireTime.after(order.getCreateDate())){
                                System.out.println(order);
                                Order dbOrder = new Order();
                                dbOrder.setIsValid(Flag.N.getValue());
                                dbOrder.setId(order.getId());
                                orderService.updateOrderByKey(dbOrder);
                            }
                        }
                    }
                });
            }
            executorService.shutdown();

            while (!executorService.isTerminated()) {
                TimeUnit.SECONDS.sleep(1);
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
