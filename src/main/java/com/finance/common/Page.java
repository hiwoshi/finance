package com.finance.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : shenhao
 * @date : 2019/10/8 16:53
 */
@Data
public class Page<T> implements IPage<T> {

    @ApiModelProperty("页面大小")
    private Long size = 5L;

    @ApiModelProperty("总页数")
    private Long total;

    @ApiModelProperty("当前页")
    private Long current = 1L;

    private List<OrderItem> orders;

    private List<T> records;

    @ApiModelProperty("排序字段")
    private List<String> orderStr;

    @ApiModelProperty("排序顺序")
    private List<String> orderAsc;

    @Override
    public List<OrderItem> orders() {
        this.orders = new ArrayList<>();
        initOrderStr();
        if (CollectionUtils.isEmpty(orderAsc)){
            orderStr.forEach(item -> this.orders.add(new OrderItem().setColumn(CamelAndUnderLineConverter.humpToLine2(item))));
        }else if (orderAsc.size() == 1){
            boolean flag = "asc".equalsIgnoreCase(orderAsc.get(0));
            orderStr.forEach(item -> this.orders.add(new OrderItem().setColumn(CamelAndUnderLineConverter.humpToLine2(item)).setAsc(flag)));
        }else {
            if (orderStr.size() != orderAsc.size()) throw new RuntimeException("排序字符串与排序规则数量不同！");
            for (int i = 0; i < orderStr.size(); i++) {
                this.orders.add(new OrderItem().setAsc("asc".equalsIgnoreCase(orderAsc.get(i)))
                                                .setColumn(CamelAndUnderLineConverter.humpToLine2(orderStr.get(i))));
            }
        }
        return this.orders;
    }

    @Override
    public List<T> getRecords() {
        return records;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public IPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    private void initOrderStr(){
        if (CollectionUtils.isEmpty(orderStr)){
            this.orderStr = new ArrayList<>();
            this.orderStr.add("id");
        }
    }

}
