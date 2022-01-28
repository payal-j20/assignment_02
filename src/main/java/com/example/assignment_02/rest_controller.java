/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assignment_02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shiv
 */
@RestController
@Validated

public class rest_controller {
    @RequestMapping(method=POST,value="/add",produces = MediaType.APPLICATION_JSON_VALUE,consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String add( @RequestBody @Valid training_center j, BindingResult r) {
        
        System.out.println("values : "+j.course_offered.get(0));
        db ob=new db();
        Connection con;
        try {
            con = ob.init();
            PreparedStatement t_c = con.prepareStatement("insert into training_center values(?,?,?,?,?,?)");
        PreparedStatement a_d = con.prepareStatement("insert into address values(?,?,?,?,?)");
        PreparedStatement c2 = con.prepareStatement("insert into course_offered values(?,?)");
        
        t_c.setString(1,j.getCentername());
        t_c.setString(2, j.getCentercode());
        t_c.setInt(3, j.getStudent_capacity());
        LocalDate c_date = LocalDate.now();
        Date sq_date=Date.valueOf(c_date);
        System.out.println(sq_date);
        t_c.setDate(4, sq_date);
        t_c.setString(5, j.getContact_email());
        t_c.setString(6,j.getContact_phone());
        t_c.executeUpdate();
        a_d.setString(1, j.address.getDetailed_address());
        a_d.setString(2, j.address.getCity());
        a_d.setString(3, j.address.getState());
        a_d.setInt(4, j.address.getPincode());
        a_d.setString(5, j.getCentercode());
        a_d.executeUpdate();
        t_c.close();
        
        a_d.close();
        for(int i=0;i<j.course_offered.size();i++){
            System.out.println("i :"+i);
            System.out.println(j.course_offered.get(i));
            
            PreparedStatement c_o = con.prepareStatement("insert into course_offered values(?,?)");
            c_o.setString(1,j.course_offered.get(i));
            c_o.setString(2,j.getCentercode());
            c_o.executeUpdate();
        }
        
        } catch (ClassNotFoundException ex) {
            System.out.println("exceptions : "+ex);
        } 
        catch(SQLIntegrityConstraintViolationException ex){
            
            System.out.println("catched : "+ex.getMessage());
            System.out.println("catched : "+ex.getErrorCode());
            if(ex.getErrorCode()==30000){
                return HttpStatus.BAD_REQUEST+":"+"Center code already exist";
            }
            
        }
           catch (SQLException ex) {
               System.out.println("Exception "+ex);
           }
        
        
        JSONObject er=new JSONObject(j);
        return er.toString();
        
    }
    @ExceptionHandler(SQLException.class)
    public void abd(SQLException e){
        System.out.println("exceptin"+e);
    }
     @ExceptionHandler(ConstraintViolationException.class)
     
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    String handle2ConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> v = e.getConstraintViolations();
        JSONObject er=new JSONObject();
        for (ConstraintViolation<?> v1 : v) {
            er.put(""+v1.getPropertyPath().toString().substring(v1.getPropertyPath().toString().lastIndexOf(".")+1), v1.getMessage());
        }
        
        System.out.println(er.toString());
        return er.toString();
    
  }
    @RequestMapping(method=GET,value="/getcenters",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCenters(){
        String center_code="";
        Map <Integer,training_center> t_c=new HashMap();
        int i=1;
        db a=new db();
        Connection con;
        try {
            con = a.init();
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from training_center");
            
            while(rs.next()){
               
                training_center ob=new training_center();
                ob.centername=rs.getString(1);
                ob.centercode=rs.getString(2);
                center_code=rs.getString(2);
                ob.student_capacity=rs.getInt(3);
                ob.contact_email=rs.getString(5);
                ob.contact_phone=rs.getString(6);
                
                Statement stmt2 = con.createStatement();  
                ResultSet rs2 = stmt2.executeQuery("select * from address where center_code = '"+center_code+"'");
                //System.out.println("query +"+rs2);
                while(rs2.next()){
                    ob.address.setDetailed_address(rs2.getString(1));
                    System.out.println("query : "+rs2.getString(1));
                    ob.address.setCity(rs2.getString(2));
                    ob.address.setState(rs2.getString(3));
                    ob.address.setPincode(rs2.getInt(4));

                }
                Statement stmt3 = con.createStatement();  
                ResultSet rs3 = stmt3.executeQuery("select * from course_offered where center_code='"+center_code+"'");
                while(rs3.next()){
                    ob.course_offered.add(rs3.getString(1));
                }
                t_c.put(i,ob);
                i++;
            
            
        }
            /*
           */
         
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(rest_controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(rest_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject all_centers=new JSONObject(t_c);
        System.out.print(": "+t_c.toString().replaceAll("\\[\\]", ""));
        return all_centers.toString();
    }
}
