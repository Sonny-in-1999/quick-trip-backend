package com.neurotoxin.quicktrip.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    TOURIST("ROLE_TOURIST"), CLIENT("ROLE_CLIENT"), ADMIN("ROLE_ADMIN");

    private final String key;
}
