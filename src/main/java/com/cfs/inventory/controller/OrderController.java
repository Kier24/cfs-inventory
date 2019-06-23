package com.cfs.inventory.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cfs.inventory.model.*;
import com.cfs.inventory.service.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.dto.SaleDto;
import com.cfs.inventory.service.SaleApplicationService;

@Controller
@SessionAttributes("cartItems")
public class OrderController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleApplicationService saleApplication;

    @ModelAttribute("cartItems")
    public List<CartItem> getCartItems() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/orders")
    public ModelAndView getOrders() {

        List<Sale> saleList = saleRepository.findAll();

        return new ModelAndView("order", "orderList", saleList);

    }

    @GetMapping(value = "/order/receipt/{orderId}")
    public ModelAndView getReceipt(@PathVariable(name = "orderId") Long orderId) {
        Sale sale = saleRepository.getOne(orderId);

        return new ModelAndView("receipt", "order", sale);
    }

    @GetMapping(value = "/orders", params = "orderDate,status")
    public ModelAndView getOrdersByOrderDateAndStatus(@RequestParam(required = false) Date orderDate, @RequestParam(required = false) String status) {

        List<Sale> saleList = new ArrayList<>();
        if (orderDate == null && status == null) {
            saleList = saleRepository.findAll();
        } else {
            saleList = saleRepository.findOrderByOrderDate(orderDate.toLocalDate());
        }

        return new ModelAndView("order", "orderList", saleList);
    }

    @GetMapping(value = "/createOrder")
    public ModelAndView createOrder(@ModelAttribute("cartItems") List<CartItem> cartItems) {

        return new ModelAndView("createOrder", "productList", productRepository.findAll());

    }

    @GetMapping(value = "viewCart")
    public ModelAndView viewCartItems(@SessionAttribute("carItems") List<CartItem> cartItems) {
        ModelAndView mav = new ModelAndView("orderCart");
        mav.addObject("cartItems", cartItems);
        mav.addObject("totalAmount", "");
        return mav;
    }

    @PostMapping(value = "/orders/delete")
    public String deleteRawMaterial(@RequestParam(name = "deleteId") Long saleId) {

        saleRepository.deleteById(saleId);

        return "redirect:/orders";
    }

    @PostMapping(value = "/addCartItem")
    @ResponseBody
    public void addCartItem(@SessionAttribute("carItems") List<CartItem> cartItems, @RequestParam Long id
            , @RequestParam int quantity) {
        Product product = productRepository.getOne(id);
        CartItem cartItem = new CartItem(product, quantity);
        cartItems.add(cartItem);
    }

    @GetMapping(value = "/orders/{id}")
    @ResponseBody
    public SaleDto getOrder(@PathVariable Long id) {

        Sale sale = saleRepository.getOne(id);
        return new SaleDto(sale.getCustomerName(), sale.getDelivery(), sale.getItems());
    }

    @PostMapping(value = "/saveOrder")
    public String saveOrder(@RequestParam String customerName, @RequestParam String deliveredBy,
                            @RequestParam String deliveryAddress, @RequestParam Date deliveryDate
            , @SessionAttribute("carItems") List<CartItem> cartItems, SessionStatus sessionStatus) {
        Delivery delivery = new Delivery(deliveryAddress, deliveryDate.toLocalDate(), deliveredBy);
        saleApplication.createNewOrder(customerName, delivery, cartItems);
        sessionStatus.setComplete();
        return "redirect:/orders";
    }

    @PostMapping(value = "/updateStatus")
    public String changeOrderStatus(@RequestParam Long orderId, @RequestParam String newStatus) {
        Status status = Status.valueOf(newStatus);
        saleApplication.changeStatus(orderId, status);
        return "redirect:/orders";
    }

    @GetMapping(value = "/order/returnItem/{order.id}")
    public String returnItemFromOrder() {

        return "returnitem";
    }
}
