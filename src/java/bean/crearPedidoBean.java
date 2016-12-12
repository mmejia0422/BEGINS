/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.EmpleadoDao;
import dao.EmpleadoDaoImpl;
import dao.OrdenVentaDao;
import dao.OrdenVentaDaoImpl;
import dao.OrdenVentaDetDao;
import dao.OrdenVentaDetDaoImpl;
import dao.estadoDocDao;
import dao.estadoDocDaoImpl;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.swing.text.html.HTML;
import model.DetaOrdenVenta;
import model.EstadoDocumentos;
import model.OrdenVenta;
import model.Usuario;
import org.omg.CORBA.Current;

/**
 *
 * @author Mario
 */
@ManagedBean(name="crearPedidoBean")
@ViewScoped
public class crearPedidoBean {
    
    private OrdenVenta selectedCabecera;
    private DetaOrdenVenta selectedPedido;
    boolean hasHeader = false;
    private boolean hasLines = false;
    private List<DetaOrdenVenta> listadoLineas;
    
    @PostConstruct
    public void init() {
        this.selectedPedido = new DetaOrdenVenta();
        this.selectedCabecera = new OrdenVenta();
    }
    
    public crearPedidoBean() {
        this.selectedPedido = new DetaOrdenVenta();
        this.selectedCabecera = new OrdenVenta();
        this.listadoLineas = new ArrayList<DetaOrdenVenta>();
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
    
     public void btnCreate(ActionEvent actionEvent, Usuario us) {
        OrdenVentaDao oVenta = new OrdenVentaDaoImpl();
        OrdenVentaDetDao oDetVenta = new OrdenVentaDetDaoImpl();
        estadoDocDao edDao = new estadoDocDaoImpl();
        String msg;
        java.sql.Date fechaSql = (java.sql.Date.valueOf(LocalDate.now()));
        
        this.selectedCabecera.setEmpleado(us.getEmpleado());
        this.selectedCabecera.setEstadoDocumentos(edDao.nuevoDoc());
        this.selectedCabecera.setFecha(fechaSql);

        if (oVenta.create(this.selectedCabecera, this.hasHeader)) {
            this.selectedPedido.setOrdenVenta(selectedCabecera);
              if(oDetVenta.create(this.selectedPedido)){
                  //this.selectedPedido = null; //limpiamos la variable
              }
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            
            this.hasHeader = true;
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

     //detalle de lineas de cabecera pendiente de registrar
    public List<DetaOrdenVenta> getListadoLineas() {
        if(this.hasLines){
        OrdenVentaDetDao oDao = new OrdenVentaDetDaoImpl();
        this.listadoLineas = oDao.lineasPedido(this.selectedCabecera.getIdOrdenVenta());
        }
        return listadoLineas;
    }

    public void setHasLines(boolean hasLines) {
        this.hasLines = hasLines;
    }
    
    
    
}
