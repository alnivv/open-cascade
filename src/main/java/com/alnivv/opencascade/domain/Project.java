package com.alnivv.opencascade.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Table(name = "oc_projects")
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    @JsonView(Views.ProjectIdName.class)
    private Long projectId;

    @JsonView(Views.ProjectIdName.class)
    @Column(name = "project_name", unique = true)
    private String projectName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "project")
    private List<Geometry> geometries;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "project")
    private List<Attribute> attributes;

    protected Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Geometry> getGeometries() {
        return geometries;
    }

    public void setGeometries(List<Geometry> geometries) {
        this.geometries = geometries;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
