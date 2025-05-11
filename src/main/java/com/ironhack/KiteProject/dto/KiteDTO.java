package com.ironhack.KiteProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KiteDTO {

    private String shape;

    private String lineType;

    private int windRequired;

    private String username;

    private String location;



}
