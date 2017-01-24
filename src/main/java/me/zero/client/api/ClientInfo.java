package me.zero.client.api;

/**
 * Created by Brady on 1/24/2017.
 */
public class ClientInfo {

    private String name;
    private String[] authors;
    private String id;
    private double build;
    private ReleaseType type;
    private String main;

    public ClientInfo(String name, String[] authors, String id, double build, ReleaseType type, String main) {
        this.name = name;
        this.authors = authors;
        this.id = id;
        this.build = build;
        this.type = type;
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getId() {
        return id;
    }

    public double getBuild() {
        return build;
    }

    public ReleaseType getType() {
        return type;
    }

    public String getMain() {
        return main;
    }

    public enum ReleaseType {
        ALPHA, BETA, SNAPSHOT, RELEASE
    }
}
