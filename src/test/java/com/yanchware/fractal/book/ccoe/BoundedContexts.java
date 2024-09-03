package com.yanchware.fractal.book.ccoe;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.values.KebabCaseString;
import com.yanchware.fractal.book.values.OwnerType;
import lombok.Getter;

import static com.yanchware.fractal.book.ccoe.Constants.CCOE_OWNER_ID;

@Getter
enum BoundedContexts {
    ReusableTemplates(new KebabCaseString("reusable-templates"));

    private final BoundedContext.Id boundedContextId;

    BoundedContexts(KebabCaseString name) {
        this.boundedContextId = new BoundedContext.Id(OwnerType.ORGANIZATIONAL, CCOE_OWNER_ID, name);
    }
}
