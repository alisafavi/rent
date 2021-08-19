package com.example.rent;

import com.example.rent.repository.UserRepository;
import com.example.rent.service.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableConfigurationProperties(FileStorageProperties.class)
public class Rent5Application {

    public static void main(String[] args) {
        SpringApplication.run(Rent5Application.class, args);

//        Timestamp time1 = Timestamp.valueOf(LocalDateTime.now());
//        Timestamp time2 = Timestamp.valueOf(LocalDateTime.now().plus(555, ChronoUnit.MINUTES));
//        if (time1.before(time2))
//            System.out.println("true");
//        else
//            System.out.println("false");

    }
}
