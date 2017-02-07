package me.zero.client.load.inject.transformer;

import me.zero.client.load.inject.transformer.reference.ClassReference;
import me.zero.client.load.inject.transformer.reference.MethodReference;

import java.util.Arrays;

/**
 * Used to contstruct bytecode method descriptors.
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public class MethodDescriptorBuilder {

    /**
     * Return value of Method
     */
    private final ClassReference returnValue;

    /**
     * Parameters of Method
     */
    private final ClassReference[] parameters;

    public MethodDescriptorBuilder(MethodReference reference) {
        this(reference.getReturnType(), reference.getParameters());
    }

    public MethodDescriptorBuilder(ClassReference returnValue, ClassReference... parameters) {
        this.returnValue = returnValue;
        this.parameters = parameters;
    }

    /**
     * Builds the Method Descriptor from the Return value and parameters
     *
     * @return Built Method Descriptor as a {@code String}
     */
    public String build() {
        StringBuilder paramDesc = new StringBuilder();
        Arrays.asList(this.parameters).forEach(param -> paramDesc.append(param.getDescriptor()));
        return "(" + paramDesc + ")" + returnValue.getDescriptor();
    }
}
