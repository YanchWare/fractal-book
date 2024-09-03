package com.yanchware.fractal.book.ccoe;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.Environment;
import com.yanchware.fractal.book.livesystem.LiveSystem;
import com.yanchware.fractal.book.values.KebabCaseString;

public class AwsThreeTierApplication extends LiveSystem<ThreeTierApplication> {
    public AwsThreeTierApplication(BoundedContext.Id boundendContextId, KebabCaseString name, String displayName, String description, Environment.Id environmentId) {
        var fractal = new ThreeTierApplication(
                boundendContextId,
                displayName,
                description,
                new ThreeTierApplication.Interface() {

                    @Override
                    public ThreeTierApplication.Interface WithDatabase(Component component) {
                        return this;
                    }

                    @Override
                    public ThreeTierApplication.Interface WithWorkload(Component component) {
                        return this;
                    }
                });
        super(new Id(boundendContextId, name), displayName, description, environmentId, fractal);
    }

    public ThreeTierApplication.Interface getOperations() {
        return this.getFractal().getOperations();
    }
}
