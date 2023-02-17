package com.tfuu.core.exception;

import com.tfuu.core.model.AppResult;
import com.tfuu.core.type.ResultType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

import static com.tfuu.core.exception.AppException.throwE;
import static com.tfuu.core.type.DefaultResultType.*;
import static com.tfuu.core.utils.StringUtils.isBlank;
import static com.tfuu.core.utils.StringUtils.isEmpty;

/**
 * @author dtsola<br/>
 *         描述：检查工具<br/>
 */
@SuppressWarnings("all")
public final class Inquisitor {

    private Inquisitor() {
    }

    /** 描述：判断非空，如果为有一个为NULL就抛异常. */
    public static <T> T[] notNull(T... srcs) throws AppException {
        if (srcs == null)
            throwE(nullPointerRequest);
        for (Object src : srcs)
            if (src == null)
                throwE(nullPointerRequest);
        return srcs;
    }

    /** 描述：判断非空，如果为NULL就抛异常. */
    public static <T> T notNull(T src) throws AppException {
        if (src == null)
            throwE(nullPointerRequest);
        return src;
    }

    /** 描述：判断非空，如果为NULL就抛异常. */
    public static <T> T notNull(T src, @Nullable String errorMsg)
            throws AppException {
        if (src == null)
            throwE(nullPointerRequest, errorMsg);
        return src;
    }

    /** 描述：判断非空，如果为NULL就抛异常. */
    public static <T> T notNull(T src, @Nullable AppResult appResult)
            throws AppException {
        if (src == null)
            throwE(appResult);
        return src;
    }

    /** 描述：判断非空，如果为NULL就抛异常. */
    public static <T> T notNull(T src, @Nullable ResultType resultType)
            throws AppException {
        if (src == null)
            throwE(resultType);
        return src;
    }

    /** 描述：判断非空，如果为NULL就抛异常. */
    public static <T> T notNull(T src, @Nullable String errorMsgFormat,
                                @Nullable String... args) throws AppException {
        if (src == null)
            throwE(nullPointerRequest, String.format(errorMsgFormat, args));
        return src;
    }

    /** 描述：判断非空，如果为NULL就抛异常. */
    public static <T> List<T> notNull(List<T> srcs,
                                      @Nullable List<String> errorMsgs) throws AppException {
        // notNull((Object) srcs, errorMsgs); // 递归bug
        if (srcs == null)
            throwE(nullPointerRequest);
        for (int i = 0; i < srcs.size(); ++i) {
            if (srcs.get(i) == null)
                throwE(errorMsgs.size() >= (i + 1) ? errorMsgs.get(i)
                        : nullPointerRequest.getInfo());
        }
        return srcs;
    }

