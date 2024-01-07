package com.example.storedemo2.repository;

import com.example.storedemo2.objects.Jobs;
import com.example.storedemo2.objects.Store;
import com.example.storedemo2.projections.JobProjection;
import com.example.storedemo2.projections.StoreProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Jobs, Long> {
    @Query("SELECT j FROM Jobs j WHERE j.id = :id ")
    Jobs getById(@Param("id") Long id);
    @Query("SELECT j FROM Jobs j " +
    "WHERE :name IS NULL OR j.name LIKE CONCAT('%',:name,'%')  ")
    List<JobProjection> getJobsList(@Param("name") String name);
}
