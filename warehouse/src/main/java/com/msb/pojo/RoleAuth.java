package com.msb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAuth {
    private Integer roleAuthId;
    private Integer roleId;
    private Integer AuthId;
}
