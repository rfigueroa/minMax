package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        Collector<Store, StoreAggregator, StoreAggregator> collector = Collector.of(
                StoreAggregator::new, //supply
                (a, m) -> { //accumulate
                    System.out.println("accumulator");
                    a.maxSales = Math.max(a.maxSales, m.netSales());
                    a.minSales = Math.min(a.minSales, m.netSales());

                    a.maxTxCount = Math.max(a.maxTxCount, m.txCount());
                    a.minTxCount = Math.min(a.minTxCount, m.txCount());

                    a.maxCash = Math.max(a.maxCash, m.cashOverShort());
                    a.minCash = Math.min(a.minCash, m.cashOverShort());

                    a.maxBev = Math.max(a.maxBev, m.bevCount());
                    a.minBev = Math.min(a.minBev, m.bevCount());

                    a.maxSos = Math.max(a.maxSos, m.bevCount());
                    a.minSos = Math.min(a.minSos, m.sos());

                    a.maxDiscount = Math.max(a.maxDiscount, m.discount());
                    a.minDiscount = Math.min(a.minDiscount, m.discount());
                },
                (storeAggregator, storeAggregator2) -> { //combine

                    var res = new StoreAggregator();
                    res.maxSales = Math.max(storeAggregator.maxSales, storeAggregator2.maxSales);
                    res.minSales = Math.min(storeAggregator.minSales, storeAggregator2.minSales);
                    res.maxTxCount = Math.max(storeAggregator.maxTxCount, storeAggregator2.maxTxCount);
                    res.minTxCount = Math.min(storeAggregator.minTxCount, storeAggregator2.minTxCount);
                    res.maxCash = Math.max(storeAggregator.maxCash, storeAggregator2.maxCash);
                    res.minCash = Math.min(storeAggregator.minCash, storeAggregator2.minCash);
                    res.maxBev = Math.max(storeAggregator.maxBev, storeAggregator2.maxBev);
                    res.minBev = Math.min(storeAggregator.minBev, storeAggregator2.minBev);
                    res.maxSos = Math.max(storeAggregator.maxSos, storeAggregator2.maxSos);
                    res.minSos = Math.min(storeAggregator.minSos, storeAggregator2.minSos);
                    res.maxDiscount = Math.max(storeAggregator.maxDiscount, storeAggregator2.maxDiscount);
                    res.minDiscount = Math.min(storeAggregator.minDiscount, storeAggregator2.minDiscount);
                    return res;
                }, (agg -> agg) // finish
        );

        List<Store> stores = Files.lines(Paths.get("store-summary-data.csv"))
                .skip(1) // remove header
                .filter(Predicate.not(String::isEmpty)) //remove empty lines
                .map(s -> new Store(s.split(","))).toList(); //cache

        StoreAggregator aggregator = stores.stream().collect(collector); // aggregate

        List<NormalizedStore> normalizedStores = stores.stream().map(store -> new NormalizedStore(store, aggregator)).sorted(Comparator.comparing(NormalizedStore::average)).toList();

        normalizedStores.forEach(System.out::println);


    }


}