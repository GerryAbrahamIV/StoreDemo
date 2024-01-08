package com.example.storedemo2.controller;

import com.example.storedemo2.service.JobService;
import com.example.storedemo2.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RequestMapping("/job")
@RestController
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping("/hi")
    public String sayHello(){
        return "job hi " + new Random().nextInt(9999);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(value = "name",required = false) String name){
        return ResponseEntity.ok(jobService.getList(name));
    }
    @PutMapping("/work")
    public ResponseEntity<?> work(
            @RequestParam(value = "human_id",required = true) Long humanId,
            @RequestParam(value = "job_id",required = true) Long jobId){
        return jobService.work(humanId,jobId);
    }
}
