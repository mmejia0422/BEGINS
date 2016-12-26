/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.OrdenVentaDao;
import dao.OrdenVentaDaoImpl;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.OrdenVenta;

/**
 *
 * @author Mario
 */
@ManagedBean(name="pedidoBean")
@ViewScoped
public class pedidoBean {

    private List<OrdenVenta> reportePedidos;
    
    public pedidoBean() {
    }

    public List<OrdenVenta> getReportePedidos() {
        OrdenVentaDao oDao = new OrdenVentaDaoImpl();
        this.reportePedidos = oDao.reportePedido();
        return reportePedidos;
    }
    
}
