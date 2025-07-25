package com.yanchware.fractal.book.values;

public record Version(int major, int minor, int patch)
{
    @Override
    public String toString()
    {
        return String.format("v%d.%d.%d", major, minor, patch);
    }
}