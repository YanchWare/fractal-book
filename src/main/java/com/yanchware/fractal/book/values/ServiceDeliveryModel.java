package com.yanchware.fractal.book.values;

public enum ServiceDeliveryModel {
    IAAS("IaaS"),
    CAAS("CaaS"),
    PAAS("PaaS"),
    FAAS("FaaS"),
    SAAS("SaaS");

    private final String value;

    ServiceDeliveryModel(String value)
    {
        this.value = value;
    }
}
