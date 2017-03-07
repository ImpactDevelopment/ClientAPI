package me.zero.client.api.util;

import java.util.ArrayList;
import java.util.List;

/**
 * List of protocols
 *
 * @since 1.0
 *
 * Created by Brady on 3/6/2017.
 */
public class Protocol {

    private static List<Protocol> registry;

    static {
        registry = new ArrayList<>();

        add(316, "1.11.x", "1.11", "1.11.2");
        add(315, "1.11");
        add(210, "1.10.x", "1.10", "1.10.1", "1.10.2");
        add(110, "1.9.3", "1.9.3", "1.9.4");
        add(109, "1.9.2", "1.9.2");
        add(108, "1.9.1");
        add(107, "1.9");
        add(47, "1.8.x", "1.8", "1.8.1", "1.8.2", "1.8.3", "1.8.4", "1.8.5", "1.8.6", "1.8.7", "1.8.8", "1.8.9");
        add(5, "1.7.10", "1.7.6", "1.7.7", "1.7.8", "1.7.9", "1.7.10");
        add(4, "1.7.2", "1.7.2", "1.7.4", "1.7.5");
    }

    private int protocol;
    private String name;
    private String[] versions;

    private Protocol(int protocol, String name, String... versions) {
        this.protocol = protocol;
        this.name = name;
        this.versions = versions;
    }

    public int getProtocol() {
        return this.protocol;
    }

    public String getName() {
        return this.name;
    }

    public String[] getVersions() {
        return this.versions;
    }

    private static void add(int protocol, String name, String... versions) {
        if (versions.length == 0)
            versions = new String[] { name };

        registry.add(new Protocol(protocol, name, versions));
    }
}
