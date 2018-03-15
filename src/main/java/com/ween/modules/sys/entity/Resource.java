package com.ween.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wen on 2018/1/9.
 */
@Entity
@Table(name = "sys_resource")
public class Resource implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column
    @NotBlank
    private String name;//资源名称
    @Column
    private String url;//资源路径
    @Column
    private String openType;//打开方式
    @Column
    private String desc;//资源描述
    @Column
    @JsonProperty("iconCls")
    private String icon;
    @Column
    private String pid;//父级资源id
    @Column
    private Integer seq;
    @Column
    private Integer status;
    @Column
    private Integer type;//资源类别
    @Column
    private Date createTime;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
