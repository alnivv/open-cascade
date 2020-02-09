package com.alnivv.opencascade.domain;

import javax.persistence.*;
import java.util.List;

@Table(name = "oc_geometries")
@Entity
public class Geometry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "geometry_id")
    private Long geometryId;

    @Column(name = "geometry_name")
    private String geometryName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "geometry")
    private List<Attribute> attributes;

    protected Geometry() {
    }

    public Geometry(String geometryName, Project project) {
        this.geometryName = geometryName;
        this.project = project;
    }

    public Long getGeometryId() {
        return geometryId;
    }

    public void setGeometryId(Long geometryId) {
        this.geometryId = geometryId;
    }

    public String getGeometryName() {
        return geometryName;
    }

    public void setGeometryName(String geometryName) {
        this.geometryName = geometryName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
