package com.loopy.repository;

import com.loopy.ApplicationTests;
import com.loopy.model.entity.OrderDetail;
import org.apache.tomcat.jni.Local;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends ApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrdered_at(LocalDateTime.now());
        orderDetail.setItemId(1L);
        orderDetail.setUserId(1L);

    }
}
