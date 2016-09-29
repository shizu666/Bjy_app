package app.wms.empty;

import java.util.Date;

/**
 * Created by zhou on 2016/9/29.
 */

public class StockInventoryTaskResquest {

    /** ID */
    private Long id;
    /**  */
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

    private String warehouseCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInventoryTaskNo() {
        return inventoryTaskNo;
    }

    public void setInventoryTaskNo(String inventoryTaskNo) {
        this.inventoryTaskNo = inventoryTaskNo;
    }

    public String getInventoryNo() {
        return inventoryNo;
    }

    public void setInventoryNo(String inventoryNo) {
        this.inventoryNo = inventoryNo;
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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
}
