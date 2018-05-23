/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismail.repository.services;

import com.ismail.repository.MotorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eli
 */
@Service
public class MotorService {
    
    @Autowired
    MotorRepo mrepo;
     
    
}
