package com.example.splitwise.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(name= "groupss")
public class Group extends BaseModel {
     
    private String groupName; 
    @ManyToMany 
    private List<User>users;
    @ManyToOne
    private User admin;
    @OneToMany(fetch = FetchType.LAZY,
    mappedBy="group" , 
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Expense>expenses;


    /*   1            M
     * Group ------>Expense
     *  1             1
     */
    
}
