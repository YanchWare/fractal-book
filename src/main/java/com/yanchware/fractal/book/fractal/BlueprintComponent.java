package com.yanchware.fractal.book.fractal;

import com.yanchware.fractal.book.ComponentBase;
import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.ServiceDeliveryModel;
import com.yanchware.fractal.book.values.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class BlueprintComponent extends ComponentBase
{
    private final List<Service> services;
    private final Type type;

    public BlueprintComponent(
            Id id,
            Version version,
            Type type,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            List<Link> links,
            List<Dependency> dependencies,
            List<Service> services)
    {
        super(id,  version, displayName, description, parameters, outputFields, links, dependencies);
        this.type = type;
        this.services = services;
    }

    public record Dependency(
            Type componentType,
            Id id) implements ComponentBase.Dependency { }

    @Getter
    @AllArgsConstructor
    public static class Service {
        private Type type;
        private List<Offer> offers;

        public record Type(
                InfrastructureDomain domain,
                ServiceDeliveryModel serviceDeliveryModel,
                PascalCaseString name) implements ComponentBase.Type { }
    }
}