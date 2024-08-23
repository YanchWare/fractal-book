package com.yanchware.fractal.book;

import com.yanchware.fractal.book.values.KebabCaseString;
import com.yanchware.fractal.book.values.OwnerId;
import com.yanchware.fractal.book.values.OwnerType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoundedContext {
    private final Id id;
    private String displayName;
    private String description;

    public record Id(
            OwnerType ownerType,
            OwnerId ownerId,
            KebabCaseString name) { }
}