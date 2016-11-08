package model;
// Generated 12-09-2015 09:53:51 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RolMenu generated by hbm2java
 */
@Entity
@Table(name="rol_menu"
    ,catalog="gasisw_bd1"
)
public class RolMenu  implements java.io.Serializable {


     private Integer idrolMenu;
     private Menu menu;
     private Rol rol;

    public RolMenu() {
    }

    public RolMenu(Menu menu, Rol rol) {
       this.menu = menu;
       this.rol = rol;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idrol_menu", unique=true, nullable=false)
    public Integer getIdrolMenu() {
        return this.idrolMenu;
    }
    
    public void setIdrolMenu(Integer idrolMenu) {
        this.idrolMenu = idrolMenu;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="menu_id", nullable=false)
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rol_id", nullable=false)
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }




}


