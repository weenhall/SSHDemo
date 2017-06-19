package com.ween.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by wen on 2017/3/15.
 */
@Entity
public class Users implements Serializable{
    @Id
    @GeneratedValue(generator = "uemail")
    @GenericGenerator(name = "uemail",strategy = "assigned")
    private String uemail;
    private String nickname;
    private String username;
    private String password;
    private String cardnum;
    private String phonenum;
    private Integer age;

    public Users(){

    }

    public Users(String uemail,String nickname, String username, String password, String cardnum, String phonenum,Integer age) {
        this.uemail=uemail;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.cardnum = cardnum;
        this.phonenum = phonenum;
        this.age=age;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
