package clientapi.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for marking sub-command argument handler methods.
 * Method parameters should begin with a {@code ExecutionContext}
 * parameter, followed by a reflection of the target sub-command
 * arguments. Usage is as follows...
 *
 * {@code java.util.Optional} may be used to mark arguments
 * as "optional", but only if the argument is the last.
 *
 * Varargs and arrays can be used to mark the expectation of multiple
 * arguments.
 *
 * {@code java.lang.Object} can be used to accept any type of argument.
 *
 * @author Brady
 * @since 10/17/2017 2:49 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Sub {

    /**
     * The headers for this sub-command. If no headers are
     * defined it is implied that the handle method should
     * take in anything if it matches the target arguments,
     * regardless of the first argument (Defining argument),
     * and there shouldn't be any other handle methods in the class
     * that have headers.
     *
     * @return Headers for the subcommand.
     */
    String[] headers() default {};

    /**
     * Returns the names of the arguments. Will be automatically
     * filled if not specified. Intended for use with obfuscation.
     */
    String[] arguments() default {};

    /**
     * A basic description of the usage for this
     * sub-command argument handler. If undefined,
     * any reference to this sub-command's description
     * will point back to the parent command's description.
     */
    String description() default "";
}
