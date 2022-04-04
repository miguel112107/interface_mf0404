package com.demo.toolrental.demo.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Miguel
 */
@Getter
@Setter
@AllArgsConstructor
public class CheckoutRequest {
    String toolCode;
    int rentalDayCount;
    int discountPercent;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    LocalDate checkoutDate;
}
