package com.example.splitwise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.splitwise.dto.SettleUPGroupReq;
import com.example.splitwise.dto.SettleUpGroupRes;
import com.example.splitwise.dto.SettleupUserReq;
import com.example.splitwise.dto.settleUpUserRes;
import com.example.splitwise.services.settleupServices;

@Controller
public class SettleupContoller {
    @Autowired
    settleupServices settleupServices;



    public settleUpUserRes getSettleupUser(SettleupUserReq req) {
        settleUpUserRes res = new settleUpUserRes();
        try {
            res.setTranscations(settleupServices.settleupUser(req.getUserId()));
            res.setStatus(com.example.splitwise.dto.ResponseStatus.SUCCESS);
        } catch (Exception e) {
            res.setStatus(com.example.splitwise.dto.ResponseStatus.FAILURE);
        }   

        return res;
    } 
    
    public SettleUpGroupRes getSettleupGroup(SettleUPGroupReq req) {
        SettleUpGroupRes res = new SettleUpGroupRes();
        try {
            res.setTranscations(settleupServices.settleupGroup(req.getGroupId()));
            res.setStatus(com.example.splitwise.dto.ResponseStatus.SUCCESS);
        } catch (Exception e) {
            res.setStatus(com.example.splitwise.dto.ResponseStatus.FAILURE);
        }   

        return res;
    }
}
