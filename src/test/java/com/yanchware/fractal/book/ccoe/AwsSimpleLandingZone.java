package com.yanchware.fractal.book.ccoe;

import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.ComponentBase;
import com.yanchware.fractal.book.Environment;
import com.yanchware.fractal.book.fractal.components.security.paas.CertificateAuthorityComponent;
import com.yanchware.fractal.book.livesystem.LiveSystem;
import com.yanchware.fractal.book.livesystem.LiveSystemComponent;
import com.yanchware.fractal.book.livesystem.components.security.paas.AwsAcmPrivateCertificateAuthority;
import com.yanchware.fractal.book.livesystem.components.storage.paas.S3;
import com.yanchware.fractal.book.values.KebabCaseString;

import java.util.Collections;
import java.util.Optional;

public class AwsSimpleLandingZone extends LiveSystem<SimpleLandingZone> {

    public AwsSimpleLandingZone(
            Id id,
            String displayName,
            String description,
            Environment.Id environmentId,
            SimpleLandingZone fractal) {
        super(id, displayName, description, environmentId, fractal);
    }

    private static S3 getS3BucketForCrl() {
        return new S3(
                new ComponentBase.Id(new KebabCaseString("business-workload-crl-storage")),
                "Business Workload CA CRL Storage",
                "Storage for Certificate Revocation List",
                new ComponentBase.Parameters(),
                Collections.emptyList(),
                Collections.emptyList(),
                LiveSystemComponent.Status.INSTANTIATING);
    }

    public static CertificateAuthorityComponent getCertificateAuthorityComponent(Component.Id id, String displayName, String description, CertificateAuthorityComponent.Subject subject) {
        return new AwsAcmPrivateCertificateAuthority(
                id,
                displayName,
                description,
                new ComponentBase.Parameters(),
                new AwsAcmPrivateCertificateAuthority.Configuration(
                        subject,
                        AwsAcmPrivateCertificateAuthority.AwsKeyAlgorithm.RSA_4096,
                        AwsAcmPrivateCertificateAuthority.AwsSigningAlgorithm.SHA512WITHRSA),
                getS3BucketForCrl(),
                new AwsAcmPrivateCertificateAuthority.CrlConfiguration(Optional.of("test.yanchware.com"), S3.CannedACL.PUBLIC_READ, 90));
    }

    @Override
    public void instantiate(InstantiationConfiguration instantiationConfiguration) {
        super.instantiate(instantiationConfiguration);
        getMutations().stream().filter(x -> x.id().equals(getCurrentMutationId())).findFirst().orElseThrow(() -> new IllegalStateException("Current muration not found")).components().add(getS3BucketForCrl());
    }
}
