package org.gsu.pms.dao.impl;

import org.gsu.pms.dao.OrderDAO;
import org.gsu.pms.dao.ProductDAO;
import org.gsu.pms.entity.Order;
import org.gsu.pms.entity.OrderDetail;
import org.gsu.pms.entity.Product;
import org.gsu.pms.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

//Transactional for Hibernate
@Transactional
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ProductDAO productDAO;

    private int getMaxOrderNum() {
        String sql = "Select max(o.orderNum) from org.gsu.pms.entity.Order" + " o ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();
        if (value == null) {
            return 0;
        }
        return value;
    }

    @Override
    public void saveOrder(CartInfo cartInfo) {
        Session session = sessionFactory.getCurrentSession();

        int orderNum = this.getMaxOrderNum() + 1;
        Order order = new Order();

        order.setId(UUID.randomUUID().toString());
        order.setOrderNum(orderNum);
        order.setOrderDate(new Date());
        order.setAmount(cartInfo.getAmountTotal());

        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        order.setCustomerName(customerInfo.getName());

        session.persist(order);

        List<CartLineInfo> lines = cartInfo.getCartLines();

        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            detail.setId(UUID.randomUUID().toString());
            detail.setOrder(order);
            detail.setAmount(line.getAmount());
            detail.setPrice(line.getProductInfo().getPrice());
            detail.setQuanity(line.getQuantity());

            String code = line.getProductInfo().getCode();
            Product product = this.productDAO.findProduct(code);
            detail.setProduct(product);

            session.persist(detail);
        }

        // Set OrderNum for report.
        cartInfo.setOrderNum(orderNum);
    }

    // @page = 1, 2, ...
    @Override
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new org.gsu.pms.model.OrderInfo"
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName) "
                + " from org.gsu.pms.entity.Order" + " ord "
                + " order by ord.orderNum desc";
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);

        return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    }

    public Order findOrder(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("id", orderId));
        return (Order) crit.uniqueResult();
    }

    @Override
    public OrderInfo getOrderInfo(String orderId) {
        Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getId(), order.getOrderDate(),
                order.getOrderNum(), order.getAmount(), order.getCustomerName());
    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        String sql = "Select new org.gsu.pms.model.OrderDetailInfo"
                + "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) "
                + " from org.gsu.pms.entity.OrderDetail" + " d "
                + " where d.order.id = :orderId ";

        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);

        return query.list();
    }

    public List<OrderDetail> listOrderDetails(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(OrderDetail.class);
        crit.add(Restrictions.eq("order",findOrder(orderId)));
        return crit.list();
    }

    @Override
    public void closeOrder(String orderId) {
        if (orderId != null) {
            List<OrderDetail> listOrderDetails = listOrderDetails(orderId);
            for (OrderDetail orderDetails : listOrderDetails) {
                sessionFactory.getCurrentSession().delete(orderDetails);
            }
            Order order = findOrder(orderId);
            sessionFactory.getCurrentSession().delete(order);
        }
    }
}
