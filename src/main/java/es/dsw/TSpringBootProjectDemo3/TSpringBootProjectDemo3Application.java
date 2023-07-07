package es.dsw.TSpringBootProjectDemo3;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


/*[1] - NUEVO FICHERO DE PARÁMETROS
 * 
 * La novedad en este código, es la presencia de la java annotation @PropertySource("classpath:otrosparametros.properties")
 * Esta java annotation le indica a spring boot que hay un nuevo fichero de propiedades (.properties) en src/main/resources.
 * Si no se indica, por muchos ficheros que creés, spring boot los obviará.
 */
@SpringBootApplication
@ComponentScan(basePackages = "es.dsw")
@PropertySource("classpath:otrosparametros.properties")
public class TSpringBootProjectDemo3Application {
	
	public static void main(String[] args) {
		SpringApplication.run(TSpringBootProjectDemo3Application.class, args);
		
	}

}
