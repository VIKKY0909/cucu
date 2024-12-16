package environment;

public class DriverSelector {
    public DriverConfiguration getDriver(String driverConfig) {
        if (driverConfig == null || driverConfig.equalsIgnoreCase("WebLocal")) {
            return new WebLocal();
        } else if (driverConfig.equalsIgnoreCase("WebRemote")) {
            return new WebRemote();
        }
        return new WebLocal(); // Default to WebLocal
    }
}
