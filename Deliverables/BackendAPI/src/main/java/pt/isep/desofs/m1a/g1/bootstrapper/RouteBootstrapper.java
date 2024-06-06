package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pt.isep.desofs.m1a.g1.model.delivery.Route;
import pt.isep.desofs.m1a.g1.repository.RouteRepository;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("bootstrap")
@Order(4)
public class RouteBootstrapper implements CommandLineRunner {

    @Autowired
    private RouteRepository repo;

    @Override
    public void run(String... args) throws Exception {

        if (repo.count() == 0) {
            List<Route> routes = Arrays.asList(
                    new Route(1L, 1L, 2L, 120.0, 2.0, 50.0, 0.5),
                    new Route(2L, 1L, 2L, 140.0, 2.2, 55.0, 0.6),
                    new Route(3L, 1L, 2L, 160.0, 2.4, 60.0, 0.7),
                    new Route(4L, 1L, 2L, 180.0, 2.6, 65.0, 0.8),
                    new Route(5L, 1L, 2L, 200.0, 2.8, 70.0, 0.9),
                    new Route(6L, 2L, 1L, 220.0, 3.0, 75.0, 1.0),
                    new Route(7L, 2L, 1L, 240.0, 3.2, 80.0, 1.1),
                    new Route(8L, 2L, 1L, 260.0, 3.4, 85.0, 1.2),
                    new Route(9L, 2L, 1L, 280.0, 3.6, 90.0, 1.3),
                    new Route(10L, 2L, 1L, 300.0, 3.8, 95.0, 1.4)
            );

            for (Route route : routes) {
                repo.save(route);
            }
        }
    }
}
