/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismail.repository;

import com.ismail.models.OtherInsurances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eli
 */     
@Repository
public interface OthersRepo extends JpaRepository<OtherInsurances, Long> {
    
}
