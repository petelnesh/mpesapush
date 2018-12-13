/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpesapush.repository;

import com.mpesapush.model.STKCallback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author techsavanna
 */
 @Repository
public interface MpesaCallBack extends JpaRepository<STKCallback,Long>{
    
}
