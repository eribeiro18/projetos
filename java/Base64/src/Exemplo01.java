import java.util.Base64;

public class Exemplo01 {

	public static void main(String[] args) {
		try {
			String texto = "A classe Base64 no Java 8!";
			System.out.println("TEXTO =>" + texto);
			String textoCodificado = Base64.getEncoder().encodeToString(texto.getBytes("utf-8"));
			System.out.println("TEXTO CODIFICADO =>" + textoCodificado);
			String textoDecodificado = new String(Base64.getDecoder().decode(textoCodificado));
			System.out.println("TEXTO DECODIFICADO =>" + textoDecodificado);
			
			String url = "hhtps://www.google.com.br";
			String urlCodificado = Base64.getUrlEncoder().encodeToString(url.getBytes("utf-8"));
			System.out.println("URL CODIFICADO =>" + urlCodificado);
			String urlDecodificado = new String(Base64.getDecoder().decode(urlCodificado));
			System.out.println("URL DECODIFICADO =>" + urlDecodificado);
			
			//O mesmo existe para Mime
		} catch (Exception e) {
			
		}
	}
}
