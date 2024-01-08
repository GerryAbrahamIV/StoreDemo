package com.example.storedemo2.controller;

import com.example.storedemo2.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.Random;

@RequestMapping("/human")
@RestController
public class HumanController {
    @Autowired
    HumanService humanService;

    @GetMapping("/hi")
    public String sayHello(){
        return "human hi " + new Random().nextInt(9999);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(value = "name",required = false) String name){
        return ResponseEntity.ok(humanService.getList(name));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) throws NoSuchObjectException {
        return humanService.getDetail(id);
    }
    @PutMapping("/use")
    public ResponseEntity<?> useItem(
            @RequestParam(value = "human_id") Long humanId,
            @RequestParam(value = "item_id") Long itemId,
            @RequestParam(value = "amount") Integer amount
    ){
        return humanService.useItem(humanId,itemId,amount);
    }
}
