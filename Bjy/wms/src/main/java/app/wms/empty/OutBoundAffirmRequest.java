package app.wms.empty;

/**
 * Created by zhou on 2016/10/9.
 */

public class OutBoundAffirmRequest {

    private String orderNo;//订单号
    private String carrierNo;//承运商号
    private String baseWarehouseCode;

    public String getBaseWarehouseCode() {
        return baseWarehouseCode;
    }

    public void setBaseWarehouseCode(String baseWarehouseCode) {
        this.baseWarehouseCode = baseWarehouseCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCarrierNo() {
        return carrierNo;
    }

    public void setCarrierNo(String carrierNo) {
        this.carrierNo = carrierNo;
    }
}
