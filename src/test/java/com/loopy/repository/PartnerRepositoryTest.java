package com.loopy.repository;

import com.loopy.ApplicationTests;
import com.loopy.model.entity.Partner;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Part;
import java.time.LocalDateTime;

public class PartnerRepositoryTest extends ApplicationTests{

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create(){

        String name = "partner01";
        String status = "registered";
        String address = "관악구";
        String callCenter = "010-2323-2323";
        String partnerNumber = "010-2321-2312";
        String buisnessNumber = "031-213-1234";
        String ceoName = "jason";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "admin";
        Long categoryId = 1L;

        Partner partner = new Partner();

        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(buisnessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
        partner.setCategoryId(categoryId);

        Partner newPartner = partnerRepository.save(partner);

        Assert.assertNotNull(newPartner);
        Assert.assertEquals(newPartner.getName(), name);
    }
}
