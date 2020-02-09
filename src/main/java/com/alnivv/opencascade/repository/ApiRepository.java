package com.alnivv.opencascade.repository;

import com.alnivv.opencascade.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ApiRepository extends CrudRepository<Project, Long> {

}
