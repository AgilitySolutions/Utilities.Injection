package com.agilitysolutions.utilities.injection.providers;

import com.agilitysolutions.utilities.injection.InjectionBinding;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import javafx.util.Pair;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GuiceBindingModule extends AbstractModule {
    private final List<Pair<Class, Class>> _pairs;

    public GuiceBindingModule() {
        _pairs = new ArrayList<Pair<Class, Class>>();
    }

    public void addBinding(Class from) {
        InjectionBinding binding = new InjectionBinding(from);
        addBinding(binding);
    }

    public void addBinding(Class from, Class to) {
        InjectionBinding binding = new InjectionBinding(from, to);
        addBinding(binding);
    }

    private void addBinding(InjectionBinding binding) {
        if (!isIncludedInBindings(binding.getFrom())) {
            Pair<Class, Class> pair = new Pair<Class, Class>(binding.getFrom(), binding.getTo());
            _pairs.add(pair);
        }
    }

    protected void configure() {
        for (Pair<Class, Class> pair : _pairs) {
            InjectionBinding binding = new InjectionBinding(pair.getKey(), pair.getValue());
            doBinding(binding);
        }
    }

    private void doBinding(InjectionBinding binding) {
        Constructor constructor = getConstructorToBindTo(getClassToUse(binding));

        if (constructor != null) {
            bind(binding.getFrom()).toConstructor(constructor);
            bindConstructorParameterTypes(constructor);
        } else if (binding.getTo() == null) {
            bind(binding.getFrom());
        } else {
            bind(binding.getFrom()).to(binding.getTo());
        }
    }

    private Class getClassToUse(InjectionBinding binding) {
        Class classToUse = binding.getTo() == null ? binding.getFrom() : binding.getTo();

        if (classToUse.isInterface()) {
            classToUse = getImplementationOfInterface(classToUse);
        }

        return classToUse;
    }

    private Constructor getConstructorToBindTo(Class type) {
        List<Constructor> constructors = Arrays.asList(type.getConstructors());

        if (constructors.size() > 0) {
            Constructor injectAnnotatedConstructor = getInjectAnnotatedConstructor(constructors);
            if (injectAnnotatedConstructor != null) {
                return injectAnnotatedConstructor;
            }

            return constructors.get(0);
        }

        return null;
    }

    private Constructor getInjectAnnotatedConstructor(List<Constructor> constructors) {
        for (Constructor constructor : constructors) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                return constructor;
            }
        }

        return null;
    }

    private Class getImplementationOfInterface(Class interfaceType) {
        Class implementationType;

        implementationType = getClassForName(interfaceType.getName().concat("Impl"));
        if (implementationType != null) {
            return implementationType;
        }

        implementationType = getClassForName(interfaceType.getName().concat("Implementation"));
        if (implementationType != null) {
            return implementationType;
        }

        implementationType = getClassForName(interfaceType.getName().concat("Class"));
        if (implementationType != null) {
            return implementationType;
        }

        implementationType = getClassForName(interfaceType.getName().replace("Interface", "Class"));
        if (implementationType != null) {
            return implementationType;
        }

        return interfaceType;
    }

    private Class getClassForName(String name) {
        Class foundClass;

        try {
            foundClass = Class.forName(name);
        } catch (ClassNotFoundException e) {
            foundClass = null;
        }

        return foundClass;
    }

    private void bindConstructorParameterTypes(Constructor constructor) {
        for (Class type : constructor.getParameterTypes()) {
            if (!isIncludedInBindings(type)) {
                doBinding(new InjectionBinding(type));
            }
        }
    }

    private boolean isIncludedInBindings(Class type) {
        for (Pair<Class, Class> pair : _pairs) {
            if (pair.getKey() == type || pair.getValue() == type) {
                return true;
            }
        }

        return false;
    }
}
