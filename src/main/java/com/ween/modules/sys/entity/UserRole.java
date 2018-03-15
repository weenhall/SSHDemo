package com.ween.modules.sys.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wen on 2018/1/9.
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable{

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.Assigned")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column
    private String userId;
    @Column
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
