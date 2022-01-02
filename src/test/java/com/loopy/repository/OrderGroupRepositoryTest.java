package com.loopy.repository;

import com.loopy.ApplicationTests;
import com.loopy.model.entity.OrderGroup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends ApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){

        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("complete");
        orderGroup.setOrderType("all");
        orderGroup.setRevAddress("장안구");
        orderGroup.setRevName("seongkyulim");
        orderGroup.setPaymentType("card");
        orderGroup.setTotalPrice(BigDecimal.valueOf(100000));
        orderGroup.setTotalQuantity(5L);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now().plusDays(1));
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("admin");
        //orderGroup.setUserId(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        Assert.assertNotNull(newOrderGroup);

    }

}
