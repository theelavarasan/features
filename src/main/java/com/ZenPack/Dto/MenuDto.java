package com.ZenPack.Dto;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.model.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class MenuDto {
    private String menuName;
    private Date createdTime;
    private String createdBy;
    private Integer parentMenuId;
    private List<Menu> menus;
    private List<FeaturedList> featuredLists;
}
