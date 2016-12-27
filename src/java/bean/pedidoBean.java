/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.OrdenVentaDao;
import dao.OrdenVentaDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.OrdenVenta;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Mario
 */
@ManagedBean(name="pedidoBean")
@ViewScoped
public class pedidoBean {

    private List<OrdenVenta> reportePedidos;
    private OrdenVenta filterOrden;
    private OrdenVenta selectedOrden;
    
    public pedidoBean() {
        this.filterOrden = new OrdenVenta();
        this.reportePedidos = new ArrayList<OrdenVenta>();
        this.selectedOrden = new OrdenVenta();
    }

    public List<OrdenVenta> getReportePedidos() {
        OrdenVentaDao oDao = new OrdenVentaDaoImpl();
        /*Integer param1 = this.filterOrden.getEstadoDocumentos().getIdestadoDocumentos();
        Integer param2 = this.filterOrden.getCliente().getIdCliente();
        String param3 = this.filterOrden.getPrioridadAlta();*/
        this.reportePedidos = oDao.reportePedido(/*param1,param2,param3*/);
        return reportePedidos;
    }

    public OrdenVenta getFilterOrden() {
        return filterOrden;
    }

    public void setFilterOrden(OrdenVenta filterOrden) {
        this.filterOrden = filterOrden;
    }

    public OrdenVenta getSelectedOrden() {
        return selectedOrden;
    }

    public void setSelectedOrden(OrdenVenta selectedOrden) {
        this.selectedOrden = selectedOrden;
    }

     public void onRowSelect(SelectEvent event) {
        this.selectedOrden = ((OrdenVenta)event.getObject());
        FacesMessage msg = new FacesMessage("Car Selected", ((OrdenVenta) event.getObject()).getIdOrdenVenta().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((OrdenVenta) event.getObject()).getIdOrdenVenta().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
