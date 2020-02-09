package com.alnivv.opencascade.controller;

import com.alnivv.opencascade.domain.Attribute;
import com.alnivv.opencascade.domain.Geometry;
import com.alnivv.opencascade.domain.Project;
import com.alnivv.opencascade.domain.Views;
import com.alnivv.opencascade.exceptions.NotFoundException;
import com.alnivv.opencascade.repository.ApiRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/projects")
public class ApiController {
    private final ApiRepository apiRepository;
    private static final String templateGeom = "Geom%s";
    private static final String templateAttr = "Attr%s";
    private final AtomicLong counterGeom = new AtomicLong(-1L);
    private final AtomicLong counterAttr= new AtomicLong(-1L);


    @Autowired
    public ApiController(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @GetMapping
    @JsonView(Views.ProjectIdName.class)
    public List<Project> list() {
        return (List<Project>) apiRepository.findAll();
    }

    @PostMapping
    @JsonView(Views.ProjectIdName.class)
    public Project create(@RequestBody String projectName) {
        Project project = new Project(projectName);
        List<Geometry> geometries = new ArrayList<>();
        List<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            attributes.add(new Attribute(String.format(templateAttr, counterAttr.incrementAndGet()), project));
            geometries.add(new Geometry(String.format(templateGeom, counterGeom.incrementAndGet()), project));
        }
        project.setGeometries(geometries);
        project.setAttributes(attributes);
        return apiRepository.save(project);
    }

    @GetMapping("{id}")
    @JsonView(Views.ProjectIdName.class)
    public Project getOne(@PathVariable("id") Long projectId) {
        return apiRepository.findById(projectId).orElseThrow(NotFoundException::new);
    }

    @PutMapping("{id}")
    @JsonView(Views.ProjectIdName.class)
    public Project update (
            @PathVariable("id") Project projectFromDB,
            @RequestBody String projectNewName) {
        projectFromDB.setProjectName(projectNewName);
        return apiRepository.save(projectFromDB);
    }

    @DeleteMapping("{id}")
    @JsonView(Views.ProjectIdName.class)
    public void delete(@PathVariable("id") Project project) {
        apiRepository.delete(project);
    }
}
