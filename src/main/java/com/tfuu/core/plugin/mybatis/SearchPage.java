package com.tfuu.core.plugin.mybatis;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tfuu.core.model.PageResult;
import com.tfuu.core.utils.PageUtils;

import java.beans.Transient;

public class SearchPage<T> extends Page<T> {
    public SearchPage() {
    }

    public SearchPage(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

    public SearchPage(long current, long size, long total, boolean isSearchCount) {
        super(current, size, total, isSearchCount);
    }

    public SearchPage(long current, long size, long total) {
        super(current, size, total);
    }

    public SearchPage(long current, long size) {
        super(current, size);
    }

    public static <T> SearchPage<T> of(Page<T> src) {
        SearchPage<T> r = new SearchPage(src.getCurrent(), src.getSize(), src.getTotal(), src.isSearchCount());
        r.setRecords(src.getRecords());
        return r;
    }

    @Transient
    public PageResult<T> getPageResult() {
        PageResult<T> r = new PageResult();
        r.setContent(this.records);
        r.setNumberOfElements(this.records.size());
        r.setPageNum((int)this.current);
        r.setPageSize((int)this.size);
        r.setTotalElements(this.total);
        r.setTotalPages((long)PageUtils.totalPage((int)this.total, (int)this.size));
        return r;
    }
}

