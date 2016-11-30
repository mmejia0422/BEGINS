/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import model.DetaOrdenVenta;
import model.OrdenVenta;

/**
 *
 * @author Mario
 */
@ManagedBean(name="crearPedidoBean")
@ViewScoped
public class crearPedidoBean {
    
    private OrdenVenta selectedCabecera;
    private DetaOrdenVenta selectedPedido;
    
    @PostConstruct
    public void init() {
        this.selectedPedido = new DetaOrdenVenta();
        this.selectedCabecera = new OrdenVenta();
    }
    
    public crearPedidoBean() {
        this.selectedPedido = new DetaOrdenVenta();
        this.selectedCabecera = new OrdenVenta();
    }

    public DetaOrdenVenta getSelectedPedido() {
        return selectedPedido;
    }

    public void setSelectedPedido(DetaOrdenVenta selectedPedido) {
        this.selectedPedido = selectedPedido;
    }

    public OrdenVenta getSelectedCabecera() {
        return selectedCabecera;
    }

    public void setSelectedCabecera(OrdenVenta selectedCabecera) {
        this.selectedCabecera = selectedCabecera;
    }
    
    public void productoCambiaValor(ValueChangeEvent e) {
    Object newValue = e.getNewValue();
    FacesContext.getCurrentInstance().renderResponse();
}
    
}
