package es.dsw.TSpringBootProjectDemo3;



import jakarta.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

/* [3] - Se crea una clase para agrupar propiedades de los ficheros.
 * 
 * Se define una clase para generar objetos con los parámetros de los ficheros .properties que nos ineresa. Para ello, es necesario
 * disponer en un atributo estático de un objeto ServletContext.
 * 
 * RECUERDA: Todo atributo estático de cualquier clase es memoria de aplicación y puede ser accedida por todos los hilos (clientes) de la aplicación.
 * 
 */
@Component
public class ApplicationData {
	

	private static ServletContext ServContext;
	
	
	//En el método onApplicationEvent de la clase StartupListener (al iniciar la aplicación) se invoca a este método para obtener el ServletContext.
    public static void initializeDataContext(ServletContext arg) {
    	ServContext= arg;
    }
    
    //Método que devolverá al hilo del cliente que lo demande un bean (de esta clase) con los datos/parámetros de los ficheros planos.
    public static ApplicationData getApplicationData()  {

    	//Debes saber o recordar, que una de las particularidades de solicitar un bean a un contenedor en spring, es que estos (si no se indica
    	//lo contrario, como es en este caso), funcionan con el patrón singleton. Es decir, la primera vez que se solicita un bean, se crea. Pero la segunda
    	//o siguientes veces, se devuelve una instancia al mismo objeto (bean) proporcionado en la primera vez.
    	
    	//Por lo dicho anteriormente, la primera vez que un cliente lo solicita, se crea el bean (accediendo a Spring a los ficheros planos para realizar
    	//la inyeción de dependencias) pero las siguientes instancias (del mismo cliente, dentro de la misma sesión de usuario) no se vuelve a crear,
    	//sino que se devuelve la misma instancia al bean, por lo que se evita leer constantemente de los ficheros.
    	
    	//No obstante, tal y como tenemos programado este ejemplo, por cada cliente diferente, si se creará un nuevo bean, pues por mucho que este método sea
    	//estático, se está instanciando desde una controladora (ver MainController.java) y el bean lo crea cada instancia de usuario. 
    	
    	//Si desearamos garantizar que Spring solo realice una vez la lectura de los ficheros planos (para minimizar tiempos/costes), ¿Cómo podríamos garantizarlo?.
    	
    
		return  WebApplicationContextUtils.getWebApplicationContext(ServContext).getBean(ApplicationData.class);
    }
    
	@Value("${app3.proyecto}")
	private String Proyecto;

	@Value("${app3.objetivo1}")
	private String Objetivo1;
	
	@Value("${app3.objetivo2}")
	private String Objetivo2;
	
	@Value("${app3.objetivo3}")
	private String Objetivo3;
	
	@Value("${app3.ide}")
	private String Ide;
	
	@Value("${app3.servidor}")
	private String Servidor;
	
	@Value("${app3.jdk}")
	private String Jdk;
	
	@Value("${app3.gestorproyecto}")
	private String Gestorproyecto;
	
	@Value("${app3.dependencias}")
	private String Dependencias;
	
	
	
	public String getProyecto() {
		return Proyecto;
	}

	public String getObjetivo1() {
		return Objetivo1;
	}

	public String getObjetivo2() {
		return Objetivo2;
	}

	public String getObjetivo3() {
		return Objetivo3;
	}

	public String getIde() {
		return Ide;
	}

	public String getServidor() {
		return Servidor;
	}

	public String getJdk() {
		return Jdk;
	}

	public String getGestorproyecto() {
		return Gestorproyecto;
	}

	public String getDependencias() {
		return Dependencias;
	}

}