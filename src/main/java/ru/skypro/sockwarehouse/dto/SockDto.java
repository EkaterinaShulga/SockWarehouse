package ru.skypro.sockwarehouse.dto;


import lombok.Data;

/**
 * SockDto - dto <br>
 * schema : <br>
 * id - id dto  <br>
 * color - color dto <br>
 * cottonPart - cotton part of sock <br>
 * quantity - quantity socksDto to add dataBase<br>
 */
@Data
public class SockDto {
    private int id;
    private String color;
    private int cottonPart;
    private Integer quantity;


}
