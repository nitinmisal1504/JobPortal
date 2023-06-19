package com.prominent.newjobportal.service;

import com.prominent.newjobportal.model.Recruiter;

import java.util.List;

public interface RecruiterService {
    public List<Recruiter> getAllRecruiters();

    public void saveRecruiter(Recruiter recruiter);

    public Recruiter getRecordById(int id);

    public void deleteRecordById(int id);
}
