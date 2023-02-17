package com.tfuu.core.type;

import com.tfuu.core.utils.StringUtils;

/**
 * 描述：结果类型描述 接口
 * @author tfuu
 */
public interface ResultType {

    /** 描述：http状态码 */
    int getCode();

    /** 描述：错误码，使用英文 */
    String getDesc();

    /** 描述：详细描述 */
    String getInfo();

    // /
    static ResultType of(int code, String info, String desc) {
        ResultType r = new ResultType() {
            @Override
            public String getInfo() {
                return info;
            }

            @Override
            public String getDesc() {
                return desc;
            }

            @Override
            public int getCode() {
                return code;
            }
        };
        return r;
    }

    static ResultType of(ResultType baseType, String info, String desc) {
        return of(baseType.getCode(),
                StringUtils.defaultIfIsEmpty(info, baseType.getInfo()),
                StringUtils.defaultIfIsEmpty(desc, baseType.getDesc()));
    }

}
