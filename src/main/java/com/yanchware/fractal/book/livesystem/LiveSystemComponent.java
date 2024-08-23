package com.yanchware.fractal.book.livesystem;

import com.yanchware.fractal.book.fractal.Offer;
import com.yanchware.fractal.book.fractal.Provider;
import com.yanchware.fractal.book.values.Version;
import lombok.Getter;

import java.util.List;

@Getter
public class LiveSystemComponent extends Offer {
    private Status status;

    public LiveSystemComponent(
            Id id,
            Version version,
            Type type,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            List<Link> links,
            List<Dependency> dependencies,
            Status status,
            Provider provider)
    {
        super(id,  version, type, displayName, description, parameters, outputFields, links, dependencies, provider);
        this.status = status;
    }

    public enum Status {
        INSTANTIATING,
        ACTIVE,
        MUTATING,
        DELETING,
        FAILED
    }
}