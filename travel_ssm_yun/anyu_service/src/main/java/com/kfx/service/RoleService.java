package com.kfx.service;


import com.kfx.pojo.Permission;
import com.kfx.pojo.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void save(Role role);

    List<Permission> findByRoleIdOtherPermission(String id);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
