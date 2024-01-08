package com.example.storedemo2.repository;

import com.example.storedemo2.objects.Human;
import com.example.storedemo2.projections.HumanProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanRepository extends CrudRepository<Human, Long> {
    @Query("SELECT h FROM Human h WHERE h.id = :id ")
    HumanProjection getById(@Param("id") Long id);

    @Query("SELECT h FROM Human h WHERE h.id = :id ")
    Human getById2(@Param("id") Long id);

    @Query("SELECT h FROM Human h")
    List<HumanProjection> getAllHuman();

    @Query("SELECT h FROM Human h " +
    "WHERE :name IS NULL OR h.name LIKE CONCAT('%',:name,'%')  ")
    List<HumanProjection> getHumanList(@Param("name") String name);
}
