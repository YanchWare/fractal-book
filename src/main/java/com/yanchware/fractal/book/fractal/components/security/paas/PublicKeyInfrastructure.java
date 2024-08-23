package com.yanchware.fractal.book.fractal.components.security.paas;

import com.yanchware.fractal.book.fractal.components.security.paas.certificates.CertificateAuthority;

public interface PublicKeyInfrastructure {
    CertificateAuthority getCertificateAuthority();
}
