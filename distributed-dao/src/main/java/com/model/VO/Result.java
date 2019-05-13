package com.model.VO;

import java.util.List;

/**
 * Created by lenovo on 2019/5/8.
 * Title Result
 * Package  com.model.VO
 * Description
 *
 * @Version V1.0
 */
public class Result {
    private long totalCount;
    private long totalPage;

    private List content;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }
}
