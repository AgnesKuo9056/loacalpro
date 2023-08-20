package com.pinyun.java.gradle;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class SocialMediaApplication {
    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private static final String PROFILE = "";

    private static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap();
        defProperties.put(SPRING_PROFILE_DEFAULT, PROFILE);
        app.setDefaultProperties(defProperties);
    }

    private static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        return profiles.length == 0 ? env.getDefaultProfiles() : profiles;
    }

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SocialMediaApplication.class);
        addDefaultProfile(app);

        Environment env = app.run(args).getEnvironment();
        String applicationName = env.getProperty("spring.application.name");
        String serverPort = env.getProperty("server.port");
        String configServerStatus = env.getProperty("spring.profiles.active");

        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();

            log.info("\n---------------------------------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://localhost:{}\n\t" +
                            "External: \thttp://{}:{}\n---------------------------------------------------------------------------------",
                    applicationName,
                    serverPort,
                    hostAddress, serverPort);

            log.info("\n---------------------------------------------------------------------------------\n\t" +
                            "Config Server: \t{}\n---------------------------------------------------------------------------------",
                    configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
