package org.xjc.demo.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xjc on 2019-4-3.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

    private Long id; // 组织Id
    private String name;// 组织名称
    private OrgLevel level;// 组织等级

}
