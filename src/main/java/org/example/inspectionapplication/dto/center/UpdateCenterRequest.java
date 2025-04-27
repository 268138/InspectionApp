package org.example.inspectionapplication.dto.center;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCenterRequest {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotBlank(message = "Phone must not be blank")
    private String phone;
}
