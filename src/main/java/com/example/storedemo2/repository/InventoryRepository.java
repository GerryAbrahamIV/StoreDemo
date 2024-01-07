package com.example.storedemo2.repository;

import com.example.storedemo2.objects.Inventory;
import com.example.storedemo2.objects.Store;
import com.example.storedemo2.projections.InventoryProjection;
import com.example.storedemo2.projections.StoreProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    @Query("SELECT i FROM Inventory i WHERE i.id = :id ")
    Inventory getById(@Param("id") Long id);
    @Query("SELECT i FROM Inventory i WHERE i.itemId = :item_id AND i.humanId = :human_id")
    Inventory getByHumanAndItemId(@Param("human_id") Long humanId,@Param("item_id") Long itemId);
    @Query("SELECT s.name AS itemName, i.stock AS stock, s.energy AS energy FROM Inventory i" +
            " JOIN Store s ON i.itemId = s.id " +
            " WHERE i.humanId = :human_id ")
    List<InventoryProjection> getByHumanId(@Param("human_id") Long humanId);


}
