package com.akshay.liftmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LiftDirections {
    UP("UP"),
    DOWN("DOWN"),
    IDLE("IDLE");

    private final String direction;

    LiftDirections(String direction) {
        this.direction = direction;
    }

    @JsonValue
    public String getDirection() {
        return direction;
    }

    @JsonCreator
    public static LiftDirections fromString(String value) {
        for (LiftDirections direction : LiftDirections.values()) {
            if (direction.getDirection().equalsIgnoreCase(value)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid lift direction: " + value);
    }
}
