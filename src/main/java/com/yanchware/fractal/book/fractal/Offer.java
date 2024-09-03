package com.yanchware.fractal.book.fractal;

import com.yanchware.fractal.book.ComponentBase;
import com.yanchware.fractal.book.livesystem.LiveSystemComponent;
import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.ServiceDeliveryModel;
import com.yanchware.fractal.book.values.Version;

import java.util.Collection;

public abstract class Offer extends ComponentBase {
    public Offer(
            Id id,
            Version version,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            Collection<Link> links,
            Collection<Dependency> dependencies)
    {
        super(id, version, displayName, description, parameters, outputFields, links, dependencies);
    }

    public record Type(
            InfrastructureDomain domain,
            ServiceDeliveryModel serviceDeliveryModel,
            PascalCaseString name) implements ComponentBase.Type
    {
        public BlueprintComponent.Service.Type toServiceType() {
            return new BlueprintComponent.Service.Type(domain, serviceDeliveryModel, name);
        }
    }

    public abstract Provider getProvider();
    public abstract Type getType();
    public abstract LiveSystemComponent getInstantiatingLiveSystemComponent();
}