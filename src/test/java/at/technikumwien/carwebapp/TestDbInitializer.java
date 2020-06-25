package at.technikumwien.carwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Profile("test")
public class TestDbInitializer {
    @Autowired
    private CarRepository carRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationEvent(){
        carRepository.saveAll(
                List.of(
                        new Car(
                                null,
                                "My Car",
                                "Bmw",
                                LocalDate.of(1990, 1, 1)
                        ),
                        new Car(
                                null,
                                "My Car2",
                                "Mini",
                                LocalDate.of(1995, 1, 1)
                        )
                )
        );
    }
}
