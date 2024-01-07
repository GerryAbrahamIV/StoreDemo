package com.example.storedemo2.repository;

import com.example.storedemo2.objects.Human;
import com.example.storedemo2.objects.Store;
import com.example.storedemo2.projections.HumanProjection;
import com.example.storedemo2.projections.StoreProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
    @Query("SELECT s FROM Store s WHERE s.id = :id ")
    Store getByItemId(@Param("id") Long id);
    @Query("SELECT s FROM Store s")
    List<StoreProjection> getAllItems();
    @Query("SELECT s FROM Store s " +
            "WHERE :name IS NULL OR s.name LIKE CONCAT('%',:name,'%')  ")
    List<StoreProjection> getStoreList(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE store s SET s.stock = 30",nativeQuery = true)
    void restock();
}
