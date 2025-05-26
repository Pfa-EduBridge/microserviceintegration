package tn.esprit.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleApplication.class, args);
    }
    @Autowired
    private ModuleRepository moduleRepository;

    @Bean
    ApplicationRunner init(){
        return (args) -> {
            //save
            moduleRepository.save(new Module("React"));
            moduleRepository.save(new Module("Angular"));
            moduleRepository.save(new Module("Nestjs"));
            //fetch
            moduleRepository.findAll().forEach(System.out::println);
        };
    }

}
