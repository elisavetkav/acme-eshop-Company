package com.codehub.acme.eshop.service;

import com.codehub.acme.eshop.domain.*;
import com.codehub.acme.eshop.enumerator.OrderStatus;
import com.codehub.acme.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * This service contains the implementation of methods regarding the {@link UserOrder} functionality
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * {@link OrderRepository}
     */
    @Autowired
    private OrderRepository orderRepository;

    /**
     * {@link BillingDetailsService}
     */
    @Autowired
    private BillingDetailsService billingDetailsService;

    /**
     * {@link ProductService}
     */
    @Autowired
    private ProductService productService;

    /**
     * {@link ShoppingBasketService}
     */
    @Autowired
    private ShoppingBasketService shoppingBasketService;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserOrder submitOrder(UserOrder order/*, User user*/) {
        BillingDetails billingDetails = billingDetailsService.addBillingDetails(order.getBillingDetails());
        order = orderRepository.save(new UserOrder(new Date(), billingDetails, OrderStatus.PENDING, order.getProductItems(), order.getUser()));
        for (ProductItem productItem : order.getProductItems()) {
            productItem = productService.getProductItem(productItem);
            Product product = productService.findProductById(productItem.getProduct().getId());
            ProductStock productStock = product.getProductStock();
            productStock.setStock(productStock.getStock() - productItem.getQuantity());
            productService.checkAvailability(productStock);
            productItem.setOrder(order);
            productService.updateProductItem(productItem);
        }
        //shoppingBasketService.delete(shoppingBasketService.findByUserId(order.getUser().getId()));
        return order;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelOrder(Long orderId) {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public UserOrder findOrderById(Long orderId) {
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public UserOrder findOrderByUserId(Long userId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public UserOrder saveOrder(UserOrder userOrder) {
        return orderRepository.save(userOrder);
    }
}