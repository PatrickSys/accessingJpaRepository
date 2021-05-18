package com.example.accessingdatajpa;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
/************************************************************************
 Made by        PatrickSys
 Date           12/05/2021
 Package        com.example.accessingdatajpa
 Description:
 ************************************************************************/
@RestController
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("/processJson")
    public ModelAndView getJson(Model model) {
        return new ModelAndView("alumno", "Alumno", new Alumno());
    }

    @PostMapping(path = "/processJson", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> addAlumno(@RequestBody String alumnoJSON) throws UnsupportedEncodingException, JsonProcessingException {

        String newAlumnoJson = URLDecoder.decode(String.valueOf(alumnoJSON), StandardCharsets.UTF_8.name());
        //newAlumnoJson = newAlumnoJson.replace("processJson=","");
        newAlumnoJson = newAlumnoJson.substring(12);

        ObjectMapper objectMapper = new ObjectMapper();
        Alumno alumno = objectMapper.readValue(newAlumnoJson, Alumno.class);
        Alumno alumnoAdded = alumnoService.addAlumno(alumno);

        return new ResponseEntity<>(alumnoAdded.getId(), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<Alumno>> findAllAlumnos(){
        List <Alumno> alumnos = alumnoService.findAllAlumnos();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }


    @GetMapping("find/{id}")
    public ResponseEntity<?> findAlumnoById (@PathVariable("id") Integer id){
        Optional<Alumno> alumno = alumnoService.findAlumnoById(id);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

}