package com.example.testrit.mapper;

import com.example.testrit.dto.OrganizationDto;
import com.example.testrit.entity.Organization;

public class OrganizationMapper {
    public static OrganizationDto mapToOrganizationDto(Organization organization) {
        return new OrganizationDto(
                organization.getId(),
                organization.getName(),
                organization.getCreatedBy());
    }

    public static Organization mapToOrganization(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setName(organizationDto.getName());
        organization.setCreatedBy(organizationDto.getCreatedBy());
        return organization;
    }
}
