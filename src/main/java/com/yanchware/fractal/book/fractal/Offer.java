package com.yanchware.fractal.book.fractal;

import com.yanchware.fractal.book.Component;
import com.yanchware.fractal.book.values.Version;
import lombok.Getter;

import java.util.List;

@Getter
public class Offer extends Component {
    private final Provider provider;

    public Offer(
            Id id,
            Version version,
            Type type,
            String displayName,
            String description,
            Parameters parameters,
            OutputFields outputFields,
            List<Link> links,
            List<Dependency> dependencies,
            Provider provider)
    {
        super(
                id,
                version,
                type,
                displayName,
                description,
                parameters,
                outputFields,
                links,
                dependencies);
        this.provider = provider;
    }
}