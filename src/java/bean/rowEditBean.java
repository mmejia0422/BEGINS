/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Mario
 */
@ManagedBean(name="rowEditBean")
@ViewScoped
public class rowEditBean implements Serializable{

    public rowEditBean() {
    }
    
    public void onRowEdit(RowEditEvent event){
        FacesMessage msg = new FacesMessage("Registro editado", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent event){
        FacesMessage msg = new FacesMessage("Registro cancelado", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
