package com.yanchware.fractal.book.ccoe;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.ComponentBase;
import com.yanchware.fractal.book.fractal.*;
import com.yanchware.fractal.book.fractal.components.security.paas.CertificateAuthorityComponent;
import com.yanchware.fractal.book.livesystem.LiveSystem;
import com.yanchware.fractal.book.values.*;
import lombok.Getter;

import java.util.*;

@Getter
class SimpleLandingZone extends Fractal<SimpleLandingZone.Interface> {
    public final static BlueprintComponent.Service.Type TYPE = new BlueprintComponent.Service.Type(InfrastructureDomain.CUSTOM_WORKLOAD, ServiceDeliveryModel.PAAS, new PascalCaseString("SimpleLandingZone"));
    public final static Version VERSION = new Version(1,0,0);

    public final Id fractalId;

    private final static CertificateAuthorityComponent.Subject subject = new CertificateAuthorityComponent.Subject(
            "commonName",
            "country",
            "distinguishedNameQualifier",
            "generationQualifier",
            "givenName",
            "initials",
            "locality",
            "organization",
            "organizationalUnit",
            "pseudonym",
            "state",
            "surname",
            "title"
    );

    protected SimpleLandingZone(BoundedContext.Id boundedContextId, SimpleLandingZone.Interface simpleLandingZoneOperations) {
        var componentId = new ComponentBase.Id(new KebabCaseString("business-workload-ca"));
        var displayName = "Business Workload Private CA";
        var description = "Private CA used to generate certificates for internal workloads";

        var fractalId = new Fractal.Id(boundedContextId, new KebabCaseString("simple-landing-zone"), VERSION);
        var blueprint = new Blueprint(fractalId, List.of(new BlueprintComponent(
                fractalId.toComponentId(),
                VERSION,
                CertificateAuthorityComponent.TYPE,
                "Simple Landing Zone",
                "Simple Landing Zone",
                new Parameters(),
                new OutputFields(Collections.emptyMap()),
                Collections.emptyList(),
                Collections.emptyList(),
                List.of(new BlueprintComponent.Service(CertificateAuthorityComponent.TYPE.toServiceType(), List.of(
                        AwsSimpleLandingZone.getCertificateAuthorityComponent(componentId, displayName, description, subject),
                        GcpSimpleLandingZone.getCertificateAuthorityComponent(componentId, displayName, description, subject))))
        )));
        super(fractalId, VERSION, TYPE, "Simple Landing Zone", "A simple landing zone with a single component", new Parameters(), new OutputFields(new HashMap<>()), new ArrayList<>(), new ArrayList<>(), blueprint, simpleLandingZoneOperations);
        this.fractalId = fractalId;
    }

    public interface Interface extends com.yanchware.fractal.book.fractal.Interface {
        void WithCompliantLiveSystems(Collection<? extends LiveSystem<? extends Fractal>> compliantLiveSystems);
    }
}
