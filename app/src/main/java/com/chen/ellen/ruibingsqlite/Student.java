package com.chen.ellen.ruibingsqlite;

public class Student {
    private String name;
    private String xueHao;
    private int age;
    private boolean isMan = false;

    public Student() {
    }

    public Student(String name, String xueHao, int age, boolean isMan) {
        this.name = name;
        this.xueHao = xueHao;
        this.age = age;
        this.isMan = isMan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXueHao() {
        return xueHao;
    }

    public void setXueHao(String xueHao) {
        this.xueHao = xueHao;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }
}
