package com.example.jdc.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Cart {
    private Integer cid;
    private Integer uid;
    private Integer gid;
    private Integer cnt;
    private BigDecimal price;

    // LocalDate.pulsDays(50)

    private LocalDateTime opttime;
}
