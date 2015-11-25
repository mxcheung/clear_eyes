package com.maxcheung.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxcheung.domain.JobCommand;
import com.maxcheung.domain.JobCommandQueue;


@Repository
public interface JobCommandQueueRepository extends JpaRepository<JobCommandQueue, Long> {
}
