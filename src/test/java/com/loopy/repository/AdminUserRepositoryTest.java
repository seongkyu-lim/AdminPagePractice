package com.loopy.repository;

import com.loopy.ApplicationTests;
import com.loopy.model.entity.AdminUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends ApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();

        adminUser.setAccount("adminuser01");
        adminUser.setPassword("12341234");
        adminUser.setStatus("registered" );
        adminUser.setRole("partner");
        adminUser.setRegisteredAt(LocalDateTime.now());
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setCreatedBy("admin");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        Assert.assertNotNull(newAdminUser);

    }
}
