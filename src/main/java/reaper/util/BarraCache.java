package reaper.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author keenan on 20/11/2017
 */
public class BarraCache {
    private Map<Integer, Map<String, Double>> cache;

    private volatile static BarraCache barraCache;

    private BarraCache() {
        cache = new HashMap<>();
    }

    public static BarraCache getBarraCache() {
        if (barraCache == null) {
            synchronized (BarraCache.class) {
                if (barraCache == null) {
                    barraCache = new BarraCache();
                }
            }
        }
        return barraCache;
    }

    public boolean saveToCache(Integer userID, Map<String, Double> fundRatio) {
        if (userID == null) {
            return false;
        } else if (cache.get(userID) != null) {
            return false;
        } else {
            cache.put(userID, fundRatio);
            return true;
        }
    }

    public boolean removeFromCache(Integer userID) {
        if (userID == null) {
            return false;
        } else if (cache.get(userID) == null) {
            return false;
        } else {
            cache.remove(userID);
            return true;
        }
    }

    public Map<String, Double> findFromCache(Integer userID) {
        return cache.get(userID);
    }
}
