package com.yanchware.fractal.book.livesystem.components.security.paas;

import com.yanchware.fractal.book.fractal.Provider;
import com.yanchware.fractal.book.fractal.components.security.paas.PublicKeyInfrastructure;
import com.yanchware.fractal.book.fractal.components.security.paas.certificates.CertificateAuthority;
import com.yanchware.fractal.book.livesystem.LiveSystemComponent;
import com.yanchware.fractal.book.values.Version;

import java.util.List;

public class AwsPrivateCertificateAuthority extends LiveSystemComponent implements PublicKeyInfrastructure {
    private final AwsCertificateAuthority certificateAuthority;

    public AwsPrivateCertificateAuthority(
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
        super(id, version, type, displayName, description, parameters, outputFields, links, dependencies, status, provider);
        certificateAuthority = new AwsCertificateAuthority(
                AwsCertificateAuthority.Configuration.get(parameters),
                AwsCertificateAuthority.CrlConfiguration.get(parameters),
                AwsCertificateAuthority.OcspConfiguration.get(parameters));
    }

    @Override
    public CertificateAuthority getCertificateAuthority() {
        return this.certificateAuthority;
    }
}
