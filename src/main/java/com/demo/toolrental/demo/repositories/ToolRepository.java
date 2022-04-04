package com.demo.toolrental.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import com.demo.toolrental.demo.models.Tools;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ToolRepository extends JpaRepository<Tools, Long> {
    @Query("SELECT t FROM Tools t where t.toolCode = tool_code")
    public ArrayList<Tools> findToolByToolCode(@Param("tool_code") String toolCode);
}