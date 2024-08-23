package com.yanchware.fractal.book.fractal.components.security.paas.certificates;

import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.livesystem.components.security.paas.AwsCertificateAuthority;

public interface CertificateAuthority {
    record Subject(
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

    interface CrlConfiguration {
        AwsCertificateAuthority.AwsCrlConfiguration get(Component.Parameters parameters);
    }

    interface OcspConfiguration {
        AwsCertificateAuthority.AwsOcspConfiguration get(Component.Parameters parameters);
    }

    interface Configuration {
        Subject subject();
        AwsCertificateAuthority.AwsConfiguration get(Component.Parameters parameters);
    }

    record RevocationConfiguration(CrlConfiguration crlConfiguration, OcspConfiguration signingAlgorithm){ }

    Configuration configuration();
    RevocationConfiguration revocationConfiguration();
}
