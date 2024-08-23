package com.yanchware.fractal.book.fractal;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record Interface(Fractal.Id fractalId, List<Operation> operations) {
    public record Operation(
            Id id,
            String displayName,
            String description,
            List<BlueprintComponent> components,
            Parameters parameters) {
        public record Parameters(Map<String, Object> value) {
        }

        public record Id(UUID value) {
        }
    }
}