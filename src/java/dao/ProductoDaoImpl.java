/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CatProducto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class ProductoDaoImpl implements ProductoDao {

    @Override
    public List<CatProducto> selectItems() {
        List<CatProducto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "from CatProducto c left join fetch c.tipoCategoria t left join fetch c.presentacion p where t.nombre  = 'Inventario'";
        try{
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return listado;
    }

    @Override
    public List<CatProducto> obtenerProdSeleccionado(Integer id) {
        
        List<CatProducto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "from CatProducto where idCatProducto = " + id;
        try{
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return listado;
    }
    
}
