package com.fraudguard.fraudguard.dto.response;

import com.fraudguard.fraudguard.data.enums.AlertLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AiResponse {

    private boolean isFake;
    private AlertLevel alertLevel;
    private String explanation;
}
