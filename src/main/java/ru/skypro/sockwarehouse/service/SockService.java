package ru.skypro.sockwarehouse.service;

import ru.skypro.sockwarehouse.dto.SockDto;
import ru.skypro.sockwarehouse.entity.Sock;

import java.util.List;

public interface SockService {

    void createSock(SockDto sockDto);

    List<Sock> getSocksByColorAndCottonPart(String color, int CottonPart);

    List<Sock> getSocks(String color);

    void issueSocks(List<Sock> socks, SockDto sockDto);

    String getSockByTitle(List<Sock> socks, String operation, int cottonPart);
}
