package com.tfuu.core.model;

import java.io.Serializable;
import java.util.Map;

public class AppResult <T> implements Serializable {
    
        private static final long serialVersionUID = 1L;
        private int code = 500;
        private String desc = "SYSTEM_CALL_FALED";
        private String info = "网络延迟,请稍后！";
        private long ts = System.currentTimeMillis() / 1000L;

        private T data;

        public AppResult(){}

        public AppResult(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public AppResult(ResultType resultType) {
            autoSetResultType(resultType);
        }

        public AppResult(ResultType resultType,T data) {
            autoSetResultType(resultType);
            this.data = data;
        }

        public AppResult(T data){
            this.data = data;
        }

        public final void autoSetResultType(ResultType resultType) {
            setCode(resultType.getCode());
            setDesc(resultType.getDesc());
            setInfo(resultType.getInfo());
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

        @Override
        public String toString() {
            return "AppResult{" +
                    "code=" + code +
                    ", ts=" + ts + '\'' +
                    ", desc='" + desc + '\'' +
                    ", info='" + info + '\'' +
                    ", data=" + data +
                    '}';
        }
        //成功的静态方法
        public static AppResult ok(){
            AppResult r = new AppResult();
            r.setCode(200);
            r.setInfo("SUCCESS");
            r.setDesc("ok");
            return r;
        }

        //成功的静态方法
        public static <T> AppResult <T> ok(T data){
            AppResult r = new AppResult();
            r.setCode(200);
            r.setInfo("SUCCESS");
            r.setDesc("ok");
            r.setData(data);
            return r;
        }
        //成功的静态方法
        public static AppResult ok(String info, Map<String, Object> data)
        {
            AppResult res = new AppResult();
            res.setCode(200);
            res.setDesc("ok");
            res.setInfo(info);
            res.setData(data);

            return res;
        }

        //成功的静态方法
        public static AppResult ok(String  data)
        {
            AppResult res = new AppResult();
            res.setCode(200);
            res.setDesc("ok");
            res.setInfo("success");
            res.setData(data);

            return res;
        }

        //成功的静态方法
        public static <T> AppResult <T> ok(String info, T data)
        {
            AppResult res = new AppResult();
            res.setCode(200);
            res.setDesc("ok");
            res.setInfo(info);
            res.setData(data);

            return res;
        }

        //失败的静态方法
        public static AppResult error(){
            AppResult r = new AppResult();
            r.setCode(r.code);
            r.setDesc(r.desc);
            r.setInfo(r.info);
            r.setData(r.data);
            return r;
        }
        //失败的静态方法
        public static AppResult error(int code){
            AppResult r = new AppResult();
            r.setCode(code);
            r.setDesc(r.desc);
            r.setInfo(r.info);
            r.setData(r.data);
            return r;
        }

        //失败的静态方法
        public static AppResult error(int code,String info){
            AppResult r = new AppResult();
            r.setCode(code);
            String desc = getDesc(code);
            r.setDesc(desc);
            r.setInfo(info);
            return r;
        }

        //失败的静态方法
        public static <T>AppResult<T> error(int code,String info,T data){
            AppResult r = new AppResult();
            r.setCode(code);
            r.setInfo(info);
            r.setData(data);
            String desc = getDesc(code);
            r.setDesc(desc);
            return r;
        }

        public static String getDesc(int code)
        {
            String desc = "SYSTEM_CALL_FALED";
            if (code == 400) {
                desc = "INPUT_VALIDATE_FAILED";
            }else if (code == 401) {
                desc = "INPUT_VALIDATE_FAILED";
            }else if (code == 403) {
                desc = "RESOURCE_FORBIDDEN";
            }else if (code == 404) {
                desc = "RESOURCE_NOT_FOUND";
            }

            return desc;
        }

}
