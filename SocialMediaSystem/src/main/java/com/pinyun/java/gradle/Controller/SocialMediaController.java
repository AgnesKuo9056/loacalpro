package com.pinyun.java.gradle.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocialMediaController {

    @GetMapping("/")
    public String home() {
        return "post"; // Assuming 'post' is the name of your Vue template (without the '.vue' extension)
    }

}
