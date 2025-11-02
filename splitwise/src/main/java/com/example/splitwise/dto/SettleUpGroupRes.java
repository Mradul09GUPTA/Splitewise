package com.example.splitwise.dto;

import java.util.List;

import com.example.splitwise.models.Transcation;

import lombok.Setter;
@Setter
public class SettleUpGroupRes {
    List<Transcation> transcations;
    ResponseStatus status;      
}
