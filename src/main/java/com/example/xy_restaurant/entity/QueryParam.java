package com.example.xy_restaurant.entity;

public class QueryParam {
    private int limit;
    private int offset;
    private String name;
    private int managerPower;

    public QueryParam() {
    }

    public QueryParam(int limit, int offset, String name, int power) {
        this.limit = limit;
        this.offset = offset;
        this.name = name;
        this.managerPower = power;
    }

    public QueryParam(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManagerPower() {
        return managerPower;
    }

    public void setManagerPower(int managerPower) {
        this.managerPower = managerPower;
    }

    @Override
    public String
    toString() {
        return "QueryParam{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", name='" + name + '\'' +
                ", managerPower=" + managerPower +
                '}';
    }
}
