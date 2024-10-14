package com.yanchware.fractal.book;

import com.yanchware.fractal.book.values.InfrastructureDomain;
import com.yanchware.fractal.book.values.KebabCaseString;
import com.yanchware.fractal.book.values.PascalCaseString;
import com.yanchware.fractal.book.values.Version;

import java.util.*;

public interface Component {
    interface Type
    {
        InfrastructureDomain domain();
        PascalCaseString name();
    }

    interface Dependency
    {
        Type componentType();
    }

    record Id(KebabCaseString value) { }

    class Parameters {
        private Map<String, Object> container;

        public Parameters(){
            container = new HashMap<>();
        }

        public Object getRequiredFieldByName(String name) {
            return getOptionalFieldByName(name)
                    .orElseThrow(() -> new IllegalArgumentException(String.format("No such field in component parameters: %s", name)));
        }

        public Optional<Object> getOptionalFieldByName(String name) {
            if(container.containsKey(name)) {
                return Optional.of(container.get(name));
            }
            return Optional.empty();
        }
    }

    record OutputFields(Map<String, Object> value) { }

    record Link(Id id, Type type, Settings settings) {
        public record Settings(Map<String, Object> value) { }
    }

    Id getId();
    Version getVersion();
    String getDisplayName();
    String getDescription();
    Parameters getParameters();
    OutputFields getOutputFields();
    Collection<? extends Link> getLinks();
    Collection<? extends Dependency> getDependencies();
}
