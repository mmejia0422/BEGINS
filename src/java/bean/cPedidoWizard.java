/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.awt.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Mario
 */
@ManagedBean(name="cPedidoWizard")
@ViewScoped
public class cPedidoWizard {

    private boolean skip;
    
    public cPedidoWizard() {
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

     public void save() {        
        FacesMessage msg = new FacesMessage("Successful", "Welcome");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user need to create a new order
            return "general";
        }
        else {
            return event.getNewStep();
        }
    }

}
