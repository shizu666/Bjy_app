package app.wms.empty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhou on 2016/9/26.
 */

public class Product implements Serializable{
    /** 序列化标识 */
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    /** 采购单号 */
    private String purchaseOrderNo;
    /** SKU */
    private String sku;
    /** 正品数量 */
    private Integer num;
    /** 赠品数量 */
    private Integer giftNum;
    /** 单位 */
    private String unit;
    /** 名称 */
    private String name;
    /** 价格 */
    private String price;
    /** 规格 */
    private String specification;
    /** 保质期 */
    private String period;
    /**保质期天数*/
    private Integer selfLife;
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

    /** 上架数量*/
    private Integer inboundedNum;
    /**仓库号*/
    private String warehouseCode;
    /**货主编码*/
    private String ownerCode;
    /**货位号*/
    private String locationCode;
    /** 有效期至 */
    private String validUntilDate;
    /**操作人--目前统一 是编号，若增加姓名，则另增字段*/
    private String operator;
    /**操作时间*/
    private String operateTime;
    /**生产日期*/
    private String produceDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(Integer giftNum) {
        this.giftNum = giftNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getSelfLife() {
        return selfLife;
    }

    public void setSelfLife(Integer selfLife) {
        this.selfLife = selfLife;
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

    public Integer getInboundedNum() {
        return inboundedNum;
    }

    public void setInboundedNum(Integer inboundedNum) {
        this.inboundedNum = inboundedNum;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(String validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }
}
