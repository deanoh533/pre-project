package com.stackoverflow.member.controller;

//import org.apache.coyote.Response;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    //@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @GetMapping
    public String test(){
//        String hello="{\n" +
//                "\t\"username\":\"dokun\",\n" +
//                "        \"email\":\"dokun@gg.gg\"\n" +
//                "}";

        return  "hello";

    }
}
