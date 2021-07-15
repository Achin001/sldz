package com.gxc.sldz.handler;

import com.gxc.sldz.Utils.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {



    @ResponseBody
    @ExceptionHandler
    public Response serviceExceptionHandler(ServiceException exception){
        Response response=new Response(Integer.valueOf(exception.getCode()),exception.getMsg(),null);
        return response;
    }






}
