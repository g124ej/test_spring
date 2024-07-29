package com.example.testrit.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.testrit.dto.OrganizationDto;
import com.example.testrit.entity.Organization;
import com.example.testrit.exception.ResourceNotFoundException;
import com.example.testrit.mapper.OrganizationMapper;
import com.example.testrit.repository.OrganizationRepository;
import com.example.testrit.service.OrganizationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto createOrganization(OrganizationDto organizationDto) {
        // Organization organization =
        // OrganizationMapper.mapToOrganization(organizationDto);

        Organization organization = new Organization();
        organization.setName(organizationDto.getName());
        organization.setCreatedBy(organizationDto.getCreatedBy());

        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationById(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not Found ! Organization with id : " + organizationId));
        return OrganizationMapper.mapToOrganizationDto(organization);
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream().map((organization) -> OrganizationMapper.mapToOrganizationDto(organization))
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto updateOrganization(Long organizationId, OrganizationDto organizationDto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not Found ! Organization with id : " + organizationId));
        organization.setName(organizationDto.getName());

        Organization updatedOrganizationObj = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(updatedOrganizationObj);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationRepository.findById(organizationId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not Found ! Organization with id : " + organizationId));
        organizationRepository.deleteById(organizationId);
    }

}
