
interface Data {
//    int timestamp; String songId; String userId; Double streamDuration; String subscriptionType;
    public Event getEvent();
}
class GDPRData implements Data {
    @Override
    public Event getEvent() {
        return new Event();
    }
}
class FullData implements Data {
    @Override
    public Event getEvent() {
        // assume some fields are masked here
        return new Event();
    }
}
class MixedData implements Data {
    @Override
    public Event getEvent() {
        // assume different events
        return new Event();
    }
}

class Event {
    int timestamp; String songId; String userId; Double streamDuration; String subscriptionType;
}
interface Region {
    public Data getCompliantData();
}
class US implements Region {
    public static volatile US instance;
    public static US getInstance() {
        if (instance == null) {
            synchronized (RegionFactory.class) {
                if (instance == null) instance = new US();
            }
        }
        return instance;
    }
    @Override
    public Data getCompliantData() {
        return new FullData();
    }
}
class Europe implements Region {
    // same singleton pattern for Europe
    @Override
    public Data getCompliantData() {
        return new GDPRData();
    }
}
class APAC implements Region {
    // same singleton pattern for APAC
    @Override
    public Data getCompliantData() {
        return new MixedData();
    }
}
class RegionFactory {
    public static volatile RegionFactory instance;
    private Map<String, Region> regionMap;
    private RegionFactory() {
        this.regionMap = new HashMap<>();
        regionMap.putIfAbsent("US", US.getInstance());
        regionMap.putIfAbsent("Europe", Europe.getInstance());
        regionMap.putIfAbsent("APAC", Europe.getInstance());
    }
    public Region getRegion(String input) {
        return this.regionMap.get(input);
    }
    public static RegionFactory getInstance() {
        if (instance == null) {
            synchronized (RegionFactory.class) {
                if (instance == null) instance = new RegionFactory();
            }
        }
        return instance;
    }
}
interface Metrics {
    public Double processAnalytics(Data data);
}
class ContentMetrics implements Metrics {
    @Override
    public Double processAnalytics(Data data) {
        return 0.0;
    }
}
class MarketingMetrics implements Metrics {
    @Override
    public Double processAnalytics(Data data) {
        return 0.0;
    }
}
class FinanceMetrics implements Metrics {
    @Override
    public Double processAnalytics(Data data) {
        return 0.0;
    }
}
//--> takes care of creation of which kind of metric
class MetricsFactory {
    // assume singleton here too
    public static MetricsFactory getInstance() {
        return new MetricsFactory();
    }
    public Double getMetrics(Data data, String teamType) {
        return switch (teamType) {
            // assume we are caching
            case "Marketing" -> new MarketingMetrics().processAnalytics(data);
            case "Content" -> new ContentMetrics().processAnalytics(data);
            case "Finance" -> new FinanceMetrics().processAnalytics(data);
            default -> null;
        };
    }
}

class MetricsHandler {
    // stores event wise scores, if asked again returns from the map. no duplicate processing.
    // avoids recalculation
    public Double getMetrics(String location, String teamType) {
        // Europe, Marketing
        Region reg = RegionFactory.getInstance().getRegion(location);
        return MetricsFactory.getInstance().getMetrics(reg.getCompliantData(), teamType);
    }
}
