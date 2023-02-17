package com.tfuu.core.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tfuu.core.type.DefaultResultType;
import com.tfuu.core.type.ResultType;

/**
 * @author dtsola
 * @author FZY 描述：Action返回业务失败结果<br/>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonView(ResultView.class)
@SuppressWarnings("all")
class ErrorResult extends AppResult {

    private static final long serialVersionUID = 1L;

    public ErrorResult() {
        autoSetResultType(DefaultResultType.appError);
    }

    public ErrorResult(ResultType type) {
        super(type);
    }

}