package ProjectIntegration.Managers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class AndroidDriverManager{
    private static AndroidDriver driver;
    private static AppiumDriverLocalService server;

    private static Process process;

    public static void startEmulator(String avdName) throws IOException {
        try {
            String emulatorPath = "C:\\Users\\singh\\AppData\\Local\\Android\\Sdk\\emulator\\emulator.exe";
            String command = emulatorPath + " -avd " + avdName;
            process = Runtime.getRuntime().exec(command);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
              Thread.sleep(60000);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void startAppiumService()
    {
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "C:\\Program Files\\nodejs;" + System.getenv("PATH"));
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .withAppiumJS(new File("C:\\Users\\singh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .usingPort(4723)
                .withEnvironment(environment)
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .withLogFile(new File("AppiumLog.txt"));
        server = AppiumDriverLocalService.buildService(builder);
        System.out.println("Server started at :" + server.getUrl());
        server.start();
        System.out.println("Server is Started.");
    }

    public static AndroidDriver getDriver() throws IOException {
        //startEmulator("emulator");
        startAppiumService();
        if(driver == null)
        {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("emulator");
            options.setPlatformName("Android");
            options.setApp("C:\\Users\\singh\\OneDrive\\Documents\\Development\\Projects\\ProjectIntegration\\src\\main\\resources\\General-Store.apk");
            options.setCapability("adbExecTimeout", 30000);

            try
            {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void quitDriver()
    {
        if(driver != null)
        {
            driver.quit();
            driver = null;
        }
        server.stop();
        //process.destroy();
    }
}
