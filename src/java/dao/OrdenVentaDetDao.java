/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetaOrdenVenta;

/**
 *
 * @author Mario
 */
public interface OrdenVentaDetDao {
    public boolean create(DetaOrdenVenta detVenta);
    public boolean update(DetaOrdenVenta detVenta);
    public boolean delete(Integer id);
    public List<DetaOrdenVenta> lineasPedido(Integer idCabecera);
    
}
