package com.kfx.service;

import com.kfx.pojo.SysLog;

import java.util.List;

public interface SysLogService {
   void save (SysLog sysLog);

    List<SysLog> findAll(int page ,int size)throws Exception;
}
