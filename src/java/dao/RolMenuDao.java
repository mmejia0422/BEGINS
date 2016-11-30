/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.RolMenu;

/**
 *
 * @author Mario
 */
public interface RolMenuDao {
    public List<RolMenu> findByResp(Integer idResp);
    public List<RolMenu> findAll();
    public boolean create(RolMenu rolMenu);
    public boolean update(RolMenu rolMenu);
    public boolean delete(Integer id);
}
