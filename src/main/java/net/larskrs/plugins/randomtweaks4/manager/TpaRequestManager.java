package net.larskrs.plugins.randomtweaks4.manager;

import net.larskrs.plugins.randomtweaks4.object.TpaRequest;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TpaRequestManager {

    private static List<TpaRequest> requests;

    public static void setUp() {
        requests = new ArrayList<>();
    }

    public static boolean hasRequest (UUID uuid) {
        for (TpaRequest tpaRequest : requests) {
            return tpaRequest.recipient == uuid;
        }
        return false;
    }
    public static TpaRequest getRequest(UUID uuid) {
        if (hasRequest(uuid)) {
            for (TpaRequest tpaRequest : requests) {
                if (tpaRequest.recipient == uuid) {
                    return tpaRequest;
                }
            }
        }
        return null;
    }

    public static void sendRequest(UUID rq, UUID recipient) {
        TpaRequest tpaRequest = new TpaRequest(rq, recipient);
        requests.add(tpaRequest);
        tpaRequest.start();
    }
    public static void closeRequest(TpaRequest r) {
        requests.remove(r);
    }
}
