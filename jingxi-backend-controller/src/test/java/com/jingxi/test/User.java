package com.jingxi.test;

import com.jingxi.model.TbOrder;
import com.jingxi.model.TbUser;

import java.util.List;

public class User extends TbUser{

    private List<TbOrder> orderList;

    public List<TbOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<TbOrder> orderList) {
        this.orderList = orderList;
    }

    public User() {}
}
