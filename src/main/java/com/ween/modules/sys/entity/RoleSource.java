package com.ween.modules.sys.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wen on 2018/1/9.
 */
@Entity
@Table(name = "sys_rolesource")
public class RoleSource implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    @Column
    private String roleId;//角色id
    @Column
    private String resourceId;//资源id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
