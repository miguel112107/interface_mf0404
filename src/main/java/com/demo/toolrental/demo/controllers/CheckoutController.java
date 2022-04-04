package com.demo.toolrental.demo.controllers;

import com.demo.toolrental.demo.models.CheckoutRequest;
import com.demo.toolrental.demo.models.RentalAgreement;
import com.demo.toolrental.demo.services.CheckoutService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CheckoutController{
    private final CheckoutService checkout;

    public CheckoutController (CheckoutService checkout){
        this.checkout = checkout;
    }

    @PostMapping("checkout")
    public RentalAgreement checkoutTool(@RequestBody CheckoutRequest checkoutRequest) throws Exception{
            return checkout.checkoutTool(checkoutRequest.getToolCode(), checkoutRequest.getRentalDayCount(), checkoutRequest.getDiscountPercent(), checkoutRequest.getCheckoutDate());
    }
}