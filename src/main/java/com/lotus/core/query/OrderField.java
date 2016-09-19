package com.lotus.core.query;

/**
 * @author wyy
 */
public class OrderField {

    public OrderField(String fieldName, String order) {
        super();
        this.fieldName = fieldName;
        this.order = order;
    }
    private String fieldName;
    private String order;

    public String getFieldName() {
        return fieldName;
    }
    public OrderField setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }
    public String getOrder() {
        return order;
    }
    public OrderField setOrder(String order) {
        this.order = order;
        return this;
    }
}
