package com.matatu.matatu_booking.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.matatu.matatu_booking.entity.Matatu;
import com.matatu.matatu_booking.entity.Route;
import com.matatu.matatu_booking.repository.MatatuRepository;
import com.matatu.matatu_booking.repository.RouteRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(MatatuRepository matatuRepository, RouteRepository routeRepository) {
        return args -> {
            // Clear existing data (optional)
            matatuRepository.deleteAll();
            routeRepository.deleteAll();

            // Create Matatus
            Matatu matatu1 = new Matatu("KCA 123A", 14);
            Matatu matatu2 = new Matatu("KBZ 456B", 14);
            Matatu matatu3 = new Matatu("KCB 789C", 14);

            matatuRepository.save(matatu1);
            matatuRepository.save(matatu2);
            matatuRepository.save(matatu3);

            // Create Routes
            Route route1 = new Route("Nairobi", "Mombasa", 1500.00);
            Route route2 = new Route("Nairobi", "Kisumu", 1200.00);
            Route route3 = new Route("Nairobi", "Nakuru", 800.00);
            Route route4 = new Route("Mombasa", "Nairobi", 1500.00);
            Route route5 = new Route("Nairobi", "Eldoret", 1000.00);
            Route route6 = new Route("Kisumu", "Nairobi", 1200.00);
            Route route7 = new Route("Nairobi", "Malindi", 2000.00);
            Route route8 = new Route("Kisumu", "Mombasa", 1800.00);
            Route route9 = new Route("Nairobi", "Nyeri", 900.00);
            Route route10 = new Route("Eldoret", "Nairobi", 1000.00);
            Route route11 = new Route("Nairobi", "Garissa", 3000.00);
            Route route12 = new Route("Kisumu", "Nyeri", 1600.00);
            Route route13 = new Route("Nairobi", "Meru", 1300.00);
            Route route14 = new Route("Malindi", "Nairobi", 2000.00);
            Route route15 = new Route("Nakuru", "Kisumu", 1100.00);
            Route route16 = new Route("Lamu", "Mombasa", 2500.00);
            Route route17 = new Route("Nairobi", "Thika", 700.00);
            Route route18 = new Route("Eldoret", "Kisumu", 1200.00);
            Route route19 = new Route("Nairobi", "Nanyuki", 1400.00);
            Route route20 = new Route("Mombasa", "Malindi", 800.00);

            // Save Routes to Repository
            routeRepository.save(route1);
            routeRepository.save(route2);
            routeRepository.save(route3);
            routeRepository.save(route4);
            routeRepository.save(route5);
            routeRepository.save(route6);
            routeRepository.save(route7);
            routeRepository.save(route8);
            routeRepository.save(route9);
            routeRepository.save(route10);
            routeRepository.save(route11);
            routeRepository.save(route12);
            routeRepository.save(route13);
            routeRepository.save(route14);
            routeRepository.save(route15);
            routeRepository.save(route16);
            routeRepository.save(route17);
            routeRepository.save(route18);
            routeRepository.save(route19);
            routeRepository.save(route20);

            System.out.println("✅ Database initialized with sample data!");
            System.out.println("📊 Matatus: " + matatuRepository.count());
            System.out.println("🛣️ Routes: " + routeRepository.count());
        };
    }
}