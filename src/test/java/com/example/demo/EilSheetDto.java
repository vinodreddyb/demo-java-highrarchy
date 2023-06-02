package com.example.demo;

import java.util.ArrayList;
import java.util.List;


public class EilSheetDto {
    private String key;
    private EilCategoryDTO data;
    private List<EilSheetDto> children = new ArrayList<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public EilCategoryDTO getData() {
        return data;
    }

    public void setData(EilCategoryDTO data) {
        this.data = data;
    }

    public List<EilSheetDto> getChildren() {
        return children;
    }

    public void setChildren(List<EilSheetDto> children) {
        this.children = children;
    }
}
