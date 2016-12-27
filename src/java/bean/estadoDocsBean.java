/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.estadoDocDao;
import dao.estadoDocDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import model.EstadoDocumentos;

/**
 *
 * @author Mario
 */
@ManagedBean(name="estadoDocsBean")
@RequestScoped
public class estadoDocsBean {
    
private List<SelectItem> selectOneItemsEstado;
private List<SelectItem> filterEstado;

    public estadoDocsBean() {
    }

    public List<SelectItem> getSelectOneItemsEstado() {
        this.selectOneItemsEstado = new ArrayList<SelectItem>();
        estadoDocDao eDao = new estadoDocDaoImpl();
        List<EstadoDocumentos> estados = eDao.selectItems();
        for (EstadoDocumentos e : estados) {
            SelectItem selectItem = new SelectItem(e.getIdestadoDocumentos(), e.getEstado());
            this.selectOneItemsEstado.add(selectItem);
        }
        return selectOneItemsEstado;
    }

    public List<SelectItem> getFilterEstado() {
        this.filterEstado = new ArrayList<SelectItem>();
        estadoDocDao eDao = new estadoDocDaoImpl();
        List<EstadoDocumentos> estados = eDao.selectItems();
        for (EstadoDocumentos e : estados) {
            SelectItem selectItem = new SelectItem(e.getEstado());
            this.filterEstado.add(selectItem);
        }
        return filterEstado;
    }
  
    
}
