package ru.skypro.sockwarehouse.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skypro.sockwarehouse.dto.SockDto;
import ru.skypro.sockwarehouse.entity.Sock;

/**
 * converts {@code SockDto(DTO)} in {@code Sock(Entity)}  <br>
 *
 * @see Sock
 * @see SockDto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SockMapper {

    Sock toSockDto(SockDto sockDto);
}
