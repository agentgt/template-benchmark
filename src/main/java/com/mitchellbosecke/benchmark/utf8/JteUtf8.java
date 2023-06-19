package com.mitchellbosecke.benchmark.utf8;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import com.mitchellbosecke.benchmark.BaseBenchmark;
import com.mitchellbosecke.benchmark.model.Stock;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.Utf8ByteOutput;

/**
 * Benchmark for jte templates.
 * <p>
 * https://github.com/casid/jte
 *
 * @author casid
 */
public class JteUtf8 extends BaseBenchmark {

    private TemplateEngine templateEngine;
    private List<Stock> items;

    @Setup
    public void setup() {
        items = Stock.dummyItems();

        templateEngine = TemplateEngine.createPrecompiled(Path.of("jte-classes"), ContentType.Html);
        templateEngine.setBinaryStaticContent(true);
        templateEngine.prepareForRendering("stocks.jte");
    }

    @Benchmark
    public byte[] benchmark() throws IOException {
        Utf8ByteOutput output = new Utf8ByteOutput();
        templateEngine.render("stocks.jte", items, output);
        return output.toByteArray();
    }
}
