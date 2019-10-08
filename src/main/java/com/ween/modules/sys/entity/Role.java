package com.ween.modules.sys.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wen on 2018/1/9.
 */
@Entity
@Table(name = "sys_role")
public class Role implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String  id;

    @NotBlank
    @Column
    private String name;//角色名
    @Column
    private Integer seq;//排序号
    @Column
    private String desc;//描述
    @Column
    private Integer status;//状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
