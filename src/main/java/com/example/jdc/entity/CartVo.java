package com.example.jdc.entity;

import lombok.Data;

@Data
public class CartVo extends Cart{
    private String title;
    private String url;
    private String type;
    private String shop;
}
