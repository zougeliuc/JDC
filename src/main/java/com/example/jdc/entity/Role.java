package com.example.jdc.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 3174588516627517764L;
    private Integer rid;
    private String rname;

    private List<Permission> permissions;
}
