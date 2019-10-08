package com.ween.modules.sys.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

/**
 * Created by wen on 2017/3/15.
 */
@Entity
@Table(name = "sys_user")
public class User implements Serializable{
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    @Column
    private String username;//用户名
    @Column
    private String password;//密码
    @Column
    private String name;//姓名
    @Column
    private Integer sex;//性别
    @Column
    private Integer age;//年龄
    @Column
    private String phone;//联系方式
    @Column(unique = true)
    private String idenCard;//证件号码
    @Column
    private String email;//邮箱
    @Column
    private String address;//地址
    @Column
    private String organization;//所属机构
    @Column
    private Integer type;//用户类别
    @Column
    private Integer status;//用户状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdenCard() {
        return idenCard;
    }

    public void setIdenCard(String idenCard) {
        this.idenCard = idenCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
