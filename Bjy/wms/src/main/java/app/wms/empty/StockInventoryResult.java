package app.wms.empty;

import java.util.Date;

/**
 * Created by zhou on 2016/9/29.
 */

public class StockInventoryResult {

    /** ID */
    private Long id;
    /** SKU */
    private String sku;
    /** 货主编号 */
    private String ownerCode;
    /** 货主名称 */
    private String ownerName;
    /** 仓库 */
    private String warehouseCode;
    /**  */
    private String warehouseName;
    /** 库区 */
    private String areaCode;
    /** 巷道 */
    private String roadCode;
    /** 货位 */
    private String locationCode;
    /**  */
    private Integer locationNum;
    /** 盘点实际数量 */
    private Integer actualNum;
    /** 是否进行过录入操作 **/
    private Integer inputed;

    /** 差异数，可正可负 */
    private Integer diff;

    private String inventoryTaskNo;
    /** 订单号 */
    private String inventoryNo;
    /** 初始化/盘点中/盘点完成 */
    private Integer taskStatus;
    /**  */
    private String taskOperator;
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

    private String productName;
    private String productUnit;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskOperator() {
        return taskOperator;
    }

    public void setTaskOperator(String taskOperator) {
        this.taskOperator = taskOperator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInventoryNo() {
        return inventoryNo;
    }

    public void setInventoryNo(String inventoryNo) {
        this.inventoryNo = inventoryNo;
    }

    public String getInventoryTaskNo() {
        return inventoryTaskNo;
    }

    public void setInventoryTaskNo(String inventoryTaskNo) {
        this.inventoryTaskNo = inventoryTaskNo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getRoadCode() {
        return roadCode;
    }

    public void setRoadCode(String roadCode) {
        this.roadCode = roadCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public Integer getLocationNum() {
        return locationNum;
    }

    public void setLocationNum(Integer locationNum) {
        this.locationNum = locationNum;
    }

    public Integer getActualNum() {
        return actualNum;
    }

    public void setActualNum(Integer actualNum) {
        this.actualNum = actualNum;
    }

    public Integer getInputed() {
        return inputed;
    }

    public void setInputed(Integer inputed) {
        this.inputed = inputed;
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

    public Integer getDiff() {
        return diff;
    }

    public void setDiff(Integer diff) {
        this.diff = diff;
    }
}
