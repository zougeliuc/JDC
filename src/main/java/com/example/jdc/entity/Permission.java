package com.example.jdc.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = -6921092704575809920L;
    private Integer pid;
    private String pname;
}
