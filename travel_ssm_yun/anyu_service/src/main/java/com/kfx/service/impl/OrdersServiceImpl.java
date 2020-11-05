package com.kfx.service.impl;

import com.github.pagehelper.PageHelper;
import com.kfx.dao.OrdersDao;
import com.kfx.pojo.Orders;
import com.kfx.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page ,int size) throws Exception {
        //当前页码,每页显示条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String orderId) {
        return ordersDao.findByOrderId(orderId);
    }
}
