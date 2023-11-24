import clases.Factura;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FacturacionElectronicaService {

    public boolean enviarFactura(Factura factura) {
        try {
           
            System.out.println("Creando XML y firmando digitalmente...");

           
            System.out.println("Enviando factura electrónica:");
            System.out.println("Número de factura: " + factura.getNumeroFactura());
            System.out.println("Cliente: " + factura.getCliente());
            System.out.println("Fecha: " + factura.getFecha());
            System.out.println("Monto Total: " + factura.getMontoTotal());
            System.out.println("firmaDigital"+factura.getFirmaDigital());

           
            String respuestaServicio = enviarFacturaAlServicioWeb();

            
            if ("Exito".equals(respuestaServicio)) {
                System.out.println("La factura se envió con éxito al servicio web.");
                return true;
            } else {
                System.out.println("Error al enviar la factura al servicio web. Respuesta: " + respuestaServicio);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String enviarFacturaAlServicioWeb() throws Exception {
        
        URL url = new URL("https://serviciowebfacturacion.com/enviarfactura");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        
        String facturaXml = construirContenidoFactura();

        connection.getOutputStream().write(facturaXml.getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

       
        return response.toString();
    }

    private String construirContenidoFactura() {
        
        return "<factura><numero>123</numero><cliente>Cliente1</cliente><monto>100.00</monto></factura>";
    }
}
