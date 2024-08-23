package com.yanchware.fractal.book.fractal;

public enum Provider
{
    Azure("Azure"),
    AWS("Aws"),
    GCP("Gcp");

    private final String value;

    Provider(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}