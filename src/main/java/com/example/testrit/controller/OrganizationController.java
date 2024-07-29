package com.example.testrit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.testrit.dto.OrganizationDto;
import com.example.testrit.service.AuthenticationService;
import com.example.testrit.service.OrganizationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;
    private AuthenticationService authenticationService;

    // REST API Add Organization
    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto) {
        organizationDto.setCreatedBy(authenticationService.getUserLogin().getId());
        OrganizationDto savedOrganization = organizationService.createOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    // REST API Get By Id
    @GetMapping("{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable("id") Long organizationId) {
        OrganizationDto organizationDto = organizationService.getOrganizationById(organizationId);
        return ResponseEntity.ok(organizationDto);
    }

    // REST API Get All
    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        List<OrganizationDto> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }

    // REST API Update Organization
    @PutMapping("{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(
            @PathVariable("id") Long organizationId,
            @RequestBody OrganizationDto updatedOrganization) {
        OrganizationDto organizationbyId = organizationService.getOrganizationById(organizationId);
        if (!authenticationService.getUserLogin().getRole().equals("superadmin")
                && organizationbyId.getCreatedBy() != authenticationService
                        .getUserLogin().getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        OrganizationDto organizationDto = organizationService.updateOrganization(organizationId, updatedOrganization);
        return ResponseEntity.ok(organizationDto);
    }

    // REST API Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable("id") Long organizationId) {
        OrganizationDto organizationbyId = organizationService.getOrganizationById(organizationId);
        if (!authenticationService.getUserLogin().getRole().equals("superadmin")
                && organizationbyId.getCreatedBy() != authenticationService
                        .getUserLogin().getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        organizationService.deleteOrganization(organizationId);
        return ResponseEntity.ok("Organization deleted successfully");
    }

}
