package com.example.splitwise.models;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@MappedSuperclass

public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private Date createdby;
     private Date LastUpdateBy;


}
