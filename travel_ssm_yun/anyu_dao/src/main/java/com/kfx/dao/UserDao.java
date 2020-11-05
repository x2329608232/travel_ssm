package com.kfx.dao;

import com.kfx.pojo.Role;
import com.kfx.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true,property = "id", column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.kfx.dao.RoleDao.findRoleByUserId")),
    })
    UserInfo findByUsername(String username);


    @Select("select * from users")
    List<UserInfo> findAll();


    @Insert("insert into users (username,password,email,phoneNum,status)values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);


    @Select("select * from users where id=#{ids}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType =java.util.List.class,many = @Many(select = "com.kfx.dao.RoleDao.findRoleAndPermission"))
    })
    UserInfo findById(String ids);

    @Select("select * from role where id not in (select roleid from  users_role where userid = #{userId})")
    List<Role> findOtherRoles(String userId);

    @Insert("insert into users_role (userid,roleId) values (#{userIds},#{roleId})")
    void addRoleToUser(@Param("userIds") String userId,@Param("roleId") String roleId);
}
