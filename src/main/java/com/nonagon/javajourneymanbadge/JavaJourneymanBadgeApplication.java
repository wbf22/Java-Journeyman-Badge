package com.nonagon.javajourneymanbadge;

import com.nonagon.javajourneymanbadge.clarice.ClariceBrain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class JavaJourneymanBadgeApplication {

    public static boolean applicationOn;




    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(JavaJourneymanBadgeApplication.class, args);
        applicationOn = true;
        ClariceBrain brain = applicationContext.getBean(ClariceBrain.class);
        brain.run();

    }

}
