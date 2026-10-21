package game;

import java.net.URL;

public final class Resources {
    private Resources() {
    }

    public static URL url(String path) {
        String normalisedPath = path.startsWith("/") ? path.substring(1) : path;

        URL resource = Resources.class.getClassLoader().getResource(normalisedPath);

        if (resource == null) {
            throw new IllegalArgumentException(
                    "Classpath resource not found: " + normalisedPath);
        }

        return resource;
    }
}
