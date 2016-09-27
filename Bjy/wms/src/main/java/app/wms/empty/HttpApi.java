package app.wms.empty;

/**
 * Created by zhou on 2016/9/22.
 */

public class HttpApi {
    //IP地址
    public static final String Ip = "http://192.168.10.100:8080/";

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
    //保存采购商品信息
    public static final String addInbound = "inbound/addInbound";

    //查询商品信息
    public static final String getAllProducts = "product/getAllProducts";






}
