/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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

}
