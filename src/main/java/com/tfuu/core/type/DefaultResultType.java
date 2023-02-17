package com.tfuu.core.type;

/**
 * 描述：系统缺省的结果类型
 * @author tfuu
 */
public enum DefaultResultType implements ResultType {

    ok(200, "ok", "处理成功"),
    string(200, "string", "处理成功"),
    redirect(302,"redirect", "请求重定向"),
    jsRedirect(200, "jsRedirect", "js请求重定向"),
    badRequest(400, "INPUT_VALIDATE_FAILED", "输入参数验证失败"),
    unauthorized(401,"TOKEN_VALIDATE_FAILED", "未授权"),
    forbidden(403,"RESOURCE_FORBIDDEN", "拒绝访问"),
    notFound(404, "RESOURCE_NOT_FOUND","接口或资源不存在"),
    appError("SYSTEM_CALL_FAILED", "系统调用失败"),
    wrongParameterType("WRONG_PARAMETER_TYPE", "参数类型错误"),
    nullPointerRequest(400,"NULL_POINT_REQ", "空指针请求"),
    emptyStringRequest(400,"EMPTY_STR_REQ", "空字符串请求"),
    blankStringRequest(400,"BLANK_STR_REQ", "空白字符串请求"),
    reqTimeOut("REQ_TIME_OUT","请求超时，稍后重试."),
    repeatableCommit(400, "repeatableCommit", "提交速度过快."),
    notModified(304, "notModified", "未修改.");

    private int code;
    private String desc;
    private String info;

    DefaultResultType(int code, String desc, String info) {
        this.code = code;
        this.desc = desc;
        this.info = info;
    }

    DefaultResultType(String desc, String info) {
        this.code = 500;
        this.desc = desc;
        this.info = info;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

}
