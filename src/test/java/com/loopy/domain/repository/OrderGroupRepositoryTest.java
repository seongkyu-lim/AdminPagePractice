package com.loopy.domain.repository;

import com.loopy.ApplicationTests;
import com.loopy.domain.entity.OrderGroup;
import com.loopy.domain.enumclass.OrderGroupOrderType;
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
        orderGroup.setOrderType(OrderGroupOrderType.EACH);
        orderGroup.setRevAddress("장안구");
        orderGroup.setRevName("seongkyulim");
        orderGroup.setPaymentType("card");
        orderGroup.setTotalPrice(BigDecimal.valueOf(100000));
        orderGroup.setTotalQuantity(5);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now().plusDays(1));
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("admin");
        //orderGroup.setUserId(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        Assert.assertNotNull(newOrderGroup);

    }

}
