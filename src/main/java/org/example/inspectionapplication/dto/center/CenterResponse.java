package org.example.inspectionapplication.dto.center;

import lombok.Data;

@Data
public class CenterResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
