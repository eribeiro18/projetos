import java.io.FileReader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Exemplo01 {

	//Nashrorn nova feature do java 8 para executar implementações em JavaScript pela JVM
	public static void main(String[] args) {
		try {
			ScriptEngine ee = new ScriptEngineManager().getEngineByName("Nashorn");
			Bindings bind = ee.getBindings(ScriptContext.ENGINE_SCOPE);
			//bind serve para setar algum valor ou mudar algo dentro do script em javascript
			bind.put("goodbye", " Até logo!");
			ee.eval(new FileReader("olamundo.js"));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
