package testBase;

import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager; //for logger
import org.apache.logging.log4j.Logger; //for logger

public class BaseClass {

	public Logger logger;// for Logging

	public WebDriver driver;


	@BeforeClass
	@Parameters("browser")   // getting browser parameter from testng.xml
	public void setup(String br)
	{
		logger = LogManager.getLogger(this.getClass());// for Logger

		//launch right browser based on parameter
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver-win64\\chromedriver.exe");

			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*","ignore-certificate-errors");

			driver = new ChromeDriver(option);
		//	driver = new ChromeDriver();
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://demo.opencart.com/index.php");
		driver.manage().window().maximize();
	}

	@AfterClass
	public void teadDown() {
		driver.quit();
	}

	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	public String randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}

	public String randomAlphaNumeric() {
		String st = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);

		return (st+"@"+num);
	}

}
