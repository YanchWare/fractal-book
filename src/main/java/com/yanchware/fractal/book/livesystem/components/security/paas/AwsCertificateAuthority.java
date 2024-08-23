package com.yanchware.fractal.book.livesystem.components.security.paas;

import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.fractal.components.security.paas.certificates.CertificateAuthority;

public record AwsCertificateAuthority (Configuration configuration, RevocationConfiguration revocationConfiguration) implements CertificateAuthority {

    public AwsCertificateAuthority(
            AwsConfiguration configuration,
            AwsCrlConfiguration crlConfiguration,
            AwsOcspConfiguration ocspConfiguration)
    {
        this(configuration, new RevocationConfiguration(crlConfiguration, ocspConfiguration));
    }

    @Override
    public Configuration configuration() {
        return this.configuration;
    }

    @Override
    public RevocationConfiguration revocationConfiguration() {
        return null;
    }

    public record AwsCrlConfiguration(
            String customName,
            String s3BucketName,
            String s3ObjectAcl,
            int expirationInDays,
            boolean enabled) implements CrlConfiguration { }

    public record AwsOcspConfiguration(String ocspCustomCName, boolean enabled) implements OcspConfiguration { }

    public record AwsConfiguration(
            Subject subject,
            AwsKeyAlgorithm keyAlgorithm,
            AwsSigningAlgorithm signingAlgorithm) implements Configuration
    {
        @Override
        public AwsConfiguration get(Component.Parameters parameters) {
            return null;
        }
    }

    public enum AwsKeyAlgorithm {
        RSA_2048,
        RSA_4096,
        EC_prime256v1,
        EC_secp384r1,
        SM2
    }

    public enum AwsSigningAlgorithm {
        SHA256WITHECDSA,
        SHA384WITHECDSA,
        SHA512WITHECDSA,
        SHA256WITHRSA,
        SHA384WITHRSA,
        SHA512WITHRSA,
        SM3WITHSM2
    }
}
