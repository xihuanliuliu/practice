package com.zj.community.utils;

public class Page {

    /**
     * 分页--从0页开始，每页多少条数据
     */

    private int pageSize;

    private int pageNum;

    // 起始页
    private int current = 1;
    // 每页大小
    private int limit = 10;
    // 数据总数(用于计算总页数)
    private int rows;

    private String path;
    /**
     * 获取起始行数
     * @return
     */
    public int getOffset() {
        return (current - 1) * limit;
    }

    /**
     * 获取起始页数
     */
    public int getPageNum() {
        int from = current - 2;
        return from < 0 ? 1 : from;
    }

    /**
     * 总页数
     * @return
     */
    public int getTotal() {
        int i = rows % limit;
        if (i == 0) {
            return rows / 10;
        } else {
            return (rows / 10) + 1;
        }
    }

    /**
     * 结束的页码
     */

    public int getEnd() {
        int total = getTotal();
        int curr = current + 2;
        return Math.min(total, curr);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit > 10 && limit < 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
