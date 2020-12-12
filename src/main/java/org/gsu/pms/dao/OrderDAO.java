package org.gsu.pms.dao;

import org.gsu.pms.model.CartInfo;
import org.gsu.pms.model.OrderDetailInfo;
import org.gsu.pms.model.OrderInfo;
import org.gsu.pms.model.PaginationResult;

import java.util.List;

public interface OrderDAO {

    void saveOrder(CartInfo cartInfo);

    PaginationResult<OrderInfo> listOrderInfo(int page,
                                              int maxResult, int maxNavigationPage);

    OrderInfo getOrderInfo(String orderId);

    List<OrderDetailInfo> listOrderDetailInfos(String orderId);

    void closeOrder(String orderId);

}
