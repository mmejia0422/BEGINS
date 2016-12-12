/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.EstadoDocumentos;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class estadoDocDaoImpl implements estadoDocDao{

    @Override
    public EstadoDocumentos nuevoDoc() {
        EstadoDocumentos obj = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM EstadoDocumentos where estado = 'PENDIENTE'";
        try {
            obj = (EstadoDocumentos) sesion.createQuery(sql).uniqueResult();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return obj;
    }
    
}
