package app.wms.empty;

import java.util.Date;

/**
 * Created by zhou on 2016/9/27.
 */

public class CustOrderProductResponse {

    /** ID */
    private Long id;
    /** 订单号 */
    private String orderNo;
    /**  */
    private Integer orderType;
    /** SKU */
    private String sku;
    /** 仓库编号 */
    private String warehouseCode;
    /** 商品类型 */
    private String productType;
    /** 数量 */
    private Integer num;
    /** 名称 */
    private String name;
    /** 单价 */
    private String price;
    /** 创建时间 */
    private Date createTime;
    /** 创建用户 */
    private String createUser;
    /** 修改时间 */
    private Date updateTime;
    /** 修改用户 */
    private String updateUser;
    /** 有效标识 */
    private Integer yn;
    /** 出库单号 */
    private String outboundNo;
    /** 出库类型 客户订单/集合单/任务单/调拨单/返厂单/报损单 */
    private Integer outboundType;
    /** 应下数量 */
    private Integer planNum;
    /** 实下数量 */
    private Integer actualNum;
    /** 差异数量 */
    private Integer diffNum;
    /** 库区 */
    private String areaCode;
    /** 货位 */
    private String locationCode;
    /** 有效期至 */
    private String operator;
    /**单位*/
    private String unit;
    /**已下数量*/
    private String offShelfNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public String getOutboundNo() {
        return outboundNo;
    }

    public void setOutboundNo(String outboundNo) {
        this.outboundNo = outboundNo;
    }

    public Integer getOutboundType() {
        return outboundType;
    }

    public void setOutboundType(Integer outboundType) {
        this.outboundType = outboundType;
    }

    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    public Integer getActualNum() {
        return actualNum;
    }

    public void setActualNum(Integer actualNum) {
        this.actualNum = actualNum;
    }

    public Integer getDiffNum() {
        return diffNum;
    }

    public void setDiffNum(Integer diffNum) {
        this.diffNum = diffNum;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOffShelfNum() {
        return offShelfNum;
    }

    public void setOffShelfNum(String offShelfNum) {
        this.offShelfNum = offShelfNum;
    }
}
