package com.yanchware.fractal.book;

import com.yanchware.fractal.book.livesystem.LiveSystemComponent;
import com.yanchware.fractal.book.values.KebabCaseString;
import com.yanchware.fractal.book.values.OwnerId;
import com.yanchware.fractal.book.values.OwnerType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Environment {
    private final Id id;
    private String displayName;
    private String description;
    private final List<LiveSystemComponent> components;
    private final List<BoundedContext.Id> allowedContexts;

    public record Id(
            OwnerType ownerType,
            OwnerId ownerId,
            KebabCaseString name) { }
}