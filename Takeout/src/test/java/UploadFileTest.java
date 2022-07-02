import org.junit.jupiter.api.Test;

/**
 * @author KaibutsuSama
 * @date 2022/7/1
 */
public class UploadFileTest {
    @Test
    public void test1(){
        String fileName = "ererewe.jpg";
        String substring = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(substring);
    }
}
