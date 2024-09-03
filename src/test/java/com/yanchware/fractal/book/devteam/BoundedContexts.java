package com.yanchware.fractal.book.devteam;

import com.yanchware.fractal.book.BoundedContext;
import com.yanchware.fractal.book.values.KebabCaseString;
import com.yanchware.fractal.book.values.OwnerType;
import lombok.Getter;

import static com.yanchware.fractal.book.devteam.Constants.DEV_TEAM_OWNER_ID;

@Getter
enum BoundedContexts {
    Sales(new KebabCaseString("sales"));

    private final BoundedContext.Id boundedContextId;

    BoundedContexts(KebabCaseString name) {
        this.boundedContextId = new BoundedContext.Id(OwnerType.ORGANIZATIONAL, DEV_TEAM_OWNER_ID, name);
    }
}
