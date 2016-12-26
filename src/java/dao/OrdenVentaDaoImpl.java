/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.OrdenVenta;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class OrdenVentaDaoImpl implements OrdenVentaDao {

    @Override
    public boolean create(OrdenVenta venta, boolean hasHeader) {
        boolean flag;
        if (!hasHeader){
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(venta);
            tx.commit();
            flag = true;
        }catch (Exception e) {
            flag = false;
            tx.rollback();
            e.printStackTrace();
        }
        } else {
            flag = true;
        }
        
        return flag;
    }

    @Override
    public boolean update(OrdenVenta venta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean confirmarPedido(OrdenVenta oVenta) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            OrdenVenta oVentaBd = (OrdenVenta) sesion.load(OrdenVenta.class, oVenta.getIdOrdenVenta());
            oVentaBd.setProcesado("Y");
            sesion.update(oVentaBd);
            tx.commit();
            flag = true;
        }catch(Exception e){
            flag = false;
            tx.rollback();
            e.printStackTrace();
        }
        
        return flag;
    }
    
}
