/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Menu;
import model.RolMenu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class RolMenuDaoImpl implements RolMenuDao{

    @Override
    public List<RolMenu> findByResp(Integer idResp) {
        List<RolMenu> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM RolMenu rm left join fetch rm.menu menu left join fetch rm.rol rol where rm.rol = '" + idResp + "' order by menu.orden";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public List<RolMenu> findAll() {
        List<RolMenu> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM RolMenu rm left join fetch rm.menu m left join fetch m.icono left join fetch rm.rol order by m.orden asc";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(RolMenu rolMenu) {
          boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(rolMenu.getMenu());
            sesion.save(rolMenu);
            tx.commit();
            flag = true;
        }catch (Exception e) {
            flag = false;
            tx.rollback();
            e.printStackTrace();
        }
        
        return flag;
    }

    @Override
    public boolean update(RolMenu rolMenu) {
       boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Menu menuBd = (Menu) sesion.load(Menu.class, rolMenu.getMenu().getIdmenu());
            RolMenu rmBd = (RolMenu) sesion.load(RolMenu.class, rolMenu.getIdrolMenu());
            menuBd.setNombre(rolMenu.getMenu().getNombre());
            menuBd.setUrl(rolMenu.getMenu().getUrl());
            menuBd.setEstado(rolMenu.getMenu().getEstado());
            menuBd.setIcono(rolMenu.getMenu().getIcono());
            menuBd.setOrden(rolMenu.getMenu().getOrden());
            rmBd.setMenu(rolMenu.getMenu());
            rmBd.setRol(rolMenu.getRol());
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
    public boolean delete(Integer id) {
       boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        
        try {
            Query q = sesion.createQuery("from RolMenu rm left join fetch rm.menu where idmenu = :idmenu");
            Query q2 = sesion.createQuery("from Menu where idmenu = :idmenu2");
            q.setParameter("idmenu", id);
            q2.setParameter("idmenu2", id);
            
            RolMenu rmDb = (RolMenu)q.list().get(0);
            Menu menuDb = (Menu) q2.list().get(0);

            sesion.delete(rmDb);
            sesion.delete(menuDb);
            tx.commit();
            flag = true;
        }catch(Exception e){
            flag = false;
            tx.rollback();
        }
       return flag;
    }
    
}
