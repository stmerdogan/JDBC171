public class Test01 {

    public static void main(String[] args) {
        //Test beklenen data ve asıl datanın karşılaştırılmasıdır

        String expectedData = "Hello";

        String actualData = "Hello";

        if(expectedData.equals(actualData)){
            System.out.println("Test PASSED");
        }else {
            System.out.println("Test FAILED");
        }

        //Otomasyon testi yapabilmek için bir "Test Framework"üne ihtiyacımız var.(JUnit, Test NG, Cucumber ...)

    }
}
