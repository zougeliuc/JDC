package com.example.jdc.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Goods implements Serializable {

  private static final long serialVersionUID = 8147491332409442517L;
  private Integer gid;
  private String url;
  private BigDecimal price;
  private String title;
  private String shop;
  private String type;

}
