package com.example.storedemo2.service;

import com.example.storedemo2.dto.HumanDTO;
import com.example.storedemo2.objects.Human;
import com.example.storedemo2.objects.Inventory;
import com.example.storedemo2.objects.Store;
import com.example.storedemo2.projections.HumanProjection;
import com.example.storedemo2.projections.StoreProjection;
import com.example.storedemo2.repository.HumanRepository;
import com.example.storedemo2.repository.InventoryRepository;
import com.example.storedemo2.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    HumanRepository humanRepository;
    @Autowired
    InventoryRepository inventoryRepository;

    public List<StoreProjection> getList(String name){
        return storeRepository.getStoreList(name);
    }
    public ResponseEntity<?> buyItems(Long humanId,Long itemId,Integer amount) {
        //Buying includes person id, store id, and number of items
        //Check stock. If not enough, return error stock not enough.
        //Check human money. If not enough, return error not enough money.
        //Reduce stock by amount, default 1.
        //Calculate the cost
        //Update inventory, add x items to the human
        //Update human, increase energy and decrease money
        try {
            if (amount <= 0) {
                return ResponseEntity.ok("Amount must be more than 0!");
            }
            Human human = humanRepository.getById2(humanId);
            Store item = storeRepository.getByItemId(itemId);
            if (item.getStock() < amount) {
                return ResponseEntity.ok("Not enough stock! Only " + item.getStock() + " " + item.getName() + " left!");
            }
            Long cost = item.getCost() * amount;
            if (human.getMoney() < cost) {
                return ResponseEntity.ok("Not enough money! " + human.getName() + " has " + human.getMoney() + ". "
                        + amount + " " + item.getName() + " costs " + cost + ".");
            }
            item.setStock(item.getStock() - amount);
            human.setMoney(human.getMoney() - cost);
            human.setEnergy(human.getEnergy() + item.getEnergy());

            Inventory inventory = inventoryRepository.getByHumanAndItemId(humanId, itemId);
            if (inventory == null) {
                inventory = new Inventory(humanId, itemId);
            }
            inventory.setStock(inventory.getStock() + amount);

            storeRepository.save(item);
            human = humanRepository.save(human);
            inventory = inventoryRepository.save(inventory);

            HumanDTO response = new HumanDTO();
            response.human = humanRepository.getById(humanId);
            response.inventory = inventoryRepository.getByHumanId(humanId);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> restock(){
        storeRepository.restock();
        return ResponseEntity.ok("Restocked items.");
    }

}



