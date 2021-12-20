package tests;

import com.google.common.cache.Cache;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.time.Duration;



public class NPtests {
        public WebDriver driver;
        public WebDriverWait wait;

       @BeforeTest
       public void setUp () {
          driver = new ChromeDriver();
//         driver = new FirefoxDriver();
//         driver = new SafariDriver();
          driver.manage().window().maximize();
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
          wait = new WebDriverWait(driver, 10);
        }
        @Test
        public void findOffice () {
           driver.get("https://novaposhta.ua/ru");
           driver.findElement(By.linkText("Отделения")).click();
           driver.findElement(By.xpath("//*[@id='top_menu']/li[5]/ul/li[3]/a")).click();
           WebElement city = driver.findElement(By.id("oCityFilter"));
           city.sendKeys("Киев");
           driver.findElement(By.id("li8d5a980d-391c-11dd-90d9-001a92567626")).click();
           driver.findElement(By.id("oWarehouseFilter")).click();
           WebElement officeNum = driver.findElement(By.id("oWarehouseFilter"));
           officeNum.sendKeys("Отделение №203");
           driver.findElement(By.id("oSearchFilter")).click();

           String address = driver.findElement(By.xpath("//span[text() = 'Киев, просп. Отрадный, 6/1']")).getText();
           Assertions.assertThat(address).contains("Киев, просп. Отрадный, 6/1");

           String type = driver.findElement(By.xpath("//span[text() = 'Отд                    203 ']")).getText();
           Assertions.assertThat(type).contains("Отд");

           String weight = driver.findElement(By.xpath("//span[text() = '30 кг']")).getText();
           Assertions.assertThat(weight).contains("30 кг");



        }
        @Test
        public void deliveryCost(){
           driver.get("https://novaposhta.ua/ru/office/view/id/203/city/%D0%9A%D0%B8%D0%B5%D0%B2");
           driver.findElement(By.xpath("//span[text() = 'Стоимость доставки']")).click();
           WebElement city = driver.findElement(By.id("DeliveryForm_senderCity"));
           city.sendKeys("Київ");
           driver.findElement(By.xpath("//span[text() = 'м. Київ, Київська обл.']")).click();
           WebElement cityRecepient = driver.findElement(By.id("DeliveryForm_recipientCity"));
           cityRecepient.sendKeys("Дніпро");
           driver.findElement(By.xpath("//span[text() = 'м. Дніпро, Дніпропетровська обл.']")).click();
           driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][cost]']")).click();
           WebElement cost = driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][cost]']"));
           cost.sendKeys("1000");
           driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][weight]']")).click();
           WebElement weight = driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][weight]']"));
           weight.sendKeys("10");
           driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][volumetricLength]']")).click();
           WebElement length = driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][volumetricLength]']"));
           length.sendKeys("20");
           driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][volumetricWidth]']")).click();
           WebElement width = driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][volumetricWidth]']"));
           width.sendKeys("30");
           driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][volumetricHeight]']")).click();
           WebElement height = driver.findElement(By.cssSelector("input[name = 'DeliveryForm[optionsSeat][1][volumetricHeight]']"));
           height.sendKeys("15");
           WebElement deliveryFloor  =  driver.findElement(By.id("DeliveryForm_floorCountAsc"));
           deliveryFloor.sendKeys("7");
           driver.findElement(By.cssSelector("input[name = 'yt0']")).click();
           String Text = driver.findElement(By.tagName("h3")).getText();
           Assertions.assertThat(Text).contains("Расчетная стоимость доставки");
        }

        @Test
    public void vacancy(){
           driver.get("https://novaposhta.ua");
           driver.findElement(By.linkText("Вакансії")).click();
           driver.switchTo().frame("RUAFRAME");
           driver.findElement(By.name("ddlRubric")).click();
           driver.findElement(By.xpath("//*[@id='ddlRubric']/option[3]")).click();
           driver.findElement(By.id("GridView1_ctl04_btnViewVacancy")).click();
           driver.findElement(By.className("vac_links_apply")).click();
           driver.get("https://rabota.ua/company477532/vacancy8842242?mode=apply#apply");
           driver.findElement(By.xpath("//span[text() = 'Откликнуться']")).click();
           By fileInput = By.cssSelector("input[type=file]");
           String filePath = "/Users/mariia.karpenko/Desktop/np";
           driver.findElement(fileInput).sendKeys(filePath);
       }

    @AfterTest
        public void tearDown (){
         driver.close();
       }
    }
