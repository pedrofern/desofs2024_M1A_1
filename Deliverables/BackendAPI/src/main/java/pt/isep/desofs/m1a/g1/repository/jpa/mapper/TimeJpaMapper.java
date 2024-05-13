package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.logistics.Time;

@Mapper
public interface TimeJpaMapper {


    default Time map(String value) {
        return new Time(value);
    }

    default String map(Time time) {
        return time.getValue();
    }
}

