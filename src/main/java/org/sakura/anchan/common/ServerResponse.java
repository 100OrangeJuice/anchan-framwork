package org.sakura.anchan.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 封装的高复用响应对象
 * Created by Anchan on 2017/12/1.
 */

/*  失败的时候不返回data,所以data的value是null
 *  能保证序列号json的时候，如果是null的对象，key也会消失。
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;
    /*
        构造方法
     */
    private ServerResponse(int status){
        this.status = status;
    }
    private ServerResponse(int status, T data){
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status,String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    private ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
    @JsonIgnore
    //使之不在json结果序列号中
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /*
     *  get方法
     */
    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return msg;
    }
    /*
        成功的方法
     */
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }
    /*
        失败的方法
     */
    public static  ServerResponse createByError(){
        return new ServerResponse(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public static  ServerResponse createByErrorMessage(String errorMessage){
        return new ServerResponse(ResponseCode.ERROR.getCode(),errorMessage);
    }
    public static  ServerResponse createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse(errorCode,errorMessage);
    }
}
