/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assignment_02;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author shiv
 */
//@Validated
public class training_center {
    
    @Valid
    Address address=new Address();
    
    @Size(min=2,max=40,message="Maximum characters allowed in CenterName is 40 ")
    @NotBlank(message="CenterName can't be blank")
    String centername;
    
    @Size(min=12,max=12,message="Enter exact 12 characters in CenterCode ")
    String centercode;
    
    @Positive(message="Enter valid student_capacity")
    int student_capacity;
    
    
    
    @Size(min=1,max=20,message="Enter atleast 1 course name")
    List<String> course_offered=new ArrayList();

    
    
    @Email
    String contact_email;
    
    
    @NotBlank(message="Enter valid phone number")
    @Pattern(regexp="(^$|[0-9]{10})",message="Enter valid phone number")
    String contact_phone ;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCentername() {
        return centername;
    }

    public void setCentername(String centername) {
        this.centername = centername;
    }

    public String getCentercode() {
        return centercode;
    }

    public void setCentercode(String centercode) {
        this.centercode = centercode;
    }

    public int getStudent_capacity() {
        return student_capacity;
    }

    public void setStudent_capacity(int student_capacity) {
        this.student_capacity = student_capacity;
    }

    

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }
    public List<String> getCourse_offered() {
        return course_offered;
    }

    public void setCourse_offered(List<String> course_offered) {
        this.course_offered = course_offered;
    }

    @Override
    public String toString() {
        return "training_center{" + "address=" + address + ", centername=" + centername + ", centercode=" + centercode + ", student_capacity=" + student_capacity + ", course_offered=" + course_offered + ", contact_email=" + contact_email + ", contact_phone=" + contact_phone + '}';
    }

   

    
    
    
    
}
