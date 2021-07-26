package com.dell.ecloud.model.repository;

import com.dell.ecloud.model.UserFile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "file", path = "file")
public interface UserFileRepository extends PagingAndSortingRepository<UserFile, Long> {

    Iterable<UserFile> findAllByUserid(long userId);

}