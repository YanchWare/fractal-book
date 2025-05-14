package com.yanchware.fractal.book.ccoe;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.fractal.Blueprint;
import com.yanchware.fractal.book.fractal.BlueprintComponent;
import com.yanchware.fractal.book.fractal.Fractal;
import com.yanchware.fractal.book.fractal.components.customworkload.faas.CustomFunctionWorkload;
import com.yanchware.fractal.book.fractal.components.customworkload.paas.CustomPaaSWorkload;
import com.yanchware.fractal.book.fractal.components.security.paas.CertificateAuthorityComponent;
import com.yanchware.fractal.book.values.*;

import java.util.Collections;
import java.util.List;

public class ThreeTierApplication extends Fractal<ThreeTierApplication.Interface> {
    public final static BlueprintComponent.Service.Type TYPE = new BlueprintComponent.Service.Type(InfrastructureDomain.CUSTOM_WORKLOAD, ServiceDeliveryModel.PAAS, new PascalCaseString("ThreeTierApplication"));
    public final static Version VERSION = new Version(1,0,0);

    public interface Interface extends com.yanchware.fractal.book.fractal.Interface {
        Interface withDatabase(Component component);
        Interface withFunctionWorkload(CustomFunctionWorkload component);
        Interface withPaaSWorkload(CustomPaaSWorkload component);
    }

    public ThreeTierApplication(BoundedContext.Id boundedContextId, String displayName, String description, Interface threeTierApplicationInterface) {
        var fractalId = new Id(boundedContextId, new KebabCaseString("three-tier-application"), VERSION);
        var blueprint = new Blueprint(fractalId, List.of(new BlueprintComponent(
                fractalId.toComponentId(),
                VERSION,
                CertificateAuthorityComponent.TYPE,
                "Simple Landing Zone",
                "Simple Landing Zone",
                new Parameters(),
                new OutputFields(Collections.emptyMap()),
                Collections.emptyList(),
                Collections.emptyList(),
                List.of(new BlueprintComponent.Service(CertificateAuthorityComponent.TYPE.toServiceType(), List.of(
                        // TODO: Add blueprint component as API Gateway, DBMS, etc
                ))))));

        super(
                fractalId,
                VERSION,
                TYPE,
                displayName,
                description,
                new Parameters(),
                new OutputFields(Collections.emptyMap()),
                Collections.emptyList(),
                Collections.emptyList(),
                blueprint,
                threeTierApplicationInterface);
    }
}
