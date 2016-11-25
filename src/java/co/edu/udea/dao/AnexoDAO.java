/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.model.Anexo;
import co.edu.udea.model.AnexoPK;
import java.util.List;

/**
 *
 * @author wondercode
 */
public interface AnexoDAO {
    Anexo get(AnexoPK id);
    List<Anexo> getAll();
    void save(Anexo a);
}
