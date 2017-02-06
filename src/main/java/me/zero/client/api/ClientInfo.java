package me.zero.client.api;

/**
 * Contains all information that is inside of the client.json
 *
 * @since 1.0
 *
 * Created by Brady on 1/24/2017.
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

    public ClientInfo(String name, String[] authors, String id, double build, ReleaseType type, String main) {
        this.name = name;
        this.authors = authors;
        this.id = id;
        this.build = build;
        this.type = type;
        this.main = main;
    }

    /**
     * @since 1.0
     *
     * @return The Client Name
     */
    public String getName() {
        return name;
    }

    /**
     * @since 1.0
     *
     * @return The authors of the Client
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * @since 1.0
     *
     * @return The Client Unique ID
     */
    public String getId() {
        return id;
    }

    /**
     * @since 1.0
     *
     * @return The Client Build
     */
    public double getBuild() {
        return build;
    }

    /**
     * @since 1.0
     *
     * @return The Release Type
     */
    public ReleaseType getType() {
        return type;
    }

    /**
     * @since 1.0
     *
     * @return The Main Class
     */
    public String getMain() {
        return main;
    }

    public enum ReleaseType {
        ALPHA, BETA, SNAPSHOT, RELEASE
    }
}
