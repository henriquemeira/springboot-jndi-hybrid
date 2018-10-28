package co.h2a.sandbox.springbootjndi.config;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name = "spring.datasource.jndi-name")
public class TomcatConfig {

    @Value("${spring.datasource.jndi-name:}")
    private String jndiName;

    @Value("${spring.datasource.driver-class-name:org.h2.Driver}") // Default org.h2.Driver
    private String driverClassName;

    @Value("${spring.datasource.url:jdbc:h2:mem:testdb}") // Defautl jdbc:h2:mem:testedb
    private String jdbcUrl;

    @Value("${spring.datasource.username:sa}") // Default sa
    private String username;

    @Value("${spring.datasource.password:}") // Default ""
    private String password;


    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {

        return new TomcatServletWebServerFactory() {
            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                tomcat.enableNaming();

                System.out.println("DEBUG: TomcatWebServer criado");

                return super.getTomcatWebServer(tomcat);
            }

            @Override
            protected void postProcessContext(Context context) {

                // context
                ContextResource resource = new ContextResource();
                resource.setName(jndiName);
                resource.setType(DataSource.class.getName());
                resource.setProperty("factory", "com.zaxxer.hikari.HikariJNDIFactory");
                resource.setProperty("driverClassName", driverClassName);
                resource.setProperty("jdbcUrl", jdbcUrl);
                resource.setProperty("username", username);
                resource.setProperty("password", password);
                context.getNamingResources().addResource(resource);

                System.out.println("DEBUG: postProcessContext executado");

            }
        };
    }


    @Bean
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException
    {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:/comp/env/" + jndiName);
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();

        System.out.println("DEBUG: jndiDatasource criado");

        return (DataSource) bean.getObject();
    }

}
