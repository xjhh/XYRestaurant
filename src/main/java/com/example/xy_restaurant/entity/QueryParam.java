package com.example.xy_restaurant.entity;

public class QueryParam {
    private int limit;
    private int offset;
    private String name;
    private int type;

    public QueryParam() {
    }

    public QueryParam(int limit, int offset, String name, int type) {
        this.limit = limit;
        this.offset = offset;
        this.name = name;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String
    toString() {
        return "QueryParam{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
