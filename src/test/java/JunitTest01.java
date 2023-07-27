import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class JunitTest01 {

  @Test
  public void test01(){
    //Test geçti veya kaldı gibi dönüt alabilmek için doğrulama(assertion) yapmamız gerekir.

    //Assertion türleri:
    String expectedData = "Hello";
    String actualData = "Hello";

    assertEquals(expectedData,actualData);//assertEquals() methodu --> parametereleri eşitse test geçer, değilse kalır
    assertTrue(actualData.length()>1);//assertTrue() methodu --> parantez içindeki değeri true dönerse test geçer

    //Negative test için:
    assertFalse(actualData.contains("X"));//assertFalse() methodu --> parantez içindeki değer false dönerse test geçer
    assertNotEquals(1,2);//assertNotEquals() -->parametereleri eşit değilse test geçer

  }

  @Test
  public void test02(){


  }

  @Test
  public void test03(){



  }

}