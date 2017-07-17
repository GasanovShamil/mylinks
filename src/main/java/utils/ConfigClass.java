package utils;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConfigClass {
	public static List<String> USED_URLS_LIST = Collections.unmodifiableList(Arrays.asList(new String[] {"stats","createUrl", "index", "login", "redirect","logout","createUser","manageUrl","csvManager","account","errorPage.jsp"}));
	public static String baseUrl = "http://localhost:8080/mylinks/";
}
 