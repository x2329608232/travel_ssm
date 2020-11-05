package com.kfx.service.impl;

import com.github.pagehelper.PageHelper;
import com.kfx.dao.SysLogDao;
import com.kfx.pojo.SysLog;
import com.kfx.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl  implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page ,int size) throws Exception{
        PageHelper.startPage(page,size);
        return sysLogDao.findALl();
    }
}
