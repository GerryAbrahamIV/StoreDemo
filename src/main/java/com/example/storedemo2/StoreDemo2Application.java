package com.example.storedemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@RestController
public class StoreDemo2Application {

    public static void main(String[] args) {
        System.out.println("ready");
        SpringApplication.run(StoreDemo2Application.class, args);
        System.out.println("go");
    }


    @GetMapping("/hi")
    public String sayHello(){
        return "hi " + new Random().nextInt(9999);
    }
}
