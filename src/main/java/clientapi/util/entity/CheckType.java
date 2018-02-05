package clientapi.util.entity;

/**
 * @author Brady
 * @since 2/5/2018 2:16 PM
 */
public enum CheckType {

    /**
     * Run before the ALLOW type. If any check of this type fails,
     * then the filter will fail.
     */
    RESTRICT,

    /**
     * Run after the RESTRICT type. If any check of this type passes,
     * then the filter will pass.
     */
    ALLOW
}
