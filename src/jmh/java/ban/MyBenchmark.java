package ban;

import ban.inspector.inspector.Inspector;
import org.openjdk.jmh.annotations.*;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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

    private Inspector inspector;
    private String word = "고르곤졸라피자 졸라맛있다.";

    @Setup(Level.Trial)
    public void setup(){
        inspector = context.getBean("banWordService", Inspector.class);
    }


    @Benchmark
    public void testMethod() {
        inspector.inspect(word);
    }

}
