package com.example.demo.common.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
public class PageParam {

    private int page = 1;


    private int size = 20;


    private String sortName = "";


    private String sortOrder = "DESC";


    public boolean isAsc() {
        return Objects.equals(StringUtils.isEmpty(sortOrder) ? "" : sortOrder.toUpperCase(), "ASC");
    }

    @JsonIgnore
    public List<OrderItem> orderItem() {
        if(StringUtils.isEmpty(sortName)) {
            return Collections.emptyList();
        }
        List<OrderItem> orderItems;
        if(isAsc()) {
            orderItems = OrderItem.ascs(sortName.split(","));
        } else {
            orderItems = OrderItem.descs(sortName.split(","));
        }
        return orderItems;
    }
}
