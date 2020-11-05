package com.kfx.controller;

import com.kfx.pojo.Role;
import com.kfx.pojo.UserInfo;
import com.kfx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 添加用户
     * anyu用户才可添加
     * @param userInfo
     * @return
     */
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='anyu'")
    public String save(UserInfo userInfo)  {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 查询所有用户
     * 拥有ADMIN角色用户才能访问
     * @return
     */
    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll()  {
        List<UserInfo> userInfoList =  userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userInfoList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 用户详情
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id){
        UserInfo user = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     *用户关联操作
     * 查询用户没有的所有角色
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String userId){
        UserInfo  userInfo = userService.findById(userId);
        //根据用户id查询出 没有的角色
        List<Role>  roleList =   userService.findOtherRoles(userId);
        ModelAndView  mv = new ModelAndView();
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
