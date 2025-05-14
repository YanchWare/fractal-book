package com.yanchware.fractal.book.ccoe;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.Environment;
import com.yanchware.fractal.book.fractal.components.customworkload.GitCustomWorkload;
import com.yanchware.fractal.book.fractal.components.customworkload.faas.CustomFunctionWorkload;
import com.yanchware.fractal.book.fractal.components.customworkload.paas.CustomPaaSWorkload;
import com.yanchware.fractal.book.livesystem.LiveSystem;
import com.yanchware.fractal.book.livesystem.components.customworkload.faas.LambdaFunction;
import com.yanchware.fractal.book.livesystem.components.customworkload.paas.ElasticBeanstalkApplication;
import com.yanchware.fractal.book.values.KebabCaseString;

public class AwsThreeTierApplication extends LiveSystem<ThreeTierApplication> {
    public AwsThreeTierApplication(BoundedContext.Id boundendContextId, KebabCaseString name, String displayName, String description, Environment.Id environmentId) {
        var fractal = new ThreeTierApplication(
                boundendContextId,
                displayName,
                description,
                new ThreeTierApplication.Interface() {

                    @Override
                    public ThreeTierApplication.Interface withDatabase(Component component) {
                        return this;
                    }

                    @Override
                    public ThreeTierApplication.Interface withFunctionWorkload(CustomFunctionWorkload component) {
                        if (!(component instanceof LambdaFunction)) {
                            throw new IllegalArgumentException("Only Lambda Functions are supported");
                        }
                        return this;
                    }

                    @Override
                    public ThreeTierApplication.Interface withPaaSWorkload(CustomPaaSWorkload component) {
                        if (!(component instanceof ElasticBeanstalkApplication)) {
                            throw new IllegalArgumentException("Only Elastic Beanstalk Applications are supported");
                        }
                        return this;
                    }

                });
        super(new Id(boundendContextId, name), displayName, description, environmentId, fractal);
    }

    public ThreeTierApplication.Interface getOperations() {
        return this.getFractal().getOperations();
    }
}
