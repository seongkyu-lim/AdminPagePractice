package com.loopy.sample;

import com.loopy.ApplicationTests;
import com.loopy.domain.entity.Category;
import com.loopy.domain.entity.Partner;
import com.loopy.domain.repository.CategoryRepository;
import com.loopy.domain.repository.PartnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
public class PartnerSample extends ApplicationTests {

    private Random random;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void sampleCreate(){
        random = new Random();
        List<Category> categoryList = categoryRepository.findAll();

        for(int i = 0; i < categoryList.size(); i++){
            Category category = categoryList.get(i);

            for(int j = 1; j < 10; j++){

                // 가입 상태 랜덤
                int div = (random.nextInt(10)+1) % 2;
                String status = (div == 0 ? "REGISTERED" : "UNREGISTERED");

                Partner partner = Partner.builder()
                        .category(category) // category 객체가 SQL문으로 생성항 category_id컬럼에 id를 자동으로 채움. (sql문으로 생성 시, 따로 외래키 설정을 안했는데 어떻게 가능하지?
                        .name(category.getTitle()+j+" 호점")
                        .status(status)
                        .address("서울시 강남구 "+j+"번길"+random.nextInt(100)+1+"호")
                        .callCenter("070-"+String.format("%04d", random.nextInt(100)+1)+"-"+String.format("%04d", random.nextInt(100)+1))
                        .partnerNumber("010-1111-"+String.format("%04d", i))
                        .businessNumber((random.nextInt(999999999)+1)+""+j)
                        .ceoName(j+" 대표")
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals("UNREGISTERED") ? getRandomDate() : null )
                        .build();

                //log.info("{}",partner);
                partnerRepository.save(partner);
            }
        }
    }


    private LocalDateTime getRandomDate(){
        return LocalDateTime.of(2019,getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11)+1;
    }
}
