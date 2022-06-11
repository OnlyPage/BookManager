package com.example.bookManager.repositories;

import com.example.bookManager.domain.RoleDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleDetail, Integer>
{
}
