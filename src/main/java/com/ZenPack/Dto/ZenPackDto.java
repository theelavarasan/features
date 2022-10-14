package com.ZenPack.Dto;

import com.ZenPack.model.Menu;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZenPackDto {
    private Long id;
    private String name;
    private String createdBy;
    private Date createdDate=new Date();
    private String updatedBy;
    private Date updatedTime=new Date();
    private List<MenuDto> menus;
    private List<FeatureDto> features;
}
