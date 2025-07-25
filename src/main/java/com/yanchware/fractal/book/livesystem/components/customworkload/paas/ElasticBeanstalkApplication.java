package com.yanchware.fractal.book.livesystem.components.customworkload.paas;

import com.yanchware.fractal.book.fractal.Provider;
import com.yanchware.fractal.book.fractal.components.customworkload.paas.CustomPaaSWorkload;
import com.yanchware.fractal.book.livesystem.LiveSystemComponent;
import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.ServiceDeliveryModel;
import com.yanchware.fractal.book.values.Version;

import java.util.Collection;

public class ElasticBeanstalkApplication extends CustomPaaSWorkload implements LiveSystemComponent {
    public final static Type TYPE = new Type(InfrastructureDomain.CUSTOM_WORKLOAD, ServiceDeliveryModel.PAAS, new PascalCaseString("ElasticBeanstalkApplication"));
    private final Status status;

    public ElasticBeanstalkApplication(
            Id id,
            Version version,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            Collection<Link> links,
            Collection<Dependency> dependencies,
            GitCustomWorkloadConfiguration configuration) {
        super(id, version, displayName, description, parameters, outputFields, links, dependencies, configuration);
        status = Status.INSTANTIATING;
    }

    @Override
    public Provider getProvider() {
        return Provider.AWS;
    }

    @Override
    public Type getType() {
        return TYPE;
    }

    @Override
    public LiveSystemComponent getInstantiatingLiveSystemComponent() {
        return this;
    }

    @Override
    public Status getStatus() {
        return status;
    }
}
