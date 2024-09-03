package com.yanchware.fractal.book.fractal.components.storage.paas;

import com.yanchware.fractal.book.fractal.Offer;
import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.ServiceDeliveryModel;
import com.yanchware.fractal.book.values.Version;

import java.util.List;

public abstract class ObjectStorage extends Offer {
    public final static Offer.Type TYPE = new Offer.Type(InfrastructureDomain.STORAGE, ServiceDeliveryModel.PAAS, new PascalCaseString("ObjectStorage"));
    public final static Version VERSION = new Version(1,0,0);

    public ObjectStorage(
            Id id,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            List<Link> links,
            List<Dependency> dependencies)
    {
        super(id, VERSION, displayName, description, parameters, outputFields, links, dependencies);
    }

    @Override
    public Type getType() {
        return TYPE;
    }
}
