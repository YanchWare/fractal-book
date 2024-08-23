package com.yanchware.fractal.book.livesystem.components.security.paas;

import com.yanchware.fractal.book.fractal.Provider;
import com.yanchware.fractal.book.fractal.components.security.paas.PublicKeyInfrastructure;
import com.yanchware.fractal.book.livesystem.LiveSystemComponent;
import com.yanchware.fractal.book.values.Version;

import java.util.List;

public class GcpCertificateAuthorityService extends LiveSystemComponent implements PublicKeyInfrastructure {
    public GcpCertificateAuthorityService(
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
    }


}
