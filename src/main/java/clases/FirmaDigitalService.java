
package clases;



import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class FirmaDigitalService {

    public String firmarDocumento(String contenidoDocumento) {
        try {
            
            KeyPair keyPair = generarParDeClaves();

            
            PrivateKey privateKey = keyPair.getPrivate();

            
            String firma = firmarContenido(privateKey, contenidoDocumento);

           
            return firma;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private KeyPair generarParDeClaves() throws Exception {
        
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    private String firmarContenido(PrivateKey privateKey, String contenido) throws Exception {
        
        Signature firma = Signature.getInstance("SHA256withRSA");
        firma.initSign(privateKey);

      
        firma.update(contenido.getBytes("UTF-8"));

        
        byte[] firmaBytes = firma.sign();

        
        return new String(firmaBytes, "UTF-8");
    }
}
