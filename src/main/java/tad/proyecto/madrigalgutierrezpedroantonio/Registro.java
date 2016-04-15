package tad.proyecto.madrigalgutierrezpedroantonio;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.declarative.Design;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("tad.proyecto.madrigalgutierrezpedroantonio.MyAppWidgetset")
public class Registro extends UI {
    
    private Button login;
    private Button registro;
    
    public Registro(){
        setContent(Design.read(getClass().getResourceAsStream("Registro.html")));
        iniciarComponentes();
        configurarComportamientos();
    }
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
    }

    private void iniciarComponentes() {
        login = new Button();
        registro = new Button();
        getPage().setTitle("Gestión de ligas");
    }

    private void configurarComportamientos() {
        login.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
            }
        });
        
        registro.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
            }
        });
    }

    @WebServlet(urlPatterns = "/Registro/*", name = "RegistroServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Registro.class, productionMode = false)
    public static class RegistroServlet extends VaadinServlet {
    }
}
