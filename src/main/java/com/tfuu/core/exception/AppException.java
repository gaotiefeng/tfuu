package com.tfuu.core.exception;


import com.tfuu.core.model.AppResult;
import com.tfuu.core.type.ResultType;

import static com.tfuu.core.model.AppResult.error;
import static com.tfuu.core.utils.ObjectUtils.defaultIfNull;

/**
 * @author dtsola
 * @author FZY
 *         <p>
 *         描述： 统一异常（基础）类<br/>
 */
@SuppressWarnings("all")
public class AppException extends RuntimeException {

    private AppResult result = null;

    /**
     * 描述：缺省构造器<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     */
    public AppException() {
        result = error();
    }

    /**
     * 描述：构造器<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     */
    public AppException(AppException appException) {
        super(appException);
        result = defaultIfNull(appException.getResult(), error());
    }

    /**
     * 描述：构造器<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public AppException(Throwable cause) {
        super(cause);
        if (cause instanceof AppException)
            result = defaultIfNull(((AppException) cause).getResult(), error());
        else
            result = error();
    }

    /**
     * 描述：构造器<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     */
    public AppException(String info) {
        super(info);
        result = error(info);
    }

    /**
     * 描述：构造器<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param desc
     *            错误码<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     */
    public AppException(String desc, String info) {
        super(info);
        result = error(info);
        result.setDesc(desc);
    }

    /**
     * 描述：构造器<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public AppException(String info, Throwable cause) {
        super(info, cause);
        result = error(info);
    }

    /**
     * 描述：构造器<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param desc
     *            错误码<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public AppException(String desc, String info, Throwable cause) {
        super(info, cause);
        result = error(info);
        result.setDesc(desc);
    }

    /**
     * 描述：构造器<br/>
     * 使用传入的resultType<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param result
     *            用于Action接口返回<br/>
     */
    public AppException(ResultType resultType) {
        super(resultType.getInfo());
        this.result = error(resultType);
    }

    /**
     * 描述：构造器<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param resultType
     *            用于Action接口返回<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public AppException(ResultType resultType, Throwable cause) {
        super(cause);
        this.result = error(resultType);
    }

    /**
     * 描述：构造器<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param appResult
     *            用于Action接口返回<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     */
    public AppException(ResultType resultType, String info) {
        super(info);
        this.result = error(resultType);
        this.result.setInfo(info);
    }

    /**
     * 描述：构造器<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param resultType
     *            用于Action接口返回<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public AppException(ResultType resultType, String info, Throwable cause) {
        super(info, cause);
        this.result = error(resultType);
        this.result.setInfo(info);
    }

    /**
     * 描述：构造器<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param result
     *            用于Action接口返回<br/>
     */
    public AppException(AppResult result) {
        super(result.getInfo());
        this.result = result;
    }

    /**
     * 描述：构造器<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param appResult
     *            用于Action接口返回<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public AppException(AppResult appResult, Throwable cause) {
        super(appResult.getInfo(), cause);
        this.result = appResult;
    }

    /**
     * 描述：构造器<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param appResult
     *            用于Action接口返回<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     */
    public AppException(AppResult appResult, String info) {
        super(info);
        this.result = appResult;
        this.result.setInfo(info);
    }

    /**
     * 描述：构造器<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param appResult
     *            用于Action接口返回<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public AppException(AppResult appResult, String info, Throwable cause) {
        super(info, cause);
        this.result = appResult;
        this.result.setInfo(info);
    }

    /**
     * 描述：获取 Action结果描述 信息
     */
    public AppResult getResult() {
        return result;
    }

    // /
    /**
     * 描述：抛异常<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     */
    public static void throwE() throws AppException {
        throw new AppException();
    }

    /**
     * 描述：抛异常<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public static void throwE(Throwable cause) throws AppException {
        throw new AppException(cause);
    }

    /**
     * 描述：抛异常<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     */
    public static void throwE(String info) throws AppException {
        throw new AppException(info);
    }

    /**
     * 描述：抛异常<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param desc
     *            错误码<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     */
    public static void throwE(String desc, String info) throws AppException {
        throw new AppException(desc, info);
    }

    /**
     * 描述：抛异常<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public static void throwE(String info, Throwable cause) throws AppException {
        throw new AppException(info, cause);
    }

    /**
     * 描述：抛异常<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param desc
     *            错误码<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public static void throwE(String desc, String info, Throwable cause)
            throws AppException {
        throw new AppException(desc, info, cause);
    }

    /**
     * 描述：抛异常<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param result
     *            用于Action接口返回<br/>
     */
    public static void throwE(AppResult result) throws AppException {
        throw new AppException(result);
    }

    /**
     * 描述：抛异常<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param appResult
     *            用于Action接口返回<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public static void throwE(AppResult appResult, Throwable cause)
            throws AppException {
        throw new AppException(appResult, cause);
    }

    /**
     * 描述：抛异常<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param appResult
     *            用于Action接口返回<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     */
    public static void throwE(AppResult appResult, String info)
            throws AppException {
        throw new AppException(appResult, info);
    }

    /**
     * 描述：抛异常<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param appResult
     *            用于Action接口返回<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     */
    public static void throwE(AppResult appResult, String errorMsgFormat,
                              String... args) throws AppException {
        throw new AppException(appResult, String.format(errorMsgFormat, args));
    }

    /**
     * 描述：抛异常<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param appResult
     *            用于Action接口返回<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public static void throwE(AppResult appResult, String info, Throwable cause)
            throws AppException {
        throw new AppException(appResult, info, cause);
    }

    /**
     * 描述：抛异常<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     */
    public static void throwE(AppException appException) throws AppException {
        throw new AppException(appException);
    }

    /**
     * 描述：抛异常<br/>
     * 默认使用{@link com.fushitech.core.model.ErrorResult}<br/>
     * 返回Action异常结果信息<br/>
     */
    public static void throwE(ResultType resultType) throws AppException {
        throw new AppException(resultType);
    }

    /**
     * 描述：抛异常<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param resultType
     *            用于Action接口返回<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public static void throwE(ResultType resultType, Throwable cause)
            throws AppException {
        throw new AppException(resultType, cause);
    }

    /**
     * 描述：抛异常<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param resultType
     *            用于Action接口返回<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     */
    public static void throwE(ResultType resultType, String info)
            throws AppException {
        throw new AppException(resultType, info);
    }

    /**
     * 描述：抛异常<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param resultType
     *            用于Action接口返回<br/>
     * @param message
     *            错误信息，主要用于 日志 记录<br/>
     */
    public static void throwE(ResultType resultType, String errorMsgFormat,
                              String... args) throws AppException {
        throw new AppException(resultType, String.format(errorMsgFormat, args));
    }

    /**
     * 描述：抛异常<br/>
     * 使用传入的appResult<br/>
     * 返回Action异常结果信息<br/>
     *
     * @param resultType
     *            用于Action接口返回<br/>
     * @param info
     *            错误信息，主要用于 日志 记录<br/>
     * @param cause
     *            引起当前异常的源头异常，异常链路，可以接受其他 异常的调用信息，来初始化 异常栈信息，主要用于 日志 记录<br/>
     */
    public static void throwE(ResultType resultType, String info,
                              Throwable cause) throws AppException {
        throw new AppException(resultType, info, cause);
    }

}
