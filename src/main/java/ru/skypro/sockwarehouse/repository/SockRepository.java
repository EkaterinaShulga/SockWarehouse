package ru.skypro.sockwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.sockwarehouse.entity.Sock;

import java.util.List;
import java.util.Optional;

@Repository
public interface SockRepository extends JpaRepository<Sock, Integer> {

    Optional<List<Sock>> findByColorAndCottonPart(String color, int cottonPart);

    Optional<List<Sock>> findSocksByColor(String color);


}

