package com.fraudguard.fraudguard.dto.response;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlertValidationResponse {

    private boolean isFake;
    private AlertLevel alertLevel;
    private String explanation;

}
