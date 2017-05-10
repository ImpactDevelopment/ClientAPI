package me.zero.client.api;

/**
 * Contains all information that is inside of the client.json
 *
 * @author Brady
 * @since 1/24/2017 12:00 PM
 */
public final class ClientInfo {

    /**
     * Client name
     */
    private String name;

    /**
     * List of Client Authors
     */
    private String[] authors;

    /**
     * Client Unique ID
     */
    private String id;

    /**
     * Client Build
     */
    private double build;

    /**
     * Release Type
     */
    private ReleaseType type;

    /**
     * The Main Class
     */
    private String main;

    /**
     * @return The Client Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The authors of the Client
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * @return The Client Unique ID
     */
    public String getId() {
        return id;
    }

    /**
     * @return The Client Build
     */
    public double getBuild() {
        return build;
    }

    /**
     * @return The Release Type
     */
    public ReleaseType getType() {
        return type;
    }

    /**
     * @return The Main Class
     */
    public String getMain() {
        return main;
    }

    public enum ReleaseType {
        ALPHA, BETA, SNAPSHOT, RELEASE
    }
}
