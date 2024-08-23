package com.yanchware.fractal.book.livesystem;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.Environment;
import com.yanchware.fractal.book.fractal.Fractal;
import com.yanchware.fractal.book.values.KebabCaseString;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class LiveSystem {
    private final Id id;
    private String displayName;
    private String description;
    private Fractal.Id fractalId;
    private Mutation.Id currentMutationId;
    private final List<Mutation> mutations;
    private final Environment.Id environmentId;
    private Status status;

    public record Id(
            BoundedContext.Id boundedContextId,
            KebabCaseString name){ }

    public enum Status {
        ACTIVE,
        MUTATING,
        FAILED
    }

    public record Mutation(
            Id id,
            List<LiveSystemComponent> components,
            Status status)
    {
        public record Id (UUID value) { }

        public enum Status {
            IN_PROGRESS,
            COMPLETED,
            FAILED,
            CANCELLED
        }
    }
}