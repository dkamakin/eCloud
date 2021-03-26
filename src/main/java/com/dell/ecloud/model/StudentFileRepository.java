package com.dell.ecloud.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "file", path = "file")
public interface StudentFileRepository extends PagingAndSortingRepository<StudentFile, Long> {}