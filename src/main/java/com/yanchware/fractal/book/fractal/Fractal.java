package com.yanchware.fractal.book.fractal;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.ComponentBase;
import com.yanchware.fractal.book.values.KebabCaseString;
import com.yanchware.fractal.book.values.Version;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class Fractal<I extends Interface> extends ComponentBase
{
    private final Blueprint blueprint;
    private final I operations;
    private final Type type;
    private final Id fractalId;

    public Fractal(
            Id fractalid,
            Version version,
            Type type,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            List<Link> links,
            List<Dependency> dependencies,
            Blueprint blueprint,
            I operations)
    {
        super(fractalid.toComponentId(),  version, displayName, description, parameters, outputFields, links, dependencies);
        this.type = type;
        this.blueprint = blueprint;
        this.operations = operations;
        this.fractalId = fractalid;
    }

    public record Id(BoundedContext.Id boundedContextId, KebabCaseString name, Version version)
    {
        public ComponentBase.Id toComponentId()
        {
            return new ComponentBase.Id(
                    new KebabCaseString(
                            String.format("%s/%s:%s",
                                    boundedContextId.toString(),
                                    name.toString(),
                                    version.toString())));
        }
    }
}