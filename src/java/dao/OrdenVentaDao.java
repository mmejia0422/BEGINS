/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetaOrdenVenta;
import model.OrdenVenta;

/**
 *
 * @author Mario
 */
public interface OrdenVentaDao {

    public boolean create(OrdenVenta venta, boolean flag);

    public boolean update(OrdenVenta venta);

    public boolean delete(Integer id);

    public boolean confirmarPedido(OrdenVenta oVenta);
    
    public List<OrdenVenta> reportePedido(/*Integer param1, Integer param2, String param3*/); 

}
