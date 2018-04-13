package clientapi.util.server;

import java.util.Set;

import static clientapi.util.server.PluginFinderResponse.Result.FAILURE;
import static clientapi.util.server.PluginFinderResponse.Result.SUCCESS;

/**
 * @author Brady
 * @since 4/12/2018 8:38 PM
 */
public final class PluginFinderResponse {

    /**
     * The list of plugins found
     */
    private final Set<String> plugins;

    /**
     * The last error
     */
    private final String error;

    /**
     * The result status, either SUCCESS or FAILURE
     */
    private final Result result;

    PluginFinderResponse(String error) {
        this.plugins = null;
        this.error = error;
        this.result = FAILURE;
    }

    PluginFinderResponse(Set<String> plugins) {
        this.plugins = plugins;
        this.error = null;
        this.result = SUCCESS;
    }

    /**
     * @return The plugins found, if found
     */
    public final Set<String> getPlugins() {
        if (result != SUCCESS)
            throw new UnsupportedOperationException("Cannot get plugins that were retrieved unless response type is SUCCESS");

        return this.plugins;
    }

    /**
     * @return The last error, if there is one
     */
    public final String getError() {
        if (result != FAILURE)
            throw new UnsupportedOperationException("Cannot get error that occured unless response type is FAILURE");

        return this.error;
    }

    /**
     * @return The outcome of the request, SUCCESS or FAILURE
     */
    public final Result getResult() {
        return this.result;
    }

    public enum Result {
        SUCCESS, FAILURE
    }
}
