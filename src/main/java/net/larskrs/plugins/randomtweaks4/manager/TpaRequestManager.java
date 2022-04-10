package net.larskrs.plugins.randomtweaks4.manager;

import net.larskrs.plugins.randomtweaks4.object.TpaRequest;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.UUID;

public class TpaRequestManager {

    private static HashMap<UUID, TpaRequest> requests;

    public static void setUp() {
        requests = new HashMap<>();
    }

    public static boolean hasRequest (UUID uuid) {
        return requests.containsKey(uuid);
    }
    public static TpaRequest getRequest(UUID uuid) {
        if (hasRequest(uuid)) {
            requests.get(uuid);
        }
        return null;
    }

    public static void sendRequest(UUID rq, UUID recipient) {
        TpaRequest tpaRequest = new TpaRequest(rq, recipient);
        requests.put(recipient, tpaRequest);
        tpaRequest.start();
    }
}
