package ru.skypro.sockwarehouse.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.sockwarehouse.dto.SockDto;
import ru.skypro.sockwarehouse.entity.Sock;
import ru.skypro.sockwarehouse.service.SockService;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socks")
public class SockController {

    private final SockService service;

    @Operation(
            summary = "createSock",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "400", content = @Content()),
                    @ApiResponse(responseCode = "500", content = @Content())
            }
    )
    @PostMapping("/income")
    public ResponseEntity<HttpStatus> createSock(@RequestBody SockDto sockDto) {
        log.info("method - createSock - Controller");
        if (sockDto.getQuantity() <= 0 || sockDto.getColor().isEmpty() || sockDto.getCottonPart() <= 0 || sockDto.getCottonPart() > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        service.createSock(sockDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "issueSock",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "400", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content()),
                    @ApiResponse(responseCode = "500", content = @Content())
            }
    )
    @PostMapping("/outcome")
    public ResponseEntity<HttpStatus> issueSock(@RequestBody SockDto sockDto) {
        log.info("method - issueSock - Controller");
        List<Sock> socks = service.getSocksByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart());
        if (socks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        } else if (socks.size() < sockDto.getQuantity()) {
            log.info("we don't have the right number of socks in stock");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.issueSocks(socks, sockDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "getSockByTitle",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "400", content = @Content()),
                    @ApiResponse(responseCode = "500", content = @Content())
            }
    )
    @GetMapping
    public ResponseEntity<String> getSockByTitle(@RequestParam(value = "color") String color,
                                                 @RequestParam(value = "operation") String operation,
                                                 @RequestParam(value = "cottonPart") int cottonPart) {
        log.info("method - getSockByTitle - Controller");

        List<Sock> socks = service.getSocks(color);
        if (socks.isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(service.getSocksByTitle(socks, operation, cottonPart));
    }
}
