package com.dell.ecloud.model.repository;

import com.dell.ecloud.model.UserFile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "files", path = "files")
public interface UserFileRepository extends PagingAndSortingRepository<UserFile, Long> {

    Iterable<UserFile> findAllByUserid(long userId);

    Iterable<UserFile> findAllByNameContaining(String search);

}