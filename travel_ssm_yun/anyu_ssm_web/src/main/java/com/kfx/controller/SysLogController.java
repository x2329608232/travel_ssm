package com.kfx.controller;

import com.github.pagehelper.PageInfo;
import com.kfx.pojo.SysLog;
import com.kfx.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有日志
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "5")Integer size) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
       List<SysLog> list= sysLogService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);

       modelAndView.addObject("pageInfo",pageInfo);
       modelAndView.setViewName("syslog-list");
       return modelAndView;
    }
}
