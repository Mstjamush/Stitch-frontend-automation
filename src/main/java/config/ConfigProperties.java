/*
# set & get environment/globals variables
*/

package config;


public class ConfigProperties {
    public static config.ApplicationConfigReader appConfig = new config.ApplicationConfigReader();
    public static String htmlReportPath = appConfig.getHtmlReportPath();
    public static String baseUrl = appConfig.getBaseUrl();
    public static String dashboardUrl = appConfig.getDashboardUrl();
    public static String isReportingEnable = appConfig.getIsEnableReporting();

    public static String sendEmail = appConfig.getSendEmailAfterExecution();
    public static String from = appConfig.getFrom();
    public static String[] To = appConfig.getTo();
    public static String fromPassword = appConfig.getFromPassword();

    public static String Project = appConfig.getProject();
    public static String Platform = appConfig.getPlatform();
    public static String Environment = appConfig.getEnviroment();

    public static String Device = appConfig.getDevice();
    public static String Browser = appConfig.getBrowser();
    public static String isHeadless = appConfig.getHeadless();



}
