
package clases;


import co.com.example.servlets.Factura;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Date;

public class ValidadorFacturas {

   
    public boolean validarFactura(Factura factura, PublicKey clavePublicaEmisor) {
        try {
            
            if (!verificarFirma(factura, clavePublicaEmisor)) {
                System.out.println("Firma digital no válida para la factura: " + factura.getNumeroFactura());
                return false;
            }

            

            
            if (!esFacturaReciente(factura)) {
                System.out.println("La factura no es reciente: " + factura.getNumeroFactura());
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean verificarFirma(Factura factura, PublicKey clavePublicaEmisor) throws Exception {
       
        String firmaRecibida = factura.getFirmaDigital();
        String contenidoFactura = construirContenidoFactura(factura);

        
        Signature verificadorFirma = Signature.getInstance("SHA256withRSA");
        verificadorFirma.initVerify(clavePublicaEmisor);

        
        verificadorFirma.update(contenidoFactura.getBytes("UTF-8"));

   
        return verificadorFirma.verify(firmaRecibida.getBytes("UTF-8"));
    }

    private boolean esFacturaReciente(Factura factura) {
    try {
        
        if (factura == null) {
            System.out.println("Error: La factura es null.");
            return false;
        }

        Date fechaActual = new Date();

        
        if (fechaActual == null) {
            System.out.println("Error: La fecha actual es null.");
            return false;
        }

        // Verificar si factura.getFecha() es null
        Date fechaFactura = (Date) factura.getFecha();
        if (fechaFactura == null) {
            System.out.println("Error: La fecha de la factura es null.");
            return false;
        }

        
        long diferenciaEnMillis = fechaActual.getTime() - fechaFactura.getTime();

       
        long diferenciaEnDias = diferenciaEnMillis / (24 * 60 * 60 * 1000);

        return diferenciaEnDias <= 30;
    } catch (Exception e) {
        
        e.printStackTrace();
        return false;
    }
    
}



    private String construirContenidoFactura(Factura factura) {

        return factura.getNumeroFactura() + factura.getCliente() + factura.getMontoTotal();
    }
}

