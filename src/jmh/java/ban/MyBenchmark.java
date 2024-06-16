package ban;

import ban.inspector.service.BanWordService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Component
@SpringBootApplication
@State(Scope.Benchmark)
public class MyBenchmark {


    static ConfigurableApplicationContext context = new SpringApplicationBuilder()
        .sources(MyBenchmark.class)
        .web(WebApplicationType.NONE)
        .build().run();

    private BanWordService banWordService;
    private String word = "고르곤졸라피자 졸라맛있다.";

    @Setup(Level.Trial)
    public void setup(){
        banWordService = context.getBean("banWordService", BanWordService.class);
    }


    @Benchmark
    public void testMethod() {
        banWordService.valid(word);
    }

}
