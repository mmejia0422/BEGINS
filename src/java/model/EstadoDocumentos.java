package model;
// Generated 11-20-2016 09:32:27 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * EstadoDocumentos generated by hbm2java
 */
public class EstadoDocumentos  implements java.io.Serializable {


     private Integer idestadoDocumentos;
     private String estado;
     private String descripcion;
     private String documento;
     private Set ordenVentas = new HashSet(0);

    public EstadoDocumentos() {
    }

	
    public EstadoDocumentos(String estado, String descripcion, String documento) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.documento = documento;
    }
    public EstadoDocumentos(String estado, String descripcion, String documento, Set ordenVentas) {
       this.estado = estado;
       this.descripcion = descripcion;
       this.documento = documento;
       this.ordenVentas = ordenVentas;
    }
   
    public Integer getIdestadoDocumentos() {
        return this.idestadoDocumentos;
    }
    
    public void setIdestadoDocumentos(Integer idestadoDocumentos) {
        this.idestadoDocumentos = idestadoDocumentos;
    }
    public String getEstado() {
        this.estado = this.estado.substring(0, 1).toUpperCase() + this.estado.substring(1).toLowerCase();
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDocumento() {
        return this.documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public Set getOrdenVentas() {
        return this.ordenVentas;
    }
    
    public void setOrdenVentas(Set ordenVentas) {
        this.ordenVentas = ordenVentas;
    }




}


