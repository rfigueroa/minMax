package org.example;

import java.util.Arrays;

public record NormalizedStore(String name, double average, double netSales, double txCount, double cashOverShort, double bevCount, double sos, double discount) {

    public NormalizedStore(Store store, StoreAggregator aggregator){
        this(store.name(),
                normalize(store.netSales(), aggregator.maxSales, aggregator.minSales),
                normalize(store.txCount(), aggregator.maxTxCount, aggregator.minTxCount),
                normalize(store.cashOverShort(), aggregator.maxCash, aggregator.minCash),
                normalize(store.bevCount(), aggregator.maxBev, aggregator.minBev),
                normalize(store.sos(), aggregator.maxSos, aggregator.minSos),
                normalize(store.discount(), aggregator.maxDiscount, aggregator.minDiscount)
                );
    }

    private NormalizedStore(String name, double netSales, double txCount, double cashOverShort, double bevCount, double sos, double discount) {
        this(name, avg(netSales, txCount, cashOverShort, bevCount, sos, discount), netSales, txCount, cashOverShort, bevCount, sos, discount);
    }

    private static double avg(double ... values) {
        return Arrays.stream(values).average().orElse(Double.NaN);
    }

    private static double normalize(double value, double max, double min) {
        return (value - min) / (max - min);
    }

}
