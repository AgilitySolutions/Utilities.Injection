package com.agilitysolutions.utilities.injection;

public class InjectionBinding {
    private final Class _from;
    private final Class _to;

    public Class getFrom() {
        return _from;
    }

    public Class getTo() {
        return _to;
    }

    public InjectionBinding(Class from, Class to) {
        _from = from;
        _to = to;
    }

    public InjectionBinding(Class from) {
        _from = from;
        _to = null;
    }
}
