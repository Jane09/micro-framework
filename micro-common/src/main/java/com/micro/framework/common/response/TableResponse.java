package com.micro.framework.common.response;

import lombok.*;

import java.util.List;

/**
 * @author tb
 * @date 2018/12/27 18:05
 */
@Getter
@Setter
@Builder
public class TableResponse<T> {

    private Table<T> data;

    public TableResponse(long total, List<T> rows) {
        this.data = new Table<>(total,rows);
    }

    public TableResponse() {
        this.data = new Table<>();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class Table<T> {
        private long total;
        private List<T> rows;
    }
}
