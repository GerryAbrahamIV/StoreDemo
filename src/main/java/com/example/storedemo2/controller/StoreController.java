package com.example.storedemo2.controller;

import com.example.storedemo2.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RequestMapping("/store")
@RestController
public class StoreController {
    @Autowired
    StoreService storeService;

    @GetMapping("/hi")
    public String sayHello(){
        return "store hi " + new Random().nextInt(9999);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(value = "name",required = false) String name){
        return ResponseEntity.ok(storeService.getList(name));
    }
    @PutMapping("/buy")
    public ResponseEntity<?> buyItems(
            @RequestParam(value = "human_id") Long humanId,
            @RequestParam(value = "item_id") Long itemId,
            @RequestParam(value = "amount") Integer amount){
        return storeService.buyItems(humanId,itemId,amount);
    }

    @GetMapping("/restock")
    public ResponseEntity<?> restock(){
        return storeService.restock();
    }
}
