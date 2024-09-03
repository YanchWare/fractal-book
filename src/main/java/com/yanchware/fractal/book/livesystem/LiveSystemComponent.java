package com.yanchware.fractal.book.livesystem;

import com.yanchware.fractal.book.Component;

public interface LiveSystemComponent extends Component {
    Status getStatus();

    enum Status {
        INSTANTIATING,
        ACTIVE,
        MUTATING,
        DELETING,
        FAILED
    }
}