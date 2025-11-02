package com.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="transcations")
public class Transcation  extends BaseModel {
     @ManyToOne
    private User fromUser;
    @ManyToOne
    private User toUser;
    private Long amount;




    /*
     *     1                 1
     * Transcation  ------> User (fromUser)  (Many to One)
     *    M                  1
     */
}
