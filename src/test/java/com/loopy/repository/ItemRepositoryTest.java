package com.loopy.repository;

import com.loopy.ApplicationTests;
import com.loopy.model.entity.Item;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends ApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){

        Item item = new Item();

        item.setStatus("UNREGISTERED");
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setPrice(BigDecimal.valueOf(1000000));
        item.setContent("2019년형 이에요.");
        item.setBrandName("samsung");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("partner01");
        // item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);

        Assert.assertNotNull(newItem);

    }

    public void read(){

        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assert.assertTrue(item.isPresent());


    }


}
