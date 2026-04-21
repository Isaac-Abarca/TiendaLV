/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.MissiCrochets.Tienda.domain;


import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data; // Importante

@Data // Esto genera Getters, Setters, toString, equals y hashCode automáticamente
@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Integer idCategoria;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
}