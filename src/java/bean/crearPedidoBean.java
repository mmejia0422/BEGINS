/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.EmpleadoDao;
import dao.EmpleadoDaoImpl;
import dao.OrdenVentaDao;
import dao.OrdenVentaDaoImpl;
import dao.OrdenVentaDetDao;
import dao.OrdenVentaDetDaoImpl;
import dao.estadoDocDao;
import dao.estadoDocDaoImpl;
import java.awt.event.ActionEvent;
import java.io.IOException;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;
import model.Cliente;
import model.DetaOrdenVenta;
import model.EstadoDocumentos;
import model.OrdenVenta;
import model.Usuario;
import org.omg.CORBA.Current;
import util.MyUtil;

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

    public double MontoLinea(int cantidad, byte precio) {
        double montoLinea;
        montoLinea = cantidad * precio;
        
        return montoLinea;
    }

    public double getTotalLineas() {
        double totalLineas = 0;
        for(int i = 0; this.listadoLineas.size() > i; i++){
            totalLineas = totalLineas + (this.listadoLineas.get(i).getCantidad() * this.listadoLineas.get(i).getPrecio());
        }
        return totalLineas;
    }
    
    public String clientePedido(Integer id){
        Cliente nombre;
        ClienteDao cDao = new ClienteDaoImpl();
        
        nombre = cDao.findClientebyId(id);
        
        return nombre.getNombre() + ' ' + nombre.getApellido();
    }
    
    public void confirmarPedido(OrdenVenta oVenta){
        OrdenVentaDao oDao = new OrdenVentaDaoImpl();
        String msg;

        if(oDao.confirmarPedido(oVenta)){
            
         msg = "Se guardo correctamente el pedido";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            
            
        } else {
            msg = "Error al crear el pedido";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    
}
