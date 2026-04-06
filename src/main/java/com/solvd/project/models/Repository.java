package com.solvd.project.models;

import java.util.*;

public class Repository <T>{
    private List<T> list = new ArrayList<>();
    private Set<T> set = new HashSet<>();

    public void add(T value){
        list.add(value);
        set.add(value);
    }

    public List<T> getList(){
        return list;
    }

    public Set<T> getSet(){
        return set;
    }

}
