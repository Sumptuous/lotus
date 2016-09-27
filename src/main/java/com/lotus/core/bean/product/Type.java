package com.lotus.core.bean.product;

import java.io.Serializable;

public class Type implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**主键id*/
    private Integer id;

    /**名称*/
    private String name;

    /**父id*/
    private Integer parentId;

    /**备注,用于google搜索页面描述*/
    private String note;

    /**是否可见 1:可见 0:不可见*/
    private Boolean isDisplay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", note='" + note + '\'' +
                ", isDisplay=" + isDisplay +
                '}';
    }
}