package com.yanchware.fractal.book.ccoe;

import com.yanchware.fractal.book.Environment;
import com.yanchware.fractal.book.values.KebabCaseString;
import com.yanchware.fractal.book.values.OwnerType;
import lombok.Getter;

import static com.yanchware.fractal.book.ccoe.Constants.CCOE_OWNER_ID;

@Getter
public enum Environments {
    Production(new KebabCaseString("production")),
    Test(new KebabCaseString("test"));

    private final Environment.Id environmentId;

    Environments(KebabCaseString name) {
        this.environmentId = new Environment.Id(OwnerType.ORGANIZATIONAL, CCOE_OWNER_ID, name);
    }
}
