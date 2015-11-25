package com.maxcheung.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxcheung.domain.Customer;
import com.maxcheung.domain.JobCommand;


@Repository
public interface JobCommandRepository extends JpaRepository<JobCommand, Long> {
}
