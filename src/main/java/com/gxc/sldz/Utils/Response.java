package com.gxc.sldz.Utils;


import lombok.Data;

@Data
public class Response {

    private int status;
    private String msg;
    private Object data;



    public Response(int i, String msg, Object o) {
    }
}
