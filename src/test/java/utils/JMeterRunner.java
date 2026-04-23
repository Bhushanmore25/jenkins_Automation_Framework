package utils;

public class JMeterRunner {

    public static void run(String file) {
        try {
            String jmeterPath = "D:\\Capgemini\\ApacheJemter\\apache-jmeter-5.6.3\\bin\\jmeter.bat";

            String command = jmeterPath + " -n -t " + file + " -l jmeter/results/result.jtl";

            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            System.out.println("✅ JMeter executed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}