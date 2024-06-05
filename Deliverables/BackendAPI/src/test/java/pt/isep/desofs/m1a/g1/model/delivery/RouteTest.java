package pt.isep.desofs.m1a.g1.model.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

    @Test
    void testToString() {
        Route route = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        String expected = "Route(routeId=1, idDepartureWarehouse=1, idArrivalWarehouse=2, distance=10.0, time=5.0, energy=2.0, extraTime=1.0)";
        assertEquals(expected, route.toString());
    }

    @Test
    void getRouteId() {
        Route route = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        assertEquals(1L, route.getRouteId());
    }

    @Test
    void getIdDepartureWarehouse() {
        Route route = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        assertEquals(1L, route.getIdDepartureWarehouse());
    }

    @Test
    void getIdArrivalWarehouse() {
        Route route = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        assertEquals(2L, route.getIdArrivalWarehouse());
    }

    @Test
    void getDistance() {
        Route route = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        assertEquals(10.0, route.getDistance());
    }

    @Test
    void getTime() {
        Route route = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        assertEquals(5.0, route.getTime());
    }

    @Test
    void getEnergy() {
        Route route = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        assertEquals(2.0, route.getEnergy());
    }

    @Test
    void getExtraTime() {
        Route route = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        assertEquals(1.0, route.getExtraTime());
    }

    @Test
    void setRouteId() {
        Route route = new Route();
        route.setRouteId(1L);
        assertEquals(1L, route.getRouteId());
    }

    @Test
    void setIdDepartureWarehouse() {
        Route route = new Route();
        route.setIdDepartureWarehouse(1L);
        assertEquals(1L, route.getIdDepartureWarehouse());
    }

    @Test
    void setIdArrivalWarehouse() {
        Route route = new Route();
        route.setIdArrivalWarehouse(2L);
        assertEquals(2L, route.getIdArrivalWarehouse());
    }

    @Test
    void setDistance() {
        Route route = new Route();
        route.setDistance(10.0);
        assertEquals(10.0, route.getDistance());
    }

    @Test
    void setTime() {
        Route route = new Route();
        route.setTime(5.0);
        assertEquals(5.0, route.getTime());
    }

    @Test
    void setEnergy() {
        Route route = new Route();
        route.setEnergy(2.0);
        assertEquals(2.0, route.getEnergy());
    }

    @Test
    void setExtraTime() {
        Route route = new Route();
        route.setExtraTime(1.0);
        assertEquals(1.0, route.getExtraTime());
    }

    @Test
    void testEquals() {
        Route route1 = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        Route route2 = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        assertTrue(route1.equals(route2) && route2.equals(route1));
        assertEquals(route1.hashCode(), route2.hashCode());
    }

    @Test
    void testHashCode() {
        Route route = new Route(1L, 1L, 2L, 10.0, 5.0, 2.0, 1.0);
        int expectedHash = route.hashCode();
        assertEquals(expectedHash, route.hashCode());
    }
}
