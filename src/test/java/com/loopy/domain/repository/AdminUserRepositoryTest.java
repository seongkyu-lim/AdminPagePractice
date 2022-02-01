package com.loopy.domain.repository;

import com.loopy.ApplicationTests;
import com.loopy.domain.entity.AdminUser;
import com.loopy.domain.repository.AdminUserRepository;
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

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        Assert.assertNotNull(newAdminUser);

    }
}
