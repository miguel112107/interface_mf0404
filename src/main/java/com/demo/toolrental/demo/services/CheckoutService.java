package com.demo.toolrental.demo.services;

import com.demo.toolrental.demo.models.RentalAgreement;
import com.demo.toolrental.demo.models.Tooltype;
import com.demo.toolrental.demo.models.Tools;
import com.demo.toolrental.demo.repositories.ToolRepository;
import com.demo.toolrental.demo.repositories.ToolTypeRepository;
import com.demo.toolrental.demo.utils.Holidays;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author Miguel
 */

 @Service
public final class CheckoutService {

    ZoneId defaultZoneId = ZoneId.systemDefault();
    Tooltype[] tooltype = new Tooltype[3];
    Tools[] tools = new Tools[4];
    private final ToolRepository toolRepository;
    private final ToolTypeRepository toolTypeRepository;

    public CheckoutService(ToolRepository toolRepository, ToolTypeRepository toolTypeRepository) {
        this.toolRepository = toolRepository;
        this.toolTypeRepository = toolTypeRepository;
    }

    // public ToolType initializeToolTypes(int i) {
    //     ToolType toolType;
    //     switch (i) {
    //         case 0:
    //             toolType = new ToolType("Ladder", 1.99, true, true, false);
    //             break;
    //         case 1:
    //             toolType = new ToolType("Chainsaw", 1.49, true, false, true);
    //             break;
    //         case 2:
    //             toolType = new ToolType("Jackhammer", 2.99, true, false, false);
    //             break;
    //         default:
    //             toolType = null;
    //             break;
    //     }
    //     return toolType;

    // }

    // public void initializeTool() {
    //     tools[0] = new Tools("CHNS", 1, "Stihl");
    //     tools[1] = new Tools("LADW", 0, "Werner");
    //     tools[2] = new Tools("JAKD", 2, "DeWalt");
    //     tools[3] = new Tools("JAKR", 2, "Ridgid");
    // }
    
    public RentalAgreement checkoutTool(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) throws Exception {
        Tools newTool = toolRepository.findToolByToolCode(toolCode).get(0);
        Tooltype newToolType = toolTypeRepository.getById(newTool.getToolType());
        return checkout(newToolType, newTool, rentalDayCount, discountPercent, checkoutDate);
    }

    /**
     * Checkout method used to gather all information about the rental and to create
     * agreement documentation for printing.
     * 
     * @param toolCode The code used to find the tool needed
     * @param rentalDayCount The amount of days customer will rent tool
     * @param discountPercent Discount entered by employee
     * @param checkoutDate The day the tool will be rented
     * @return RentalAgreement for reference to object if needed
     */
    public RentalAgreement checkout(Tooltype newToolType, Tools newTool, int rentalDayCount, int discountPercent, LocalDate checkoutDate) throws Exception {
       
        RentalAgreement agreement = new RentalAgreement();
        RentalAgreement tempAgreement = null;
        String error="";
        
            if (newTool != null) {
                if (rentalDayCount >= 1) {
                    agreement.setRentalDays(rentalDayCount);
                } else {
                    throw new Exception("Please enter rental agreement days larger than 1");
                }

                if (discountPercent >= 0 && discountPercent <= 100) {
                    agreement.setDiscountPercent(discountPercent);
                } else {
                    throw new Exception("Please enter discount percent between 0 and 100");
                }
                agreement.setToolCode(newTool.getToolCode());
                agreement.setToolType(newToolType.getToolName());
                agreement.setToolBrand(newTool.getBrand());
                agreement.setCheckOutDate(checkoutDate);
                agreement.setDueDate(getDueDate(checkoutDate, rentalDayCount));// need to TEST calc data
                agreement.setDailyRentalCharge(newToolType.getDailyCharge());
                agreement.setChargeDays(getChargeDays(checkoutDate, agreement.getDueDate(), newToolType, rentalDayCount));// need to TEST calc data
                agreement.setPreDiscountCharge(getPreDiscountCharge(agreement.getChargeDays(), agreement.getDailyRentalCharge())); //need to calc data
                agreement.setDiscountPercent(discountPercent);
                agreement.setDiscountAmount(getDiscountAmount(discountPercent, agreement.getPreDiscountCharge())); //need to calc data
                agreement.setFinalCharge(getFinalCharge(agreement.getPreDiscountCharge(), agreement.getDiscountAmount())); //need to calc data
                tempAgreement = agreement;
            } else {
                throw new Exception("System could not find tool. Please search again.");
            }

            return tempAgreement;
    }
    
