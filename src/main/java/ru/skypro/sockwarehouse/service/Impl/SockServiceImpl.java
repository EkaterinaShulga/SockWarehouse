package ru.skypro.sockwarehouse.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.sockwarehouse.dto.SockDto;
import ru.skypro.sockwarehouse.entity.Sock;


import ru.skypro.sockwarehouse.exception.SockNotFoundException;
import ru.skypro.sockwarehouse.mapper.SockMapper;
import ru.skypro.sockwarehouse.repository.SockRepository;
import ru.skypro.sockwarehouse.service.SockService;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SockServiceImpl implements SockService {

    private final SockRepository sockRepository;
    private final SockMapper sockMapper;

    /**
     * creating new sock  <br>
     * saves the  {@code sock} to the database  <br>
     * creates a number of socks equal sockDto.getQuantity
     *
     * @param sockDto - created sock
     */
    @Override
    public void createSock(SockDto sockDto) {
        log.info("createSock - SockServiceImpl");
        int count = sockDto.getQuantity();
        try {
            while (count > 0) {
                Sock sock = sockMapper.toSockDto(sockDto);
                sockRepository.save(sock);
                count--;
            }
        } catch (NullPointerException e) {
            log.info("count == null");
        }
    }

    /**
     * gets socks from the dataBase by color <br>
     *
     * @param color - color sock
     * @return List<Sock>
     */
    @Override
    public List<Sock> getSocks(String color) {
        log.info("getSocks - SockServiceImpl");
        return sockRepository.findByColor(color).
                orElseThrow(() -> new SockNotFoundException("Носки такого цвета  " + color + " не найдены!"));

    }

    /**
     * gets socks from the dataBase by color and cottonPart <br>
     *
     * @param color      - color sock
     * @param cottonPart - cottonPart sock
     * @return List<Sock>
     */
    @Override
    public List<Sock> getSocksByColorAndCottonPart(String color, int cottonPart) {
        log.info("getSocksByColorAndCottonPart - SockServiceImpl");
        return sockRepository.findByColorAndCottonPart(color, cottonPart)
                .orElseThrow(() -> new SockNotFoundException("Носки такого цвета  " + color
                        + "и состава" + cottonPart + " не найдены!"));
    }

    /**
     * delete socks from dataBase<br>
     * number of socks to remove equal sockDto.getQuantity
     *
     * @param socks   - List<Sock>
     * @param sockDto - SockDto
     */

    @Override
    public void issueSocks(List<Sock> socks, SockDto sockDto) {
        log.info("issueSocks - SockServiceImpl");
        int count = sockDto.getQuantity();
        int i = 0;
        List<Sock> result = new ArrayList<>(count);
        while (i < count) {
            result.add(socks.get(i));
            i++;
        }
        try {
            while (count > 0) {
                for (Sock sock : result) {
                    sockRepository.delete(sock);
                    count--;
                }
            }
        } catch (NullPointerException e) {
            log.info("count == null");
        }
    }

    /**
     * gets socks from the dataBase by color, cottonPart and operation <br>
     *
     * @param socks      - List<Sock> <br>
     * @param operation  - operator for comparing the value of the amount of cotton in socks <br>
     * @param cottonPart - cottonPart sock <br>
     * @return List<Sock>
     */
    @Override
    public String getSockByTitle(List<Sock> socks, String operation, int cottonPart) {
        log.info("getSockByTitle - SockServiceImpl");
        List<Sock> result = new ArrayList<>();
        try {
            switch (operation) {
                case "moreThan":
                    for (Sock sock : socks) {
                        if (sock.getCottonPart() > cottonPart) {
                            result.add(sock);
                        }
                    }
                case "lessThan":
                    for (Sock sock : socks) {
                        if (sock.getCottonPart() < cottonPart) {
                            result.add(sock);
                        }
                    }
                case "equal":
                    for (Sock sock : socks) {
                        if (sock.getCottonPart() == cottonPart) {
                            result.add(sock);
                        }
                    }
            }
            return "По заданным критериям запроса найдено " + result.size() + " пар носков";
        } catch (NullPointerException e) {
            return "По заданным критериям запроса носков нет";
        }
    }
}


