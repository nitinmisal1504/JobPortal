package com.prominent.newjobportal.repository;

import com.prominent.newjobportal.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter,Integer> {
}
