package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
public class TestMain {
    public static void main(String[] args) {
        SpringApplication.run(TestMain.class);
        System.out.println("Application Started");
    }


    @RestController
    class Service {

        @RequestMapping(method = RequestMethod.GET, path="/ping")
        public String ping(){
            return "Success"+new Date();
        }
    }

}
