package ru.skypro.sockwarehouse.dto;


import lombok.Data;

/**
 * SockDto - dto, schema : <br>
 * id - id sockDto  <br>
 * color - color SockDto <br>
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
