/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.model.Departamento;

/**
 *
 * @author corre
 */
public interface DepartamentoDAO {
    Departamento get(String codigo);
    
}
