package com.yanchware.fractal.book.livesystem;

import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.fractal.BlueprintComponent;
import com.yanchware.fractal.book.fractal.Offer;
import com.yanchware.fractal.book.fractal.Provider;
import com.yanchware.fractal.book.values.Version;
import lombok.Getter;

import java.util.List;

@Getter
public class LiveSystemComponent extends Offer {
    private Status status;

    public LiveSystemComponent(
            Component.Id id,
            Version version,
            BlueprintComponent.Service.Type type,
            String displayName,
            String description,
            Component.Parameters parameters,
            Component.OutputFields outputFields,
            List<Link> links,
            List<BlueprintComponent.Dependency> dependencies,
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