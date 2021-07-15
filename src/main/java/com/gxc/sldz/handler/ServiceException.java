package com.gxc.sldz.handler;

import lombok.Data;

@Data
public class ServiceException extends Throwable {
    private String code;
    private String msg;

    public ServiceException(String toString, String msg) {
    }


}
