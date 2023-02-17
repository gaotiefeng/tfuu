package com.tfuu.core.model;

/**
 *
 */

import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dtsola<br/>
 *         描述：分页结果对象<br/>
 */
@SuppressWarnings("all")
@JsonView(ResultView.class)
public class PageResult<T> implements JsonString {

    /** 描述：页大小 */
    private int pageSize = 10;
    /** 描述：当前页码（从1开始计算） */
    private int pageNum = 1;
    /** 描述：总元素个数 */
    private long totalElements = 0;
    /** 描述：总页数 */
    private long totalPages = 0;
    /** 描述：当前页，元素实际个数 */
    private int numberOfElements = 0;
    /** 描述：元素列表 */
    private List<T> content = new ArrayList<T>();
    /** 描述：自定义字段*/
    private Parameters diyItems = Parameters.create();

    public PageResult() {
    }

    public Parameters appendDiyItem(String key, Object value){
        diyItems.append(key, value);
        return diyItems;
    }

    public Parameters getDiyItems() {
        return diyItems;
    }

    public void setDiyItems(Parameters diyItems) {
        this.diyItems = diyItems;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageResult<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public PageResult<T> setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public PageResult<T> setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public PageResult<T> setTotalPages(long totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public PageResult<T> setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    public List<T> getContent() {
        return content;
    }

    public PageResult<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }

    /** 描述：用输入值的属性覆盖当前对象的值 */
    public PageResult<T> autoOverrideResult(PageResult input) {
        pageSize = input.pageSize;
        pageNum = input.pageNum;
        totalElements = input.totalElements;
        totalPages = input.totalPages;
        numberOfElements = input.numberOfElements;
        diyItems = input.diyItems;
        content = input.content;
        return this;
    }

}

