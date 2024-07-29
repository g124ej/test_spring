package com.example.testrit.service;

import java.util.List;

import com.example.testrit.dto.OrganizationDto;

public interface OrganizationService {

    OrganizationDto createOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationById(Long id);

    List<OrganizationDto> getAllOrganizations();

    OrganizationDto updateOrganization(Long id, OrganizationDto organizationDto);

    void deleteOrganization(Long organizationId);
}
