package com.example.storedemo2.service;

import com.example.storedemo2.objects.Human;
import com.example.storedemo2.objects.Jobs;
import com.example.storedemo2.projections.JobProjection;
import com.example.storedemo2.repository.HumanRepository;
import com.example.storedemo2.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    HumanRepository humanRepository;
    @Autowired
    JobRepository jobRepository;

    public List<JobProjection> getList(String name){
        return jobRepository.getJobsList(name);
    }
    public ResponseEntity<?> work(Long humanId,Long jobId) {
        //Working to get money.
        //Get job detail
        //Get human and check human energy
        //If energy is less than job energy, return error
        //Otherwise, update human's energy and money.
        //Decrease energy by job energy and increase money by wage.
        try {
            Human human = humanRepository.getById2(humanId);
            Jobs job = jobRepository.getById(jobId);
            if (human.getEnergy() < job.getEnergy()) {
                return ResponseEntity.ok("Not enough energy! " + human.getName() + " has " + human.getEnergy() + " energy. "
                        + job.getName() + " requires " + job.getEnergy() + " energy!");
            } else {
                human.setMoney(human.getMoney() + job.getWage());
                human.setEnergy(human.getEnergy() - job.getEnergy());
            }
            human = humanRepository.save(human);

            return ResponseEntity.ok(human);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}



