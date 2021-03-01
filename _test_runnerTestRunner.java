import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class _test_runnerTestRunner {
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(_test_runnerTestSuite.class);
      JSONArray failures = new JSONArray();

        for (Failure failure : result.getFailures()) {
            JSONObject obj = new JSONObject();
            obj.put("name", failure.getTestHeader());
            obj.put("stack", failure.getTrace());
            failures.add(obj);
        }
        JSONObject out = new JSONObject();
        out.put("passed", result.wasSuccessful());
        out.put("failures", failures);
        System.out.println();
        System.out.println("__UNIT_TEST_RESULT_START__");
        System.out.println(out);
        System.out.println("__UNIT_TEST_RESULT_END__");
    }
}