package org.example;

public class StoreAggregator {
    public double minSales = Double.POSITIVE_INFINITY;
    public double maxSales = Double.NEGATIVE_INFINITY;
    public int minTxCount = Integer.MAX_VALUE;
    public int maxTxCount = Integer.MIN_VALUE;
    public double minCash = Double.POSITIVE_INFINITY;
    public double maxCash = Double.NEGATIVE_INFINITY;
    public int minBev = Integer.MAX_VALUE;
    public int maxBev = Integer.MIN_VALUE;
    public int minSos = Integer.MAX_VALUE;
    public int maxSos = Integer.MIN_VALUE;
    public double minDiscount = Double.POSITIVE_INFINITY;
    public double maxDiscount = Double.NEGATIVE_INFINITY;

    @Override
    public String toString() {
        return "StoreAggregator{" +
                "minSales=" + minSales +
                ", maxSales=" + maxSales +
                ", minTxCount=" + minTxCount +
                ", maxTxCount=" + maxTxCount +
                ", minCash=" + minCash +
                ", maxCash=" + maxCash +
                ", minBev=" + minBev +
                ", maxBev=" + maxBev +
                ", minSos=" + minSos +
                ", maxSos=" + maxSos +
                ", minDiscount=" + minDiscount +
                ", maxDiscount=" + maxDiscount +
                '}';
    }
}
