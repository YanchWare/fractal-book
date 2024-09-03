package com.yanchware.fractal.book.livesystem.components.security.paas;

import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.ComponentBase;
import com.yanchware.fractal.book.fractal.Offer;
import com.yanchware.fractal.book.fractal.Provider;
import com.yanchware.fractal.book.fractal.components.security.paas.CertificateAuthorityComponent;
import com.yanchware.fractal.book.livesystem.LiveSystemComponent;
import com.yanchware.fractal.book.livesystem.components.storage.paas.S3;
import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.ServiceDeliveryModel;
import lombok.Getter;

import java.util.*;

public class AwsAcmPrivateCertificateAuthority extends CertificateAuthorityComponent implements LiveSystemComponent {
    public final static Offer.Type TYPE = new Offer.Type(InfrastructureDomain.SECURITY, ServiceDeliveryModel.PAAS, new PascalCaseString("AcmPca"));

    private static final String CUSTOM_CNAME_PARAM_KEY = "customCName";

    @Getter
    private final Configuration configuration;
    @Getter
    private final Status status;
    @Getter
    private final Collection<CertificateAuthorityComponent> subordinates;
    @Getter
    private final S3 crlBucket;

    public AwsAcmPrivateCertificateAuthority(
            Id id,
            String displayName,
            String description,
            Parameters parameters,
            Status status,
            Configuration configuration)
    {
        this(id, displayName, description, parameters, configuration, null, null);
    }

    public AwsAcmPrivateCertificateAuthority(
            Id id,
            String displayName,
            String description,
            Parameters parameters,
            Configuration configuration,
            S3 crlBucket,
            CrlConfiguration crlConfiguration)
    {
        this(id, displayName, description, parameters, configuration, crlBucket, crlConfiguration, null);
    }

    public AwsAcmPrivateCertificateAuthority(
            Id id,
            String displayName,
            String description,
            Parameters parameters,
            Configuration configuration,
            OcspConfiguration ocspConfiguration)
    {
        this(id, displayName, description, parameters, configuration, null, null, ocspConfiguration);
    }

    public AwsAcmPrivateCertificateAuthority(
            Id id,
            String displayName,
            String description,
            Parameters parameters,
            Configuration configuration,
            S3 crlBucket,
            CrlConfiguration crlConfiguration,
            OcspConfiguration ocspConfiguration)
    {
        RevocationConfiguration revocationConfiguration = null;
        if (crlConfiguration != null || ocspConfiguration != null) {
            revocationConfiguration = new RevocationConfiguration(
                    Optional.ofNullable(crlConfiguration),
                    Optional.ofNullable(ocspConfiguration));
        }

        Collection<Link> links = crlBucket != null
                ? List.of(new Component.Link(crlBucket.getId(), crlBucket.getType(), new Link.Settings(Collections.emptyMap())))
                : Collections.emptyList();
        super(id, displayName, description, parameters, links, Collections.emptyList(), configuration, revocationConfiguration);

        this.status = Status.INSTANTIATING;
        this.configuration = configuration;
        this.subordinates = new ArrayList<>();
        this.crlBucket = crlBucket;
    }

    @Override
    public Offer.Type getType() {
        return TYPE;
    }

    @Override
    public LiveSystemComponent getInstantiatingLiveSystemComponent() {
        return this;
    }

    @Override
    public void withSubordinates(Collection<CertificateAuthorityComponent> subordinateCas) {
        this.subordinates.addAll(subordinates.stream().peek(x -> {
            if (!(x instanceof AwsAcmPrivateCertificateAuthority)){
                throw new IllegalArgumentException("AwsAcmPca only supports AwsAcmPca as a subordinate CA");
            }
        }).toList());
    }

    @Override
    public Provider getProvider() {
        return Provider.AWS;
    }

    public record CrlConfiguration(
            Optional<String> customCName,
            S3.CannedACL s3ObjectAcl,
            int expirationInDays) implements CertificateAuthorityComponent.CrlConfiguration {
    }

    public record OcspConfiguration(Optional<String> ocspCustomCName) implements CertificateAuthorityComponent.OcspConfiguration {
        public OcspConfiguration(ComponentBase.Parameters parameters) {
            var customCName = parameters.getOptionalFieldByName(CUSTOM_CNAME_PARAM_KEY);
            Optional<String> customCNameOptional = customCName.map(Object::toString);
            this(customCNameOptional);
        }
    }

    public record Configuration(
            Subject subject,
            AwsKeyAlgorithm keyAlgorithm,
            AwsSigningAlgorithm signingAlgorithm) implements CertificateAuthorityComponent.Configuration
    {
        public Configuration(Subject subject, ComponentBase.Parameters parameters) {
            this(subject, AwsKeyAlgorithm.fromParameters(parameters), AwsSigningAlgorithm.fromParameters(parameters));
        }
    }

    public enum AwsKeyAlgorithm {
        RSA_2048,
        RSA_4096,
        EC_prime256v1,
        EC_secp384r1,
        SM2;

        private static final String KEY_ALGORITHM_PARAM_KEY = "keyAlgorithm";

        static AwsKeyAlgorithm fromParameters(ComponentBase.Parameters parameters) {
            var keyAlgorithmStr = parameters.getRequiredFieldByName(KEY_ALGORITHM_PARAM_KEY);
            try {
                return AwsKeyAlgorithm.valueOf(keyAlgorithmStr.toString());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(STR."Invalid key algorithm: \{keyAlgorithmStr}. Supported algorithms: \{Arrays.toString(AwsKeyAlgorithm.values())}");
            }
        }
    }

    public enum AwsSigningAlgorithm {
        SHA256WITHECDSA,
        SHA384WITHECDSA,
        SHA512WITHECDSA,
        SHA256WITHRSA,
        SHA384WITHRSA,
        SHA512WITHRSA,
        SM3WITHSM2;

        private static final String SIGNING_ALGORITHM_PARAM_KEY = "signingAlgorithm";

        static AwsSigningAlgorithm fromParameters(ComponentBase.Parameters parameters) {
            var signingAlgorithmStr = parameters.getRequiredFieldByName(SIGNING_ALGORITHM_PARAM_KEY);
            try {
                return AwsSigningAlgorithm.valueOf(signingAlgorithmStr.toString());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(STR."Invalid signing algorithm: \{signingAlgorithmStr}. Supported algorithms: \{Arrays.toString(AwsSigningAlgorithm.values())}");
            }

        }
    }
}
