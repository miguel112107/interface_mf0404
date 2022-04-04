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
@Table(name="Tools")
@NoArgsConstructor
public class Tools {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="tool_code")
    private String toolCode;

    @Column(name="tool_type_id")
    private Long toolTypeId;

    @Column(name="brand")
    String brand;
    
    public Tools(String toolCode, Long toolTypeId, String brand){
        this.toolCode = toolCode;
        this.toolTypeId = toolTypeId;
        this.brand = brand;
    }

    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getToolCode(){
        return toolCode;
    }
    
    public void setToolCode(String newToolCode){
        this.toolCode = newToolCode;
    }
    
    public Long getToolType(){
        return toolTypeId;
    }
    
    public void setToolType(Long toolTypeId){
        this.toolTypeId = toolTypeId;
    }
    
    public String getBrand(){
        return brand;
    }
    
    public void setBrand(String newBrand){
        this.brand = newBrand;
    }
}
