package com.demo.toolrental.demo.repositories;

import com.demo.toolrental.demo.models.Tooltype;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolTypeRepository extends JpaRepository<Tooltype, Long> {

}