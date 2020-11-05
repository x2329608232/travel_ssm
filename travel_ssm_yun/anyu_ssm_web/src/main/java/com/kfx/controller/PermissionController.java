package com.kfx.controller;

import com.kfx.pojo.Permission;
import com.kfx.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Permission> permissions = permissionService.finaAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList",permissions);
        modelAndView.setViewName("permission-list");
        return modelAndView;

    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @RequestMapping("/save")
    public  String save (Permission permission){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }


}
