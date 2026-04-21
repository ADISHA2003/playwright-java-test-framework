package Listeners;
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import io.prometheus.client.exporter.HTTPServer;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Map;

public class GrafanaTestListener implements ITestListener {

    // Counter for test results (labels: test name, status)
    private static final Counter testCounter = Counter.build()
            .name("api_test_results_total")
            .help("Total API test results")
            .labelNames("testName", "status")
            .register();

    // Histogram to record response times (in seconds)
    private static final Histogram responseTimeHistogram = Histogram.build()
            .name("api_test_response_time_seconds")
            .help("API test response times in seconds")
            .labelNames("testName")
            .register();

    // Optionally, start an HTTP server to expose metrics
    private static HTTPServer server;

    static {
        try {
            // Start HTTP server on port 9091
            server = new HTTPServer(9091);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        recordTestMetrics(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        recordTestMetrics(result, "FAIL");
    }

    private void recordTestMetrics(ITestResult result, String status) {
        String testName = result.getName();

        // Retrieve metrics from the test (if any)
        @SuppressWarnings("unchecked")
        Map<String, Object> metrics = (Map<String, Object>) result.getAttribute("apiMetrics");

        if (metrics != null) {
            // Record test result counter
            testCounter.labels(testName, status).inc();

            // Record response time histogram (convert milliseconds to seconds)
            Long responseTimeMs = (Long) metrics.get("responseTime");
            if (responseTimeMs != null) {
                double responseTimeSec = responseTimeMs / 1000.0;
                responseTimeHistogram.labels(testName).observe(responseTimeSec);
            }
        }
    }
}
