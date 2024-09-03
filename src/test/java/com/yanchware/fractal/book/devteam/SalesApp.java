package com.yanchware.fractal.book.devteam;

import com.yanchware.fractal.book.ccoe.AwsSimpleLandingZone;
import com.yanchware.fractal.book.ccoe.AwsThreeTierApplication;
import com.yanchware.fractal.book.ccoe.Environments;
import com.yanchware.fractal.book.values.KebabCaseString;

import java.util.List;

public class SalesApp {
    public static void main(String[] args) {
        var landingZone = AwsSimpleLandingZone.getLandingZone(Environments.Test.getEnvironmentId());
        var system = new AwsThreeTierApplication(
                BoundedContexts.Sales.getBoundedContextId(),
                new KebabCaseString("sales-app"),
                "Sales App",
                "Sales application",
                Environments.Test.getEnvironmentId());

        system.getFractal().getOperations()
                .WithDatabase(null)
                .WithWorkload(null);

        landingZone.getOperations().WithCompliantLiveSystems(List.of(system));
        landingZone.instantiate();
    }
}
