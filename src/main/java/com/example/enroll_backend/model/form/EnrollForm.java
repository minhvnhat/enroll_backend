package com.example.enroll_backend.model.form;

public class EnrollForm {
    private int sid;
    private String op;

    public int getSid() {
        return sid;
    }

    public String getOp() {
        return op;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setOp(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return "EnrollForm{" +
                "sid=" + sid +
                ", op='" + op + '\'' +
                '}';
    }
}
