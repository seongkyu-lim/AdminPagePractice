package com.loopy.domain.repository;

import com.loopy.ApplicationTests;
import com.loopy.domain.entity.Category;
import com.loopy.domain.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends ApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create(){

        String type = "computer";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "Admin";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(), type);
        Assert.assertEquals(newCategory.getTitle(), title);



    }

    @Test
    public void read(){
        Optional<Category> optionalCategory = categoryRepository.findByType("computer");

        optionalCategory.ifPresent(c -> {
            Assert.assertEquals(c.getType(), "computer");
            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });

    }
}
