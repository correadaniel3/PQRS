/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.model.Personal;
import co.edu.udea.model.PersonalPK;

/**
 *
 * @author corre
 */
public interface PersonalDAO {
    Personal get(PersonalPK pk);
}
