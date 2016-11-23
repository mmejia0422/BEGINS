/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ProductoDao;
import dao.ProductoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import model.CatProducto;

/**
 *
 * @author Mario
 */
@ManagedBean(name="productoBean")
@RequestScoped
public class productoBean {

    private List<SelectItem> selectItemProducto;
    
    public productoBean() {
    }

    public List<SelectItem> getSelectItemProducto() {
        this.selectItemProducto = new ArrayList<SelectItem>();
        ProductoDao producto = new ProductoDaoImpl();
        List<CatProducto> listado = producto.selectItems();
        for (CatProducto p : listado) {
            SelectItem selectItem = new SelectItem(p.getIdCatProducto(), p.getNombre() + " - " + p.getPresentacion().getNombre() + " - " + p.getPresentacion().getDescripcion());
            this.selectItemProducto.add(selectItem);
        }
        return selectItemProducto;
    }

    
}