    public void printAgreement(RentalAgreement agreement){
        agreement.printFinalValues();
    }

    /**
     * Searches tool array for matching tool using tool code
     *
     * @param toolCode The string to search for inside tool array. Tool code
     * determines which tool to return
     * @return Tool
     */
    public Tools getTool(String toolCode) {
        for (Tools tool : tools) {
            if (tool.getToolCode().equals(toolCode)) {
                Tools finalTool = new Tools(tool.getToolCode(), tool.getToolType(), tool.getBrand());
                return finalTool;
            }
        }
        return null;
    }

    /**
     * This method is used to get the due date based on the checkout date and
     * the rental days gathered at checkout
     *
     * @param checkoutDate The date entered as the tool checkout date
     * @param rentalDays The amount of days the tool will be rented
     * @return LocalDate Returns the date the tool should be returned
     */
    private LocalDate getDueDate(LocalDate checkoutDate, int rentalDays) throws ParseException {
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        return dueDate;
    }

    /**
     * This method is used to get the due date based on the checkout date and
     * the rental days gathered at checkout
     *
     * @param checkoutDate The date entered as the tool checkout date
     * @param dueDate The date in which the tool will be returned
     * @param toolType The type of tool rented by customer. This is used to determine
     *                 what days can be charged to the customer.
     * @param rentalDayCount The amount of days the tool will be rented. 
     * @return int Returns the amount of days to charge after determining holiday 
     *             weekend charges according to tool type
     */
    private int getChargeDays(LocalDate checkOutDate, LocalDate dueDate, Tooltype toolType, int rentalDayCount) {
        int weekends = 0; //weekend days within rental time range
        int holidays = 0; //holiday days within rental time range
        boolean observeIndependence;
        boolean observeLaborDay;
        int independenceDay;
        int laborDay;
        LocalDate compareDate;

        for (LocalDate date = checkOutDate; !date.isAfter(dueDate); date = date.plusDays(1)) {
            DayOfWeek day = date.getDayOfWeek();
            independenceDay = Holidays.IndependenceDayObserved(date);
            laborDay = Holidays.LaborDayObserved(date);
            compareDate = date;
            observeIndependence = independenceDay == compareDate.getDayOfMonth(); //Is 4th of July within rental period?
            observeLaborDay = laborDay == compareDate.getDayOfMonth(); // is Labor Day within rental period?

            if (day.getValue() > 5 && !toolType.getWeekendCharge()) {
                weekends++;
            }
            if (!toolType.getHolidayCharge()) {
                if (observeIndependence) {
                    holidays++; //add for Independence day
                }
                if (observeLaborDay) {
                    holidays++; //add for Labor Day
                }

            }
        }
        return (rentalDayCount - (weekends + holidays)); //subtract holidays and weekends not charged from rental day total
    }

    /**
     * This method is used to find the amount charged for the tool rental before a 
     * discount is applied.
     * 
     * @param chargeDays The number of days customer will be charged
     * @param dailyCharge The amount of money charged daily for tool rental
     * @return double THe amount charged to customer before discounts are applied
     */
    private double getPreDiscountCharge(int chargeDays, double dailyCharge) {
        return Math.ceil((chargeDays * dailyCharge) * 100.0) / 100.0;
    }

    /**
     * This method is used to determine what the discount will be after discount
     * is applied by employee
     * 
     * @param discountPercent The percent value for the discount applied 
     * @param preDiscountCharge The original price for the rental
     * @return double The discount amount after calculations
     */
    private double getDiscountAmount(double discountPercent, double preDiscountCharge) {
        double discountDec = discountPercent / 100;
        return Math.ceil((discountDec * preDiscountCharge) * 100.0) / 100.0;
    }

    /**
     * This method is used to get a final charge amount after all discounts 
     * are calculated
     * 
     * @param preDiscountCharge The original charge before discounts are applied
     * @param discountAmount The discount previously calculated
     * @return double Returns the final charge for the entire rental
     */
    private double getFinalCharge(double preDiscountCharge, double discountAmount) {
        return Math.ceil((preDiscountCharge - discountAmount) * 100.0) / 100.0;
    }
}
