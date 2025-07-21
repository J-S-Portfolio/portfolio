package az.javidsadigli.portfolio.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ConsoleStorage 
{
    private Map<String, String> commandOutputs; 

    public ConsoleStorage() 
    {
        this.commandOutputs = new HashMap<String, String>(); 

        this.commandOutputs.put("help", """
HELP SYSTEM OF JAVID SADIGLI'S WEBSITE

    help            Displays a list of commands that can be runned 
    clear           Clears the console
    cv              Downloads CV of Javid Sadigli
    about           Gives a description about Javid Sadigli
    contact         Shows contact information to reach Javid Sadigli
            """);

        this.commandOutputs.put("about", """
ABOUT JAVID SADIGLI

    Experienced Software Engineer with a strong focus on Java and Spring Boot for building scalable, high-performance backend 
    systems. Skilled in designing and implementing RESTful APIs, developing microservices architectures, and working with both 
    SQL and NoSQL databases. Proficient in leveraging modern tools and platforms including Kubernetes for container 
    orchestration, and Prometheus with Grafana for monitoring and observability. Committed to writing clean, maintainable code 
    and collaborating effectively with cross-functional teams to deliver reliable and impactful software solutions.
            """);

        this.commandOutputs.put("contact", """
CONTACT INFORMATION OF JAVID SADIGLI

    Email: j.sadigli@ufaz.az
    Phone number: +994 50 790 55 30
    LinkedIn: https://www.linkedin.com/in/cavidsad%C4%B1ql%C4%B1/
            """);
    }   
    
    public String getCommandOutput(String command) 
    {
        String output = this.commandOutputs.get(command);

        return Optional.ofNullable(output)
            .orElse(String.format("Command not found : '%s'", command));
    }    
}
