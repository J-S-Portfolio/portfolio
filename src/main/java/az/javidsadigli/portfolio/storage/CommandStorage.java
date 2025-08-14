package az.javidsadigli.portfolio.storage;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommandStorage 
{
    private Map<String, String> commandOutputs; 
    
    @Value("${application.contact-information.email}")
    private String emailInfo; 

    @Value("${application.contact-information.phone}")
    private String phoneInfo; 

    @Value("${application.contact-information.linkedin-url}")
    private String linkedinUrlInfo; 

    @PostConstruct
    public void initialize() 
    {
        this.commandOutputs = new HashMap<String, String>(); 

        this.commandOutputs.put("help", """

            HELP SYSTEM OF JAVID SADIGLI'S WEBSITE

                help            Displays a list of commands that can be runned 
                clear           Clears the console
                cv              Downloads CV of Javid Sadigli
                about           Gives information about Javid Sadigli
                contact         Shows contact information to reach Javid Sadigli

            """);

        this.commandOutputs.put("about", """

            ABOUT JAVID SADIGLI

                Experienced Software Engineer with a strong focus on Java and Spring Boot for building scalable, 
                high-performance backend systems. Skilled in designing and implementing RESTful APIs, developing 
                microservices architectures, and working with both SQL and NoSQL databases. Proficient in leveraging 
                modern tools and platforms including Kubernetes for container orchestration, and Prometheus with 
                Grafana for monitoring and observability. Committed to writing clean, maintainable code and collaborating 
                effectively with cross-functional teams to deliver reliable and impactful software solutions.

            """);

        this.commandOutputs.put("contact", String.format("""

            CONTACT INFORMATION OF JAVID SADIGLI

                Email: %s
                Phone number: %s
                LinkedIn: %s
                
            """, emailInfo, phoneInfo, linkedinUrlInfo));
    }   
    
    public String getCommandOutput(String command) 
    {
        return this.commandOutputs.get(command);
    } 
    
    public String getCommandNotFoundOutput(String command)
    {
        return String.format(
            "Command not found: '%s'.\nNote: You can run 'help' command to see all commands that can be runned.", 
            command);
    }
}
