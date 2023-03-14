package ru.skypro.sockwarehouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.sockwarehouse.dto.SockDto;
import ru.skypro.sockwarehouse.entity.Sock;
import ru.skypro.sockwarehouse.mapper.SockMapper;
import ru.skypro.sockwarehouse.mapper.SockMapperImpl;
import ru.skypro.sockwarehouse.repository.SockRepository;
import ru.skypro.sockwarehouse.service.Impl.SockServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SockServiceImplTest {

    @Mock
    private SockRepository sockRepository;

    @Spy
    private SockMapper sockMapper = new SockMapperImpl();

    @InjectMocks
    SockServiceImpl sockService;

    private Sock sock;
    private SockDto sockDto;

    @BeforeEach
    void init() {

        sock = new Sock();
        sock.setId(1);
        sock.setCottonPart(50);
        sock.setColor("red");

        sockDto = new SockDto();
        sockDto.setId(1);
        sockDto.setCottonPart(50);
        sockDto.setColor("red");
        sockDto.setQuantity(1);

    }

    @Test
    void createSockTest() {
        when(sockRepository.save(any())).thenReturn(sock);
        sockService.createSock(sockDto);
    }

    @Test
    void getSocksTest() {
        List<Sock> sockList = new ArrayList<>();
        sockList.add(sock);
        when(sockRepository.findSocksByColor(anyString())).thenReturn(Optional.of(sockList));

        assertNotNull(sockService.getSocks(sockDto.getColor()));
    }

    @Test
    void getSocksByColorAndCottonPart() {
        List<Sock> sockList = new ArrayList<>();
        sockList.add(sock);
        when(sockRepository.findByColorAndCottonPart(anyString(), anyInt())).thenReturn(Optional.of(sockList));
        assertNotNull(sockService.getSocksByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart()));
    }

    @Test
    void issueSocks() {
        List<Sock> sockList = new ArrayList<>();
        sockList.add(sock);
        sockService.issueSocks(sockList, sockDto);

        verify(sockRepository, atMostOnce()).delete(sock);
    }

    @Test
    void getSockByTitle() {
        List<Sock> sockList = new ArrayList<>();
        sockList.add(sock);
        String result = sockService.getSocksByTitle(sockList, "equal", 50);

        assertNotNull(result);

    }


}


