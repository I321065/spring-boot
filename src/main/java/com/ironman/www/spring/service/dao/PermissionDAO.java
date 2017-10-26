package com.ironman.www.spring.service.dao;

import com.ironman.www.spring.service.entity.Permission;

import java.util.List;

/**
 * Created by superuser on 10/25/17.
 */
public interface PermissionDAO {

    List<Permission> findAllPermissions();

    List<Permission> findPermissionsByUserId(long userId);
}
