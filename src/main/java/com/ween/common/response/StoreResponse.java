package com.ween.common.response;
import com.ween.common.response.Response;

import java.util.List;
public class StoreResponse extends Response {
    private List<?> rows;
    private long total;

    public StoreResponse() {
        setSuccess(true);
    }

    public StoreResponse(boolean success, List<?> rows, long total) {
        super(success);
        this.rows = rows;
        this.total = total;
    }

    public StoreResponse(boolean success) {
        super(success);
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
