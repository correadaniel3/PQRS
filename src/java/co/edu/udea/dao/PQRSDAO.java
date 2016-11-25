/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.model.Pqrs;
import java.util.List;

/**
 *
 * @author wondercode
 */
public interface PQRSDAO {
    void addPqrs(Pqrs seller);      
    void editPqrs(Pqrs seller);     
    void deletePqrs(String id);              
    Pqrs getPqrs(String id);           
    List <Pqrs> getAllPqrs();  
}
