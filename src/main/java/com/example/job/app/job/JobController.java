package com.example.job.app.job;

import com.example.job.app.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private  JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll()
    {
        return ResponseEntity.ok(jobService.findAll());
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        try {
            jobService.createJob(job);
            return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add job", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job=jobService.getJobById(id);
        if(job!=null)
            return new ResponseEntity<>(job,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted=jobService.deleteJobById(id);
        if(deleted)
        {
         return new ResponseEntity<>("job deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<String>updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
        boolean updated=jobService.updateJob(id,updatedJob);
        if(updated)
        {
            return  new ResponseEntity<>("Job updated successfulyy",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
