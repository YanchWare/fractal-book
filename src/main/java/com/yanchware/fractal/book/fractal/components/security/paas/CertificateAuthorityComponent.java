package com.yanchware.fractal.book.fractal.components.security.paas;

import com.yanchware.fractal.book.fractal.Offer;
import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.ServiceDeliveryModel;
import com.yanchware.fractal.book.values.Version;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Getter
public abstract class CertificateAuthorityComponent extends Offer {
    public record Subject(
            String commonName,
            String country,
            String distinguishedNameQualifier,
            String generationQualifier,
            String givenName,
            String initials,
            String locality,
            String organization,
            String organizationalUnit,
            String pseudonym,
            String state,
            String surname,
            String title) { }

    public interface CrlConfiguration {
    }

    public interface OcspConfiguration {
    }

    public interface Configuration {
        Subject subject();
    }

    public record RevocationConfiguration(Optional<CrlConfiguration> crlConfiguration, Optional<OcspConfiguration> ocspConfiguration){ }

    public final static Offer.Type TYPE = new Offer.Type(InfrastructureDomain.SECURITY, ServiceDeliveryModel.PAAS, new PascalCaseString("CertificateAuthority"));
    public final static Version VERSION = new Version(1,0,0);

    protected Configuration configuration;
    protected RevocationConfiguration revocationConfiguration;

    public CertificateAuthorityComponent(
            Id id,
            String displayName,
            String description,
            Parameters parameters,
            Collection<Link> links,
            Collection<Dependency> dependencies,
            Configuration configuration)
    {
        this(id, displayName, description, parameters, links, dependencies, configuration, null);
    }


    public CertificateAuthorityComponent(
            Id id,
            String displayName,
            String description,
            Parameters parameters,
            Collection<Link> links,
            Collection<Dependency> dependencies,
            Configuration configuration,
            RevocationConfiguration revocationConfiguration)
    {
        super(id, VERSION, displayName, description, parameters, new OutputFields(new HashMap<>()), links, dependencies);
        this.configuration = configuration;
        this.revocationConfiguration = revocationConfiguration;
    }

    @Override
    public Type getType() {
        return TYPE;
    }

    public void withSubordinate(CertificateAuthorityComponent subordinateCa) {
        withSubordinates(List.of(subordinateCa));
    }
    public abstract void withSubordinates(Collection<CertificateAuthorityComponent> subordinateCas);
}