    /** 描述：判断非空，如果为NULL或空字符串就抛异常. */
    public static <T extends CharSequence> T notEmpty(T src,
                                                      @Nullable AppResult appResult) throws AppException {
        if (isEmpty(src))
            throwE(appResult);
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串就抛异常. */
    public static <T extends CharSequence> T notEmpty(T src,
                                                      @Nullable ResultType resultType) throws AppException {
        if (isEmpty(src))
            throwE(resultType);
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串就抛异常. */
    public static <T extends CharSequence> T notEmpty(T src)
            throws AppException {
        if (isEmpty(src))
            throwE(emptyStringRequest);
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串就抛异常. */
    public static <T extends CharSequence> List<T> notEmpty(List<T> srcs,
                                                            @Nullable String errorMsg) throws AppException {
        notNull(srcs, errorMsg);
        srcs.forEach(src -> notEmpty(src, errorMsg));
        return srcs;
    }

    /** 描述：判断非空，如果为NULL或空字符串就抛异常. */
    public static <T extends CharSequence> T notEmpty(T src,
                                                      @Nullable String errorMsg) throws AppException {
        if (isEmpty(src))
            throwE(emptyStringRequest, errorMsg);
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串就抛异常. */
    public static <T extends CharSequence> T notEmpty(T src,
                                                      @Nullable String errorMsgFormat, @Nullable String... args)
            throws AppException {
        if (isEmpty(src))
            throwE(emptyStringRequest, String.format(errorMsgFormat, args));
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串就抛异常. */
    public static <T extends CharSequence> List<T> notEmpty(List<T> srcs,
                                                            @Nullable List<String> errorMsgs) throws AppException {
        notNull(srcs, errorMsgs);
        for (int i = 0; i < srcs.size(); ++i) {
            if (isEmpty(srcs.get(i)))
                throwE(emptyStringRequest,
                        errorMsgs.size() >= (i + 1) ? errorMsgs.get(i)
                                : emptyStringRequest.getInfo());
        }
        return srcs;
    }

    /** 描述：判断检查条件，如果expression为false就抛异常. */
    public static void checkArgument(boolean expression,
                                     @Nullable String errorMsg) throws AppException {
        if (!expression)
            throwE(badRequest, errorMsg);
    }

    /** 描述：判断检查条件，如果expression为false就抛异常. */
    public static void checkArgument(boolean expression,
                                     @Nullable AppResult appResult) throws AppException {
        if (!expression)
            throwE(appResult);
    }

    /** 描述：判断检查条件，如果expression为false就抛异常. */
    public static void checkArgument(boolean expression, ResultType resultType)
            throws AppException {
        notNull(resultType);
        if (!expression)
            throwE(resultType);
    }

    /** 描述：判断检查条件，如果expression为false就抛异常. */
    public static void checkArgument(boolean expression) throws AppException {
        if (!expression)
            throwE(badRequest);
    }

    /** 描述：判断检查条件，如果expression 返回值不为NULL就抛异常. */
    public static void checkArgument(Supplier<AppResult> expression) {
        AppResult r = expression.get();
        if (r != null)
            throwE(r);
    }

    /** 描述：判断非空，如果为NULL或空字符串或空白就抛异常. */
    public static <T extends CharSequence> T notBlank(T src,
                                                      @Nullable AppResult appResult) throws AppException {
        if (isBlank(src))
            throwE(appResult);
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串或空白就抛异常. */
    public static <T extends CharSequence> T notBlank(T src,
                                                      @Nullable ResultType resultType) throws AppException {
        if (isBlank(src))
            throwE(resultType);
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串或空白就抛异常. */
    public static <T extends CharSequence> T notBlank(T src)
            throws AppException {
        if (isBlank(src))
            throwE(blankStringRequest);
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串或空白就抛异常. */
    public static <T extends CharSequence> List<T> notBlank(List<T> srcs,
                                                            @Nullable String errorMsg) throws AppException {
        notNull(srcs, errorMsg);
        srcs.forEach(src -> notBlank(src, errorMsg));
        return srcs;
    }

    /** 描述：判断非空，如果为NULL或空字符串或空白就抛异常. */
    public static <T extends CharSequence> T notBlank(T src,
                                                      @Nullable String errorMsg) throws AppException {
        if (isBlank(src))
            throwE(blankStringRequest, errorMsg);
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串或空白就抛异常. */
    public static <T extends CharSequence> T notBlank(T src,
                                                      @Nullable String errorMsgFormat, @Nullable String... args)
            throws AppException {
        if (isBlank(src))
            throwE(blankStringRequest, String.format(errorMsgFormat, args));
        return src;
    }

    /** 描述：判断非空，如果为NULL或空字符串或空白就抛异常. */
    public static <T extends CharSequence> List<T> notBlank(List<T> srcs,
                                                            @Nullable List<String> errorMsgs) throws AppException {
        notNull(srcs, errorMsgs);
        for (int i = 0; i < srcs.size(); ++i) {
            if (isBlank(srcs.get(i)))
                throwE(blankStringRequest,
                        errorMsgs.size() >= (i + 1) ? errorMsgs.get(i)
                                : blankStringRequest.getInfo());
        }
        return srcs;
    }

}
