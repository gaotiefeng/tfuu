package com.tfuu.core.model;

import com.tfuu.core.type.DefaultResultType;
import com.tfuu.core.type.ResultType;

import java.io.Serializable;
import java.util.Map;

public class AppResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code = 500;
    private String desc;
    private String info;
    private long ts = System.currentTimeMillis() / 1000L;
    
    private T data;

    public AppResult(){}

    public AppResult(int code, String info){
        this.code = code;
        this.info = info;
    }

    public AppResult(T data, ResultType type) {
        setData(data);
        autoSetResultType(type);
    }

    public AppResult(ResultType type) {
        autoSetResultType(type);
    }

    public AppResult(T data){
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public long getTs() {
        return ts;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public final void autoSetResultType(ResultType resultType) {
        setCode(resultType.getCode());
        setDesc(resultType.getDesc());
        setInfo(resultType.getInfo());
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", ts=" + ts + '\'' +
                ", desc='" + desc + '\'' +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
    //成功的静态方法
    public static AppResult ok(){
        return new AppResult(DefaultResultType.ok);
    }

    //成功的静态方法
    public static <T> AppResult<T> ok(T data){
        AppResult r = new AppResult(DefaultResultType.ok);
        r.setData(data);
        return r;
    }
    //成功的静态方法
    public static AppResult ok(String info, Map<String, Object> data)
    {
        AppResult res = new AppResult(DefaultResultType.ok);
        res.setInfo(info);
        res.setData(data);

        return res;
    }

    //成功的静态方法
    public static AppResult ok(String  data)
    {
        AppResult res = new AppResult(DefaultResultType.ok);
        res.setData(data);

        return res;
    }

    //成功的静态方法
    public static <T> AppResult<T> ok(String info, T data)
    {
        AppResult res = new AppResult(DefaultResultType.ok);
        res.setInfo(info);
        res.setData(data);

        return res;
    }

    //失败的静态方法
    public static AppResult error(){
        AppResult r = new AppResult(DefaultResultType.appError);
        return r;
    }

    //失败的静态方法
    public static AppResult error(ResultType resultType){
        AppResult r = new AppResult(resultType);
        return r;
    }

    //失败的静态方法
    public static AppResult error(String info){
        AppResult r = new AppResult(DefaultResultType.appError);
        r.setInfo(info);
        return r;
    }

    //失败的静态方法
    public static <T> AppResult<T> error(ResultType resultType, String info, T data){
        AppResult r = new AppResult(resultType);
        r.setInfo(info);
        r.setData(data);
        return r;
    }

    //失败 枚举类
    public static <T> AppResult<T> error(ResultType resultType, String info){
        AppResult r = new AppResult(resultType);
        r.setInfo(info);
        return r;
    }

}
