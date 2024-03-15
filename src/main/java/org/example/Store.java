package org.example;

public record Store(String name, double netSales, int txCount, double cashOverShort, int bevCount, int sos, double discount)  {

    public Store(String[] values) {
        this(values[0],
                Double.parseDouble(values[1].trim()),
                Integer.parseInt(values[2].trim()),
                Double.parseDouble(values[3].trim()),
                Integer.parseInt(values[4].trim()),
                Integer.parseInt(values[5].trim().trim()),
                Double.parseDouble(values[6].trim())
        );

    }

}
