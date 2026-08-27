package es.dsw.events;



//Importaciones necesarias para capturar el evento de inicio de la aplicación.
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//Importaciones necesarias solo para el código introducido dentro de onApplicationEvent
import org.springframework.web.context.WebApplicationContext;

import es.dsw.TSpringBootProjectDemo3.ApplicationData;
import jakarta.servlet.ServletContext;
import org.springframework.context.ApplicationContext;


//[2] - EVENTO DE INICIO DE APLICACIÓN (Opcional)
//La clase se puede llamar como desees, lo importante es que implemente la interfaz ApplicationListener<ContextRefreshedEvent>
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {


	//Evento de arranque de la aplicación: Cuando se ejecuta este evento, toda la api de spring ya se encuentra cargada así como sus contenedores (en el main() no se garantiza esto).
	//Es el lugar ideal para cargar cache de datos o lanzar nuevos hilos de ejecución que requieran procesamiento paralelo; refresco de caches de datos cada cierto tiempo, etc.
	//En este ejemplo, aunque los parámetros de los ficheros .properties pueden estar disponibles desde las controladoras, se carga en un atributo estático de la clase ApplicationData
	//de un objeto servletContext que necesitará para poder proporcionar a los hilos de los clientes ojetos con datos inyectados desde los ficheros .properties.
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        
    	//Lo que se hace en este trozo de código es obtener a partir del objeto de tipo ContextRefreshedEvent el servletContext. El servletContext, nos permitirá
    	//generar al hilo de cada cliente de un bean de la clase ApplicationData con datos de los ficheros .properties ya inyectados.
    	//El método estático ApplicationData.initializeDataContext le proporciona un servletContext para poder generar todos los beans que necesite el cliente en su contexto.
    	//IMPORTANTE: Para entender este código, es recomendable profundizar en el framework spring en cuanto a crear contenedores para generar beans: Inyección de dependencias.
    	ApplicationContext applicationContext = event.getApplicationContext();
    	WebApplicationContext webApplicationContext = (WebApplicationContext) applicationContext;
    	ServletContext servletContext = webApplicationContext.getServletContext();
    	
    	//A continuación, a cada clase que deseo que inyecte propiedades de los ficheros .properties, le proporciono el objeto servletContext. En este caso es solo una clase, si quisieras
    	//crear más clases, deberias duplicar la siguiente línea de código (cambiando el nombre de la clase claro).
		ApplicationData.initializeDataContext(servletContext);
    
    } 
}