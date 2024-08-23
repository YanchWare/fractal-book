package com.yanchware.fractal.book.fractal;

import lombok.Getter;

import java.util.List;

@Getter
public record Blueprint(Fractal.Id fractalId, List<BlueprintComponent> components) {
}