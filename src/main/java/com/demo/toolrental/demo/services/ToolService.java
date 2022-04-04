package com.demo.toolrental.demo.services;

import java.util.List;

import com.demo.toolrental.demo.models.Tooltype;
import com.demo.toolrental.demo.models.Tools;
import com.demo.toolrental.demo.repositories.ToolRepository;
import com.demo.toolrental.demo.repositories.ToolTypeRepository;

import org.springframework.stereotype.Service;

@Service
public class ToolService {
    private final ToolRepository toolRepository;
    private final ToolTypeRepository toolTypeRepository;

    public ToolService(ToolRepository toolRepository, ToolTypeRepository toolTypeRepository) {
        this.toolRepository = toolRepository;
        this.toolTypeRepository = toolTypeRepository;
    }

    public List<Tools> getAllTools() {
        return toolRepository.findAll();
    }

    public List<Tooltype> getAllToolTypes() {
        return toolTypeRepository.findAll();
    }

    public Tooltype getToolTypeById(Long id){
        return toolTypeRepository.getById(id);
    }
}