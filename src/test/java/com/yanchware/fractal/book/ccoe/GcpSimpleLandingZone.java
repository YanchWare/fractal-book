package com.yanchware.fractal.book.ccoe;

import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.ComponentBase;
import com.yanchware.fractal.book.Environment;
import com.yanchware.fractal.book.fractal.components.security.paas.CertificateAuthorityComponent;
import com.yanchware.fractal.book.livesystem.LiveSystem;
import com.yanchware.fractal.book.livesystem.components.security.paas.GcpCasCertificateAuthority;

public class GcpSimpleLandingZone extends LiveSystem<SimpleLandingZone> {
    public GcpSimpleLandingZone(
            Id id,
            String displayName,
            String description,
            Environment.Id environmentId,
            SimpleLandingZone fractal)
    {
        super(id, displayName, description, environmentId, fractal);
    }

    public static CertificateAuthorityComponent getCertificateAuthorityComponent(Component.Id id, String displayName, String description, CertificateAuthorityComponent.Subject subject) {
        return new GcpCasCertificateAuthority(
                id,
                displayName,
                description,
                new ComponentBase.Parameters(),
                new GcpCasCertificateAuthority.Configuration(subject));
    }
}
