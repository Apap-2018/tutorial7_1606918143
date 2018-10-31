package com.apap.tutorial7.repository;

import com.apap.tutorial7.model.DealerModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerDb extends JpaRepository<DealerModel, Long> {

}
