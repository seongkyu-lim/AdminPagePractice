package com.loopy.repository;

import com.loopy.ApplicationTests;
import com.loopy.model.entity.OrderDetail;
import org.apache.tomcat.jni.Local;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends ApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("waiting");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(4);
        orderDetail.setPrice(BigDecimal.valueOf(10000));
        orderDetail.setCreatedBy("adminServer");
        orderDetail.setCreatedAt(LocalDateTime.now());
        // orderDetail.setItemId(1L);
        // orderDetail.setOrderGroupId(1L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrderDetail);

    }
}
