package com.hnguigu.changgou.dto;

import java.io.Serializable;

public class BrandQueryDTO implements Serializable {

    private static final long serialVersionUID = 4131722791169623960L;

    private String name;
    private String letter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
