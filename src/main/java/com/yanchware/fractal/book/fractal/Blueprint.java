package com.yanchware.fractal.book.fractal;

import java.util.List;

public record Blueprint(Fractal.Id fractalId, List<BlueprintComponent> components) {
}