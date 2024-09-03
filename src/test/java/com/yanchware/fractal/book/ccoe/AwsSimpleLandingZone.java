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
import java.util.Map;
import java.util.Optional;

public class AwsSimpleLandingZone extends LiveSystem<SimpleLandingZone> {

    private AwsSimpleLandingZone(Environment.Id environmentId) {
        var fractal = new SimpleLandingZone(BoundedContexts.ReusableTemplates.getBoundedContextId(), compliantLiveSystems -> {
            // TODO: Add live systems to landing zone
        });
        super(new Id(BoundedContexts.ReusableTemplates.getBoundedContextId(), environmentId.name()), "AWS Landing Zone", "AWS Landing Zone", environmentId, fractal);
    }

    public static AwsSimpleLandingZone getLandingZone(Environment.Id environmentId) {
        return new AwsSimpleLandingZone(environmentId);
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

    protected static CertificateAuthorityComponent getCertificateAuthorityComponent(Component.Id id, String displayName, String description, CertificateAuthorityComponent.Subject subject) {
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

    public SimpleLandingZone.Interface getOperations() {
        return getFractal().getOperations();
    }

    public void instantiate() {
        var instantiationConfiguration = new InstantiationConfiguration(Map.of(
                // TODO do the mapping
        ));
        super.instantiate(instantiationConfiguration);
        getMutations().stream().filter(
                x -> x.id().equals(getCurrentMutationId())).findFirst().orElseThrow(() -> new IllegalStateException("Current mutation not found"))
                .components().add(getS3BucketForCrl());
    }
}
