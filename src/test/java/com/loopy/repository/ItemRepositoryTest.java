package com.loopy.repository;

import com.loopy.ApplicationTests;
import com.loopy.model.entity.Item;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends ApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){

        Item item = new Item();

        item.setName("노트북");
        item.setPrice(1000L);
        item.setContent("애플");

        Item newItem = itemRepository.save(item);

        Assert.assertNotNull(newItem);

    }

    public void read(){

        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assert.assertTrue(item.isPresent());


    }


}
