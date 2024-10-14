package com.yanchware.fractal.book.livesystem.components.security.paas;

import com.yanchware.fractal.book.fractal.Offer;
import com.yanchware.fractal.book.fractal.Provider;
import com.yanchware.fractal.book.fractal.components.security.paas.CertificateAuthorityComponent;
import com.yanchware.fractal.book.livesystem.LiveSystemComponent;
import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.ServiceDeliveryModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GcpCasCertificateAuthority extends CertificateAuthorityComponent implements LiveSystemComponent {
    public final static Offer.Type TYPE = new Offer.Type(InfrastructureDomain.SECURITY, ServiceDeliveryModel.PAAS, new PascalCaseString("CasPool"));

    private final Collection<CertificateAuthorityComponent> subordinates;
    private final Status status;

    public GcpCasCertificateAuthority(
            Id id,
            String displayName,
            String description,
            Parameters parameters,
            Configuration configuration)
    {
        super(id, displayName, description, parameters, Collections.emptyList(), Collections.emptyList(), configuration);
        this.subordinates = new ArrayList<>();
        this.status = Status.INSTANTIATING;
    }


    @Override
    public void withSubordinates(Collection<CertificateAuthorityComponent> subordinateCas) {
        this.subordinates.addAll(subordinates.stream().peek(x -> {
            if (!(x instanceof GcpCasCertificateAuthority)){
                throw new IllegalArgumentException("GcpCasCertificateAuthority only supports GcpCasCertificateAuthority as a subordinate CA");
            }
        }).toList());
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Offer.Type getType() {
        return TYPE;
    }

    @Override
    public LiveSystemComponent getInstantiatingLiveSystemComponent() {
        return this;
    }

    @Override
    public Provider getProvider() {
        return Provider.GCP;
    }

    public record Configuration(Subject subject) implements CertificateAuthorityComponent.Configuration
    {
    }

}
