package com.yanchware.fractal.book.fractal;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.values.KebabCaseString;
import com.yanchware.fractal.book.values.Version;
import lombok.Getter;

import java.util.List;

@Getter
public class Fractal extends Component
{
    private final Blueprint blueprint;
    private final Interface fractalInterface;

    public Fractal(
            Component.Id id,
            Version version,
            Type type,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            List<Link> links,
            List<Dependency> dependencies,
            Blueprint blueprint,
            Interface fractalInterface)
    {
        super(id,  version, type, displayName, description, parameters, outputFields, links, dependencies);
        this.blueprint = blueprint;
        this.fractalInterface = fractalInterface;
    }

    public record Id(BoundedContext.Id boundedContextId, KebabCaseString name, Version version)
    {
        public Component.Id toComponentId()
        {
            return new Component.Id(
                    new KebabCaseString(
                            String.format("%s/%s:%s",
                                    boundedContextId.toString(),
                                    name.toString(),
                                    version.toString())));
        }
    }
}