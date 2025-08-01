package com.siscon.employee.infrastructure.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YAMLConfig {

   @Value("${header.ctrl.constante.api.algoritmo}")
    private String projectName;


}
