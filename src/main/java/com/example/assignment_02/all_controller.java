/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assignment_02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author payal
 */
@Controller
public class all_controller {
    @RequestMapping("/")
    public String abc(){
        return "newjsp.jsp";
    }
}
