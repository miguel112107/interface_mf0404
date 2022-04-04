package com.demo.toolrental.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
/**
 *
 * @author Miguel
 */

@Entity
@Table(name="Tooltype")
@NoArgsConstructor
public class Tooltype {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    Long id;
    @Column(name="tool_name")
    String toolName;
    @Column(name="daily_charge")
    double dailyCharge;
    @Column(name="weekday_charge")
    boolean weekdayCharge;
    @Column(name="weekend_charge")
    boolean weekendCharge;
    @Column(name="holiday_charge")
    boolean holidayCharge;

    public Tooltype(String name, double dailyCharge, boolean weekdayCharge, boolean weekendCharge,
            boolean holidayCharge) {
        this.toolName = name;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean getWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public boolean getWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public boolean getHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }
}
