package com.prominent.newjobportal.service;

import com.prominent.newjobportal.model.Recruiter;
import com.prominent.newjobportal.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterServiceImple implements RecruiterService{
    @Autowired
    private RecruiterRepository recruiterRepository;
    public void setRecruiterRepository(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    public RecruiterServiceImple(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    @Override
    public void saveRecruiter(Recruiter recruiter) {
        this.recruiterRepository.save(recruiter);
    }

    @Override
    public Recruiter getRecordById(int id) {
        Optional<Recruiter> optional=recruiterRepository.findById(id);
        Recruiter recruiter=null;
        if(optional.isPresent()){
            recruiter=optional.get();
        }else {
            throw new RuntimeException("Recruiter not found for id - "+id);
        }
        return recruiter;
     }

    @Override
    public void deleteRecordById(int id) {
        this.recruiterRepository.deleteById(id);

    }
}
