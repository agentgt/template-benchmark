package com.mitchellbosecke.benchmark.utf8;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import com.mitchellbosecke.benchmark.BaseBenchmark;
import com.mitchellbosecke.benchmark.JStachio.StocksModel;
import com.mitchellbosecke.benchmark.JStachioStocksTemplate;
import com.mitchellbosecke.benchmark.model.Stock;

import io.jstach.jstachio.escapers.Html;
import io.jstach.jstachio.formatters.DefaultFormatter;

public class StringGetBytesUtf8 extends BaseBenchmark {

    private List<Stock> items;
    private StocksModel model;
    private JStachioStocksTemplate template;
    
    @Setup
    public void setup() {
        items = Stock.dummyItems();
        model = new StocksModel(items);
        template = new JStachioStocksTemplate(DefaultFormatter.provider(), Html.provider());
    }

    @Benchmark
    public byte[] benchmark() {
        StringBuilder sb = new StringBuilder(8 * 1024);
        return template.execute(model, sb).toString().getBytes(StandardCharsets.UTF_8);
    }
}