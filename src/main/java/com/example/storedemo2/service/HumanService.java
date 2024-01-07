package com.example.storedemo2.service;

import com.example.storedemo2.dto.HumanDTO;
import com.example.storedemo2.objects.Human;
import com.example.storedemo2.objects.Inventory;
import com.example.storedemo2.objects.Store;
import com.example.storedemo2.projections.HumanProjection;
import com.example.storedemo2.projections.InventoryProjection;
import com.example.storedemo2.repository.HumanRepository;
import com.example.storedemo2.repository.InventoryRepository;
import com.example.storedemo2.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Service
public class HumanService {
    @Autowired
    HumanRepository humanRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    StoreRepository storeRepository;

    public List<HumanProjection> getList(String name){
        return humanRepository.getHumanList(name);
    }
    public ResponseEntity<?> getDetail(Long id) throws NoSuchObjectException {
        HumanProjection proj = humanRepository.getById(id);
        if(proj == null || proj.getName() == null){
            throw new NoSuchObjectException("No humans with that ID!");
        }
        List<InventoryProjection> inv = inventoryRepository.getByHumanId(id);

        HumanDTO response = new HumanDTO();
        response.human = proj;
        response.inventory = inv;
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> useItem(Long humanId, Long itemId, Integer amount){
        //Replenish energy with items.
        //Get human + item + inventory
        //If inventory == null or stock isnt enough, return error
        //Update inventory stock - amount
        //Update human energy + energy * amount
        try {
            if (amount <= 0) {
                return ResponseEntity.ok("Amount must be more than 0!");
            }
            Human human = humanRepository.getById2(humanId);
            Store item = storeRepository.getByItemId(itemId);
            Inventory inventory = inventoryRepository.getByHumanAndItemId(humanId, itemId);
            if (inventory.getStock() < amount) {
                return ResponseEntity.ok("Not enough items! Only " + inventory.getStock() + " " + item.getName() + " left!");
            }
            inventory.setStock(inventory.getStock() - amount);

            Long energyRestored = item.getEnergy() * amount;
            human.setEnergy(human.getEnergy() + energyRestored);

            humanRepository.save(human);
            inventoryRepository.save(inventory);

            HumanDTO response = new HumanDTO();
            response.human = humanRepository.getById(humanId);
            response.inventory = inventoryRepository.getByHumanId(humanId);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }

    }
}
