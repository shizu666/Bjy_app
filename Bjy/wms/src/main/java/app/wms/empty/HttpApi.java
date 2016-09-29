package app.wms.empty;

/**
 * Created by zhou on 2016/9/22.
 */

public class HttpApi {
    //IP地址
    public static final String Ip = "http://192.168.9.130:8080/";

    //仓库号
    public static final String code = "sh001";

    //接口请求头
    public static final String requestHead = "wms-web/services/";
    //requset 仓库号
    public static final String baseWarehouseCode = "?baseWarehouseCode=";
    //某仓库内承运商信息
    public static final String  parems = "carrier/getCarrierList/";
    //根据入库抽检采购单号获取信息
    public static final String getchecklistbyorderno = "inboundcheck/getchecklistbyorderno/";

    //根据采购单号查询商品信息
    public static final String getPrePurchaseProduct ="inbound/getPrePurchaseProduct/";
    //保存采购商品上架信息
    public static final String addInbound = "inbound/addInbound";

    //调拨上架订单查询
    public static final String getonshelflistbyorderno = "inboundonshelf/getonshelflistbyorderno/";
    //保存调拨上架商品信息
    public static final String saveinboundonshelf = "inboundonshelf/saveinboundonshelf";

    //拣货下架订单查询
    public static final String getOrderInfo = "offshelf/getOrderInfo/";
    //保存拣货下架商品信息
    public static final String doSubmit = "offshelf/doSubmit";

    //货位查询商品
    public static final String getStockLocation = "stockLocation/getStockLocation";

    //跨区移库任务信息
    public static final String checkMoveTask = "moveTask/checkMoveTask";
    //跨区移库上架
    public static final String  diffMoveStockLocationOn = "stockLocation/diffMoveStockLocationOn";
    //跨区移库下架
    public static final String diffMoveStockLocationOff = "stockLocation/diffMoveStockLocationOff";

    //补货任务信息
    public static final String checkAndGetMoveTask = "moveTask/checkAndGetMoveTask";
    //补货下架
    public static final String doReplenishOff = "stockLocation/doReplenishOff";
    //补货上架
    public static final String doReplenishOn = "stockLocation/doReplenishOn";

    //盘点任务获取
    public static final String getInventoryTask = "inventory/getInventoryTask";
    //盘点入库
    public static final String addInventoryResult = "inventory/addInventoryResult";



    //查询商品信息
    public static final String getAllProducts = "product/getAllProducts";






}
