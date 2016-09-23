package app.wms.empty;

/**
 * Created by zhou on 2016/9/22.
 */

public class HttpApi {

    //接口请求头
    public static final String requestHead = "wms-web/services/";
    //requset 仓库号
    public static final String baseWarehouseCode = "?baseWarehouseCode=";
    //某仓库内承运商信息
    public static final String  parems = "carrier/getCarrierList/";
    //根据采购单号获取信息
    public static final String getchecklistbyorderno = "inboundcheck/getchecklistbyorderno/";



}
