package com.yanchware.fractal.book.livesystem.components.storage.paas;

import com.yanchware.fractal.book.fractal.Offer;
import com.yanchware.fractal.book.fractal.Provider;
import com.yanchware.fractal.book.fractal.components.storage.paas.ObjectStorage;
import com.yanchware.fractal.book.livesystem.LiveSystemComponent;
import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.ServiceDeliveryModel;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class S3 extends ObjectStorage implements LiveSystemComponent {
    public final static Offer.Type TYPE = new Offer.Type(InfrastructureDomain.STORAGE, ServiceDeliveryModel.PAAS, new PascalCaseString("S3"));
    @Getter
    private final Status status;

    public S3(
            Id id,
            String displayName,
            String description,
            Parameters parameters,
            List<Link> links,
            List<Dependency> dependencies,
            Status status)
    {
        super(id, displayName, description, parameters, new OutputFields(new HashMap<>()), links, dependencies);
        this.status = status;
    }

    @Override
    public Provider getProvider() {
        return Provider.AWS;
    }

    public enum CannedACL {
        PRIVATE,
        PUBLIC_READ,
        PUBLIC_READ_WRITE,
        AWS_EXEC_READ,
        AUTHENTICATED_READ,
        BUCKET_OWNER_READ,
        BUCKET_OWNER_FULL_CONTROL,
        LOG_DELIVERY_WRITE
    }

    @Override
    public Offer.Type getType() {
        return TYPE;
    }

    @Override
    public LiveSystemComponent getInstantiatingLiveSystemComponent() {
        return this;
    }
}
