package com.loopy.domain.repository;

import com.loopy.domain.entity.Category;
import com.loopy.domain.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    List<Partner> findByCategory(Category category);
}
