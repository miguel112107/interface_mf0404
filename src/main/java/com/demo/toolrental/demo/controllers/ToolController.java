package com.demo.toolrental.demo.controllers;

import java.util.List;

import com.demo.toolrental.demo.models.Tools;
import com.demo.toolrental.demo.models.Tooltype;
import com.demo.toolrental.demo.services.ToolService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ToolController{
    private final ToolService tools;

    public ToolController (ToolService tools){
        this.tools = tools;
    }

    @GetMapping("/tools")
    public List<Tools> getAllTools() throws Exception{
            return tools.getAllTools();
    }

    @GetMapping("/tooltypes")
    public List<Tooltype> getAllToolTypes() throws Exception{
            return tools.getAllToolTypes();
    }
}