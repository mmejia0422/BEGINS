/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
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

    @Override
    public List<OrdenVenta> reportePedido(/*Integer param1, Integer param2, String param3*/) {
        List<OrdenVenta> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "from OrdenVenta ov left join fetch ov.estadoDocumentos docs left join "
                + "     fetch ov.cliente cl left join fetch ov.empleado em "
                + "   where docs.idestadoDocumentos = coalesce(null, docs.idestadoDocumentos) and cl.idCliente = coalesce(null, cl.idCliente) "
                + "and em.idEmpleado = coalesce(null, em.idEmpleado) and ov.prioridadAlta = coalesce(null, ov.prioridadAlta)"
                + " order by docs.estado, ov.prioridadAlta desc, ov.fechaEntrega asc, ov.idOrdenVenta";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;  
    }
    
}
