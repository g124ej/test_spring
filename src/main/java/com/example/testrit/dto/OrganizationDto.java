package com.example.testrit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private Long id;
    private String name;
    private Long createdBy;
}
