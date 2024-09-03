package com.yanchware.fractal.book.ccoe;

import com.yanchware.fractal.book.fractal.Blueprint;
import com.yanchware.fractal.book.fractal.Fractal;
import com.yanchware.fractal.book.fractal.Interface;
import com.yanchware.fractal.book.values.Version;

import java.util.List;

public class ThreeTierApplication extends CompliantFractal {
    public ThreeTierApplication(Fractal.Id id, Version version, Type type, String displayName, String description, Parameters parameters, OutputFields outputFields, List<Link> links, List<Dependency> dependencies, Blueprint blueprint, Interface fractalInterface) {
        super(id, version, type, displayName, description, parameters, outputFields, links, dependencies, blueprint, fractalInterface);
    }
}
