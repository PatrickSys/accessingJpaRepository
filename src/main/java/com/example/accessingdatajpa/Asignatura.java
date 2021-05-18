package com.example.accessingdatajpa;

import javax.persistence.*;

/************************************************************************
 Made by        PatrickSys
 Date           18/05/2021
 Package        com.example.accessingdatajpa
 Description:
 ************************************************************************/
@Entity
public class Asignatura {
    @Id
    private int id;
    public String nombre;
    public int nota;

    @ManyToOne
    private Alumno alumno;

}
