package com.example.demo.common.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Data
public class Sheet<T> implements Serializable {

    private static final long serialVersionUID = -3985294121806252528L;

    private long total = -1;

    private Collection<T> rows;

    public static <T> Sheet<T> empty() {
        return of(Collections.emptyList(), 0);
    }

    public static <T> Sheet<T> of(Collection<T> rows, long total) {
        Sheet<T> sheet = new Sheet<>();
        sheet.setRows(rows);
        sheet.setTotal(total);
        return sheet;
    }
}
