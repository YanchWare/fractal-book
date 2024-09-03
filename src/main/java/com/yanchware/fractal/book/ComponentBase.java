package com.yanchware.fractal.book;

import com.yanchware.fractal.book.values.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@AllArgsConstructor
@Getter
public abstract class ComponentBase implements Component
{
    protected final Id id;
    protected final Version version;
    protected String displayName;
    protected String description;
    protected final Parameters parameters;
    protected final OutputFields outputFields;
    protected final Collection<? extends Link> links;
    protected final Collection<? extends Dependency> dependencies;

    public abstract Type getType();
}