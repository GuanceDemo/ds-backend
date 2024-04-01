import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Base64Test {

    @Test
    public void testJavaBase64() throws Exception {

        String originalInput = "HelloWorld!";
        Base64 base64 = new Base64();
        String encodedString = new String(base64.encode(originalInput.getBytes()));

        assertEquals("SGVsbG9Xb3JsZCE=", encodedString);
    }
}
