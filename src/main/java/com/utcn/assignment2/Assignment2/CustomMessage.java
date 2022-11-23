package com.utcn.assignment2.Assignment2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public record CustomMessage(@JsonProperty("timestamp") LocalDateTime timestamp,
                            @JsonProperty("id_device") Long id_device,
                            @JsonProperty("measurement_value") Float value)
        implements Serializable {
}
