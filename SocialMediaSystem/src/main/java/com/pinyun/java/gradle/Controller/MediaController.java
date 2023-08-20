package com.pinyun.java.gradle.Controller;
import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class MediaController {

    @GetMapping("/welcome")
    public  String welcome() {
        return "Hello Word";
    }
}