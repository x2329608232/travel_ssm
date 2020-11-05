package com.kfx.dao;

import com.kfx.pojo.Member;
import com.kfx.pojo.Orders;
import com.kfx.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrdersDao {
        @Select("select * from orders")
        @Results({
                @Result(id = true,property = "id",column = "id"),
                @Result(property = "orderNum",column ="orderNum" ),
                @Result(property = "orderTime",column = "orderTime"),
                @Result(property = "orderStatus",column = "orderStatus"),
                @Result(property = "peopleCount",column = "peopleCount"),
                @Result(property = "payType",column = "payType"),
                @Result(property = "orderDesc",column = "orderDesc"),
                @Result(property = "product",column = "PRODUCTId",javaType = Product.class,one = @One(select ="com.kfx.dao.ProductDao.findById"))
        })
        List<Orders> findAll() throws  Exception   ;

        @Select("select * from orders where id=#{orderId}")
        @Results({
                @Result(id = true,property = "id",column = "id"),
                @Result(property = "orderNum",column = "orderNum"),
                @Result(property = "orderTime",column = "orderTime"),
                @Result(property = "orderStatus",column = "orderStatus"),
                @Result(property = "peopleCount",column = "peopleCount"),
                @Result(property = "payType",column = "payType"),
                @Result(property = "orderDesc",column = "orderDesc"),
                @Result(property = "product",column = "PRODUCTID", javaType = Product.class,one = @One(select = "com.kfx.dao.ProductDao.findById")),
                @Result(property = "member",column = "MEMBERID", javaType = Member.class,one = @One(select = "com.kfx.dao.MemberDao.findById")),
                @Result(property = "travellers",column = "id", javaType = java.util.List.class,many = @Many(select = "com.kfx.dao.TravellerDao.findByOrderId")),
        })
        Orders findByOrderId(String orderId);
}
