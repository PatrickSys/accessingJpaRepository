package com.example.accessingdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/************************************************************************
 Made by        PatrickSys
 Date           12/05/2021
 Package        com.example.accessingdatajpa
 Description:
 ************************************************************************/
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {




}
