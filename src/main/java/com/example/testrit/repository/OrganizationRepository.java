package com.example.testrit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.testrit.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
