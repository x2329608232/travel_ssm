package com.kfx.service;

import com.kfx.pojo.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> finaAll();

    void save(Permission permission);
}
