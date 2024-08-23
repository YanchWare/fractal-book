package com.yanchware.fractal.book.fractal;

import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.ServiceDeliveryModel;
import com.yanchware.fractal.book.values.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class BlueprintComponent extends Component
{
    private final List<Service> services;

    public BlueprintComponent(
            Id id,
            Version version,
            Type type,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            List<Link> links,
            List<Dependency> dependencies)
    {
        super(id,  version, type, displayName, description, parameters, outputFields, links, dependencies);
        this.services = new ArrayList<>();
    }

    public record Dependency(
            Type componentType,
            Id id) implements Component.Dependency { }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class Service {
        private Type type;
        private List<Offer> offers;

        public record Type(
                InfrastructureDomain domain,
                ServiceDeliveryModel serviceDeliveryModel,
                PascalCaseString name) implements Component.Type { }

        public Service withOffer(
                PascalCaseString name,
                String displayName,
                String description,
                Version version,
                Provider provider)
        {
            var offer = new Offer(
                    id,
                    version,
                    new Type(type.domain, type.serviceDeliveryModel, name),
                    displayName,
                    description,
                    parameters,
                    outputFields,
                    links,
                    (List<Component.Dependency>) dependencies,
                    provider);
            offers.add(offer);
            return this;
        }
    }

    public Service withService(
            ServiceDeliveryModel serviceDeliveryModel,
            PascalCaseString name)
    {
        var service = new Service(
                new Service.Type(type.domain(), serviceDeliveryModel, name),
                new ArrayList<>());
        services.add(service);
        return service;
    }
}