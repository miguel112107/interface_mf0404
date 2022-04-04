package com.demo.toolrental.demo.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;

/**
 *
 * @author Miguel
 */

public class RentalAgreement {
    String toolCode;
    String toolType;
    String toolBrand;
    int rentalDays;
    LocalDate checkOutDate;
    LocalDate dueDate;
    double dailyRentalCharge;
    int chargeDays;
    double preDiscountCharge;
    double discountPercent;
    double discountAmount;
    double finalCharge;
    

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(double preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(double finalCharge) {
        this.finalCharge = finalCharge;
    }
    
    /**
     * Method used to print all values for the user agreement
     */
    public void printFinalValues(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println("Tool code: "+ getToolCode());
        System.out.println("Tool type: "+ getToolType());
        System.out.println("Tool brand: "+ getToolBrand());
        System.out.println("Rental days: "+ getRentalDays());
        System.out.println("Check out date: "+ formatter.format(getCheckOutDate()));
        System.out.println("Due date: "+ formatter.format(getDueDate()));
        System.out.println("Daily rental charge: $"+ String.format("%.2f",getDailyRentalCharge()));
        System.out.println("Charge days: "+ getChargeDays());
        System.out.println("Pre-discount charge: $"+ String.format("%.2f",getPreDiscountCharge()));
        System.out.println("Discount percent: "+ getDiscountPercent()+"%");
        System.out.println("Discount amount: $"+ String.format("%.2f",getDiscountAmount()));
        System.out.println("Final Charge: $"+ String.format("%.2f", getFinalCharge()));
        System.out.println("--------------------------------------------");
    }
}
